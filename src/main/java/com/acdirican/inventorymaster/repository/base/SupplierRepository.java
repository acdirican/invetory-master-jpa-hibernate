package com.acdirican.inventorymaster.repository.base;

import com.acdirican.inventorymaster.model.Supplier;

public abstract class SupplierRepository extends Respository<Supplier> {

	public SupplierRepository(RepositoryManager repository) {
		super(repository);
	}

}
