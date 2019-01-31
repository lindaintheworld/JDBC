package product.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Product;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	public ProductMenu() {}
	
	public void displayMenu() {
		do {
			System.out.println("*************");
			System.out.println();
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 가격 수정");
			System.out.println("3. 상품 삭제");
			System.out.println("4. 상품 아이디로 조회");
			System.out.println("5. 상품 제목으로 조회 : 리스트 사용");
			System.out.println("6. 상품 제목으로 조회 : 맵 사용");
			System.out.println("7. 상품 목록 전체 조회 : 리스트 사용");
			System.out.println("8. 상품 목록 전체 조회 : 맵 사용");
			System.out.println("9. 끝내기");
			System.out.println("번호 선택 : ");
			
			switch(sc.nextInt()){
			case 1 : pc.insertProduct(insertProduct()); break;
			case 2 : pc.modifyProduct(modifyProduct()); break;
			case 3 : pc.deleteProduct(inputProductId()); break;
			case 4 : pc.searchProduct(inputProductId()); break;
			case 5 : pc.searchProductList(inputPName()); break;
			case 6 : pc.searchProductMap(inputPName()); break;
			case 7 : pc.selectAllList(); break;
			case 8 : pc.selectAllMap(); break;
			case 9 : System.out.print("정말로 종료하시겠습니까? [y/n] : " );
					if(sc.next().toUpperCase().charAt(0) == 'Y') return;
					else break;
			default : System.out.println("번호를 잘못 입려하셨습니다. 다시 입력해주세요"); break;
			}
			
		}while(true);
	}
	
	public Product insertProduct() {
		Product p = new Product();
		System.out.print("상품 번호 입력 : ");
		p.setProductId(sc.next());
		System.out.print("상품 이름 입력 : ");
		p.setpName(sc.next());
		System.out.print("상품 가격 입력 : ");
		p.setPrice(sc.nextInt());
		System.out.println("메모 입력 : ");
		sc.nextLine();
		p.setDescription(sc.nextLine());
		
		return p;
	}
	
	public Product modifyProduct() {
		Product p = new Product();
		
		System.out.print("수정할 상품 번호 입력 : ");
		p.setProductId(sc.next());
		System.out.print("수정할 가격 입력 : ");
		p.setPrice(sc.nextInt());
		
		return p;
	}
	
	public String inputProductId() {
		System.out.print("수정 또는 삭제할 상품 번호 입력 : ");
		return sc.next();
	}
	
	public String inputPName() {
		System.out.println("검색할 상품명 입력 : ");
		return sc.next();
	}
	
	public void PrintError(String message) {
		System.out.println("\n프로그램 오류 발생!");
		System.out.println("시스템 관리자에게 문의하십시오.");
		System.out.println("오류 메세지 : " + message);
	}
	
	public void printProduct(Product p) {
		if(p == null) return;
		System.out.println("\n조회된 상품 정보는 다음과 같습니다.");
		System.out.println(p);
	}
	
	public void printProductList(ArrayList<Product> productList) {
		System.out.println("\n조회된 상품의 수 : " + productList.size());
		for(Product p : productList) {
			System.out.println(p);
		}
	}
	
	public void printProductMap(HashMap<String, Product> productMap) {
		System.out.println("\n조호된 상품의 수" + productMap.size());
		Iterator<String> keyIter = productMap.keySet().iterator();
		while(keyIter.hasNext()) {
			System.out.println(productMap.get(keyIter.next()));
		}
	}
}
