package restassured;

import io.restassured.path.json.JsonPath;

public class ReadComplexJson {
	public static void main(String[] args) {
		
		JsonPath json=new JsonPath(Payload.bookPayload());
		
		//read purchase amount	
		int pamount=json.getInt("dashboard.purchaseAmount");
		System.out.println(pamount);
		
		//read number of courses
		int ccouressize=json.getInt("courses.size()");
		System.err.println(ccouressize);
		
		//read title of the first course
		String firstcourseTitle=json.getString("courses[0].title");
		System.out.println(firstcourseTitle);
		
		//read all course title and there price
		for(int i=0;i<ccouressize;i++)
		{
			System.out.println(json.getString("courses["+i+"].title")+"  "+json.getInt("courses["+i+"].price"));
			
		}
		
		//read no of copies sold by Java course
		for(int i=0;i<ccouressize;i++)
		{
			if(json.getString("courses["+i+"].title").equalsIgnoreCase("Java"))
			{
				System.out.println(json.getInt("courses["+i+"].copies"));
			}
		}
		
		int temp=0;
		//verify purchase amount match with sum of all courses
		for(int i=0;i<ccouressize;i++)
		{
			temp=temp+json.getInt("courses["+i+"].price")*json.getInt("courses["+i+"].copies");
			
		}
		if(temp==json.getInt("dashboard.purchaseAmount"))
		System.out.println("Purchase Amount "+json.getInt("dashboard.purchaseAmount")+" and Sum of All courses "+temp+"Matched");
		else
			System.out.println("Not matched");
	}

}