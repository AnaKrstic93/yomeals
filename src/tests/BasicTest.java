package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public abstract class BasicTest {
	
	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait wait;
	
	protected String baseUrl = "demo.yo-meals.com";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	
	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver");

		this.driver = new ChromeDriver ();
		this.js = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait (driver, 10);

		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait (10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout (10, TimeUnit.SECONDS);
		
		locationPopupPage = new LocationPopupPage (driver, js, wait);
		loginPage = new LoginPage (driver, js, wait);
		notificationSystemPage = new NotificationSystemPage (driver, js, wait);
		profilePage = new ProfilePage (driver, js, wait);
		authPage = new AuthPage (driver, js, wait);
		mealPage = new MealPage (driver, js, wait);
		cartSummaryPage = new CartSummaryPage (driver, js, wait);
	}
}
