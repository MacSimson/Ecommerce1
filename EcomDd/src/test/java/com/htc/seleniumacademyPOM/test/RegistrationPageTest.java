package com.htc.seleniumacademyPOM.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.htc.seleniumacademyPOM.page.HomePage;
import com.htc.seleniumacademyPOM.page.LoginPage;
import com.htc.seleniumacademyPOM.page.ReadExcelFile;
import com.htc.seleniumacademyPOM.page.RegistrationPage;

public class RegistrationPageTest {
	RegistrationPage rpage = null;
	HomePage hpage = null;
	LoginPage lpage = null;
	WebDriver driver = null;


	Properties data=null;

	@BeforeClass
	public void loadProptiesFile()
	{
		data=new Properties();
		try {
			FileInputStream fis=new FileInputStream("E:\\java program\\workspace__Selenium_HTC\\EcomDd\\src\\test\\resources\\apps.properties");
			data.load(fis);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\HTC\\selenium\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		hpage=new HomePage(driver);
		lpage=new LoginPage(driver);
		driver.get(hpage.getURL());
	}

	@AfterMethod
	public void tearDown() {
		//chromeDriver.close();
	}
	@DataProvider(name = "ecomLogin_valid")
	public Object[][] ecomStoreSigninValidData()
	{

		ReadExcelFile ref= new ReadExcelFile(data.getProperty("datarepository.ecomstore.signin"));

		Object[][] loginDataSet=ref.getRecords(data.getProperty("datarepository.ecomstore.signin.sheetname.valid"));

		System.out.println("test"+loginDataSet.length);
		return loginDataSet;

	}
	@DataProvider(name = "ecomLogin_invalid")
	public Object[][] ecomStoreSigninInValidData()
	{

		ReadExcelFile ref= new ReadExcelFile(data.getProperty("datarepository.ecomstore.signin"));

		Object[][] loginDataSet=ref.getRecords(data.getProperty("datarepository.ecomstore.signin.sheetname.invalid"));

		System.out.println(loginDataSet.length);
		return loginDataSet;

	}

	@Test(enabled=false)
	@Parameters({"firstName","middleName", "lastName", "email", "passcode", "confirmPasscode", "message"})
	public void testCreateAccount_fillDetails_ShouldCreateNewAccountsuccessfully(String firstname,String middlename, String lastname, String email, String passcode, String confirmPasscode, String message) {
		rpage = new RegistrationPage(driver);

		hpage = rpage.createNewAccount(firstname,middlename, lastname, email, passcode, confirmPasscode);

		//Asserting
		Assert.assertEquals(hpage.isWelcomeTextDisplayed(), message);

		//Logging Out
		hpage.clickAccountButton();
		hpage.clickLogoutButton();
	}

	@Test(dataProvider ="ecomLogin_valid",enabled=false )
	//@Parameters({"email", "passcode"})
	public void testLoginPage_enterCredantials_shouldLoginSuccessful(String ... parameters) {
		//hpage = new HomePage(driver);
		//driver.get(hpage.getURL());

		String email=parameters[0];
		String password=parameters[1];

		if(email!=null && password!=null)
			lpage=hpage.loginWithCredantials(email, password);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(lpage.pageTitle(), parameters[2]);

		//Assert.assertEquals(lpage.pageTitle(), "My Account");

	}
	@Test(dataProvider ="ecomLogin_invalid" )
	public void testBrowserStackSignin_InValidloginCredential_ShouldErrorInSigninPage(String ... parameters)
	{
		//driver.get(hpage.getURL());
		String email=parameters[0];
		String password=parameters[1];

		if(email!=null && password!=null)
			lpage=hpage.loginWithCredantials(email, password);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		hpage.isErrorMsgDisplayed();
		Assert.assertEquals(hpage.isErrorMsgDisplayed(), parameters[2]);
	}
}
