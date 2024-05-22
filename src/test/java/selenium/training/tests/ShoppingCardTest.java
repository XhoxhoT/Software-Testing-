package selenium.training.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.training.pages.CheckoutPageDK;
import selenium.training.pages.LoginPageDK;
import selenium.training.pages.NoteBookPageDK;
import selenium.training.pages.ShoppingCartPageDK;
import selenium.training.utils.Credentials;
import selenium.training.utils.Driver;

import java.time.Duration;

public class ShoppingCardTest {
    public ShoppingCartPageDK shoppingCartPageDK;
    public LoginPageDK loginPageDK;
    public CheckoutPageDK checkoutPageDK;

    public NoteBookPageDK noteBookPageDK;

    public ShoppingCardTest() {
        shoppingCartPageDK = new ShoppingCartPageDK();
        loginPageDK = new LoginPageDK();
        checkoutPageDK = new CheckoutPageDK();
    }

    @BeforeMethod
    public void init() {
        Driver.registerIfNeeded("continue");
        int initialShoppingCartElement = Integer.parseInt(Driver.removeParanthesis(shoppingCartPageDK.shoppingCartNumberDisplayed.getText()));
        if(initialShoppingCartElement == 0){
            noteBookPageDK = new NoteBookPageDK();
            noteBookPageDK.navigateToNotebookPage();
            noteBookPageDK.clickElementAndDisplayMessage("shoppingcart", 6);
        }
    }

    @Test
    public void shoppingCardTest() {
        //Hover over Shopping Cart – Menu
        shoppingCartPageDK.hoverShoppingCardButton();
        //Verify that ‘Go To Cart’ – button is displayed
        Assertions.assertTrue(shoppingCartPageDK.goToCartButton.isDisplayed());
        // Click ‘Go To Cart’ – button
        shoppingCartPageDK.navigate2GoToCard();
        //Verify that we have navigate to Shopping Cart Page
        Assertions.assertEquals(shoppingCartPageDK.shoppingCartURL, Driver.getDriver().getCurrentUrl());
        Assertions.assertEquals("Shopping cart", shoppingCartPageDK.pageTitle.getText());
        // Verify that following buttons are displayed
        //Apperantely, "Update shopping cart button" is not visible.
        Assertions.assertTrue(shoppingCartPageDK.continueShoppingButton.isDisplayed() && shoppingCartPageDK.estimateShippingButton.isDisplayed());
        //Verify that the prices sum for all items is equal to Total Price in the end of the page
        double totalPrice = shoppingCartPageDK.getTotalPrice();
        Assertions.assertEquals(shoppingCartPageDK.sumProductUnitPriceInTheShoppingCart(), totalPrice);
        //Click “Update shopping cart” – button
        //shoppingCartPageDK.clickUpdateShoppingCartButton();
        //Check checkbox with text: I agree with the terms of service and I adhere to them unconditionally
        //EXTRA: Check if the checkbox is not selected first
        Assertions.assertFalse(shoppingCartPageDK.inputTermsAndConditions.isSelected());
        shoppingCartPageDK.checkTermsAndConditions();
        Assertions.assertTrue(shoppingCartPageDK.inputTermsAndConditions.isSelected());
        // Click “Checkout” – button
        shoppingCartPageDK.clickCheckoutButton();
        //Verify “First name”, “Last name” and “Email” input fields displays the values you entered while Register.
        Assertions.assertEquals(Credentials.FIRSTNAME, checkoutPageDK.firstName.getAttribute("value"));
        Assertions.assertEquals(Credentials.LASTNAME, checkoutPageDK.lastName.getAttribute("value"));
        Assertions.assertEquals(Credentials.EMAIL, checkoutPageDK.email.getAttribute("value"));
        //EXTRA: Clicking the "Continue" without filling the billing information
        //------ will make the error validation messages appear.
        //------ Checking if there are 5 error validation messages
        checkoutPageDK.clickContinueButton("Billing address");
        if(checkoutPageDK.listOfValidationErrorMessages.size() != 0){
            Assertions.assertEquals(5, checkoutPageDK.listOfValidationErrorMessages.size());
            //EXTRA: Checking if the "City is required" error validation message has appeared
            Assertions.assertTrue(checkoutPageDK.checkIfErrorValidationMessageHasAppeared("City is required"));
            //EXTRA: The following error message: "INVALID ERROR" should not be present
            Assertions.assertFalse(checkoutPageDK.checkIfErrorValidationMessageHasAppeared("INVALID ERROR"));
            checkoutPageDK.fillShippingAddressInformation("Albania", "Shkoder", "Isa Boletini, Shkoder", "4001", "+3556968670123");
            //Click “Continue” - button
            checkoutPageDK.clickContinueButton("Billing address");
        }
        //In Shipping method, select the second option (Next Day Air)
        //EXTRA: There are few cases when radio buttons can all be selected
        //------ therefore let's ensure when one is selected the other two options
        //------ are not selected.
        checkoutPageDK.clickOption(checkoutPageDK.shippingOptions, 0);
        Assertions.assertTrue(checkoutPageDK.shippingOptions.get(0).isSelected() && !checkoutPageDK.shippingOptions.get(1).isSelected() && !checkoutPageDK.shippingOptions.get(2).isSelected());
        checkoutPageDK.clickOption(checkoutPageDK.shippingOptions, 2);
        Assertions.assertTrue(!checkoutPageDK.shippingOptions.get(0).isSelected() && !checkoutPageDK.shippingOptions.get(1).isSelected() && checkoutPageDK.shippingOptions.get(2).isSelected());
        //------ Let's ensure that the second option is "Next Day Air"
        Assertions.assertTrue(checkoutPageDK.labelForNextDayShippingOption.getText().contains("Next Day Air"));
        //In Shipping method, select the second option (Next Day Air)
        checkoutPageDK.clickOption(checkoutPageDK.shippingOptions, 1);
        // Click “Continue” – button
        checkoutPageDK.clickContinueButton("Shipping method");
        //EXTRA: Check if there are two payment options
        Assertions.assertEquals(2, checkoutPageDK.listOfPaymentOptions.size());
        // In Payment method select the first option (Check/Money Order)
        checkoutPageDK.clickOption(checkoutPageDK.listOfPaymentOptions, 0);
        //  Click “Continue” – button
        checkoutPageDK.clickContinueButton("Payment method");
        // In Payment Information click “Continue” – button
        checkoutPageDK.clickContinueButton("Payment Information");
        // In Confirm Order, verify that the price displayed in Total is the same as the one in step 6.
        Assertions.assertEquals(totalPrice, checkoutPageDK.getTotalPrice());
        //  Click “Confirm” – button
        checkoutPageDK.clickConfirmButton();
        // Verify your order is done successful and an order number is displayed.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPageDK.orderConfirmationMessage));
        Assertions.assertTrue(checkoutPageDK.orderConfirmationMessage.isDisplayed() && checkoutPageDK.isOrderNumberDisplayed());
    }


}
