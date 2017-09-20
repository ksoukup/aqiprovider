package com.fdmgroup.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.dao.RecordDaoJpa;

/**
 * Servlet implementation class AqiProviderCurrentServlet
 */
@WebServlet("/AqiProviderCurrent")
public class AqiProviderCurrentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Logger logger = LogManager.getLogger(AqiProviderCurrentServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AqiProviderCurrentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("ServedAt", "Served at " + request.getContextPath());
		RecordDaoJpa recordDao = new RecordDaoJpa();
		String records = recordDao.getCurrentReadingForARegionAsAJSON("rNO");
		logger.trace("Servlet got a JSON Records as String \n", AqiProviderCurrentServlet.class);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		response.getWriter().write(records);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
