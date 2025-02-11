package EcomPojo;

import java.util.List;

public class OrdersRequestPayload {
	
	private String country;
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the productOrderedId
	 */
	public String getProductOrderedId() {
		return productOrderedId;
	}
	/**
	 * @param productOrderedId the productOrderedId to set
	 */
	public void setProductOrderedId(String productOrderedId) {
		this.productOrderedId = productOrderedId;
	}
	private String productOrderedId;

}
