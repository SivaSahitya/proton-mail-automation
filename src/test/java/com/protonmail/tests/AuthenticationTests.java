package com.protonmail.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.protonmail.pages.LoginPage;
import com.protonmail.utils.ConfigReader;

public class AuthenticationTests extends BaseTest {

    @Test
    public void validLoginSdonehouldOpenMail() {

    	driver.get("https://account.proton.me/mail");

        LoginPage loginPage = new LoginPage(driver);

        String username = ConfigReader.getProtonUsername();
        String password = ConfigReader.getProtonPassword();

        Assert.assertNotNull(username, "PROTON_USERNAME is not configured");
        Assert.assertNotNull(password, "PROTON_PASSWORD is not configured");

        loginPage.login(username, password);

        // We will add a stronger post-login assertion after
        // observing the actual logged-in Proton Mail UI.
    }
    
    @Test
    public void invalidPasswordShouldShowLoginValidation() {

        driver.get("https://account.proton.me/mail");

        LoginPage loginPage = new LoginPage(driver);

        String username = ConfigReader.getProtonUsername();

        Assert.assertNotNull(username, "PROTON_USERNAME is not configured");

        loginPage.enterUsername(username);
        loginPage.enterPassword("InvalidPassword@12345");
        loginPage.clickSignIn();

        Assert.assertTrue(
                loginPage.isLoginFormDisplayed(),
                "Login form should remain displayed after invalid password"
        );
    }
}