package Serialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import restassured.Payload;

public class SerialiTest {
	public static void main(String[] args) {
		
		AddPlace add=new AddPlace();
		add.setAccuracy(50);
		add.setAddress("katraj 48");
		add.setLanguage("English");
		add.setName("swami bnglow");
		add.setPhone_number("99999999999");
		add.setWebsite("www.google.com");
		List<String>typelist=new ArrayList<>();
		typelist.add("show park");
		typelist.add("pune");
		add.setTypes(typelist);
		Location location=new Location();
		location.setLat(-30.88838);
		location.setLng(302.23482093);
		add.setLocation(location);
		
		
			// add base URL
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				// in given()--give all input
				String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
						.body(add)
						// in when()--give resourse and http method
						.when().post("/maps/api/place/add/json")
						// in then()--verify the results
						.then().assertThat().statusCode(200).extract().response().asString();
				System.out.println(response);
				// extract json response using JsonPath class
				JsonPath json = new JsonPath(response);
				String place_id = json.getString("place_id");// get place_id from response body.Id is created after post method
																// hit
	}

}
