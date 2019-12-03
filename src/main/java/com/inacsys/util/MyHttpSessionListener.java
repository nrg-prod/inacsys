package com.inacsys.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.managedBean.LoginMB;

public class MyHttpSessionListener implements HttpSessionListener {
	final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);
	private static final long serialVersionUID = 1L;
	LoginAccess loginaccess = new LoginAccess();

	public MyHttpSessionListener() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void sessionCreated(HttpSessionEvent event) {

		logger.debug("ID Session Created: " + event.getSession().getId());
		event.getSession().setMaxInactiveInterval(24 * 3600); // in seconds
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		try {
			HttpSession session = event.getSession();
			String userName = (String) session.getAttribute("username");
			logger.debug("User name:: " + userName);
			loginaccess.setStatus("deactive");
			loginaccess.setUsername(userName);
			DemoController controller = null;
			ApplicationContext ctx = WebApplicationContextUtils
					.getWebApplicationContext(event.getSession()
							.getServletContext());
			controller = (DemoController) ctx.getBean("controller");
			controller.userLogin(loginaccess);
			logger.debug("ID Session Destroyed: " + event.getSession().getId());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

}
