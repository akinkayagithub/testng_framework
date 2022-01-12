package app_test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AppleHomePage;
import utilities.Driver;

public class Base {
    /*
    This class is designed to be base class of all test classes, and
    It has driver instance to be used by all child test classes
    Also, it has before and after setup and teardown tasks using Driver Utils
     */

    public WebDriver driver;
    public AppleHomePage appleHomePage;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
        appleHomePage = new AppleHomePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
