# ScreeningAutomation
Summary:
* Uses a Fitnesse Wiki Table to populate global vars and run Selenium WebDriver (Java) tests.
* Requires an initial run to grab (good) baseline website/chrome-application screen captures to compare subsequent screen captures.
* Baseline images are stored in the user's C:\baseline directory.
* Current images and respective diffs are stored in C:\current\<timestamp> directory.

Uses the following:
* Selenium WebDriver (Java)
* Fitnesse
* Sikuli (currently not implemented)

Chrome-Application usage:
* This screening application also works with applications running on Google Chrome Portable with a local Node.js server.
* To run:
** 1. Start the application to be tested
** 2. Let the server boot up, noting which port number the application will be using
** 3. Let the application start
** 4. Exit out of the application (Alt-F4), observing that the server is still up
** 5. Start the screening application by initiating the fitnesse-standalone.jar through cmd (make sure the port numbers for the application-to-be-tested and the fitnesse-wiki are different)
** 6. Set up your fitnesse table (Samples should be available in the code directory) (Documentation in progress.)
** 7. If no baseline yet, enter 'true' for the entire column of taking-baseline-set. (The baseline images SHOULD be the ones that have been considered "good".) (Does not have to be run every application run, only when approved designs have changed.)
** 8. Click on "Test" on the top left of the fitnesse-wiki screen.
** 9. Remove 'true' from the taking-baseline-set column and run the test
** 10. Navigate to the 'current' folder and go to the most recently generated folder
** 11. Filter through the series of diff images and flag major issues.

Web usage (exact same as steps 5-11 on Chrome-Application usage):
** 1. Start the screening application by initiating the fitnesse-standalone.jar through cmd (make sure the port numbers for the application-to-be-tested and the fitnesse-wiki are different)
** 2. Set up your fitnesse table (Samples should be available in the code directory) (Documentation in progress.)
** 3. If no baseline yet, enter 'true' for the entire column of taking-baseline-set. (The baseline images SHOULD be the ones that have been considered "good".) (Does not have to be run every application run, only when approved designs have changed.)
** 4. Click on "Test" on the top left of the fitnesse-wiki screen.
** 5. Remove 'true' from the taking-baseline-set column and run the test
** 6. Navigate to the 'current' folder and go to the most recently generated folder
** 7. Filter through the series of diff images and flag major issues.

Notes:
* Screening application is currently designed for full-screen Chrome.
