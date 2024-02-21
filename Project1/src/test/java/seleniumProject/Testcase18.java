package seleniumProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase18 {
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
    }
    @Test
    public void product() {
    List<WebElement> inventoryItems = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));

    for (WebElement inventoryItem : inventoryItems) {

        WebElement productName = inventoryItem.findElement(By.cssSelector(".inventory_item_name"));
        System.out.println("Product Name: " + productName.getText());

        WebElement productDescription = inventoryItem.findElement(By.cssSelector(".inventory_item_desc"));
        System.out.println("Product Description: " + productDescription.getText());

        WebElement productPrice = inventoryItem.findElement(By.cssSelector(".inventory_item_price"));
        System.out.println("Product Price: " + productPrice.getText());

        WebElement addToCartButton = inventoryItem.findElement(By.cssSelector(".btn_inventory"));
        System.out.println("Add to Cart Button: " + addToCartButton.getText());

        System.out.println("---------------------------------------");
    }
    driver.quit();
    }
}
