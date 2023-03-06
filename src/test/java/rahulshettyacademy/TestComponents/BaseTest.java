package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage; //
	public WebDriver initializeDriver() throws IOException
	{
		
		//properties class 
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\91814\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis); /// System.getProperty("project_path");
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		// prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			
		}
		else if (browserName.equalsIgnoreCase("firefox"))	
		{
		}
		else if (browserName.equalsIgnoreCase("edge"))	
		{
		
		//edge
		}
						else
						{
			//safari
						}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();	
		return driver;
		
		
		
	//	System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"
		
	}
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
 	
		ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}

	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot  ts = (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(60000);
		driver.close();
	}	
}