package com.fdm.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.dao.ReadingDAO;
import com.fdm.dao.ReadingDAOJPAImpl;
import com.fdm.model.Reading;

public class WelcomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext context = getServletContext();
		EntityManager em = ((EntityManagerFactory)context.getAttribute("emf")).createEntityManager();
		ReadingDAO readingDAO = new ReadingDAOJPAImpl(em);
		List<Reading> readingsList = readingDAO.getReadingsLatest();
		
		req.setAttribute("message", "Most Recent Air Quality Readings");
		req.setAttribute("readings", readingsList);
		RequestDispatcher rd = req
				.getRequestDispatcher("./WEB-INF/welcomePage.jsp");
		rd.forward(req, resp);
	}

}
