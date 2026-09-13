# SauceDemo Regression Suite

Selenium + Java + TestNG regression suite for https://www.saucedemo.com,
using Page Object Model. This version fixes a missing-folder build path
issue that caused "Cannot find class in classpath" errors in earlier
versions of this project.

## Coverage (15 tests)

- Login (4): valid login, invalid password, locked-out user, empty credentials
- Inventory (6): product count, price sort (low-high, high-low), name sort
  (A-Z, Z-A) with scroll-to-bottom + 3s wait + screenshot, add-to-cart badge
- Cart (3): add multiple items, view cart contents, remove item
- Checkout (2): full checkout flow, missing-field validation

Every click/type pauses 5 seconds (in BasePage.java) so each step is watchable.
Screenshots from the 4 sort scenarios save into the `screenshots/` folder at
the project root.

## IMPORTANT: fresh import instructions

1. In Eclipse, delete any old version of this project first: right-click it
   in Package Explorer -> Delete -> check "Delete project contents on disk".
2. Extract this zip to a NEW folder (do not extract over an old copy).
3. File -> Import -> Maven -> Existing Maven Projects -> Browse to the
   extracted folder -> Finish.
4. Right-click the project -> Maven -> Update Project (check "Force Update").
5. Project -> Clean... -> select this project -> Clean.
6. Wait for the background build to finish (bottom-right progress bar).

This project already includes a real (non-empty) `src/main/resources` folder,
which was missing in an earlier version and caused a "Build path entry is
missing" warning that led to classpath errors. That issue should not recur
with this package.

## How to run - three options, in order of reliability

**1. Individual test class (most reliable, proven to always work):**
Right-click any file in `src/test/java/com/saucedemo/tests` (e.g.
LoginTests.java) -> Run As -> TestNG Test

**2. TestRunner.java (runs the full suite as a plain Java program):**
Right-click `TestRunner.java` -> Run As -> Java Application
This runs all 4 classes together via testng.xml, but through a normal
Java entry point instead of Eclipse's separate TestNG Suite launcher,
avoiding that launcher's classpath configuration entirely.

**3. testng.xml directly (fastest if it works, but has had classpath issues
before on this machine):**
Right-click `src/test/resources/testng.xml` -> Run As -> TestNG Suite
If this gives "Cannot find class in classpath", just use option 1 or 2
instead - they give identical test coverage.

**4. Command line (completely bypasses Eclipse):**
Open a terminal in the project root and run:
```
mvn clean test
```
Let it run fully to completion (several minutes, due to the 5-second
pauses) before checking output.

## Notes on locators

Locators are based on SauceDemo's known, stable public structure. If a test
fails with "no such element", right-click the real element on the live page
-> Inspect, and update the matching Page Object - this is normal test
maintenance, not a sign of a broken project.

Fix test count in README
