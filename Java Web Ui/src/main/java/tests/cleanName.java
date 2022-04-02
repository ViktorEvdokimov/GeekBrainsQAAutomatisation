package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class cleanName {

    private static final int TIMEOUT_VALUE = 1;
    private static final TimeUnit TIMEOUT_UNITS = TimeUnit.SECONDS;


    public static void main(String[] args){
        testLowerCase();
    }

    private static boolean isElementPresent (WebDriver driver, String xpath){
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        if (elements.isEmpty()) {
            driver.quit();
            throw new RuntimeException("Тест упал, не найден путь: " + xpath);
        }
        return elements.size() > 0;
    }

    private static WebDriver createAndSetupWebDriver (){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(TIMEOUT_VALUE, TIMEOUT_UNITS);

        return driver;
    }

    private static void testLowerCase (){
        String address = "кузнецов пётр сергеевич";
        WebDriver driver = createAndSetupWebDriver();

        driver.get("https://dadata.ru/clean/");
        driver.findElement(By.xpath("//div[@class='api-tabs']/span[text()[contains(.,'ФИО')]]")).click();
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).clear();
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).sendKeys(address);
        driver.findElement(By.xpath("//section[@class='api-data-input']/button")).click();

        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'не требует ручной проверки')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Фамилия')]]/../div[text()[contains(.,'Кузнецов')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Отчество')]]/../div[text()[contains(.,'Сергеевич')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Пол')]]/../div[text()[contains(.,'мужской')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Им. падеж (кто?):')]]/../div[text()[contains(.,'Кузнецов Пётр Сергеевич')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Род. падеж (кого?):')]]/../div[text()[contains(.,'Кузнецова Петра Сергеевича')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Дат. падеж (кому?):')]]/../div[text()[contains(.,'Кузнецову Петру Сергеевичу')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Твор. падеж (кем?):')]]/../div[text()[contains(.,'Кузнецовым Петром Сергеевичем')]]");

        driver.quit();
    }
}
