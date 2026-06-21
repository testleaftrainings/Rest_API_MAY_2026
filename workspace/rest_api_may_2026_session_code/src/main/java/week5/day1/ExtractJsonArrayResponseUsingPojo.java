package week5.day1;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ExtractJsonArrayResponseUsingPojo {
	
	public static void main(String[] args) {
	
		
        List<String> hardwareCategory = new ArrayList<String>();
		
		// Using JSON path
		List<String> category = RestAssured.given()
        .baseUri("https://dev195091.service-now.com")
        .basePath("/api/now/table")
        .auth()
        .basic("admin", "@1DGu+KDi8wv")
        .pathParam("tableName", "incident")       
        .log().all()
        .when()
        .get("/{tableName}")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .extract()
        .jsonPath()
        .getList("result.category");
		
		System.out.println(category);
		
		for (String eachCategory : category) {
			if(eachCategory.equalsIgnoreCase("hardware")) {
				hardwareCategory.add(eachCategory);
			}
		}
		
		System.out.println(hardwareCategory);
		System.out.println(hardwareCategory.size());
		
		// Using POJO De-Serialization
		
		RetrieveAllRecordsResponsePojo allRecordsResponse = RestAssured.given()
        .baseUri("https://dev195091.service-now.com")
        .basePath("/api/now/table")
        .auth()
        .basic("admin", "@1DGu+KDi8wv")
        .pathParam("tableName", "incident")       
        .log().all()
        .when()
        .get("/{tableName}")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .extract()
        .as(RetrieveAllRecordsResponsePojo.class);
		
		List<Result> records = allRecordsResponse.getResult();
		
		for (Result record : records) {
			if(record.getCategory().equalsIgnoreCase("hardware")) {
				System.out.println(record.getCategory());
			}
		}
		
		
	}

}