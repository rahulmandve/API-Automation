package restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class Post {
		
	public static void main(String args[]) {
		RestAssured.baseURI="http://dummy.restapiexample.com";
		given().log().all().header("Content-Type", "application/json").body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
		.when().post("api/v1/create").then().log().all().assertThat().statusCode(200);
		
	
	}

}
