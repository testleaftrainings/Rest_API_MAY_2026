package week5.day1;

import java.util.List;
import java.util.stream.Collectors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ExtractJsonArrayResponseUsingPojoJava8 {
	
	public static void main(String[] args) {		
		
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
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .extract()        
        .as(RetrieveAllRecordsResponsePojo.class);
		
		List<Result> records = allRecordsResponse.getResult();
		
		for (Result result : records) {
			if(!result.getCategory().isEmpty()) {
				System.out.println(result.getCategory());
			}
		}
		
		System.out.println("");
		
		records.stream().map(record -> record.getCategory())
		                .filter(category -> !category.isEmpty())
		                .filter(category -> category.equalsIgnoreCase("Hardware"))
		                .collect(Collectors.toList())
		                .forEach(System.out::println);
		
	}

}