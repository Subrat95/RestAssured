import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReUsableMethods;
import files.payloadValues;
import files.placePayloads;

public class BaseTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//given -- uri, payload, header,body, queryparams
		//when -- http method , on which resource
		//then -- response

		JsonPath JsonResponse;
		RestAssured.baseURI = "https://reqres.in";



		System.out.println("-------Get user details---------");
		System.out.println("-----------------------------------");
		System.out.println("");

		String getUser = given().log().all() .queryParam("page","1")
				.queryParam("id", "2").when().get("api/users")
				.then().statusCode(200).extract().asString();

		JsonResponse = ReUsableMethods.convertStringToJSON(getUser); String firstName
		= JsonResponse.getString("data.first_name");

		System.out.println("-------Create user details---------");
		System.out.println("-----------------------------------");
		System.out.println("");



		String response = given().log().all() .body("{\n" + "    \"id\": 1,\n" +
				"    \"email\": \"george.bluth@reqres.in\",\n" +
				"    \"first_name\": \""+firstName+"\",\n" +
				"    \"last_name\": \"Bluths\",\n" +
				"    \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"\n" + "}")


				.when().post("api/users") .then().statusCode(201) .header("Connection",
						equalTo("keep-alive")).extract().response().asString();

		JsonResponse = ReUsableMethods.convertStringToJSON(response); String id
		=JsonResponse.get("id"); System.out.println("Created user id is : " + id);
		System.out.println("");

		System.out.println("-------Update user details---------");
		System.out.println("-----------------------------------");
		System.out.println("");

		//update a user

		//given().log().all().body(payloadValues.updateUser()).when().
		//put("api/users/2").then().log().all().assertThat().statusCode(200).extract().asString();

		//get user details
		/*
		 * given().log().all().queryParam("id", 2) .when().get("api/users")
		 * .then().log().all().statusCode(200).extract().asString();
		 */

		//update user details using json file upload
		//In order to read file in string format, first we need to fetch file from given path, then convert to bytes> then String

		String filePath = "/Users/subbu/Downloads/updatePayload.json";
		given().log().all().body(new String(Files.readAllBytes(Paths.get(filePath))) ).when().
		put("api/users/2").then().log().all().assertThat().statusCode(200).extract().asString();




	}

}
