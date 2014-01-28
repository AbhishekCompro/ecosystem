package WCG.ResourceDirectory;


import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;


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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import WCG.launchcomponent.*;
import org.junit.Assert;
import com.compro.automation.core.SetupDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import com.compro.automation.utils.Screenshot;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)

//-------------SRD.WCG.TX.US2 - As a user, I want to view Instruction Text for Student Resource Directory.
public class Test_SRD_WCG_TX_US2 { 
	
	
	private String testEnv;
	private RemoteWebDriver driver = null;
	String category;
	
	
 	String classname =this.getClass().getSimpleName().toString();
 	Package pack = this.getClass().getPackage();
 	String component = pack.getName();
 	String folder ;
   	String screen_resolution;
   	String actual = "";
   	String baseScreenshotFolder;

String device;
String student_tools;
String srd_page_header;
String home_dropdown;
String srd_li;
String home_li;
String srd_i_icon;
String abt_student_res;
String srd_unit4;
String srd_resource_container;
String srd_i_icon_highlighted;
String srd_information_icon;

String srd_topband_container;
String srd_container;
String srd_header_container;
String srd_instructions_container;
String srd_tree_container;
String srd_footer;
String information_popup_close;
	

private boolean isElementPresent(By by) {
    try {
        driver.findElement(by);
        return true;
    } catch (NoSuchElementException e) {
        return false;
    }
}
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	 
		public static int index=0,assertResult;
	   	private String browserchoice;

	   
	   	public Test_SRD_WCG_TX_US2(String testEnv){
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
String browserchoice = SetupDriver.browserName;
ele_path = new CSVHandler("src/test/resources/element_path.csv");
screenshot_folder = new CSVHandler("src/test/resources/screenshot_folder.csv");
baseScreenshotFolder = screenshot_folder.getElementXpath("baseScreenshotFolder");

System.out.println("screen_resolution"+ screen_resolution);
student_tools = ele_path.getElementXpath("student_tools");
srd_page_header = ele_path.getElementXpath("srd_page_header");
home_dropdown = ele_path.getElementXpath("home_dropdown");
srd_li = ele_path.getElementXpath("srd_li");
home_li = ele_path.getElementXpath("home_li");
srd_i_icon = ele_path.getElementXpath("srd_i_icon");
abt_student_res = ele_path.getElementXpath("abt_student_res");
srd_unit4 = ele_path.getElementXpath("srd_unit4");
srd_resource_container = ele_path.getElementXpath("srd_resource_container");

srd_topband_container = ele_path.getElementXpath("srd_topband_container");
srd_container = ele_path.getElementXpath("srd_container");
srd_header_container = ele_path.getElementXpath("srd_header_container");
srd_instructions_container = ele_path.getElementXpath("srd_instructions_container");
srd_tree_container = ele_path.getElementXpath("srd_tree_container");
srd_footer = ele_path.getElementXpath("srd_footer");
srd_information_icon = ele_path.getElementXpath("srd_information_icon");
srd_i_icon_highlighted = ele_path.getElementXpath("srd_i_icon_highlighted");
information_popup_close = ele_path.getElementXpath("information_popup_close");

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
  
  
//Verify the presence of instruction text

 //@Ignore
   @Test
   public void TC_SRD_US2_1_1() throws Exception {
	   
		if(category.equalsIgnoreCase("Small") == false)
		{
	   
	// For each test case
	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
	   String tcname = new Exception().getStackTrace()[0].getMethodName();
	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
	   Thread.sleep(10000);

  
	
	//Call launch component 
	Home hm = new Home(); 
	hm.wcg_login(driver);
 	    synchronized (driver){driver.wait(30000);}		
 	// Launching SRD from Home Dropdown 
 	    System.out.println("home_dropdown"+ home_dropdown);
     driver.findElement(By.xpath(home_dropdown)).click();
     
     
   
     
     driver.findElement(By.xpath(srd_li + "//a")).click();
     System.out.println("Launched 'Student Resources' from home dropdown");
	 synchronized (driver){driver.wait(6000);}	
//   CaptureScreenshot.takeScreenshot(driver, "Using_Home_dropdown");
     
     // Checklist -- Match the information text in the right pane
	 System.out.println("Checklist -- Match the information text in the right pane");
     WebElement element1 = driver.findElement(By.xpath(abt_student_res));
     String strng1 = element1.getText();
     
     String expected = "About Student Resources";
      
     TestAssertion.assertionEquals(driver, expected, strng1);

     
     System.out.println("Validated Instruction text header");

     // capturing screenshot
        WebElement element2 = driver.findElement(By.xpath(srd_resource_container));
	    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	    StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
	    String methodName = e.getMethodName();
	    String filename = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName  + "_" + SetupDriver.host + "_Srd_resource_container" ;
	    System.out.println("Capturing screenshot...");
	    synchronized (driver){driver.wait(12000);}
	    Screenshot.takeElementScreenshot(driver, element2, element2.getLocation(),filename  + actual);  
     
     
     // Checklist -- Validating for absence of "i" icon
     WebElement element3 = driver.findElement(By.xpath(srd_i_icon));
     System.out.println("Checklist -- Validating for absence of 'i' icon");
     Boolean idisplayed = element3.isDisplayed();
     System.out.println(" i displayed " + idisplayed);
     TestAssertion.assertionEquals(driver, "false",idisplayed.toString());
  
     
		} 
     
   }
   
   
 //Mobile -- Click on "I" icon and verify the presence of the instruction text


   //@Ignore
     @Test
     public void TC_SRD_US2_1_2() throws Exception {
  	   
    	 if(category.equalsIgnoreCase("Small") == true) {
  	// For each test case
  	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
  	   String tcname = new Exception().getStackTrace()[0].getMethodName();
  	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
  	   Thread.sleep(10000);

 	  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
 	  StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
 	  String methodName = e.getMethodName();
  	
  	   
  	//Call launch component 
  	Home hm = new Home(); 
  	hm.wcg_login(driver);
   	    synchronized (driver){driver.wait(30000);}		
   	// Launching SRD from Home Dropdown 
   	    System.out.println("home_dropdown"+ home_dropdown);
       driver.findElement(By.xpath(home_dropdown)).click();
            
       
       driver.findElement(By.xpath(srd_li + "//a")).click();
       System.out.println("Launched 'Student Resources' from home dropdown");
  	   synchronized (driver){driver.wait(6000);}	
  	 
  	 
       // Checklist -- Match the information text header
    	 driver.findElement(By.xpath(srd_information_icon)).click();
  	   System.out.println("Checklist -- Match the information text header");
       WebElement element1 = driver.findElement(By.xpath(abt_student_res));
       String strng1 = element1.getText();
       String expected = "About Student Resources";
       TestAssertion.assertionEquals(driver, expected, strng1);
       System.out.println("Validated Instruction text header");
  	 
  	 
	 String filename14 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_instructions_container" ;
	 System.out.println("capturing screenshot srd_instructions_container...");
	 synchronized (driver){driver.wait(12000);}
	 WebElement element14 = driver.findElement(By.xpath(srd_instructions_container));
	 Screenshot.takeElementScreenshot(driver, element14, element14.getLocation(), filename14  + actual); 
       
     
       
       // Checklist -- Validating for presence & highlightining of "i" icon
       WebElement element3 = driver.findElement(By.xpath(srd_i_icon_highlighted));
       System.out.println("Checklist -- Validating for presence & highlightining of 'i' icon");
       Boolean idisplayed = element3.isDisplayed();
       System.out.println(" i displayed " + idisplayed);
       TestAssertion.assertionEquals(driver, "true",idisplayed.toString());
       
    	 }

       
     }
     
     
   //Mobile -- Close the instruction popup


     //@Ignore
       @Test
       public void TC_SRD_US2_2_2() throws Exception {
    	   
      	 if(category.equalsIgnoreCase("Small") == true) {
    	// For each test case
    	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
    	   String tcname = new Exception().getStackTrace()[0].getMethodName();
    	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
    	   Thread.sleep(10000);

   	  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
   	  StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
   	  String methodName = e.getMethodName();
    	
    	   
    	//Call launch component 
    	Home hm = new Home(); 
    	hm.wcg_login(driver);
     	    synchronized (driver){driver.wait(30000);}		
     	// Launching SRD from Home Dropdown 
     	    System.out.println("home_dropdown"+ home_dropdown);
         driver.findElement(By.xpath(home_dropdown)).click();
              
         
         driver.findElement(By.xpath(srd_li + "//a")).click();
         System.out.println("Launched 'Student Resources' from home dropdown");
    	   synchronized (driver){driver.wait(6000);}	
    	 
    	 
         // Checklist -- Match the information text header
      	 driver.findElement(By.xpath(srd_information_icon)).click();

    
       
         
         // Checklist -- Validating for presence & highlightining of "i" icon
         WebElement element3 = driver.findElement(By.xpath(srd_i_icon_highlighted));
         System.out.println("Checklist -- Validating for presence & highlightining of 'i' icon");
         Boolean idisplayed = element3.isDisplayed();
         System.out.println(" i displayed " + idisplayed);
         TestAssertion.assertionEquals(driver, "true",idisplayed.toString());
         
         // close i popup
         driver.findElement(By.xpath(information_popup_close)).click();
         
         // Checklist -- Validating for non highlightining of "i" icon
         Boolean idisplayed2 = isElementPresent(By.xpath(srd_i_icon_highlighted));
         System.out.println(" i displayed " + idisplayed2);
         TestAssertion.assertionEquals(driver, "false",idisplayed2.toString());
      	 }

         
       }

	   
}