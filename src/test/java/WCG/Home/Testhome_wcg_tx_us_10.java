package WCG.Home;


import java.util.Collection;
import org.junit.After;
import org.junit.Before;
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

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_10 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;	  
	CSVHandler ele_path=null;
	String screen_resolution;
	private String search_button;
	   
	public Testhome_wcg_tx_us_10(String testEnv){
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
		screen_resolution = SetupDriver.resolutionCategory;
		search_button=ele_path.getElementXpath("search_button");
	}
		

//=================Checking visibility of Global Search textbox

@Test
	public void TC_HOM_US10_1()throws Exception{
	    Home hm = new Home(); 
	    hm.wcg_login(driver);
	    synchronized (driver) {driver.wait(5000);}
		driver.findElement(By.xpath(search_button)).click();
		Thread.sleep(5000);
		Boolean search_box=driver.findElement(By.name(ele_path.getElementXpath("search_placeholder"))).isDisplayed();
		TestAssertion.assertionEquals(driver,"true", search_box.toString());
		String search_box_text=driver.findElement(By.name(ele_path.getElementXpath("search_placeholder"))).getAttribute("placeholder");
		TestAssertion.assertionEquals(driver,"Search myNGconnect", search_box_text); 
	}
	
//=================Checking Global Search placeholder

@Test
	public void TC_HOM_US10_2()throws Exception{
	    Home hm = new Home(); 
    	hm.wcg_login(driver);
		driver.findElement(By.xpath(search_button)).click();
		String search_box_text=driver.findElement(By.name(ele_path.getElementXpath("search_placeholder"))).getAttribute("placeholder");
		TestAssertion.assertionEquals(driver,"Search myNGconnect", search_box_text);    
		}
	 	


	@After
	public void tearDown() throws Exception 
	{
		TestRun.stop(driver);
	}
}