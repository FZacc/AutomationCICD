package com.fz.PageObject;

import com.fz.AbstractClasses.AbstractUtilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends AbstractUtilities {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        // initialize all class elements with driver
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement submitBtn;
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public CataloguePage loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        Actions a = new Actions(driver);
        a.click(submitBtn).build().perform();
        //submitBtn.click();
        return new CataloguePage(driver);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementVisibility(errorMessage);
        return errorMessage.getText();
    }
}
