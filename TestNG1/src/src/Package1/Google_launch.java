package Package1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Google_launch {
	@BeforeMethod
	public void Test()
	{
		ChromeDriver driver =new ChromeDriver();
		driver.get("https//www.google.com");
		WebElement e1=driver.findElement(By.id("q"));
		e1.sendKeys("India");
		e1.sendKeys(Keys.ENTER);
				
		
	}
	
	@AfterMethod
	public void Af()
	{
		
	}
	
	

}
