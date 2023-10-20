package Amazon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Amazon.test.Testcase1;

public class Amazin_Login {
	WebDriver d;
	@FindBy(id="ap_email")
     WebElement username;
	@FindBy(id="continue")
    WebElement Continue_button;
	@FindBy(id="ap_password")
    WebElement  password ;
	@FindBy(id="signInSubmit")
    WebElement  Signin ;
	Testcase1 a= new Testcase1();
	public void un()
	{
		username.sendKeys("9067259501");
	}
	public void Continue_button1()
	{
		Continue_button.click();
	}
	public void pwd()
	{
		password.sendKeys("Best@123");
	}
	public void Signin()
	{
		Signin.click();
		}
	public Amazin_Login(WebDriver a)//Constructor
	{
		PageFactory.initElements(a,this);
	}
	
	
}
