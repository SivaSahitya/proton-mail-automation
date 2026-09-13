# Architecture Note — Proton Mail Web Automation

## 1. Purpose

This framework is designed for maintainable UI regression automation of Proton Mail Web. The design separates test intent from browser and page interaction details so that selectors and synchronization logic can be updated without rewriting test scenarios.

## 2. Technology Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Eclipse IDE
- Chrome for local execution

## 3. Framework Structure

```text
proton-mail-automation/
├── pom.xml
├── README.md
├── .gitignore
├── src/
│   ├── main/java/com/protonmail/
│   │   ├── base/
│   │   │   └── DriverFactory.java
│   │   ├── pages/
│   │   │   ├── LoginPage.java
│   │   │   └── ComposePage.java
│   │   └── utils/
│   │       └── ConfigReader.java
│   └── test/java/com/protonmail/tests/
│       ├── BaseTest.java
│       ├── BrowserLaunchTest.java
│       ├── AuthenticationTests.java
│       └── ComposeTests.java
└── testcases/
```

## 4. Design Approach

### Page Object Model

Application-specific locators and user actions are kept in page classes. Tests call business-level methods such as `login()` rather than repeating low-level Selenium commands.

### Driver Factory

`DriverFactory` centralizes WebDriver creation and cleanup. This avoids creating browser sessions independently in each test class and provides a single place to extend browser configuration later.

### Base Test

`BaseTest` owns common TestNG setup and teardown. Tests extend this class so browser initialization and cleanup are reused consistently.

### Configuration and Secrets

Credentials are read from environment variables through `ConfigReader`. Real credentials are not stored in source code. `.gitignore` also excludes local configuration and generated outputs.

### Synchronization

The framework uses Selenium explicit waits for dynamic UI elements rather than arbitrary fixed sleeps. This is intended to reduce timing-related flakiness.

### Assertions

Tests are expected to validate application state, not merely that a click completed. Authentication tests are being strengthened with post-login/negative-state assertions as the suite evolves.

## 5. Test Flow

```text
TestNG Test
    ↓
BaseTest
    ↓
DriverFactory
    ↓
WebDriver / Chrome
    ↓
Page Object
    ↓
Proton Mail Web
    ↓
Assertion
    ↓
Pass / Fail
```

## 6. Maintainability Decisions

- Stable semantic attributes such as `data-testid` are preferred over generated element IDs where available.
- Reusable actions are kept in page classes.
- Test data and credentials are separated from test logic.
- Browser setup is centralized.
- Failures should be diagnosable through test results and captured evidence as reporting is expanded.

## 7. Known Automation Risk

During live exploration, the rich-text composer editor was visually present but was not consistently discoverable by WebDriver under the same locator strategy used during manual inspection. This was treated as an automation synchronization/DOM-stability risk rather than reported as a product defect without sufficient evidence.

## 8. Future Scaling

To scale from approximately 20 tests to 200+ tests, the framework should introduce reusable components for common widgets, centralized locator abstractions, data factories/builders, environment-specific configuration, parallel-safe driver management, tagging for smoke/regression suites, CI execution, and targeted retry handling only for infrastructure-related failures.
