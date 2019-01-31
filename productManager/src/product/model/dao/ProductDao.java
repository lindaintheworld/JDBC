package product.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import static common.JDBCTemp.*;
import product.exception.ProductException;
import product.model.vo.Product;

public class ProductDao {
	private Properties prop = new Properties();
	
	public ProductDao() throws ProductException {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public int insertProduct(Connection conn, Product p) throws ProductException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insert");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new ProductException("새로운 상품정보 기록 실패!");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new ProductException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int modifyProduct(Connection conn, Product p) throws ProductException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("update");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p.getPrice());
			pstmt.setString(2, p.getProductId());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new ProductException("정보 수정 실패!");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new ProductException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int delete(Connection conn, String productId) throws ProductException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				System.out.println("정보 삭제 실패!");
			}
			
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public Product searchProductId(Connection conn, String bid) throws ProductException {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectId");
		
		try {
			p = new Product();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bid);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p.setProductId(rset.getString("product_id"));
				p.setpName(rset.getString("p_Name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
		
			}
			
			if(p == null) {
				throw new ProductException("조회 실패!");
			}
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
				
		
		return p;
	}
	
	public ArrayList<Product> searchNameList(Connection conn, String pName) throws ProductException{
		ArrayList<Product> PList = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + pName + "%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setpName(rset.getString("p_Name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				
				PList.add(p);
				
				if(PList.size() == 0) {
					throw new ProductException("조회 실패!");
				}
			}
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return PList;
		
	}
	
	public HashMap<String, Product> searchNameMap(Connection conn, String pName) throws ProductException {
		HashMap<String, Product> pMap = new HashMap<String, Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + pName + "%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setpName(rset.getString("p_Name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				
				pMap.put(p.getProductId(), p);
				
				if(pMap.isEmpty()) {
					throw new ProductException("조회 실패!");
				}
			}
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pMap;
		
	}
	
	public ArrayList<Product> searchAllList(Connection conn) throws ProductException{
		ArrayList<Product> PList = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setpName(rset.getString("p_Name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				
				PList.add(p);
				
				if(PList.size() == 0) {
					throw new ProductException("조회 실패!");
				}
			}
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return PList;
		
	}
	
	public HashMap<String, Product> searchAllMap(Connection conn) throws ProductException {
		HashMap<String, Product> pMap = new HashMap<String, Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setpName(rset.getString("p_Name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				
				pMap.put(p.getProductId(), p);
				
				if(pMap.isEmpty()) {
					throw new ProductException("조회 실패!");
				}
			}
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pMap;
		
	}
}
