package com.inhance.testFramework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
 * Should be very shallow
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
	
	public boolean enableScreenshot;
	
	public Function<WebElement,Boolean> testWebFixture;
	public Function<String, Boolean> testSikuliFixture;
	
	public String imageFileLocation;
	public String instanceStartTime;
	
	public boolean takingBaselineSet;
	
	String currentDiffFilename;
	
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
	

//	@BeforeClass(alwaysRun = true)
	public boolean initialize(String home) throws Exception {
//	public void setUp() throws Exception {	
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
//	    baseUrl = "http://www.google.com/";
	    baseUrl = home;
	    navigateByAddress(baseUrl);
	    driver.manage().window().maximize(); 
//	    driver.manage().window().setSize(new Dimension(1024, 768));
	    wait = new WebDriverWait(driver, 20);
	    
	    //make sure to right click the resources\images folder, select Build Path -> Include
	    ImagePath.add(SimpleFramework.class.getCanonicalName()+"/images");
	    enableScreenshot = false;
	    navigationPath = new ArrayList<String>();
	    instanceStartTime = (new Timestamp(System.currentTimeMillis())).getTime()+"";
	    
        File baselineDirectory = new File("C:\\baseline");
        if (!baselineDirectory.exists()) {
        	baselineDirectory.mkdirs();
        }
        
        File currentDirectory = new File("C:\\current"+instanceStartTime);
        if (!currentDirectory.exists()) {
        	currentDirectory.mkdirs();
        }
	    
	    return true;
	}
	
//	@AfterClass(alwaysRun = true)
//	public void tearDown() throws Exception {
//		driver.navigate().to("about:config");
//		driver.navigate().to("about:blank");
//		driver.quit();
////		String verificationErrorString = verificationErrors.toString();
////		if (!"".equals(verificationErrorString)) {
////			fail(verificationErrorString);
////		}
//	}
	
	public boolean simpleTest() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
//	
//	public void imageButtonTest() {
//		clickElementBySikulixReferenceImageInLocalArea("Im feeling lucky button.PNG", s);
//	}
//	
	public boolean navigateByAddress(String address) {
		System.out.println("navigateByAddress(String address): address = " + address); 
		driver.get(address);
		return true;
	}
	
	public void navigateByGlobalAddress() {
//		System.out.println("navigateByGlobalAddress(String address): address = " + globalAddress); 
		if(globalAddress.length()>0) {
			driver.get(globalAddress);
		}else {
			
		}
	}
//	
	public void clickElementByXpath() {
		if(xpath.length()>0) {
			System.out.println("clickElementByXpath(String xpath): xpath = " + xpath);
			WebElement element = driver.findElement(By.xpath(xpath));
			addXpathToNavigationPath(xpath);
			element.click();			
		}else {
			
		}
	}
//	
//	public void clickElementById(String id) {
//		System.out.println("clickElementById(String id): id = " + id);
//		WebElement element = driver.findElement(By.id(id));
//		element.click();		
//	}
	
	public void addXpathToNavigationPath(String newXpath) {
		//Since these xPaths would be used as file-names, we need to use a different divider, and just convert it at a later time
		//There's risk in the re-conversion, but since it's minimal, it will be considered on a case by case basis (i.e. Just change the xpath value to not have a ^)
//		newXpath.replace("\\", "^");
		navigationPath.add(newXpath);
	}
	
	public String getNavigationPath() {
		String str = "";
		for(int i=0; i<navigationPath.size(); i++) {
			str = str+navigationPath.get(i);			
		}
		return str;
	}

	public void clickElementById() {
		System.out.println("clickElementById(String id): elementId = " + elementId);
		WebElement element = driver.findElement(By.id(elementId));
		element.click();		
	}
