Proton Mail Automation QA Assessment — Video Walkthrough
0:00–0:30 — Introduction

“Hi, I’m Sahitya.

For this assessment, I worked on a Selenium-based automation framework for the Proton Mail Web application using Java, Selenium WebDriver, TestNG and Maven.

My approach was risk-based, with a focus on maintainability, reusable Page Objects, reliable synchronization, and meaningful assertions.”

0:30–1:10 — Project Structure

“Let me first show the project structure.

The base package contains the common browser and test foundation.

The pages package contains Page Objects such as LoginPage and ComposePage.

The utils package contains reusable configuration functionality.

The tests package contains the TestNG test classes.

The pom.xml manages the Maven dependencies.”

Show:

src/main/java
 ├── base
 ├── pages
 └── utils

src/test/java
 └── tests

pom.xml
1:10–1:50 — Test Strategy

“For the test strategy, I prioritized scenarios based on user impact and regression value.

P0 covers critical authentication and primary email workflows.

P1 covers important recurring functionality such as drafts, inbox actions, search, filters, attachments and asynchronous behavior.

I documented the scenarios, priorities, preconditions, expected results and automation classification in the test-case workbook.”

Show your Excel workbook.

1:50–2:40 — Automation Demonstration

“Now I'll demonstrate the scenarios that I actually executed successfully.

I used environment variables for the Proton test credentials instead of storing passwords in the source code.

Here I'm running the authentication tests.”

Run:

AuthenticationTests

Show:

Total tests run: 2
Passes: 2
Failures: 0
Skips: 0

Then say:

“The first test validates successful authentication, and the second validates the invalid-password scenario.”

2:40–3:20 — Framework Design

“I used Page Object Model to keep test logic separate from application interaction.

For example, the LoginPage class contains the login locators and actions, while the authentication test focuses on what needs to be validated.

BaseTest provides common setup and teardown, and DriverFactory manages WebDriver creation and cleanup.

I also used explicit waits rather than fixed sleeps wherever possible.”

Show:

DriverFactory.java
BaseTest.java
LoginPage.java
AuthenticationTests.java
3:20–4:00 — Compose Investigation / Automation Finding

“During Compose automation, I successfully automated the login, Compose opening, recipient and subject interactions.

However, the rich-text message editor behaved differently during WebDriver execution compared with manual interaction.

The editor was visually available, but WebDriver was not consistently able to locate the active editor element.

I therefore documented this as an automation stability risk rather than incorrectly reporting it as a confirmed product defect.”

Show your Defect_Report.md.

4:00–4:35 — Documentation

“I also prepared the supporting assessment documentation.

This includes the test strategy and test cases, architecture note, execution report and automation finding report.

The documentation distinguishes between scenarios that were actually executed and the remaining planned regression coverage.”

Show the GitHub repository.

4:35–5:00 — Closing

“To summarize, I established the Java, Maven, Selenium and TestNG framework, implemented reusable Page Objects and common test infrastructure, and successfully validated the authentication scenarios.

The next implementation scope is to complete and stabilize the remaining Compose, Draft, Inbox, Search, Filter, Attachment and asynchronous scenarios, along with richer reporting and CI execution.

Thank you.