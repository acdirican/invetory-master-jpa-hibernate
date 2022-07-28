package com.acdirican.inventorymaster.repository.base;

import java.util.List;

import com.acdirican.inventorymaster.model.Log;

/**
 * Abstract base class for entity repositories
 *
 * @author Ahmet Cengizhan Dirican
 *
 */
public abstract class Logger extends BaseRepository<Log> {

	public Logger(RepositoryManager repository) {
		super(repository);
	}
	
	public abstract List<Log> listByProduct(int productID);
	
	

}
