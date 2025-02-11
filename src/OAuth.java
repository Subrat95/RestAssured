import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GetCourseBaseResponse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuth {
	
	@Test
	public void getCourseDeatails() {
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		//generate token
		String tokenResponse =given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		//Extract token
		JsonPath jsonPath =ReUsableMethods.convertStringToJSON(tokenResponse);
		String token = jsonPath.getString("access_token");
		//Course details api
		
		GetCourseBaseResponse getCourseBaseResponse =given().log().all()
		.param("access_token", token)
		.when().get("oauthapi/getCourseDetails")
		.then().log().all().extract().as(GetCourseBaseResponse.class);
		
		String[] expectedList = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		//System.out.println(getCourseBaseResponse.getCourses().getWebAutomation().get(2).getCourseTitle());
		int listSize =getCourseBaseResponse.getCourses().getWebAutomation().size();
		for (int i=0;i< listSize;i++) {
			String courseName = getCourseBaseResponse.getCourses().getWebAutomation().get(i).getCourseTitle();
			if(courseName.equalsIgnoreCase("Selenium Webdriver Java")) {
				System.out.println("Price of "+courseName+""+getCourseBaseResponse.getCourses().getWebAutomation().get(i).getPrice());
				break;
			}
		}
		
		
		//get all course names from WebAutomation json
		ArrayList<String> actualList = new ArrayList<String>();
		for (int i=0;i<listSize;i++) {
			actualList.add( getCourseBaseResponse.getCourses().getWebAutomation().get(i).getCourseTitle());
		}
		
		//convert expected list to array of list so it can be easily compared
		
		List<String> convertedExpectedList = Arrays.asList(expectedList);
		
		Assert.assertTrue(convertedExpectedList.equals(actualList));
		
	}
	


}
