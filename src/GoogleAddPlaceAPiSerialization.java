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

public class GoogleAddPlaceAPiSerialization {
	
	
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
		//Set up request to POST
		String uri ="https://rahulshettyacademy.com";
		
		RequestSpecification requestBuilder = new RequestSpecBuilder().setBaseUri(uri)
				.build();
		RequestSpecification requestAddPlace = given().log().all().spec(requestBuilder).
				queryParam("key", "qaclick123").body(payload);
		
		
		GoogleAddPlaceResponse addPlaceResponse = requestAddPlace.when().
				post("maps/api/place/add/json").then().log().all().extract().response()
				.as(GoogleAddPlaceResponse.class);
		
		ResponseSpecification response =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		//Validate Response Body
		Assert.assertEquals(addPlaceResponse.getStatus(), "OK");
		Assert.assertEquals(addPlaceResponse.getScope(), "APP");
		
		
		
	}

}
