package com.acdirican.inventorymaster.cli;


import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.SupplierService;

/**
 * Cli (Viewer) for Supplier entity
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class SupplierCli extends AbstractCLi {
	private SupplierService supplierService;

	public SupplierCli(Cli cli, SupplierService supplierService) {
		super(cli);
		this.supplierService = supplierService;
		this.scanner = Utils.scanner;
	}

	String delete_all(List<Integer> id_list) {
		if (!Utils.confirm("Are you sure to delete all the supplier with the given IDs? [y/n]")) {
			return "Delete cancelled";
		}

		if (id_list.size() == 0) {
			return "No supplier ID for deletion!";
		}

		int result = supplierService.deleteAll(id_list);
		if (result == id_list.size()) {
			return "All suppliers deleted succesfull.";
		} else if (result > 0) {
			return "Products partially deleted.";
		} else if (result == 0) {
			return "No supplier deleted.";
		}

		return Error.DBERROR;

	}

	String find(String name) {

		List<Supplier> suppliers = supplierService.find(name);
		if (suppliers == null) {
			return Error.DBERROR;
		}
		printSupplierList(suppliers);
		return suppliers.size() + " suppliers have been successfull listed.";

	}

	String update(int ID) {
		Optional<Supplier> supplierOp = supplierService.getWidthID(ID);
		if (supplierOp.isEmpty()) {
			return Error.ERROR + "Supplier with the ID " + ID + " could not be found!";
		}
		Supplier supplier = supplierOp.get();

		Utils.line();
		System.out.println(supplier);
		Utils.line();
		System.out.println("Live empty if you don't want to update the field!");
		System.out.println("Enter supplier name:");
		String name = scanner.nextLine().trim();
		Utils.shortLine();
		System.out.println("Enter phone:");
		String phone = scanner.nextLine();
		System.out.println("Enter address:");
		String address = scanner.nextLine();
		Utils.shortLine();
		System.out.println("Enter contact person firstname:");
		String contactFirstName = scanner.nextLine();
		System.out.println("Enter contact person last:");
		String contactPersonLastName = scanner.nextLine();
		
		Utils.line();

		if (!name.equals("")) {
			supplier.setName(name);
		}
		
		if (!phone.equals("")) {
			supplier.getContact().setPhone(phone);
		}
		
		if (!address.equals("")) {
			supplier.getContact().setAddress(address);
		}

		if (!contactFirstName.equals("")) {
			supplier.getContactPerson().setFirstName(contactFirstName);
		}
		
		if (!contactPersonLastName.equals("")) {
			supplier.getContactPerson().setLastName(contactPersonLastName);;
		}
		
		if (supplierService.update(supplier)) {
			return "Supplier succesfully updated!";
		} else {
			return "Supplier could not be updated!";
		}

	}

	String delete(int ID) {
		if (!Utils.confirm("Are you sure to delete? [y/n]")) {
			return "Delete cancelled";
		}

		boolean result = supplierService.delete(ID);
		if (result) {
			return "Supplier delete is succesfull.";
		}

		return Error.ERROR + "Supplier with the ID " + ID + " could not be found!";
	}

	String getWithIndex(int index) {
		Optional<Supplier> supplierOp = supplierService.getWidthIndex(index);
		if (supplierOp.isPresent()) {
			Supplier supplier = supplierOp.get();
			System.out.println(supplier);
			System.out.println("Products:");
			ProductCli.printProductList(supplier.getProducts());
			return "Supplier fetch is succesfull.";
		}

		return Error.ERROR + "Supplier with the index number " + index + " could not be found!";
	}

	String getWidthID(int ID) {
		Optional<Supplier> supplierOp = supplierService.getWidthID(ID);
		if (supplierOp.isPresent()) {
			Supplier supplier = supplierOp.get();
			System.out.println(supplier);
			System.out.println("Products:");
			ProductCli.printProductList(supplier.getProducts());
			return "Supplier fetch is succesfull.";
		}

		return Error.ERROR + "Supplier with the ID number " + ID + " could not be found!";
	}

	String add() {
		System.out.println("Add New Supplier");
		Utils.line();
		
		System.out.println("Enter name:");
		String name = scanner.nextLine();
		System.out.println("Enter phone:");
		Utils.shortLine();;
		String phone = scanner.nextLine();
		System.out.println("Enter address:");
		String address = scanner.nextLine();
		Utils.shortLine();
		System.out.println("Enter contact person firstname:");
		String contactFirstName = scanner.nextLine();
		System.out.println("Enter contact person last:");
		String contactPersonLastName = scanner.nextLine();
		Utils.line();
		Supplier supplier = new Supplier(name, phone, address, contactFirstName, contactPersonLastName);

		if (supplierService.add(supplier).isPresent())
			return "A new supplier added.";
		else
			return "Data coul not be added.";

	}

	static void printSupplierList(List<Supplier> suppliers) {
		System.out.printf("%-10s %-50s\n", "ID", "Name");
		Utils.line();
		for (Supplier supplier : suppliers) {
			System.out.printf("%-10d %-50s\n", supplier.getID(), supplier.getName());
		}
		Utils.line();
	}

	String list() {
		List<Supplier> suppliers = supplierService.list();
		if (suppliers == null) {
			return Error.DBERROR;
		}
		printSupplierList(suppliers);
		return suppliers.size() + " suppliers have been successfull listed.";

	}

}
