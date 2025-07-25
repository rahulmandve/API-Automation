package automation;

import automation.utils.AppConstants;
import automation.utils.ConfigUtility;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.Map;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
               "html:build/target/cucumber-html-report",
               "json:build/target/cucumber.json"
        }
//        ,glue = {"src/java/automation/stepsdefs/ServiceNameStepDefinitions"}
        ,features = {"src/test/resources/features"}
        ,tags = " @QaTest "

)
public class RunCucumberTest {
    private static final Logger logger= LogManager.getLogger(RunCucumberTest.class);

    @BeforeClass
    public static void setUpClass()
    {
        logger.info("------------Started Execution-------------");
        ConfigUtility.initializaConfiguration();

        logger.info("BUILD_NUMBER "+System.getenv("BUILD_NUMBER"));
        logger.info("BUILD_URL "+System.getenv("BUILD_URL"));
        logger.info("BUILD_NAME "+System.getenv("UILD_NAME"));
        logger.info("BUILD_TAG "+System.getenv("BUILD_TAG "));
    }

    @AfterClass
    public static void tearDown()
    {
        logger.info("---------Finished Exceution----------");
        logger.info("---------------Scenario passed "+ AppConstants.passedScenarioCount+"-------------\n");

        if(AppConstants.failedScenarioCount>0)
        {
            logger.info("------------------Scenario failed: "+ AppConstants.failedScenarioCount +"----------------\n");
            logger.info(AppConstants.failedScernario.toString());
            logger.info("-----------------------------------------------------------------------------------------");

        }
        logger.info("**********************Test case Results Report Starts*************************");

        //logging the test results report
        for(Map.Entry<String,String> entry:AppConstants.allTestItems.entrySet())
            logger.info("----------------"+entry.getKey()+": "+entry.getValue());

            logger.info("\n");
            logger.info("*************************************************************");

            writeTestResults();
            //Close DB connection



    }

    public static void writeTestResults(){
        BufferedWriter bw=null;
        FileWriter fw=null;

        try{
            String fileName=RunCucumberTest.class.getClassLoader().getResource("Test_Results.html").getFile();
            StringBuilder failedScernarioNames=new StringBuilder();

            String msgStatus="";
            String msgScernarioSummary="";
            String msgExceptionSummary="";

            if(AppConstants.failedScenarioCount>0){
                for(String str:AppConstants.failedScernario)
                {
                    failedScernarioNames.append("<li>"+str+"/li");
                }
                msgScernarioSummary="<h3><font color=\"SlateGray\" face=\"Verdana\">Scernario Passed: "+AppConstants.passedScenarioCount+"/font </h3>"
                        +"<h3><font color=\"SlateGray\" face=\"Verdana\">Scernario Failed: "+AppConstants.failedScenarioCount+"/font </h3>";
                msgExceptionSummary=readErrorLog();

                String content="<html><head><meta charse\"uft-8\"><title>Cucumber Test Results</title></head>\n"
                        +"<body><div></br>"+
                        "<h1><font color=\"RoyalBlue\" face=\"verdana\">Test Summary Report</font></h1></br>\n"+msgStatus+"</div></body></html>";

                fw=new FileWriter(fileName);
                bw=new BufferedWriter(fw);
                bw.write(content);
            }
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }finally {
            try{
                if(bw!=null)
                    bw.close();
                if (fw!=null)
                    fw.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public static String readErrorLog()
    {
        BufferedReader br=null;
        FileReader fr=null;
        StringBuilder listerror=new StringBuilder();
        try{
            fr=new FileReader("logs/project-Name-error.log");
            br=new BufferedReader(fr);
            String scurrentLine;
            while((scurrentLine=br.readLine())!=null)
            {
                listerror.append("<li>"+scurrentLine+" </li>");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(br!=null)
                    br.close();
                if(fr!=null)
                    fr.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return listerror.toString();
    }
}
