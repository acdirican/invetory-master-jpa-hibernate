package com.acdirican.inventorymaster.cli;


import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.ProductService;

/**
 * Cli for Product entity
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public class ProductCli extends AbstractCLi {
	

	private ProductService productService;
	

	public ProductCli(Cli cli, ProductService productRepository) {
		super(cli);
		this.productService = productRepository;
	}

	String delete_all(List<Integer> id_list) {
		if (!Utils.confirm("Are you sure to delete all the product with the given IDs? [y/n]")) {
			return "Delete cancelled";
		}

		if (id_list.size() == 0) {
			return "No product ID for deletion!";
		}

		int result = productService.deleteAll(id_list);
		if (result == id_list.size()) {
			return "All products deleted succesfull.";
		} else if (result > 0) {
			return "Products partially deleted.";
		} else if (result == 0) {
			return "No product deleted.";
		}

		return Error.DBERROR;

	}

	String list_depleteds() {

		List<Product> products = productService.listDepleteds();

		if (products == null) {
			return Error.DBERROR;
		}
		printProductList(products);
		return products.size() + " products have been successfull listed.";

	}

	String list_equals(double quantity) {

		List<Product> products = productService.listEquals(quantity);
		if (products == null) {
			return Error.DBERROR;
		}
		printProductList(products);
		return products.size() + " products have been successfull listed.";

	}

	String find(String name) {

		List<Product> products = productService.find(name);
		if (products == null) {
			return Error.DBERROR;
		}
		printProductList(products);
		return products.size() + " products have been successfull listed.";

	}

	String list_morethan(double quantity) {

		List<Product> products = productService.listMoreThan(quantity);
		if (products == null) {
			return Error.DBERROR;
		}
		printProductList(products);
		return products.size() + " products have been successfull listed.";

	}

	String list_lessthan(double quantity) {

		List<Product> products = productService.listLessThan(quantity);
		if (products == null) {
			return Error.DBERROR;
		}
		printProductList(products);
		return products.size() + " products have been successfull listed.";

	}

	String update(int ID) {
		Optional<Product> productOp = productService.getWidthID(ID);
		if (productOp.isEmpty()) {
			return Error.ERROR + "Product with the ID " + ID + " could not be found!";
		}
		Product product = productOp.get();

		Utils.line();
		System.out.println(product);
		Utils.line();
		System.out.println("Live empty if you don't want to update the field!");

		System.out.println("Enter product name:");
		String name = scanner.nextLine().trim();

		System.out.println("Enter product quantity:");
		String quantityStr = scanner.nextLine().trim();

		Supplier supplier = cli.selectSupplier();

		Utils.line();

		if (!name.equals("")) {
			product.setName(name);
		}
		if (!quantityStr.equals("")) {
			product.setQuantity(Double.parseDouble(quantityStr));
		}
		if (supplier != null) {
			product.setSupplier(supplier);
		}

		if (productService.update(product)) {
			return "Product succesfully updated!";
		}
		return Error.ERROR + "Product could not be updated!";
	
	}

	String delete(int ID) {
		if (!Utils.confirm("Are you sure to delete? [y/n]")) {
			return "Delete cancelled";
		}

		boolean result = productService.delete(ID);
		if (result) {
			return "Product delete is succesfull.";
		}

		return Error.ERROR + "Product with the ID " + ID + " could not be found!";
	}

	String getWidthIndex(int index) {
		
		Optional<Product> productOp = productService.getWidthIndex(index);
		if (productOp.isEmpty()) {
			return Error.ERROR + "Product with the index " + index + " could not be found!";
		}
		Product product = productOp.get();
		
		System.out.println(product);
		return "Product fetch is succesfull.";
	}

	public String getWidthID(int ID) {
		Optional<Product> productOp = productService.getWidthID(ID);
		if (productOp.isEmpty()) {
			return Error.ERROR + "Product with the ID " + ID + " could not be found!";
		}
		System.out.println(productOp.get());
		return "Product get is successfull.";
	}

	
	String add() {
		Utils.line();
		System.out.println("Enter product name:");
		String name = scanner.nextLine();
		System.out.println("Enter product quantity:");
		double quantity = Double.parseDouble(scanner.nextLine());
		Supplier supplier = cli.selectSupplier();
		if (supplier == null) {
			return "You should select a supplier ID from the given list.";
		}
		
		Product product = new Product(name, quantity, supplier);

		if (productService.add(product).isPresent())
			return "A new product added.";
		else
			return "Data coul not be added.";

	}

	static void printProductList(List<Product> products) {
		System.out.printf("%-10s %-30s %-20s %-30s\n", "ID", "Name", "Quantity", "Supplier");
		Utils.line();
		for (Product product : products) {
			System.out.printf("%-10d %-30s %-20f %-30s\n", product.getID(), product.getName(), product.getQuantity(),
					product.getSupplier().getName());
		}
		Utils.line();
	}

	String list() {
		List<Product> products = productService.list();
		if (products == null) {
			return Error.DBERROR;
		}
		printProductList(products);
		return products.size() + " products have been successfull listed.";

	}

	public String increaseInvetory(int ID, double quantity) {
		Optional<Product> productOp = productService.getWidthID(ID);
		if (productOp.isEmpty()) {
			return Error.ERROR + "Product with the ID " + ID + " could not be found!";
		}
		System.out.println(productOp.get());
		if (Utils.confirm("Are you sure to increase the product's inventory?")) {
			return productService.increaseInvetory(productOp.get(), quantity)  
					? "Inventory increased succesfully"
					: "Inventory could no be increased! Check Product ID or quantity!";
		}
		else {
			return "Cancelled!";
		}
		
	}

	public String decreaseInvetory(int ID, double quantity) {
		Optional<Product> productOp = productService.getWidthID(ID);
		if (productOp.isEmpty()) {
			return Error.ERROR + "Product with the ID " + ID + " could not be found!";
		}
		System.out.println(productOp.get());
		if (Utils.confirm("Are you sure to decrease the product's inventory?")) {
			return productService.decreaseInvetory(productOp.get(), quantity)  
					? "Inventory decreased succesfully"
					: "Inventory could no be decreased! Check Product ID or quantity!";
		}
		else {
			return "Cancelled!";
		}
	}

	



}
