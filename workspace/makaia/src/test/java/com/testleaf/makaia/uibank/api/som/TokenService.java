package com.testleaf.makaia.uibank.api.som;

import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.uibank.deserialization.pojos.CreateTokenResponse;

import io.restassured.http.ContentType;

public class TokenService extends UiBankAPI {
	
	private static final String ENDPOINT = "/users/login";
	
	public TokenService() {
		requestBuilder = globalRequest();
	}
	
	public TokenService createToken() {
		response = apiClient.post(requestBuilder.setContentType(ContentType.JSON), ENDPOINT, """
				{
                  "username": "FebApiuser",
                  "password": "Eagle@123"
                } """);
		return this;
	}
	
	public TokenService validateTokenCreatedSuccessfully() {
		response.then().assertThat().statusCode(200)
		                            .contentType(ContentType.JSON);
		return this;
	}
	
	public TokenService extractToken() {
		CreateTokenResponse createTokenResponse = deSerializeResponse(response.asPrettyString(), CreateTokenResponse.class);
		TestUtils.setTestData("token", createTokenResponse.getId());
		return this;
	}

}
