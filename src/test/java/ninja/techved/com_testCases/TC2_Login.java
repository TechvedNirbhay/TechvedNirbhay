package ninja.techved.com_testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ninja.techved.com_pageObject.P1_Login;
import ninja.techved.com_pageObject.P2_Welcome;
import ninja.techved.com_utilities.ReadConfig;
import ninja.techved.com_utilities.XLUtils;

public class TC2_Login extends BaseClass {
	
	
	@Test(dataProvider="LoginData")
	public void TC2_Login(String user,String pwd) throws InterruptedException
	{
		try {
		P1_Login lp=new P1_Login(driver);
		lp.setTextInUnameField(user);
		Thread.sleep(1000);
		logger.info("user name provided");
		Thread.sleep(1000);
		lp.setTextInUpassField(pwd);
		Thread.sleep(1000);
		lp.selectRadioButtonEmp();
		logger.info("Select Emp Radio button successfully");
		Thread.sleep(1000);
		lp.clickOnSubmitButton();
		logger.info("Click on submit button successfully");
		Thread.sleep(3000);
		
		String ErrMessage =lp.getErrMassFromPage();
		
		if(ErrMessage.equals("Enter valid email id"))
		{  
			lp.clearPasswordInputFields();
			lp.clearUsernameTextFilds();
			
			logger.warn("Login failed Without Domain name");
			Assert.assertTrue(false);
		}
		
		
		else if (lp.ErrMassWithTechDomain().equals("Invalid login details.")) {
			
			lp.clearPasswordInputFields();
			lp.clearUsernameTextFilds();
			
			logger.warn("Login failed with Domain name");
			Assert.assertTrue(false);
		}
		}
			catch(org.openqa.selenium.NoSuchElementException e)
			{
				P2_Welcome wp = new P2_Welcome(driver);
				wp.imgBtnClose();
				String welcomeText = wp.getText();
				Assert.assertEquals(welcomeText,"Nirbhay Deep Trigunayat", "Login Pass");
				Assert.assertTrue(true);
				logger.info("Login passed");
			}
		
		}
		
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		ReadConfig rc = new ReadConfig();
		
		String ExcelPath =rc.getExcelPath();
		
		String path=System.getProperty("user.dir")+ExcelPath;
		/*
		 * int rownum=XLUtils.get_row_count(path, "Sheet1"); int
		 * colcount=XLUtils.get_cell_Count(path,"Sheet1",1);
		 */
		String logindata[][]=new String[8][2];
		
		for(int i=1;i<=8;i++)
		{
			for(int j=0;j<2;j++)
			{
				logindata[i-1][j]=XLUtils.get_cell_data(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}

}
