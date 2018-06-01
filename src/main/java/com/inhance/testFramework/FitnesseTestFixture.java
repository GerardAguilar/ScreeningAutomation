package com.inhance.testFramework;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import com.google.common.base.Function;

/***
 * 
 * @author gaguilar
 * Need to create a shallow fixture
 */
public class FitnesseTestFixture {
	protected WebDriver driver;
	protected String baseUrl;
	protected WebDriverWait wait;
	protected Screen s = new Screen();  
	public int valueZero;
	public int valueTwo;
	
	public String globalAddress;
	public String elementId;
	public String xpath;
	public ArrayList<String> navigationPath;
	public ArrayList<String> navigationPathAlternate;
	
//	public boolean enableScreenshot;
	
	public Function<WebElement,Boolean> testWebFixture;
	public Function<String, Boolean> testSikuliFixture;
	
	public String imageFileLocation;
	public String instanceStartTime;
	
	public boolean willTakeBaselineSet;
	public boolean willMakeALogFile;
	public String pageScrollPosition;
	public boolean willClick;
	public boolean willWaitFor;
	public boolean willHover;
	public boolean checkVisibilityByClickability;
	public boolean checkVisibilityByOpacity;
	public boolean checkVisibilityBySelectability;
	public boolean checkInvisibilityByInvisibility;
	public boolean checkInvisibilityByOpacity;
	public boolean checkVisibilityByVisibility;
	public boolean checkVisibilityByWidth;
	public boolean checkDocumentReady;
	public boolean willCheckCssAttributeValuePair;
	public boolean willCheckExpectedCondition;
	public boolean willCheckJavascriptResult;
	public boolean willEndScreening;
	public boolean willInitialize;
	public boolean willVerifyResultingAddress;
	public boolean willGenerateNewTab;
	
	public String javascriptExecutorString;
	public String cssAttributeValuePair;
	public String expectedConditionType;
	public String notes;	
	public String chromeLocation;
	public String resultingAddress;
	public String chromeBinaryLocation;
	
	String currentDiffFilename;
	int id;
	public int fullScreenPageHeight = 1086;
	Long waitForElement;
	//waitFactor multiplies the transition-duration of the element we're currently interacting with
	long waitFactor;
	//how much polling to do in the course of the wait 
	float pollingFactor;	
	PrintWriter out;
	boolean baselineSet = false;
	String loc;
	
	public void setChromeBinaryLocation(String temp) {
		chromeBinaryLocation = temp;
	}
	public String getChromeBinaryLocation() {
		return chromeBinaryLocation;
	}
	
	public void setWillGenerateNewTab(boolean temp) {
		willGenerateNewTab = temp;
	}
	public boolean getWillGenerateNewTab() {
		return willGenerateNewTab;
	}
	
	public void setResultingAddress(String temp) {
		resultingAddress = temp;
	}
	public String getResultingAddress() {
		return resultingAddress;
	}
	
	public void setWillVerifyResultingAddress(boolean temp) {
		willVerifyResultingAddress = temp;
	}
	public boolean getWillVerifyResultingAddress() {
		return willVerifyResultingAddress;
	}
	
	public void setWillInitialize(boolean temp) {
		willInitialize = temp;
	}
	public boolean getWillInitialize() {
		return willInitialize;
	}
	
	public void setWillEndScreening(boolean temp) {
		willEndScreening = temp;
	}
	public boolean getWillEndScreening() {
		return willEndScreening;
	}
	
	public void setJavascriptExecutorString(String temp) {
		javascriptExecutorString = temp;
	}
	public String getJavascriptExecutorString() {
		return javascriptExecutorString;
	}
	
	public void setWillCheckJavascriptResult(boolean temp) {
		willCheckJavascriptResult = temp;
	}
	public boolean getWillCheckJavascriptResult() {
		return willCheckJavascriptResult;
	}
	
	public void setExpectedConditionType(String temp) {
		expectedConditionType = temp;
	} 
	public String getExpectedConditionType() {
		return expectedConditionType;
	}
	
	public void setWillCheckExpectedCondition(boolean temp) {
		willCheckExpectedCondition = temp;
	}
	public boolean getWillCheckExpectedCondition() {
		return willCheckExpectedCondition;
	}
	
	public void setCssAttributeValuePair(String temp) {
		cssAttributeValuePair = temp;
	}
	public String getCssAttributeValuePair() {
		return cssAttributeValuePair;
	}
	
	public void setWillCheckCssAttributeValuePair(boolean temp) {
		willCheckCssAttributeValuePair = temp;
	}
	public boolean getWillCheckCssAttributeValuePair() {
		return willCheckCssAttributeValuePair;
	}
	
	public void setNotes(String temp) {
		notes = temp;
	}
	public String getNotes() {
		return notes;
	}
	
	public void setWillHover(boolean temp) {
		willHover = temp;
	}
	public boolean getWillHover() {
		return willHover;
	}
	
	public void setCheckDocumentReady(boolean temp) {
		checkDocumentReady = temp;
	}
	public boolean getCheckDocumentReady() {
		return checkDocumentReady;
	}
	
	public void setChromeLocation(String temp) {
		chromeLocation = temp;
	}
	public String getChromeLocation() {
		return chromeLocation;
	}
	
