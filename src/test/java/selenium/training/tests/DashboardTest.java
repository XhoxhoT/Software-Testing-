package selenium.training.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.training.pages.DashbordPageDK;
import selenium.training.pages.LoginPageDK;
import selenium.training.pages.NoteBookPageDK;
import selenium.training.utils.Driver;


public class DashboardTest {

    public LoginPageDK loginPageDK;

    public DashbordPageDK dashbordPageDK;

    public NoteBookPageDK noteBookPageDK;


    public DashboardTest() {
        loginPageDK = new LoginPageDK();
        dashbordPageDK = new DashbordPageDK();
        noteBookPageDK = new NoteBookPageDK();
    }

    @BeforeMethod
    public void init() {
        Driver.loginOrRegister(3);
    }

    @AfterMethod
    public void close() {
       // noteBookPageDK.afterEachLogoutAndClose();
    noteBookPageDK.logout();
    }

    @Test
    public void ClickElementsToAdd() {

        int initialWishingListElements = Integer.parseInt(Driver.removeParanthesis(noteBookPageDK.wishlistCardNumberDisplayed.getText()));
        int initialShoppingCartElement = Integer.parseInt(Driver.removeParanthesis(noteBookPageDK.shoppingCartNumberDisplayed.getText()));

        dashbordPageDK.clickNotebooks();

        // Click on the second element
        // Verify that the message is displayed after clicking the second element
        Assert.assertEquals(noteBookPageDK.clickSecondElementAndDisplayMessage(), "The product has been added to your wishlist");

        // Click on the third element
        // Verify that the message is displayed after clicking the third element
        Assert.assertEquals(noteBookPageDK.clickThirdElementAndDisplayMessage(), "The product has been added to your wishlist");

        // Click on the fourth element
        // Verify that the message is displayed after clicking the fourth element
        Assert.assertEquals(noteBookPageDK.clickFourthElementAndDisplayMessage(), "The product has been added to your shopping cart");

        // Click on the fifth element
        // Verify that the message is displayed after clicking the fifth element
        Assert.assertEquals(noteBookPageDK.clickfifthElementAndDisplayMessage(), "The product has been added to your shopping cart");
        // Click on the sixth element
        // Verify that the message is displayed after clicking the sixth element
        Assert.assertEquals(noteBookPageDK.clickSixthElementAndDisplayMessage(), "The product has been added to your shopping cart");

        //Verify that Wishlist on Menu bar displays +2
        Assert.assertEquals(Integer.parseInt(Driver.removeParanthesis(noteBookPageDK.wishlistCardNumberDisplayed.getText())), initialWishingListElements + 2);
        //Verify that Shopping Cart on Menu bar displays +3
        Assert.assertEquals(Integer.parseInt(Driver.removeParanthesis(noteBookPageDK.shoppingCartNumberDisplayed.getText())), initialShoppingCartElement + 3);
    }

}
