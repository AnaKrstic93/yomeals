package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {
	
	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait wait;
	
	protected String baseUrl = "http://demo.yo-meals.com";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;
	
	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		this.driver = new ChromeDriver ();
		this.js = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait (driver, 20);

		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait (20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout (20, TimeUnit.SECONDS);
		
		locationPopupPage = new LocationPopupPage (driver, js, wait);
		loginPage = new LoginPage (driver, js, wait);
		notificationSystemPage = new NotificationSystemPage (driver, js, wait);
		profilePage = new ProfilePage (driver, js, wait);
		authPage = new AuthPage (driver, js, wait);
		mealPage = new MealPage (driver, js, wait);
		cartSummaryPage = new CartSummaryPage (driver, js, wait);
		searchResultPage = new SearchResultPage (driver, js, wait);
	}
	
	@AfterMethod
	public void cleanup (ITestResult testResults) throws IOException, InterruptedException {
		if (testResults.getStatus() == ITestResult.FAILURE) {
			
			TakesScreenshot src = (TakesScreenshot) driver;
			File screenshot = src.getScreenshotAs(OutputType.FILE);
			
			LocalDateTime localDateTime = LocalDateTime.now();
			DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
			
			FileHandler.copy(screenshot, new File ("./Screenshots/" + localDateTime.format(formatDateTime) + ".png"));
		}
		
		Thread.sleep(1000);
		this.driver.quit();
		
	}

}
