import files.payloadValues;
import io.restassured.path.json.JsonPath;

public class ComplexJsonValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jsonPath = new JsonPath(payloadValues.courseDetails()) ;
		
		//1. Print No of courses returned by API
		
		int noOfCourses = jsonPath.getInt("courses.size()");
		System.out.println("1) No of courses returned in response is : " +noOfCourses);
		System.out.println("");
		
		//Print Purchase amount
		
		String purchasedAmount = jsonPath.getString("dashboard.purchaseAmount");
		System.out.println("2) Total Purchased amount is : " +purchasedAmount);
		System.out.println("");
		
		//Print Title of the first course
		String firstTitle = jsonPath.get("courses[0].title");
		System.out.println("3) Title of the first course is : " +firstTitle);
		
		System.out.println("");
		System.out.println("4) All course titles and their respective Prices ");
		
		//Print All course titles and their respective Prices
		int arrayLentgh = jsonPath.getInt("courses.size()");
		
		for (int i=0;i< arrayLentgh;i++) {
			System.out.println("Title of the course is '" + jsonPath.get("courses[" + i +"].title") + "' with a price of Rs."+jsonPath.get("courses[" + i +"].price"));
			//System.out.println("Price of the course is : " + jsonPath.get("courses[" + i +"].price"));
			
		}
		System.out.println("");
		
		//Print no of copies sold by RPA Course
		for (int i=0;i< arrayLentgh;i++) {
			
			String title = jsonPath.get("courses[" + i +"].title");
			
			if(title.contentEquals("RPA")) {
				System.out.println("5) Number of copies sold by RPA Course is : " + jsonPath.get("courses[" + i +"].copies"));
				break;
				
			}
			
		}
		System.out.println("");
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		int totalPurchasedAmount = jsonPath.getInt("dashboard.purchaseAmount");
		int finalCalculatedPrice = 0, calculatedPrice=0, price=0, copies=0, i =0;
		
		
		for (i=0;i< arrayLentgh;i++) {
			
			 price = jsonPath.get("courses[" + i +"].price");
			 copies = jsonPath.get("courses[" + i +"].copies");
			calculatedPrice = price*copies; 
			finalCalculatedPrice = finalCalculatedPrice +calculatedPrice;
			
		}
		if(totalPurchasedAmount == finalCalculatedPrice) {
			System.out.println("6) Sum of all Course prices matches with Purchase Amount which is :" + finalCalculatedPrice);
		}
		else {
			System.out.println("Sum of all Course prices is :" + totalPurchasedAmount);
			System.out.println("Sum of all Purchase Amount is :" + finalCalculatedPrice);
		}
		
		
		
	}

}
