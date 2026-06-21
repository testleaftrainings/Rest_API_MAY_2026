package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class LoginPage extends UiBankTestNGHooks {
	
	public LoginPage() {
		getWait().until(ExpectedConditions.titleIs("UiBank-Welcome"));
	}
	
	public LoginPage enterUserName(String username) {
		type(locateElement(Locators.ID, "username"), username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		type(locateElement(Locators.ID, "password"), password);
		return this;
	}
	
	public LoginPage clickSignInButton() {
		click(locateElement(Locators.XPATH, "//button[text()='Sign In']"));
		return this;
	}
	
	public AccountsPage acceptPrivacyPolicy() {
		click(locateElement(Locators.XPATH, "//button[normalize-space(text())='I agree to the Privacy Policy']"));
		return new AccountsPage();
	}

}