package com.fz.Tests;

import com.fz.PageObject.CartPage;
import com.fz.PageObject.CataloguePage;
import com.fz.TestComponents.BaseTest;
import com.fz.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void handlingLoginError(){
        landingPage.loginApplication("testFail@gmail.com","Little_Sword@");
        Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage());
    }

    @Test
    public void handlingProductError() throws IOException, InterruptedException {
        String username = "mfzahraa@gmail.com";
        String pwd = "Little_Sword@1";

        String productName = "ZARA COAT 3";

        //For Login
        CataloguePage cataloguePage = landingPage.loginApplication("mfzahraa@gmail.com", "Little_Sword@1");

        // For catalogue page
        List<WebElement> products = cataloguePage.getProducts();

        cataloguePage.addToCart(productName);

        /* For cart page*/
        CartPage cartPage = cataloguePage.goToCart();
        Boolean isMatch = cartPage.verifyIfChecked("ZARA COAT 33");
        Assert.assertFalse(isMatch);
    }
}
