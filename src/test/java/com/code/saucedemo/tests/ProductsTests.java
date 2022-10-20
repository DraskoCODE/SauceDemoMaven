package com.code.saucedemo.tests;

import com.code.saucedemo.models.Product;
import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTests {

    @Test
    public void verifyAddProductToCartByName() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);

        int numOfProductsBeforeAdd = productsPage.getNumOfProductsInCart();
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");
        int numOfProductsAfterAdd = productsPage.getNumOfProductsInCart();
        Assert.assertEquals(numOfProductsAfterAdd, numOfProductsBeforeAdd + 3, "Number of products is not as expected");

        productsPage.close();
    }

    @Test
    public void verifySortingProductsListByPriceLowToHigh() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectSorting("Price (low to high)");
        boolean sorting = true;
        List<Product> productList = productsPage.getListProducts();
        for(int i = 0; i < productList.size() - 1; i++) {
            int j = i + 1;
            if(productList.get(i).getPrice() > productList.get(j).getPrice()) {
                sorting = false;
                break;
            }
        }
        Assert.assertEquals(sorting, true, "Sorting is not as expected");
        productsPage.close();
    }

}
