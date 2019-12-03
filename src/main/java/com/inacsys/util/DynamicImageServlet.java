package com.inacsys.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class DynamicImageServlet
 */
public class DynamicImageServlet extends HttpServlet {
	final Logger logger = LoggerFactory.getLogger(DynamicImageServlet.class);
	private static final long serialVersionUID = 1L;

	public DynamicImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			// Get image file.

			String file = request.getParameter("file");
			logger.debug("image ---------->" + file);

			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream("c:/product/" + file));

			// Get image contents.
			byte[] bytes = new byte[in.available()];

			in.read(bytes);
			in.close();

			// Write image contents to response.
			response.getOutputStream().write(bytes);

		} catch (IOException e) {

			logger.error("Inside Exception", e);

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
