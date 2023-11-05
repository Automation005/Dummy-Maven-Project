package com.tutorialninjademo_Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddTocartTest {
	public WebDriver driver;
	public ChromeOptions options;

	@BeforeMethod
	public void Setup() {
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();

	}

	@Test
	public void checkigOutValidProduct() throws InterruptedException {
		driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		driver.findElement(By.xpath("//div[@class = 'caption']/following-sibling::div/child::button[1]")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), 'Product Code:Product 21')]")).isDisplayed());
		driver.findElement(By.xpath("//button[@id = 'button-cart']")).click();
		String expectedMessage = "Success: You have added HP LP3065 to your shopping cart!";
		Thread.sleep(3000);
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		driver.findElement(By.xpath("//div[@id = 'cart']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Checkout")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[normalize-space()='I want to use a new address']")).click();
		driver.findElement(By.cssSelector("input#input-payment-firstname")).sendKeys("eric");
		driver.findElement(By.cssSelector("input#input-payment-lastname")).sendKeys("martinez");
		driver.findElement(By.cssSelector("input#input-payment-company")).sendKeys("pnt");
		driver.findElement(By.cssSelector("input#input-payment-address-1")).sendKeys("1237 monument blvrd");
		driver.findElement(By.cssSelector("input#input-payment-city")).sendKeys("martinez");
		driver.findElement(By.cssSelector("input#input-payment-postcode")).sendKeys("94553");
		
		Thread.sleep(2000);
		
		Select select = new Select (driver.findElement(By.cssSelector("select#input-payment-country")));
		select.selectByVisibleText("United States");
		
		Thread.sleep(2000);
		
		Select select1 = new Select (driver.findElement(By.cssSelector("select#input-payment-zone")));
		select1.selectByVisibleText("California");
		
		driver.findElement(By.cssSelector("input#button-payment-address")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input#button-shipping-address")).click();
		
		Thread.sleep(2000); 
		driver.findElement(By.cssSelector("textarea.form-control")).sendKeys("Leave it at  my door");
		driver.findElement(By.cssSelector("input#button-shipping-method")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[id='collapse-payment-method'] textarea[name='comment']")).sendKeys("I Don't have any cash");
		driver.findElement(By.cssSelector("input[type=checkbox]")).click();
		driver.findElement(By.cssSelector("input#button-payment-method")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Confirm Order']")).click();
		
		String expectedconfirmMessage = "Your order has been successfully processed!";
		Thread.sleep(3000);
		String actualconfirmessage = driver.findElement(By.xpath("//p[normalize-space()='Your order has been successfully processed!']")).getText();
		Assert.assertTrue(actualconfirmessage.contains(expectedconfirmMessage));
		
		driver.findElement(By.cssSelector("a.btn.btn-primary")).click();
		
		

	}

	@AfterMethod
	public void teardown() {

	}
}
