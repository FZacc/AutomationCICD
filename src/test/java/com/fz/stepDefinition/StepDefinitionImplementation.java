package com.fz.stepDefinition;

import com.fz.PageObject.*;
import com.fz.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {

    public LandingPage landingPage;
    public CataloguePage cataloguePage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on E-commerce site")
    public void i_landed_on_ecommerce_site() throws IOException {
        landingPage = launchApp();
    }

    @Given("^I logged in by using (.+) and (.+)$")
    public void i_logged_in_by_email_pwd(String email, String password){
        cataloguePage = landingPage.loginApplication(email,password);
    }

    @When("^add (.+) to cart$")
    public void add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = cataloguePage.getProducts();
        cataloguePage.addToCart(productName);

    }

    @When("^checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName){
        CartPage cartPage = cataloguePage.goToCart();
        Boolean isMatch = cartPage.verifyIfChecked(productName);
        Assert.assertTrue(isMatch);
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        // For Checkout page
        String country = "moro";
        confirmationPage = checkoutPage.placeOrder(country);
    }

    @Then("{string} is displayed on confirmation page")
    public void message_displayed_confirmation_page(String string){
        String confirmMessage = confirmationPage.verifyConfirmationMessage(string);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} is displayed on login page")
    public void wrong_credential_login_page(String string){
        Assert.assertEquals(string,landingPage.getErrorMessage());
        driver.close();
    }


}
