import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraIssue {


	//Create a Bug
	@Test(dataProvider="CreateBug")
	public void createJiraIssue(String summary, String attachmentPath) {

		RestAssured.baseURI ="https://subratkumar908.atlassian.net";
		String authorizationBasic = "Basic c3VicmF0a3VtYXI5MDhAZ21haWwuY29tOkFUQVRUM3hGZkdGMDYwOXgwNzBveEpWRDNadFJGclhlVkFUVElyQlN6ZTBCV0czWDJfR2d1QmpESWR4YTlZT1NuV0c2dm9ORXVjZk1HeVRPR2I5YUw0dEtpbFFaNGotbkh2WERYRWl5T1dxNnM5aGJCVkloM2wxSEdGZkNTOXJ2Q0NOUXVlTVZFb1NpVkpIWGY0WmVGd2dZdTBZdkdZOE5MMUxReTBBMTdNVlFzSXk4M0c5WW1paz1BMEMyMzJCMQ==";

		String createIssueResponse = given().log().all()
				.header("Content-Type","application/json")
				.header("Authorization", authorizationBasic)
				.body("{\n"
						+ "    \"fields\": {\n"
						+ "       \"project\":\n"
						+ "       {\n"
						+ "          \"key\": \"SCRUM\"\n"
						+ "       },\n"
						+ "       \"summary\": \""+summary+"\",\n"
						+ "       \"issuetype\": {\n"
						+ "          \"name\": \"Bug\"\n"
						+ "       }\n"
						+ "   }\n"
						+ "}\n"
						+ "")
				.when().post("rest/api/3/issue")
				.then().log().all().assertThat().statusCode(201)
				.extract().response().asString();
		//extract issue ID from response

		JsonPath jsonpath = ReUsableMethods.convertStringToJSON(createIssueResponse);		
		String jiraId = jsonpath.getString("id");


		//Add Attachment to issue

		given().log().all()
		.pathParam("id", jiraId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization", authorizationBasic)
		.multiPart("file", new File(attachmentPath))
		.when().post("rest/api/3/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200).extract().asString();

	}

	@DataProvider(name="CreateBug")
	public static Object[][] createBugData() {

		return new Object[][] {
			{"App Crashes","/Users/subbu/Desktop/File1.png"}
			,{"User got logged out on email change","/Users/subbu/Downloads/update.json.pages"}};

	}

}
