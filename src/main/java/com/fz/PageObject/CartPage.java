package com.fz.PageObject;

import com.fz.AbstractClasses.AbstractUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractUtilities {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;
    @FindBy(css = ".cartSection h3")
    WebElement cartProducts_;
    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;
    By cartProductsLocator = By.cssSelector(".cartSection h3");



    /*
    * driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        //check if ZARA COAT is selected
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean isMatch = cartProducts.stream().anyMatch(el->el.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(isMatch);*/
    public List<WebElement> getCartProducts() {
        waitForElementVisibility(cartProductsLocator);
        return cartProducts;
    }
    public Boolean verifyIfChecked(String productName){
        return getCartProducts().stream().anyMatch(el->el.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        Actions a = new Actions(driver);
        a.click(checkoutBtn).build().perform();
        //checkoutBtn.click();
        return new CheckoutPage(driver);

    }


}
