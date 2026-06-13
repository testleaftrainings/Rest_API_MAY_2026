package week3.day2;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

public class FilterHardwareCategoryRecords {

	public static void main(String[] args) {
		
		given()
		 .auth()
		 .basic("admin", "@1DGu+KDi8wv")
		 .queryParam("category", "hardware")
		.when()
		 .get("https://dev195091.service-now.com/api/now/table/incident")
        .then()
         .log().all()
         .assertThat()
         .statusCode(200)
         .statusLine(Matchers.containsString("OK"))
         .body("result.category", Matchers.everyItem(Matchers.equalToIgnoringCase("hardware")));
         
		 
	}

}
