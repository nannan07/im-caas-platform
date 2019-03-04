package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjProductCompanyMapper;
import com.allmsi.caas.model.PjProductCompany;
import com.allmsi.caas.model.vo.PjProductCompanyVo;
import com.allmsi.caas.service.ProductCompanyService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class ProductCompanyServiceImpl implements ProductCompanyService {

	@Resource
	private PjProductCompanyMapper ProductCompanyDao;

	@Override
	public boolean insertBatch(String pjid, List<PjProductCompanyVo> productCompanyList) {
		if (StrUtil.isEmpty(pjid)) {
			return false;
		}
		ProductCompanyDao.deleteByPjid(pjid);
		if (productCompanyList != null && productCompanyList.size() > 0) {
			List<PjProductCompany> list = new ArrayList<PjProductCompany>();
			for (PjProductCompanyVo pCVo : productCompanyList) {
				PjProductCompany productCompany = new PjProductCompany(pCVo);
				productCompany.setId(UUIDUtil.getUUID());
				productCompany.setPjid(pjid);
				list.add(productCompany);
			}
			ProductCompanyDao.insertBatch(list);
		}
		return true;
	}

	@Override
	public int deleteByPjid(String pjid) {
		return ProductCompanyDao.deleteByPjid(pjid);
	}

	@Override
	public List<PjProductCompanyVo> selectProductCompanyList(String pjid) {
		if (StrUtil.isEmpty(pjid)) {
			return null;
		}
		List<PjProductCompany> list = ProductCompanyDao.selectProductCompanyList(pjid);
		List<PjProductCompanyVo> productCompanyList = new ArrayList<PjProductCompanyVo>();// 出品公司
		for (PjProductCompany c : list) {
			PjProductCompanyVo productCVo = new PjProductCompanyVo(c);
			productCompanyList.add(productCVo);
		}
		return productCompanyList;
	}

}
