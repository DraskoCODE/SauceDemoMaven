package com.code.saucedemo.pages;

import com.code.saucedemo.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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

    public WebElement getCart() {
        return this.driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public WebElement getSelectContainer() {
        return this.driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
    }

    public WebElement getInventoryList() {
        return this.driver.findElement(By.className("inventory_list"));
    }

    public List<WebElement> getListInventoryItems() {
        return this.getInventoryList().findElements(By.className("inventory_item"));
    }

    public List<Product> getListProducts() {
        List<Product> toReturn = new ArrayList<>();
         List<WebElement> webElementList = new ArrayList<>();
        webElementList = this.getListInventoryItems();

       for(int i = 0; i < webElementList.size(); i++) {

            WebElement item = webElementList.get(i);

            WebElement itemName = item.findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String name = itemName.getText();

            WebElement itemPrice = item.findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String price = itemPrice.getText();
            String tmpPrice = price.substring(1);

            WebElement itemImage = item.findElement(By.xpath(".//img[@class='inventory_item_img']"));
            String src = itemImage.getAttribute("src");

           Product product = new Product(name, Double.parseDouble(tmpPrice), src);
            toReturn.add(product);
       }
         return toReturn;
    }

    public void addProductToCartByName(String name) {
        List<WebElement> webElementList = this.getListInventoryItems();

        int indexOfItem = 0;
        for(int i = 0; i < webElementList.size(); i++) {
            WebElement item = webElementList.get(i);
            if(item.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText().equals(name)) {
                indexOfItem = i;
                break;
            }
        }
        WebElement btnAddToCart = webElementList.get(indexOfItem).findElement(By.xpath(".//button"));
        btnAddToCart.click();
    }

    public int getNumOfProductsInCart() {
        int toReturn;

        WebElement cart = this.getCart();

        List<WebElement> cartSpan = cart.findElements(By.xpath(".//span"));
        if(cartSpan.size() == 0) {
            toReturn = 0;
        }
        else {
            toReturn = Integer.parseInt(cartSpan.get(0).getText());
        }
        return toReturn;
    }

    public void selectSorting(String sorting) {
        WebElement sortingContainer = this.getSelectContainer();
        sortingContainer.click();
        List<WebElement> webElementList = sortingContainer.findElements(By.xpath(".//option"));

        int index = 0;
        for(int i = 0; i < webElementList.size(); i++) {
            if(webElementList.get(i).getText().equals(sorting)) {
                index = i;
                break;
            }
        }
        webElementList.get(index).click();
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
