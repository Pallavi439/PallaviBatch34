package TestNgListner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(RepoterListner.class)
public class Sample1 {
	ChromeDriver driver;
	
	@Test
	public void test()
	{
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		WebElement wb=driver.findElement(By.id("APjFqb"));
		wb.sendKeys("India");
		wb.sendKeys(Keys.ENTER);
		
		
		
	}
	

}
