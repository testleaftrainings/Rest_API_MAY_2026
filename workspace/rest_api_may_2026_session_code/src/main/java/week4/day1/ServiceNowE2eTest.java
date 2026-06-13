package week4.day1;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.UpdateIncidentPojo;

public class ServiceNowE2eTest {
	
	private static final String TABLENAME = "incident";
	private String sysId;
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	
	@BeforeSuite
	public void beforesSuite() {
		requestSpecBuilder.setBaseUri("https://dev195091.service-now.com");
		requestSpecBuilder.setBasePath("/api/now/table");
		requestSpecBuilder.setAuth(RestAssured.basic("admin", "@1DGu+KDi8wv"));
		requestSpecBuilder.addPathParam("tableName", TABLENAME);
		requestSpecBuilder.addFilter(new AllureRestAssured());
		requestSpecBuilder.addFilter(new RequestLoggingFilter());
		requestSpecBuilder.addFilter(new ResponseLoggingFilter());
	}	
	
	@Test(priority = 1)
	public void createARecord() {
		
	
	RequestSpecification preCondtions = given()
		                                .spec(requestSpecBuilder.build())      
                                        .header("Content-Type", "application/json");
                 
	Response response = preCondtions.post("/{tableName}");
	
	sysId = response.then()                
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    //hamcrest
                    .statusLine(containsString("Created"))
                    .body("result", hasKey("sys_id"))
                    .body("result.sys_id", not(emptyOrNullString()))
                    .body("result.short_description", emptyString())
                    .body("result.description", emptyString())
                    .body("result.category", equalTo("inquiry"))
                    .time(lessThanOrEqualTo(5000L))
                    .extract()
                    .jsonPath()
                    .getString("result.sys_id");	
	
	}
	
	@Test(priority = 2)
	public void retrieveARecord() {
		
		RequestSpecification preCondition = given()
		         .spec(requestSpecBuilder.build())
		         .pathParam("sys_id", sysId);	
		
		
		Response response = preCondition.get("/{tableName}/{sys_id}");
		
		 response.then()
		 .assertThat()
		 .statusCode(200)
		 .statusLine(containsString("OK"))
		 .time(lessThanOrEqualTo(2500L))
		 .body("result.sys_id", equalTo(sysId));
	}

	@Test(priority = 3)
	public void updateExistingRecord() {
		UpdateIncidentPojo updateIncident = new UpdateIncidentPojo();
		updateIncident.setShort_description("Update Record");
		updateIncident.setDescription("Update existing record using put method");
		updateIncident.setCategory("database");
		
		RequestSpecification preCondition = given()
		 .spec(requestSpecBuilder.build())
		 .pathParam("sys_id", sysId)
		 .contentType(ContentType.JSON);
		
		Response response = preCondition.body(updateIncident).put("/{tableName}/{sys_id}"); 
		
		response.then()
		 .assertThat()
		 .statusCode(200)
		 .statusLine(containsString("OK"))
		 .contentType(ContentType.JSON)
		 .body("result.short_description", equalTo(updateIncident.getShort_description()))
		 .body("result.description", equalTo(updateIncident.getDescription()))
		 .body("result.category", equalTo(updateIncident.getCategory()));
	}
	
	@Test(priority = 4)
	public void delteExistingRecord() {
		RequestSpecification preCondition = given()
		 .spec(requestSpecBuilder.build())
         .pathParam("sys_id", sysId);
        
		Response response = preCondition.delete("/{tableName}/{sys_id}");
        
		response.then()
         .log().all()
         .assertThat()
         .statusCode(204)
         .statusLine(containsString("No Content"));
	}
}