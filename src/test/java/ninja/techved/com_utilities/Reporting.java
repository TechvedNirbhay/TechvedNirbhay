package ninja.techved.com_utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//public class Reporting implements ITestListener {
	
	public class Reporting extends TestListenerAdapter{
		
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest logger; 
		
		/*
		 * public void onTestStart(ITestResult result) {
		 * 
		 * }
		 */

	

	public void onTestFailure(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.FAIL,"Test case failed is " +/* MarkupHelper.createLabel(result.getName(),*/ ExtentColor.RED);
		logger.log(Status.FAIL,"Test case failed is "  + result.getThrowable());
		
		String screenshotPath = (System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".png");
		
		/*
		 * try { logger.addScreenCaptureFromPath(screenshotPath); } catch (IOException
		 * e1) {
		 * 
		 * e1.printStackTrace(); }
		 */
		
		File file = new File (screenshotPath);
		
		if (file.exists()) {
			
			try {
				
				logger.fail("Screeensshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
				
			}
			
			catch(IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
			
	}

	public void onTestSkipped(ITestResult result) {
		
		logger = extent.createTest(result.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(result.getTestName(), ExtentColor.ORANGE));
		
	}
	
	

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
	
		String timeStamp = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report"+timeStamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extend-config.xml");
		
		ReadConfigExtendReports RcEr = new ReadConfigExtendReports();
		String Hostname = RcEr.getHostName();
		String OSname = RcEr.getOSName();
		String Testername = RcEr.getTesterName();
		String Browsername = RcEr.getBrowserName();
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);	
		extent.setSystemInfo("Hostname",Hostname);
		extent.setSystemInfo("OS",OSname);
		extent.setSystemInfo("Tester Name",Testername);
		extent.setSystemInfo("Browser",Browsername);
		
		
		htmlReporter.config().setDocumentTitle("Automation Report");//  Title of  the Report
		htmlReporter.config().setReportName("Functional Report ");//  Name of the Report
		htmlReporter.config().setTheme(Theme.DARK);	
		
		
	}

	public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}
	
	 @AfterMethod
		public void onTestSuccess(ITestResult result) {
			logger = extent.createTest(result.getName());
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
	
	 }

}
