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

public class MobileGestures_DragAndDrop extends MobileAppBaseTest {
	
	@Test
	public void dragAndDrop() throws MalformedURLException, InterruptedException  {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		
		//To drag and drop, need to identify the element to be dragged (Source) and identify
		//coordinates of destination to drop
		WebElement Source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		
		dragAnddropAction(Source, 828, 740);
		
		String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		
		Assert.assertEquals(result, "Dropped!");
	}

}
