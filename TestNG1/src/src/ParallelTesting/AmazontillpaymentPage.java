package ParallelTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class AmazontillpaymentPage {
	
	@Test
	public void test() throws InterruptedException
	{
		ChromeDriver d=new ChromeDriver();
		d.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fcss%2Fhomepage.html%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		d.manage().window().maximize();
//		WebElement hoverover=d.findElement(By.xpath("//span[@class='nav-line-2 ']"));
//		WebElement signin=d.findElement(By.xpath("//span[.='Sign in']"));
//		signin.click();
		
		
//		Actions a1= new Actions(d);
//		a1.moveToElement(hoverover).perform();
		
		
		WebElement wb1=d.findElement(By.id("ap_email"));
		wb1.sendKeys("9067259501");
		
		WebElement Conitinue_on_click_button=d.findElement(By.id("continue"));
		Conitinue_on_click_button.click();
		WebElement pass=d.findElement(By.id("ap_password"));
		pass.sendKeys("Best@123");
		WebElement signin=d.findElement(By.id("signInSubmit"));
		signin.click();
		WebElement search=d.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("shoe");
		search.sendKeys(Keys.ENTER);
		WebElement searchlaptop=d.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-tall-aspect'])[1]"));
		searchlaptop.click();
		Thread.sleep(1000);
		WebElement addtocart=d.findElement(By.xpath("//span[@class='a-button a-spacing-small a-button-primary a-button-icon natc-enabled']"));
		addtocart.click();
		d.quit();
		
		
		
		
		
		
		
		
		
	}
	

}
