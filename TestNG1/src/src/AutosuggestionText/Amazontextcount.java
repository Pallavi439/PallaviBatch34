package AutosuggestionText;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Amazontextcount {
	@Test
	public void test() throws InterruptedException
	{
		ChromeDriver d=new ChromeDriver();
		d.get("https://www.amazon.in/");
		//Thread.sleep(1000);
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().window().maximize();
		WebElement wb1=d.findElement(By.id("twotabsearchtextbox"));
		wb1.sendKeys("shoe");
		
		List<WebElement> list = d.findElements(By.xpath("//div[@class='two-pane-results-container']/div/div"));
		
		int count = list.size();
		System.out.println(count);
		for(int i=0;i<count;i++)
		{
			WebElement ii = list.get(i);
			String name = ii.getText();
			System.out.println(name);
		}
		
		
		
		
		
	}

	

}
