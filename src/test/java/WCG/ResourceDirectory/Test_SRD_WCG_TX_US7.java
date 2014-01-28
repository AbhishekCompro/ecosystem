package WCG.ResourceDirectory;


import java.util.Collection;

import WCG.launchcomponent.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.compro.automation.core.SetupDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import com.compro.automation.utils.Screenshot;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Test_SRD_WCG_TX_US7 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;
	String category;
	String folder ;
	String screen_resolution;
	String classname =this.getClass().getSimpleName().toString();
 	Package pack = this.getClass().getPackage();
 	String component = pack.getName();
   	String actual = "";
   	String baseScreenshotFolder;

   	String extension =".png" ;
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
	String srd_list_view;
	String srd_grid_view;
	String srd_list_enabled;
	String srd_root;
	String brcm_size;
	String srd_breadcrum;
	String srd_res_container;
	String srd_resource_container;
	
	public static int index=0,assertResult;
   	private String browserchoice;

 
public Test_SRD_WCG_TX_US7(String testEnv){
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
		

//		//Read Properties file


String screen_resolution = SetupDriver.resolutionCategory;
System.out.println("screen_resolution"+ screen_resolution);
ele_path = new CSVHandler("src/test/resources/element_path.csv");
screenshot_folder = new CSVHandler("src/test/resources/screenshot_folder.csv");
baseScreenshotFolder = screenshot_folder.getElementXpath("baseScreenshotFolder");
category = SetupDriver.resolutionCategory;
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
srd_list_view = ele_path.getElementXpath("srd_list_view");
srd_grid_view = ele_path.getElementXpath("srd_grid_view");
srd_list_enabled = ele_path.getElementXpath("srd_list_enabled");
srd_resource_container = ele_path.getElementXpath("srd_resource_container");

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
  
  
//--------------Click on grid view

//@Ignore
  @Test
	public void TC_SRD_US7_1_1() throws Exception {

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
		synchronized (driver) {
			driver.wait(2000);
		}

		driver.findElement(By.xpath(home_dropdown)).click();
		driver.findElement(By.xpath(srd_li + "//a")).click();

		WebElement element1 = driver.findElement(By
				.xpath(srd_page_header));
		String strng1 = element1.getText();
	
		 String expected = "Student Resources";
		 
	     TestAssertion.assertionEquals(driver, expected, strng1);

		synchronized (driver) {
			driver.wait(8000);
		}

		System.out.println("Launched 'Student Resources' from home dropdown");

	    driver.findElement(By.xpath(srd_unit1)).click();
	    
	    
	    driver.findElement(By.xpath(srd_chapter1)).click();
	    
	    driver.findElement(By.xpath(srd_ch1_guided_writing)).click();
	    
		   synchronized (driver) {
				driver.wait(6000);
			}

		   
		// for assert ad screenshot comparison
	    driver.findElement(By.xpath(srd_list_view)).click(); //List View
		System.out.println("Clicked List view");
		   WebElement listEle = driver.findElement(By.xpath(srd_list_enabled));
		   String chk1 = listEle.getAttribute("title");
		   System.out.println("active status (list) : " + chk1);
		   TestAssertion.assertionEquals(driver, "List View", chk1);
		
	    synchronized (driver) {
			driver.wait(6000);

		}
	    driver.findElement(By.xpath(srd_grid_view)).click(); // Grid view
		System.out.println("Clicked Grid view");
		
	    synchronized (driver) {
			driver.wait(6000);

		}

		   WebElement listEle2 = driver.findElement(By.xpath(srd_grid_view));
		   System.out.println("Checklist - Grid view icon is active");
		   String chk2 = listEle2.getAttribute("title");
		   System.out.println("active status (Grid) : " + chk2);
		   TestAssertion.assertionEquals(driver, "Grid View", chk2);
		 
		    // capturing screenshot
		   
		    WebElement element3 = driver.findElement(By.xpath(srd_resource_container));
		    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		    StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
		    String methodName = e.getMethodName();
		    String filename = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host +  "_Resources_displayed_in_grid_view" ;
		    System.out.println("Capturing screenshot...");
		    synchronized (driver){driver.wait(12000);}
		    
		    Screenshot.takeElementScreenshot(driver, element3, element3.getLocation(),filename );
		}
	}

 // -----------  Click on list view
 
 
//@Ignore
	@Test
	public void TC_SRD_US7_2_1() throws Exception {

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
		synchronized (driver) {
			driver.wait(2000);
		}

		driver.findElement(By.xpath(home_dropdown)).click();
		driver.findElement(By.xpath(srd_li + "//a")).click();

		WebElement element1 = driver.findElement(By
				.xpath(srd_page_header));
		String strng1 = element1.getText();
		//Assert.assertEquals("Error in Student Resources ", "Student Resources",strng1);
		
		 String expected = "Student Resources";
	    TestAssertion.assertionEquals(driver, expected, strng1);

		synchronized (driver) {
			driver.wait(8000);
		}

		System.out.println("Launched 'Student Resources' from home dropdown");

	    driver.findElement(By.xpath(srd_unit1)).click();
	    
	    
	    driver.findElement(By.xpath(srd_chapter1)).click();
	    
	    driver.findElement(By.xpath(srd_ch1_guided_writing)).click();
		   synchronized (driver) {
				driver.wait(6000);
			}
		   System.out.println("Default view");
		// for assert ad screenshot comparison
	    driver.findElement(By.xpath(srd_list_view)).click(); //List View
		   		   
		   System.out.println("Checklist - List view icon is active");
		   WebElement listEle = driver.findElement(By.xpath(srd_list_enabled));
		   String chk1 = listEle.getAttribute("title");
		   System.out.println("active status (list) : " + chk1);
		   TestAssertion.assertionEquals(driver, "List View", chk1);

		    // capturing screenshot
		    WebElement element3 = driver.findElement(By.xpath(srd_resource_container));
		    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		    StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
		    String methodName = e.getMethodName();
		    String filename = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName  +"_" + SetupDriver.host +  "_Resources_displayed_in_list_view" ;
		    System.out.println("Capturing screenshot...");
		    synchronized (driver){driver.wait(12000);}
		    
		    Screenshot.takeElementScreenshot(driver, element3, element3.getLocation(),filename );

		}
	}
	
}