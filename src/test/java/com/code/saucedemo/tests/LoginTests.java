package com.code.saucedemo.tests;

import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void verifyLoginStandardUser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.isDiplayed(), true, "Product page is not displayed");
        productsPage.close();

    }

    @Test
    public void verifyLoginWithLockOutUser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("locked_out_user", "secret_sauce"));

        Assert.assertEquals(loginPage.isDisplayed(), true, "Login page is not displayed");
        loginPage.close();

    }

    @Test
    public void verifyMessageOnLoginWithLockOutUser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("locked_out_user", "secret_sauce"));

        Assert.assertEquals(loginPage.getDataErrorMessage(), "Epic sadface: Sorry, this user has been locked out.", "Error message is not as expected");
        loginPage.close();

    }

}
