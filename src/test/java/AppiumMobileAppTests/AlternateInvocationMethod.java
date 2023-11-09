package AppiumMobileAppTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AlternateInvocationMethod extends MobileAppBaseTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void alternateInvocation() throws MalformedURLException  {
		
		//invoking app with package name and activity
		
		//cmd -> adb shell dumpsys window | find "mCurrentFocus" (this command is to get package details)
	//Package name - io.appium.android.apis
	//Activity Name - io.appium.android.apis.preference.PreferenceDependencies
	
	Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
	driver.startActivity(activity);
		
		
		driver.findElement(By.id("android:id/checkbox")).click();
		
		//xpath syntax => //tagname[@attribute='value']
		// xpath => //(tagname)[index]
		driver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
		//verify the popup text is WifiSettings
		String popupTitle = driver.findElement(By.className("android.widget.TextView")).getText();
		Assert.assertEquals(popupTitle, "WiFi settings");
		
		driver.findElement(By.id("android:id/edit")).sendKeys("AppiumTest");
		//alternate to click ok button => driver.findElement(AppiumBy.className("tagname")).get(index).click();
		driver.findElement(By.id("android:id/button1")).click();
		
	}

}
