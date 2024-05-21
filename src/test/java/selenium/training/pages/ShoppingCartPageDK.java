package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.training.utils.GlobalConfigs;

import java.util.List;

public class ShoppingCartPageDK extends BasePageDK {

    public String shoppingCartURL = GlobalConfigs.URL + "cart";

    @FindBy(xpath = "//div[@class='page-title']")
    public WebElement pageTitle;

    @FindBy(xpath = "//button[@name='continueshopping']")
    public WebElement continueShoppingButton;
    @FindBy(xpath = "//a[@href='#estimate-shipping-popup']")
    public WebElement estimateShippingButton;

    @FindBy(xpath = "//span[@class='product-subtotal']")
    public List<WebElement> listProductUnitPrice;

    @FindBy(xpath = "//span[@class='value-summary']//strong")
    public WebElement totalPrice;

    @FindBy(xpath = "//button[@id='updatecart']")
    public WebElement updateShoppingCartButton;

    @FindBy(xpath = "//input[@id='termsofservice']")
    public WebElement inputTermsAndConditions;

    @FindBy(xpath = "//button[@id='checkout']")
    public WebElement checkoutButton;

    public double sumProductUnitPriceInTheShoppingCart(){
        double total = 0;
        for(WebElement element : listProductUnitPrice){
            String price = element.getText().substring(1).replace(",", "");
            total += Double.parseDouble(price);
        }
        return total;
    }

    public double getTotalPrice(){
        return Double.parseDouble(totalPrice.getText().substring(1).replace(",", ""));
    }

    public void clickUpdateShoppingCartButton(){
        updateShoppingCartButton.click();
    }

    public void checkTermsAndConditions(){
        inputTermsAndConditions.click();
    }

    public void clickCheckoutButton(){
        checkoutButton.click();
    }

}
