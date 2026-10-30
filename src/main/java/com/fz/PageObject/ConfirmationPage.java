package com.fz.PageObject;

import com.fz.AbstractClasses.AbstractUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ConfirmationPage extends AbstractUtilities {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(className = "hero-primary")
    WebElement confirmationMessage;

    /*
    * String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));*/

    public String verifyConfirmationMessage(String displayedMessage){
        String confirmMessage = confirmationMessage.getText();
        return confirmMessage;
    }



}
