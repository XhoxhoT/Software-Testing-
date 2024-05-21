package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.training.utils.Credentials;
import selenium.training.utils.Driver;

import java.time.Duration;
import java.util.List;

public class CheckoutPageDK extends BasePageDK {

    public Actions actions = new Actions(Driver.getDriver());
    @FindBy(xpath = "//input[@id='BillingNewAddress_FirstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='BillingNewAddress_LastName']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='BillingNewAddress_Email']")
    public WebElement email;

    @FindBy(xpath = "//select[@id='BillingNewAddress_CountryId']")
    public WebElement country;

    @FindBy(xpath = "//input[@id='BillingNewAddress_City']")
    public WebElement city;

    @FindBy(xpath = "//input[@id='BillingNewAddress_Address1']")
    public WebElement firstAddress;

    @FindBy(xpath = "//input[@id='BillingNewAddress_ZipPostalCode']")
    public WebElement zipOrPostalCode;

    @FindBy(xpath = "//input[@id='BillingNewAddress_PhoneNumber']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//span[@class='field-validation-error']")
    public List<WebElement> listOfValidationErrorMessages;

    @FindBy(xpath = "//button[text()='Continue']")
    public List<WebElement> continueButton;

    @FindBy(xpath = "//input[@name='shippingoption']")
    public List<WebElement> shippingOptions;

    @FindBy(xpath = "//label[@for='shippingoption_1']")
    public WebElement labelForNextDayShippingOption;

    @FindBy(xpath = "//input[@name='paymentmethod']")
    public List<WebElement> listOfPaymentOptions;

    @FindBy(xpath = "//span[@class='value-summary']//strong")
    public WebElement cartTotal;

    @FindBy(xpath = "//button[text()='Confirm']")
    public WebElement confirmButton;

    @FindBy(xpath = "//strong[text()='Your order has been successfully processed!']")
    public WebElement orderConfirmationMessage;

    @FindBy(xpath = "//div[@class='order-number']//strong")
    public WebElement orderNumber;

    public WebDriverWait webDriverWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));

    public void fillShippingAddressInformation(String country, String city, String firstAddressLine, String zipOrPostalCode, String phonenumber) {
        firstName.sendKeys(Credentials.FIRSTNAME);
        lastName.sendKeys(Credentials.LASTNAME);
        email.sendKeys(Credentials.EMAIL);
        Select countrySelect = new Select(this.country);
        countrySelect.selectByVisibleText(country);
        this.city.sendKeys(city);
        this.firstAddress.sendKeys(firstAddressLine);
        this.zipOrPostalCode.sendKeys(zipOrPostalCode);
        this.phoneNumber.sendKeys(phonenumber);
    }

    public boolean checkIfErrorValidationMessageHasAppeared(String errorMessage) {
        for (WebElement presentErrorMessage : listOfValidationErrorMessages) {
            if (presentErrorMessage.getText().equals(errorMessage)) return true;
        }
        return false;
    }

    public void clickOption(List<WebElement> webElementList, int index){
        webElementList.get(index).click();
    }

    public void clickContinueButton(String whichSection) {
        WebElement continueButtonElement = null;
        switch (whichSection) {
            case "Billing address": {
                continueButtonElement = continueButton.get(0);
                break;
            }
            case "Shipping method": {
                continueButtonElement = continueButton.get(2);
                break;
            }
            case "Payment method": {
                continueButtonElement = continueButton.get(3);
                break;
            }
            case "Payment Information": {
                continueButtonElement = continueButton.get(4);
                break;
            }
        }
        webDriverWait.until(ExpectedConditions.visibilityOf(continueButtonElement));
        actions.moveToElement(continueButtonElement).click().perform();
    }

    public double getTotalPrice(){
        return Double.parseDouble(cartTotal.getText().replace("$", "").replace(",", ""));
    }

    public void clickConfirmButton(){
        confirmButton.click();
    }

    public boolean isOrderNumberDisplayed(){
        String number = orderNumber.getText().substring(14);
        try{
            Integer.parseInt(number);
        }catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

}
