package com.acdirican.inventorymaster.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.acdirican.inventorymaster.repository.RepositoryJPA;

@Entity
@Table(name ="supplier")
@NamedQueries({
		@NamedQuery(name = "Supplier.findByName", query = "SELECt s FROM Supplier s WHERE s.name LIKE :name"),
		@NamedQuery(name = "Supplier.listProducts", query ="SELECT s FROM Supplier s WHERE s.ID = :id")} )
public class Supplier implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	
	private String name;
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<Product> products;
	
	public Supplier() {}
	
	public Supplier(Integer iD, String name) {
		super();
		this.ID = iD;
		this.name = name;
	}
	public Supplier(String name) {
		this(null, name);
	}
	public Supplier(int ID) {
		this.ID=ID;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Supplier [ID=" + ID + ", name=" + name + ", products=" + products + "]";
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
}
