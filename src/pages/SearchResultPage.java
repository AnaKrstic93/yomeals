package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public List<WebElement> getSearchResults () {
		return driver.findElements(By.xpath("//div[@class=\"product-name\"]/a"));
	}
	
	public ArrayList<String> getSearchResultsNames () {
		ArrayList<String> mealNames = new ArrayList<String> ();
		for (int i = 0; i < this.getResultsNumber(); i++) {
			mealNames.add(this.getSearchResults().get(i).getText());
		}
		return mealNames;
	}
	
	public int getResultsNumber() {
		return getSearchResults().size();
	}

}
