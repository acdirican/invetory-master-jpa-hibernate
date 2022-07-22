package com.acdirican.inventorymaster.repository;

import javax.persistence.EntityManager;

/**
 * Abstract base class for entity repositories
 *
 * @author Ahmet Cengizhan Dirican
 *
 */
public class AbstracyRepository {
	protected Repository repository;
	protected EntityManager entityManager;
	public AbstracyRepository(Repository repository) {
		this.repository = repository;
		this.entityManager = repository.getConnection();
	}
}
