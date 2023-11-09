package AppiumGeneralStore_Hybrid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumMobileAppTests.MobileAppBaseTest;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class GS_ValidateCartItems extends MobileAppBaseTest {

	
	@Test
	public void validateCart() throws InterruptedException {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("AppiumUser");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(1000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		//Wait till cart is loaded as properties in both product and cart page is same
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		
		ArrayList<WebElement> cartProducts = new ArrayList<>(driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")));
		int cartCount = cartProducts.size();
		double value = 0;
		for(int i=0; i<cartCount; i++) {
			
			String ProductPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			double price = Double.valueOf(ProductPrice.substring(1));
			value = value + price;
			
		}
		System.out.println("Final Total Amount of 2 items" +value);
		
		Double TotalAmount = Double.valueOf(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(2));
		Assert.assertEquals(value, TotalAmount);
		
		//long press terms and condition
		longPressGesture(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
		driver.findElement(By.id("android:id/button1")).click();
		
		driver.findElement(By.className("android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click(); //lands to google page
		Thread.sleep(5000);
		//from app, navigation goes to web page, hence this is a hybrid app
		//switch to web view and also download chromedriver and set the driver path in baseTest for automator object
		
		Set<String> contexts = driver.getContextHandles();
		for(String contextName: contexts) {
			System.out.println(">>>>>>>>>>>>" +contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore"); //print the contexts once to get exact name to pass
		driver.findElement(By.name("q")).sendKeys("hello");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//to come back to app
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("AppiumUser");
	}
}

