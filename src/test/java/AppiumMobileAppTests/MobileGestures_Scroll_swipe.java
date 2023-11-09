package AppiumMobileAppTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileGestures_Scroll_swipe extends MobileAppBaseTest {
	
	@Test	
	public void swipeGesture()  {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click(); 
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		
		//the focus of 1st image will be true, as we swipe the focus changes to next element 
		//Scenario: verify if 1st element focus is true and swipe, then check focus of first element should be false
		WebElement swipeElement = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		String ImageFocus = driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable");
		Assert.assertEquals(ImageFocus,"true");
		//Swipe
		
		swipeGesture(swipeElement,"left");
		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");

	}
	
	
	public void scrollGesture()  {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//For scroll use androiduiautomator locator
		scrollTillScrollable();
		
	}
}
