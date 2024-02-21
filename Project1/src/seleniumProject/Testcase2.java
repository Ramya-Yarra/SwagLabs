package seleniumProject;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Testcase2 {
	public WebDriver driver;

	public static void main(String[] args) throws IOException, Exception {
		System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        String excelFilePath = "D:\\Testing\\Selenium\\ReadingData.xlsx";
        String sheetName = "Sheet3";
        Object[][] testData = readExcelData(excelFilePath, sheetName);

        for (Object[] data : testData) {
            String username = (String) data[0];
            String password = (String) data[1];

            WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
            WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
            usernameInput.sendKeys(username);
            passwordInput.sendKeys(password);
            

            loginButton.click();
            String errorMessage = driver.findElement(By.cssSelector("div.error-message-container.error h3")).getText();
            Assert.assertEquals(errorMessage.trim(), "Epic sadface: Username and password do not match any user in this service");
            System.out.println("" + errorMessage);

        }
    }

    private static Object[][] readExcelData(String excelFilePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                data[i - 1][j] = formatter.formatCellValue(cell);
            }
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }
}
