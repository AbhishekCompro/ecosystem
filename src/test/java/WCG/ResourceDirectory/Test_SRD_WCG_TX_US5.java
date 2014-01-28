package WCG.ResourceDirectory;


import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import WCG.launchcomponent.*;
import com.compro.automation.core.SetupDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import com.compro.automation.utils.Screenshot;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Test_SRD_WCG_TX_US5 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;
	
 	String classname =this.getClass().getSimpleName().toString();
 	Package pack = this.getClass().getPackage();
 	String component = pack.getName();
 	String folder ;
   	String screen_resolution;
   	String actual = "";
   	String baseScreenshotFolder;
	String category;
	
	private boolean isElementPresent(By by) {
	    try {
	        driver.findElement(by);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	String device;
	String student_tools;
	String srd_page_header;
	String home_dropdown;
	String srd_li;
	String home_li;
	String srd_i_icon;
	String abt_student_res;
	String srd_unit1;
	String srd_unit4;
	String srd_chapter1;
	String srd_ch1_guided_writing;
	String srd_resource_viewer;
	String srd_resource_container;
   	String resourceId;
	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		
	public static int index=0,assertResult;
   	private String browserchoice;

   	
   	public Test_SRD_WCG_TX_US5(String testEnv){
   		this.testEnv = testEnv;
   	  }	 

	 
@Parameters
public static Collection<Object[]> data() throws Exception {
	   return (new TestEnvironement()).getEnvironment();
}


	   CSVHandler ele_path=null;
		CSVHandler screenshot_folder=null;
	
	private String path;
	Process process=null;
	
	@Before
	public void setUp() throws Exception 
	{		driver = TestRun.init(testEnv);	
		category = SetupDriver.resolutionCategory;
			

		if(category.equalsIgnoreCase("Large")) {driver.manage().window().maximize();}
		if(category.equalsIgnoreCase("Medium_landscape")){driver.manage().window().setSize(new Dimension(1050, 1280 ));}
		if(category.equalsIgnoreCase("Medium_portrait")){driver.manage().window().setSize(new Dimension(800, 1000 ));}
		if(category.equalsIgnoreCase("Small")){driver.manage().window().setSize(new Dimension(380, 700 ));}

String screen_resolution = SetupDriver.resolutionCategory;
System.out.println("screen_resolution"+ screen_resolution);
ele_path = new CSVHandler("src/test/resources/element_path.csv");
screenshot_folder = new CSVHandler("src/test/resources/screenshot_folder.csv");
baseScreenshotFolder = screenshot_folder.getElementXpath("baseScreenshotFolder");
category = SetupDriver.resolutionCategory;
student_tools = ele_path.getElementXpath("student_tools");
srd_page_header = ele_path.getElementXpath("srd_page_header");
home_dropdown = ele_path.getElementXpath("home_dropdown");
srd_li = ele_path.getElementXpath("srd_li");
home_li = ele_path.getElementXpath("home_li");
srd_i_icon = ele_path.getElementXpath("srd_i_icon");
abt_student_res = ele_path.getElementXpath("abt_student_res");
srd_unit1 = ele_path.getElementXpath("srd_unit1");
srd_unit4 = ele_path.getElementXpath("srd_unit4");
srd_chapter1= ele_path.getElementXpath("srd_chapter1");
srd_ch1_guided_writing = ele_path.getElementXpath("srd_ch1_guided_writing");
srd_resource_viewer = ele_path.getElementXpath("srd_resource_viewer");
srd_resource_container = ele_path.getElementXpath("srd_resource_container");
resourceId = ele_path.getElementXpath("resourceId");

//folder for screenshot
folder= baseScreenshotFolder + component + "/" + classname + "/" ;

//if ((SetupDriver.host).equalsIgnoreCase("real-device"))
//			folder= baseScreenshotFolder + component + "/" + classname + "/"+ SetupDriver.host + "/" + SetupDriver.deviceType + "/";
//
//	else
//			folder= baseScreenshotFolder + component + "/" + classname + "/" ;

	}
	
@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}
			
  private String baseUrl;  
  
//--------------Click on download button in the resource viewer

//@Ignore
  @Test
  public void TC_SRD_US5_1_1() throws Exception {
	  
		if(category.equalsIgnoreCase("Large") == true)
		{
	  
		// For each test case
	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
	   String tcname = new Exception().getStackTrace()[0].getMethodName();
	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
	   Thread.sleep(10000);


	//Call launch component 
	Home hm = new Home(); 
	hm.wcg_login(driver);
	
	    synchronized (driver){driver.wait(12000);}		
	  
  driver.findElement(By.xpath(home_dropdown)).click();
  driver.findElement(By.xpath(srd_li + "//a")).click();

  synchronized (driver){driver.wait(6000);}
  WebElement element1 = driver.findElement(By.xpath(srd_page_header));
  String strng1 = element1.getText();

  String expected = "Student Resources";
  TestAssertion.assertionEquals(driver, expected, strng1);
  
  synchronized (driver){driver.wait(8000);}
  
  System.out.println("Launched 'Student Resources' from home dropdown");
  
  
  driver.findElement(By.xpath(srd_unit1)).click();
  
  
  driver.findElement(By.xpath(srd_chapter1)).click();
  
  driver.findElement(By.xpath(srd_ch1_guided_writing)).click();
  
  String oldTab = driver.getWindowHandle();
 
  driver.findElement(By.id(resourceId)).click(); // Opening the resource
  synchronized (driver) 
	{
		driver.wait(12000);
		
	}
  
  Boolean b8= isElementPresent(By.xpath(srd_resource_viewer));
  System.out.println("isElementPresent 8 " + b8.toString());
  TestAssertion.assertionEquals(driver, "true", b8.toString());
  System.out.println("Resource viewer launch asserted");
  
  

  	driver.findElement(By.cssSelector("button.btn.btn-download")).click(); // clicking download in resource viewer
      synchronized (driver){driver.wait(2000);} 
      synchronized (driver){driver.wait(8000);}
      
      // checklist -- Resource is launched in a new tab
      
  	ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
  	driver.switchTo().window(newTab.get(1));
  	Thread.sleep(5000);
  	String title= driver.getTitle();
//  	System.out.println(title);
  	Boolean b3 = title.contains("resourceContent.spr");
  	System.out.println("check new tab" + b3.toString());
  	TestAssertion.assertionEquals(driver,"true",b3.toString());
  	Thread.sleep(5000);
  	driver.switchTo().window(oldTab);
  
      synchronized (driver){driver.wait(2000);} 

		}
}

  
//-- Mobile------------Click on download button in the resource viewer

//@Ignore
  @Test
  public void TC_SRD_US5_1_2() throws Exception {
	  
		if(category.equalsIgnoreCase("Large") == false)
		{
	  
		// For each test case
	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
	   String tcname = new Exception().getStackTrace()[0].getMethodName();
	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
	   Thread.sleep(10000);


	//Call launch component 
	Home hm = new Home(); 
	hm.wcg_login(driver);
	
	    synchronized (driver){driver.wait(12000);}		
	  
  driver.findElement(By.xpath(home_dropdown)).click();
  driver.findElement(By.xpath(srd_li + "//a")).click();

  synchronized (driver){driver.wait(6000);}
  WebElement element1 = driver.findElement(By.xpath(srd_page_header));
  String strng1 = element1.getText();

  String expected = "Student Resources";
  TestAssertion.assertionEquals(driver, expected, strng1);
  
  synchronized (driver){driver.wait(8000);}
  
  System.out.println("Launched 'Student Resources' from home dropdown");
  
  
  driver.findElement(By.xpath(srd_unit1)).click();
  
  
  driver.findElement(By.xpath(srd_chapter1)).click();
  
  driver.findElement(By.xpath(srd_ch1_guided_writing)).click();
  
  String oldTab = driver.getWindowHandle();
 
  driver.findElement(By.id(resourceId)).click(); // Opening the resource
  synchronized (driver) 
	{
		driver.wait(12000);
		
	}
  
  Boolean b8= isElementPresent(By.xpath(srd_resource_viewer));
  System.out.println("isElementPresent 8 " + b8.toString());
  TestAssertion.assertionEquals(driver, "true", b8.toString());
  System.out.println("Resource viewer launch asserted");
  
  

  	driver.findElement(By.cssSelector("button.btn.btn-download")).click(); // clicking download in resource viewer
      synchronized (driver){driver.wait(2000);} 
      synchronized (driver){driver.wait(8000);}
      
      // checklist -- Resource is launched in a new tab
      
  	ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
  	driver.switchTo().window(newTab.get(1));
  	Thread.sleep(5000);
  	String title= driver.getTitle();
//  	System.out.println(title);
  	Boolean b3 = title.contains("resourceContent.spr");
  	System.out.println("check new tab" + b3.toString());
  	TestAssertion.assertionEquals(driver,"true",b3.toString());
  	Thread.sleep(5000);
  	driver.switchTo().window(oldTab);
  
      synchronized (driver){driver.wait(2000);} 

		}
}

  
  // Click on the close icon - Clicking close icon
  
//@Ignore
@Test
public void TC_SRD_US5_2_1() throws Exception {

	// For each test case
	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
	   String tcname = new Exception().getStackTrace()[0].getMethodName();
	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
	   Thread.sleep(10000);


	//Call launch component 
	Home hm = new Home(); 
	hm.wcg_login(driver);
	
	    synchronized (driver){driver.wait(12000);}		
	  
  driver.findElement(By.xpath(home_dropdown)).click();
  driver.findElement(By.xpath(srd_li + "//a")).click();

  synchronized (driver){driver.wait(6000);}
  WebElement element1 = driver.findElement(By.xpath(srd_page_header));
  String strng1 = element1.getText();

  String expected = "Student Resources";
  TestAssertion.assertionEquals(driver, expected, strng1);
  
  synchronized (driver){driver.wait(8000);}
  
  System.out.println("Launched 'Student Resources' from home dropdown");
  synchronized (driver){driver.wait(6000);}
  
  driver.findElement(By.xpath(srd_unit1)).click();
  
  
  driver.findElement(By.xpath(srd_chapter1)).click();
  
  driver.findElement(By.xpath(srd_ch1_guided_writing)).click();
  
  synchronized (driver) {
		driver.wait(6000);
	}
  
//take screenshot 1

  WebElement element2 = driver.findElement(By.xpath(srd_resource_container));
  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
  StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
  String methodName = e.getMethodName();
  String filename = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName  +"_" + SetupDriver.host +  "_Srd_status_before_viewerlaunch" ;
  System.out.println("Capturing screenshot...");
  synchronized (driver){driver.wait(12000);}
  Screenshot.takeElementScreenshot(driver, element2, element2.getLocation(),filename );   

  synchronized (driver){driver.wait(2000);}


  driver.findElement(By.id(resourceId)).click();  // Opening the resource
  synchronized (driver) 
	{
		driver.wait(12000);
		
	}
  Boolean b8= isElementPresent(By.xpath(srd_resource_viewer));
  System.out.println("isElementPresent 8 " + b8.toString());
  TestAssertion.assertionEquals(driver, "true", b8.toString());
  System.out.println("Resource viewer launch asserted");

  synchronized (driver){driver.wait(6000);}
  
  driver.findElement(By.cssSelector("i.custom-tooltip.close-icon")).click();  // Clicking at the close button of resource viewer 
  synchronized (driver) 
	  	{
	  		driver.wait(6000);
	  		
	  	}
  Boolean b9= isElementPresent(By.xpath(srd_resource_viewer));
  System.out.println("isElementPresent 9 " + b9.toString());
  TestAssertion.assertionEquals(driver, "false", b9.toString());
  System.out.println("Resource viewer close asserted");

  synchronized (driver) {
		driver.wait(6000);
	}
  
// take screenshot 2

  String filename2 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName  + "_" + SetupDriver.host + "_Srd_status_after_viewerlaunch_close" ;
  System.out.println("Capturing screenshot...");
  synchronized (driver){driver.wait(12000);}
  Screenshot.takeElementScreenshot(driver, element2, element2.getLocation(),filename2 );   

  
	}


}
