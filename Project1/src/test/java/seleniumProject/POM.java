package seleniumProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class POM {
	public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginTestcase1 loginPage = new LoginTestcase1(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        String SwagTitleAct = driver.getTitle();
		String SwagTitleExp = "Swag Labs";
		Assert.assertEquals(SwagTitleAct, SwagTitleExp);
		System.out.println("Page title after login: " + driver.getTitle());
		System.out.println("Logged In successfully");
		driver.close();	
    }
}