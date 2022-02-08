package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor 
extends HandlerInterceptorAdapter{
	//로깅을 위한 변수
	private static final Logger logger=
			LoggerFactory.getLogger(SampleInterceptor.class);
	
	//선처리
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("pre handle 작동....");

		return true;//true면 계속진행, false면 멈춤
	}
	
	//후처리
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("post handle 작동....");

	}

}
