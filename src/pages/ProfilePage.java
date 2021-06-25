package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getFirstNameInput () {
		return this.driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement getLastNameInput () {
		return this.driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement getAddressInput () {
		return this.driver.findElement(By.name("user_address"));
	}
	
	public WebElement getPhoneInput () {
		return this.driver.findElement(By.name("user_phone"));
	}
	
	public WebElement getZipCodeInput () {
		return this.driver.findElement(By.name("user_zip"));
	}
	
	public Select getCountryInput () {
		Select country = new Select (this.driver.findElement(By.id("user_country_id")));
		return country;
	}

	public Select getStateInput () {
		Select state = new Select (this.driver.findElement(By.id("user_state_id")));
		return state;
	}

	public Select getCityInput () {
		Select city = new Select (this.driver.findElement(By.id("user_city")));
		return city;
	}
	
	public WebElement getSaveBtn () {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
	public void hoverImg () {
		Actions hover = new Actions (this.driver);
		hover.moveToElement(this.driver.findElement(By.className("avatar")));
	}
	
	public WebElement getUploadImgBtn () {
		return this.driver.findElement(By.xpath("//*[@title = \"Uplaod\"]"));
	}
	
	
	public WebElement getFileInput () {
		return this.driver.findElement(By.xpath("//input [@type = \"file\"]"));
	}
	
	public void uploadPhoto (String imgPath) {
		this.hoverImg();
		js.executeScript("arguments[0].click();", this.getUploadImgBtn());
		this.getFileInput().sendKeys(imgPath);
	}
	
	public WebElement getRemoveImgBtn () {
		return this.driver.findElement(By.className("remove"));
	}
	
	public void removePhoto () {
		this.hoverImg();
		js.executeScript("arguments[0].click();", this.getRemoveImgBtn());
	}
	
	public void changeUserInfo (String firstName, 
								String lastName,
								String address,
								String phoneNum,
								String zipCode,
								String country,
								String state,
								String city) throws InterruptedException {
		
		this.getFirstNameInput().clear();
		this.getFirstNameInput().sendKeys(firstName);
		
		this.getLastNameInput().clear();
		this.getLastNameInput().sendKeys(lastName);
		
		this.getAddressInput().clear();
		this.getAddressInput().sendKeys(address);
		
		this.getPhoneInput().clear();
		this.getPhoneInput().sendKeys(phoneNum);
		
		this.getZipCodeInput().clear();
		this.getZipCodeInput().sendKeys(zipCode);
		
		this.getCountryInput().selectByVisibleText(country);
		Thread.sleep(500);
		this.getStateInput().selectByVisibleText(state);
		Thread.sleep(500);
		this.getCityInput().selectByVisibleText(city);
		Thread.sleep(500);
		this.js.executeScript("arguments[0].click();", this.getSaveBtn());
	}
}

