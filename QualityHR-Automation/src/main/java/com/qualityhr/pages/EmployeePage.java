package com.qualityhr.pages;

import com.qualityhr.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EmployeePage extends BasePage {

    // ================= LOCATORS =================
    private By pimModule = By.xpath("//a[contains(@href, 'viewPimModule')]");
    private By addButton = By.xpath("//button[contains(.,'Add')]");

    private By firstNameField = By.name("firstName");
    private By middleNameField = By.name("middleName");
    private By lastNameField = By.name("lastName");

    private By loginDetailsToggle = By.xpath("//span[contains(@class,'oxd-switch-input')]");

    private By usernameField = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By passwordField = By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input");
    private By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input");

    private By saveButton = By.xpath("//button[@type='submit']");

    private By successToast = By.xpath("//div[contains(@class,'oxd-toast--success')]");

    private By loader = By.className("oxd-form-loader");

    private By validationErrors = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    // ================= NAVIGATION =================
    public void navigateToPIM() {
        waitForElementVisible(pimModule).click();
    }

    // ================= ADD EMPLOYEE =================
    public void fillFullEmployeeDetails(String fname, String mname, String lname,
                                        String loginUser, String loginPass) {

        waitForElementVisible(addButton).click();
        waitForElementVisible(firstNameField);

        // Fill basic details
        sendKeys(firstNameField, fname);
        sendKeys(middleNameField, mname);
        sendKeys(lastNameField, lname);

        // Wait loader gone before toggle
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

        // Click toggle safely
        WebElement toggle = waitForElementVisible(loginDetailsToggle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);

        // Wait fields appear
        waitForElementVisible(usernameField);

        // Fill login details
        sendKeys(usernameField, loginUser);
        sendKeys(passwordField, loginPass);
        sendKeys(confirmPasswordField, loginPass);

        // Wait again before save
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

        waitForElementVisible(saveButton).click();
    }

    public boolean isEmployeeAdded() {
        try {
            wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
            return true;
        } catch (Exception e) {
            printValidationErrors();
            return false;
        }
    
    }

    // ================= DEBUG VALIDATION =================
    public void printValidationErrors() {
        List<WebElement> errors = driver.findElements(validationErrors);

        if (errors.size() > 0) {
            System.out.println("===== VALIDATION ERRORS =====");
            for (WebElement e : errors) {
                System.out.println(e.getText());
            }
        } else {
            System.out.println("No validation errors found.");
        }
    }
}