package com.acdirican.inventorymaster.service;

import java.util.Optional;

import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.repository.base.RepositoryManager;
import com.acdirican.inventorymaster.service.base.LoggerService;
import com.acdirican.inventorymaster.service.base.ProductService;
import com.acdirican.inventorymaster.service.base.ServiceManager;
import com.acdirican.inventorymaster.service.base.SupplierService;

public class ServiceManagerImpl implements ServiceManager{
	
	private RepositoryManager repositoryManager;
	private static ProductService productService;
	private static SupplierService supplierService;
	private static LoggerService loggerService;

	public ServiceManagerImpl(RepositoryManager repositoryManager) {
		this.repositoryManager = repositoryManager;
		productService = new ProductServiceImpl(repositoryManager.getProductRepository());
		supplierService =  new SupplierServiceImpl(repositoryManager.getSupplierRepository());
		loggerService =  new LoggerServiceImpl(repositoryManager.getLogger());
	}
	
	@Override
	public ProductService getProductService() {
		return productService;
	}

	@Override
	public SupplierService getSupplierService() {
		// TODO Auto-generated method stub
		return supplierService;
	}

	@Override
	public LoggerService getLoggerService() {
		// TODO Auto-generated method stub
		return loggerService;
	}

	@Override
	public Optional<Supplier> findSupplier(int ID) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public String metaData() {
		return "Meta data service not implemented yet!";
	}



}
