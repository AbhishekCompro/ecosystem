package WCG.ResourceDirectory;


import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import WCG.launchcomponent.*;

import com.compro.automation.core.SetupDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import com.compro.automation.utils.Screenshot;

@RunWith(Parameterized.class)
public class Test_SRD_WCG_TX_US1 {

	String category;
	private String testEnv;
	private RemoteWebDriver driver = null;
   
	 	String classname =this.getClass().getSimpleName().toString();
	 	Package pack = this.getClass().getPackage();
	 	String component = pack.getName();
	 	
	   	String folder ;
	   	String screen_resolution;
	   	String actual = "";
	   
	   	CSVHandler2 CSVnew = new CSVHandler2("src/test/resources/TC_SRD_US1_4_1.csv");

int csvCol = 4;
String myData[][] = CSVnew.CsvToArray(csvCol);

// {{"2","Unit 2","2","Chapter 4","1","Charts and Infographics","","","",""}};


String baseScreenshotFolder;
String device;
String student_tools;
String srd_page_header;
String home_dropdown;
String srd_li;
String home_li;
String dropdown_list_container;
String srd_information_icon;

String srd_topband_container;
String srd_container;
String srd_header_container;
String srd_instructions_container;
String srd_tree_container;
String srd_footer;

//static boolean Active = true ;
//static boolean  = true;
int rowCount = myData.length;
int colCount = myData[0].length;


 	//-----------------------------------------

String testNodeslevel1[][] = new String[rowCount-1][colCount];	//sl no
String testNodeslevel2[][] = new String[rowCount-1][colCount];	//status

String level1[][] = new String[rowCount-1][colCount];	// component

	
public void initializeArray(){
	int j1=0;
	for(int i=1; i<rowCount; i++){	

		while(j1<i){
		testNodeslevel1[j1][0] = myData[i][0];
		j1++;
		}
	}

	int j2=0;
	for(int i=1; i<rowCount; i++){
		while(j2<i){
		
		testNodeslevel2[j2][0] = myData[i][1];
		j2++;
		}
	}

	

//--------------------------------------------------	
	int j11=0;
	for(int i=1; i<rowCount; i++){
		
		while(j11<i){
		level1[j11][0] = myData[i][2];
		j11++;
		}
	}

	
}

   	private String browserchoice;
	   
