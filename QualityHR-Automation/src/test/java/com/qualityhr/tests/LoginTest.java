package com.qualityhr.tests;

import com.qualityhr.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testValidLogin() {

        System.out.println(">>> STARTING LOGIN TEST - MANASA <<<");

        LoginPage loginPage = new LoginPage(driver);

        String user = config.getUsername();
        String pass = config.getPassword();

        loginPage.login(user, pass);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        String currentUrl = driver.getCurrentUrl();

        System.out.println("Login Redirection URL: " + currentUrl);

        Assert.assertTrue(
            currentUrl.contains("dashboard"),
            "Login failed! Dashboard was not reached."
        );

        System.out.println(">>> LOGIN TEST PASSED - 5/5 <<<");
    }
}