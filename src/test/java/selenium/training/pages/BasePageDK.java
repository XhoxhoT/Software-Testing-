package selenium.training.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;

import java.time.Duration;

public class BasePageDK {

    public BasePageDK() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "body > div.master-wrapper-page > div.header > div.header-upper > div.header-links-wrapper > div.header-links > ul > li:nth-child(2) > a")
    public WebElement logoutButtonWebElement;

    @FindBy(xpath = "//a[@class='ico-wishlist']")
    public WebElement wishlistButton;

    @FindBy(xpath = "//a[@href='/cart']")
    public WebElement shoppingCardButton;

    @FindBy(xpath="//button[@class='button-1 cart-button']")
    public WebElement goToCartButton;

    @FindBy(xpath = "//span[@class='wishlist-qty']")
    public WebElement wishlistCardNumberDisplayed;

    @FindBy(xpath = "//span[@class='cart-qty']")
    public WebElement shoppingCartNumberDisplayed;

    Actions actions = new Actions(Driver.getDriver());


    public void logout() {
        try {

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(7));
            wait.until(ExpectedConditions.elementToBeClickable(logoutButtonWebElement));
            logoutButtonWebElement.click();
            Assertions.assertEquals("https://demo.nopcommerce.com/",Driver.getDriver().getCurrentUrl());
        } catch (StaleElementReferenceException e) {
            logoutButtonWebElement.click();
            Assertions.assertEquals("https://demo.nopcommerce.com/", Driver.getDriver().getCurrentUrl());
        } finally {
            // Navigate to the home page after logging out
            Driver.getDriver().get(GlobalConfigs.URL);
        }
    }

    public void afterEachLogoutAndClose(){
        logout();
        Driver.getDriver().close();
    }

    public void navigateToWishlistPage(){
        actions.moveToElement(wishlistButton).click().perform();
    }

    public void hoverShoppingCardButton() { actions.moveToElement(shoppingCardButton).perform();}

    public void navigate2GoToCard(){
        actions.moveToElement(goToCartButton).click().perform();
    }
}





