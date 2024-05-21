package selenium.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;

import java.time.Duration;

public class NoteBookPageDK extends BasePageDK{

    public String url = GlobalConfigs.URL + "notebooks";

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div[3]/div[2]/button[3]")
    public WebElement secondWishListButton;

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[3]/div[2]/button[3]")
    public WebElement thirdWishListButton;

    @FindBy(css = "p.content")
    public WebElement addedToWishlistAndShoppingCart;

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[4]/div/div[2]/div[3]/div[2]/button[1]")
    public WebElement fourthShoppingCartButton;

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[5]/div/div[2]/div[3]/div[2]/button[1]")
    public WebElement fifthShoppingCartButton;

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[6]/div/div[2]/div[3]/div[2]/button[1]")
    public WebElement sixthShoppingCartButton;

    @FindBy(xpath = "//span[@class='close']")
    public WebElement spanClose;

    public WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    public String clickSecondElementAndDisplayMessage() {

        actions.moveToElement(secondWishListButton).click().perform();
      //  secondWishListButton.click();
        wait.until(ExpectedConditions.visibilityOf(addedToWishlistAndShoppingCart));
        String message = addedToWishlistAndShoppingCart.getText();
        wait.until(ExpectedConditions.invisibilityOf(addedToWishlistAndShoppingCart));
        return message;
    }

    public String clickThirdElementAndDisplayMessage() {
        thirdWishListButton.click();
        wait.until(ExpectedConditions.visibilityOf(addedToWishlistAndShoppingCart));
        String message = addedToWishlistAndShoppingCart.getText();
        wait.until(ExpectedConditions.invisibilityOf(addedToWishlistAndShoppingCart));
        return message;
    }

    public String clickFourthElementAndDisplayMessage() {
        fourthShoppingCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(addedToWishlistAndShoppingCart));
        String message = addedToWishlistAndShoppingCart.getText();
        wait.until(ExpectedConditions.invisibilityOf(addedToWishlistAndShoppingCart));
        return message;
    }

    public String clickfifthElementAndDisplayMessage() {
        fifthShoppingCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(addedToWishlistAndShoppingCart));
        String message = addedToWishlistAndShoppingCart.getText();
        wait.until(ExpectedConditions.invisibilityOf(addedToWishlistAndShoppingCart));
        return message;
    }

    public String clickSixthElementAndDisplayMessage() {
        sixthShoppingCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(addedToWishlistAndShoppingCart));
        String message = addedToWishlistAndShoppingCart.getText();
        wait.until(ExpectedConditions.invisibilityOf(addedToWishlistAndShoppingCart));
        return message;
    }

    public void navigateToNotebookPage(){
        Driver.getDriver().get(url);
    }
}
