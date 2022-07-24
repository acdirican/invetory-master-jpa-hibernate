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
import javax.transaction.Transaction;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Log;

/**
 * Looger class to keep track inventory updates using Log entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class Logger extends BaseEntityRepository {

	public Logger(BaseRepository repository) {
		super(repository);
	}

	public List<Log> list() {
		return entityManager.createQuery("from Log", Log.class).getResultList();
	}
	
	public List<Log> listByProduct(int productID) {
		return entityManager.createNamedQuery("Log.filterByProductID", Log.class)
				.setParameter("id", productID)
				.getResultList();
	}
	
	public Optional<Log> add(Log log) {
		if (log != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(log);
				entityManager.getTransaction().commit();
				return Optional.of(log);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Optional.empty();
	}

	/**
	 * Finds the Log matching the given index
	 * 
	 * @param index
	 * @return
	 */
	public Optional<Log> getWidthIndex(int index) {
		//? error prone
		return Optional.of(list().get(index));
	}

	public boolean delete(int ID) {
		Optional<Log> suOptional=getWidthID(ID);
		if (suOptional.isPresent()) {
			return delete(suOptional.get());
		}
		return false;
	}

	public boolean delete(Log log) {
		if(log == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(log);
		entityManager.getTransaction().commit();
		return true;
	}

	public Optional<Log> getWidthID(int ID) {
		Log log =  entityManager.find(Log.class, ID);
		return log != null 
				? Optional.of(log)
				: Optional.empty();
	}

	public boolean update(Log log) {
		if(log == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.merge(log);
		entityManager.getTransaction().commit();
		return true;
	}

	

}
