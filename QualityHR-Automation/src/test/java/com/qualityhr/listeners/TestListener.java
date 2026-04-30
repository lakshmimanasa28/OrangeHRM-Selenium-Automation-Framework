package com.qualityhr.listeners;

import com.aventstack.extentreports.*;
import com.qualityhr.utils.ExtentReportManager;
import org.testng.*;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail("Test Failed ❌");
        test.get().fail(result.getThrowable());

        // Optional Screenshot (add later if needed)
        // String path = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
        // test.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped ⚠️");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
        System.out.println(" Report Saved. Refresh Eclipse folder now.");
    }
}