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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.dao.ReadingDAO;
import com.fdm.dao.ReadingDAOJPAImpl;
import com.fdm.model.Reading;

/**
 * Servlet implementation class GetRegionInfo
 */

public class GetRegionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final static Logger logger = LogManager.getLogger(GetRegionInfo.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRegionInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		EntityManager em = ((EntityManagerFactory)context.getAttribute("emf")).createEntityManager();
		ReadingDAO readingDAO = new ReadingDAOJPAImpl(em);
		request.getRequestURL();
		logger.trace("Region Requested " + request.getParameter("regionName"));
		List<Reading> readingsList = readingDAO.getReadingsByRegion(request.getParameter("regionName"));
		request.setAttribute("readings", readingsList);
		
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/getRegionInfo.jsp");
		rd.forward(request, response);
	}

}
