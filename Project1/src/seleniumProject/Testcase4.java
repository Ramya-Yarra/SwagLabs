package seleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase4 {
	public WebDriver driver;
	 @BeforeTest
	    public void initializeDriver() {		 
	    	System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
	    	driver = new ChromeDriver();
	        driver.get("https://www.saucedemo.com/");
	        driver.manage().window().maximize();
	    }
	    @Test
	    public void login() {
	        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
	        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
	        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
	        driver.findElement(By.className("bm-item-list"));
	        WebElement aboutLink = driver.findElement(By.id("about_sidebar_link"));
	        aboutLink.click();
	        System.out.println("Page has been Navigated" +" "+ driver.getTitle());
	    }
	   
}