   	public Test_SRD_WCG_TX_US1(String testEnv){
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
	{	driver = TestRun.init(testEnv);
	
category = SetupDriver.resolutionCategory;

		  this.initializeArray();
if(category.equalsIgnoreCase("Large")) {driver.manage().window().maximize();}
if(category.equalsIgnoreCase("Medium_landscape")){driver.manage().window().setSize(new Dimension(1050, 1280 ));}
if(category.equalsIgnoreCase("Medium_portrait")){driver.manage().window().setSize(new Dimension(800, 1000 ));}
if(category.equalsIgnoreCase("Small")){driver.manage().window().setSize(new Dimension(380, 700 ));}



String screen_resolution = SetupDriver.resolutionCategory;

String browserchoice = SetupDriver.browserName;
ele_path = new CSVHandler("src/test/resources/element_path.csv");
screenshot_folder = new CSVHandler("src/test/resources/screenshot_folder.csv");
baseScreenshotFolder = screenshot_folder.getElementXpath("baseScreenshotFolder");

student_tools = ele_path.getElementXpath("student_tools");
srd_page_header = ele_path.getElementXpath("srd_page_header");
home_dropdown = ele_path.getElementXpath("home_dropdown");
srd_li = ele_path.getElementXpath("srd_li");
home_li = ele_path.getElementXpath("home_li");
dropdown_list_container = ele_path.getElementXpath("dropdown_list_container");


srd_topband_container = ele_path.getElementXpath("srd_topband_container");
srd_container = ele_path.getElementXpath("srd_container");
srd_header_container = ele_path.getElementXpath("srd_header_container");
srd_instructions_container = ele_path.getElementXpath("srd_instructions_container");
srd_tree_container = ele_path.getElementXpath("srd_tree_container");
srd_footer = ele_path.getElementXpath("srd_footer");
srd_information_icon = ele_path.getElementXpath("srd_information_icon");



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
  

  
  
  
//Click on Main menu drop down , and launch SRD from drop down

//@Ignore
@Test
public void TC_SRD_US1_1_1() throws Exception {
	  
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
	

	  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	  StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
	  String methodName = e.getMethodName();
	
 WebElement element1 = driver.findElement(By.xpath(srd_page_header));
 String strng1 = element1.getText();// component header
 String strng2 = driver.findElement(By.xpath(home_dropdown)).getText(); // dropdown header
 String expected = "Student Resources"; // Checklist -- Student Resources text match

 TestAssertion.assertionEquals(driver, expected, strng1);
 
 		if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, expected, strng2);}
 		synchronized (driver){driver.wait(2000);}

 driver.findElement(By.xpath(home_dropdown)).click();
 synchronized (driver){driver.wait(1000);}
 System.out.println("Checklist -- Check mark corresponding to Student Resources in Component Switcher menu");
 Boolean icon = driver.findElement(By.xpath(srd_li +"//a/div[@class='badge ']")).isDisplayed();
 TestAssertion.assertionEquals(driver,"true",icon.toString()); // Checklist -- Check mark corresponding to Student Resources in Component Switcher menu
 System.out.println("Launched 'Student Resources' from home dropdown");
 synchronized (driver){driver.wait(1000);}
 driver.findElement(By.xpath(home_dropdown)).click();
 synchronized (driver){driver.wait(12000);}
 
 //  capturing screenshot srd_topband_container -- Checklist - Screenshot match of upper section

 String filename11 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_topband_container" ;
 System.out.println("Capturing screenshot srd_topband_container...");
 synchronized (driver){driver.wait(12000);}
 WebElement element11 = driver.findElement(By.xpath(srd_topband_container));
 Screenshot.takeElementScreenshot(driver, element11, element11.getLocation(), filename11  + actual);  
 
 // capturing screenshot srd_container -- Checklist - 'Take screenshot Information text present

 String filename12 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_container" ;
 System.out.println("capturing screenshot srd_container...");
 synchronized (driver){driver.wait(12000);}
 WebElement element12 = driver.findElement(By.xpath(srd_container));
 Screenshot.takeElementScreenshot(driver, element12, element12.getLocation(), filename12  + actual);
 
 // capturing screenshot srd_header_container

 String filename13 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_header_container" ;
 System.out.println("capturing screenshot srd_header_container...");
 synchronized (driver){driver.wait(12000);}
 WebElement element13 = driver.findElement(By.xpath(srd_header_container));
 Screenshot.takeElementScreenshot(driver, element13, element13.getLocation(), filename13  + actual);
 
 // capturing screenshot srd_instructions_container

	 if(category.equalsIgnoreCase("Small") == false) {
		 String filename14 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_instructions_container" ;
		 System.out.println("capturing screenshot srd_instructions_container...");
		 synchronized (driver){driver.wait(12000);}
		 WebElement element14 = driver.findElement(By.xpath(srd_instructions_container));
		 Screenshot.takeElementScreenshot(driver, element14, element14.getLocation(), filename14  + actual);
	 }

	 if(category.equalsIgnoreCase("Small") == true) {
		 driver.findElement(By.xpath(srd_information_icon)).click();
		 String filename14 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_instructions_container" ;
		 System.out.println("capturing screenshot srd_instructions_container...");
		 synchronized (driver){driver.wait(12000);}
		 WebElement element14 = driver.findElement(By.xpath(srd_instructions_container));
		 Screenshot.takeElementScreenshot(driver, element14, element14.getLocation(), filename14  + actual); 
	 }

 // capturing screenshot srd_tree_container

 String filename15 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_tree_container" ;
 System.out.println("capturing screenshot srd_tree_container...");
 synchronized (driver){driver.wait(12000);}
 WebElement element15 = driver.findElement(By.xpath(srd_tree_container));
 Screenshot.takeElementScreenshot(driver, element15, element15.getLocation(), filename15  + actual);
 
 // capturing screenshot srd_footer

 String filename16 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_srd_footer" ;
 System.out.println("capturing screenshot srd_footer...");
 synchronized (driver){driver.wait(12000);}
 WebElement element16 = driver.findElement(By.xpath(srd_footer));
 Screenshot.takeElementScreenshot(driver, element16, element16.getLocation(), filename16  + actual);
 
  
}

//--------Click on SRD on home page under "Student Tools" - clicking image
  
//@Ignore
  @Test
  public void TC_SRD_US1_2_1() throws Exception {
		// For each test case
	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
	   String tcname = new Exception().getStackTrace()[0].getMethodName();
	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
	   Thread.sleep(10000);
	Home hm = new Home(); 
	hm.wcg_login(driver);

	synchronized (driver){driver.wait(12000);}
	    
//	String srdStudenttoolImage = student_tools + "/div/div/div";
//	
//	driver.findElement(By.xpath(srdStudenttoolImage)).click();
	driver.findElement(By.cssSelector("div.nav-icon.student-resources")).click();
	
	synchronized (driver){driver.wait(6000);}
    WebElement element1 = driver.findElement(By.xpath(srd_page_header));
    String strng1 = element1.getText(); // component header
    String strng2 = driver.findElement(By.xpath(home_dropdown)).getText(); // dropdown header
    String expected = "Student Resources"; // Checklist -- Student Resources text match
    System.out.println("Checklist -- Student Resources text match");
    TestAssertion.assertionEquals(driver, expected, strng1);
    if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, expected, strng2);}
    

    
    driver.findElement(By.xpath(home_dropdown)).click();
    synchronized (driver){driver.wait(1000);}
    Boolean icon = driver.findElement(By.xpath(srd_li + "//a/div[@class='badge ']")).isDisplayed();
    TestAssertion.assertionEquals(driver,"true",icon.toString()); // Checklist -- Check mark corresponding to Student Resources in Component Switcher menu
    System.out.println("Checklist -- Check mark corresponding to Student Resources in Component Switcher menu");
    
    //System.out.println("Launched 'Student Resources' from Student Tools clicking image");
    
    // capturing screenshot
    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    StackTraceElement e = stacktrace[1];
    String methodName = e.getMethodName();
    String filename = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName  + "_" + SetupDriver.host + "_Launch_srd_using_Student_Tools_Image" ;
    System.out.println("Capturing screenshot...");
    synchronized (driver){driver.wait(12000);}
    WebElement element2 = driver.findElement(By.xpath(dropdown_list_container));
    Screenshot.takeElementScreenshot(driver, element2, element2.getLocation(), filename  + actual);  
    
    synchronized (driver){driver.wait(1000);}
    driver.findElement(By.xpath(home_dropdown)).click();
    
