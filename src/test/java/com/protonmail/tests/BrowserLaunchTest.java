package com.protonmail.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserLaunchTest extends BaseTest {

    @Test
    public void openProtonMail() {

        driver.get("https://proton.me/mail");

        String actualTitle = driver.getTitle();

        System.out.println("Page title: " + actualTitle);

        Assert.assertFalse(
                actualTitle.isEmpty(),
                "Page title should not be empty"
        );
    }
}