package week3.day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ValidateResponseFormatShouldBeXML {
	
	@Test
	public void validateResponseFormat() {
		RestAssured.given()
		           .auth()
		           .basic("admin", "@1DGu+KDi8wv")
		          //.header("Accept", "application/xml")
		           .accept(ContentType.XML)
		           .when()
		           .get("https://dev195091.service-now.com/api/now/table/incident")
		           .then()
		           .assertThat()
		           .statusCode(200)
		           .contentType(ContentType.XML);
	}

}