//    // capturing screenshot
//    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//    StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
//    String methodName = e.getMethodName();
//    String filename = folder + "/" + methodName + "/" + SetupDriver.browserName + "_" + SetupDriver.resolutionCategory + "_" + SetupDriver.host + "_Launch_srd_using_Student_Tools_Image" ;
//    System.out.println("Capturing screenshot...");
//    synchronized (driver){driver.wait(12000);}
//    Screenshot.takeScreenshot(driver,filename  + actual);  

  }
  
  
//  Click on SRD on home page under "Student Tools" - clicking text


//@Ignore
  @Test
  public void TC_SRD_US1_2_1a() throws Exception {
	  
		// For each test case
	   System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
	   String tcname = new Exception().getStackTrace()[0].getMethodName();
	   System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
	   Thread.sleep(10000);
   
	 

	//Call launch component 
	Home hm = new Home(); 
	hm.wcg_login(driver);

	synchronized (driver){driver.wait(12000);} 
    String srdStudenttoolText = student_tools + "//div/h3[@class='caption']";
    driver.findElement(By.xpath(srdStudenttoolText)).click();
	synchronized (driver){driver.wait(6000);}
    WebElement element1 = driver.findElement(By.xpath(srd_page_header));
    String strng1 = element1.getText();
    String strng2 = driver.findElement(By.xpath(home_dropdown)).getText(); // dropdown header
    String expected = "Student Resources"; // Checklist -- Student Resources text match
    System.out.println("Checklist -- Student Resources text match");
    TestAssertion.assertionEquals(driver, expected, strng1);
    if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, expected, strng2);}
    

    
    driver.findElement(By.xpath(home_dropdown)).click();
    synchronized (driver){driver.wait(1000);}
    System.out.println("Checklist -- Check mark corresponding to Student Resources in Component Switcher menu");
    Boolean icon = driver.findElement(By.xpath(srd_li +"//a/div[@class='badge ']")).isDisplayed();
    TestAssertion.assertionEquals(driver,"true",icon.toString()); // Checklist -- Check mark corresponding to Student Resources in Component Switcher menu
	
    //System.out.println("Launched 'Student Resources' from Student Tools clicking text");
	
    // asserting screenshot
    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
    String methodName = e.getMethodName();
    String filename = folder + "/" + methodName+ "/" + SetupDriver.resolutionCategory+ "/" + SetupDriver.browserName  + "_" + SetupDriver.host + "_Launch_srd_using_Student_Tools_Text" ;
    System.out.println("Capturing screenshot...");
    synchronized (driver){driver.wait(12000);}
    WebElement element2 = driver.findElement(By.xpath(dropdown_list_container));
    Screenshot.takeElementScreenshot(driver, element2, element2.getLocation(), filename  + actual);  
	
    synchronized (driver){driver.wait(1000);}
    driver.findElement(By.xpath(home_dropdown)).click();
    

  }
  
  
