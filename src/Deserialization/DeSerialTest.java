package Deserialization;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import restassured.Payload;

public class DeSerialTest {
	public static void main(String[] args) {

		// add base URL
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// in given()--give all input
		String response = given()
				.body(Payload.getCourses())
				// in when()--give resourse and http method
				.when().post("/maps/api/place/add/json")
				// in then()--verify the results
				.then().assertThat().statusCode(200).extract().response().asString();
		// extract json response using JsonPath class
		JsonPath json = new JsonPath(response);
		String place_id = json.getString("place_id");

		GetCourse gc=given().log().all()
				.when().get("/maps/api/place/get/json").as(GetCourse.class);
		
		System.out.println(gc.getLinkedin());


	}

}
