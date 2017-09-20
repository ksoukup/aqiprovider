package com.fdmgroup.dao;

import java.io.StringWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.model.Record;
import com.fdmgroup.model.Region;

public class RecordDaoJpa {

	EntityManager em = null;
	private static final Logger logger = LogManager.getLogger(RecordDaoJpa.class);
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aqiprovider");

	public List<Record> getCurrentReadingForARegion(String id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT r1 FROM Record r1 WHERE r1.timestamp = (SELECT MAX(r2.timestamp) FROM Record r2 WHERE r2.region.id = :id) AND r1.region.id = :id");
		q.setParameter("id", id);
		List<Record> records = q.getResultList();
		logger.trace("Got Current Reading for region: " + id);
		return records;
	}

	public String getCurrentReadingForARegionAsAJSON(String id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT r1 FROM Record r1 WHERE r1.timestamp = (SELECT MAX(r2.timestamp) FROM Record r2 WHERE r2.region.id = :id) AND r1.region.id = :id");
		q.setParameter("id", id);
		List<Record> records = q.getResultList();
		StringBuilder sb = new StringBuilder();

		
		/*for(Record record : records){
			try {
				JAXBContext jc = JAXBContext.newInstance(Record.class);
				String jsonRecord = marshalledStreamToString(jc, record);
				sb.append(jsonRecord);
			} catch (JAXBException e) {
				logger.error("Error converting to JSON element", e);
			}
		}*/

		logger.trace("Got Current Reading for region: " + id);
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Record.class);
			sb.append( marshalledStreamToString(jc, records));
		} catch (JAXBException e) {
			logger.error("Error converting to JSON element", e);
		}

		return sb.toString();
	}

	private String marshalledStreamToString(JAXBContext jc, Object pObject) throws JAXBException {

		StringWriter sw = new StringWriter();

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty("eclipselink.media-type", "application/json");
		marshaller.marshal(pObject, sw);

		return sw.toString();
	}

}
