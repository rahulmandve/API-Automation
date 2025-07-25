package basic;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

public class SumValidation {

    @Test
    public void sum() {
        int sum = 0;
        JsonPath js=new JsonPath(Payload.CoursePrice());
        int count=	js.getInt("courses.size()");
        for(int i=0;i<count;i++)
        {
            int price=js.getInt("courses["+i+"].price");
            int copies=js.getInt("courses["+i+"].copies");
            sum += price * copies;

        }
        System.out.println(sum);
        int purchaseAmount =js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);


    }
}
