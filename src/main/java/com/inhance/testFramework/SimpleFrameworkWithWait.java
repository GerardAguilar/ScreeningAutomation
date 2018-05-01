package com.inhance.testFramework;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

//import com.google.common.base.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

public class SimpleFrameworkWithWait {
	protected WebDriver driver;
	protected String baseUrl;
	protected WebDriverWait wait;
	protected Screen s = new Screen();  
	public Function<WebElement,Boolean> testWebFixture;
	public Function<String, Boolean> testSikuliFixture;
//	public SimpleFramework(){	
//
//	}

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {	
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
	    baseUrl = "http://www.inhance.com";
	    navigateByAddress(baseUrl);
	    driver.manage().window().maximize(); 
//	    driver.manage().window().setSize(new Dimension(1024, 768));
	    wait = new WebDriverWait(driver, 20);
	    
	    //make sure to right click the resources\images folder, select Build Path -> Include
	    ImagePath.add(SimpleFrameworkWithWait.class.getCanonicalName()+"/images");
	    

	}
	
//	@AfterClass(alwaysRun = true)
//	public void tearDown() throws Exception {
//		driver.navigate().to("about:config");
//		driver.navigate().to("about:blank");
//		driver.quit();
//	}
	
//	@Test
//	public void simpleTest() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void imageButtonTest() throws FindFailed {
		clickElementBySikulixReferenceImageInLocalAreaPreparation("Capture.PNG", s);
		clickElementBySikulixReferenceImageInLocalArea("Capture.PNG", s);
	}
	
	
	public void navigateByAddress(String address) {
		System.out.println("navigateByAddress(String address): address = " + address); 
		driver.get(address);
	}
	
//	public void fluentWaitElementHasAppeared(Region r) {
//		Wait<Region> wait = new FluentWait<Region>(r)
//			    .withTimeout(6, TimeUnit.SECONDS)
//			    .pollingEvery(1, TimeUnit.SECONDS)
//			    .ignoring(NoSuchElementException.class);
//		wait.until(fixtureFluentWaitElementHasAppeared(r));
//	}
	
//	public boolean fixtureFluentWaitElementHasAppeared(Region r) {
//		if(false) {
//			return true;
//		}else {
//			return false;
//			
//		}
//	}
	
	public void clickElementByXpathPreparation(String xpath) {
		WebElement el = driver.findElement(By.xpath(xpath));
		Wait<WebElement> wait = new FluentWait<WebElement>(el)
			    .withTimeout(6, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);

		initializeTestFixtureWeb(testWebFixture);
		wait.until(testWebFixture);//automatically feeds the parameter used to initialize wait into the testFixture	
	}
	
	public void clickElementBySikulixReferenceImageInLocalAreaPreparation(String imageFileLocation, Region r) throws FindFailed {
		Wait<String> wait = new FluentWait<String>(imageFileLocation)
			    .withTimeout(6, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
		
		initializeTestFixtureSikuli(testSikuliFixture, r);
		wait.until(testSikuliFixture);
	}
	
	public void initializeTestFixtureSikuli(Function<String,Boolean> testFixture, final Region r) throws FindFailed{
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
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						throw new FindFailed(imageFileLocation + " not found.");
					} catch (FindFailed e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return false;
					}
				}				
			}	
		};	
	}
	
	public void initializeTestFixtureWeb(Function<WebElement,Boolean> testFixture) {	
		testFixture = new Function<WebElement,Boolean>(){
			public Boolean apply(WebElement el) {//shallow copy of address
				//wait until element is available					
				return el.isDisplayed();					
			}
		};			
	}
	
	public void clickElementByXpath(String xpath) {
		System.out.println("clickElementByXpath(String xpath): xpath = " + xpath);
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
	}
	
	public void clickElementById(String id) {
		System.out.println("clickElementById(String id): id = " + id);
		WebElement element = driver.findElement(By.id(id));
		element.click();		
	}
	
	public void clickElementBySikulixReferenceImage(String imageFileLocation) {
		System.out.println("clickElementBySikulixReferenceImage(String imageFileLocation): imageFileLocation = " + imageFileLocation);
		try {
			s.click(imageFileLocation, 0);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickElementBySikulixReferenceImageInLocalArea(String imageFileLocation, Region region) {
		System.out.println("clickElementBySikulixReferenceImage(String imageFileLocation): \nimageFileLocation = " + imageFileLocation + "\nregion = " + region.toString());
		try {
			region.click(imageFileLocation, 0);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			System.out.println("clickElementBySikulixReferenceImageInLocalArea(String imageFileLocation, Region region): " + imageFileLocation + " | " + region.toString());
			e.printStackTrace();
		}
	}
	
	public Region generateRegion(int leftMostXCoordinate, int topMostYCoordinate, int regionWidth, int regionHeight) {
		System.out.println("generateRegion(int leftMostXCoordinate, int topMostYCoordinate, int regionWidth, int regionHeight): "
				+ "\nleftMostXCoordinate: " + leftMostXCoordinate
				+ "\ntopMostYCoordinate: " + topMostYCoordinate
				+ "\nregionWidth: " + regionWidth
				+ "\nregionHeight: " + regionHeight);
		
		Region r = new Region(leftMostXCoordinate, topMostYCoordinate, regionWidth, regionHeight);
		return r;
	}	
	
	
}