//	
//	public void clickElementBySikulixReferenceImage(String imageFileLocation) {
//		System.out.println("clickElementBySikulixReferenceImage(String imageFileLocation): imageFileLocation = " + imageFileLocation);
//		try {
//			s.click(imageFileLocation, 0);
//		} catch (FindFailed e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void clickElementBySikulixReferenceImageInLocalArea(String imageFileLocation, Region region) {
//		System.out.println("clickElementBySikulixReferenceImage(String imageFileLocation): \nimageFileLocation = " + imageFileLocation + "\nregion = " + region.toString());
//		try {
//			region.click(imageFileLocation, 0);
//		} catch (FindFailed e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public Region generateRegion(int leftMostXCoordinate, int topMostYCoordinate, int regionWidth, int regionHeight) {
//		Region r = new Region(leftMostXCoordinate, topMostYCoordinate, regionWidth, regionHeight);
//		return r;
//	}	
	
	public void takeScreenshot() {
		if(enableScreenshot) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Long timestamp = (new Timestamp(System.currentTimeMillis())).getTime();
			System.out.println("Taking screenshot at: "+timestamp);
			String navPath = getNavigationPath();
			String newFilename = "c:\\baseline\\" + navPath + ".png";
			String currentFilename = "c:\\current"+instanceStartTime+"\\"+ navPath +".png";
			String currentDiffFilename = "c:\\current"+instanceStartTime+"\\"+ navPath +"_diff.png";
			BufferedImage currentShot;
			BufferedImage baselineShot;
			
			if(takingBaselineSet) {
				try {
					FileUtils.copyFile(scrFile, new File(newFilename));
//					FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot"+ timestamp +".png"));
				} catch (IOException e) {
					e.printStackTrace();
//					Assert.fail();
				}	
			}else {
				try {
					FileUtils.copyFile(scrFile, new File(currentFilename));
					currentShot = ImageIO.read(scrFile);
					baselineShot = ImageIO.read(new File(newFilename));
					compareBaseLineWithImmediateScreenshot(baselineShot,currentShot,currentDiffFilename);

				} catch (IOException e) {
					e.printStackTrace();
//					Assert.fail();
				}	
			}
			
		}
		//The below line should consistently default takeScreenshot as false
		enableScreenshot = false;
	}
	
	public String image() {
//		String htmlImage = "<div><img src=\"" + "https://codecademy-production.s3.amazonaws.com/profile_thumbnail/5168e70f720111040f002066_65523160.jpg?AWSAccessKeyId=AKIAJKZ7QVQFD72XNJ3Q&Expires=1525740677&Signature=t3Ui9kI8MyA%2B%2Fyj8vM38QIIhUlg%3D" + "\"/></div>";
//		String htmlImage = "<div><img src=\"" + "http://localhost/files/HamburgerMenuModified.PNG" + "\"/></div>";
		String htmlImage="";
		String elementId="1";
		htmlImage = "<div><img id= "+elementId+" src=\"" + "" + "\"/></div>";
		return htmlImage;
	}
	
	
	/***
	 * To get away from using a local webserver, I can try using javascript to create IMG element, then appent the IMG to Fitnesse page
	 * For fitnesse, we'll need to have 2 columns. 
	 * The first column is to set the ID of the cell
	 * The second column is to call the below JS method to populate the above mentioned cell
	 */
	public void displayDiff() {
		
	}
	

	
	public void clickElementByXpathPreparation(String xpath) {
		WebElement el = driver.findElement(By.xpath(xpath));
		Wait<WebElement> wait = new FluentWait<WebElement>(el)
			    .withTimeout(6, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);

		initializeTestFixtureWeb(testWebFixture);
		wait.until(testWebFixture);//automatically feeds the parameter used to initialize wait into the testFixture	
	}
	
	public void initializeTestFixtureWeb(Function<WebElement,Boolean> testFixture) {	
		testFixture = new Function<WebElement,Boolean>(){
			public Boolean apply(WebElement el) {//shallow copy of address
				//wait until element is available					
				return el.isDisplayed();					
			}
		};			
	}
	
	public boolean verifyImageBySikuli(){
		boolean present = false;
		if(imageFileLocation.length()>0) {			
			Wait<String> wait = new FluentWait<String>(imageFileLocation)
				    .withTimeout(6, TimeUnit.SECONDS)
				    .pollingEvery(1, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
			
			try {
				initializeTestFixtureSikuli(testSikuliFixture);
				wait.until(testSikuliFixture);
				present = true;
			} catch (FindFailed e) {
//				e.printStackTrace();
				//if we couldn't find the image, return false for verify image by sikuli, instead of just exiting out with an exception
				present = false;
			}					
		}
		return present;	
	}
	
	public void initializeTestFixtureSikuli(Function<String,Boolean> testFixture) throws FindFailed{
		final Region r = s;
		testSikuliFixture = new Function<String,Boolean>() {
			public Boolean apply (String imageFileLocation){
				try {
					if(r.find(imageFileLocation)!=null) {
						return true;
					}else {
						return false;
					}
				} 
				catch (FindFailed e) {
//					e.printStackTrace();
					try {
						throw new FindFailed(imageFileLocation + " not found.");
					} catch (FindFailed e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						return false;
					}
				}				
			}	
		};	
	}
	
	//output the diff into the proper location
	public void compareBaseLineWithImmediateScreenshot(BufferedImage baselineImage, BufferedImage newImage, String someLocation) {
		BufferedImage diff = getDifferenceImage(baselineImage, newImage);
		File outputfile = new File(someLocation); 
		try {
			ImageIO.write(diff, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	//generate the diff
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
	

	
	public static void main(String[] args) {		
		FitnesseTestFixture temp = new FitnesseTestFixture();
	}
	

	
	
}
