package com.fz.PageObject;

import com.fz.AbstractClasses.AbstractUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractUtilities {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(css = "[placeholder='Select Country']")
    WebElement dropdownInput;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement countryToSelect;
    @FindBy(css = ".btnn")
    WebElement orderBtn;

    By dropdownResults = By.cssSelector(".ta-results");
    By submit = By.cssSelector(".action__submit");



    /*
    * driver.findElement(By.cssSelector(".totalRow button")).click();

        // autosuggestive dropdown
         /*driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("mor");
        List<WebElement> dropdownResults = driver.findElements(By.cssSelector(".list-group-item"));
        System.out.println(dropdownResults.size());
        WebElement matchedElement = dropdownResults.stream().filter(e->e.getText().equalsIgnoreCase("morocco")).findFirst().orElse(null);
        matchedElement.click();

    Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"moro").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
        driver.findElement(By.cssSelector(".btnn")).click();*/



    public void selectCountry(String country) {
        waitForWebElementVisibility(dropdownInput);
        Actions a = new Actions(driver);
        a.sendKeys(dropdownInput, country).build().perform();
        waitForElementVisibility(dropdownResults);
        a.click(countryToSelect).build().perform();
        //countryToSelect.click();
    }

    public ConfirmationPage placeOrder(String country) {
        selectCountry(country);
        waitUntilElementIsClickable(submit);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", orderBtn);
        //orderBtn.click();
        return new ConfirmationPage(driver);

    }


}
