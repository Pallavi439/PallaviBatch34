package Assignment1;

import org.testng.annotations.Test;

//Program for passing enabled parameter @Test

public class Assignment92 {
	
	@Test(enabled=false)
	public void Atest()
	{
	System.out.print("test1");	
	}
	@Test
	public void test1()
	{
	System.out.print("test2");	
	}
	@Test
	public void test2()
	{
	System.out.print("test3");	
	}
	

}
