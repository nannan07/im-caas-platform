package com.allmsi.sys.service;

import com.allmsi.sys.model.vo.LoginVo;
import com.allmsi.sys.model.vo.UserVo;

public interface LoginService {

	UserVo login(LoginVo loginVo);

}
