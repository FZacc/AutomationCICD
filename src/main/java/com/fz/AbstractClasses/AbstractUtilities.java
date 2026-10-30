package com.fz.AbstractClasses;

import com.fz.PageObject.CartPage;
import com.fz.PageObject.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractUtilities {


    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cart;
    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersPage;

    public AbstractUtilities(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);

    }

    public CartPage goToCart(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", cart);
        //Actions a = new Actions(driver);
        //a.click(cart).build().perform();

        //cart.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage(){
        ordersPage.click();
        return new OrdersPage(driver);
    }

    public void waitForElementVisibility(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElementVisibility(WebElement findBy) {
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear() throws InterruptedException {
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementIsClickable(By findBy) {
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }
    public void waitUntilWebElementIsClickable(WebElement findBy) {
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }
}
