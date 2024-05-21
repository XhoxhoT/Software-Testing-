package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;

public class CustomerInfoPageDK extends BasePageDK{
    public String url = GlobalConfigs.URL + "customer/info";

    @FindBy(xpath = "//input[@id='Email']")
    public WebElement emailInput;

    @FindBy(xpath = "//button[@id='save-info-button']")
    public WebElement saveButton;


    @FindBy(xpath = "//span[@class='close']")
    public WebElement spanClose;

    public void navigateToCustomerInfoPage(){
        Driver.getDriver().get(url);
    }

    public void changeEmail(){
        emailInput.clear();
        emailInput.sendKeys("testEmail" + System.currentTimeMillis() + "@test.com");
    }

    public void saveChanges(){
        saveButton.click();
        spanClose.click();
    }

}
