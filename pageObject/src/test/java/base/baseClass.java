package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.org.jline.utils.Log;

//import com.w2a.utilities.ExcelReader;

public class baseClass {
	//Screenshot
	//Log4J
//	properties
//	Keyword
//	database
//	javamail
//	extent
//	listeners
//	excel
	//waits
	
		public static WebDriver driver;
		public static Logger log = Logger.getLogger(baseClass.class.getName());
		public static Properties OR = new Properties();
		public static Properties Config = new Properties();
		public static FileInputStream fis;
//		public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\Excel\\dataExcel.xlsx");
		public static WebDriverWait wait;
		
		@BeforeSuite 
		public void setUp() {
			if(driver==null) {
				PropertyConfigurator.configure(".\\src\\test\\resources\\Properties\\log4j.properties");
				
				
				try {
				fis = new FileInputStream(".\\src\\test\\resources\\Properties\\OR.properties");
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					OR.load(fis);
					Log.info("OR properties loaded !!!!");
				}catch(IOException e) {
					e.printStackTrace();
				}
				
				try {
					fis = new FileInputStream(".\\src\\test\\resources\\Properties\\config.properties");
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						OR.load(fis);
						Log.info("Configxxz properties loaded !!!!");
					}catch(IOException e) {
						e.printStackTrace();
					}
				if(Config.getProperty("browser").equals("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					log.info("Chrome Brower Launched");
				}
				else if(Config.getProperty("browser").equals("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					log.info("FireFox Brower Launched");
				}
				
				driver.get(Config.getProperty("testingurl"));
				log.info("Navigating to URL : " + Config.getProperty("testingurl"));
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
				
				wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
				
				
			}
			
		}
		
		
		@AfterSuite
		public void tearDown() {
			
		}
}
