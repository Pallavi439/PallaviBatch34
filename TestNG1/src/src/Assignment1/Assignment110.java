package Assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
//How to do the double click on any element?
//Use Actions class and doubleClick () method
public class Assignment110 {
	@Test
	public void test()
	{
	ChromeDriver driver=new ChromeDriver();
	driver.get("https://www.google.com/");
	WebElement wb=driver.findElement(By.id(""));
	wb.sendKeys("India");
	
	
	Actions a1=new Actions(driver);
	a1.doubleClick(wb).perform();
	}
	

}