// Click on Main menu drop down , and launch SRD from drop down

//@Ignore
@Test
public void TC_SRD_US1_3_1() throws Exception {
	  
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
	
	  // full page screenshot
	
	  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	  StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
	  String methodName = e.getMethodName();
	  String filename2 = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_Launch_srd_using_Home_dropdown_2" ;
	  System.out.println("Capturing screenshot...");
	  synchronized (driver){driver.wait(20000);}
	  Screenshot.takeScreenshot(driver,filename2 + actual);  
	
  WebElement element1 = driver.findElement(By.xpath(srd_page_header));
  String strng1 = element1.getText();// component header
  String strng2 = driver.findElement(By.xpath(home_dropdown)).getText(); // dropdown header
  String expected = "Student Resources"; // Checklist -- Student Resources text match
  System.out.println("Checklist -- Student Resources text match");
  TestAssertion.assertionEquals(driver, expected, strng1);
  if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, expected, strng2);}
  synchronized (driver){driver.wait(2000);}

  
  driver.findElement(By.xpath(home_dropdown)).click();
  synchronized (driver){driver.wait(1000);}
  System.out.println("Checklist -- Check mark corresponding to Student Resources in Component Switcher menu");
	Boolean icon = driver.findElement(By.xpath(srd_li +"//a/div[@class='badge ']")).isDisplayed();
	TestAssertion.assertionEquals(driver,"true",icon.toString()); // Checklist -- Check mark corresponding to Student Resources in Component Switcher menu
  System.out.println("Launched 'Student Resources' from home dropdown");
  
  // capturing screenshot

  String filename = folder + "/" + methodName + "/" + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_" + SetupDriver.host + "_Launch_srd_using_Home_dropdown_1" ;
  System.out.println("Capturing screenshot...");
  synchronized (driver){driver.wait(12000);}
  WebElement element2 = driver.findElement(By.xpath(dropdown_list_container));
  Screenshot.takeElementScreenshot(driver, element2, element2.getLocation(), filename  + actual);  
  
  synchronized (driver){driver.wait(1000);}
  driver.findElement(By.xpath(home_dropdown)).click();
  
   
}



//Click on SRD , Click on Main menu drop down and select any component


