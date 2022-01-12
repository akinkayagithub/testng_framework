package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppleHomePage {

    public AppleHomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h2")
    public WebElement firstHeading;
}
