package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class AccountCreateResultPage extends UiBankTestNGHooks {
	
	public AccountCreateResultPage() {
		getWait().until(ExpectedConditions.titleIs("UiBank-Creation Result"));
	}
	
	public AccountCreateResultPage extractAccountNumber() {
		TestUtils.setTestData("accountId", getElementText(locateElement(Locators.ID, "accountId")));
		return this;
	}
	
	public AccountCreateResultPage validateNickName(String name) {
		Assert.assertEquals(getElementText(locateElement(Locators.ID, "accountName")), name);
		return this;
	}

}