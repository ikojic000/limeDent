package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Database;
import model.Product;


/**
 * @author ikojic000
 * 
 *         The ProductDAO class provides methods for accessing and manipulating
 *         product data in the database.
 * 		
 */
public class ProductDAO {
	
	/**
	 * Constructs a new ProductDAO object.
	 */
	public ProductDAO() {
		
	}
	
	
	/**
	 * 
	 * Returns a list of all products in the database.
	 * 
	 * @return an ArrayList of Product objects
	 */
	public ArrayList<Product> getAllProducts() {
		
		ArrayList<Product> allProducts = new ArrayList<Product>();
		
		String sql = "select * from products";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			ResultSet rs = pst.executeQuery();
			
			while ( rs.next() ) {
				
				allProducts.add( new Product( rs.getInt( "id" ) , rs.getInt( "productCode" ) ,
						rs.getString( "productTitle" ) , rs.getBigDecimal( "productPrice" ) ) );
				
			}
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
		return allProducts;
		
	}
	
	
	/**
	 * 
	 * Returns a list of products that match the specified search string.
	 * 
	 * @param search the search string to match against product data
	 * 
	 * @return an ArrayList of Product objects that match the search string
	 */
	public ArrayList<Product> searchProduct( String search ) {
		
		ArrayList<Product> searchedProducts = new ArrayList<Product>();
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "select * from products where id like ? or productCode like ? or productTitle like ?";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , "%" + search + "%" );
			pst.setString( 2 , "%" + search + "%" );
			pst.setString( 3 , "%" + search + "%" );
			ResultSet rs = pst.executeQuery();
			
			while ( rs.next() ) {
				
				Product product = new Product( rs.getInt( "id" ) , rs.getInt( "productCode" ) ,
						rs.getString( "productTitle" ) , rs.getBigDecimal( "productPrice" ) );
				searchedProducts.add( product );
				
			}
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
		return searchedProducts;
		
	}
	
	
	/**
	 * 
	 * Adds a new product to the database.
	 * 
	 * @param product - the Product object to add to the database
	 */
	public void addProduct( Product product ) {
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "insert into products(productTitle, productPrice) values (?, ?)";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , product.getName() );
			pst.setString( 2 , product.getPrice().toString() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Deletes the specified product from the database.
	 * 
	 * @param product - the Product object to delete from the database
	 */
	public void deleteProduct( Product product ) {
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "delete from products where id=? and productCode=?";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , product.getId().toString() );
			pst.setString( 2 , product.getCode().toString() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	};
	
	
	/**
	 * 
	 * Updates the specified product in the database.
	 * 
	 * @param product - the Product to be updated in the database.
	 */
	public void updateProduct( Product product ) {
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "update products set productTitle=?, productPrice=? where id=?";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , product.getName() );
			pst.setString( 2 , product.getPrice().toString() );
			pst.setString( 3 , product.getId().toString() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	};
	
}
