package com.allmsi.plugin.open.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.allmsi.plugin.file.model.FilePo;
import com.allmsi.plugin.file.service.FileService;
import com.allmsi.plugin.open.config.SpringContextOpen;
import com.allmsi.plugin.open.dao.OpenMapper;
import com.allmsi.plugin.open.dao.OpenResourcesMapper;
import com.allmsi.plugin.open.model.OpenPo;
import com.allmsi.plugin.open.model.OpenResourcesPo;
import com.allmsi.plugin.open.model.vo.OpenUrl;
import com.allmsi.plugin.open.model.vo.OpenVo;
import com.allmsi.plugin.open.service.OpenSelect;
import com.allmsi.plugin.open.service.OpenService;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.protocol.ErrorProtocol;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.util.RandomUtil;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class OpenServiceImpl implements OpenService {

	@Resource
	private OpenMapper openDao;

	@Resource
	private OpenResourcesMapper openResourcesDao;

	@Autowired
	private SpringContextOpen springContextOpen;
	
	
	@Value("${upload_file_path.value:null}")
	private String UPLOAD_FILE_PATH;
	
	@Value("${path.value:null}")
	private String PATH;
	
	@Value("${open_path.value:null}")
	private String OPEN_PATH;

	@Value("${tCount.value:null}")
	private String TCOUNT;
	
	@Value("${expiry_date.value:null}")
	private String EXPIRY_DATE;

	private final int DEFAULT_EXPIRY_DATE = 7;

	private final int DEFAULT_TCOUNT = 7;

	@Resource
	private FileService fileService;
	
	private final String RELATIVE_PATH_TYPE = "01";
	
	@Autowired
    private Environment env;


	@Override
	public boolean checkToken(String token) {
		if (StrUtil.isEmpty(token)) {
			return false;
		}
		OpenPo openPo = getOpenPo(token, null);
		return (!(openPo == null || StrUtil.isEmpty(openPo.getId())));
	}

	@Override
	public Object selectByTokenCode(String token, String code) {
		OpenPo openPo = getOpenPo(token, code);
		if (openPo == null || StrUtil.isEmpty(openPo.getId())) {
			return "error";
		}
		if (1 == openPo.getStatus()) {
			return "Invalid";
		}
		List<Object> object = new ArrayList<Object>();
		String className = env.getProperty(openPo.getType());
		if (StrUtil.isEmpty(className)) {
			return null;
		}
		openDao.updateByPrimaryKey(openPo.getId());
		OpenSelect openResourceSelect = springContextOpen.getServiceImpl(OpenSelect.class, className);
		object.addAll(openResourceSelect.resourceSelect(getReseourcesIdsByOpenId(openPo)));
		return object;
	}

	@Override
	public Object insert(String objectId, List<String> resourseIds, List<String> receiverIds, String cUser, String type,
			Integer tCount) {
		if (StrUtil.isEmpty(objectId) || StrUtil.isEmpty(cUser) || StrUtil.isEmpty(type) || resourseIds == null
				|| resourseIds.size() == 0 || receiverIds == null || receiverIds.size() == 0) {
			return new ErrorProtocol();
		}
		List<OpenUrl> volist = new ArrayList<OpenUrl>();
		if (tCount == null || tCount < 0) {
			String tCountString = TCOUNT;
			tCount = StrUtil.isNumeric(tCountString) ? Integer.valueOf(tCountString) : DEFAULT_TCOUNT;
		}
		volist.addAll(insertOpen(objectId, resourseIds, receiverIds, cUser, type, tCount));
		return new SuccessProtocol(volist);
	}

	@Override
	public ListBean openListSelect(String objectId, String receiverId, int page, int pageSize) {
		int total = getTotal(objectId, receiverId);
		List<OpenPo> list = getOpenPoList(objectId, receiverId, page, pageSize);
		List<OpenVo> openVoList = new ArrayList<OpenVo>();
		for (OpenPo openPo : list) {
			OpenVo openVo = new OpenVo(openPo);
			String url = getUrl(openPo.getToken());
			openVo.setUrl(url);
			openVoList.add(openVo);
		}
		return new ListBean(total, openVoList);
	}

	@Override
	public ListBean openListByobjReceIdSelect(String objectId, String receiverId) {
		int total = getTotal(objectId, receiverId);
		List<OpenPo> list = getOpenPoList(objectId, receiverId, null, null);
		List<OpenVo> openVoList = new ArrayList<OpenVo>();
		for (OpenPo openPo : list) {
			OpenVo openVo = new OpenVo(openPo);
			String url = getUrl(openPo.getToken());
			openVo.setUrl(url);
			openVo.setList(getResources(openPo));
			openVoList.add(openVo);
		}
		return new ListBean(total, openVoList);
	}

	@Override
	public Object openMoreSelect(String id) {
		if (StrUtil.isEmpty(id)) {
			return new ErrorProtocol();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("expiry_date", Integer.valueOf(EXPIRY_DATE));
		map.put("id", id);
		OpenPo openPo = new OpenPo();
		openPo = openDao.openSelect(map).get(0);
		if (openPo == null || StrUtil.isEmpty(openPo.getId())) {
			return new ErrorProtocol();
		}
		List<String> reseourcesIds = getReseourcesIdsByOpenId(openPo);
		String className = env.getProperty(openPo.getType());
		if (StrUtil.isEmpty(className)) {
			return new ErrorProtocol();
		}
		OpenVo openVo = new OpenVo(openPo);
		OpenSelect openResourceSelect = springContextOpen.getServiceImpl(OpenSelect.class, className);
		openVo.setList(openResourceSelect.resourceSelect(reseourcesIds));
		return new SuccessProtocol(openVo);
	}

	@Override
	public boolean checkDownload(String token, String code, String resourseId) {
		OpenPo openPo = getOpenPo(token, code);
		if (openPo == null || StrUtil.isEmpty(openPo.getId())) {
			return false;
		}
		List<String> reseourcesIds = getReseourcesIdsByOpenId(openPo);
		if (!reseourcesIds.contains(resourseId)) {
			return false;
		}
		return true;
	}

	@Override
	public void download(String resourseId, HttpServletResponse response, HttpServletRequest request) {
		if (StrUtil.isEmpty(resourseId)) {
			return;
		}
		FilePo fileInfo = fileService.selectById(resourseId);
		if (fileInfo == null || StrUtil.isEmpty(fileInfo.getFilePath())) {
			return;
		}
		String path = fileInfo.getFilePath() + "/" + fileInfo.getnFileName() + fileInfo.getFileType();
		if (RELATIVE_PATH_TYPE.equals(fileInfo.getFilePathType())) {
			path = request.getSession().getServletContext().getRealPath("") + path;
		} else {
			path = UPLOAD_FILE_PATH + path;
		}
		File file = new File(path);
		PrintWriter out = null;
		InputStream fis = null;
		OutputStream toClient = null;
		try {
			if (file.exists()) {
				fis = new BufferedInputStream(new FileInputStream(file));
				response.reset();
				response.setContentType("application/x-download");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + new String(fileInfo.getFileName().getBytes("gb2312"), "iso-8859-1"));
				response.addHeader("Content-Length", "" + file.length());
				toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				byte[] buffer = new byte[1024 * 1024 * 4];
				int i = -1;
				while ((i = fis.read(buffer)) != -1) {
					toClient.write(buffer, 0, i);
				}
			} else {
				out = response.getWriter();
				out.print("<script>");
				out.print("alert(\"没有可下载文件！\")");
				out.print("</script>");
			}
		} catch (IOException ex) {
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.print("<script>");
			out.print("alert(\"没有可下载文件！\")");
			out.print("</script>");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (toClient != null) {
				try {
					toClient.flush();
					toClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	private List<OpenUrl> insertOpen(String objectId, List<String> resourseIds, List<String> receiverIds, String cUser,
			String type, int tCount) {
		List<OpenPo> list = new ArrayList<OpenPo>();
		List<OpenUrl> volist = new ArrayList<OpenUrl>();
		for (String receiveId : receiverIds) {
			String id = UUIDUtil.getUUID();
			String code = RandomUtil.random(100000, 1000000) + " ";
			list.add(new OpenPo(id, id, code, objectId, receiveId, cUser, type, tCount));
			String url = getUrl(id);
			volist.add(new OpenUrl(url, code, receiveId));
		}

		List<OpenResourcesPo> openResPoList = new ArrayList<OpenResourcesPo>();
		for (OpenPo openPo : list) {
			for (String string : resourseIds) {
				openResPoList.add(new OpenResourcesPo(UUIDUtil.getUUID(), openPo.getId(), string));
			}
		}
		int msg = openDao.insertBatch(list);
		openResourcesDao.insertBatch(openResPoList);
		return (msg != 0) ? volist : null;
	}

	private OpenPo getOpenPo(String token, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		String date = EXPIRY_DATE;
		map.put("expiry_date", StrUtil.isNumeric(date) ? Integer.valueOf(date) : DEFAULT_EXPIRY_DATE);
		map.put("token", token);
		map.put("code", code);
		OpenPo openPo = new OpenPo();
		openPo = openDao.selectByTokenCode(map);
		return openPo;
	}

	private List<String> getReseourcesIdsByOpenId(OpenPo openPo) {
		List<String> reseourcesIds = openResourcesDao.selectByOpenId(openPo.getId());
		return reseourcesIds;
	}

	private String getUrl(String id) {
		String path = PATH;
		String openPath = OPEN_PATH;
		String url = path + openPath + "?t=" + id;
		return url;
	}

	private int getTotal(String objectId, String receiverId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("expiry_date", Integer.valueOf(EXPIRY_DATE));
		map.put("objectId", objectId);
		map.put("receiverId", receiverId);
		return openDao.openCount(map);
	}

	private List<OpenPo> getOpenPoList(String objectId, String receiverId, Integer page, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("expiry_date", Integer.valueOf(EXPIRY_DATE));
		map.put("objectId", objectId);
		map.put("receiverId", receiverId);
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		return openDao.openSelect(map);
	}

	private List<Object> getResources(OpenPo openPo) {
		if (openPo == null || StrUtil.isEmpty(openPo.getId())) {
			return null;
		}
		List<String> reseourcesIds = getReseourcesIdsByOpenId(openPo);
		String className = env.getProperty(openPo.getType());
		if (StrUtil.isEmpty(className)) {
			return null;
		}
		OpenSelect openResourceSelect = springContextOpen.getServiceImpl(OpenSelect.class, className);
		return openResourceSelect.resourceSelect(reseourcesIds);
	}

}
