package com.code.saucedemo.tests;

import com.code.saucedemo.models.Product;
import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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

    @Test
    public void verifyLoginWithProblemUser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("problem_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        List<Product> productList = productsPage.getListProducts();

        boolean problemUserIn = true;
        for(int i = 1; i < productList.size(); i++) {
            if(!productList.get(0).getImgSource().equals(productList.get(i).getImgSource())) {
                problemUserIn = false;
                break;
            }
        }
        Assert.assertEquals(problemUserIn, true, "Problem user is not logged");
        productsPage.close();
    }

    @Test
    public void verifyLoginWithPerformanceUser()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("performance_glitch_user", "secret_sauce"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.isDiplayed(), true, "Product page is not displayed");
        productsPage.close();

    }
}
