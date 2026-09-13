package com.protonmail.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By composeButton =
            By.cssSelector("[data-testid='sidebar:compose']");

    private By toField =
            By.cssSelector("[data-testid='composer:to']");

    private By ccButton =
            By.cssSelector("[data-testid='composer:recipients:cc-button']");

    private By ccField =
            By.cssSelector("[data-testid='composer:to-cc']");

    private By bccButton =
            By.cssSelector("[data-testid='composer:recipients:bcc-button']");

    private By bccField =
            By.cssSelector("[data-testid='composer:to-bcc']");

    private By subjectField =
            By.cssSelector("[data-testid='composer:subject']");

    private By messageBody =
            By.cssSelector("div[contenteditable='true']");

    private By sendButton =
            By.cssSelector("[data-testid='composer:send-button']");

    public ComposePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickCompose() {
        wait.until(
                ExpectedConditions.elementToBeClickable(composeButton)
        ).click();
    }

    public void enterRecipient(String recipient) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(toField))
                .sendKeys(recipient, Keys.ENTER);
    }

    public void clickCC() {
        wait.until(ExpectedConditions.elementToBeClickable(ccButton))
                .click();
    }

    public void enterCC(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ccField))
                .sendKeys(email, Keys.ENTER);
    }

    public void clickBCC() {
        wait.until(ExpectedConditions.elementToBeClickable(bccButton))
                .click();
    }

    public void enterBCC(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bccField))
                .sendKeys(email, Keys.ENTER);
    }

    public void enterSubject(String subject) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subjectField))
                .sendKeys(subject);
    }

    public void enterMessage(String message) {

        int count = driver.findElements(
                By.cssSelector("div[contenteditable='true']")
        ).size();

        System.out.println("Contenteditable elements found: " + count);

        WebElement body = wait.until(driver ->
                driver.findElements(
                        By.cssSelector("div[contenteditable='true']")
                ).stream()
                 .filter(WebElement::isDisplayed)
                 .findFirst()
                 .orElse(null)
        );

        body.click();
        body.sendKeys(message);
    }

    public void clickSend() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton))
                .click();
    }
}