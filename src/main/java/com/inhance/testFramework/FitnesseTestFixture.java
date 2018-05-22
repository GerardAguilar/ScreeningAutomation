package com.inhance.testFramework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import junit.framework.Assert;

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
	
	public boolean enableScreenshot;
	
	public Function<WebElement,Boolean> testWebFixture;
	public Function<String, Boolean> testSikuliFixture;
	
	public String imageFileLocation;
	public String instanceStartTime;
	
	public boolean takingBaselineSet;
	public boolean makeALogFile;
	
	public int pageScrollPosition;
	
	String currentDiffFilename;
	int id;
//	protected String fullScreenPageHeight = "1086";
	public int fullScreenPageHeight = 1086;
	
	Long waitForElement;
	//waitFactor multiplies the transition-duration of the element we're currently interacting with
	float waitFactor;
	//how much polling to do in the course of the wait 
	float pollingFactor;
	
	public void setMakeALogFile(boolean temp) {
		makeALogFile = temp;
	}
	public boolean getMakeALogFile() {
		return makeALogFile;
	}
	
	public void setPageScrollPosition(int temp){
		pageScrollPosition = temp;
	}
	public int getPageScrollPosition() {
		return pageScrollPosition;
	}
	
	public void setTakingBaselineSet(boolean temp) {
		takingBaselineSet = temp;
	}
	
	public boolean getTakingBaselineSet() {
		return takingBaselineSet;
	}
	
	public void setImageFileLocation(String temp) {
		imageFileLocation = temp;
	}
	public String getImageFileLocation() {
		return imageFileLocation;		
	}
		
	public void setEnableScreenshot(boolean temp) {
		enableScreenshot = temp;
	}
	
	public boolean getEnableScreenshot() {
		return enableScreenshot;
	}
	
	public FitnesseTestFixture(){	
			try {
				initialize("about:blank");
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	

	public boolean initialize(String home) throws Exception {
		boolean setupFirefoxDriver = false;
		boolean setupChromeDriver = true;
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
	        geckodriver = new File("Driver" + "\\geckodriver.exe"); 
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
		}

	    if(setupChromeDriver) {
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\GoogleChromePortable\\GoogleChromePortable.exe");
			options.addArguments("disable-infobars");
			options.addArguments("--allow-file-access-from-files");
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");              
			driver = new ChromeDriver(options);
	    }
	    
//	    driver.manage().window().setSize(new Dimension(1024, 768));
//	    baseUrl = "http://www.google.com/";
	    baseUrl = home;
	    navigateByAddress(baseUrl);
	    driver.manage().window().maximize(); 
	    wait = new WebDriverWait(driver, 20);
	    //make sure to right click the resources\images folder, select Build Path -> Include
	    ImagePath.add(SimpleFramework.class.getCanonicalName()+"/images");
	    enableScreenshot = false;
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
	    
        id = 0;
        pageScrollPosition = -1;
        waitForElement = (long) 0;
        waitFactor = 10;
        pollingFactor = 1; //the bigger the value the faster the polling
        
	    return true;
	}
	
////	@AfterClass(alwaysRun = true)
//	public void tearDown() throws Exception {
//		createLogFile();
//		driver.quit();
//	}
	
	public void createLogFile() {
		String appendedIndex = "";
		if(makeALogFile) {
	        try {
	        	String loc ="";
	        	if(takingBaselineSet) {
	        		loc = "C:\\baseline\\log.txt";
	        	}else {
	        		loc = "C:\\current\\"+instanceStartTime+"\\log.txt";
	        	}
				PrintWriter out = new PrintWriter(loc);
		        for(int i=0; i<navigationPathAlternate.size(); i++) {
		        	appendedIndex = String.format("%04d", i);
		        	out.println(appendedIndex + ": " + navigationPathAlternate.get(i));
		        }
		        out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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

	/***
	 * Clicks an xpath element AND stores the xpath for navigational purposes
	 * TODO: Wouldn't this cause issues when branching out? Or keep track of entire path instead of branches?
	 */
	public void clickElementByXpath() {
		if(xpath.length()>0) {
//			System.out.println("clickElementByXpath(String xpath): xpath = " + xpath);
			WebElement element = driver.findElement(By.xpath(xpath));			
//			addXpathToNavigationPath(xpath);
			addActionToNavigationPathAlternate(xpath);
			element.click();			
		}else {
			
		}
	}
	
//	public void clickElementByXpathPreparation(String xpath) {
//	WebElement el = driver.findElement(By.xpath(xpath));
//	Wait<WebElement> wait = new FluentWait<WebElement>(el)
//		    .withTimeout(6, TimeUnit.SECONDS)
//		    .pollingEvery(1, TimeUnit.SECONDS)
//		    .ignoring(NoSuchElementException.class);
//
//	initializeTestFixtureWeb(testWebFixture);
//	wait.until(testWebFixture);//automatically feeds the parameter used to initialize wait into the testFixture	
//}
//
	
	public long getElementTransitionLength(WebElement el) {
//		return Long.parseLong(el.getCssValue("transition"));
//		return Long.parseLong(el.getCssValue("-webkit-transition"));
		String cssValue = el.getCssValue("transition-duration");
		String trimmedCssValue = cssValue.substring(0, cssValue.length()-1);
		if(trimmedCssValue.length()>0) {
//			return Long.valueOf(trimmedCssValue,10);//java.lang.NumberFormatException: For input string: "0.1"
//			return Long.parseLong(trimmedCssValue,10);//java.lang.NumberFormatException: For input string: "0.1"
//			System.out.println("Waiting for " + el.toString() + " for "+trimmedCssValue + "s");
			return (long)Double.parseDouble(trimmedCssValue);
		}else {
			return (long) 0;
		}
		
	}
	public boolean waitForElementToAppear() {
		boolean appeared = false;
		String xpathCopy = xpath;
		WebElement el = driver.findElement(By.xpath(xpath));
		Wait<WebElement> wait = new FluentWait<WebElement>(el)
			    .withTimeout(6, TimeUnit.SECONDS)
			    .pollingEvery(2, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);

		wait.until(new Function<WebElement, Boolean>() 
		{
			public Boolean apply(WebElement elCopy) {//shallow copy of address
			//wait until element is available		
				boolean isDisplayed = elCopy.isDisplayed();
				boolean hasOpacity = false;
				//what if Opacity is not present in CSS?
				if(elCopy.getCssValue("opacity")=="1"){
					hasOpacity = true;
				}
				return isDisplayed&&hasOpacity;				
		}
		});	
		return appeared;		
	}
	
//public void initializeTestFixtureWeb(Function<WebElement,Boolean> testFixture) {	
//	testFixture = new Function<WebElement,Boolean>(){
//		public Boolean apply(WebElement el) {//shallow copy of address
//			//wait until element is available					
//			return el.isDisplayed();					
//		}
//	};			
//}
	
	//Wait for image to appear
	public void locateElementInPageByXpathAndWaitForNonZeroWidth() {
		waitForPageStability3();
	}
	
	//Click and then wait for transition to end
	public void clickElementByXpathAndWaitForReadyState() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;		
		if(xpath.length()>0) {
			System.out.println("clickElementByXpathAndWaitForReadyState(String xpath): xpath = " + xpath);
			WebElement element = driver.findElement(By.xpath(xpath));			
			addActionToNavigationPathAlternate(xpath);
			element.click();		
			waitForElement = getElementTransitionLength(element);
//			driver.manage().timeouts().implicitlyWait(waitForElement, TimeUnit.SECONDS);//doesn't pause
//			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);//doesn't pause
//			new WebDriverWait(driver, waitForElement*3).until(new ExpectedCondition<Boolean>() {
//				@Override
//				public Boolean apply(WebDriver webDriver) {
//					return false;//force the timeout in WebDriverWait(driver, timeout)
//				}
//			});
		}else {
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
			navigationPathAlternate.add(str);			
		}else {
			str = navigationPathAlternate.get(size-1)+action;
			navigationPathAlternate.add(str);
		}
	}
	
	public String getNavigationPathAlt() {
		int size = navigationPathAlternate.size();
		return navigationPathAlternate.get(size-1);
	}
	
	public int getNavigationPathAltEventId() {
		int size = navigationPathAlternate.size();
		return size-1;
	}

	public void clickElementById() {
//		System.out.println("clickElementById(String id): elementId = " + elementId);
		WebElement element = driver.findElement(By.id(elementId));
		element.click();		
	}
	
	public void waitForPageStability3() {
		System.out.println("waitForPageStability3");	
		if(xpath.length()>0) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)			    
					.withTimeout(4, TimeUnit.SECONDS)
				    .pollingEvery((long) .5, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
			
			wait.until(new Function<WebDriver, Boolean>()
			{
				public Boolean apply(WebDriver driverCopy) {		
					boolean nonZeroWidth = checkIfElementHasNonZeroWidth(driverCopy);//never seems to be true
					
					if(nonZeroWidth) {
						return true;
					}else {
						return false;
					}
				}
			});
		}

	}
	
	public boolean checkIfElementIsInsideWindowBounds(WebDriver driverCopy) {
		System.out.println("checkIfElementIsInsideWindowBounds");
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
		System.out.println("checkIfElementHasNonZeroWidth: " + xpath + ": " + elementWidth);
		if(elementWidth>0) {//always seem to be zero (like the cross/hamburger was never pressed.)
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
	public void takeScreenshot() {
		if(enableScreenshot) {
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
			
			if(takingBaselineSet) {
				try {
					FileUtils.copyFile(screenshotFile, new File(newFilename));
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}else {
				try {
					FileUtils.copyFile(screenshotFile, new File(currentFilename));					
					currentShot = ImageIO.read(screenshotFile);
					baselineShot = ImageIO.read(new File(newFilename));
					compareBaseLineWithImmediateScreenshot(baselineShot,currentShot,currentDiffFilename);

				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
		//The below line should consistently default takeScreenshot as false
		enableScreenshot = false;
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
	public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
	    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
	    int width2 = img2.getWidth(); // take no arguments
	    int height1 = img1.getHeight();
	    int height2 = img2.getHeight();
	    if ((width1 != width2) || (height1 != height2)) {
	        System.err.println("Error: Images dimensions mismatch");
	        System.exit(1);
	    }

	    // NEW - Create output Buffered image of type RGB
	    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

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
		if(pageScrollPosition>=0) {

			addActionToNavigationPathAlternate("s"+pageScrollPosition);
			JavascriptExecutor jse = (JavascriptExecutor)driver;

			String scrollheight = (getPageHeight()*pageScrollPosition)+"";
//			System.out.println("Scrolling start: " + getPageYOffset());
			jse.executeScript("window.scrollBy(0,"+scrollheight+")", "");
			
//			Wait<JavascriptExecutor> wait = new FluentWait<JavascriptExecutor>(jse)
//				    .withTimeout(6, TimeUnit.SECONDS)
//				    .pollingEvery(2, TimeUnit.SECONDS)
//				    .ignoring(NoSuchElementException.class);
//
//			wait.until(new Function<JavascriptExecutor, Boolean>() 
//			{
//				public Boolean apply(JavascriptExecutor jseCopy) {
//					boolean scrollStatus = jseCopy.executeScript("return document.readyState").toString().equals("complete");
//					boolean jqueryStatus = (boolean)jseCopy.executeScript("return jQuery.active == 0");
//					if(scrollStatus&&jqueryStatus) {
//						System.out.println("Finally done scrolling: " + getPageYOffset());
//						return true;
//					}else {
//						System.out.println("Not done scrolling: " + getPageYOffset());
//						return false;
//					}
//				}
//			});	
			
//			waitForPageStability();
		}
		//the reset to -1 is needed so that we can switch this functionality on and off in Fitnesse
		pageScrollPosition = -1;
	}

	//Needed to create an instance for Fitnesse to work with
	public static void main(String[] args) {		
		FitnesseTestFixture temp = new FitnesseTestFixture();
	}

}
