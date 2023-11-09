package AppiumMobileBrowserTests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class MobileWebBaseTest {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	
	/*alternate app invocation method
	Reference - https://support.testsigma.com/support/solutions/articles/32000019977-how-to-find-app-package-and-app-activity-of-your-android-app
	
	*/
	
	@BeforeClass
	public void configureAppiumForWeb() throws MalformedURLException {	
		
		// start and stop Server through code (main.js) is the main file give that path
    service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Manoush\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
			.withIPAddress("127.0.0.1").usingPort(4723).build();
	service.start();
	
	//Android driver object
	 
	UiAutomator2Options automator = new UiAutomator2Options();
	automator.setDeviceName("AppiumUdemyTest"); //give the device name set in android studio
	automator.setChromedriverExecutable("C:\\Users\\Manoush\\Appium_Projects\\AppiumProject\\src\\test\\java\\utils\\chromedriver.exe");
	automator.setCapability("browserName", "Chrome");
	
  
	
	//need to send all information to Appium server first, hence provide the server url as first parameter
   // Capabilities (2nd parameter) will be the emulator name given in Studio
	
	 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), automator);
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
		
	}
	
	
}