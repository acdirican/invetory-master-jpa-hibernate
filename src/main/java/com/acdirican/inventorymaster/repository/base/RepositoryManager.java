package com.acdirican.inventorymaster.repository.base;


import java.util.Optional;

import javax.persistence.EntityManager;

import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.repository.HibernateRepositoryManagerImpl;
import com.acdirican.inventorymaster.repository.JPARepositoryManagerImpl;
import com.acdirican.inventorymaster.repository.ProductRepositoryImpl;
import com.acdirican.inventorymaster.repository.SupplierRepositoryImpl;

/**
 * Factory to create entity repositories {@link ProductRepositoryImpl} and {@link SupplierRepositoryImpl}, and to connect to the database;
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public abstract  class RepositoryManager {
	public enum RepositoryType {JPA, HIBERNATE};
	
	protected static final String ERROR = "Database error!";
	
	protected static ProductRepository productRepository;
	protected static SupplierRepository supplierRepository;
	protected static Logger logger;
	
	public abstract boolean connect();
	public abstract void close();
	public abstract String metaData(String tableName);
	public abstract String metaData();
	
	public static RepositoryManager getRepository(RepositoryType type) {
		if (type.equals(RepositoryType.JPA)) {
			return new JPARepositoryManagerImpl();
		}
		else {
			return new HibernateRepositoryManagerImpl();
		}
	}
	
	public ProductRepository getProductRepository() {
		return productRepository;
	}
	
	public SupplierRepository getSupplierRepository() {
		return supplierRepository;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public Optional<Supplier> findSupplier(int ID) {
		return supplierRepository.getWidthID(ID);
	}

	protected abstract EntityManager getConnection();
	
	

}
