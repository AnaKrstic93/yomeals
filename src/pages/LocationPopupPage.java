package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}
	
	public WebElement getSelectLocation () {
		return this.driver.findElement(By.className("location-selector"));
	}
	
	public WebElement getCloseBtn () {
		return this.driver.findElement(By.className("close-btn-white"));
	}
	
	public WebElement getKeyword () {
		return this.driver.findElement(By.id("locality_keyword"));
	}
	
	public WebElement getLocationItem (String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), \"" + locationName + "\")]/.."));
	}
	
	public WebElement getLocationInput () {
		return this.driver.findElement(By.id("location_id"));
	}
	
	public WebElement getSubmit () {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
	public void openLocationDialog () {
		this.getSelectLocation().click();
	}
	
	public void setLocationName (String locationName) throws InterruptedException {
		this.getKeyword().click();
		Thread.sleep(500);
		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		Thread.sleep(500);
		js.executeScript("arguments[0].value=arguments[1];", this.getLocationInput(), dataValue);
		Thread.sleep(500);
		js.executeScript("arguments[0].click();", this.getSubmit());
	}
	
	public void closeLocationDialog () {
		this.getCloseBtn().click();
	}
}
