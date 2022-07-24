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

import javax.persistence.ParameterMode;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;
import com.mysql.cj.QueryReturnType;

/**
 * Repository class for the product entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class ProductRepository extends BaseEntityRepository {

	public ProductRepository(BaseRepository repository) {
		super(repository);
	}

	public List<Product> list() {
		return entityManager.createQuery("from Product", Product.class).getResultList();
	}

	/* NamedQuery */
	public List<Product> listMoreThan(double quantity) {
		return entityManager.createNamedQuery("Product.findMoreThan", Product.class).setParameter("quantity", quantity)
				.getResultList();
	}

	/* NamedQuery */
	public List<Product> listLessThan(double quantity) {
		return entityManager.createNamedQuery("Product.findLessThan", Product.class).setParameter("quantity", quantity)
				.getResultList();
	}

	/**
	 * List product whose quantities are equals to the given value using a stored
	 * procedure
	 * 
	 */
	public List<Product> listEquals(double quantity) {
		return entityManager.createNamedStoredProcedureQuery("Product.filterByQuantity")
				.setParameter("quantity", quantity)
				.getResultList();
		
		/* Alternative
		return entityManager.createQuery("select p from Product p WHERE p.quantity = :quantity", Product.class)
				.setParameter("quantity", quantity)
				.getResultList();
		*/
	}

	/**
	 * List delpeted product using a stored procedure
	 * 
	 */
	public List<Product> listDepleteds() {
		//return entityManager.createStoredProcedureQuery("get_depleted_products", Product.class).getResultList();
		return entityManager.createNamedStoredProcedureQuery("Product.getDepleteds")
				.getResultList();
	}

	public Optional<Product> add(Product product) {
		 try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return Optional.of(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
	}

	/* ? Do using JPA */
	public Optional<Product> getWithIndex(int i) {
		//? error prone
		return Optional.of(list().get(i));
	}

	public boolean delete(int ID) {
		Optional<Product> product = getWidthID(ID);
		if (product.isPresent()) {
			return delete(product.get());
		}
		return false;
	}
	
	public boolean delete(Product product) {
		if (product == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(product);
		entityManager.getTransaction().commit();
		return true;
	}

	public Optional<Product> getWidthID(int ID) {
		Product product = entityManager.find(Product.class, ID);
		return product != null 
				? Optional.of(product)
				: Optional.empty();
	}

	public List<Product> find(String name) {
		return entityManager.createNamedQuery("Product.findByName", Product.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}

	public boolean update(Product product) {
		entityManager.getTransaction().begin();
		entityManager.merge(product);
		entityManager.getTransaction().commit();
		return true;
	}

	/* Batch operation */
	public int deleteAll(List<Integer> id_list) {
		StringJoiner ids =  new StringJoiner(",");
		for (int ID : id_list) {
			 ids.add(String.valueOf(ID));
		}
		String sql =  "delete from Product p where p.ID IN (" + ids.toString() + ")" ;
		//System.out.println(sql);
		entityManager.getTransaction().begin();
		int result = entityManager.createQuery(sql).executeUpdate();
		entityManager.getTransaction().commit();
		return result;
	}

	/*Using named query*/
	public int increaseInvetory(int ID, double quantity) {
		entityManager.getTransaction().begin();;
		int result = entityManager.createNamedQuery("Product.increaseInvetory").setParameter("id", ID)
				.setParameter("quantity", quantity).executeUpdate();
		entityManager.getTransaction().commit();
		return result;
	}

	/*Using the entity*/
	public double increaseInvetory(Product product, double quantity) {
		if (product== null && quantity>0) {
			return -1;
		}
		entityManager.getTransaction().begin();
		product.setQuantity(product.getQuantity() + quantity);
		entityManager.merge(product);
		entityManager.getTransaction().commit();
		return quantity;
	}

	public double decreaseInvetory(Product product, double quantity) {
		if (product== null && quantity>0) {
			return -1;
		}
		entityManager.getTransaction().begin();
		product.setQuantity(product.getQuantity() - quantity);
		entityManager.merge(product);
		entityManager.getTransaction().commit();
		return quantity;
	}

}
