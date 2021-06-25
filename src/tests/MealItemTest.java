package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test (priority = 1)
	public void addMealToCartTest () throws InterruptedException {
		this.driver.get(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closeLocationDialog();
		mealPage.addMealToCart("2");
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("The Following Errors Occurred:"),
			      "[ERROR] Add to cart not possible.");
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Please Select Location"),
			      "[ERROR] Location selection needed.");
		notificationSystemPage.messageDisappearWait();
		
		locationPopupPage.openLocationDialog();
		Thread.sleep(1000);
		locationPopupPage.setLocationName("City Center - Albany");
		Thread.sleep(1000);
		mealPage.addMealToCart("2");
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"),
			      "[ERROR] Verification message doesn't appear.");
	}
	
	@Test (priority = 2)
	public void addMealToFavouriteTest () throws InterruptedException {
		this.driver.get(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		mealPage.addToFavourite();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Please login first!"),
			      "[ERROR] Login needed.");
		
		this.driver.get(this.baseUrl + "/guest-user/login-form");
		loginPage.logIn(this.email, this.password);
		this.driver.get(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		mealPage.addToFavourite();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Product has been added to your favorites"),
			      "[ERROR] Product addition to favourites faliure.");
	}
	
	@Test (priority = 3)
	public void clearCartTest () throws IOException, InterruptedException {
		File file = new File ("data/Data.xlsx");
		FileInputStream fis = new FileInputStream (file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		SoftAssert softAssert = new SoftAssert();
		
		this.driver.get(this.baseUrl + "/meals");
		locationPopupPage.setLocationName("City Center - Albany");
		
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();
			
			Thread.sleep(1000);
			this.driver.get(mealUrl);
			mealPage.addMealToCart("\"" + quantity + "\"");
			
			softAssert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"),
				      "[ERROR] Meal addition to cart faliure.");
		}
		
		cartSummaryPage.clearAll();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("All meals removed from Cart successfully"),
			      "[ERROR] Cart clearing faliure.");
	}
}
