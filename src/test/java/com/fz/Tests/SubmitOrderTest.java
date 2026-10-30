package com.fz.Tests;

import com.fz.PageObject.*;
import com.fz.TestComponents.BaseTest;
import com.fz.TestComponents.Retry;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData",groups = {"purchase"},alwaysRun = true
    ,timeOut = 40000)
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        //For Login
        CataloguePage cataloguePage = landingPage.loginApplication(input.get("email"),input.get("pwd"));

        // For catalogue page
        List<WebElement> products = cataloguePage.getProducts();

        cataloguePage.addToCart(input.get("product"));

        /* For cart page*/
        CartPage cartPage = cataloguePage.goToCart();
        Boolean isMatch = cartPage.verifyIfChecked(input.get("product"));
        Assert.assertTrue(isMatch);
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        // For Checkout page
        String country = "moro";
        ConfirmationPage confirmationPage = checkoutPage.placeOrder(country);


        //For Confirmation page
        String displayedMessage = "Thankyou for the order.";
        String confirmMessage = confirmationPage.verifyConfirmationMessage(displayedMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(displayedMessage));



    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void ordersHistory(){
        CataloguePage cataloguePage = landingPage.loginApplication("zmar@gmail.com","Little_Sword@1");
        OrdersPage ordersPage = cataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrder(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        String filePath = System.getProperty("user.dir")+"//src//test//java//com//fz//data//dataSets.json";
        List<HashMap<String, String>> data = getJsonDataToMap(filePath);


        return new Object[][]{{data.get(0)}};

    }



    /*public Object[][] getData(){
        //return new Object[][]{{"zmar@gmail.com","Little_Sword@1","ZARA COAT 3"},{"mfzahraa@gmail.com","Little_Sword@1","ADIDAS ORIGINAL"}};
        HashMap<String, String> firstDataSet = new HashMap<String,String>();
        firstDataSet.put("email","zmar@gmail.com");
        firstDataSet.put("pwd","Little_Sword@1");
        firstDataSet.put("product","ZARA COAT 3");

        HashMap<String, String> secondDataSet = new HashMap<String,String>();
        secondDataSet.put("email","mfzahraa@gmail.com");
        secondDataSet.put("pwd","Little_Sword@1");
        secondDataSet.put("product","ADIDAS ORIGINAL");


        return new Object[][]{{firstDataSet},{secondDataSet}};

    }*/


}
