package com.code.saucedemo.pages;

import com.code.saucedemo.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    public WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        this.driver.get("https://www.saucedemo.com/inventory.html");
        this.driver.manage().window().maximize();
    }

    public WebElement getInventoryList() {
        return this.driver.findElement(By.className("inventory_list"));
    }

    public List<WebElement> getListInventoryItems() {
        return this.getInventoryList().findElements(By.className("inventory_item"));
    }

    public List<Product> getListProducts() {
       List<WebElement> webElementList = this.getListInventoryItems();

       for(int i = 0; i < webElementList.size(); i++) {

       }
         return null;
    }

    public boolean isDiplayed() {
        boolean toReturn = false;

        if(this.driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            toReturn = true;
        }

        return toReturn;
    }

    public void close() {
        this.driver.close();
        this.driver.quit();
    }

}
