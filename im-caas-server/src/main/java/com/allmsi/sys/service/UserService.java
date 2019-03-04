package com.allmsi.sys.service;

import java.util.List;

import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.vo.UserVo;


/**
 * 用户信息
 * @author sunnannan
 *
 */
public interface UserService {

	List<UserVo> select(UserVo userVo);

	ListBean selectPage(UserVo userVo, Integer page, Integer pageSize);

    UserVo selectByPrimaryKey(String id);

	List<UserVo> selectByDeptId(String deptId);

	List<UserVo> selectByRoleId(String roleId);
	
	int checkUnique(UserVo userVo);

	String insert(UserVo userVo);

	String insert(UserVo userVo, boolean isPwdMD5);

    boolean updateByPrimaryKeySelective(UserVo userVo);

	boolean deleteByPrimaryKey(String id);

	boolean pwdUpdate(String userId, String psw, String newPsw);

}
