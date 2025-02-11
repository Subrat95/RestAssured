
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

public class GraphQL {

	public static void main(String[] args) {
		
		String locationNAme ="SUbrat";
		String characterName = "Test";
		String episodeName = "tester";
		
		// TODO Auto-generated method stub
		String response =given().log().all().contentType(ContentType.JSON)
				.body("{\"query\":\"mutation ($locationName: String!, $characterName: String!, $episodeName: String!)\\n\\n{\\n  createLocation(location: {name: $locationName, type: \\\"Country\\\", dimension: \\\"222\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Actor\\\", status: \\\"Alive\\\", species: \\\"Human\\\", gender: \\\"male\\\", image: \\\"Not available\\\", originId: 18037, locationId: 18037}) {\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date: \\\"01/01/2025\\\", episode: \\\"The Rise\\\"}) "
						+ "{\\n    id\\n  }\\n}\\n\",\"variables\":"
						+ "{\"locationName\":\""+locationNAme+"\",\"characterName\":\""+characterName+"\",\"episodeName\":\""+episodeName+"\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().asString();
		
		System.out.println(response);
		
		

	}

}
