import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payloadValues;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider = "BookData",testName = "TC1 - Add Books to Library")
	public void addBook(String isbn, String aisle) {
		
		//Post Method to add a Book to Library
		RestAssured.baseURI="http://216.10.245.166";
		String addBookResponse = given().log().all().header("Content-Type","application/json")
		.body(payloadValues.addBookPayload(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().extract().response().asString();
		
		JsonPath jsonPath = ReUsableMethods.convertStringToJSON(addBookResponse);
		System.out.println("ID of Newly added Book in Libray is : " +jsonPath.get("ID"));

	}
	
	@Test(testName = "TC2 - Delete added Books", dataProvider="DeleteBookData")
	public void deleleBooks(String Id) {
		
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json")
		.body(payloadValues.deleteBook(Id))
		.when().post("Library/DeleteBook.php")
		.then().log().all();
	
	}
	
	
	@DataProvider(name="BookData")
	public Object[][] getBookData() {
		return new Object[][] {{"aaa","301"},{"bbb","310"},{"ccc","130"}};
	}
	
	@DataProvider(name="DeleteBookData")
	public Object[] deleteBook() {
		return new Object[] {"aaa301","bbb310","ccc130"};
	}
	
	

}
