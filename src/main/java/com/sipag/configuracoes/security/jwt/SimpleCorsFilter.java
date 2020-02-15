package com.sipag.configuracoes.security.jwt;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("cors filter loogerd");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest reque = (HttpServletRequest) request;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELET,PUT");
		resp.setHeader("Access-Control-Allow-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization");
//		resp.addHeader("X-Frame-Options", "SAMEORIGIN");
//		resp.addHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8400/*");

		
		if ("OPTIONS".equalsIgnoreCase(reque.getMethod())) {
			resp.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}

}
