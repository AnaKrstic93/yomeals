package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getUsernameInput () {
		return this.driver.findElement(By.name("username"));
	}
	
	public WebElement getPasswordInput () {
		return this.driver.findElement(By.name("password"));
	}
	
	public WebElement getLoginButton () {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
	public void logIn (String username, String password) {
		this.getUsernameInput().clear();
		this.getUsernameInput().sendKeys(username);
		this.getPasswordInput().clear();
		this.getPasswordInput().sendKeys(password);
		this.getLoginButton().click();
	}
}
