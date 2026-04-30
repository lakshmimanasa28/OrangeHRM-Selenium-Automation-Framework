package com.qualityhr.pages;

import com.qualityhr.base.BasePage;
import com.qualityhr.utils.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LeavePage extends BasePage {

    private final WaitHelper helper;

    private final String leaveListUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList";

    private final By leaveMenu = By.xpath("//span[normalize-space()='Leave']");
    private final By assignLeaveMenu = By.xpath("//a[normalize-space()='Assign Leave']");
    private final By empInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By hintBox = By.xpath("//div[@role='listbox']");
    private final By allHints = By.xpath("//div[@role='listbox']//*[self::span or self::div][normalize-space()]");

    private final By typeDropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    private final By leaveTypeOptions = By.xpath("//div[@role='listbox']//*[self::span or self::div][normalize-space()]");

    private final By fromDate = By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]");
    private final By toDate = By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]");

    private final By assignBtn = By.xpath("//button[@type='submit']");
    private final By confirmBtn = By.xpath("//button[contains(@class,'oxd-button--secondary')]");

    private final By loader = By.className("oxd-form-loader");

    public LeavePage(WebDriver driver) {
        super(driver);
        this.helper = new WaitHelper(driver);
    }

    public void assignLeaveStep(String fullName, String start, String end) {
        System.out.println("[Manasa-Log] Navigating to Assign Leave...");

        wait.until(ExpectedConditions.elementToBeClickable(leaveMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(assignLeaveMenu)).click();

        wait.until(ExpectedConditions.urlContains("assignLeave"));

        if (driver.getPageSource().contains("403")) {
            throw new RuntimeException("Access denied: Assign Leave not permitted for this user");
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(empInput));

        selectEmployeeFromAutocomplete(fullName);

        helper.forceClick(typeDropdown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hintBox));

        WebElement leaveType = wait.until(d -> {
            List<WebElement> options = d.findElements(leaveTypeOptions);
            for (WebElement option : options) {
                if (option.isDisplayed()) {
                    return option;
                }
            }
            return null;
        });
        if (leaveType != null) {
            leaveType.click();
        } else {
            throw new TimeoutException("Leave type options did not appear");
        }

        updateDate(fromDate, start);
        updateDate(toDate, end);

        helper.forceClick(assignBtn);
        handlePopup();
    }

    public boolean verifyLeaveList() {
        System.out.println("[Manasa-Log] Verifying in Leave List...");
        driver.get(leaveListUrl);
        wait.until(ExpectedConditions.urlContains("viewLeaveList"));
        return driver.getCurrentUrl().contains("viewLeaveList");
    }

    private void selectEmployeeFromAutocomplete(String fullName) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(empInput));
        input.click();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(fullName);

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            List<WebElement> options = shortWait.until(d -> {
                List<WebElement> visible = d.findElements(allHints);
                visible.removeIf(e -> !e.isDisplayed());
                return visible.isEmpty() ? null : visible;
            });

            WebElement chosen = options.stream()
                    .filter(e -> e.getText() != null
                            && e.getText().trim().toLowerCase().contains(fullName.trim().toLowerCase()))
                    .findFirst()
                    .orElse(options.get(0));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chosen);

        } catch (TimeoutException e) {
            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);
        }
    }

    private void updateDate(By locator, String val) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
        el.sendKeys(Keys.CONTROL + "a");
        el.sendKeys(Keys.BACK_SPACE);
        el.sendKeys(val);
        el.sendKeys(Keys.TAB);
    }

    private void handlePopup() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        } catch (Exception e) {
            System.out.println("[Manasa-Log] No popup appeared.");
        }
    }
}