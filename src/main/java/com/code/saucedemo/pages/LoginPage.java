package com.code.saucedemo.pages;

import com.code.saucedemo.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        this.driver.get("https://www.saucedemo.com/");
        this.driver.manage().window().maximize();
    }

    //mapiranje elemenata
    public WebElement getInpUserName() {
        return driver.findElement(By.xpath("//input[@data-test='username']"));
    }

    public WebElement getInpPassword() {
        return driver.findElement(By.xpath("//input[@data-test='password']"));
    }

    public WebElement getBtnLogin() {
        return driver.findElement(By.xpath("//input[@name='login-button']"));
    }

    //TODO ime metode promeniti
    public WebElement getDataTestError() {
        return driver.findElement(By.xpath("//h3[@data-test='error']"));
    }

    //akcije nad elementima
    public void setUserName(String userName) {
        this.driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys(userName);
        //this.getInpUserName().sendKeys(userName);
    }

    public void setPassword(String password) {
        this.getInpPassword().sendKeys(password);
    }

    public void clickOnLogin() {
        this.getBtnLogin().click();
    }

    public void login(String userName, String password) {
        this.getInpUserName().sendKeys(userName);
        this.setPassword(password);
        this.clickOnLogin();
    }

    public void login(User user) {
        this.driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys(user.getUserName());
        this.driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys(user.getPassword());
        this.driver.findElement(By.xpath("//input[@name='login-button']")).click();

       /* this.getInpUserName().sendKeys(user.getUserName());
        this.setPassword(user.getPassword());
        this.clickOnLogin();*/
    }

    public boolean isDisplayed() {
        boolean toReturn = false;

        if(this.driver.getCurrentUrl().equals("https://www.saucedemo.com/")) {
            toReturn = true;
        }

        return toReturn;
    }

    public String getDataErrorMessage() {
        return this.getDataTestError().getText();
    }

    public void close() {
        this.driver.close();
        this.driver.quit();
    }

}
