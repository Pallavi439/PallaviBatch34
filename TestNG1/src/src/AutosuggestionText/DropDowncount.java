package AutosuggestionText;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DropDowncount {
	@Test
	public void test()
	{
		ChromeDriver d=new ChromeDriver();
		d.get("https://www.amazon.in/");
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().window().maximize();
		WebElement wb=d.findElement(By.xpath("//div[@class='nav-search-scope nav-sprite']"));
		wb.click();
		List<WebElement> drop=d.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
		 int count =drop.size();
		 System.out.println(count);
		 for(int i=0;i<count;i++)
		 {
			 WebElement aa= drop.get(i);
			 String wb2=aa.getText();
			 System.out.println(wb2);
		 }
		
		
		
		
		
		
		
	}
	

}
