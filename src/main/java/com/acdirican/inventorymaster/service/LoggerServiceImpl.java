package com.acdirican.inventorymaster.service;


import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Log;
import com.acdirican.inventorymaster.repository.base.Logger;
import com.acdirican.inventorymaster.service.base.LoggerService;

/**
 * Looger class to keep track inventory updates using Log entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class LoggerServiceImpl implements LoggerService {
	private Logger logger;

	public LoggerServiceImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public List<Log> list() {
		return logger.list();
	}

	@Override
	public Optional<Log> add(Log object) {
		return logger.add(object);
	}

	@Override
	public Optional<Log> getWidthIndex(int index) {
		return logger.getWidthIndex(index);
	}

	@Override
	public boolean delete(int ID) {
		return logger.delete(ID);
	}

	@Override
	public boolean delete(Log object) {
		return logger.delete(object);
	}

	@Override
	public Optional<Log> getWidthID(int ID) {
		return logger.getWidthID(ID);
	}

	@Override
	public boolean update(Log object) {
		return logger.update(object);
	}

	@Override
	public List<Log> listByProduct(int productID) {
		return logger.listByProduct(productID);
	}
	

}
