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
	
	public static String bookPayload()
	{
		return "{\r\n"
				+ "  \"dashboard\":{\r\n"
				+ "    \"purchaseAmount\":2450,\r\n"
				+ "    \"website\":\"www.google.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\":[\r\n"
				+ "    {\r\n"
				+ "      \"title\":\"QA\",\r\n"
				+ "      \"price\":100,\r\n"
				+ "      \"copies\":2\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\":\"Dev\",\r\n"
				+ "      \"price\":90,\r\n"
				+ "      \"copies\":5\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\":\"Java\",\r\n"
				+ "      \"price\":200,\r\n"
				+ "      \"copies\":9\r\n"
				+ "    }\r\n"
				+ "    ]\r\n"
				+ "}";
		
	}
}
