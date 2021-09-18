package ninja.techved.com_utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	 Properties pro;
		
		
		public ReadConfig() {
			
			File src = new File("./Configuration/Config.properties");
			
			try {
				
				FileInputStream fis =new FileInputStream(src);
				pro = new Properties();
				pro.load(fis);
				
				//System.out.println("Properties File has loaded");
				
			}catch (Exception e) {
				System.out.println("Exception is"+e.getMessage());
			
			}
		}
		
		
		public String getChromePath() {
			
			String path = pro.getProperty("chromepath");
			
			return path;
			
		}
		
		
		public String getURL() {
			
			String BaseURL = pro.getProperty("BaseURL");
			
			return BaseURL;
			
		}
		
		public String getusername() {
			
			String Uname = pro.getProperty("username");
			
			return Uname;
		
		}
		
		public String getpassword() {
			
			String Upass = pro.getProperty("password");
			
			return Upass; 
			
			
		}
		
		
		
		public String getExcelPath() {
			String ExcelPath = pro.getProperty("ExcelPath");
			return ExcelPath;
			
		}
		

}
