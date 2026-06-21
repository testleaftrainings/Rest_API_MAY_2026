package com.testleaf.matschie.servicenow.tests;

import org.testng.annotations.Test;

import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.servicenow.api.som.IncidentService;
import com.testleaf.matschie.servicenow.serialization.pojos.UpdateIncident;

public class ServiceNowE2eTest {
	
	@Test(priority = 1)
	public void userShouldAbleToCreateNewRecordWithoutRequestBody() {
		new IncidentService()
		    .createNewRecord()
		    .checkRecordSuccessfullyCreated()
		    .extractSysid();		
	}
	
	@Test(priority = 2)
	public void userShouldAbleToFetchParticularRecordBasedOnSysId() {
		new IncidentService()
		    .retriveARecord(TestUtlis.getTestData("sys_id"))
		    .checkParticularRecordRetrivedSuccessfully();
	}
	
	@Test(priority = 3)
	public void userShouldAbleToModifyParticularRecordDetails() {
		UpdateIncident updateIncident = new UpdateIncident();
		updateIncident.setShortDescription("Update Operation");
		updateIncident.setDescription("Update the exiting content");
		updateIncident.setCategory("database");
		
		new IncidentService()
		    .updateExistingRecord(TestUtlis.getTestData("sys_id"), updateIncident)
		    .checkExistingRecordGotUpdatedSuccessfully();
	}
	
	@Test(priority = 4)
	public void userShouldAbleToDeleteExitsingRecord() {
		new IncidentService()
		    .deleteExistingRecord(TestUtlis.getTestData("sys_id"))
		    .checkExistingRecordGotDeletedSuccessfully();
	}

}