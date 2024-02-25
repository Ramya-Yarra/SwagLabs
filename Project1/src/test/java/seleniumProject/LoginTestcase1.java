package seleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTestcase1 {
	 private WebDriver driver;

	    private By usernameField = By.id("user-name");
	    private By passwordField = By.id("password");
	    private By loginButton = By.id("login-button");

	    public LoginTestcase1(WebDriver driver2) {
	    	this.driver = driver;
		}

		public void enterUsername(String username) {
	        driver.findElement(usernameField).sendKeys("standard_user");
	    }

	    public void enterPassword(String password) {
	        driver.findElement(passwordField).sendKeys("secret_sauce");
	    }

	    public void clickLoginButton() {
	        driver.findElement(loginButton).click();
	    }
}
