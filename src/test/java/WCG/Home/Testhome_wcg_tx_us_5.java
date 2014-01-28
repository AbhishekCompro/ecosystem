package WCG.Home;


import java.util.ArrayList;
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
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import WCG.launchcomponent.*;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_5 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;	  
	CSVHandler ele_path=null;
	   
	public Testhome_wcg_tx_us_5(String testEnv){
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
	}
		
			
//=================Launching IMT from icon
@Ignore
	@Test
	public void TC_HOM_016_a()throws Exception{
	    
	    Home hm = new Home(); 
	    hm.wcg_login(driver);
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.cssSelector(ele_path.getElementXpath("imt_icon"))).click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab.get(1));
	    Thread.sleep(5000);
	    String title= driver.getTitle();
	    TestAssertion.assertionEquals(driver,"Interactive Map Tool",title);
	    driver.switchTo().window(oldTab);
	}	 	


//=================Launching IMT from text

	@Test
	public void TC_HOM_US5_1()throws Exception{
	   
        Home hm = new Home(); 
        hm.wcg_login(driver);
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.xpath(ele_path.getElementXpath("student_tools") + "/div[2]/div/h3")).click();
		Thread.sleep(15000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab.get(1));
	    String title1= driver.getTitle();
	    TestAssertion.assertionEquals(driver,"Interactive Map Tool",title1);
	    driver.switchTo().window(oldTab);
	}	 	

	@After
	public void tearDown() throws Exception {
		TestRun.stop(driver);		
  }

}