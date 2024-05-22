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
import java.util.List;

public class NoteBookPageDK extends BasePageDK {

    public String url = GlobalConfigs.URL + "notebooks";

    @FindBy(css = "p.content")
    public WebElement addedToWishlistAndShoppingCart;

    @FindBy(xpath = "//button[@title='Add to wishlist']")
    public List<WebElement> wishlistButtons;

    @FindBy(xpath = "(//button)[text()='Add to cart']")
    public List<WebElement> shoppingCardButtons;
    public WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    public String clickElementAndDisplayMessage(String type, int index) {
        switch (type) {
            case "wishlist": {
                wishlistButtons.get(index - 1).click();
            }
            case "shoppingcart": {
                shoppingCardButtons.get(index - 1).click();
            }
        }
        wait.until(ExpectedConditions.visibilityOf(addedToWishlistAndShoppingCart));
        String message = addedToWishlistAndShoppingCart.getText();
        wait.until(ExpectedConditions.invisibilityOf(addedToWishlistAndShoppingCart));
        return message;
    }

    public void navigateToNotebookPage() {
        Driver.getDriver().get(url);
    }
}
