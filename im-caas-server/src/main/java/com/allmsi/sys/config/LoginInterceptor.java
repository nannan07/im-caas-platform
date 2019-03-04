package com.allmsi.sys.config;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.allmsi.cache.Cache;
import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.google.gson.Gson;

/**
 * Login connector 拦截非法访问的链接和不存在的用户访问请求
 * 
 * @author sunnannan
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final CacheInstenceSubclass cacheInstenceSubclass;

	public LoginInterceptor(CacheInstenceSubclass cacheInstenceSubclass) {
		this.cacheInstenceSubclass = cacheInstenceSubclass;
	}

	/**
	 * 获取缓存对象
	 * 
	 * @return
	 */
	public Cache getCache() {
		return cacheInstenceSubclass.getCache();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		if (getCache().isExists(CacheKeyPrefix.TOKEN.getValue() + token)) {
			getCache().expire(CacheKeyPrefix.TOKEN.getValue() + token, 30 * 60);
			return true;
		} else {
			String result = new Gson().toJson(new WarnProtocol(0));
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			return false;
		}
	}
}
