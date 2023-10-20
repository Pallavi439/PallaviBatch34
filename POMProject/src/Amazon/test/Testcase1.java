package Amazon.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Amazon.source.Amazin_Login;


public class Testcase1 extends Lunchandquit{
	WebDriver d;
	
	@Test
	public void Amazontest()
	{
		 d=new ChromeDriver();
		d.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fcss%2Fhomepage.html%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		d.manage().window().maximize();
		Amazin_Login a1=new Amazin_Login(d);
		a1.un();
		a1.Continue_button1();
		a1.pwd();
		a1.Signin();
		
		

		
		
		
	
	}
	

}
