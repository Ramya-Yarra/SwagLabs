package seleniumProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase29 {
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
	        driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer"));
	        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul"));
	        for (WebElement sociallist : list) {
	            System.out.println(sociallist.getText());
	        }        
	        WebElement footerCopy = driver.findElement(By.cssSelector("footer.footer div.footer_copy"));
	        System.out.println("Footer text: " + footerCopy.getText());
	    }
	    @AfterTest
	    public void close() {
	    	driver.close();
	    }
}
