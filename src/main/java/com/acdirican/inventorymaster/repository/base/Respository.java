package com.acdirican.inventorymaster.repository.base;

import java.util.List;

import com.acdirican.inventorymaster.model.BaseEntity;

public abstract class Respository<T extends BaseEntity> extends BaseRepository<T> {
	
	public Respository(RepositoryManager repository) {
		super(repository);
	}
	
	public abstract List<T> find(String name);
	/* Batch operation */
	public abstract int deleteAll(List<Integer> id_list);
}
