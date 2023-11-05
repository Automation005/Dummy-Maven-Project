package com.tutorialninjademo_Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register_Test {
public WebDriver driver;
	
	@BeforeMethod
	public void registerSetup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority=1)
	public void verifyingRegisterWithMandatoryFields() {
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'firstname']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'lastname']")).sendKeys("Panda");
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'email']")).sendKeys(Util.emailWithDateTimeStamp());
		driver.findElement(By.xpath("//aside[@id = 'column-right']/preceding::input[@id = 'input-telephone']")).sendKeys("9876543210");
		driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'password']")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'confirm']")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//div[@class = 'pull-right']/child::a[1]/following-sibling::input[@name = 'agree']")).click();
		driver.findElement(By.xpath("//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
			
	}
	
	@Test(priority=2)
	public void verifyingRegisterWithAllFields() throws Exception {
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'firstname']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'lastname']")).sendKeys("Panda");
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'email']")).sendKeys(Util.emailWithDateTimeStamp());
		driver.findElement(By.xpath("//aside[@id = 'column-right']/preceding::input[@id = 'input-telephone']")).sendKeys("9876543210");
		driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'password']")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'confirm']")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//aside[@id = 'column-right']/preceding::input[@name = 'newsletter' and @value = '1']")).click();
		driver.findElement(By.xpath("//div[@class = 'pull-right']/child::a[1]/following-sibling::input[@name = 'agree']")).click();
		driver.findElement(By.xpath("//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
			
	}
	
	@Test(priority=3)
	public void verifyingRegisterWithExistingEmail() {
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'firstname']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'lastname']")).sendKeys("Panda");
		driver.findElement(By.xpath("//fieldset[@id = 'account']/descendant::input[@name = 'email']")).sendKeys("seleniumpanda@gmail.com");
		driver.findElement(By.xpath("//aside[@id = 'column-right']/preceding::input[@id = 'input-telephone']")).sendKeys("9876543210");
		driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'password']")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'telephone']/following::input[@name = 'confirm']")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//aside[@id = 'column-right']/preceding::input[@name = 'newsletter' and @value = '1']")).click();
		driver.findElement(By.xpath("//div[@class = 'pull-right']/child::a[1]/following-sibling::input[@name = 'agree']")).click();
		driver.findElement(By.xpath("//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")).click();
		
		String actualDuplicateWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedDuplicateWarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertTrue(actualDuplicateWarningMessage.contains(expectedDuplicateWarningMessage));
			
	}
	
	@Test(priority=4)
	public void verifyingRegisterWithNoFields() {
		driver.findElement(By.xpath("//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")).click();
		
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = "Warning: You must agree to the Privacy Policy!";
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-firstname']/following-sibling::div[1]")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-lastname']/following-sibling::div[1]")).getText();
		String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-email']/following-sibling::div[1]")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-telephone']/following-sibling::div[1]")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-password']/following-sibling::div[1]")).getText();
		String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
		
			
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
