package com.qualityhr.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            new File("reports").mkdirs();
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setReportName("QualityHR Automation - Manasa");
            spark.config().setDocumentTitle("Manasa");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("QA Engineer", "Manasa");
            extent.setSystemInfo("Environment", "Production-Demo");
        }
        return extent;
    }
}