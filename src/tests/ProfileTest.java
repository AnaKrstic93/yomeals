package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test (priority = 1)
	public void editProfileTest () throws InterruptedException {
		this.driver.get(this.baseUrl + "/guest-user/login-form");
		locationPopupPage.closeLocationDialog();
		loginPage.logIn(this.email, this.password);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Login Successful"));
		
		this.driver.get(this.baseUrl + "/member/profile");
		profilePage.changeUserInfo("Name", 
								   "Surname", 
								   "Adress", 
								   "1111111", 
								   "123456", 
								   "United Kingdom", 
								   "England", 
								   "London");
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Setup Successful"));
		
		authPage.logOut();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successful"));
	}
}
