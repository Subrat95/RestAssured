package EcomPojo;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequestPayload {
	
	private List<OrdersRequestPayload> ordersRequestPayload;

	/**
	 * @return the ordersRequestPayload
	 */
	public List<OrdersRequestPayload> getOrdersRequestPayload() {
		return ordersRequestPayload;
	}

	/**
	 * @param ordersRequestPayload the ordersRequestPayload to set
	 */
	public void setOrdersRequestPayload(List<OrdersRequestPayload> ordersRequestPayload) {
		this.ordersRequestPayload = ordersRequestPayload;
	}

}
