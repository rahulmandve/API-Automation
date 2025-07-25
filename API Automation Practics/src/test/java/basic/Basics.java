package basic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
     String response= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.addPlace()).when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        JsonPath js=new JsonPath(response);
       String id= js.getString("place_id");
       System.out.println(id);
String newadd="70 winter walk, USA";
       given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
               .body("{\n" +
                       "\"place_id\":\""+id+"\",\n" +
                       "\"address\":\""+newadd+"\",\n" +
                       "\"key\":\"qaclick123\"\n" +
                       "}")
               .when().put("/maps/api/place/update/json")
               .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

     String getplace=  given().queryParam("key","qaclick123").queryParam("place_id",id)
               .when().get("/maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();

     JsonPath jsp=new JsonPath(getplace);
     String actualAddress=jsp.getString("address");
     System.out.println(actualAddress);

        Assert.assertEquals(actualAddress,newadd);
    }
}
