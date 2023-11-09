package AppiumMobileBrowserTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileWebPageTest extends MobileWebBaseTest {
	
	// CODE NOT WORKING - need to use different android version with device (android 12 with pixel 6)
	
	@Test
	public void mobileWebInvocation() {
		
		/* driver.get("https://www.google.com");
		driver.getTitle();
		driver.findElement(By.name("q")).sendKeys("rahul shetty");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER); */
		
		//Automate practice webpage
		driver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		driver.getTitle();
		driver.findElement(By.className("navbar-toggler-icon")).click();
		driver.findElement(By.xpath("//a[text()='Product']")).click();
		((JavascriptExecutor)driver).executeScript("windows.scrollBy(0,1000)", "");
		String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
		Assert.assertEquals(text, "Devops");
		
	}

}
