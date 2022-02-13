package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class cleanAddress {

    private static final int TIMEOUT_VALUE = 1;
    private static final TimeUnit TIMEOUT_UNITS = TimeUnit.SECONDS;


    public static void main(String[] args){
        testEngAddress();
        testShortAddress();
        testAddressWithoutRoom();
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

    private static void testAddressWithoutRoom (){
        String address = "Санкт Питербург Заводская 1-я 20";
        WebDriver driver = createAndSetupWebDriver();

        driver.get("https://dadata.ru/clean/");
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).clear();
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).sendKeys(address);
        driver.findElement(By.xpath("//section[@class='api-data-input']/button")).click();

        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'не требует ручной проверки')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'рассылка под вопросом: дома нет в ФИАС')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Россия')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'198205')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Северо-Западный')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Санкт-Петербург')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Красносельский')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Старо-Паново')]]");
        isElementPresent(driver, "//section[@class='api-data-result']/div/div/div[text()[contains(.,'1-я Заводская')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'дом:')]]/..//div[text()[contains(.,'20')]]");

        driver.quit();
    }


    private static void testShortAddress (){
        String address = "Мск. ул. Тверская д.19а кв.15";
        WebDriver driver = createAndSetupWebDriver();

        driver.get("https://dadata.ru/clean/");
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).clear();
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).sendKeys(address);
        driver.findElement(By.xpath("//section[@class='api-data-input']/button")).click();

        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'не требует ручной проверки')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'подходит для рассылки')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Россия')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'125375')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Центральный')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Москва')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Тверской')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Тверская')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'19А')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'15')]]");

        driver.quit();
    }

    private static void testEngAddress (){
        String address = "2-Y Donskoy Proyezd, 4 building 4, flat 64 Moskva";
        WebDriver driver = createAndSetupWebDriver();

        driver.get("https://dadata.ru/clean/");
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).clear();
        driver.findElement(By.xpath("//input[@class='api-data-input__field']")).sendKeys(address);
        driver.findElement(By.xpath("//section[@class='api-data-input']/button")).click();

        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'не требует ручной проверки')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//span[text()[contains(.,'подходит для рассылки')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Россия')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'119071')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Центральный')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'Москва')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//p[text()[contains(.,'Район:')]]/..//div[text()[contains(.,'Донской')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'2-й Донской')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,' 4')]]");
        isElementPresent(driver, "//section[@class='api-data-result']//div[text()[contains(.,'64')]]");

        driver.quit();
    }
}
