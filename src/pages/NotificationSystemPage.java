package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getMessage () {
		return this.driver.findElement(By.className("alert--success"));
	}
	
	public String getMessageText () {
		return this.getMessage().getText();
	}
	
	public void messageDisappearWait () {
		this.wait.until(ExpectedConditions.attributeContains(getMessage(), "style", "display: none;"));
	}
}
