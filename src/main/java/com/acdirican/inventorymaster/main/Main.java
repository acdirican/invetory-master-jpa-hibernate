package com.acdirican.inventorymaster.main;


import com.acdirican.inventorymaster.cli.Cli;
import com.acdirican.inventorymaster.repository.BaseRepository;
import com.acdirican.inventorymaster.repository.BaseRepository.RepositoryType;


/**
 * Starter class of the project.
 *  
 * @author Ahmet Cengizhan Dirican
 *
 */
public class Main {
	public static void main(String[] args) {
		BaseRepository respository =  BaseRepository.getRepository(RepositoryType.JPA);
		Cli cli =  new Cli(respository);		
	}
}

