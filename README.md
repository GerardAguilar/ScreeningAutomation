# ScreeningAutomation
Summary:
* Uses a Fitnesse Wiki Table to populate global vars and run Selenium WebDriver (Java) tests.
* Requires an initial run to grab (good) baseline website screen captures to compare subsequent website screen captures.
* Baseline images are stored in the user's C:\baseline directory.
* Current images and respective diffs are stored in C:\current\<timestamp> directory.

Uses the following:
* Selenium WebDriver (Java)
* Fitnesse
* Sikuli (currently not implemented)
