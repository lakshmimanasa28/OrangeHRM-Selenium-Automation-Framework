package com.qualityhr.tests;

import com.qualityhr.pages.LoginPage;
import com.qualityhr.pages.LeavePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeaveTest extends BaseTest {

    @Test
    public void testFinalStableLeaveFlow() {
        System.out.println(">>> STARTING FINAL SUBMISSION RUN - Manasa <<<");
        
      
        LoginPage login = new LoginPage(driver);
        login.login(config.getUsername(), config.getPassword());

        LeavePage leavePage = new LeavePage(driver);
        
       
        String empName = "Sumit Savaliya Yupp";
        leavePage.assignLeaveStep(empName, "2026-04-20", "2026-04-21");
        boolean result = leavePage.verifyLeaveList();
        Assert.assertTrue(result, "Final Leave List verification failed.");
        
        System.out.println(">>> 5/5 PASS ACHIEVED - PROJECT COMPLETE <<<");
    }
}