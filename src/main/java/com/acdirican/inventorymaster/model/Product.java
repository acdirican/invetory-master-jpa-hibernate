package com.acdirican.inventorymaster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 * Product entity. This class represents a product in the database.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */

@Entity
@Table(name ="product")
@NamedQueries( {
	@NamedQuery(name = "Product.findByName"
			, query = "SELECT p FROM Product p WHERE p.name LIKE :name" ),
	@NamedQuery(name = "Product.findMoreThan"
	, query ="SELECT p FROM Product p WHERE p.quantity >= : quantity"),
	@NamedQuery(name = "Product.findLessThan"
	, query ="SELECT p FROM Product p WHERE p.quantity <= : quantity"),
	@NamedQuery(name = "Product.increaseInvetory"
	, query ="UPDATE Product p SET p.quantity = p.quantity + :quantity Where p.ID = :id")
})

//https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/named-stored-procedure.html
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name="Product.getDepleteds",
		procedureName = "get_depleted_products",
		resultClasses = {Product.class}),
	
	@NamedStoredProcedureQuery(
		name="Product.filterByQuantity",
		procedureName = "get_products_byquantity",
		resultClasses = {Product.class},
		parameters = {
				@StoredProcedureParameter(name="quantity", mode = ParameterMode.IN ,type=Double.class)
		}) 
})


public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	
	@Column(nullable = false)
	private String name;
	
	@Column(columnDefinition = "double default 0" )
	private Double quantity;
	
	@ManyToOne
	@JoinColumn(name = "SupplierID")
	private Supplier supplier;
	
	public Product() {}
	
	public Product(Integer iD, String name, double quantity, Supplier supplier) {
		super();
		this.ID = iD;
		this.name = name;
		this.quantity = quantity;
		this.supplier = supplier;
	}

	public Product(String name, double quantity) {
		this(null, name, quantity, null);
	}

	public Product(Integer ID, String name, double quantity) {
		this(null, name, quantity, null);
	}


	public Product(String name, double quantity, Supplier supplier) {
		this(null, name, quantity, supplier);
	}

	public Integer getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Override
	public String toString() {
		return "Product [ID=" + ID + ", name=" + name + ", quantity=" + quantity + ", supplier:" + supplier.getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		return true;
	}

	public Supplier getSupplier() {
		return supplier;
	}
	
	
	
}
