package com.code.saucedemo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    //protected String url;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @BeforeMethod
    public void beforeMethod() {

    }

   @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void close() {
        this.driver.close();
        this.driver.quit();
    }
}
