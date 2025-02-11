import org.testng.Assert;
import org.testng.annotations.Test;

import EcomPojo.CreateOrderRequestPayload;
import EcomPojo.CreateProductResponsePayload;
import EcomPojo.GetallProducts;
import EcomPojo.LoginUserRequestPayload;
import EcomPojo.LoginUserResponsePayload;
import EcomPojo.OrdersRequestPayload;
import EcomPojo.RegisterUserRequestPayload;
import files.ReUsableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcomTest {

	@Test(testName="Purchase a Product")
	public void purchaseProductThenDelete() {

		
		String email = "SKPsubrat@gmail.com";
		String password = "Hello@123";
		String baseUri = "https://rahulshettyacademy.com";

		//Prepare Request spec builder
		RequestSpecification requestBuilder;

		requestBuilder = new RequestSpecBuilder().setBaseUri(baseUri).setContentType(ContentType.JSON).build();
		//Build request Payload
		LoginUserRequestPayload payloadLogin = new LoginUserRequestPayload();
		payloadLogin.setUserEmail(email);
		payloadLogin.setUserPassword(password);

		//Construct Request Spec Builder

		ResponseSpecification responseBuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

		System.out.println("*****************Login user*****************");
		LoginUserResponsePayload responseLogin =
				given().log().all().spec(requestBuilder).body(payloadLogin).when().post("api/ecom/auth/login")
				.then().assertThat().spec(responseBuilder).statusCode(200).extract().as(LoginUserResponsePayload.class);

		

		String authToken = responseLogin.getToken();
		String customerId = responseLogin.getUserId();
		System.out.println("*****************Create a new Product*****************");
		//Build Create Product Request
		RequestSpecification requestCreateProduct = new RequestSpecBuilder().setBaseUri(baseUri)
				.addHeader("Authorization", authToken).build();

		RequestSpecification createProductRequest =   given().spec(requestCreateProduct)
		.param("productName", "Marvel heros")
		.param("productAddedBy", customerId)
		.param("productCategory", "TV serial")
		.param("productSubCategory", "TV series")
		.param("productPrice", "2010")
		.param("productDescription", "Best TV series")
		.param("productFor", "men")
		.multiPart("productImage",new File("/Users/subbu/Downloads/download.jpeg"));
		
		
		CreateProductResponsePayload createProductResponsePayload= createProductRequest.when().post("api/ecom/product/add-product")
		.then().assertThat().statusCode(201).extract().as(CreateProductResponsePayload.class);
		String productId= createProductResponsePayload.getProductId();
		

		
		System.out.println("*****************Delete Product*****************");
		
		RequestSpecification deleteSpecBuilder = new RequestSpecBuilder().setBaseUri(baseUri).setContentType(ContentType.JSON)
				.addHeader("Authorization", authToken).build();
		
		RequestSpecification deleteRequest = given().log().all().spec(deleteSpecBuilder).pathParam("productId", productId);
		
		String deleteProductresponse = deleteRequest.when().delete("api/ecom/product/delete-product/{productId}").then().extract().asString();
		
		JsonPath jsonPath = ReUsableMethods.convertStringToJSON(deleteProductresponse);
		String productDeleteSuccess = jsonPath.get("message");
		
		Assert.assertEquals(productDeleteSuccess, "Product Deleted Successfully");
		


	}


}
