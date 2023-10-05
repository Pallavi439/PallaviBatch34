package DDTExcelSheet;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Childclass {
	@Test
	public void test1() throws EncryptedDocumentException, IOException
	{
	ChromeDriver d=new ChromeDriver();
	CredentialFile c=new CredentialFile();
	String RF=c.test();
	String RF1=c.passtest();
	d.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fcss%2Fhomepage.html%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
	d.manage().window().maximize();
	
	WebElement wb1=d.findElement(By.id("ap_email"));
	wb1.sendKeys(RF);
	
	WebElement Conitinue_on_click_button=d.findElement(By.id("continue"));
	Conitinue_on_click_button.click();
	WebElement pass=d.findElement(By.id("ap_password"));
	pass.sendKeys(RF1);
	WebElement signin=d.findElement(By.id("signInSubmit"));
	signin.click();
	d.close();
//	

}
}
