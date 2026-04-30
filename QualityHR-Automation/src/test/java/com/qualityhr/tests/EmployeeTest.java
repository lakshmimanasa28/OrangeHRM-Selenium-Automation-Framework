package com.qualityhr.tests;

import com.qualityhr.pages.EmployeePage;
import com.qualityhr.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {

    @Test
    public void testAddEmployeeWithFullDetails() {

        LoginPage login = new LoginPage(driver);
        login.login(config.getUsername(), config.getPassword());

        EmployeePage emp = new EmployeePage(driver);
        emp.navigateToPIM();

       
        String username = "user_" + System.currentTimeMillis();

       
        String password = "Test@1234";

        emp.fillFullEmployeeDetails("Manasa", "QA", "Lak", username, password);

        boolean result = emp.isEmployeeAdded();

        Assert.assertTrue(result, "FAILED: Employee record was not saved successfully!");
    }
}