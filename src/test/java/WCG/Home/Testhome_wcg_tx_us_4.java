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
public class Testhome_wcg_tx_us_4 { 
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;
	 CSVHandler per_val=null;
	 private String home_dropdown;
     private String dl_link;
     private String screen_resolution;
     String classname =this.getClass().getSimpleName();
     String pack=this.getClass().getPackage().getName();
   	 String folder;
     
	 public Testhome_wcg_tx_us_4(String testEnv){
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
	    ele_path = new CSVHandler("src/test/resources/element_path.csv");
	    per_val = new CSVHandler("src/test/resources/permitted_values.csv");
	    String screenshot_path[]=per_val.readCSV_col(7);
		screen_resolution = SetupDriver.resolutionCategory;
	    home_dropdown = ele_path.getElementXpath("home_dropdown");
		dl_link=ele_path.getElementXpath("dl_link");
		folder= screenshot_path[1] + pack + "/" + classname + "/" ;
	}
		
		
//=================-DL(By text)

@Test
public void TC_HOM_US4_1()throws Exception{
	
	String methodname=new Exception().getStackTrace()[0].getMethodName();
	String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_dl_text_launch";
	Home hm = new Home(); 
	hm.wcg_login(driver);
    driver.findElement(By.xpath(dl_link)).click();
    Thread.sleep(10000);
    
//	 =============================verify heading of launched component and i icon 

	 TestAssertion.assertionEquals(driver,"Digital Library",
			 driver.findElement(By.xpath((ele_path.getElementXpath("dl_header"))+"/div/h1")).getText());
	 
	 Boolean i_icon=driver.findElement(By.xpath((ele_path.getElementXpath("dl_header"))+"/div/div/div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",i_icon.toString());
	 
//	 =============================verify search textbox default text
	 
	 TestAssertion.assertionEquals(driver,"Search the Digital Library",
			 driver.findElement(By.xpath((ele_path.getElementXpath("dl_header"))+"/div[2]/form/div/input")).getAttribute("placeholder"));
	 
//	 =============================verify Default tab is Browser by Unit
	 
	 TestAssertion.assertionEquals(driver,"active",
			 driver.findElement(By.xpath(ele_path.getElementXpath("dl_tabs"))).getAttribute("class"));
	 TestAssertion.assertionEquals(driver,"Unit",
			 driver.findElement(By.xpath((ele_path.getElementXpath("dl_tabs"))+"/a/span")).getText());
 
	 
//	 ================================== Check Mark 
	 
	 driver.findElement(By.xpath(home_dropdown)).click();
	 Boolean chk_mark_dl = driver.findElement(By.xpath((ele_path.getElementXpath("dl_dropdown")) + "div")).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",chk_mark_dl.toString());
	 

//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeElementScreenshot(driver, driver.findElement(By.xpath(ele_path.getElementXpath("dl_header"))),
			 driver.findElement(By.xpath(ele_path.getElementXpath("dl_header"))).getLocation(), (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
		     String dl = driver.findElement(By.xpath(home_dropdown)).getText();
			 TestAssertion.assertionEquals(driver,"Digital Library",dl);
		 }
}

//================-DL(By image)
@Ignore
@Test
public void TC_HOM_014_a()throws Exception{
	
	String methodname=new Exception().getStackTrace()[0].getMethodName();
	String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_dl_image_launch";
	Home hm = new Home(); 
	hm.wcg_login(driver);
    driver.findElement(By.cssSelector("li.flex-active-slide")).click();
    Thread.sleep(15000);
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

//==================Check Back after opening DL

@Test

public void TC_HOM_US4_2()throws Exception{

	  Boolean chk_mark;
	  Home hm = new Home(); 
	  hm.wcg_login(driver);
	  Thread.sleep(20000);
	  driver.findElement(By.cssSelector("li.flex-active-slide")).click();
	  Thread.sleep(5000);
	  driver.executeScript("history.go(-1)");
	  Thread.sleep(5000);
	  
//		 ======================== check eEdition label , dl link , mag image , srd image
		 
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


@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}

}