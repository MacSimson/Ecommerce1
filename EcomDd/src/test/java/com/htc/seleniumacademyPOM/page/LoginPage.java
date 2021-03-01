package com.htc.seleniumacademyPOM.page;

import org.openqa.selenium.WebDriver;

public class LoginPage {
	protected WebDriver driver;
	
	
	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}

	

}
