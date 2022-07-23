package com.acdirican.inventorymaster.repository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.acdirican.inventorymaster.model.Supplier;

/**
 * Factory to create entity repositories {@link ProductRepository} and {@link SupplierRepository}, and to connect to the database;
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public abstract  class BaseRepository {
	public enum RepositoryType {JPA, HIBERNATE};
	
	protected static final String ERROR = "Database error!";
	
	protected static ProductRepository productRepository;
	protected static SupplierRepository supplierRepository;
	
	public abstract boolean connect();
	public abstract void close();
	public abstract String metaData(String tableName);
	public abstract String metaData();
	
	public static BaseRepository getRepository(RepositoryType type) {
		if (type.equals(RepositoryType.JPA)) {
			return new RepositoryJPA();
		}
		else {
			return new RepositoryHibernate();
		}
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

	protected abstract EntityManager getConnection();
	

}
