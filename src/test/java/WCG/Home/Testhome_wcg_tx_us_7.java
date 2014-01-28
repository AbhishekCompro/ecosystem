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
public class Testhome_wcg_tx_us_7 { 
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;
	 CSVHandler per_val=null;
	 private String home_dropdown;
	 private String screen_resolution;
	 String classname =this.getClass().getSimpleName();
	 String folder ;
	 String pack=this.getClass().getPackage().getName();
	 
	 public Testhome_wcg_tx_us_7(String testEnv){
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
		ele_path = new CSVHandler("src/test/resources/element_path.csv");
	    String screenshot_path[]=per_val.readCSV_col(7);
		screen_resolution = SetupDriver.resolutionCategory;
	    home_dropdown = ele_path.getElementXpath("home_dropdown");
	    folder= screenshot_path[1] + pack + "/" + classname + "/" ;
	}
		
//=============-connect to NG(By icon)

@Test
public void TC_HOM_US7_1()throws Exception{
	 
	 String methodname=new Exception().getStackTrace()[0].getMethodName();
	 String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_connect_to_ng_icon_launch";
	 Home hm = new Home(); 
	 hm.wcg_login(driver);
     driver.findElement(By.cssSelector(ele_path.getElementXpath("connect_ng_icon"))).click();
     synchronized (driver) {driver.wait(10000);}
	 

 
//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeScreenshot(driver, (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
//	 =============================verify heading of launched component

	 TestAssertion.assertionEquals(driver,"Connect to NG",
			 driver.findElement(By.xpath(ele_path.getElementXpath("connect_to_ng_header")+"/header/h1")).getText());
	 System.out.println("i icon "+driver.findElement(By.xpath(ele_path.getElementXpath("connect_to_ng_header")+"/header/div/div")).isDisplayed());
	 
	 TestAssertion.assertionEquals(driver,"World Almanac",
			 driver.findElement(By.xpath(ele_path.getElementXpath("world_almanac_header"))).getText());
	 
	 TestAssertion.assertionEquals(driver,"Explorer Interviews",
			 driver.findElement(By.xpath(ele_path.getElementXpath("explorer_interview_header"))).getText());
	 
	 TestAssertion.assertionEquals(driver,"Search Countries",
			 driver.findElement(By.id("search")).getAttribute("placeholder"));
	 
	 Boolean aud_player=driver.findElement(By.xpath(ele_path.getElementXpath("connect_to_ng_audio_player"))).isDisplayed();
	 
	 TestAssertion.assertionEquals(driver,"true",aud_player.toString());
	 
	 Boolean i_icon=driver.findElement(By.xpath(ele_path.getElementXpath("connect_to_ng_header")+"/header/div/div")).isDisplayed();
	 
	 TestAssertion.assertionEquals(driver,"true",i_icon.toString());
	 
	 
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

//=============-connect to NG(By text)

@Ignore
@Test
public void TC_HOM_020_b()throws Exception{
	 
	 
	 String base_filename = folder + "connect_to_ng_text_launch" ;
	 Home hm = new Home(); 
     hm.wcg_login(driver);
     driver.findElement(By.xpath(ele_path.getElementXpath("student_tools") + "/div/div[2]/h3")).click();
     synchronized (driver) {driver.wait(10000);}
	 

//	 ===================== capture screenshot and compare with base image
	 
	 Screenshot.takeScreenshot(driver, (base_filename));
//	 ImageCompare.compareTo((base_filename+".png"), (base_filename+extension+".png"));
	 
//	 =============================verify heading of launched component

	 TestAssertion.assertionEquals(driver,"Connect to NG",
			 driver.findElement(By.xpath(ele_path.getElementXpath("connect_to_ng_header"))).getText());
	 
 
	 
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


//==================Check Back after opening connect to NG

@Test

public void TC_HOM_US7_2()throws Exception{
		 
		 Boolean chk_mark;
		 Home hm = new Home(); 
		 hm.wcg_login(driver);
	     driver.findElement(By.cssSelector(ele_path.getElementXpath("connect_ng_icon"))).click();
	     Thread.sleep(5000);
	     driver.executeScript("history.go(-1)");
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


@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}

}