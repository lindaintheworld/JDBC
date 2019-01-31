package product.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import static common.JDBCTemp.*;
import product.exception.ProductException;
import product.model.dao.ProductDao;
import product.model.vo.Product;

public class ProductService {
	
	private ProductDao pdao;
	public ProductService() throws ProductException{
		pdao = new ProductDao();
	}

	public int insertProduct(Product p) throws ProductException {
		Connection conn = getConnection();
		int result = pdao.insertProduct(conn, p);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}

	public int deleteProduct(String productId) throws ProductException {
		Connection conn = getConnection();
		int result = pdao.delete(conn, productId);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}

	public int modifyProduct(Product p) throws ProductException {
		Connection conn = getConnection();
		int result = pdao.modifyProduct(conn, p);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}

	public Product searchProductId(String bid) throws ProductException {
		Connection conn = getConnection();
		Product p = pdao.searchProductId(conn, bid);
		close(conn);
		return p;
	}

	public ArrayList<Product> selectList() throws ProductException {
		Connection conn = getConnection();
		ArrayList<Product> pList = pdao.searchAllList(conn);
		close(conn);
		return pList;
	}

	public HashMap<String, Product> selectMap() throws ProductException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		HashMap<String, Product> pMap = pdao.searchAllMap(conn);
		close(conn);		
		return pMap;
	}

	public ArrayList<Product> selectProductList(String pName) throws ProductException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ArrayList<Product> pList = pdao.searchNameList(conn, pName);
		close(conn);
		return pList;
	}

	public HashMap<String, Product> selectProductMap(String pName) throws ProductException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		HashMap<String, Product> pMap = pdao.searchNameMap(conn, pName);
		close(conn);		
		return pMap;
	}

}
