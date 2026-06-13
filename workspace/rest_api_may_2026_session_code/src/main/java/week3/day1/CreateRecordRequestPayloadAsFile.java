package week3.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRecordRequestPayloadAsFile {
	
	public static void main(String[] args) {
		
		String request_payload = """
				{
                  "short_description":"Create Opp",
                  "description":"Create new record using post method"
                }
				""";
		
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