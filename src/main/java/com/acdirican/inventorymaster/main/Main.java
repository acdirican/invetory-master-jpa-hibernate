package com.acdirican.inventorymaster.main;


import com.acdirican.inventorymaster.cli.Cli;
import com.acdirican.inventorymaster.repository.base.RepositoryManager;
import com.acdirican.inventorymaster.repository.base.RepositoryManager.RepositoryType;
import com.acdirican.inventorymaster.service.ServiceManagerImpl;


/**
 * Starter class of the project.
 *  
 * @author Ahmet Cengizhan Dirican
 *
 */
public class Main {
	public static void main(String[] args) {
		RepositoryManager repository =  RepositoryManager.getRepository(RepositoryType.JPA);
		
		if (!repository.connect()) {
			System.err.println("Could not be connected to the mysql database!");
			return;
		}
		
		Cli cli =  new Cli(new ServiceManagerImpl(repository));	
		repository.close();
	}
}

