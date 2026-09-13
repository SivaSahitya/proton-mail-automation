package com.protonmail.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By signInButton = By.cssSelector("button[type='submit']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
                .sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();

        waitForLoginToComplete();
    }

    public void waitForLoginToComplete() {
        wait.until(driver ->
                !driver.getCurrentUrl().equals("https://account.proton.me/mail")
        );
    }

    public boolean isLoginFormDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField)
        ).isDisplayed();
    }
}