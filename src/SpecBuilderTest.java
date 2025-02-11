import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleAddPlacePayload;
import pojo.GoogleAddPlaceResponse;
import pojo.GoogleLocation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class SpecBuilderTest {


	@Test
	public void addPlace() {

		//construct payload
		GoogleAddPlacePayload payload = new GoogleAddPlacePayload();
		GoogleLocation googleLocation = new GoogleLocation();
		googleLocation.setLat(38.7722);
		googleLocation.setLng(38.7722);

		ArrayList<String> listTypes = new ArrayList<String>();
		listTypes.add("hello");
		listTypes.add("show");
		payload.setLocation(googleLocation);
		payload.setAccuracy(44);
		payload.setName("Padhi");
		payload.setAddress("Film City Address");
		payload.setPhone_number("(+91) 9040 011 1111");
		payload.setTypes(listTypes);
		payload.setWebsite("www.google.com");

		//Set up request Spec
		RequestSpecification googleRequestGenerator = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		//Set up response spec
		ResponseSpecification responseSender = new ResponseSpecBuilder()
				.expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RequestSpecification googleRequest	= given().log().all().spec(googleRequestGenerator).body(payload);
		
		GoogleAddPlaceResponse addPlaceResponse = googleRequest
		.when().post("maps/api/place/add/json")
		.then().log().all().spec(responseSender).extract().response().as(GoogleAddPlaceResponse.class);
		
		
		//Validate Response Body
		Assert.assertEquals(addPlaceResponse.getStatus(), "OK");
		Assert.assertEquals(addPlaceResponse.getScope(), "APP");


	}

}
