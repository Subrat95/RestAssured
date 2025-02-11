import org.testng.Assert;
import org.testng.annotations.Test;

import files.payloadValues;
import io.restassured.path.json.JsonPath;

public class SumTotal {

	@Test
	public void sumTotalAmount() {
		JsonPath jsonPath = new JsonPath(payloadValues.courseDetails()) ;
		int arrayLentgh = jsonPath.getInt("courses.size()");
		int totalPurchasedAmount = jsonPath.getInt("dashboard.purchaseAmount");
		int finalCalculatedPrice = 0, calculatedPrice=0, price=0, copies=0, i =0;
		for (i=0;i< arrayLentgh;i++) {
			price = jsonPath.get("courses[" + i +"].price");
			copies = jsonPath.get("courses[" + i +"].copies");
			calculatedPrice = price*copies; 
			finalCalculatedPrice = finalCalculatedPrice +calculatedPrice;
		}
		Assert.assertEquals(finalCalculatedPrice, totalPurchasedAmount);
		if(totalPurchasedAmount == finalCalculatedPrice) {
			System.out.println("Sum of all Course prices matches with Purchase Amount which is Rs." + finalCalculatedPrice);
		}
		else {
			System.out.println("Sum of all Course prices is :" + totalPurchasedAmount);
			System.out.println("Sum of all Purchase Amount is :" + finalCalculatedPrice);
		}

	}

}
