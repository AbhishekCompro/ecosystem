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
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import WCG.launchcomponent.*;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_14 { 
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;
	 private String profile_button;
	 private String sign_out;

	public Testhome_wcg_tx_us_14(String testEnv){
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
		profile_button = ele_path.getElementXpath("profile_button");
		sign_out=ele_path.getElementXpath("sign_out");
	}	
	
//=================Sign out 	

	@Test
	public void TC_HOM_US14_1() throws Exception{
	  
	  Home hm = new Home(); 
	  hm.wcg_login(driver);
	  driver.findElement(By.xpath(profile_button)).click();
	  Thread.sleep(5000);
	  driver.findElement(By.cssSelector(sign_out)).click();
	  Thread.sleep(5000);
	  Boolean stu_img= driver.findElement(By.id("imgStudents")).isDisplayed();
	  TestAssertion.assertionEquals(driver,"true", stu_img.toString());

	}

//=================Back after Sign out 	

@Test
	public void TC_HOM_US14_2() throws Exception{
	  
	  Home hm = new Home(); 
	  hm.wcg_login(driver);
	  driver.findElement(By.xpath(profile_button)).click();
	  driver.findElement(By.cssSelector(sign_out)).click();
	  Thread.sleep(5000);
	  driver.executeScript("history.go(-1)");
	  Thread.sleep(5000);
	  Boolean stu_img= driver.findElement(By.id("imgStudents")).isDisplayed();
	  TestAssertion.assertionEquals(driver,"true", stu_img.toString());

	}


@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}

}