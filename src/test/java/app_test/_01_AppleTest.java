package app_test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class _01_AppleTest extends Base{
    @Test(testName = "Apple title validation", priority = 1)
    public void validateAppleTitle(){
        driver.get("https://www.apple.com/");
        Assert.assertEquals(driver.getTitle(), "Apple");
    }

    @Test(testName = "Apple heading validation", priority = 2)
    public void validateAppleMainHeading(){
        driver.get("https://www.apple.com/");
        Assert.assertTrue(appleHomePage.firstHeading.isDisplayed());
    }
}
