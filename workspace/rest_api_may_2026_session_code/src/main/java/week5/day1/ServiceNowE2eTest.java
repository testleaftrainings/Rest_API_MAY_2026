package week5.day1;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import week3.day2.UpdateIncidentPojo;

public class ServiceNowE2eTest {	
	
	private String sysId;
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private IncidentService incidentService = new IncidentService();
	
	@BeforeSuite
	public void beforesSuite() {
		requestSpecBuilder.setBaseUri("https://dev195091.service-now.com");
		requestSpecBuilder.setBasePath("/api/now/table");
		requestSpecBuilder.setAuth(RestAssured.basic("admin", "@1DGu+KDi8wv"));		
		requestSpecBuilder.addFilter(new AllureRestAssured());
		requestSpecBuilder.addFilter(new RequestLoggingFilter());
		requestSpecBuilder.addFilter(new ResponseLoggingFilter());
	}	
	
	@Test(priority = 1)
	public void createARecordWithoutRequestBody() {	
	
	 incidentService.createNewRecord(given()
		                             .spec(requestSpecBuilder.build())      
                                     .header("Content-Type", "application/json"));
	 
	 incidentService.validateCreationResponse();
	 incidentService.validateResponseHavingSysIdKey();
	 incidentService.validateSysIdValueShouldNotEmptyOrNull();
	 incidentService.validateShortDescriptionShouldHaveEmptyString();
	 incidentService.validateDescriptionShouldHaveEmptyString();
	 incidentService.validateCategoryShouldHaveValue("inquiry");
	 incidentService.validateResponseTime(5000L);
	 
	 sysId = incidentService.extractSysIdValue();
	 
	}

	@Test(priority = 2)
	public void retrieveARecord() {		
		
		incidentService.retrieveExitingRecord(given()
		                .spec(requestSpecBuilder.build()), sysId);
		
		incidentService.validateSuccessResponse();
		incidentService.validateResponseTime(2500L);
		incidentService.validateSysIdValue(sysId);
		incidentService.validateShortDescriptionShouldHaveEmptyString();
		incidentService.validateDescriptionShouldHaveEmptyString();
		incidentService.validateCategoryShouldHaveValue("inquiry");
	}

	@Test(priority = 3)
	public void updateExistingRecord() {
		UpdateIncidentPojo updateIncident = new UpdateIncidentPojo();
		updateIncident.setShort_description("Update Record");
		updateIncident.setDescription("Update existing record using put method");
		updateIncident.setCategory("database");
				
		incidentService.updateExitingRecord(given().spec(requestSpecBuilder.build())
		                              .contentType(ContentType.JSON), sysId, updateIncident);
		
		incidentService.validateSuccessResponse();
		incidentService.validateShortDescriptionShouldNotEmptyString();
		incidentService.validateShortDescriptionShouldHaveValue(updateIncident.getShort_description());
		incidentService.validateDescriptionShouldNotEmptyString();
		incidentService.validateDescriptionShouldHaveValue(updateIncident.getDescription());
		incidentService.validateCategoryShouldHaveValue(updateIncident.getCategory());		 
	}
	
	@Test(priority = 4)
	public void delteExistingRecord() {
        
		incidentService.deleteExitingRecord(given()
		               .spec(requestSpecBuilder.build()), sysId);
        
		incidentService.validateDeletionResponse();
	}
}