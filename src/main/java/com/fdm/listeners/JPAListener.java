package com.fdm.listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JPAListener implements ServletContextListener {

	private static EntityManagerFactory emf;
	
	
    public JPAListener() {
      
    }
    
    @Override
    public void contextInitialized(ServletContextEvent event)  { 
    	emf = Persistence.createEntityManagerFactory("airquality");
    	event.getServletContext().setAttribute("emf", emf);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	if (emf != null) emf.close();
    }
    
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }
	
}
