package restassured;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class EndToEndApiAutomationScenario {
	public static void main(String[] args) {

		// add base URL
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// in given()--give all input
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addGoogleMapPayload())
				// in when()--give resourse and http method
				.when().post("/maps/api/place/add/json")
				// in then()--verify the results
				.then().assertThat().statusCode(200).extract().response().asString();
		// extract json response using JsonPath class
		JsonPath json = new JsonPath(response);
		String place_id = json.getString("place_id");// get place_id from response body.Id is created after post method
														// hit
		System.out.println(place_id);

		// update address using put method
		String newAddress = "gokal nager 10 katraj";
		given().queryParam("key", "qaclick123")
				.body("{\r\n" + "\"place_id\":\"" + place_id + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// get place detail using GET api and verify updated address match
		String actualresponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js = new JsonPath(actualresponse);
		String actualAddress = js.getString("address");

		Assert.assertEquals(actualAddress, newAddress);

	}
}
