package com.protonmail.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.protonmail.pages.ComposePage;
import com.protonmail.pages.LoginPage;
import com.protonmail.utils.ConfigReader;

public class ComposeTests extends BaseTest {

    private void login() {

        driver.get("https://account.proton.me/mail");

        LoginPage loginPage = new LoginPage(driver);

        String username = ConfigReader.getProtonUsername();
        String password = ConfigReader.getProtonPassword();

        Assert.assertNotNull(username, "PROTON_USERNAME is not configured");
        Assert.assertNotNull(password, "PROTON_PASSWORD is not configured");

        loginPage.login(username, password);
    }

    @Test
    public void sendEmailWithValidDetails() {

        login();

        ComposePage composePage = new ComposePage(driver);
        

        String receiver = ConfigReader.getProtonReceiverUsername();
        String subject = "QA Test " + System.currentTimeMillis();
        String body = "This is an automated Proton Mail test.";

        Assert.assertNotNull(
                receiver,
                "PROTON_RECEIVER_USERNAME is not configured"
        );

        composePage.clickCompose();
        composePage.enterRecipient(receiver);
        composePage.enterSubject(subject);
        composePage.enterMessage(body);
        composePage.clickSend();
    }

    @Test
    public void sendEmailWithCCAndBCC() {

        login();

        ComposePage composePage = new ComposePage(driver);

        String receiver = ConfigReader.getProtonReceiverUsername();
        String subject = "CC BCC Test " + System.currentTimeMillis();
        String body = "Testing CC and BCC functionality.";

        Assert.assertNotNull(
                receiver,
                "PROTON_RECEIVER_USERNAME is not configured"
        );

        composePage.clickCompose();

        composePage.enterRecipient(receiver);

        composePage.clickCC();
        composePage.enterCC(receiver);

        composePage.clickBCC();
        composePage.enterBCC(receiver);

        composePage.enterSubject(subject);
        composePage.enterMessage(body);

        composePage.clickSend();
    }

    @Test
    public void sendingWithoutRecipientShouldBePrevented() {

        login();

        ComposePage composePage = new ComposePage(driver);

        composePage.clickCompose();
        composePage.enterSubject("Missing Recipient Test");
        composePage.enterMessage("This email should not be sent.");

        composePage.clickSend();

        // We will add the exact validation assertion
        // after observing Proton's actual behavior.
    }
}