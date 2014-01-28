package WCG.Home;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
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
public class Testhome_wcg_tx_us_15 { 
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;	
   	 private String terms_link;
	
	 public Testhome_wcg_tx_us_15(String testEnv){
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
		ele_path = new CSVHandler("src/test/config/element_path.csv");
		terms_link = ele_path.getElementXpath("terms_link");

	}


//=================Checking terms and conditions link

@Test
	public void TC_HOM_US15_1()throws Exception{
	    
	Home hm = new Home(); 
	    hm.wcg_login(driver);
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.linkText(terms_link)).click();
		Thread.sleep(5000);
    	ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab.get(1));
	    Thread.sleep(5000);
//	    System.out.println(newTab.get(0)+newTab.get(1));
	    String title= driver.getTitle();
	    System.out.println(driver.getTitle());
	    Boolean title_present=title.contains("myNGconnect_Sci_TermsOfUse.pdf");
	    TestAssertion.assertionEquals(driver,"true", title_present.toString());
	    Thread.sleep(5000);
	    driver.switchTo().window(oldTab);
	}

	@After
	public void tearDown() throws Exception 
	{
		TestRun.stop(driver);
	}

}