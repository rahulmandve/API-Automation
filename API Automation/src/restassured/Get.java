package restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Get {
	public static void main(String[] args) {
		RestAssured.baseURI="http://dummy.restapiexample.com";
		
		String response=given().log().all().header("Content-Type", "application/json").body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
				.when().post("api/v1/create").then().assertThat().statusCode(200).body("message",equalTo("Successfully! Record has been added."))
				.extract().response().asString();
				//.header("Content-Type", "application/json");
				
				System.out.println(response);
				
				JsonPath json=new JsonPath(response);
			int id=	json.getInt("data.id");
		
			String salary=json.getString("data.salary");
		//update
			int expectedsal=300000 
					;
		given().log().all().header("Content-Type", "application/json").body("{\"name\":\"test\",\"salary\":\""+expectedsal+"\",\"age\":\"23\"}")
		.when().put("/api/v1/update/"+id)
		.then().assertThat().log().all().statusCode(200).body("message", equalTo("Successfully! Record has been updated."));
		
		//get updated record and verify record update successfully or not
		
		String actualSalary=given().log().all().header("Content-Type", "application/json")
		.when().get("/api/v1/employee/"+id)
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath json1=new JsonPath(actualSalary);
		
	Assert.assertEquals(salary, expectedsal);
		
		}

}
