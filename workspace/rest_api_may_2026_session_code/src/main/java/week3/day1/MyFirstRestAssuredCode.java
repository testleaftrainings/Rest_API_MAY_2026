package week3.day1;

import io.restassured.RestAssured;

public class MyFirstRestAssuredCode {
	
	public static void main(String[] args) {
		RestAssured.given()
		           .auth()
		           .basic("admin", "@1DGu+KDi8wv")
		           .log().all()
		           .when()
		           .get("https://dev195091.service-now.com/api/now/table/incident")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200);
	}

}