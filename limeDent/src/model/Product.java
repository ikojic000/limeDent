package model;


import java.math.BigDecimal;


/**
 * 
 * @author ikojic000
 * 
 *         Represents a product offered by a dental office, which can be an
 *         item, article, service, or product. The product can be initialized
 *         using various constructors which allow different combinations of
 *         fields to be set, including the ID, code, name, and price. The class
 *         contains getter and setter methods for each of the fields, which can
 *         be used to retrieve or modify the values of the corresponding
 *         attributes.
 * 
 *
 */
public class Product {
	
	private Integer id;
	private Integer code;
	private String name;
	private BigDecimal price;
	
	/**
	 * 
	 * Creates a new product with the specified ID.
	 * 
	 * @param id The ID of the product.
	 */
	public Product( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Creates a new product with the specified ID and code.
	 * 
	 * @param id   The ID of the product.
	 * @param code The code of the product.
	 */
	public Product( Integer id , Integer code ) {
		
		this.id = id;
		this.code = code;
		
	}
	
	
	/**
	 * 
	 * Creates a new product with the specified name and price.
	 * 
	 * @param name  The name of the product.
	 * @param price The price of the product.
	 */
	public Product( String name , BigDecimal price ) {
		
		this.name = name;
		this.price = price;
		
	}
	
	
	/**
	 * 
	 * Creates a new product with the specified ID, name, and price.
	 * 
	 * @param id    The ID of the product.
	 * @param name  The name of the product.
	 * @param price The price of the product.
	 */
	public Product( Integer id , String name , BigDecimal price ) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		
	}
	
	
	/**
	 * 
	 * Creates a new product with the specified ID, code, name, and price.
	 * 
	 * @param id    The ID of the product.
	 * @param code  The code of the product.
	 * @param name  The name of the product.
	 * @param price The price of the product.
	 */
	public Product( Integer id , Integer code , String name , BigDecimal price ) {
		
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		
	}
	
	
	/**
	 * 
	 * Returns the ID of the product.
	 * 
	 * @return The ID of the product.
	 */
	public Integer getId() {
		
		return id;
		
	}
	
	
	/**
	 * 
	 * Sets the ID of the product.
	 * 
	 * @param id The ID to set.
	 */
	public void setId( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Returns the code of the product.
	 * 
	 * @return The code of the product.
	 */
	public Integer getCode() {
		
		return code;
		
	}
	
	
	/**
	 * 
	 * Sets the code of the product.
	 * 
	 * @param code The code to set.
	 */
	public void setCode( Integer code ) {
		
		this.code = code;
		
	}
	
	
	/**
	 * 
	 * Returns the name of the product.
	 * 
	 * @return The name of the product.
	 */
	public String getName() {
		
		return name;
		
	}
	
	
	/**
	 * 
	 * Sets the name of the product.
	 * 
	 * @param name The name to set.
	 */
	public void setName( String name ) {
		
		this.name = name;
		
	}
	
	
	/**
	 * 
	 * Returns the price of the product.
	 * 
	 * @return The price of the product.
	 */
	public BigDecimal getPrice() {
		
		return price;
		
	}
	
	
	/**
	 * 
	 * Sets the price of the product.
	 * 
	 * @param price the price to set
	 */
	public void setPrice( BigDecimal price ) {
		
		this.price = price;
		
	}
	
	
	/**
	 * 
	 * Returns a String representation of this Product object.
	 * 
	 * @return The String representation of this Product object.
	 */
	@Override
	public String toString() {
		
		return name;
		
	}
	
}
