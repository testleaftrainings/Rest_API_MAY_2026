package com.testleaf.makaia.uibank.e2e.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testleaf.makaia.general.utils.FakerData;
import com.testleaf.makaia.general.utils.PropertiesHandler;
import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.uibank.api.som.AccountService;
import com.testleaf.makaia.uibank.ui.pages.LoginPage;

public class AccountFeaturee2eTest extends UiBankTestNGHooks {
	
	private String nickName;
	
	@BeforeClass
	public void beforeClass() {
		nickName = FakerData.generateRandomName();	
	}
	
	@Test(priority = 1)
	public void userShouldAbleToCreateAccountInUI() {
		new LoginPage()
		    .enterUserName(PropertiesHandler.config("uibank.username"))
		    .enterPassword(PropertiesHandler.secret("uibank.password"))
		    .clickSignInButton()
		    .acceptPrivacyPolicy()
		    .clickOnTheApplyNewAccountBtn()
		    .enterNickName(nickName)
		    .selectTypeOfAccount("savings")
		    .clickApplyButton()
		    .validateNickName(nickName)
		    .extractAccountNumber();
	}
	
	@Test(priority = 2)
	public void userShouldAbleToSeeCreatedAccountDetailsInAPI() {
		new AccountService()
		    .fetchAccountDetailsByNickName(nickName)
		    .validateAccountDetailsFetchedSuccessfully()
		    .validateAccountNumber(Integer.parseInt(TestUtils.getTestData("accountId")))
		    .validateAccountNickName(nickName)
		    .validateAccountType("savings");
	}	

}