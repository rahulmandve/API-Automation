package restassured;

public class Payload {

	public static String addGoogleMapPayload()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"swami house\",\r\n"
				+ "  \"phone_number\": \"(+91) 9999999999\",\r\n"
				+ "  \"address\": \"48/3/2 katraj road pune\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"coloy park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"English\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
