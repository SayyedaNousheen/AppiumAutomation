package AppiumGeneralStore_Hybrid;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumMobileAppTests.MobileAppBaseTest;
import io.appium.java_client.AppiumBy;

public class GS_AddProductToCart extends MobileAppBaseTest {

	
	@Test
	public void addToCart() throws InterruptedException {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("AppiumUser");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(1000);
		
		
		//scroll to desired product 1
		
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Air Jordan 9 Retro\"));"));
		
		int sizeList = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i< sizeList; i++ ) {
			String ProductName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(ProductName.contentEquals("Air Jordan 9 Retro")) {
		      WebElement RequiredAddToCartLink = driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(i);
		      if(RequiredAddToCartLink.isDisplayed()) {
		    	  RequiredAddToCartLink.click();
		      }
		      else {
		    	  scrollLittle();
		    	  RequiredAddToCartLink.click();
		      }
			}
		}
		Thread.sleep(1000);
	
				
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		//Wait till cart is loaded as properties in both product and cart page is same
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		
		String cartProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(cartProduct, "Air Jordan 9 Retro");
	}
}

