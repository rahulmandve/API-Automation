package automation.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigUtility {
    private static ConfigUtility single_instance = null;

    private static final Logger logger = LogManager.getLogger(ConfigUtility.class);

    private ConfigUtility() {
        readPropertifile();
    }

    public static ConfigUtility initializaConfiguration() {
        if (single_instance == null)
            single_instance = new ConfigUtility();
        return single_instance;
    }

    public void readPropertifile() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String loglevel = System.getProperty("set.log.level");

            //default log level in log4j2.xml config is level.INFO
            if (!((loglevel == null) || (loglevel == ""))) {
                loglevel = loglevel.toLowerCase();
                if (loglevel.equals("info") || loglevel.equals("warn") || loglevel.equals("debug") || loglevel.equals("error") || loglevel.equals("all")) {
                    LoggerContext context = (LoggerContext) LogManager.getContext(false);
                    Configuration config = context.getConfiguration();
                    LoggerConfig rootconfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

                    if (loglevel.equals("debug"))
                        rootconfig.setLevel(Level.DEBUG);
                    else if (loglevel.equals("info"))
                        rootconfig.setLevel(Level.INFO);
                    else if (loglevel.equals("warn"))
                        rootconfig.setLevel(Level.WARN);
                    else if (loglevel.equals("error"))
                        rootconfig.setLevel(Level.ERROR);
                    else
                        rootconfig.setLevel(Level.ALL);
                }

            }
            //setting run env
            String testEnv = System.getProperty("run.env");

            logger.info("------Inside readPropertyfile run.env - " + testEnv);
            if (testEnv == null || testEnv == "")
                testEnv = "default";
            else
                testEnv = testEnv.toLowerCase();

            input = Files.newInputStream(Paths.get("C:\\Users\\RAHUL MANDAWE\\IdeaProjects\\API_Automation_Cucumber_Framework\\src\\test\\resources\\application.properties"));
            prop.load(input);
            if (prop.getProperty(testEnv + "." + "token.service.url") == null) {
                logger.info("----------Reverting to default configuration-------");
                testEnv = "default";
            }
            if (AppConstants.USE_TM)
                AppConstants.TOKEN_SERVICE_BASE_URL = prop.getProperty(testEnv + "." + "tm.token.service.url");
            else
                AppConstants.TOKEN_SERVICE_BASE_URL = prop.getProperty(testEnv + "." + "token.service.url");
            logger.info("---------Token service base url - " + AppConstants.TOKEN_SERVICE_BASE_URL + " -----------");

//           updating service url
            if (AppConstants.USE_TM)
                AppConstants.SERVICE_BASE_URL = prop.getProperty(testEnv + "." + "tm.service.service.url");
            else
                AppConstants.SERVICE_BASE_URL = prop.getProperty(testEnv + "." + "service.service.url");
            logger.info("---------Service service base url - " + AppConstants.SERVICE_BASE_URL + " -----------");

            AppConstants.client_id = prop.getProperty(testEnv + ".client_id");
            AppConstants.client_secret = prop.getProperty(testEnv + ".client_secret");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
        }
    }

}
