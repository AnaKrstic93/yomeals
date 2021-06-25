package tests;

import java.io.File;
import java.io.IOException;

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
	
	@Test (priority = 2)
	public void changeProfileImageTest () throws IOException {
		this.driver.get(this.baseUrl + "/guest-user/login-form");
		locationPopupPage.closeLocationDialog();
		loginPage.logIn(this.email, this.password);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Login Successful"));
		
		this.driver.get(this.baseUrl + "/member/profile");
		String imgPath = new File ("img//image.png").getCanonicalPath();
		profilePage.uploadPhoto(imgPath);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Uploaded Successfully"));
		notificationSystemPage.messageDisappearWait();
		
		profilePage.removePhoto();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Deleted Successfully"));
		notificationSystemPage.messageDisappearWait();
		
		authPage.logOut();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successful"));
	}
}
