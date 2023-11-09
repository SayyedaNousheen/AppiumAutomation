package AppiumGeneralStore_Hybrid;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumMobileAppTests.MobileAppBaseTest;
import io.appium.java_client.AppiumBy;

public class GS_FormValidation extends MobileAppBaseTest{
	
	@Test
	public void FillForm() throws InterruptedException {
		
		//Error Validation - Toasts are created using - android.widget.toast
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String ToastText = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(ToastText, "Please enter your name");
		
		//Happy Flow
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("AppiumUser");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(2000);
	}

}
