package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;

public class LoginPageDK extends BasePageDK{

    @FindBy(css = "a.ico-login")
    public WebElement Login;

    @FindBy(css = ".button-1.login-button")
    public WebElement loginButton;


    @FindBy(css = ".topic-block-title>h2")
    public WebElement successLoginMessage;

    @FindBy(className = "email")
    public WebElement email;

    @FindBy(className = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    public WebElement errorMessage;

    public void login(String Email, String password)
    {
        email.sendKeys(Email);
        passwordInput.sendKeys(password);

    }

    public void navigateToHomePage() {
        Driver.getDriver().get(GlobalConfigs.URL);
    }

    public void clickLoginButton(){
        Login.click();
    }

    public void submit(){
        loginButton.click();
    }

}
