package basic;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        // TODO Auto-generated method stub


        JsonPath js = new JsonPath(Payload.CoursePrice());
        //Print No of courses returned by API

        int coures = js.getInt("courses.size()");
        System.out.println(coures);
        //Print Purchase Amount
        int totalamount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalamount);

        //Print Title of the first course
        String firsttitle = js.getString("courses[0].title");
        System.out.println(firsttitle);
        for (int i = 0; i < coures; i++) {
            String title = js.get("courses[" + i + "].title").toString();
            String price = js.get("courses[" + i + "].price").toString();
            System.out.println(title);
            System.out.println(price);


        }
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < coures; i++) {
            String title = js.get("courses[" + i + "].title").toString();
            if(title.equalsIgnoreCase("RPA"))
            {
                String copies = js.get("courses[" + i + "].copies").toString();
                System.out.println(copies);
                break;
            }
        }
    }

}
