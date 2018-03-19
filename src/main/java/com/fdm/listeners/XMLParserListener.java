package com.fdm.listeners;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.controllers.RefreshReadings;

public class XMLParserListener implements ServletContextListener {
	 private ScheduledExecutorService    scheduler    = null;
	 private Logger logger = LogManager.getLogger(XMLParserListener.class);
	
	
	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            logger.trace("Scheduler Shutting down successfully " + new Date());
            scheduler.shutdown();
        } catch (Exception ex) {
        }
    }

	@Override
    public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("emf");
		if (emf != null) {
	    	RefreshReadings rr = new RefreshReadings();
	    	rr.runParser(emf);
	        logger.trace("Completed Running XML Parser at: " + new Date());
		}
		else logger.error("Entity manager Factory is null");
        if ((scheduler == null) || (!scheduler.isTerminated())) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            LocalDateTime nextWholeHour = LocalDateTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS).plusMinutes(10);
            Duration duration = Duration.between(LocalDateTime.now(), nextWholeHour);
            scheduler.scheduleAtFixedRate(new ScheduledTask(emf), duration.getSeconds(), 1, TimeUnit.SECONDS);
        }
    }

}

class ScheduledTask extends TimerTask {
	private Logger logger = LogManager.getLogger(XMLParserListener.class);
    private EntityManagerFactory emf;
	
	ScheduledTask(EntityManagerFactory emf){
		if (emf != null) {
			this.emf = emf;
		}
		else logger.error("Entity manager Factory is null");
	}
	
	public void run() {
    	RefreshReadings rr = new RefreshReadings();
    	rr.runParser(emf);
        logger.trace("Completed Running XML Parser at: " + new Date());
        
    }
}
