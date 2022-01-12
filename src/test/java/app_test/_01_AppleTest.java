package app_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

public class _01_AppleTest extends Base{
    @Test(testName = "Apple title validation", priority = 1)
    public void validateAppleTitle(){
        driver.get("https://www.apple.com/");
        Assert.assertEquals(driver.getTitle(), "Apple");
    }

    @Test(testName = "Apple heading validation", priority = 2)
    public void validateAppleMainHeading(){
        driver.get("https://www.apple.com/");

        //TO-DO use explicit wait instead of pause
        Waiter.pause(3);
        Assert.assertTrue(appleHomePage.firstHeading.isDisplayed());
    }
}
