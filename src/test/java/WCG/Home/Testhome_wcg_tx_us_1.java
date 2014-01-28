package WCG.Home;



import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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
import WCG.launchcomponent.*;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_1 { 
	

	 private String testEnv;
	 private RemoteWebDriver driver = null;	
	 private String home_dropdown;
	 String device;
	 String device_orientation;
	 CSVHandler ele_path=null;
	 CSVHandler per_val=null;
	 private String screen_resolution;
	 String classname =this.getClass().getSimpleName();
	 String folder ;
	 String pack=this.getClass().getPackage().getName();
	
	 public Testhome_wcg_tx_us_1(String testEnv){
		this.testEnv = testEnv;
	  }
		 	
	 
	@Parameters
	public static Collection<Object[]> data() throws Exception {
		   return (new TestEnvironement()).getEnvironment();
	}
  	
	@Before
	public void setUp() throws Exception 
	{
		driver = TestRun.init(testEnv);
		per_val = new CSVHandler("src/test/resources/permitted_values.csv");
	    String screenshot_path[]=per_val.readCSV_col(7);
		device=SetupDriver.deviceType;
		device_orientation=SetupDriver.deviceOrientation;
		screen_resolution = SetupDriver.resolutionCategory;
		ele_path = new CSVHandler("src/test/resources/element_path.csv");
		home_dropdown = ele_path.getElementXpath("home_dropdown");
		folder= screenshot_path[1] + pack + "/" + classname + "/" ;
	}
		

//==============Click back after selecting option from home dropdown

	
@Test
public void TC_HOM_US1_4()throws Exception{
	
     String[] data=per_val.readCSV_col(2);
     Home hm = new Home(); 
     hm.wcg_login(driver);
     int start,end;
     Boolean chk_mark;
     if (screen_resolution.equalsIgnoreCase("Small")){
    	 start=3;end=9;}
     else{
    	 start=2;end=8;}                                
	 for(int k=start;k<=end;k++)
	 {
		 
		 driver.findElement(By.xpath(home_dropdown)).click();
		 WebElement un = driver.findElement(By.xpath("//li["+k+"]" + "/a/span"));
		 un.click();
		 Thread.sleep(5000);
		 System.out.println("data "+data[k]);
		 if (data[k].equalsIgnoreCase("myAssignments")||data[k].equalsIgnoreCase("Global Search"))
		   driver.executeScript("history.go(0)");
		 else
		   driver.executeScript("history.go(-1)"); 
//		 ======================== check eEdition label , dl link , mag image , srd image
		 Thread.sleep(5000);
		 TestAssertion.assertionEquals(driver,"Student eEdition",
				 driver.findElement(By.xpath(ele_path.getElementXpath("eEdition_label"))).getText());
		 Boolean dl=driver.findElement(By.xpath(ele_path.getElementXpath("dl_link"))).isDisplayed();
		 TestAssertion.assertionEquals(driver,"true",dl.toString()); 
		 Boolean mag=driver.findElement(By.xpath(ele_path.getElementXpath("mag_image"))).isDisplayed();
		 TestAssertion.assertionEquals(driver,"true",mag.toString()); 
		 Boolean srd=driver.findElement(By.cssSelector(ele_path.getElementXpath("srd_icon"))).isDisplayed();
		 TestAssertion.assertionEquals(driver,"true",srd.toString());
		 
//		 ======================== Menu should show checkmark with Home in portrait and small		 

		driver.findElement(By.xpath(home_dropdown)).click();
		synchronized (driver){driver.wait(2000);}	
		chk_mark = driver.findElement(By.xpath((ele_path.getElementXpath("home")) + "div")).isDisplayed();  
		TestAssertion.assertionEquals(driver,"true",chk_mark.toString()); 
		driver.findElement(By.xpath(home_dropdown)).click(); 
		
			 
//		 ======================== switcher drop down text validation in large and landscape
			 
		 if(screen_resolution.equalsIgnoreCase("Large") || screen_resolution.equalsIgnoreCase("Medium_landscape")){
		    String switcher_Dropdown = driver.findElement(By.xpath(home_dropdown)).getText();
		   TestAssertion.assertionEquals(driver,"Home",switcher_Dropdown);
		 }  
	   }
}

//=============Check Component Switcher

//=============Home
@Ignore
@Test
public void TC_HOM_002_a()throws Exception{
	 
	String home;
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver){driver.wait(10000);}	
	 
//	    ================================ Active component should launch and Check Mark should be displayed with opened component for Medium_portrait, Medium_landscape, Large
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_portrait") || screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
	    driver.findElement(By.xpath("//li/a/span")).click();
	    Thread.sleep(8000);
	    
//	    ================================Selected component icon should be displayed for medium portrait
	    
	    if (screen_resolution.equalsIgnoreCase("Medium_portrait")){
	    	WebElement we=driver.findElement(By.xpath(home_dropdown));
	    	Screenshot.takeElementScreenshot(driver,we,we.getLocation(),"D:/Profile/home.png");
	    }
	    
//	    ================================Selected component should be displayed as text for large and medium landscape
	    
	    else{
	           home = driver.findElement(By.xpath(home_dropdown)).getText();	  
	          TestAssertion.assertionEquals(driver, "Home", home);
	    }
	    
//	    ================================ Check Mark 
	    
	    driver.findElement(By.xpath(home_dropdown)).click();
	    Boolean chk_mark_se = driver.findElement(By.xpath("//body/header/div/div[2]/div/div/ul/li/a/div")).isDisplayed();  //for desktop and tab
	   TestAssertion.assertionEquals(driver,"true",chk_mark_se.toString()); 
	 }
	 
