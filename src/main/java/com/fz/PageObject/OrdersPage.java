package com.fz.PageObject;

import com.fz.AbstractClasses.AbstractUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractUtilities {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "tr td:nth-of-type(2)")
    List<WebElement> productsName;


    public Boolean verifyOrder(String productName){
        return productsName.stream().anyMatch(el->el.getText().equalsIgnoreCase(productName));
    }



}
