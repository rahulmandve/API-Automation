package ResReqSpecBuilder;

import Pojo_Serlization.GMapPlaceAdd;
import Pojo_Serlization.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestResponseSpecBuilderTest
{

    @Test
    public void ReqResSpecBuilderTest()
    {
        GMapPlaceAdd gmap=new GMapPlaceAdd();
        gmap.setAccuracy(50);
        gmap.setAddress("29, side layout, cohen 09");
        gmap.setLanguage("French-IN");
        gmap.setName("Frontline house");
        gmap.setPhone_number("(+91) 983 893 3937");
        gmap.setWebsite("http://google.com");
        List<String>l=new ArrayList<>();
        l.add("shoe park");
        l.add("shop");
        gmap.setTypes(l);
        Location lo=new Location();
        lo.setLat(-38.383494);
        lo.setLng(33.427362);
        gmap.setLocation(lo);

        RequestSpecification resp= new RequestSpecBuilder().addQueryParam("key","qaclick123").setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        Response response=given().spec(resp).log().all()
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
    }
}
