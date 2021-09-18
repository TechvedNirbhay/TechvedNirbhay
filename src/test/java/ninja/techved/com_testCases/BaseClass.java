package ninja.techved.com_testCases;

	import java.io.File;
	import java.io.IOException;
	import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
	import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebDriver.Timeouts;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ninja.techved.com_utilities.ReadConfig;

//import io.github.bonigarcia.wdm.WebDriverManager;
	

	import org.apache.log4j.Logger;

	public class BaseClass {

		static ReadConfig readconfig = new ReadConfig();
		public String baseurl = readconfig.getURL();
		public String Username = readconfig.getusername();
		public String Password = readconfig.getpassword();
		
		
		public static WebDriver driver;
		public static Logger logger;
		
		@Parameters("Browser_name")

		@BeforeClass

		 //@Test

		public void setup(String br) throws InterruptedException {

			logger = Logger.getLogger("ninja.techved.com");

			PropertyConfigurator.configure("log4j.properties");

			if (br.equals("chrome")) {

				System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());

				//WebDriverManager.chromedriver().setup();

				System.out.println("Chrome path has been set properly");
				logger.info("Chrome path has been set properly");

				Thread.sleep(1000);

				driver = new ChromeDriver();

				Thread.sleep(1000);

				driver.manage().window().maximize();

				System.out.println("Chrome browser has open");
				logger.info("Chrome browser has open");

			}

			else if (br.equals("firefox")) {

				//WebDriverManager.firefoxdriver().setup();

				System.out.println("firefox path has been set properly");
				logger.info("firefox path has been set properly");
				Thread.sleep(1000);

				driver = new FirefoxDriver();

				Thread.sleep(1000);

				driver.manage().window().maximize();

				System.out.println("Firefox browser has open");
				logger.info("Firefox browser has open");

			}

			else if (br.equals("IE")) {

				//WebDriverManager.iedriver().setup();

				System.out.println("IC path has been set properly");
				logger.info("IC path has been set properly");
				Thread.sleep(1000);
				driver = new InternetExplorerDriver();

				Thread.sleep(1000);
				driver.manage().window().maximize();
				System.out.println("Internet ExplorerDriver browser has open");
				logger.info("Internet ExplorerDriver browser has open");

			}

			else {

				System.out.println("Browser does not exist");
				logger.info("Browser does not exist");

			}

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get(baseurl);

		}

		public static void captureScreen( String tname) throws IOException {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");

			FileUtils.copyFile(source, target);
			System.out.println("Screenshot taken");
			logger.info("Screenshot taken");

		}
		
		public static Timeouts timehandle() {
			
			Timeouts waitingtime = driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			return waitingtime;
		}
		
		public static void sendKeys(/* WebDriver driver, */ WebElement element, int timeout, String  value) {
		
			new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
			
			element.sendKeys(value);
		
			
		}
		
		public static void selectFrom(WebElement element, int timeout,Select select,String  value) {
			
			new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
			
			/*Select select_Account = new Select (driver.findElement(By.id("FirmId")));
			Thread.sleep(2000);
			select_Account.selectByVisibleText("NEC");
			*/
			
			select.selectByVisibleText(value);		
					
		}
		
		public static void clickOn(/* WebDriver driver, */ WebElement element, int timeout) {
		
			new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
			
			element.click();
			
		}
		
		public static void doubleClickOn(WebElement element, int timeout) {
			
			new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
			Actions act = new Actions(driver);
			act.moveToElement(element).
		      doubleClick().
		      build().perform();
			
		}
		
		public static void clearTextFields(/* WebDriver driver, */ WebElement element, int timeout) {
			
			new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
			
			element.clear();
			
		}
		public static String getTextFromPage(WebElement element, int timeout/* , String values */) {
			
			new WebDriverWait(driver,timeout);/*.until(ExpectedConditions.textToBePresentInElement(element,values));*/
			
			 String WebPagesText = element.getText();
			 
			 return WebPagesText;
		}
		
	}



