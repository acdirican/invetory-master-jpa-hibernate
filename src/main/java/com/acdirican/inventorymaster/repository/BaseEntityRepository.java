package com.acdirican.inventorymaster.repository;

import javax.persistence.EntityManager;

/**
 * Abstract base class for entity repositories
 *
 * @author Ahmet Cengizhan Dirican
 *
 */
public class BaseEntityRepository {
	protected BaseRepository repository;
	protected EntityManager entityManager;
	public BaseEntityRepository(BaseRepository repository) {
		this.repository = repository;
		this.entityManager = repository.getConnection();
	}
}
