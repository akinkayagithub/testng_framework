package app_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

import java.util.List;

// TO DO -> Use Page Object Model to centralize all web elements in page class
public class CarvanaTest extends Base{
    /**
     TASK-1
     Given user is on "https://www.carvana.com/"
     Then validate title equals to "Carvana | Buy & Finance Used Cars Online | At Home Delivery"
     And validate url equals to "https://www.carvana.com/"
     */

    @Test(priority = 1)
    public void validateTitleAndUrl(){
        driver.get("https://www.carvana.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/");
        Assert.assertEquals(driver.getTitle(), "Carvana | Buy & Finance Used Cars Online | At Home Delivery");
    }


    /**
     TASK-2
     Given user is on "https://www.carvana.com/"
     Then validate the logo is displayed
     NOTE: Logo is displayed on the top-left corner of the home page
     */

    @Test (priority = 2)
    public void validateCarvanaLogo(){
        driver.get("https://www.carvana.com/");
        WebElement logo = driver.findElement(By.xpath("//div[@data-qa='logo-wrapper']"));
        Assert.assertTrue(logo.isDisplayed());
    }


    /**
     TASK-3
     Given user is on "https://www.carvana.com/"
     Then user should see navigation sections
     |HOW IT WORKS     |
     |ABOUT CARVANA    |
     |SUPPORT & CONTACT|
     */

    @Test (priority = 3)
    public void validateNavigationHeaderSections(){
        driver.get("https://www.carvana.com/");
        List<WebElement> navigationHeaderSections = driver.findElements(By.xpath("//div[@data-qa='navigation-wrapper']/div/a"));
        String[] expectedSectionTexts = {"HOW IT WORKS", "ABOUT CARVANA", "SUPPORT & CONTACT"};

        for (int i = 0; i < navigationHeaderSections.size(); i++) {
            Assert.assertEquals(navigationHeaderSections.get(i).getText(), expectedSectionTexts[i]);
        }
    }


    /**
     TASK-4
     Given user is on "https://www.carvana.com/"
     When user clicks on "SIGN IN" button
     Then user should be routed to "Sign in" modal
     When user enters email as "johndoe@gmail.com"
     And user enters password as "abcd1234"
     And user clicks on "SIGN IN" button
     Then user should see error message as "Email address and/or password combination is incorrect
     Please try again or reset your password."
     */

