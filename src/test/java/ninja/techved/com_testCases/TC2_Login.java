package ninja.techved.com_testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ninja.techved.com_pageObject.P1_Login;
import ninja.techved.com_utilities.ReadConfig;
import ninja.techved.com_utilities.XLUtils;

public class TC2_Login extends BaseClass {
	
	
	@Test(dataProvider="LoginData")
	public void TC2_Login(String user,String pwd) throws InterruptedException
	{
		P1_Login lp=new P1_Login(driver);
		lp.setTextInUnameField(user);
		logger.info("user name provided");
		lp.setTextInUpassField(pwd);
		logger.info("password provided");
		lp.selectRadioButtonEmp();
		logger.info("Select Emp Radio button successfully");
		lp.clickOnSubmitButton();
		logger.info("Click on submit button successfully");
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
		
		}
		
	}
	
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		ReadConfig rc = new ReadConfig();
		
		String ExcelPath =rc.getExcelPath();
		
		String path=System.getProperty("user.dir")+ExcelPath;
		
		int rownum=XLUtils.get_row_count(path, "Sheet1");
		int colcount=XLUtils.get_cell_Count(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.get_cell_data(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}

}
