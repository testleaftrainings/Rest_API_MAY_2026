package week3.day2;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

public class ServiceNowE2eTest {
	
	private static final String TABLENAME = "incident";
	private String sysId;
	
	@Test(priority = 1)
	public void createARecord() {
		
		sysId = given()
				 .filter(new AllureRestAssured())
                 .auth()
                 .basic("admin", "@1DGu+KDi8wv")       
                 .header("Content-Type", "application/json")
                 .log().all()
                .when()       
                 .post("https://dev195091.service-now.com/api/now/table/"+TABLENAME)
                 .then()
                 .log().all()
                 .statusCode(201)
                 .contentType(ContentType.JSON)
                 //hamcrest
                 .statusLine(containsString("Created"))
                 .body("result", hasKey("sys_id"))
                 .body("result.sys_id", not(emptyOrNullString()))
                 .body("result.short_description", emptyString())
                 .body("result.description", emptyString())
                 .body("result.category", equalTo("inquiry"))
                 .time(lessThanOrEqualTo(3000L))
                 .extract()
                 .jsonPath()
                 .getString("result.sys_id");	
	
	}
	
	@Test(priority = 2)
	public void retrieveARecord() {
		
		given()
		 .filter(new AllureRestAssured())
		 .auth()
		 .basic("admin", "@1DGu+KDi8wv")
		 .pathParam("tableName", TABLENAME)
		 .pathParam("sys_id", sysId)		
		.when()
		 .get("https://dev195091.service-now.com/api/now/table/{tableName}/{sys_id}")
		.then()
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
		
		given()
		 .filter(new AllureRestAssured())
		 .auth()
		 .basic("admin", "@1DGu+KDi8wv")
		 .pathParam("tableName", TABLENAME)
		 .pathParam("sys_id", sysId)
		 .contentType(ContentType.JSON)
		.when()
		 .body(updateIncident)
		 .put("https://dev195091.service-now.com/api/now/table/{tableName}/{sys_id}") 
		.then()
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
		given()
		 .filter(new AllureRestAssured())
         .auth()
         .basic("admin", "@1DGu+KDi8wv")
         .pathParam("tableName", TABLENAME)
         .pathParam("sys_id", sysId)
         .log().all()
        .when()
         .delete("https://dev195091.service-now.com/api/now/table/{tableName}/{sys_id}")
        .then()
         .log().all()
         .assertThat()
         .statusCode(204)
         .statusLine(containsString("No Content"));
	}
}