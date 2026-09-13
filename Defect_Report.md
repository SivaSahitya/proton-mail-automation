# Proton Mail Assessment — Defect / Automation Findings

## Finding AR-001 — Rich-text compose editor is not consistently exposed to WebDriver

### Classification

Automation-relevant stability risk / investigation finding.

This finding is **not being reported as a confirmed Proton Mail product defect**.

### Environment

- OS: Windows 11
- Browser: Google Chrome 152.x
- Selenium: 4.35.0
- Java: 17.0.12
- Application: Proton Mail Web

### Preconditions

- Valid Proton Mail Sender test account
- User successfully authenticated
- Compose window opened

### Observed behavior

During manual exploration, the message editor was visually present and usable.

During automated execution, WebDriver intermittently failed to locate the editor element using the inspected DOM locator. Multiple locator/wait strategies were attempted, including:

- `#rooster-editor`
- `div[contenteditable='true']`
- explicit visibility/presence waits

The automation subsequently timed out while waiting for the editor.

### Expected

Once the Compose window is open and the editor is visually ready, WebDriver should be able to locate the active message editor and enter the message body reliably.

### Actual

The editor could be visible to the user while not being locatable through the selected WebDriver locator at the same execution point.

### Reproducibility

Observed during automation attempts; not sufficient evidence to classify as an application defect. It is treated as an automation stability risk.

### Impact

This prevents the current automation flow from reliably completing the Send workflow and from providing a deterministic end-to-end assertion.

### Evidence

TestNG failure logs recorded `TimeoutException` and `NoSuchElementException` in `ComposePage.enterMessage()` while locating the message editor.

### Recommended investigation

1. Inspect the DOM immediately before and during the failure.
2. Check for shadow DOM, iframe, dynamically mounted editor instances, or DOM replacement.
3. Identify a stable application-specific attribute or component hook.
4. Use event/state-based synchronization rather than fixed sleeps.
5. Re-run the test across fresh browser sessions to confirm reproducibility.

---

## No confirmed product defects

No unrelated product defect is being claimed without deterministic reproduction and sufficient evidence.
