package week5.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ExtractResponseValueUsingPojo {

	public static void main(String[] args) {
		
		// Using the JSON path
		String value = RestAssured.given()
		           .baseUri("https://dev195091.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "@1DGu+KDi8wv")
		           .pathParam("tableName", "incident")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .post("/{tableName}")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(201)
		           .contentType(ContentType.JSON)
		           .extract()
		           .jsonPath()
		           .getString("result.opened_by.value");
		
		System.out.println(value);
		
		// Using POJO De-serialization pattern to extract value from response
		CreateResponsePojo createResponse = RestAssured.given()
        .baseUri("https://dev195091.service-now.com")
        .basePath("/api/now/table")
        .auth()
        .basic("admin", "@1DGu+KDi8wv")
        .pathParam("tableName", "incident")
        .contentType(ContentType.JSON)
        .log().all()
        .when()
        .post("/{tableName}")
        .then()
        .log().all()
        .assertThat()
        .statusCode(201)
        .contentType(ContentType.JSON)
        .extract()
        .as(CreateResponsePojo.class);
		
		System.out.println(createResponse.getResult().getMade_sla());
		System.out.println(createResponse.getResult().getOpened_by().getValue());
		System.out.println(createResponse.getResult().getSys_id());
		

	}

}
