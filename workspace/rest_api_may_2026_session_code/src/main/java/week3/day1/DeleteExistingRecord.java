package week3.day1;

import io.restassured.RestAssured;

public class DeleteExistingRecord {

	public static void main(String[] args) {
		RestAssured.given()
		           .auth()
		           .basic("admin", "@1DGu+KDi8wv")
		           .pathParam("tableName", "incident")
		           .pathParam("sys_id", "149b2c35839dc7107e5e9d45eeaad39f")
		           .log().all()
		           .when()
		           .delete("https://dev195091.service-now.com/api/now/table/{tableName}/{sys_id}")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(204);
	}

}