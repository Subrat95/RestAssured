package files;

public class payloadValues {
	//to create a user
	public static String createUser() {
		return "{\n"
				+ "    \"id\": 1,\n"
				+ "    \"email\": \"george.bluth@reqres.in\",\n"
				+ "    \"first_name\": \"Georges\",\n"
				+ "    \"last_name\": \"Bluths\",\n"
				+ "    \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"\n"
				+ "}";
	}
	//to update a user
	public static String updateUser() {
		return "{\n"
				+ "    \"data\": {\n"
				+ "        \"id\": 2,\n"
				+ "        \"email\": \"janet.weaver@reqres.in\",\n"
				+ "        \"first_name\": \"Janetd\",\n"
				+ "        \"last_name\": \"Weaver\",\n"
				+ "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n"
				+ "    },\n"
				+ "    \"support\": {\n"
				+ "        \"url\": \"https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral\",\n"
				+ "        \"text\": \"Tired of writing endless social media content? Let Content Caddy generate it for you.\"\n"
				+ "    }\n"
				+ "}";
	}

	public static String courseDetails() {
		return "{\n"
				+ "    \"dashboard\": {\n"
				+ "        \"purchaseAmount\": 910,\n"
				+ "        \"website\": \"rahulshettyacademy.com\"\n"
				+ "    },\n"
				+ "    \"courses\": [\n"
				+ "        {\n"
				+ "            \"title\": \"Selenium Python\",\n"
				+ "            \"price\": 50,\n"
				+ "            \"copies\": 6\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"title\": \"Cypress\",\n"
				+ "            \"price\": 40,\n"
				+ "            \"copies\": 4\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"title\": \"RPA\",\n"
				+ "            \"price\": 45,\n"
				+ "            \"copies\": 10\n"
				+ "        }\n"
				+ "    ]\n"
				+ "}";

	}

	public static String addBookPayload(String isbn, String aisle) {

		String addBook = "{\n"
				+ "  \"name\": \"Novel Kumar\",\n"
				+ "  \"isbn\": \""+isbn+"\",\n"
				+ "  \"aisle\": \""+aisle+"\",\n"
				+ "  \"author\": \"Kumar\"\n"
				+ "}";

		return addBook;


	}

	public static String deleteBook(String Id) {
		return "{\n"
				+ "    \"" + Id +"\" :\"com1199\"\n"
				+ "}";
	}

}
