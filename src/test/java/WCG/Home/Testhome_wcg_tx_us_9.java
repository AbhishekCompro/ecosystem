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
import WCG.launchcomponent.*;

@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_9 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;	  
	CSVHandler ele_path=null;
	private String home_dropdown;
    private String screen_resolution;
	   
	public Testhome_wcg_tx_us_9(String testEnv){
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
		screen_resolution = SetupDriver.resolutionCategory;
	    ele_path = new CSVHandler("src/test/resources/element_path.csv");	
		home_dropdown = ele_path.getElementXpath("home_dropdown");
	

	}
		
//=============myAssignments(By icon)

@Test
public void TC_HOM_US9_1()throws Exception{
	
	Boolean chk_mark;
	Home hm = new Home(); 
	hm.wcg_login(driver);
    driver.findElement(By.cssSelector(ele_path.getElementXpath("myassignment_icon"))).click();
    Thread.sleep(5000);
	String myassign = driver.findElement(By.xpath(ele_path.getElementXpath("alert"))).getText();
	TestAssertion.assertionEquals(driver,"Enhanced tool coming August 2014",myassign);
	driver.findElement(By.linkText("CLOSE")).click();
	
//	 ======================== check eEdition label , dl link , mag image , srd image
	 
	 TestAssertion.assertionEquals(driver,"Student eEdition",
			 driver.findElement(By.xpath(ele_path.getElementXpath("eEdition_label"))).getText());
	 Boolean dl=driver.findElement(By.xpath(ele_path.getElementXpath("dl_link"))).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",dl.toString()); 
	 Boolean mag=driver.findElement(By.xpath(ele_path.getElementXpath("mag_image"))).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",mag.toString()); 
	 Boolean srd=driver.findElement(By.cssSelector(ele_path.getElementXpath("srd_icon"))).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",srd.toString());
	 
//	 ======================== Menu should show checkmark with Home in portrait and small		 

	driver.findElement(By.xpath(home_dropdown)).click();
	synchronized (driver){driver.wait(2000);}	
	chk_mark = driver.findElement(By.xpath((ele_path.getElementXpath("home")) + "div")).isDisplayed();  
	TestAssertion.assertionEquals(driver,"true",chk_mark.toString()); 
	driver.findElement(By.xpath(home_dropdown)).click(); 
	
		 
//	 ======================== switcher drop down text validation in large and landscape
		 
	 if(screen_resolution.equalsIgnoreCase("Large") || screen_resolution.equalsIgnoreCase("Medium_landscape")){
	    String switcher_Dropdown = driver.findElement(By.xpath(home_dropdown)).getText();
	   TestAssertion.assertionEquals(driver,"Home",switcher_Dropdown);
	 }  
}

//=============myAssignments(By text)
@Ignore
@Test
public void TC_HOM_024_b()throws Exception{
	
	 Boolean chk_mark;
	 Home hm = new Home(); 
     hm.wcg_login(driver);
     driver.findElement(By.xpath(ele_path.getElementXpath("student_tools")+ "/div[2]/div[2]/h3")).click();
     Thread.sleep(5000);
	 String myassign = driver.findElement(By.cssSelector("label.component-label")).getText();
	 TestAssertion.assertionEquals(driver,"myAssignments",myassign);
	 driver.findElement(By.linkText("CLOSE")).click();
//	 ======================== check eEdition label , dl link , mag image , srd image
	 
	 TestAssertion.assertionEquals(driver,"Student eEdition",
			 driver.findElement(By.xpath(ele_path.getElementXpath("eEdition_label"))).getText());
	 Boolean dl=driver.findElement(By.xpath(ele_path.getElementXpath("dl_link"))).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",dl.toString()); 
	 Boolean mag=driver.findElement(By.xpath(ele_path.getElementXpath("mag_image"))).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",mag.toString()); 
	 Boolean srd=driver.findElement(By.cssSelector(ele_path.getElementXpath("srd_icon"))).isDisplayed();
	 TestAssertion.assertionEquals(driver,"true",srd.toString());
	 
//	 ======================== Menu should show checkmark with Home in portrait and small		 

	driver.findElement(By.xpath(home_dropdown)).click();
	synchronized (driver){driver.wait(2000);}	
	chk_mark = driver.findElement(By.xpath((ele_path.getElementXpath("home")) + "div")).isDisplayed();  
	TestAssertion.assertionEquals(driver,"true",chk_mark.toString()); 
	driver.findElement(By.xpath(home_dropdown)).click(); 
	
		 
//	 ======================== switcher drop down text validation in large and landscape
		 
	 if(screen_resolution.equalsIgnoreCase("Large") || screen_resolution.equalsIgnoreCase("Medium_landscape")){
	    String switcher_Dropdown = driver.findElement(By.xpath(home_dropdown)).getText();
	   TestAssertion.assertionEquals(driver,"Home",switcher_Dropdown);
	 }  
}		


//==================Check Back after opening myAssignments

@Test

public void TC_HOM_US9_2()throws Exception{
	  
	  Boolean chk_mark;
	  Home hm = new Home(); 
	  hm.wcg_login(driver);
	  driver.findElement(By.cssSelector(ele_path.getElementXpath("myassignment_icon"))).click();
	  Thread.sleep(5000);
	  driver.executeScript("history.go(0)");
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