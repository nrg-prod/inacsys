package com.inacsys.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.managedBean.LoginMB;

public class SessionFilter implements Filter {
	final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	private ArrayList<String> urlList;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			logger.debug("---------------inside filter----------------");
			HttpServletRequest req = (HttpServletRequest) request;
			logger.debug("filter1");
			HttpServletResponse res = (HttpServletResponse) response;
			logger.debug("filter2");
			HttpSession ses = req.getSession(false);
			logger.debug("filter3");
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/pages/xhtml/login.xhtml") > 0
					|| (ses != null && ses.getAttribute("") != null)
					|| reqURI.indexOf("/public") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				logger.debug("filter4");
				chain.doFilter(request, response);

			}

			else {
				logger.debug("filter5");
				res.sendRedirect(req.getContextPath()
						+ "/pages/xhtml/login.xhtml");
			}

		} catch (Exception e) {
			logger.debug("filter exception6");
			logger.error("Inside Exception", e);
		} finally {

		}

	}

	public void init(FilterConfig config) throws ServletException {

	}

}
