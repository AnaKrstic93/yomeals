package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait); 
	}
	
	public WebElement getQuantityInput() {
		return this.driver.findElement(By.name("product_qty"));
	}
	
	public WebElement getAddToCartBtn () {
		return this.driver.findElement(By.linkText(" Add To Cart "));
	}
	
	public WebElement getAddToFavorite() {
		return driver.findElement(By.xpath("//a[@title=\"Favorite\"]"));
	}
	
	public void addMealToCart (String quantity) {
		this.getQuantityInput().click();
		this.getQuantityInput().sendKeys(Keys.BACK_SPACE);
		this.getQuantityInput().sendKeys(quantity);
		this.getAddToCartBtn().click();
	}
	
	public void addToFavourite () {
		this.getAddToFavorite().click();
	}
}
