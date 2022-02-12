package app_test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utilities.ConfigReader;
import utilities.Driver;

public class Base {
    /*
    This class is designed to be base class of all test classes, and
    It has driver instance to be used by all child test classes
    Also, it has before and after setup and teardown tasks using Driver Utils
     */

    public WebDriver driver;
    public int explicitWait;

    @BeforeTest
    public void setGlobalVariables(){
        explicitWait = Integer.parseInt(ConfigReader.getProperty("explicitTimeOut"));
    }

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
