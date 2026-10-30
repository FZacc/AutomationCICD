package com.fz.PageObject;

import com.fz.AbstractClasses.AbstractUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CataloguePage extends AbstractUtilities {
    WebDriver driver;

    public CataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".mb-3")
    List<WebElement> products;


    @FindBy(css = ".ng-animating")
    WebElement loader;
    @FindBy(css = ".card-body button:last-of-type")
    WebElement addToCartWebElement;



    By productsLocator = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public List<WebElement> getProducts(){
        waitForElementVisibility(productsLocator);
        return products;
    }

    /*
    * WebElement product = products.stream().filter(e->e.findElement(By.cssSelector("b"))
            .getText().equals(productName)).findFirst().orElse(null);
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //get msg to check if element is successfully added into cart
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        // ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));*/

    public WebElement getProductByName(String productName){
        //System.out.println(product.getText());
        return getProducts().stream().filter(e->e.findElement(By.cssSelector("b"))
            .getText().equals(productName)).findFirst().orElse(null);
    }

    public void addToCart(String productName) throws InterruptedException {
        WebElement product = getProductByName(productName).findElement(addToCart);
        Actions a = new Actions(driver);
        a.click(product).build().perform();
        waitForElementVisibility(toastMessage);
        waitForElementToDisappear();
        //new CartPage(driver);
    }






}
