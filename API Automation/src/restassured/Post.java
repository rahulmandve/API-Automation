package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Post {
		
	public static void main(String args[]) {
		RestAssured.baseURI="http://dummy.restapiexample.com";
		String response=given().log().all().header("Content-Type", "application/json").body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
		.when().post("api/v1/create").then().assertThat().statusCode(200).body("message",equalTo("Successfully! Record has been added."))
		.extract().response().asString();
		//.header("Content-Type", "application/json");
		
		System.out.println(response);
		
		JsonPath json=new JsonPath(response);
	int id=	json.getInt("data.id");
	System.out.println(id);
		
	
	}

}
