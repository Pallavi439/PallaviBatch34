package ParallelTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Google {
	WebDriver driver;
	@Test
	@Parameters("browser")
	public void test(String choosingbrowser)
	{
		if(choosingbrowser.equals("chrome"))
		{
			 driver =new ChromeDriver();
		}
		if(choosingbrowser.equals("firefox"))
		{
			 driver =new FirefoxDriver();	
		}
		if(choosingbrowser.equals("edge"))
		{
			 driver =new EdgeDriver();	
		}
	
	driver.get("https://www.google.com/");
	WebElement e1=driver.findElement(By.id("APjFqb"));
	e1.sendKeys("India");
	e1.sendKeys(Keys.ENTER);
			

}
}
