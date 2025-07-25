package automation.utils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Hooks {

    private static final Logger logger= LogManager.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario)
    {
    logger.info("");
    logger.info("Starting Scenario \""+scenario.getName()+"\"");
    logger.info("-------------------------------------------------------------");

    for(String tagname : scenario.getSourceTagNames())
    {
        if(tagname.startsWith(AppConstants.JIRA_ITEM_PREFIX)){
            if((!AppConstants.allTestItems.containsKey(tagname)) || scenario.isFailed())
            {

            }

        }
    }
    }
}
