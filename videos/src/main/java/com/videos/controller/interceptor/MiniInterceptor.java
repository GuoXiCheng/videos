package com.videos.controller.interceptor;

import com.videos.Utils.JsonResult;
import com.videos.Utils.JsonUtils;
import com.videos.Utils.RedisOperator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class MiniInterceptor implements HandlerInterceptor {

	@Autowired
	public RedisOperator redis;

	public static final String USER_REDIS_SESSION = "user-redis-session";

	//在controller调用之前拦截请求
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userId = request.getHeader("headerUserId");
		String userToken = request.getHeader("headerUserToken");
		System.out.println("进入拦截器");
		if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)) {
//			String uniqueToken = redis.get(USER_REDIS_SESSION + ":" + userId);
			String uniqueToken = "token";
			if(StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)) {
				System.out.println("请登录");
				returnErrorResponse(response, new JsonResult().errorTokenMsg("请登录..."));
				return false;
			}
//			}else {
//				if(!uniqueToken.equals(userToken)) {
//					System.out.println("账户被挤出...");
//					returnErrorResponse(response,new JsonResult().errorTokenMsg("账号被挤出..."));
//					return false;
//				}
//			}
		}else {
			System.out.println("请登录");
			returnErrorResponse(response,new JsonResult().errorTokenMsg("请登录..."));
			return false;
		}

		return true;
		//返回false，请求被拦截返回
		//返回true，请求ok，可以继续执行

	}


	public void returnErrorResponse(HttpServletResponse response,JsonResult result)
		throws IOException,UnsupportedEncodingException{
		OutputStream out = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json");
			out = response.getOutputStream();
			out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
			out.flush();
		}finally {
			if(out!=null) {
				out.close();
			}
		}
	}

	//请求controller之后，渲染视图之前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	//请求controller之后，渲染视图之后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
