# QualityHR-Automation

## 📌 Project Overview

This project is a Selenium-based automation testing framework developed for the OrangeHRM application.

The framework is built using **Java + Selenium + TestNG + Maven** and follows the **Page Object Model (POM)** design pattern for clean, scalable, and maintainable automation.

### 🎯 Objective

To automate major modules of OrangeHRM and demonstrate real-world test automation practices such as:

* UI Automation Testing
* Reusable Framework Design
* Data-Driven Testing
* Reporting & Logging
* Failure Screenshot Capture
* Retry Mechanism

---

# 🚀 Tech Stack

| Tool               | Purpose                   |
| ------------------ | ------------------------- |
| Java               | Core Programming Language |
| Selenium WebDriver | Web UI Automation         |
| TestNG             | Test Execution            |
| Apache Maven       | Build Management          |
| Extent Reports     | Reporting                 |
| Excel              | Test Data Management      |

---

# 📂 Project Structure

QualityHR-Automation/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/qualityhr/
│   │   │       ├── base/
│   │   │       │   └── BasePage.java
│   │   │       │
│   │   │       ├── driver/
│   │   │       │   └── DriverFactory.java
│   │   │       │
│   │   │       ├── pages/
│   │   │       │   ├── AdminPage.java
│   │   │       │   ├── EmployeePage.java
│   │   │       │   ├── LeavePage.java
│   │   │       │   └── LoginPage.java
│   │   │       │
│   │   │       └── utils/
│   │   │           ├── ConfigReader.java
│   │   │           ├── ExtentReportManager.java
│   │   │           ├── ScreenshotUtil.java
│   │   │           └── WaitHelper.java
│   │   │
│   │   └── resources/
│   │       ├── config.properties
│   │       ├── testdata.xlsx
│   │       └── testdata.xlsx.tmp
│   │
│   ├── test/
│   │   ├── java/
│   │   │   └── com/qualityhr/
│   │   │       ├── listeners/
│   │   │       │   └── TestListener.java
│   │   │       │
│   │   │       └── tests/
│   │   │           ├── BaseTest.java
│   │   │           ├── LoginTest.java
│   │   │           ├── AdminTest.java
│   │   │           ├── EmployeeTest.java
│   │   │           └── LeaveTest.java
│   │   │
│   │   └── resources/
│
│── reports/
│── screenshots/
│── pom.xml
│── testng.xml
└── README.md
---

# ⚙️ Configuration

Update `config.properties`

```properties id="1x6h97"
baseUrl=https://opensource-demo.orangehrmlive.com/
browser=chrome
timeout=10
username=Admin
password=admin123
testDataPath=src/test/resources/testdata/testdata.xlsx
```

---

# ▶️ How to Run Project

## ✅ Prerequisites

Install:

* Java Development Kit 8 or above
* Apache Maven
* Google Chrome
* ChromeDriver (matching browser version)
* IDE: IntelliJ IDEA / Eclipse IDE

---

## ✅ Run All Tests

```bash id="2v2j3n"
mvn test
```

---

## ✅ Run Specific Suite

```bash id="8nuxxq"
mvn test -DsuiteXmlFile=testng.xml
```

---

## ✅ Run From IDE

* Open project in IntelliJ / Eclipse
* Right click `testng.xml`
* Click **Run**

---

# 📊 Reports

After execution:

### Extent Report

```bash id="lnc5p4"
reports/ExtentReport.html
```

### Failure Screenshots

```bash id="d8z0o8"
screenshots/
```

---

# ✅ Framework Features

## 🔹 Design Pattern

✔ Page Object Model (POM)

## 🔹 Automation Utilities

✔ Base Test Setup
✔ Base Page Methods
✔ Common Wait Methods
✔ Click / Type Utilities

## 🔹 Test Execution

✔ TestNG Execution
✔ Retry Failed Tests
✔ Suite Execution

## 🔹 Reporting

✔ Extent Reports
✔ Logs
✔ Screenshots on Failure

## 🔹 Data Driven Testing

✔ Excel Utility
✔ External Test Data Support

---

# 📌 Modules Automated

## 🔐 Login Module

* Valid Login
* Invalid Login
* Empty Username Validation
* Empty Password Validation
* Logout Verification

---

## 🏠 Dashboard Module

* Verify Dashboard Page Load
* Verify Menus Displayed
* Quick Navigation Checks

---

## 👤 Admin Module

* Add New User
* Search User
* Delete User
* Verify Role Assignment

---

## 👨‍💼 PIM Module

* Add Employee
* Search Employee
* Open Employee Details
* Invalid Search Validation

---

## 📅 Leave Module

* Open Leave Section
* Apply Leave
* Verify My Leave Records
* Invalid Date Validation

---

## 📝 Form Validation Module

* Mandatory Field Errors
* Invalid Inputs
* Dropdown Selection Validation

---

# 🧠 Framework Design Explanation

## BaseTest.java

Handles:

* Browser Launch
* Driver Initialization
* Setup & Teardown

## BasePage.java

Contains reusable methods:

* click()
* sendKeys()
* waitForElement()
* getText()

## Page Classes

Each page contains:

* Locators
* Page Actions
* Business Methods

## Test Classes

Contains actual scenarios using page objects.

---

# 📈 Sample Test Flow

## Login Test

1. Launch Browser
2. Open OrangeHRM
3. Enter Username
4. Enter Password
5. Click Login
6. Validate Dashboard

---

# 🔁 Retry Mechanism

If any test fails due to temporary issue:

* Automatically retries test
* Helps reduce flaky failures

---

# 📷 Screenshot Capture

Whenever test fails:

* Screenshot auto captured
* Saved in screenshots folder
* Attached in report

---

# 🛠 Maven Dependencies Used

* Selenium Java
* TestNG
* WebDriverManager
* Apache POI
* Extent Reports

---

# 📌 Future Enhancements

* Cross Browser Testing
* Parallel Execution
* Jenkins CI/CD
* Docker Support
* Allure Reports
* Headless Execution
* Cloud Testing (BrowserStack / Sauce Labs)

---

# 👨‍💻 Author

**Katakamsetty Lakshmi Manasa**

---

# 📌 Why This Project Is Good For Resume

✔ Real-time Automation Framework
✔ Industry Standard Design Pattern
✔ Reporting Integration
✔ Data Driven Framework
✔ Reusable Structure
✔ Selenium + TestNG Hands-on Experience

---

# 📜 License

This project is for educational and learning purposes.

---

# 📬 Contact

For collaboration or learning discussions, feel free to connect.
