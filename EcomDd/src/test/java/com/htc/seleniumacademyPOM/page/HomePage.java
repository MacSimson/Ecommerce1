package com.htc.seleniumacademyPOM.page;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class HomePage {
	protected WebDriver driver;
	private By welcomeTextBy = By.xpath("//*[@id=\\\"top\\\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li/span");
	private By accountButtonBy = By.xpath("(//span[text() = \"Account\"])[1]");
	private By logoutButtonBy = By.xpath("//a[text() = \"Log Out\"]");
	private By loginButtonBy = By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a");
	private By emailTextBoxBy = By.xpath("//input[@title = \"Email Address\"]");
	private By passcodeTextBoxBy = By.xpath("//input[@title = \"Password\"]");
	private By loginbtnBy = By.xpath("//span[text() = \"Login\"]");
	private By errorMsgBy = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li/span");
	private String URL = "http://demo-store.seleniumacademy.com/";

	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String isWelcomeTextDisplayed() {
		String text =  driver.findElement(welcomeTextBy).getText();
		return text;
	}
	public String isErrorMsgDisplayed() {
		//boolean flag=false;
		String text =  driver.findElement(errorMsgBy).getText();	
		if(text!=null) {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			//flag=true;
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			File DestFile=new File("D:\\ScreenShot\\login.jpg");

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return text;
	}

	public void clickAccountButton() {
		driver.findElement(accountButtonBy).click();
	}

	public void clickLogoutButton() {
		driver.findElement(logoutButtonBy).click();
	}

	public void clickLoginButton() {
		driver.findElement(loginButtonBy).click();
	}

	public void setEmail(String email) {
		driver.findElement(emailTextBoxBy).sendKeys(email);
	}

	public void setPasscode(String passcode) {
		driver.findElement(passcodeTextBoxBy).sendKeys(passcode);
	}
	
	public void clickFinalLoginButton() {
		driver.findElement(loginbtnBy).click();
	}

	public LoginPage loginWithCredantials(String email, String passcode) {
		LoginPage lpage = null;

		this.clickAccountButton();
		this.clickLoginButton();
		this.setEmail(email);
		this.setPasscode(passcode);
		this.clickFinalLoginButton();
		
		lpage = new LoginPage(driver);
		
		return lpage;

	}
	public String getURL() {
		return URL;
	}
}
