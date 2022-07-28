package com.acdirican.inventorymaster.cli;


import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Log;
import com.acdirican.inventorymaster.service.base.LoggerService;

/**
 * Cli for Log entity
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class LogCli extends AbstractCLi {
	private LoggerService loggerService;

	public LogCli(Cli cli, LoggerService loggerService) {
		super(cli);
		this.loggerService = loggerService;
		this.scanner = Utils.scanner;
	}

	String getWithIndex(int index) {
		Optional<Log> logOp = loggerService.getWidthIndex(index);
		if (logOp.isPresent()) {
			Log log = logOp.get();
			System.out.println(log);
			return "Log get with Index is succesfull.";
		}

		return Error.ERROR + "Log with the index number " + index + " could not be found!";
	}

	String getWidthID(int ID) {
		Optional<Log> logOp = loggerService.getWidthID(ID);
		if (logOp.isPresent()) {
			Log log = logOp.get();
			System.out.println(log);
			return "Log get with ID is succesfull.";
		}
		return Error.ERROR + "Log with the ID number " + ID + " could not be found!";
	}

	
	static void printLogList(List<Log> logs) {
		System.out.printf("%-10s %-10s %-10s %-30s %-26s\n", "ID", "Quantity", "Type", "Product", "Time");
		Utils.line();
		for (Log log : logs) {
			System.out.printf("%-10d %-10f %-10s %-30s %-26s\n", log.getID(), log.getQuantity()
					, log.getType()
					, log.getProduct().toShortString(), log.getTime());
		}
		Utils.line();
	}

	String list() {
		List<Log> logs = loggerService.list();
		if (logs == null) {
			return Error.DBERROR;
		}
		printLogList(logs);
		return logs.size() + " logs have been successfull listed.";

	}
	
	public String list(int ID) {
		List<Log> logs = loggerService.listByProduct(ID);
		if (logs == null) {
			return Error.DBERROR;
		}
		printLogList(logs);
		return logs.size() + " logs have been successfull listed.";

	}

	

}
