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

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;

/**
 * Fundamental repository class responsible for DB connection and entity repository initialization
 * 
 * JPA: -> EntityManagerFacotry -> Session -> createQuery, createNamed, find, persist, merge, remove
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class RepositoryHibernate extends BaseRepository{

	private static Session session;
	private static SessionFactory sessionFactory;
	
	
	public boolean connect() {
		
	    Configuration cfg =  new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		
		productRepository = new ProductRepository(this);
		supplierRepository =  new SupplierRepository(this);
		
		return true;
	}

	public void close() {
		if (session != null) {
			session.close();
			sessionFactory.close();
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
	
	public ProductRepository getProductRepository() {
		return productRepository;
	}
	
	public SupplierRepository getSupplierRepository() {
		return supplierRepository;
	}

	public Optional<Supplier> findSupplier(int ID) {
		return supplierRepository.getWidthID(ID);
	}
	
	public Session getConnection() {
		return session;
	}
	
	
	
}
