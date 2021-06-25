package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

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
	
}
