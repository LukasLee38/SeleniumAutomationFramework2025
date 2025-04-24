package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void testValidationLogin() {
		
		LoginPage loginpage = new LoginPage(driver);
		
		loginpage.enterUsername("admin@yourstore.com");
		loginpage.enterPasswod("admin");
		loginpage.clickLogin();
		
		System.out.println("Title of the page is : " + driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		
		
	}
}
