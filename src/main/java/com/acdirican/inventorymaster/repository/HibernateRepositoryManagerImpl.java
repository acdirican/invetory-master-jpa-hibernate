package com.acdirican.inventorymaster.repository;

import java.util.Optional;
import java.util.StringJoiner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.repository.base.ProductRepository;
import com.acdirican.inventorymaster.repository.base.RepositoryManager;
import com.acdirican.inventorymaster.repository.base.SupplierRepository;

/**
 * Fundamental repository class responsible for DB connection and entity repository initialization
 * 
 * JPA: -> EntityManagerFacotry -> Session -> createQuery, createNamed, find, persist, merge, remove
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class HibernateRepositoryManagerImpl extends RepositoryManager{

	private static Session session;
	private static SessionFactory sessionFactory;
	
	
	public boolean connect() {
		
	    Configuration cfg =  new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		
		productRepository = new ProductRepositoryImpl(this);
		supplierRepository =  new SupplierRepositoryImpl(this);
		
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
