package com.acdirican.inventorymaster.main;


import com.acdirican.inventorymaster.cli.Cli;
import com.acdirican.inventorymaster.repository.base.RepositoryManager;
import com.acdirican.inventorymaster.repository.base.RepositoryManager.RepositoryType;


/**
 * Starter class of the project.
 *  
 * @author Ahmet Cengizhan Dirican
 *
 */
public class Main {
	public static void main(String[] args) {
		RepositoryManager respository =  RepositoryManager.getRepository(RepositoryType.JPA);
		Cli cli =  new Cli(respository);		
	}
}

