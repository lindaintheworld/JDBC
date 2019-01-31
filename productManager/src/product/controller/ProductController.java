package product.controller;

import java.util.ArrayList;
import java.util.HashMap;

import product.exception.ProductException;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.view.ProductMenu;

public class ProductController {
	
	private ProductService pservice;
	public ProductController() {
		try {
			pservice = new ProductService();
		} catch (ProductException e) {
			e.printStackTrace();
			new ProductMenu().PrintError(e.getMessage());
			return;
		}
		
	}
	
	public void insertProduct(Product p) {
		ProductMenu menu = new ProductMenu();
		try {
			if(pservice.insertProduct(p) > 0)
				System.out.println("상품 등록 성공!");
		} catch (ProductException e) {
			e.printStackTrace();
			menu.PrintError(e.getMessage());
			return;
		}
	}
	
	public void deleteProduct(String productId) {
		ProductMenu menu = new ProductMenu();
		try {
			if(pservice.deleteProduct(productId) > 0)
				System.out.println("상품 삭제 성공!");
		} catch (ProductException e) {
			e.printStackTrace();
			menu.PrintError(e.getMessage());
			return;
		}
	}
	
	public void modifyProduct(Product p) {
		ProductMenu menu = new ProductMenu();
		try {
			if(pservice.modifyProduct(p) > 0)
				System.out.println("상품 수정 성공!");
		} catch (ProductException e) {
			e.printStackTrace();
			menu.PrintError(e.getMessage());
			return;
		}
	}
	
	public void searchProduct(String productId) {
		ProductMenu menu = new ProductMenu();
		try {
			Product p = pservice.searchProductId(productId);
			menu.printProduct(p);
			if(p != null)
				System.out.println("상품 조회 성공!");
		} catch (ProductException e) {
			menu.PrintError(e.getMessage());
		}
	}
	
	public void selectAllList() {
		ProductMenu menu = new ProductMenu();
		try {
			menu.printProductList(pservice.selectList());
		} catch (ProductException e) {
			menu.PrintError(e.getMessage());
		}
	}
	
	public void selectAllMap() {
		ProductMenu menu = new ProductMenu();
		try {
			menu.printProductMap(pservice.selectMap());
		} catch (ProductException e) {
			e.printStackTrace();
			menu.PrintError(e.getMessage());
		}
	}
	
	public void searchProductList(String pName) {
		ProductMenu menu = new ProductMenu();
		try {
			ArrayList<Product> pList = pservice.selectProductList(pName);
			menu.printProductList(pList);
			System.out.println("상품명으로 조회 성공!");
		} catch (ProductException e) {
			e.printStackTrace();
			menu.PrintError(e.getMessage());
		}
	}
	
	public void searchProductMap(String pName) {
		ProductMenu menu = new ProductMenu();
		try {
			HashMap<String, Product> pMap = pservice.selectProductMap(pName);
			menu.printProductMap(pMap);
		} catch (ProductException e) {
			e.printStackTrace();
			menu.PrintError(e.getMessage());
		}
	}

}
