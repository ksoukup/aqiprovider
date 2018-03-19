package com.fdm.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.dao.*;
import com.fdm.factories.UserFactory;
import com.fdm.model.User;
import com.fdm.runner.Client;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(AddUser.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		logger.debug("Session info: " + session.getId());
		if(session.isNew() || !session.getAttributeNames().hasMoreElements()) { 
			session.setAttribute("timesVisit", 0);
		}
		else {
			int timesVisit = (int) session.getAttribute("timesVisit");
			session.setAttribute("timesVisit", timesVisit + 1);
		}
		
		UserDao userDao = new UserDaoSQLImpl();

		User user = new UserFactory().createUser();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));
		
		//userDao.addUser(user);

		request.setAttribute("user", user);
		RequestDispatcher rd = request
				.getRequestDispatcher("./WEB-INF/valid.jsp");
		rd.forward(request, response);
	}

}
