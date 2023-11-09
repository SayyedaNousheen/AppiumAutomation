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

public class MobileGestures_LongPress extends MobileAppBaseTest {
	
	@Test
	public void longPress() throws MalformedURLException  {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		
		//Long Press Gesture
		//Javascript executor should be used to perfoem gestures
		WebElement elementToLongPress= driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPressGesture(elementToLongPress);
		
		Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']")).isDisplayed());
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']")).click();
		
	}

}
