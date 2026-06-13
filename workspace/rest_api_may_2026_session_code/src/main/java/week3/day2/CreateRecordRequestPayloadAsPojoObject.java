package week3.day2;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRecordRequestPayloadAsPojoObject {
	
	public static void main(String[] args) {
		
		CreateIncidentPojo createIncident = new CreateIncidentPojo();
		
		createIncident.setShort_description("Create Opp - POJO");
		createIncident.setDescription("Create new record using post method");
		createIncident.setCategory("software");
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "@1DGu+KDi8wv")
		           //.contentType(ContentType.JSON)
		           .header("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(createIncident)
		           .post("https://dev195091.service-now.com/api/now/table/incident")
		           .then()
		           .log().all()
		           .statusCode(201)
		           .contentType(ContentType.JSON)
		           //hamcrest
		           .statusLine(Matchers.containsString("Created"))
		           .body("result", Matchers.hasKey("sys_id"))
		           .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		           .body("result.short_description", Matchers.equalTo(createIncident.getShort_description()))
		           .body("result.description", Matchers.equalTo(createIncident.getDescription()))
		           .body("result.category", Matchers.equalTo(createIncident.getCategory()));
		}

}