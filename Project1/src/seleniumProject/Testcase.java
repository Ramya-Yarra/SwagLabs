package seleniumProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testcase {
	public WebDriver driver;

    @Test
    public void PageTitle() {		 
    	System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
    	driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        String SwagTitleAct = driver.getTitle();
		String SwagTitleExp = "Swag Labs";
		Assert.assertEquals(SwagTitleAct, SwagTitleExp);
		System.out.println("" + driver.getTitle());
    }
}
