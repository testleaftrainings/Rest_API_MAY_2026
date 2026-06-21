package step.defs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import week3.day2.CreateIncidentPojo;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

public class IncidentTableSteps {
	
	private RequestSpecBuilder preConditions = new RequestSpecBuilder();
	private Response response;
	private CreateIncidentPojo createIncidentPojo = new CreateIncidentPojo();

	@Given("set base uri {string}")
	public void set_base_uri(String baseUri) {
		preConditions.setBaseUri(baseUri);
	}

	@Given("set base path {string}")
	public void set_base_path(String basePath) {
		preConditions.setBasePath(basePath);
	}

	@Given("add basic authentication username {string} and password {string}")
	public void add_basic_authentication_username_and_password(String username, String password) {
		preConditions.setAuth(basic(username, password));
	}

	@Given("add path parameter variable name {string} and value {string}")
	public void add_path_parameter_variable_name_and_value(String variableName, String value) {
		preConditions.addPathParam(variableName, value);
	}
	
	@Given("add header key {string} and value {string}")
	public void add_header_key_and_value(String key, String value) {
	    preConditions.addHeader(key, value);
	}
	
	@Given("set short description value as {string}")
	public void set_short_description_value_as(String shortDescription) {
	    createIncidentPojo.setShort_description(shortDescription);
	}
	
	@Given("set description value as {string}")
	public void set_description_value_as(String description) {
	    createIncidentPojo.setDescription(description);
	}
	
	@Given("set category value as {string}")
	public void set_category_value_as(String category) {
	    createIncidentPojo.setCategory(category);
	}
	
	@When("add the request payload in the create incident pojo object")
	public void add_the_request_payload_in_the_create_incident_pojo_object() {
	    preConditions.setBody(createIncidentPojo);
	}

	@When("hit get method of the {string} endpoint")
	public void hit_get_method_of_the_endpoint(String endpoint) {
		response = given().spec(preConditions.build()).get(endpoint);
	}
	
	@When("add the request payload in the json string")
	public void add_the_request_payload_in_the_json_string(String requestPayload) {
	    preConditions.setBody(requestPayload);
	}
	
	@When("hit post method of the {string} endpoint")
	public void hit_post_method_of_the_endpoint(String endpoint) {
		response = given().spec(preConditions.build()).post(endpoint);
	}
	
	@When("add the request payload in the the file format and location is {string}")
	public void add_the_request_payload_in_the_the_file_format_and_location_is(String requestPayload) {
		
	}
	
	@When("hit post method of the {string} endpoint and request payload as file format the location is {string}")
	public void hit_post_method_of_the_endpoint_and_request_payload_as_file_format_the_location_is(String endpoint, String fileLocation) {
	    response = given().spec(preConditions.build()).body(new File(fileLocation)).post(endpoint);
	}

	@Then("status code is {int}")
	public void status_code_is(Integer expected) {
		response.then().assertThat().statusCode(expected);
	}

	@Then("status name is {string}")
	public void status_name_is(String expected) {
		response.then().assertThat().statusLine(Matchers.containsString(expected));
	}

	@Then("response format is {string}")
	public void response_format_is(String expected) {
		response.then().assertThat().contentType(Matchers.containsString(expected.toLowerCase()));
	}
	
	@Then("validate the response with following expected value")
	public void validate_the_response_with_following_expected_value(DataTable dataTable) {
	    List<Map<String, String>> asMaps = dataTable.asMaps();
	    for (Map<String, String> map : asMaps) {			
			response.then().assertThat().statusCode(Integer.parseInt(map.get("statusCode")));			
			response.then().assertThat().statusLine(Matchers.containsString(map.get("statusLine")));
			response.then().assertThat().contentType(Matchers.containsString(map.get("responseFormat").toLowerCase()));
		}
	}

}