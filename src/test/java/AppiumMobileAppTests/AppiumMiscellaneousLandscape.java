package AppiumMobileAppTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.net.MalformedURLException;


public class AppiumMiscellaneousLandscape extends MobileAppBaseTest {
	
	@Test
	public void landscapeViewTest() throws MalformedURLException, InterruptedException  {
		
		//Locators - xpath, id, classname, accessibilityId, androidUIautomator (Tool to identify - Appium Inspector)
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		Thread.sleep(1000);
		
		
		/*Landscape rotation test
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);
		Thread.sleep(1000); */
		
		
		//xpath syntax => //tagname[@attribute='value']
		// xpath => //(tagname)[index]
		driver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
		
		//verify the popup text is WifiSettings
		String popupTitle = driver.findElement(By.className("android.widget.TextView")).getText();
		Assert.assertEquals(popupTitle, "WiFi settings");
		
		//Copy (setClipboardText()) and paste from clipboard (getClipboardText())
		driver.setClipboardText("AppiumTest");
		
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		
		//Keyboard enter to move to next line
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		Thread.sleep(2000);
		//alternate to click ok button => driver.findElement(AppiumBy.className("tagname")).get(index).click();
		driver.findElement(By.id("android:id/button1")).click();
		
		//Press back button, home button, keyboard enter
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		
		
		
	}

}
