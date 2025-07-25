package automation.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppConstants {

    public static boolean USE_TM=false;
    public static String TOKEN_SERVICE_BASE_URL="";
    public static String SERVICE_BASE_URL="";
    public static volatile String GLOBALE_ACCESS_TOKEN="NOT_POPULATED";
    public static String client_id="";
    public static String client_secret="";
    public static volatile int passedScenarioCount=0;
    public static volatile int failedScenarioCount=0;
    public static volatile List<String> failedScernario=new ArrayList<String>();
    public static volatile Map<String,String> allTestItems=new HashMap<String, String>();
    public static final String JIRA_ITEM_PREFIX="@-";
//    public static volatile Map<String , String> allTestItems=new HashMap<String,String>();




}
