package seleniumProject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase22 {
	public WebDriver driver;

    @BeforeTest
    public void initializeDriver() {		 
    	System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
    	driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void login() throws IOException {
    	driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Ramya");
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("yarra");
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("522317");
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
        
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

        for (WebElement cartItem : cartItems) {
            
            WebElement itemName = cartItem.findElement(By.cssSelector(".cart_item_label .inventory_item_name"));
            System.out.println("Item Name: " + itemName.getText());

            WebElement itemDescription = cartItem.findElement(By.cssSelector(".cart_item_label .inventory_item_desc"));
            System.out.println("Item Description: " + itemDescription.getText());

            WebElement itemPrice = cartItem.findElement(By.cssSelector(".item_pricebar .inventory_item_price"));
            System.out.println("Item Price: " + itemPrice.getText());

            System.out.println("---------------------------------------");
        }
        }
}
