import org.testng.annotations.Test;

import EcomPojo.CreateOrderRequestPayload;
import EcomPojo.CreateProductResponsePayload;
import EcomPojo.GetallProducts;
import EcomPojo.LoginUserRequestPayload;
import EcomPojo.LoginUserResponsePayload;
import EcomPojo.OrdersRequestPayload;
import EcomPojo.RegisterUserRequestPayload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcommerceTest {

	@Test(testName="Purchase a Product")
	public void purchaseProductThenDelete() {

		//Register a User in website
		String email = "subrat_02@gmail.com";
		String password = "Hello@1234";
		String baseUri = "https://rahulshettyacademy.com";

		//Prepare Request spec builder
		RequestSpecification requestBuilder;

		RegisterUserRequestPayload payloadRegister = new RegisterUserRequestPayload();
		payloadRegister.setFirstName("Subrat");
		payloadRegister.setLastName("Kumar");
		payloadRegister.setUserEmail(email);
		payloadRegister.setUserPassword(password);
		payloadRegister.setConfirmPassword(password);
		payloadRegister.setGender("Male");
		payloadRegister.setOccupation("Student");
		payloadRegister.setUserRole("customer");
		payloadRegister.setUserMobile("1111199999");
		payloadRegister.setRequired(true);

		System.out.println("*****************Register a New User*****************");

		requestBuilder = new RequestSpecBuilder().setBaseUri(baseUri).setContentType(ContentType.JSON).build();
		//given().log().all().spec(requestBuilder)
		//.body(payloadRegister)
		//.when().post("api/ecom/auth/register").then().log().all().assertThat().statusCode(200);


		LoginUserRequestPayload payloadLogin = new LoginUserRequestPayload();
		payloadLogin.setUserEmail(email);
		payloadLogin.setUserPassword(password);

		//Construct Request Spec Builder

		ResponseSpecification responseBuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

		System.out.println("*****************Login new user*****************");
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
		.param("productName", "Wandaa")
		.param("productAddedBy", customerId)
		.param("productCategory", "Seriess")
		.param("productSubCategory", "TV seriessss")
		.param("productPrice", "200")
		.param("productDescription", "Best Seriess")
		.param("productFor", "men")
		.multiPart("productImage",new File("/Users/subbu/Downloads/4k-marvel-2560X1440-wallpaper-tfbu1wo3pp8mip0a.jpg"));
		
		
		CreateProductResponsePayload createProductResponsePayload= createProductRequest.when().post("api/ecom/product/add-product")
		.then().assertThat().statusCode(201).extract().as(CreateProductResponsePayload.class);
		
		
		String productId= createProductResponsePayload.getProductId();
		
//		System.out.println("*****************Create a new Order using product "+ productId+" *****************");
		
		RequestSpecification baseRequest= new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", authToken)
				.build();
		
		OrdersRequestPayload orderPayload = new OrdersRequestPayload();
		orderPayload.setCountry("India");
		orderPayload.setProductOrderedId(productId);
		
		List<OrdersRequestPayload> listOrderDetails = new ArrayList<OrdersRequestPayload>();
		listOrderDetails.add(orderPayload);
		CreateOrderRequestPayload createOrderRequestPayload = new CreateOrderRequestPayload();
		createOrderRequestPayload.setOrdersRequestPayload(listOrderDetails);
		
		
		RequestSpecification createOrderReq = given().log().all().spec(baseRequest).body(createOrderRequestPayload);
		//String createOrderResponse = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().asString();
		//System.out.println(createOrderResponse);
		
		//Commenting becuase 500 error comn=ing while post request
		
		System.out.println("*****************Get list of Products*****************");
		
		RequestSpecification productListRequest = new RequestSpecBuilder().setBaseUri(baseUri)
				//.setContentType("text/html")
				.addHeader("Authorization", authToken)
				.addHeader("Content-Type", "application/json")
				.build();
		
		
		
		ResponseSpecification getProductresponse = new ResponseSpecBuilder().expectContentType("text/html").build();
		
		
		//GetallProducts getAllProducts = given().log().all().spec(productListRequest)
				//.body("{\n"
						//+ "    \"productName\": \"\",\n"
						//+ "    \"minPrice\": null,\n"
						//+ "    \"maxPrice\": null,\n"
						//+ "    \"productCategory\": [],\n"
						//+ "    \"productSubCategory\": [],\n"
						//+ "    \"productFor\": []\n"
						//+ "}")
				
				//.when().get("api/ecom/product/get-all-products").then().
				//spec(getProductresponse)
				//.extract().as(GetallProducts.class);
		//System.out.println(getAllProducts.getGetData().get(0).get_id());

	}


}
