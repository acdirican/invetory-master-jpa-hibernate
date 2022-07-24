package com.acdirican.inventorymaster.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "log")
public class Log {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer ID;
	
	@Column(name="quantity", nullable = false)
	private Double quantity;
	
	@Column(name="time", nullable = false)
	private LocalDateTime time; 
	
	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;

	@Enumerated(EnumType.STRING)
	@Column(name="type", nullable = false)
	private InventoryMovement type;
	
	public Log() {
	}
	
	public Log(Double quantity, Product product) {
		super();
		this.quantity = quantity;
		this.time = LocalDateTime.now();
		this.product = product;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Log [ID=" + ID + ", quantity=" + quantity + ", time=" + time + ", product=" + product.getName() + "]";
	}
	
	
	
	
	
}
