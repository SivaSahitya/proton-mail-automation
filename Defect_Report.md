Proton Mail Web Assessment — Defect & Findings Report
Finding ID: AR-001
Title

Rich-text message editor cannot be reliably located during automated Compose execution

Finding Type

Automation Stability Risk

This is not classified as a confirmed product defect because the editor works correctly during manual interaction and there is insufficient evidence to conclude that Proton Mail itself is malfunctioning.

Environment
Field	Details
Operating System	Windows 11
Browser	Google Chrome 152.x
Selenium	4.35.0
Java	17.0.12
Test Framework	TestNG
Application	Proton Mail Web
Test Area	Compose / Send
Preconditions
A valid Proton Mail Sender account is available.
The user can successfully authenticate.
The Compose window can be opened.
A valid recipient and subject can be entered.
Steps to Reproduce
Launch Proton Mail using the Selenium automation framework.
Log in using the Sender test account.
Open the Compose window.
Enter a valid recipient.
Enter a subject.
Attempt to locate and enter text into the message body using Selenium.
Observe the WebDriver result.
Expected Result

After the Compose window is opened, the active message editor should be consistently identifiable by the automation framework so that the message body can be entered and the Send workflow can continue.

Actual Result

The message editor is visible and usable during manual interaction, but during automated execution Selenium was unable to consistently locate the active editor.

The following approaches were investigated:

#rooster-editor
div[contenteditable='true']
presence-based wait
visibility-based wait

The automated test subsequently failed with:

TimeoutException
NoSuchElementException

while attempting to interact with the message editor.

Impact

The issue currently prevents the automated Compose → Enter Body → Send workflow from being completed reliably.

This reduces the stability of the Compose regression test and prevents a deterministic end-to-end Send assertion.

Reproducibility

Observed repeatedly during automation attempts.

Manual interaction of the Compose editor was successful.

Because the behavior was not reproduced as a user-facing application failure, it is classified as an automation stability finding rather than a product defect.

Evidence

TestNG execution logs captured:

org.openqa.selenium.TimeoutException
org.openqa.selenium.NoSuchElementException

The failures occurred in:

ComposePage.enterMessage()

during the sendEmailWithValidDetails automation scenario.

Automation Priority

P1 — High for the automation suite

Reason: the finding blocks reliable automation of a critical email-sending workflow.

Product Severity: N/A

Reason: no confirmed end-user product failure was established.

Recommended Investigation
Capture the DOM immediately before the failure.
Determine whether the editor is dynamically mounted or replaced after Compose opens.
Check for iframe, shadow DOM, or component re-rendering.
Identify a stable application-specific selector or component attribute.
Synchronize against the editor's actual ready state instead of using fixed delays.
Re-run the test across fresh browser sessions to confirm the behavior.
Current Status

Open — Automation Investigation

The Compose workflow remains partially automated pending a stable synchronization/locator strategy for the rich-text editor.

Confirmed Defects

No confirmed product defects were identified during the scope of this assessment.

Only observations supported by reproducible evidence have been recorded.