package ninja.techved.com_utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigExtendReports {
	
	 Properties pro;
		
		
		public ReadConfigExtendReports() {
			
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
		
					public String getHostName() {
			
					String HostName = pro.getProperty("Hostname");
			
					return HostName;
		}
					
			public String getOSName () {		
				String OSName = pro.getProperty("OS");
				return OSName;
				
			}
			
			public String getTesterName () {		
				String TesterName = pro.getProperty("Tester");
				return TesterName;
				
			}
					
			public String getBrowserName () {		
				String BrowserName = pro.getProperty("Browser");
				return BrowserName;
				
			}	
}
