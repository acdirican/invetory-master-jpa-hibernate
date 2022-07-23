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
import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.model.Supplier;

/**
 * Repository class for the supplier entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class SupplierRepository extends BaseEntityRepository {

	public SupplierRepository(BaseRepository repository) {
		super(repository);
	}

	public List<Supplier> list() {
		return entityManager.createQuery("from Supplier", Supplier.class).getResultList();
	}

	public Optional<Supplier> add(Supplier supplier) {
		if (supplier != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(supplier);
				entityManager.getTransaction().commit();
				return Optional.of(supplier);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Optional.empty();
	}

	/**
	 * Finds the Supplier matching the given index
	 * 
	 * @param index
	 * @return
	 */
	public Optional<Supplier> getWidthIndex(int index) {
		//? error prone
		return Optional.of(list().get(index));
	}

	public boolean delete(int ID) {
		Optional<Supplier> suOptional=getWidthID(ID);
		if (suOptional.isPresent()) {
			return delete(suOptional.get());
		}
		return false;
	}

	public boolean delete(Supplier supplier) {
		if(supplier == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(supplier);
		entityManager.getTransaction().commit();
		return true;
	}

	public Optional<Supplier> getWidthID(int ID) {
		Supplier supplier =  entityManager.find(Supplier.class, ID);
		return supplier != null 
				? Optional.of(supplier)
				: Optional.empty();
	}

	/* Prepared Statement */
	public List<Product> getProducts(Supplier supplier) {
		 return entityManager.createNamedQuery("Supplier.listProducts", Product.class)
				.setParameter("id", supplier.getID()) 
				.getResultList();
	}

	public boolean update(Supplier supplier) {
		if(supplier == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.merge(supplier);
		entityManager.getTransaction().commit();
		return true;
	}

	public List<Supplier> find(String name) {
		return entityManager.createNamedQuery("Supplier.findByName", Supplier.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}

	/* Batch operation */
	public int deleteAll(List<Integer> id_list) {
		StringJoiner ids =  new StringJoiner(",");
		for (int ID : id_list) {
			 ids.add(String.valueOf(ID));
		}
		String sql =  "delete from Supplier s where s.ID IN (" + ids.toString() + ")" ;
		//System.out.println(sql);
		entityManager.getTransaction().begin();
		int result = entityManager.createQuery(sql).executeUpdate();
		entityManager.getTransaction().commit();
		return result;
	}
}
