package AppiumMobileAppTests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class MobileAppBaseTest {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	
	/*alternate app invocation method
	Reference - https://support.testsigma.com/support/solutions/articles/32000019977-how-to-find-app-package-and-app-activity-of-your-android-app
	
	*/
	
	@BeforeClass
	public void configureAppiumForApp() throws MalformedURLException {		
		
		// start and stop Server through code (main.js) is the main file give that path
    service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Manoush\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
			.withIPAddress("127.0.0.1").usingPort(4723).build();
	service.start();
	
	//Android driver object
	 
	UiAutomator2Options automator = new UiAutomator2Options();
	automator.setDeviceName("AppiumUdemyTest"); //give the device name set in android studio
	automator.setChromedriverExecutable("C:\\Users\\Manoush\\Appium_Projects\\AppiumProject\\src\\test\\java\\utils\\chromedriver.exe");
	automator.setApp("C:\\Users\\Manoush\\Appium_Projects\\AppiumProject\\src\\test\\java\\utils\\General-Store.apk");
		
  
	
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
	

	 // >>>>>>>>>>>>>>>>>>>>>>>>> Below gestures are Used only for Native apps <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
		/* GESTURES
		 * Reference - https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
		 */
		
		
		public void longPressGesture(WebElement elementToLongPress) {
			((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
					ImmutableMap.of("elementId",((RemoteWebElement)elementToLongPress).getId(), "duration",2000));
		}
		
		public void scrollByKnownElement(String TextVal) {
			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"+TextVal+\"));"));

		}
		
		public void scrollLittle() {
			((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 1.0
				));
		}
		
		//scroll till end
		public void scrollTillScrollable() {
			boolean canScrollMore;
			do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
			}
			while (canScrollMore);
	//do while ensures it keeps scrolling till it reached end of scroll
			
		}
		
		//SwipeGesture
		public void swipeGesture(WebElement swipeElement, String direction) {
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					"elementId", ((RemoteWebElement)swipeElement).getId(),
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", direction,
				    "percent", 0.75
				));
		}
		
		
		public void dragAnddropAction(WebElement Source, int endX, int endY) throws InterruptedException {
			((JavascriptExecutor)driver).executeScript("mobile: dragGesture",
					ImmutableMap.of(
							"elementId", ((RemoteWebElement)Source).getId(),
							"endX", endX ,
							"endY", endY 
							));
			Thread.sleep(2000);
		}
}