//	    ================================ menu should contain the component
	 
	 else if (screen_resolution.equalsIgnoreCase("Small")){
		home = driver.findElement(By.xpath("//li[2]/a/span")).getText();
		TestAssertion.assertionEquals(driver, "Home", home);
	 }

}

//=============SE

@Test
public void TC_HOM_US1_2_b()throws Exception{

	 String se;
	 String methodname=new Exception().getStackTrace()[0].getMethodName();
	 String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_student_eEdition_launch";
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 Thread.sleep(15000);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver){driver.wait(5000);}	
	 driver.findElement(By.xpath((ele_path.getElementXpath("student_eEdition_dropdown")) + "span")).click();
	 Thread.sleep(20000);
	 
//	 ===================== capture screenshot and compare with base image
//	 CaptureScreenshot.takeScreenshot(driver, base_filename);
	 Screenshot.takeScreenshot(driver, (base_filename));
//	 Screenshot.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
//	    ================================ Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_se = driver.findElement(By.xpath((ele_path.getElementXpath("student_eEdition_dropdown")) + "div")).isDisplayed();  
	 TestAssertion.assertionEquals(driver,"true",chk_mark_se.toString()); 
	 
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
		 se = driver.findElement(By.xpath(home_dropdown)).getText();	  
	     TestAssertion.assertionEquals(driver, "Student eEdition", se);
	 }
	   
}
	 
//	 MAG

@Test
public void TC_HOM_US1_2_c()throws Exception{

	 String methodname=new Exception().getStackTrace()[0].getMethodName();
	 String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_mag_launch";
	 String mag;
	 Home hm = new Home(); 
	 hm.wcg_login(driver);	
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver) {driver.wait(5000);}
	 driver.findElement(By.xpath((ele_path.getElementXpath("mag_dropdown")) + "span")).click();
	 synchronized (driver) {driver.wait(10000);}
	 
//	 =============================verify heading of launched component

	 TestAssertion.assertionEquals(driver,"Maps and Graphs",
			 driver.findElement(By.xpath(ele_path.getElementXpath("rd_header"))).getText());
	 
 
//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeScreenshot(driver, (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
//	    ================================ Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_mag = driver.findElement(By.xpath((ele_path.getElementXpath("mag_dropdown")) + "div")).isDisplayed();  
	 TestAssertion.assertionEquals(driver,"true",chk_mark_mag.toString());	 
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
			 mag = driver.findElement(By.xpath(home_dropdown)).getText();
			 TestAssertion.assertionEquals(driver,"Maps and Graphs",mag);

	 }

}
	 
//	 DL

@Test
public void TC_HOM_US1_2_d()throws Exception{
	
	 String methodname=new Exception().getStackTrace()[0].getMethodName();
	 String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_dl_launch";
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver) {driver.wait(5000);}
	 driver.findElement(By.xpath((ele_path.getElementXpath("dl_dropdown")) + "span")).click();
	 synchronized (driver) {driver.wait(10000);}
	 
//	 =============================verify heading of launched component

	 TestAssertion.assertionEquals(driver,"Digital Library",
			 driver.findElement(By.xpath((ele_path.getElementXpath("dl_header"))+"/div/h1")).getText());
	 
 