	public void setCheckVisibilityByWidth(boolean temp) {
		checkVisibilityByWidth = temp;
	}
	public boolean getCheckVisibilityByWidth() {
		return checkVisibilityByWidth;
	}
	
	public void setCheckVisibilityByVisbility(boolean temp) {
		checkVisibilityByVisibility = temp;
	}
	public boolean getCheckVisibilityByVisbility() {
		return checkVisibilityByVisibility;
	}
	
	public void setCheckInvisibilityByOpacity(boolean temp) {
		checkInvisibilityByOpacity = temp;
	}
	public boolean getCheckInvisibilityByOpacity() {
		return checkInvisibilityByOpacity;
	}
	
	public void setCheckInvisibilityByInvisibility(boolean temp) {
		checkInvisibilityByInvisibility = temp;
	}
	public boolean getcheckInvisibilityByInvisibility() {
		return checkInvisibilityByInvisibility;
	}
	
	public void setCheckVisibilityBySelectability(boolean temp) {
		checkVisibilityBySelectability = temp;
	}
	public boolean getCheckVisibilityBySelectability() {
		return checkVisibilityBySelectability;
	}
	
	public void setCheckVisibilityByOpacity(boolean temp) {
		checkVisibilityByOpacity = temp;
	}
	public boolean getCheckVisibilityByOpacity() {
		return checkVisibilityByOpacity;
	}
	
	public void setCheckVisibilityByClickability(boolean temp) {
		checkVisibilityByClickability = temp;
	}
	public boolean getCheckVisibilityByClickability() {
		return checkVisibilityByClickability;
	}
	
	public void setWillClick(boolean temp) {
		willClick = temp;
	}
	public boolean getWillClick() {
		return willClick;
	}
	
	public void setWillWaitFor(boolean temp) {
		willWaitFor = temp;
	}
	public boolean getWilLWaitFor() {
		return willWaitFor;
	}
	
	public void setWillMakeALogFile(boolean temp) {
		willMakeALogFile = temp;
	}
	public boolean getWillMakeALogFile() {
		return willMakeALogFile;
	}
	
	public void setPageScrollPosition(String temp){
		pageScrollPosition = temp;
	}
	public String getPageScrollPosition() {
		return pageScrollPosition;
	}
	
	public void setWillTakeBaselineSet(boolean temp) {
		willTakeBaselineSet = temp;
	}
	
	public boolean getWillTakeBaselineSet() {
		return willTakeBaselineSet;
	}
	
	public void setImageFileLocation(String temp) {
		imageFileLocation = temp;
	}
	public String getImageFileLocation() {
		return imageFileLocation;		
	}
		
//	public void setEnableScreenshot(boolean temp) {
//		enableScreenshot = temp;
//	}
//	
//	public boolean getEnableScreenshot() {
//		return enableScreenshot;
//	}
	
