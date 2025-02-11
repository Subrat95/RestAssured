
import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class Oauth2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String clientId= "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
		String redirect_uri = "https://rahulshettyacademy.com/getCourse.php";
		
		/*
		 * String url
		 * ="https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss";
		 * 
		 * System.setProperty("Webdriver.chrome.driver",
		 * "/Users/subbu/Downloads/Selenium/chromedriver/chromedriver"); WebDriver
		 * driver = new ChromeDriver(); driver.get(url);
		 * driver.findElement(By.id("identifierId")).sendKeys(
		 * "padhi.subrat.skp@gmail.com");
		 * driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
		 * Thread.sleep(4000);
		 * driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))
		 * .sendKeys("Blue$shield1");
		 * driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))
		 * .sendKeys(Keys.ENTER);
		 */
		//get code response
		//given().log().all().auth()
		//.oauth2("scope", "https://www.googleapis.com/auth/userinfo.email")
		//.oauth2(redirect_uri, "")
		//.
		
		//get accessToken
		
		String accessTokenresponse =given().log().all().queryParam("code", "")
		.queryParam("client_id", clientId)
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam(clientId, redirect_uri)
		.queryParam("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath jsonPath = new JsonPath(accessTokenresponse);
		
		String accessToken = jsonPath.getString("access_token");
		
		
		//get Courses
		String GetCourseResponse = given().log().all().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();

	}
	
	

}
