package week3.day1;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRecordRequestPayloadAsString {
	
	public static void main(String[] args) {
		
		File request_payload = new File("src/main/resources/request_payload/create-incident.json");
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "@1DGu+KDi8wv")
		           //.contentType(ContentType.JSON)
		           .header("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(request_payload)
		           .post("https://dev195091.service-now.com/api/now/table/incident")
		           .then()
		           .log().all()
		           .statusCode(201)
		           .contentType(ContentType.JSON);
		}

}