	public FitnesseTestFixture(){	
			try {
//				initialize("about:blank");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void setBaseUrl(String temp) {
		baseUrl = temp;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public void setGlobalAddress(String temp) {
		globalAddress = temp;
	}
	public String getGlobalAddress(String temp) {
		return globalAddress;
	}
	
	public void setElementId(String temp) {
		elementId = temp;
	}
	public String getElementId() {
		return elementId;
	}
	
	public void setXpath(String temp) {
		xpath = temp;
	}
	public String getXpath() {
		return xpath;
	}

//	public boolean initialize(String home) throws Exception {
//		boolean setupFirefoxDriver = false;
//		boolean setupChromeDriver = true;
//		if(setupFirefoxDriver) {
//			ClassLoader classLoader = getClass().getClassLoader();
//	        URL resource = classLoader.getResource("geckodriver.exe");
//	        String os = System.getProperty("os.name").toLowerCase();
//	        
//	        //Make a directory to place Drivers in
//	        //This is used for later where we want multiple drivers
//	        File f = new File("Driver");
//	        if (!f.exists()) {
//	            f.mkdirs();
//	        }
//	        
//	        File geckodriver;
//	        geckodriver = new File("Driver" + "\\geckodriver.exe"); 
//	        //In the case of a MAC, we may need to copy the tar.gz file and then reference the resulting geckodriver application
//	        if(os.contains("mac")) {
//	            geckodriver = new File(System.getProperty("user.dir") + "/geckodriver");  
//	        }else {
//	        	geckodriver = new File("Driver" + "\\geckodriver.exe"); 
//	            if (!geckodriver.exists()) {
//	            	geckodriver.createNewFile();
//	                FileUtils.copyURLToFile(resource, geckodriver);
//	            }
//	        }
//	        String geckodriverLocation = geckodriver.getAbsolutePath();        
//	        System.setProperty("webdriver.gecko.driver", geckodriverLocation);
//		    driver = new FirefoxDriver();
//		}
//
//	    if(setupChromeDriver) {
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\GoogleChromePortable\\GoogleChromePortable.exe");
//			options.addArguments("disable-infobars");
//			options.addArguments("--allow-file-access-from-files");
//			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");              
//			driver = new ChromeDriver(options);
//	    }
//	    
////	    driver.manage().window().setSize(new Dimension(1024, 768));
////	    baseUrl = "http://www.google.com/";
//	    baseUrl = home;
////	    baseUrl = "http://localhost:8024";
//	    navigateByAddress(baseUrl);
//	    System.out.println("Current Window Handle: " + driver.getWindowHandle() );
//	    System.out.println("Other handles: ");
//	    ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
//	    for(int i=0; i<windowHandles.size(); i++) {
//	    	System.out.println(windowHandles.get(i));
//	    }
//	    driver.manage().window().maximize(); 
//	    wait = new WebDriverWait(driver, 20);
//	    //make sure to right click the resources\images folder, select Build Path -> Include
//	    ImagePath.add(SimpleFramework.class.getCanonicalName()+"/images");
////	    enableScreenshot = false;
//	    navigationPath = new ArrayList<String>();
//	    navigationPathAlternate = new ArrayList<String>();
//	    instanceStartTime = (new Timestamp(System.currentTimeMillis())).getTime()+"";
//	    
//        File baselineDirectory = new File("C:\\baseline");
//        if (!baselineDirectory.exists()) {
//        	baselineDirectory.mkdirs();
//        }
//        
//        File currentDirectory = new File("C:\\current\\"+instanceStartTime);
//        if (!currentDirectory.exists()) {
//        	currentDirectory.mkdirs();
//        }
//	    
//        id = 0;
//        pageScrollPosition = "";
//        waitForElement = (long) 0;
//        waitFactor = 20;
//        pollingFactor = 1; //the bigger the value the faster the polling
//        
//        createLogFile();
//        
//	    return true;
//	}
	public void initializeAlt() throws Exception {
		if(willInitialize) {
			System.out.println("initializeAlt");
			boolean setupFirefoxDriver = false;//currently has errors
			boolean setupChromeDriver = true;
			
		    navigationPath = new ArrayList<String>();
		    navigationPathAlternate = new ArrayList<String>();
		    instanceStartTime = (new Timestamp(System.currentTimeMillis())).getTime()+"";
		    
	        File baselineDirectory = new File("C:\\baseline");
	        if (!baselineDirectory.exists()) {
	        	baselineDirectory.mkdirs();
	        }
	        
	        File currentDirectory = new File("C:\\current\\"+instanceStartTime);
	        if (!currentDirectory.exists()) {
	        	currentDirectory.mkdirs();
	        }
	        
	        if(willTakeBaselineSet){
	        	baselineSet = true;
	        }
	        
	        createLogFile();
	        
			if(setupFirefoxDriver) {
				
				ClassLoader classLoader = getClass().getClassLoader();
		        URL resource = classLoader.getResource("geckodriver.exe");
		        String os = System.getProperty("os.name").toLowerCase();
		        
		        //Make a directory to place Drivers in
		        //This is used for later where we want multiple drivers
		        File f = new File("Driver");
		        if (!f.exists()) {
		            f.mkdirs();
		        }
		        
		        File geckodriver;
		        //In the case of a MAC, we may need to copy the tar.gz file and then reference the resulting geckodriver application
		        if(os.contains("mac")) {
		            geckodriver = new File(System.getProperty("user.dir") + "/geckodriver");  
		        }else {
		        	geckodriver = new File("Driver" + "\\geckodriver.exe"); 
		            if (!geckodriver.exists()) {
		            	geckodriver.createNewFile();
		                FileUtils.copyURLToFile(resource, geckodriver);
		            }
		        }
		        String geckodriverLocation = geckodriver.getAbsolutePath();        
		        System.setProperty("webdriver.gecko.driver", geckodriverLocation);
			    driver = new FirefoxDriver();
			    addActionToNavigationPathAlternate("Initialize geckodriver.exe");
			}

			//chromeDriverLocation == "the driver"
			//chromeBinaryLocation == "the car"
		    if(setupChromeDriver) {
				if(chromeBinaryLocation.length()>0) {
					/***
					 * From our resources folder, copy chromedriver.exe into a Driver folder
					 * Modify that chrome driver to attach to the chrome binary as designated in the Fitnesse table
					 */
					ClassLoader classLoader = getClass().getClassLoader();
			        URL resource = classLoader.getResource("chromedriver.exe");
					File chromedriver = new File("Driver"+"\\chromedriver.exe");
		            if (!chromedriver.exists()) {
		            	chromedriver.createNewFile();
		                FileUtils.copyURLToFile(resource, chromedriver);
		            }
					String chromeDriverLocation = chromedriver.getAbsolutePath();
			        
					ChromeOptions options = new ChromeOptions();
					options.setBinary(chromeBinaryLocation);
					options.addArguments("disable-infobars");
					options.addArguments("--allow-file-access-from-files");
					
					System.setProperty("webdriver.chrome.driver", chromeDriverLocation);              
					driver = new ChromeDriver(options);
					System.out.println("Chrome driver in " + chromeDriverLocation + " is paired with binary " + chromeBinaryLocation);
					addActionToNavigationPathAlternate("Initialize GoogleChromePortable.exe");
				}
		    }
		    
//		    driver.manage().window().setSize(new Dimension(1024, 768));	    
		    baseUrl = globalAddress;
		    navigateByAddress(baseUrl);
		    addActionToNavigationPathAlternate("Navigating to " + baseUrl);
		    driver.manage().window().maximize(); 
		    wait = new WebDriverWait(driver, 20);
		    //make sure to right click the resources\images folder, select Build Path -> Include
		    ImagePath.add(SimpleFramework.class.getCanonicalName()+"/images");

	        id = 0;
	        pageScrollPosition = "";
	        waitForElement = (long) 0;
	        waitFactor = 20;
	        pollingFactor = 1; //the bigger the value the faster the polling
	        
		}
		
	}
	
	public void getOtherWindowHandles() {
	    System.out.println("Current Window Handle: " + driver.getWindowHandle() );
	    System.out.println("Other handles: ");
	    ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
	    for(int i=0; i<windowHandles.size(); i++) {
	    	System.out.println(windowHandles.get(i));
	    }
	}
	
	
	
////	@AfterClass(alwaysRun = true)
//	public void tearDown() throws Exception {
//		createLogFile();
//		driver.quit();
//	}
	
	public void createLogFile() {
//		String appendedIndex = "";
		if(willMakeALogFile) {
	        try {
	        	loc ="";
	        	if(baselineSet) {
	        		loc = "C:\\baseline\\log.txt";
	        	}else {
	        		loc = "C:\\current\\"+instanceStartTime+"\\log.txt";
	        	}
//					PrintWriter out = new PrintWriter(loc);
	        	out = new PrintWriter(loc);
//			        for(int i=0; i<navigationPathAlternate.size(); i++) {
//			        	appendedIndex = String.format("%04d", i);
//			        	out.println(appendedIndex + ": " + navigationPathAlternate.get(i));
//			        }
		        out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
	
	BufferedWriter test;
	
	public void populateLogFile(ArrayList<String> navArray) {
		String appendedIndex = String.format("%04d", navArray.size()-1);
		//open out for appending
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(loc, true)));
//	    	out.println(appendedIndex + ": " + navigationPathAlternate.get(navArray.size()-1));
	    	out.append("\r\n" + appendedIndex + ": " + navigationPathAlternate.get(navArray.size()-1));
	    	out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
	
//	public void checkNewTab() {
//		if(willGenerateNewTab) {
//			WebElement element = driver.findElement(By.xpath(xpath));			
//			addActionToNavigationPathAlternate(xpath+"_newTab");
//			element.click();	
//
//		}
//	}
	
	public void endScreening() {
		if(willEndScreening) {
			out.close();
			driver.quit();
		}
	}
	
	public boolean simpleTest() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public boolean navigateByAddress(String address) {
//		System.out.println("navigateByAddress(String address): address = " + address); 
		driver.get(address);
		return true;
	}
	
	/***
	 * Used by Fitnesse. Requires global variable
	 */
	public void navigateByGlobalAddress() {
		if(globalAddress.length()>0) {
			driver.get(globalAddress);
		}else {
			
		}
	}


	public void hoverOverElement() {
		if(xpath.length()>0 && willHover) {
			addActionToNavigationPathAlternate(xpath + "_hover");		
			WebElement el = driver.findElement(By.xpath(xpath));
			Actions builder = new Actions(driver);
					
			//move cursor to displayed element
			//Scrolling seems to mess with the bottom code
//	        classname = el.getLocation();
//	        xcordi = classname.getX();
//	        ycordi = classname.getY();
//	        System.out.println("Element is located at: " + xcordi + ": "+ycordi);

			builder.moveToElement(el).perform();
		}		
	}
	
	//Wait for image to appear
	public void waitForElement() {
//		System.out.println("locateElementInPageByXpathAndWaitForNonZeroWidth: " + getNavigationPathAltEventId() + xpath.length() + willWaitFor);	
//		System.out.println("waitForElement()");
//		if(xpath.length()>0 && willWaitFor) {
		if(xpath.length()>0 && (willCheckExpectedCondition | willCheckCssAttributeValuePair | willCheckJavascriptResult | willVerifyResultingAddress | willGenerateNewTab)) {
//			System.out.println("actually locateElementInPageByXpathAndWaitForNonZeroWidth: " + xpath + "_wait");
			addActionToNavigationPathAlternate(xpath + "_wait");			

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)			    
					.withTimeout(waitFactor, TimeUnit.SECONDS)
				    .pollingEvery((long) .5, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);

			JavascriptExecutor jseWait = (JavascriptExecutor)driver;
			Wait<JavascriptExecutor> waitJse = new FluentWait<JavascriptExecutor>(jseWait)
				    .withTimeout(6, TimeUnit.SECONDS)
				    .pollingEvery(2, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
			
			
			//wait for nonZeroWidth
			if(checkVisibilityByWidth) {
				wait.until(new Function<WebDriver, Boolean>()
				{
					public Boolean apply(WebDriver driverCopy) {		
						boolean nonZeroWidth = checkIfElementHasNonZeroWidth(driverCopy);
						
						if(nonZeroWidth) {
							return true;
						}else {
							return false;
						}
					}
				});
			}

			//wait for element to be clickable
			if(checkVisibilityByClickability) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			}

			//wait for element to be opaque
			if(checkVisibilityByOpacity) {
				final String xpathCopy = xpath;
				wait.until(new Function<WebDriver, Boolean>() 
				{
					public Boolean apply(WebDriver driverCopy) {//shallow copy of address
					//wait until element is available	
						WebElement elCopy = driver.findElement(By.xpath(xpathCopy));
						boolean hasOpacity = false;
						//what if Opacity is not present in CSS?
//						System.out.println(elCopy.toString() + " opacity: " + elCopy.getCssValue("opacity"));
						if(elCopy.getCssValue("opacity").equals("1")){
							hasOpacity = true;
						}
						return hasOpacity;				
				}
				});	
			}
			
			//wait for element to be selectable
			if(checkVisibilityBySelectability) {
				wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
			}
			
			//wait for element to be visible
			if(checkVisibilityByVisibility) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			}

			//wait for element to be invisible
			if(checkInvisibilityByInvisibility) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			}
			
			if(checkInvisibilityByOpacity) {
				final String xpathCopy = xpath;
				wait.until(new Function<WebDriver, Boolean>() 
				{
					public Boolean apply(WebDriver driverCopy) {//shallow copy of address
					//wait until element is available	
						WebElement elCopy = driver.findElement(By.xpath(xpathCopy));
						boolean hasOpacity = false;
						//what if Opacity is not present in CSS?
//						System.out.println(elCopy.toString() + " opacity: " + elCopy.getCssValue("opacity"));
						if(elCopy.getCssValue("opacity").equals("0")){
							hasOpacity = true;
						}
						return hasOpacity;				
				}
				});	
			}
			
			//wait for document to be ready
			if(checkDocumentReady) {
				waitJse.until(new Function<JavascriptExecutor, Boolean>() 
				{
					public Boolean apply(JavascriptExecutor jseCopy) {
						boolean scrollStatus = jseCopy.executeScript("return document.readyState").toString().equals("complete");
						boolean jqueryStatus = (boolean)jseCopy.executeScript("return jQuery.active == 0");
						if(scrollStatus&&jqueryStatus) {
//							System.out.println("Finally done scrolling: " + getPageYOffset());
							return true;
						}else {
//							System.out.println("Not done scrolling: " + getPageYOffset());
							return false;
						}
					}
				});	
			}
			
			if(willCheckCssAttributeValuePair) {
				final String xpathCopy = xpath;
				final String cssAttributeCopy = cssAttributeValuePair;
				wait.until(new Function<WebDriver, Boolean>() 
				{
					public Boolean apply(WebDriver driverCopy) {//shallow copy of address
					//wait until element is available	
						WebElement elCopy = driverCopy.findElement(By.xpath(xpathCopy));
						boolean metCssCriteria = false;
						String[] cssArray = new String[3];
						cssArray = cssAttributeCopy.split(":");
						String attr = cssArray[0];
						String op = cssArray[1];
						String expectedValue = cssArray[2];
						//what if Opacity is not present in CSS?
						String actualValue = elCopy.getCssValue(attr);
						if(attr.equals("color")||attr.equals("background-color")) {
							actualValue = org.openqa.selenium.support.Color.fromString(elCopy.getCssValue(attr)).asHex();
						}else if(expectedValue.contains("scale")) {
							expectedValue=convertCssScaleToMatrix(expectedValue);
						}
						System.out.println(elCopy.toString() + attr + " " + op + " " + expectedValue + " (expected): " + actualValue + " (actual)");

						if(op.equals("!=")) {							
							if(!actualValue.equals(expectedValue)) {
								metCssCriteria = true;
							}
						}else if(op.equals("==")) {
							if(actualValue.equals(expectedValue)){
								metCssCriteria = true;
							}
						}
						return metCssCriteria;				
				}
				});	
			}
			
			if(willCheckJavascriptResult) {
				
				final String javascriptExecutorStringCopy = javascriptExecutorString;
				waitJse.until(new Function<JavascriptExecutor, Boolean>() 
				{					
					public Boolean apply(JavascriptExecutor jseCopy) {
						boolean javascriptStatus = false;
						if(javascriptExecutorStringCopy.equals("return document.readyState")) {
							javascriptStatus = jseCopy.executeScript("return document.readyState").toString().equals("complete");
						}else if (javascriptExecutorStringCopy.equals("return jQuery.active == 0")) {
							javascriptStatus = (boolean)jseCopy.executeScript("return jQuery.active == 0");
						}else {
							//this is untested, and will have to catch a number of other common results that gets returned from running Javascript
							javascriptStatus = (boolean)jseCopy.executeScript(javascriptExecutorStringCopy);
						}
						return javascriptStatus;
					}
				});	
			}			
			
			if(willCheckExpectedCondition) {
				expectedConditionType = expectedConditionType.toLowerCase();
				switch(expectedConditionType) {
					case "":
						System.out.println("Expected Condition Type is empty");
						break;
					case "elementtobeselected":
						wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
						break;
					case "elementtobeclickable":
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
						break;
					case "visibilityofelementlocated":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
						break;
					case "invisibilityofelementlocated":
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
						break;
					default:
						System.out.println("Unable to determine expected condition type: " + expectedConditionType);
						break;
				}
			}
			if(willVerifyResultingAddress) {
				System.out.println("willVerifyResultingAddress");
				Wait<String> waitString = new FluentWait<String>(resultingAddress)					
						.withTimeout(waitFactor, TimeUnit.SECONDS)
					    .pollingEvery((long) .5, TimeUnit.SECONDS)
					    .ignoring(NoSuchElementException.class);
				
				/***
				 * With will generate new tab opening, we need to switch tabs to verify the address, then close it, and switch back
				 */
				if(willGenerateNewTab) {
					System.out.println("willGenerateNewTab");
					final String currentWindow = driver.getWindowHandle();
					ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
					final String newWindow = windowHandles.get(windowHandles.size()-1);
					driver.switchTo().window(newWindow);
					waitString.until(new Function<String, Boolean>(){
						public Boolean apply(String address) {						
							Boolean rightAddress = driver.getCurrentUrl().equals(resultingAddress);
							if(rightAddress) {
								System.out.println("window: " + currentWindow + " | newwindow: " + newWindow);
								driver.close();
								driver.switchTo().window(currentWindow);
								System.out.println("window: " + driver.getWindowHandle());
								return true;
							}else {
								return false;
							}
						}
					});
				}else {
					System.out.println("willVerifyResultingAddress: " + willVerifyResultingAddress + " | willGenerateNewTab: " + willGenerateNewTab);
					waitString.until(new Function<String, Boolean>(){
						public Boolean apply(String address) {						
							return (driver.getCurrentUrl().equals(resultingAddress));
						}
					});
				}


			}
			

			
			//wait for ...

			xpath = "";
			willWaitFor = false;
		}

	}
	
	/***
	 * Expects a string like the format scale(xxxx.xxxx, xxxx.xxxx) and converts to matrix(xxxx.xxxx, 0.0, xxxx.xxxx, 0.0)
	 * scale(1.2, 1.2) -> matrix(1.2, 0, 0, 1.2, 0, 0)
	 * @param scaleStr
	 * @return
	 */
	protected String convertCssScaleToMatrix(String scaleStr) {
		String matrixStr = "";
		String tempStr = scaleStr.substring(6, scaleStr.length()-1);
		String[] tempArray = new String[2];
		tempStr = tempStr.replaceAll("\\s","");
		tempArray = tempStr.split(",");
		matrixStr = "matrix("+tempArray[0]+", 0, 0, "+tempArray[1]+", 0, 0)";
		return matrixStr;
	}
	
	//Click and then wait for transition to end
	public void clickElementByXpath() {
//		System.out.println("clickElementByXpathAndWaitForReadyState: " + xpath);
//		JavascriptExecutor jse = (JavascriptExecutor)driver;		
		if(xpath.length()>0 && willClick) {
//			System.out.println("actually clickElementByXpathAndWaitForReadyState(String xpath): xpath = " + xpath);
			WebElement element = driver.findElement(By.xpath(xpath));			
			addActionToNavigationPathAlternate(xpath);
			element.click();		
//			waitForElement = getElementTransitionLength(element);
//			driver.manage().timeouts().implicitlyWait(waitForElement, TimeUnit.SECONDS);//doesn't pause
//			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);//doesn't pause
//			new WebDriverWait(driver, waitForElement*3).until(new ExpectedCondition<Boolean>() {
//				@Override
//				public Boolean apply(WebDriver webDriver) {
//					return false;//force the timeout in WebDriverWait(driver, timeout)
//				}
//			});
			xpath = "";
			willClick=false;
		}
		else {
			//don't click anything
		}

	}

	/***
	 * 
	 * @param newXpath
	 * @return String with / replaced by ^
	 * Since these xPaths would be used as file-names, we need to use a different divider, and just convert it at a later time
	 * There's risk in the re-conversion, but since it's minimal, it will be considered on a case by case basis (i.e. Just change the xpath value to not have a ^)
	 */
	public String convertXpathToNotMakeFolders(String newXpath) {
		return newXpath.replace('/', '^');
	}
	
	/***
	 * 
	 * @param newXpath
	 * Calls convertXpathToNotMakeFolders and finally adds to navigationPath
	 */
	public void addXpathToNavigationPath(String newXpath) {
		String convertedXpath = convertXpathToNotMakeFolders(newXpath);
		navigationPath.add("^" + convertedXpath);
	}
	
	public void addActionToNavigationPath(String action) {
		navigationPath.add("^" + action);
	}
	
	public String getNavigationPath() {
		String str = "";
		for(int i=0; i<navigationPath.size(); i++) {
			str = str+navigationPath.get(i);			
		}
		return str;
	}
	
	public void addActionToNavigationPathAlternate(String action) {
		//instead of each cell containing an action
		//have each cell contain the string of actions to get to that point
		//use the index for the eventID
		//then output the list into a log file, with ids
		//later on, have the output in realtime
		String str;
		int size = navigationPathAlternate.size();
		if(size==0) {
			str = action;
			navigationPathAlternate.add("\r\n\t"+str);		
			populateLogFile(navigationPathAlternate);
		}else {
			str = navigationPathAlternate.get(size-1)+"\r\n\t"+action;
			navigationPathAlternate.add(str);
			populateLogFile(navigationPathAlternate);
		}
	}
	
	public String getNavigationPathAlt() {
		int size = navigationPathAlternate.size();
		return navigationPathAlternate.get(size-1);
	}
	
	public int getNavigationPathAltEventId() {
		int eventId = navigationPathAlternate.size()-1;
		System.out.println("getNavigationPathAltEventId(): " + (eventId));
		return eventId;
	}

//	public void clickElementById() {
////		System.out.println("clickElementById(String id): elementId = " + elementId);
//		WebElement element = driver.findElement(By.id(elementId));
//		element.click();		
//	}
		
	public boolean checkIfElementIsInsideWindowBounds(WebDriver driverCopy) {
//		System.out.println("checkIfElementIsInsideWindowBounds");
		boolean inside = false;
		boolean horizontallyContained = false;
		boolean verticallyContained = false;
		WebElement el = driverCopy.findElement(By.xpath(xpath));
		Dimension windowDimension = driverCopy.manage().window().getSize();
		float elementX = el.getRect().getX();
		float elementY= el.getRect().getY();
		float elementWidth = el.getRect().getWidth();
		float elementHeight = el.getRect().getHeight();
		
		if(elementX<0 || elementX+elementWidth>windowDimension.width) {
			horizontallyContained = false;
		}else {
			horizontallyContained = true;
		}
		
		if(elementY<0 || elementY+elementHeight>windowDimension.height) {
			verticallyContained = false;
		}else {
			verticallyContained = true;
		}
		
		if(horizontallyContained && verticallyContained) {
			inside = true;
		}		
		
		return inside;
	}
	
	public boolean checkIfElementHasNonZeroWidth(WebDriver driverCopy) {
		
		boolean nonzero = false;
		WebElement el = driverCopy.findElement(By.xpath(xpath));
		String width = el.getCssValue("width");
		String trimmedWidth = width.substring(0,width.length()-2);
		long elementWidth = (long)Double.parseDouble(trimmedWidth);
//		System.out.println("checkIfElementHasNonZeroWidth: " + xpath + ": " + elementWidth);
		if(elementWidth>0) {
			nonzero = true;
		}
		return nonzero;		
	}
	
	/***
	 * Uses enableScreenshot global variable to enable toggling in Fitnesse
	 * Uses takingBaselineSet global variable to toggle baseline setup in Fitnesse
	 * Takes a screenshot of current webdriver's view
	 * Creates a folder with the instance's start time
	 * Calls compareBaseLineWithImmediateScreenshot()
	 * Updates baseline OR compares current with baseline
	 * Outputs into C:\\baseline or C:\\current\\-instanceStartTime- (for current and diffs)
	 */
	@SuppressWarnings("unused")
	public void takeScreenshotAndGenerateDiff() {
//		if(enableScreenshot) {
			//wait for page to be in a ready state and jquery.active to be 0
//			waitForPageStability();
//			waitForPageStability2(xpath);
//			waitForPageStability3();
//			id++;
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Long timestamp = (new Timestamp(System.currentTimeMillis())).getTime();
//			System.out.println("Taking screenshot at: "+timestamp);
//			String navPath = getNavigationPath();
//			String navPath = getNavigationPathAlt();
			String navId = getNavigationPathAltEventId()+"";
//			String newFilename = "c:\\baseline\\" + navPath + ".png";
//			String currentFilename = "c:\\current\\"+instanceStartTime+"\\"+ navPath +".png";
//			String currentDiffFilename = "c:\\current\\"+instanceStartTime+"\\"+ navPath +"_diff.png";			
			String newFilename = "c:\\baseline\\" + navId + ".png";
			String currentFilename = "c:\\current\\"+instanceStartTime+"\\"+ navId +".png";
			String currentDiffFilename = "c:\\current\\"+instanceStartTime+"\\"+ navId +"_diff.png";	
			BufferedImage currentShot;
			BufferedImage baselineShot;
			
			if(baselineSet) {
				try {
					FileUtils.copyFile(screenshotFile, new File(newFilename));
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}else {
				try {
					FileUtils.copyFile(screenshotFile, new File(currentFilename));					
					currentShot = ImageIO.read(screenshotFile);
					File temp = new File(newFilename);
					if(temp.exists()) {
						baselineShot = ImageIO.read(temp);
					}else {
						baselineShot = null;
					}
					//generates diff
					compareBaseLineWithImmediateScreenshot(baselineShot,currentShot,currentDiffFilename);

				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
//		}
		//The below line should consistently default takeScreenshot as false
//		enableScreenshot = false;
	}
	
	/***
	 * Uses sikuli to wait for an image to appear before continuing with baseline/current screenshots and comparison
	 * Wait length is currently hard coded as 6 seconds, with polling occuring every 1 second.
	 * @return true if found, false if not found
	 */
	public boolean verifyImageBySikuli(){
		boolean present = false;
		if(imageFileLocation.length()>0) {			
			Wait<String> wait = new FluentWait<String>(imageFileLocation)
				    .withTimeout(6, TimeUnit.SECONDS)
				    .pollingEvery(1, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
			
			try {
				initializeTestFixtureSikuli();
				wait.until(testSikuliFixture);
				present = true;
			} catch (FindFailed e) {
//				e.printStackTrace();
				//if we couldn't find the image, return false for verify image by sikuli, instead of just exiting out with an exception
				present = false;
			}					
		}
		imageFileLocation = "";
		return present;	
	}
	
	/***
	 * Used to separate setting up the wait and initializing the wait function
	 * @param testSikuliFixture global variable, 
	 * @throws FindFailed
	 */
	public void initializeTestFixtureSikuli() throws FindFailed{
		final Region r = s;
		testSikuliFixture = new Function<String,Boolean>() {
			public Boolean apply (String imageFileLocation){
//				try {
//					if(r.find(imageFileLocation)!=null) {
					if(r.exists(imageFileLocation)!=null) {
						return true;
					}else {
						return false;
					}
//				} 
//				catch (FindFailed e) {
////					try {
////						throw new FindFailed(imageFileLocation + " not found.");
////					} catch (FindFailed e1) {
//						return false;
////					}
//				}				
			}	
		};	
	}
	
	/***
	 * Calls getDifferenceImage() and writes the image into an outputfile	 * 
	 * @param baselineImage
	 * @param newImage
	 * @param someLocation
	 */
	public void compareBaseLineWithImmediateScreenshot(BufferedImage baselineImage, BufferedImage newImage, String diffFileName) {
		BufferedImage diff = getDifferenceImage(baselineImage, newImage);
		File outputfile = new File(diffFileName); 
		try {
			ImageIO.write(diff, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/***
	 * Found from https://stackoverflow.com/questions/25022578/highlight-differences-between-images/25151302
	 * Compares two images' pixels and outputs a gray scale of the difference
	 * @param img1
	 * @param img2
	 * @return
	 */
	@SuppressWarnings("unused")
	public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
	    BufferedImage outImg;
	    int width2 = img2.getWidth(); // take no arguments
	    int height2 = img2.getHeight();
	    //if there's no baseline image
	    if(img1==null) {
	    	outImg = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
	        for(int i=0; i<height2; i++) {
	        	for(int j=0; j<width2; j++) {
	        		int lightGrey =  Color.LIGHT_GRAY.getRGB();
	        		outImg.setRGB(j, i, lightGrey);	   
	        	}
	        }
	    }else {
		    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
		    int height1 = img1.getHeight();
	    	
	    	//if the screenshot image does not have the same dimensions as the baseline image
	    	if ((width1 != width2) || (height1 != height2)) {
	    		//use the smaller of each dimension to create the outimg
	    		int smallerWidth = Math.min(width1,  width2);
	    		int smallerHeight = Math.min(height1, height2);
	    		outImg = new BufferedImage(smallerWidth, smallerHeight, BufferedImage.TYPE_INT_RGB);
		        System.err.println("Error: Images dimensions mismatch");
		        //change diff output to grey
		        for(int i=0; i<smallerHeight; i++) {
		        	for(int j=0; j<smallerWidth; j++) {
//		        		int white = img1.getRGB(j,i)*100;
		        		int blue =  Color.BLUE.getRGB();
		        		outImg.setRGB(j, i, blue);	   
		        	}
		        }
		    //finally, if the baseline image exists and the screenshot image has the same dimensions.
		    }else {
		    	outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
			    // Modified - Changed to int as pixels are ints
			    int diff;
			    int result; // Stores output pixel
			    for (int i = 0; i < height1; i++) {
			        for (int j = 0; j < width1; j++) {
			            int rgb1 = img1.getRGB(j, i);
			            int rgb2 = img2.getRGB(j, i);
			            int r1 = (rgb1 >> 16) & 0xff;
			            int g1 = (rgb1 >> 8) & 0xff;
			            int b1 = (rgb1) & 0xff;
			            int r2 = (rgb2 >> 16) & 0xff;
			            int g2 = (rgb2 >> 8) & 0xff;
			            int b2 = (rgb2) & 0xff;
			            diff = Math.abs(r1 - r2); // Change
			            diff += Math.abs(g1 - g2);
			            diff += Math.abs(b1 - b2);
			            diff /= 3; // Change - Ensure result is between 0 - 255
			            // Make the difference image gray scale
			            // The RGB components are all the same
			            result = (diff << 16) | (diff << 8) | diff;
			            outImg.setRGB(j, i, result); // Set result
			        }
			    }
		    }
	    }
	    

	    

	    // Now return
	    return outImg;
	}
	
	//needs to be long to be run in another javascriptexecutor script call
	protected Long getPageYOffset() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		//Below code doesn't seem to be having issues with exceptions
		//The parselong(sVal, 10) hopefully deals with our NumberFormatException
		Object oVal = executor.executeScript("return window.pageYOffset;");
		String sVal = oVal.toString();
		Long lVal = Long.parseLong(sVal, 10);
		return lVal;
	}
	
	protected Long getPageHeight() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Long value = (long) executor.executeScript("return document.body.offsetHeight;");
		return value;
	}
	
	public void scrollDownXFullScreenPageHeightAndWait() {
//		System.out.println("scrollDownXFullScreenPageHeightAndWait: " + pageScrollPosition);
		if(pageScrollPosition.length()>0) {
			addActionToNavigationPathAlternate("s"+pageScrollPosition);
			JavascriptExecutor jseScroll = (JavascriptExecutor)driver;			
			String scrollheight = (getPageHeight()*(Integer.parseInt(pageScrollPosition)))+"";
//			System.out.println("Scrolling start: " + getPageYOffset());
//			jseScroll.executeScript("window.scrollBy(0,"+scrollheight+")", "");
			jseScroll.executeScript("window.scroll(0,"+scrollheight+")", "");
			
			//the reset to "" is needed so that we can switch this functionality on and off in Fitnesse
			pageScrollPosition = "";
		}
		
		
	}

	//Needed to create an instance for Fitnesse to work with
	public static void main(String[] args) {		
		FitnesseTestFixture temp = new FitnesseTestFixture();
	}

}
