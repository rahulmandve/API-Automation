package automation.stepsdefs;

import automation.utils.AppConstants;
import automation.utils.JsonUtility;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ServiceNameStepDefinitions {

    private String correlationID="";
    private Response response;
    private RequestSpecification request;
    private String strOauthType = "";
    private static final Logger logger = LogManager.getLogger(ServiceNameStepDefinitions.class);

    @Given("I have to build request for generate token")
    public void I_have_to_build_request_for_generate_token() {

        request = given().formParam("client_id", AppConstants.client_id)
                .formParam("client_secret", AppConstants.client_secret)
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust");
        request.log().everything();
    }

    @When("I make an API call to get token")
    public void I_make_an_API_call_to_get_token() {

        String url = AppConstants.TOKEN_SERVICE_BASE_URL;
        logger.debug("Before StrOauthType" + strOauthType + " and strOauthURL " + url);
        response = request.relaxedHTTPSValidation().when().post(url);
    }

    @Then("I should get success response code {int} from token service")
    public void I_should_get_success_response_code_from_token_service(Integer statusCode) {
        try {
            response.then().statusCode(statusCode);

        } catch (AssertionError as) {
            logger.error("Setting Golable access token variable to to tkoen request is failed");
            AppConstants.GLOBALE_ACCESS_TOKEN = "TOKEN_REQUEST_FAILED";
            logger.error(as.getMessage());
        } finally {
            logger.info("Time taken = " + response.getTime() + " Milliseconds and the reponse is : " + response.prettyPrint());
        }
    }

    @And("I will update global access token")
    public void I_will_update_global_access_token() {
        logger.debug("------------------Updating Globale Access Token-------------------");
        AppConstants.GLOBALE_ACCESS_TOKEN = response.path("access_token");
    }

    @Given("I have to make request to course details service")
    public void I_have_to_make_request_to_course_details_service() {
        request = given().queryParam("access_token", AppConstants.GLOBALE_ACCESS_TOKEN)
                .log().everything();

    }

    @When("I make API call to course details service")
    public void I_make_API_call_to_course_details_service() {
        response = request.relaxedHTTPSValidation().when().get(AppConstants.SERVICE_BASE_URL);
    }

    @Then("I should get Status code {int} from course details service")
    public void I_should_get_Status_code_from_course_details_service(Integer code) {
        response.then().statusCode(code);
    }

    @And("Verify below fields in response")
    public void Verify_below_fields_in_response(DataTable table) {
        try {
            List<Map<String, String>> data = table.asMaps();
            logger.debug("Validating response fields");
            JsonUtility.validateResponseFields(response, data.get(0));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    @Given("build request from the feature file input body")
    public void build_request_from_the_feature_file_input_body(DataTable table){
        List<Map<String,String>> data=table.asMaps();
        String jsonRequest=JsonUtility.buildRequestFromFeatureFileInput(data.get(0));
        correlationID= UUID.randomUUID().toString();
        logger.info("correlatio id for this request is"+correlationID);
        buildServiceRequest(jsonRequest,data.get(0).get("member"));

    }
    public void buildServiceRequest(String jsonRequest,String member)
    {
      request=  RestAssured.given().config(RestAssured.config().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    request.header("","");
        request.header("","");
        request.header("","");
        request.body(jsonRequest);
    request.log().everything(true);
    }

}
