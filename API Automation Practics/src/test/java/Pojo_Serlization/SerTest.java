package Pojo_Serlization;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerTest
{

    @Test
    public void SerlizationTest()
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

        Response response=given().queryParam("key","qaclick123")
                .body(gmap)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
    }
}
