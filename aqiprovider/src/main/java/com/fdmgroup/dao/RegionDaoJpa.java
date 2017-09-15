package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.fdmgroup.model.Region;

public class RegionDaoJpa implements RegionDaoInt {
	
	EntityManager em = null;
	private static final Logger logger = LogManager.getLogger(RegionDaoJpa.class);
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aqiprovider");
	
	
	/* (non-Javadoc)
	 * @see com.fdmgroup.dao.RegionDaoInt#addRegion(com.fdmgroup.model.Region)
	 */
	@Override
	public void updateRegion(Region region){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(region);
		em.getTransaction().commit();
		em.close();
		logger.trace("Updated Region " + region.getId() + " With " + region.getRecords().size() + " record(s)" );
		
	}

	@Override
	public List<Region> getCurrentReadingsForAllRegions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Region getCurrentReadingForARegion(String id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT r1 FROM Region r1 WHERE r1.id = :id AND r1.(SELECT r2 FROM ");
		q.setParameter("id", id);
		Region region = (Region)q.getSingleResult();
		logger.trace("Got Current Reading for region: " + id);
		return region;
	}

	@Override
	public Region getLast24HoursForRegion(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Region getAllReadingsForARegion(String id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT r FROM Region r WHERE r.id = :id");
		q.setParameter("id", id);
		Region region = (Region)q.getSingleResult();
		logger.trace("Got All Readings for region: " + id);
		return region;
	}
	
	public String[] getAllRegionIds(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNativeQuery("SELECT id FROM REGION");
		String[] regionIds = (String[]) q.getResultList().toArray(new String[0]);
		logger.trace("Got all regions with total of " + regionIds.length);
		return regionIds;
		
	}
	
	@Override
	public void removeRegion(String id){
		
		
	}

}
