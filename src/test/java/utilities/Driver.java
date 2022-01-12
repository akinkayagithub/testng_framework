package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    //private constructor to achieve singleton design pattern
    private Driver(){

    }

    //private WebDriver instance variable to restrict direct access to driver instance
    private static WebDriver driver;


    //getter method to access single driver instance
    public static WebDriver getDriver(){
        if(driver == null){
            if(Boolean.parseBoolean(ConfigReader.getProperty("headless"))){
                driver = new HtmlUnitDriver();
            }
            else{
                String browser = ConfigReader.getProperty("browser");
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "safari":
                        WebDriverManager.getInstance(SafariDriver.class).setup();
                        driver = new SafariDriver();
                        break;
                    default:
                        throw new NotFoundException("Specified browser is not set properly!");
                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.getProperty("implicitTimeOut")), TimeUnit.SECONDS);
            }
        }
        return driver;
    }

    public static void quitDriver(){
        if(driver != null){
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }
}