    @Test (priority = 4)
    public void validateInvalidCredentialsErrorMessage(){
        driver.get("https://www.carvana.com/");
        driver.findElement(By.xpath("//a[@data-cv-test='headerSignInLink']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-cv-test='Header.Modal']")).isDisplayed());
        driver.findElement(By.id("usernameField")).sendKeys("johndoe@gmail.com");
        driver.findElement(By.id("passwordField")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//button[@data-cv='sign-in-submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@data-qa='error-message-container']")).getText(), "Email address and/or password combination is incorrect"
                + "\nPlease try again or reset your password.");
    }


    /**
     TASK-5
     Given user is on "https://www.carvana.com/"
     When user clicks on "SEARCH ALL VEHICLES" link
     Then user should be routed to "https://www.carvana.com/cars"
     And user should see a search input box
     And user should see filter options
     |PAYMENT & PRICE|
     |MAKE & MODEL   |
     |BODY TYPE      |
     |YEAR & MILEAGE |
     |FEATURES       |
     |MORE FILTERS   |
     When user enters "Tesla" to the search input box
     Then user should see "GO" button in the search input box
     */

    @Test (priority = 5)
    public void validateSearchHeader(){
        driver.get("https://www.carvana.com/");
        driver.findElement(By.xpath("//a[@data-cv-test='Taxseason.SearchLink']")).click();
        String expectedUrl = "https://www.carvana.com/cars";

        Waiter.waitForURLToBe(driver, explicitWait, expectedUrl);
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        WebElement searchInputBox = driver.findElement(By.xpath("//input[@data-qa='search-bar-input']"));
        Assert.assertTrue(searchInputBox.isDisplayed());

        List<WebElement> searchFilterOptions = driver.findElements(By.xpath("//div[@data-qa='menu-flex']/button"));
        String[] expectedFilterOptionTexts = {"PAYMENT & PRICE", "MAKE & MODEL", "BODY TYPE", "YEAR & MILEAGE", "FEATURES", "MORE FILTERS"};

        for (int i = 0; i < searchFilterOptions.size(); i++) {
            Assert.assertEquals(searchFilterOptions.get(i).getText(), expectedFilterOptionTexts[i]);
        }

        searchInputBox.sendKeys("Tesla");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@data-qa='go-button']")).isDisplayed());
    }


    /**
     TASK-6
     Given user is on "https://www.carvana.com/"
     When user clicks on "SEARCH ALL VEHICLES" link
     Then user should be routed to "https://www.carvana.com/cars"
     When user enters "mercedes-benz" to the search input box
     And user clicks on "GO" button in the search input box
     Then user should see "mercedes-benz" in the url
     And validate each result tile
     VALIDATION OF TILE:
     Make sure each result tile is displayed with below information
     1. an image
     2. favorite vehicle button
     3. tile body
     ALSO VALIDATE TILE BODY:
     Make sure each tile body has below information
     1. Inventory type - text should not be null
     2. Make-Model information - text should not be null
     3. Price - Make sure that each price is more than zero
     4. Trim-Mileage information - text should not be null
     5. Terms-Trade information - text should not be null
     6. Delivery chip - text should be "Free Delivery"
     */

    @Test (priority = 6)
    public void validateResultTiles(){
        driver.get("https://www.carvana.com/");
        driver.findElement(By.xpath("//a[@data-cv-test='Taxseason.SearchLink']")).click();
        String expectedUrl = "https://www.carvana.com/cars";

        Waiter.waitForURLToBe(driver, explicitWait, expectedUrl);
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

        String carToSearch = "mercedes-benz";
        driver.findElement(By.xpath("//input[@data-qa='search-bar-input']")).sendKeys(carToSearch);
        driver.findElement(By.xpath("//button[@data-qa='go-button']")).click();

        Waiter.waitForURLToContain(driver, explicitWait, carToSearch);
        Assert.assertTrue(driver.getCurrentUrl().contains(carToSearch));

        List<WebElement> resultTiles = driver.findElements(By.xpath("//article[@data-qa='result-tile']"));
        List<WebElement> resultTileImages = driver.findElements(By.xpath("//article[@data-qa='result-tile']//img"));
        List<WebElement> resultTileFavButton = driver.findElements(By.xpath("//div[@data-qa='favorite-vehicle']"));
        List<WebElement> resultTileBody = driver.findElements(By.xpath("//div[@class='tile-body']"));

        List<WebElement> resultTileBodyInventoryTypes = driver.findElements(By.xpath("//div[@data-qa='inventory-type']"));
        List<WebElement> resultTileBodyMakeModels = driver.findElements(By.xpath("//div[@data-qa='make-model']"));
        List<WebElement> resultTileBodyPrices = driver.findElements(By.xpath("//div[@data-qa='price']"));
        List<WebElement> resultTileBodyTrimMileages = driver.findElements(By.xpath("//div[@data-qa='trim-mileage']"));
        List<WebElement> resultTileBodyTermsTrades = driver.findElements(By.xpath("//div[@data-qa='terms-trade']"));
        List<WebElement> resultTileBodyDeliveryChips = driver.findElements(By.xpath("//div[@data-qa='delivery-chip']"));

        for (int i = 0; i < resultTiles.size(); i++) {
            Assert.assertTrue(resultTiles.get(i).isDisplayed());
            Assert.assertTrue(resultTileImages.get(i).isDisplayed());
            Assert.assertTrue(resultTileFavButton.get(i).isDisplayed());
            Assert.assertTrue(resultTileBody.get(i).isDisplayed());

            Assert.assertTrue(resultTileBodyInventoryTypes.get(i).isDisplayed());
            Assert.assertNotNull(resultTileBodyInventoryTypes.get(i).getText());
            Assert.assertTrue(resultTileBodyMakeModels.get(i).isDisplayed());
            Assert.assertNotNull(resultTileBodyMakeModels.get(i).getText());

            Assert.assertTrue(resultTileBodyPrices.get(i).isDisplayed());
            Assert.assertTrue(Integer.parseInt(resultTileBodyPrices.get(i).getText().substring(1).replace(",", "")) > 0);

            Assert.assertTrue(resultTileBodyTrimMileages.get(i).isDisplayed());
            Assert.assertNotNull(resultTileBodyTrimMileages.get(i).getText());

            Assert.assertTrue(resultTileBodyTermsTrades.get(i).isDisplayed());
            Assert.assertNotNull(resultTileBodyTermsTrades.get(i).getText());

            Assert.assertTrue(resultTileBodyDeliveryChips.get(i).isDisplayed());
            Assert.assertEquals(resultTileBodyDeliveryChips.get(i).getText(), "Free Delivery");
        }
    }
}
