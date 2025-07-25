package pojo_DeSerlization;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PojoTest {


    @Test
    public void pojoTest()
    {
        String[] ss={"Selenium Webdriver Java","Cypress","Protractor"};

   String response= given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
            .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
            .formParam("grant_type","client_credentials")
            .formParam("scope","trust")
            .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

   System.out.println(response);
        JsonPath json=new JsonPath(response);
        String accesstoken=json.getString("access_token");
        System.out.println(accesstoken);

       GetCourses course= given().queryParam("access_token",accesstoken).when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
        System.out.println("-------------------------------------------------------------------------");
       System.out.println(course.getLinkedIn());

       List<webAutomation> s=course.getCourses().getWebAutomation();

       for(int i=0;i<s.size();i++)
       {
           if(s.get(i).getCourseTitle().contains("Selenium"))
           {
               System.out.print(s.get(i).getPrice());
           }
       }
        ArrayList<String> ar=new ArrayList<>();
       for(int j=0;j<s.size();j++)
       {
          ar.add (s.get(j).getCourseTitle());
       }

       List<String>expected= Arrays.asList(ss);
        Assert.assertEquals(expected,ar);
    }
}
