package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.training.utils.Driver;


public class DashbordPageDK extends BasePageDK{

    @FindBy(css = ".wishlist-label")
    public WebElement wishListWebElement;

    @FindBy(css = "span.wishlist-qty")
    public WebElement wishlistQuantity;

    @FindBy(css = "span.cart-qty")
    public WebElement cartQuantity;

    @FindBy(xpath = "///a[@class='add-to-wishlist-button']\n")
    public WebElement shoppingCartWebElement;

    @FindBy(css = "div.header-menu > ul.top-menu.notmobile > li > a[href='/computers']")
    public WebElement ComputerHover;

    @FindBy(css = "div.header-menu > ul.top-menu.notmobile > li > ul.sublist.first-level > li > a[href='/notebooks']")
    public WebElement NoteBooks;

    public Actions actions;
    public  DashbordPageDK() {
        super();
        actions = new Actions(Driver.getDriver());
    }


    public void clickNotebooks(){

        actions.moveToElement(ComputerHover).perform();

        NoteBooks.click();
    }


    public void clickWishList() {
        wishListWebElement.click();
    }


    public void clickshoppingCart() {
        shoppingCartWebElement.click();
    }
}
