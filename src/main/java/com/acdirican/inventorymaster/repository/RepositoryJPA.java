package com.acdirican.inventorymaster.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;

/**
 * Fundamental repository class responsible for DB connection and entity repository initialization
 * 
 * JPA: -> EntityManagerFacotry -> EntityManager -> createQuery, createNamed, find, persist, merge, remove
 * 
 * @author Ahmet Cengizhan Dirican
 * 
 *https://stackoverflow.com/questions/10607196/how-to-get-database-metadata-from-entity-manager
 */
public class RepositoryJPA extends BaseRepository {

	static final String ERROR = "Database error!";
	
	private static EntityManager entitiyManager;
	private static EntityManagerFactory entityManagerFactory;
	
	public boolean connect() {
		
		String myPersistenceUnit = "InventoryMasterJPAHibernate";
	      
		entityManagerFactory = Persistence.createEntityManagerFactory(myPersistenceUnit); 
		entitiyManager = entityManagerFactory.createEntityManager();
		
		productRepository = new ProductRepository(this);
		supplierRepository =  new SupplierRepository(this);
		logger =  new Logger(this);
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
