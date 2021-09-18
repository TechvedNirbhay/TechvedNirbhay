package ninja.techved.com_testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ninja.techved.com_pageObject.P1_Login;
import ninja.techved.com_pageObject.P2_Welcome;

public class TC1_Login extends BaseClass{
	
	
	// @Test(groups={""})
	@Test()
	public void login() throws IOException {	
		
		try {
		
			P1_Login lp = new P1_Login(driver);
		
		lp.setTextInUnameField(Username);
		logger.info("User name input succefully");
		lp.setTextInUpassField(Password);
		logger.info("User pass input succefully");
		lp.clickOnSubmitButton();
		logger.info("click on submit button succefully");
		
		P2_Welcome wp = new P2_Welcome(driver);
		String welcomeText = wp.getText();
		
		Assert.assertEquals(welcomeText,"Nirbhay Deep Trigunayat", "Login Pass");
		Assert.assertTrue(true);
		
		/*
		 * if (welcomeText.equals("Nirbhay Deep Trigunayat")) {
		 * 
		 * logger.info("Login Pass"); Assert.assertTrue(true);
		 * 
		 * }
		 * 
		 * else {
		 * 
		 * logger.info("Login fail"); BaseClass.captureScreen("login");
		 * Assert.assertTrue(false);
		 * 
		 * 
		 * }
		 */
		
		}catch(Exception e) {
			
			e.getMessage();
			logger.info("Login fail");
			BaseClass.captureScreen("login");
			Assert.assertTrue(false);
		}
	}

}
