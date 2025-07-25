package basic;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DynaicJSON {

    @Test
    public void addBook()
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
       String response= given().header("Content-Type","application/json").body(Payload.addBook())
                .when().post("/Library/Addbook.php").then().statusCode(200).extract().response().asString();
        
    }
}