//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeElementScreenshot(driver, driver.findElement(By.xpath(ele_path.getElementXpath("dl_header"))), 
			 driver.findElement(By.xpath(ele_path.getElementXpath("dl_header"))).getLocation(), (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
//	 ================================== Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_dl = driver.findElement(By.xpath((ele_path.getElementXpath("dl_dropdown")) + "div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",chk_mark_dl.toString());
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
		     String dl = driver.findElement(By.xpath(home_dropdown)).getText();
			 TestAssertion.assertionEquals(driver,"Digital Library",dl);
		 }

}
	 
//	 connect to NG

@Test
public void TC_HOM_US1_2_e()throws Exception{
	
	 String methodname=new Exception().getStackTrace()[0].getMethodName();
	 String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_connect_to_ng_launch";
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver) {driver.wait(5000);}
	 driver.findElement(By.xpath((ele_path.getElementXpath("connect_ng_dropdown")) + "span")).click();
	 synchronized (driver) {driver.wait(10000);}
	 
//	 =============================verify heading of launched component

	 TestAssertion.assertionEquals(driver,"Connect to NG",
			 driver.findElement(By.xpath(ele_path.getElementXpath("connect_to_ng_header")+"/header/h1")).getText());
	 
//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeScreenshot(driver, (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
 
	 
//	 ================================== Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_ng = driver.findElement(By.xpath((ele_path.getElementXpath("connect_ng_dropdown")) + "div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",chk_mark_ng.toString());
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
		 String ng = driver.findElement(By.xpath(home_dropdown)).getText();
		 TestAssertion.assertionEquals(driver,"Connect to NG",ng);
      }

}
	 
//	 myAssignments

@Test
public void TC_HOM_US1_2_f()throws Exception{
	
	 String myassign;
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver) {driver.wait(5000);}
	 driver.findElement(By.xpath((ele_path.getElementXpath("myassign_dropdown")) + "span")).click();
	 synchronized (driver) {driver.wait(10000);}
	 myassign = driver.findElement(By.xpath(ele_path.getElementXpath("alert"))).getText();
	 TestAssertion.assertionEquals(driver,"Enhanced tool coming August 2014",myassign);
	 driver.findElement(By.linkText("CLOSE")).click();
	 
//	 ================================== Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_assign = driver.findElement(By.xpath((ele_path.getElementXpath("home")) + "div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",chk_mark_assign.toString());
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
			TestAssertion.assertionEquals(driver,"Home",
					driver.findElement(By.xpath(home_dropdown)).getText());
	 }

}

//	 Global Search

@Test
public void TC_HOM_US1_2_g()throws Exception{

	 String gsearch;
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver) {driver.wait(5000);}
	 driver.findElement(By.xpath((ele_path.getElementXpath("gsearch_dropdown")) + "span")).click();
	 synchronized (driver) {driver.wait(10000);}
	 gsearch = driver.findElement(By.cssSelector("label.component-label")).getText();
	 TestAssertion.assertionEquals(driver,"Global Search",gsearch);
	 driver.findElement(By.linkText("CLOSE")).click();
	 
//	 ================================== Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_gsearch = driver.findElement(By.xpath((ele_path.getElementXpath("home")) + "div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",chk_mark_gsearch.toString());
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
			TestAssertion.assertionEquals(driver,"Home",
					driver.findElement(By.xpath(home_dropdown)).getText());
	 }
 
}
	 
//	 SRD

@Test
public void TC_HOM_US1_2_h()throws Exception{
	
	 String srd;
	 String methodname=new Exception().getStackTrace()[0].getMethodName();
	 String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_srd_launch";
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
	 driver.findElement(By.xpath(home_dropdown)).click();
	 synchronized (driver) {driver.wait(5000);}
	 driver.findElement(By.xpath((ele_path.getElementXpath("srd_dropdown")) + "span")).click();
	 synchronized (driver) {driver.wait(10000);}
	 
//	 =============================verify heading of launched component
	 
	 TestAssertion.assertionEquals(driver,"Student Resources",
			 driver.findElement(By.xpath(ele_path.getElementXpath("rd_header"))).getText());
	 
	 
//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeScreenshot(driver, (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
//   ================================ Check Mark 
	    
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean icon =  driver.findElement(By.xpath((ele_path.getElementXpath("srd_dropdown")) + "div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",icon.toString());

	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
	  
				 srd = driver.findElement(By.xpath(home_dropdown)).getText();
				TestAssertion.assertionEquals(driver,"Student Resources",srd);
	 }	 
}

@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}


}