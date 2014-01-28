package WCG.Home;

import java.util.Collection;
import java.util.List;

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
public class Testhome_wcg_tx_us_8 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;	  
	CSVHandler ele_path=null;	
	CSVHandler per_val=null;
	private String mag_link;
	private String home_dropdown;
    private String screen_resolution;
 	String classname =this.getClass().getSimpleName();
 	String folder ;
	String pack=this.getClass().getPackage().getName();
	
	public Testhome_wcg_tx_us_8(String testEnv){
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
	    per_val = new CSVHandler("src/test/config/permitted_values.csv");
	    ele_path = new CSVHandler("src/test/config/element_path.csv");	
	    String screenshot_path[]=per_val.readCSV_col(7);
		screen_resolution = SetupDriver.resolutionCategory;
	 	mag_link=ele_path.getElementXpath("mag_link");
		home_dropdown = ele_path.getElementXpath("home_dropdown");
		folder= screenshot_path[1] + pack + "/" + classname + "/" ;

	}
		
//================-MAG(By text)

@Test
public void TC_HOM_US8_1()throws Exception{
	
	String methodname=new Exception().getStackTrace()[0].getMethodName();
	String base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_mag_text_launch";
	String mag;
	int i=1;
	String mag_hierarchy[]=per_val.readCSV_col(8);
	Home hm = new Home(); 
	hm.wcg_login(driver);
	Thread.sleep(15000);
	driver.findElement(By.xpath(mag_link)).click();
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
	 
//	    ================================ validate left hand hierarchy
	 
	 List<WebElement> hierarchy = driver.findElements(By.xpath(ele_path.getElementXpath("rd_hierarchy")));
		for(WebElement un:hierarchy)
		{
			TestAssertion.assertionEquals(driver,mag_hierarchy[i],un.getText());	
			i++;
		 } 
		
//	    ================================ validate instruction text headings
		
		TestAssertion.assertionEquals(driver,"About Maps and Graphs",
				driver.findElement(By.xpath(ele_path.getElementXpath("rd_instruction")+"/h3")).getText());
		
		TestAssertion.assertionEquals(driver,"Maps and Graphs includes:",
				driver.findElement(By.xpath(ele_path.getElementXpath("rd_instruction")+"/div/p[3]")).getText());
		
		TestAssertion.assertionEquals(driver,"Getting Started",
				driver.findElement(By.xpath(ele_path.getElementXpath("rd_instruction")+"/div/p[4]")).getText());
	 
//	    ================================Selected component should be displayed as text for large and medium landscape
	 
	 if (screen_resolution.equalsIgnoreCase("Medium_landscape") || screen_resolution.equalsIgnoreCase("Large")){
			 mag = driver.findElement(By.xpath(home_dropdown)).getText();
			 TestAssertion.assertionEquals(driver,"Maps and Graphs",mag);

	 }
}
	
//===============-MAG(By icon)
@Ignore
@Test
public void TC_HOM_022_a()throws Exception{
	
	String base_filename = folder + "mag_icon_launch";
	String mag;
	Home hm = new Home(); 
	hm.wcg_login(driver);
    driver.findElement(By.xpath(ele_path.getElementXpath("mag_image"))).click();
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


//==================Check Back after opening MAG

@Test

public void TC_HOM_US8_2()throws Exception{
	  
	  Boolean chk_mark;
	  Home hm = new Home(); 
	  hm.wcg_login(driver);
	  Thread.sleep(10000);
	  driver.findElement(By.xpath(mag_link)).click();
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