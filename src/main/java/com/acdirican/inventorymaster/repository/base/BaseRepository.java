package com.acdirican.inventorymaster.repository.base;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.acdirican.inventorymaster.model.BaseEntity;


/**
 * Abstract base class for entity repositories
 *
 * @author Ahmet Cengizhan Dirican
 *
 */
public abstract class BaseRepository<T extends BaseEntity> {
	
	protected RepositoryManager repository;
	protected EntityManager entityManager;
	
	public BaseRepository(RepositoryManager repository) {
		this.repository = repository;
		this.entityManager = repository.getConnection();
	}
	
	public abstract List<T> list();
	public abstract Optional<T> add(T object);
	public abstract Optional<T> getWidthIndex(int index);
	public abstract boolean delete(int ID);
	public abstract boolean delete(T object);
	public abstract Optional<T> getWidthID(int ID);
	public abstract boolean update(T object);

}
