package com.filter;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "*.do")
public class UserFliter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletReq, ServletResponse servletRes, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)servletReq;
		if(request.getSession().getAttribute("name")!=null)
		{
			chain.doFilter(servletReq, servletRes);
		}
		else 
		{
			
			request.getRequestDispatcher("/login.do").forward(servletReq, servletRes);
		}
				
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
