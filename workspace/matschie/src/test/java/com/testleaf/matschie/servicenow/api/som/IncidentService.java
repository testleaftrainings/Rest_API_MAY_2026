package com.testleaf.matschie.servicenow.api.som;

import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.rest.assured.api.client.RestAssuredApiClient;
import com.testleaf.matschie.servicenow.deserialization.pojos.TableApiJson;
import com.testleaf.matschie.servicenow.serialization.pojos.UpdateIncident;
import com.testleaf.matschie.servicenow.spec.builders.RequestSpecBuilders;
import com.testleaf.matschie.servicenow.spec.builders.ResponseSpecBuilders;

import io.restassured.http.ContentType;

public class IncidentService extends RestAssuredApiClient {
	
	private static final String TABLENAME = "incident";
	
	public IncidentService createNewRecord() {		
		TestUtlis.setResponse(post(RequestSpecBuilders.getTableApiRequestSpec(TABLENAME)
				                                      .contentType(ContentType.JSON), "/{tableName}", null));
		return this;		
	}
	
	public IncidentService retriveARecord(String sysId) {
		TestUtlis.setResponse(get(RequestSpecBuilders.getTableApiRequestSpec(TABLENAME)
				                                     .pathParam("sys_id", sysId), "/{tableName}/{sys_id}"));
		return this; 
	}
	
	public IncidentService updateExistingRecord(String sysId, UpdateIncident updateIncident) {
		TestUtlis.setResponse(put(RequestSpecBuilders.getTableApiRequestSpec(TABLENAME)
				                                     .pathParam("sys_id", sysId)
				                                     .contentType(ContentType.JSON), "/{tableName}/{sys_id}", updateIncident));
		return this; 
	}
	
	public IncidentService deleteExistingRecord(String sysId) {
		TestUtlis.setResponse(delete(RequestSpecBuilders.getTableApiRequestSpec(TABLENAME)
				                                        .pathParam("sys_id", sysId), "/{tableName}/{sys_id}"));
		return this;
	}
	
	public IncidentService checkRecordSuccessfullyCreated() {
		TestUtlis.getResponse().then().spec(ResponseSpecBuilders.successCreateResponse());
		return this;
	}
	
	public IncidentService checkParticularRecordRetrivedSuccessfully() {
		TestUtlis.getResponse().then().spec(ResponseSpecBuilders.successJsonResponse());
		return this;		
	}
	
	public IncidentService checkExistingRecordGotUpdatedSuccessfully() {
		TestUtlis.getResponse().then().spec(ResponseSpecBuilders.successJsonResponse());
		return this;
	}
	
	public IncidentService checkExistingRecordGotDeletedSuccessfully() {
		TestUtlis.getResponse().then().spec(ResponseSpecBuilders.successNoContentResponse());
		return this;
	}
	
	public IncidentService extractSysid() {
		String sysId = TestUtlis.getResponse().as(TableApiJson.class).getResult().getSysId();
		TestUtlis.setTestData("sys_id", sysId);
		return this;
	}

}