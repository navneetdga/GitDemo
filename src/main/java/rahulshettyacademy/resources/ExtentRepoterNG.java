package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRepoterNG {
	
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html"; 
		ExtentSparkReporter  reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("web Automation Result");
		reporter.config().setDocumentTitle("Test Result Navneet");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Navneet"); 
		return extent;
		
		
	}

}
 