//@Ignore
@Test
public void TC_SRD_US1_4_1() throws Exception {

	// For each test case
		System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
		String tcname = new Exception().getStackTrace()[0].getMethodName();
//		CaptureScreenshotElement cse = new CaptureScreenshotElement();
//		CaptureScreenshot c = new CaptureScreenshot();
		System.out.println("current method: " +  new Exception().getStackTrace()[0].getMethodName());
		Thread.sleep(10000);
	 
//------------------------------------------------------Desktop---------------------------------------------------------------
		 

	//Call launch component 
	Home hm = new Home(); 
	hm.wcg_login(driver);
	
//	driver.switchTo().alert().dismiss();


			    synchronized (driver){driver.wait(12000);}	

			    String rootXpath = home_dropdown;  // Home Dropdown list xpath
			    int DropdownSize = driver.findElements(By.xpath(rootXpath + "//ul/li")).size();	// size of level 1
//			    System.out.println("DropdownSize: " + DropdownSize);
			    
	//		    int DropdownSize =8;
			    
		    
			    
			    
//------------------------------------------------------------------------------------------------------
			// Launching SRD from Home Drop-down   
			driver.findElement(By.xpath(rootXpath)).click();
		   driver.findElement(By.xpath(srd_li + "//a")).click();
		    
		    System.out.println("Launched 'Student Resources' from home dropdown");
		    synchronized (driver){driver.wait(4000);}
		     
		    // Counting nodes with children
		    
//-------------------------------------------------------------------	   


//------asserting

String strng = driver.findElement(By.xpath(rootXpath)).getText(); // dropdown header
//System.out.println("strng "+ strng);

if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, "Student Resources", strng);}
synchronized (driver){driver.wait(2000);}


//CaptureScreenshot.takeScreenshot(driver, "Using_Home_dropdown");

driver.findElement(By.xpath(rootXpath)).click();
synchronized (driver){driver.wait(1000);}
Boolean icon = driver.findElement(By.xpath(srd_li + "//a/div[@class='badge ']")).isDisplayed();
TestAssertion.assertionEquals(driver,"true",icon.toString()); // asserting check mark

driver.findElement(By.xpath(rootXpath)).click();
synchronized (driver){driver.wait(1000);}

System.out.println("Student Resources launch Asserted");
//-------------------------


int testLength1 = testNodeslevel1.length;	// for level 1;
//System.out.println("testNodeslevel1 length : " +testLength1);



	//-----------------------------testing from csv
	
	
for (int inputNodes1=1; inputNodes1 <= testLength1; inputNodes1++)
	{

	boolean Active = true ;
	boolean Inactive = true;	

String DropdownClick = testNodeslevel1[inputNodes1-1][0];

if(category.equalsIgnoreCase("Small") == true)
{
	int temp = Integer.parseInt(DropdownClick);
	temp = temp+1;
	DropdownClick = temp + "";
}

String status = testNodeslevel2[inputNodes1-1][0];

//System.out.println("DropdownClick : " +DropdownClick);
System.out.println("status: "+ status);


//------asserting


if (status.equalsIgnoreCase("Active")){ Inactive = false;}
if (status.equalsIgnoreCase("Inactive")){ Active = false;}

//System.out.println("Status Active: " + Active);
//System.out.println("Status Inactive: " + Inactive);


if (Active){

	String expected = level1[inputNodes1-1][0];
	System.out.println("Expected : " + expected);
System.out.println("Entered Status Active loop");
synchronized (driver){driver.wait(6000);}
driver.findElement(By.xpath(rootXpath)).click();
synchronized (driver){driver.wait(5000);}
System.out.println("clicked home dropdown from inside loop of Active");

driver.findElement(By.xpath("//ul/li["+DropdownClick+"]/a")).click();

if(category.equalsIgnoreCase("Small") == false){
String strng1 = driver.findElement(By.xpath(rootXpath)).getText(); // dropdown header
//System.out.println("strng1 "+ strng1);
TestAssertion.assertionEquals(driver, expected , strng1);
synchronized (driver){driver.wait(2000);}
}

//CaptureScreenshot.takeScreenshot(driver, "Using_Home_dropdown");

driver.findElement(By.xpath(rootXpath)).click();
synchronized (driver){driver.wait(1000);}
Boolean icon1 = driver.findElement(By.xpath("//ul/li["+DropdownClick+"]/a/div[@class='badge ']")).isDisplayed();
TestAssertion.assertionEquals(driver,"true",icon1.toString()); // asserting check mark

System.out.println(expected+" Asserted");
driver.findElement(By.xpath(rootXpath)).click();
synchronized (driver){driver.wait(6000);}

}



if (Inactive){
	
	String expected = level1[inputNodes1-1][0];
	System.out.println("Expected : " + expected);
	
	System.out.println("Entered Status Inactive loop");
 synchronized (driver){driver.wait(6000);}	
	driver.findElement(By.xpath(rootXpath)).click();
	synchronized (driver){driver.wait(5000);}
	System.out.println("clicked home dropdown from inside loop of Inactive");
	


	
//	driver.findElement(By.xpath(rootXpath)).click();
//	synchronized (driver){driver.wait(1000);}
	driver.findElement(By.xpath("//ul/li["+DropdownClick+"]/a")).click();
	synchronized (driver){driver.wait(2000);}
	
//	driver.switchTo().alert().dismiss();
 driver.findElement(By.xpath("//a[contains(text(),'CLOSE')]")).click();
	System.out.println(expected+" Component Inactive Asserted");
	
}

		} // closing for


	} // Closing test



