package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;

public class RegisterPageDK extends BasePageDK{

    @FindBy(css = "a[href='/register?returnUrl=%2F'].ico-register")
    public WebElement Register;

   @FindBy(css = "input[type='radio'][value='M'][id='gender-male'][name='Gender']")
   public WebElement maleRadioButton;

   @FindBy(id = "FirstName")
   public WebElement inputFieldName;

   @FindBy(id = "LastName")
   public WebElement inputFieldSurname;

   @FindBy(name = "DateOfBirthDay")
    public WebElement DayDropDown;

   @FindBy(name = "DateOfBirthMonth")
   public WebElement dateofBirthMonthDropdown;

   @FindBy(name = "DateOfBirthYear")
    public WebElement yearDropdown;

   @FindBy(id = "Email")
   public WebElement emailInput;

   @FindBy(id = "Company")
    public WebElement companyInput;

    @FindBy(id = "Password")
    public WebElement passwordReg;

   @FindBy(id = "ConfirmPassword")
    public WebElement confirmPasswordReg;

   @FindBy(id = "register-button")
    public WebElement registerButton;

   @FindBy(className = "result")
    WebElement registrationMessage;

    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    public WebElement errorMessage;

   public void register(String firstName, String lastName, String Email, String Company, String password, String Day, String Month, String Year)
   {
       maleRadioButton.click();
       inputFieldName.sendKeys(firstName);
       inputFieldSurname.sendKeys(lastName);

       Select select = new Select(DayDropDown);
       select.selectByVisibleText(Day);

       Select DateMonth = new Select(dateofBirthMonthDropdown);
       DateMonth.selectByVisibleText(Month);

       Select yearSelect = new Select(yearDropdown);
       yearSelect.selectByValue(Year);


       emailInput.sendKeys(Email);
       companyInput.sendKeys(Company);
       passwordReg.sendKeys(password);
       confirmPasswordReg.sendKeys(password);

       registerButton.click();

   }

   public String getEmail(){

       return emailInput.getText();
   }

   public String getPassword(){

       return passwordReg.getText();
   }

    public void navigateToHomePage() {
        Driver.getDriver().get(GlobalConfigs.URL);
    }

    public void clickRegisterButton() {
        Register.click();
    }

    public String getRegistrationMessage() {
        return registrationMessage.getText();
    }



}
