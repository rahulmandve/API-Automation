package automation.utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JsonUtility {

    private static final Logger logger = LogManager.getLogger(JsonUtility.class);

    public static String loadJsonRequest(String fileName)
    {
        JSONParser parser=new JSONParser();
        JSONObject jsonobject=null;
        try{
            jsonobject=(JSONObject)parser.parse(new FileReader(fileName));
            logger.info("Updated JSON request-"+jsonobject);
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }catch (IOException ex)
        {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        } catch (ParseException e) {
           e.printStackTrace();
           logger.error(e.getMessage());
        }
        return jsonobject.toJSONString();
    }

    public static String buildRequestFromFeatureFileInput(Map<String,String>mapofRequestBody)
    {
        JSONParser parser=new JSONParser();
        JSONObject jsonobject=null;
        String roodnode="{}";
        try{
            jsonobject=(JSONObject) parser.parse(roodnode);
            for(Map.Entry<String,String> entry:mapofRequestBody.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();

                buildSingleField(jsonobject,key,value);
            }
            logger.info("---------build json request from scratch"+jsonobject.toJSONString());
        }catch (ParseException p)
        {
            p.printStackTrace();
            logger.error(p.getMessage());
        }
        return jsonobject.toJSONString();
    }
    public static void buildSingleField(JSONObject jsonobject,String key,String value)
    {
        JSONParser parser=new JSONParser();
        JSONObject jsonInnerobject=null;
      try{
          if(key.contains("#"))
          {
            String topElement=key.substring(0,key.indexOf("#"));
            String childElement=key.substring(key.indexOf("#")+1);
            jsonInnerobject=(JSONObject) parser.parse("{}");
            if(jsonInnerobject==null){
                jsonInnerobject=(JSONObject) parser.parse("{}");
            }
            buildSingleField(jsonInnerobject,childElement,value);
            jsonobject.put(topElement,jsonInnerobject);
          }else{
              jsonobject.put(key,value);

          }
      }catch (ParseException p)
      {
          p.printStackTrace();
          logger.error(p.getMessage());
      }

    }
    public static void validateResponseField(Response response,String jsonNode,Map<String,String>mapoutput)
    {
        boolean errorOccured=false;
        StringBuilder failedAssertion=new StringBuilder();
        for(Map.Entry<String,String>entry:mapoutput.entrySet())
        {
            try{
                logger.debug("------------verifying field-"+entry.getKey()+"with expected value\""+entry.getValue()+"\"----");
                String fieldvalue=null;
                if(jsonNode.isEmpty())
                    fieldvalue=response.path(entry.getKey());
                else
                    fieldvalue=response.path(jsonNode+"."+entry.getKey());
                if(fieldvalue==null)
                    Assert.fail("Validation failed for field"+entry.getKey()+"filed not found in the response under"+jsonNode+"\"");

            }catch (AssertionError e)
            {
                errorOccured=true;
                failedAssertion.append(System.getProperty("line.separator"));
                failedAssertion.append(e.getMessage());
            }
        }
        if(errorOccured){
            logger.error("Error logged from validat output fields in response"+failedAssertion.toString());
            throw  new AssertionError(failedAssertion.toString());
        }
    }
    public static void validateResponseFields(Response response, Map<String, String> map) {
        boolean errorOcuured = false;
        StringBuilder failedAssertions = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                String value = response.path(entry.getKey());
                if (value == null)
                    Assert.fail("----Validating failed for status" + entry.getKey() + "-status not found");
                if (!value.equals(entry.getValue()))
                    Assert.fail("--------Validation failed for status" + entry.getKey() + "-found\"" + value + "\"against expected value \"" + entry.getValue() + "\"");
            } catch (AssertionError e) {
                errorOcuured = true;
                failedAssertions.append(System.getProperty("line.separator"));
                failedAssertions.append(e.getMessage());
            }
            if (errorOcuured) {
                logger.error("error logged from validation response" + failedAssertions.toString());
                throw new AssertionError(failedAssertions.toString());
            }
        }

    }

}