package Assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//Google Launch Program with @Test annotation
public class assignment94 {
	
	
	@Test()
	public void test()
	{
		 ChromeDriver d=new ChromeDriver();
		d.get("https://www.google.com/");
		d.manage().window().maximize();
		WebElement wb=d.findElement(By.id("APjFqb"));
		wb.sendKeys("India");
		wb.sendKeys(Keys.ENTER);

}
}