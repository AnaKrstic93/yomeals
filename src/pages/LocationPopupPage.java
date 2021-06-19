package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver) {
		super(driver);
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
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
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
	
	public void setLocationName (String locationName) {
		this.getKeyword().click();
		
	}
}
