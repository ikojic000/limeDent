package model;


import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 *
 * @author ikojic000
 *
 *         OfferTblPreviewData represents preview data for an offer table. It
 *         contains information about a product, the number of pieces of that
 *         product, the discount percentage, the total cost before discount, the
 *         total cost after discount, and the ID of the offer to which this
 *         preview data belongs.
 *		
 */
public class OfferTblPreviewData {
	
	private Product product;
	private Integer id;
	private Integer pieces;
	private BigDecimal total;
	private Integer discount;
	private BigDecimal totalWithDiscount;
	private Integer offer_id;
	
	/**
	 *
	 * Constructs a new OfferTblPreviewData object with the given product, number of
	 * pieces, and discount percentage. The total cost before and after discount are
	 * calculated automatically.
	 *
	 * @param product  the product associated with this preview data
	 * @param pieces   the number of pieces of the product
	 * @param discount the discount percentage
	 */
	public OfferTblPreviewData( Product product , Integer pieces , Integer discount ) {
		
		this.product = product;
		this.pieces = pieces;
		this.discount = discount;
		calculateTotal();
		calculateTotalWithDiscount();
		
	}
	
	
	/**
	 *
	 * Sets the number of pieces of the product and recalculates the total cost
	 * before and after discount.
	 *
	 * @param pieces the new number of pieces
	 */
	public void setPieces( Integer pieces ) {
		
		this.pieces = pieces;
		calculateTotal();
		calculateTotalWithDiscount();
		
	}
	
	
	/**
	 *
	 * Sets the discount percentage and recalculates the total cost after discount.
	 *
	 * @param discount the new discount percentage
	 */
	public void setDiscount( Integer discount ) {
		
		this.discount = discount;
		calculateTotalWithDiscount();
		
	}
	
	
	/**
	 *
	 * Calculates the total cost before discount based on the product and number of
	 * pieces.
	 */
	private void calculateTotal() {
		
		if ( product != null && pieces != null ) {
			
			total = product.getPrice().multiply( BigDecimal.valueOf( pieces ) );
			
		}
		
	}
	
	
	/**
	 *
	 * Calculates the total cost after discount based on the total cost before
	 * discount and the discount percentage. The calculated total is rounded to 2
	 * decimal places using the HALF_UP rounding mode.
	 */
	private void calculateTotalWithDiscount() {
		
		if ( total != null && discount != null ) {
			
			BigDecimal discountMultiplier = BigDecimal.ONE
					.subtract( BigDecimal.valueOf( discount ).divide( BigDecimal.valueOf( 100 ) ) );
			totalWithDiscount = total.multiply( discountMultiplier );
			totalWithDiscount = totalWithDiscount.setScale( 2 , RoundingMode.HALF_UP );
			
		}
		
	}
	
	
	/**
	 *
	 * Returns the product associated with this preview data.
	 *
	 * @return the product associated with this preview data
	 */
	public Product getProduct() {
		
		return product;
		
	}
	
	
	/**
	 *
	 * Sets the product associated with this preview data.
	 *
	 * @param product the new product
	 */
	public void setProduct( Product product ) {
		
		this.product = product;
		
	}
	
	
	/**
	 *
	 * Returns the ID of the offer to which this preview data belongs.
	 *
	 * @return the ID of the offer to which this preview data belongs
	 */
	public Integer getId() {
		
		return id;
		
	}
	
	
	/**
	 *
	 * Sets the ID of the offer to which this preview data belongs.
	 *
	 * @param id the new ID
	 */
	public void setId( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 *
	 * Returns the total price of the Product added.
	 *
	 * @return the total price of the offer
	 */
	public BigDecimal getTotal() {
		
		return total;
		
	}
	
	
	/**
	 *
	 * Sets the total price of the Product added.
	 *
	 * @param total the new total price
	 */
	public void setTotal( BigDecimal total ) {
		
		this.total = total;
		
	}
	
	
	/**
	 *
	 * Returns the total price with discount.
	 *
	 * @return the total price with discount
	 */
	public BigDecimal getTotalWithDiscount() {
		
		return totalWithDiscount;
		
	}
	
	
	/**
	 *
	 * Sets the total price with discount.
	 *
	 * @param totalWithDiscount the new total price with discount
	 */
	public void setTotalWithDiscount( BigDecimal totalWithDiscount ) {
		
		this.totalWithDiscount = totalWithDiscount;
		
	}
	
	
	/**
	 *
	 * Returns the ID of the offer to which this preview data belongs.
	 *
	 * @return the ID of the offer to which this preview data belongs
	 */
	public Integer getOffer_id() {
		
		return offer_id;
		
	}
	
	
	/**
	 *
	 * Sets the ID of the offer to which this preview data belongs.
	 *
	 * @param offer_id the new ID
	 */
	public void setOffer_id( Integer offer_id ) {
		
		this.offer_id = offer_id;
		
	}
	
	
	/**
	 *
	 * Returns the number of pieces of Product added.
	 *
	 * @return the number of pieces of Product added
	 */
	public Integer getPieces() {
		
		return pieces;
		
	}
	
	
	/**
	 *
	 * Returns the discount percentage.
	 *
	 * @return the discount percentage
	 */
	public Integer getDiscount() {
		
		return discount;
		
	}
	
}
