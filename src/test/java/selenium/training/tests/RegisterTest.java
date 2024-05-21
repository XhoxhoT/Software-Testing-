package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.training.pages.RegisterPageDK;
import selenium.training.utils.Credentials;
import selenium.training.utils.Driver;

public class RegisterTest {

    public RegisterPageDK registerPageDK;

    public RegisterTest() {
        this.registerPageDK = new RegisterPageDK();
    }

    @BeforeMethod
    public void init() {
        Driver.loginOrRegister(2);
    }

    @AfterMethod
    public void afterEach() {

        registerPageDK.logout();
    }

    @Test
    public void register() {

        registerPageDK.navigateToHomePage();
        registerPageDK.clickRegisterButton();

        Assert.assertEquals(Driver.getDriver().getTitle(), "nopCommerce demo store. Register");

        registerPageDK.register(
                Credentials.FIRSTNAME,
                Credentials.LASTNAME,
                Credentials.EMAIL,
                Credentials.COMPANY,
                Credentials.PASSWORD,
                Credentials.DAY,
                Credentials.MONTH,
                Credentials.YEAR
        );

        String registrationMessage = registerPageDK.getRegistrationMessage();
        Assert.assertTrue(registrationMessage.contains("Your registration completed"));
    }


}
