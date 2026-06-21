package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class AccountApplyPage extends UiBankTestNGHooks {
	
	public AccountApplyPage() {
		getWait().until(ExpectedConditions.titleIs("UiBank-Apply"));
	}
	
	public AccountApplyPage enterNickName(String name) {
		type(locateElement(Locators.ID, "accountNickname"), name);
		return this;
	}
	
	public AccountApplyPage selectTypeOfAccount(String value) {
		dropdownSelectByValue(locateElement(Locators.ID, "typeOfAccount"), value);
		return this;
	}
	
	public AccountCreateResultPage clickApplyButton() {
		click(locateElement(Locators.XPATH, "//button[text()='Apply']"));
		return new AccountCreateResultPage();
	}

}