package com.acdirican.inventorymaster.repository;


import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.acdirican.inventorymaster.repository.base.RepositoryManager;

/**
 * Fundamental repository class responsible for DB connection and entity repository initialization
 * 
 * JPA: -> EntityManagerFacotry -> EntityManager -> createQuery, createNamed, find, persist, merge, remove
 * 
 * @author Ahmet Cengizhan Dirican
 * 
 *https://stackoverflow.com/questions/10607196/how-to-get-database-metadata-from-entity-manager
 */
public class JPARepositoryManagerImpl extends RepositoryManager {

	static final String ERROR = "Database error!";
	
	private static EntityManager entitiyManager;
	private static EntityManagerFactory entityManagerFactory;
	
	public boolean connect() {
		
		String myPersistenceUnit = "InventoryMasterJPAHibernate";
	      
		entityManagerFactory = Persistence.createEntityManagerFactory(myPersistenceUnit); 
		entitiyManager = entityManagerFactory.createEntityManager();
		
		productRepository = new ProductRepositoryImpl(this);
		supplierRepository =  new SupplierRepositoryImpl(this);
		logger =  new LoggerImpl(this);
		return true;
	}

	public void close() {
		if (entitiyManager != null) {
			entitiyManager.close();
			entityManagerFactory.close();
		}
	}
	
	public String metaData(String tableName) {
		StringJoiner joiner =  new StringJoiner("\n");
		joiner.add("Tables:");
		return joiner.toString();
	}
	
	public String metaData()  {

		StringJoiner joiner = new StringJoiner("\n");

		joiner.add("Database:");
		return joiner.toString();
	}
	
	
	public EntityManager getConnection() {
		return entitiyManager;
	}
	
	
	
}
