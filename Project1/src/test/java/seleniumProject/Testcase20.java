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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase20 {
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
        
        String excelFilePath = "D:\\Testing\\Selenium\\ReadingData.xlsx";
        String sheetName = "Sheet4";
        Object[][] testData = readExcelData(excelFilePath, sheetName);

        for (Object[] data : testData) {
            String FirstName = (String) data[0];
            String LastName = (String) data[1];
            String Code = (String) data[2];

            WebElement FirstNameInput = driver.findElement(By.xpath("//*[@id=\"first-name\"]"));
            WebElement LastNameInput = driver.findElement(By.xpath("//*[@id=\"last-name\"]"));
            WebElement PostalCode = driver.findElement(By.xpath("//*[@id=\"postal-code\"]"));
            FirstNameInput.sendKeys(FirstName);
            LastNameInput.sendKeys(LastName);
            PostalCode.sendKeys(Code);
            driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
            String OverviewTitle = driver.findElement(By.className("title")).getText();
            Assert.assertEquals(OverviewTitle, "Checkout: Overview");
            System.out.println("" + OverviewTitle);

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
