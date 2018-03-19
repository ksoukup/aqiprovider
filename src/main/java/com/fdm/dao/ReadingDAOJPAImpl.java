package com.fdm.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.model.Reading;
import com.fdm.model.ReadingId;

public class ReadingDAOJPAImpl implements ReadingDAO {
	private final static Logger logger = LogManager.getLogger(ReadingDAOJPAImpl.class);
	private EntityManager em;
	
	public ReadingDAOJPAImpl(EntityManager em) {
		this.em = em;
	}
		
	/* (non-Javadoc)
	 * @see com.fdm.dao.ReadingDAO#addReading(com.fdm.model.Reading)
	 */
	@Override
	public void addReading(Reading reading) {
		em.merge(reading);
		logger.trace("Added " + reading.toString());
	}
	
	/* (non-Javadoc)
	 * @see com.fdm.dao.ReadingDAO#removeReading(java.lang.String, java.sql.Timestamp, java.lang.String)
	 */
	@Override
	public void removeReading(String regionName, Timestamp timeStamp, String type) {
		ReadingId readingId = new ReadingId(regionName,timeStamp,type);
		Reading reading = em.find(Reading.class, readingId);
		em.getTransaction().begin();
		em.remove(reading);
		em.getTransaction().commit();
	}
	
	/* (non-Javadoc)
	 * @see com.fdm.dao.ReadingDAO#updateReading(com.fdm.model.Reading)
	 */
	@Override
	public void updateReading(Reading reading) {
		em.merge(reading);
		
	}
	
	/* (non-Javadoc)
	 * @see com.fdm.dao.ReadingDAO#getReading(java.lang.String, java.sql.Timestamp, java.lang.String)
	 */
	@Override
	public Reading getReading(String regionName, Timestamp timeStamp, String type) {
		ReadingId readingId = new ReadingId(regionName,timeStamp,type);
		Reading reading = em.find(Reading.class, readingId);
		return reading;
	}
	
	/* (non-Javadoc)
	 * @see com.fdm.dao.ReadingDAO#getReadingsByRegion(java.lang.String)
	 */
	@Override
	public List<Reading> getReadingsByRegion(String regionName){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r FROM Reading r WHERE r.regionName = ");
		sb.append("'");
		sb.append(regionName);
		sb.append("'");
		return em.createQuery(sb.toString(), Reading.class).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.fdm.dao.ReadingDAO#getReadingsLatest()
	 */
	@Override
	public List<Reading> getReadingsLatest(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r1 FROM Reading r1 WHERE r1.timeStamp =");
		sb.append("(SELECT MAX(r2.timeStamp) FROM Reading r2 WHERE r1.regionName = r2.regionName) ORDER BY r1.regionName");
		logger.trace("Retrieving the latest readings from DB" );
		return em.createQuery(sb.toString(), Reading.class).getResultList();
		
		
	}
}
