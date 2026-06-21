package com.testleaf.makaia.uibank.api.som;

import org.testng.Assert;

import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.uibank.deserialization.pojos.AccountDetailsResponse;

import io.restassured.http.ContentType;

public class AccountService extends UiBankAPI {
	
	private static final String ENDPOINT = "/accounts";
	
	public AccountService() {
		requestBuilder = globalRequest();
	}
	
	public AccountService fetchAccountDetailsByNickName(String name) {
		response = apiClient.get(requestBuilder.addHeader("Authorization", "Bearer " + TestUtils.getTestData("token"))
				.addQueryParam("filter[where][friendlyName]", name), ENDPOINT);
		return this;
	}
	
	public AccountService createNewAccount() {
		return this;
	}
	
	public AccountService validateAccountDetailsFetchedSuccessfully() {
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
		return this;
	}
	
	public AccountService validateAccountNumber(int number) {
		AccountDetailsResponse[] accountDetailsResponses = deSerializeResponse(response.asPrettyString(), AccountDetailsResponse[].class);
		Assert.assertEquals(accountDetailsResponses[0].getAccountNumber(), number);
		return this;
	}
	
	public AccountService validateAccountNickName(String name) {
		AccountDetailsResponse[] accountDetailsResponses = deSerializeResponse(response.asPrettyString(), AccountDetailsResponse[].class);
		Assert.assertEquals(accountDetailsResponses[0].getFriendlyName(), name);
		return this;
	}
	
	public AccountService validateAccountType(String accountType) {
		AccountDetailsResponse[] accountDetailsResponses = deSerializeResponse(response.asPrettyString(), AccountDetailsResponse[].class);
		Assert.assertEquals(accountDetailsResponses[0].getType(), accountType);
		return this;
	}

}