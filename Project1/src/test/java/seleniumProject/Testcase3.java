package seleniumProject;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testcase3 {
	public static void main(String[] args) {
     
        System.setProperty("webdriver.chrome.driver","D:\\Testing\\Selenium\\Jar files\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
          
            driver.get("https://www.saucedemo.com/");

            String excelFilePath = "D:\\Testing\\Selenium\\ReadingData.xlsx";
            String sheetName = "Sheet1";

            ExcelUtils.setExcelFile(excelFilePath, sheetName);

            for (int row = 1; row < ExcelUtils.getRowCount(); row++) {
              
                String username = ExcelUtils.getCellData(row, 0);
                String password = "secret_sauce"; 

                login(driver, username, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
            driver.quit();
        }
    }

    private static void login(WebDriver driver, String username, String password) {
        
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        
        if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            System.out.println("Login successful for username: " + username);

        } else {
            System.out.println("Login failed for username: " + username);
        }
        
        driver.get("https://www.saucedemo.com/");
    }

    static class ExcelUtils {
        private static Workbook workbook;
        private static Sheet sheet;

        public static void setExcelFile(String filePath, String sheetName) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fileInputStream);
            sheet = workbook.getSheet(sheetName);
        }

        public static String getCellData(int row, int col) {
            Cell cell = sheet.getRow(row).getCell(col);
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }

        public static int getRowCount() {
            return sheet.getPhysicalNumberOfRows();
        }
    }
}