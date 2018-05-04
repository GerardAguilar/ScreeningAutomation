package com.inhance.testFramework;
import java.io.File;
import java.net.URL;

//import com.google.common.base.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomationAndersenConfigurations {
	WebDriver driver;
	String baseUrl;
	WebDriverWait wait;

	
	public AutomationAndersenConfigurations(){
////	    driver = new FirefoxDriver();
////	    baseUrl = "http://www.inhance.com/";
////	    driver.get(baseUrl);
////	    wait = new WebDriverWait(driver, 20);	    
	}

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
	    baseUrl = "http://www.google.com/";
//	    baseUrl = "http://inhancemetrics.com:3012/test-new.html";
	    driver.get(baseUrl);
	    driver.manage().window().maximize(); 
//	    driver.manage().window().setSize(new Dimension(1024, 768))
	    wait = new WebDriverWait(driver, 20);
	}
	
	@Test
	public void SimpleTest() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void AwningSetup() {
		
	}
	
	
	
	public static void main(String[] args) {
		AutomationAndersenConfigurations temp = new AutomationAndersenConfigurations();		
	}
	

}