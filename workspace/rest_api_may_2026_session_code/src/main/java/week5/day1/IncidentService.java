package week5.day1;

import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.UpdateIncidentPojo;

public class IncidentService {
	
	Response response;
	private static final String TABLENAME = "/incident";
	
	public void createNewRecord(RequestSpecification preConditions) {
		response = preConditions.post(TABLENAME);
	}
	
	public void retrieveExitingRecord(RequestSpecification preConditions, String sysId) {
		response = preConditions.get(TABLENAME+"/"+sysId);
	}
	
	public void updateExitingRecord(RequestSpecification preConditions, String sysId, UpdateIncidentPojo updateIncident) {
		response = preConditions.body(updateIncident).put(TABLENAME+"/"+sysId); 
	}
	
	public void deleteExitingRecord(RequestSpecification preConditions, String sysId) {
		response = preConditions.delete(TABLENAME+"/"+sysId);
	}
	
	public void validateJsonResponse(int statusCode, String statusMessage) {
		response.then().assertThat()
                       .statusCode(statusCode)
                       .statusLine(Matchers.containsString(statusMessage))
                       .contentType(ContentType.JSON);
	}
	
	public void validateCreationResponse() {
		response.then().assertThat()
		               .statusCode(201)
		               .statusLine(Matchers.containsString("Created"))
		               .contentType(ContentType.JSON);		
	}
	
	public void validateSuccessResponse() {
		response.then().assertThat()
                       .statusCode(200)
                       .statusLine(Matchers.containsString("OK"))
                       .contentType(ContentType.JSON);
	}
	
	public void validateDeletionResponse() {
		response.then().assertThat()
                       .statusCode(204)
                       .statusLine(Matchers.containsString("No Content"));
	}
	
	public void validateResponseHavingSysIdKey() {
		response.then().assertThat().body("result", Matchers.hasKey("sys_id"));
	}

	public void validateSysIdValueShouldNotEmptyOrNull() {
		response.then().assertThat().body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()));
	}
	
	public void validateSysIdValue(String expcted) {
		response.then().assertThat().body("result.sys_id", Matchers.equalTo(expcted));
	}

	public void validateShortDescriptionShouldHaveEmptyString() {
		response.then().assertThat().body("result.short_description", Matchers.emptyString());
	}
	
	public void validateShortDescriptionShouldNotEmptyString() {
		response.then().assertThat().body("result.short_description", Matchers.not(Matchers.emptyString()));
	}
	
	public void validateShortDescriptionShouldHaveValue(String expected) {
		response.then().assertThat().body("result.short_description", Matchers.equalTo(expected));
	}

	public void validateDescriptionShouldHaveEmptyString() {
		response.then().assertThat().body("result.description", Matchers.emptyString());
	}
	
	public void validateDescriptionShouldNotEmptyString() {
		response.then().assertThat().body("result.description", Matchers.not(Matchers.emptyString()));
	}
	
	public void validateDescriptionShouldHaveValue(String expected) {
		response.then().assertThat().body("result.description", Matchers.equalTo(expected));
	}

	public void validateCategoryShouldHaveValue(String expected) {
		response.then().assertThat().body("result.category", Matchers.equalTo(expected));
	}

	public void validateResponseTime(Long expectedSLA) {
		response.then().time(lessThanOrEqualTo(expectedSLA));
	}

	public String extractSysIdValue() {
		return response.then().extract().jsonPath().getString("result.sys_id");
	}

}