//-------------Click browser back button after launching SRD


//@Ignore
  @Test
  public void TC_SRD_US1_5_1() throws Exception {
 
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
    driver.findElement(By.xpath(srd_li +"//a")).click();

	synchronized (driver){driver.wait(6000);}
    WebElement element1 = driver.findElement(By.xpath(srd_page_header));
    String strng1 = element1.getText();// component header
    String strng2 = driver.findElement(By.xpath(home_dropdown)).getText(); // dropdown header
    String expected = "Student Resources"; 
        
    TestAssertion.assertionEquals(driver, expected, strng1);
    if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, expected, strng2);}
    synchronized (driver){driver.wait(2000);}
    
    driver.findElement(By.xpath(home_dropdown)).click();
    synchronized (driver){driver.wait(1000);}
    System.out.println("Checklist -- Check mark corresponding to Student Resources in Component Switcher menu");
	Boolean icon = driver.findElement(By.xpath(srd_li +"//a/div[@class='badge ']")).isDisplayed();
	TestAssertion.assertionEquals(driver,"true",icon.toString());// Checklist -- Check mark corresponding to Student Resources in Component Switcher menu
    System.out.println("Launched 'Student Resources' from home dropdown");
	  Thread.sleep(5000);
    //driver.navigate().back();	// browser back
    driver.executeScript("history.go(-1)");

	  Thread.sleep(5000);
	  
	  
	  // asserting again after browser back for home
	  

	    String strng3 = driver.findElement(By.xpath(home_dropdown)).getText(); // dropdown header
	    String expected_1 = "Home"; 
	 
	    if(category.equalsIgnoreCase("Small")== false) { TestAssertion.assertionEquals(driver, expected_1, strng3);}
	    synchronized (driver){driver.wait(2000);}
	    
	    
	  //  System.out.println("After br back string: "+ strng3);
	   // CaptureScreenshot.takeScreenshot(driver, "Using_Home_dropdown");
	    
	    //driver.findElement(By.xpath("//button[@type='button']")).click();
	    synchronized (driver){driver.wait(1000);}
		  Thread.sleep(2000);
		  
		  System.out.println("Checklist -- Check mark corresponding to Home in Component Switcher menu");
		Boolean icon_1 = driver.findElement(By.xpath(home_li+"//a/div[@class='badge ']")).isDisplayed();

		TestAssertion.assertionEquals(driver,"true",icon_1.toString()); // Checklist -- Check mark corresponding to Home in Component Switcher menu

   // System.out.println(icon_1.toString());
		
	    
  }
  
 
}

