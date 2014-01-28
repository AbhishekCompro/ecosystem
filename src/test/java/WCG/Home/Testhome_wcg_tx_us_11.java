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
import com.compro.automation.core.SetupDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import WCG.launchcomponent.*;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_11 {

	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;
	 String screen_resolution;
	 
	public Testhome_wcg_tx_us_11(String testEnv){
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
	}


//=================Checking Help

@Test
	public void TC_HOM_US11_1()throws Exception{
	   
	    Home hm = new Home();
	    hm.wcg_login(driver);
		String oldTab = driver.getWindowHandle();

	//  ====================Small view

	    if (screen_resolution.equalsIgnoreCase("Small")){

	    	driver.findElement(By.xpath(ele_path.getElementXpath("home_dropdown"))).click();
	    }

	    driver.findElement(By.xpath(ele_path.getElementXpath("help_button"))).click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab.get(1));
	    Thread.sleep(5000);
	    driver.findElement(By.linkText("User Manual PDF")).click();
	    ArrayList<String> newTab1 = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab1.get(2));
	    Thread.sleep(5000);
	    String title= driver.getCurrentUrl();
	    System.out.println("title"+title);
	    Boolean title_present=title.contains("wcg_student_usermanual.pdf");
	    TestAssertion.assertionEquals(driver,"true",title_present.toString());
	    Thread.sleep(5000);
	    driver.switchTo().window(oldTab);
	}

	@After
	public void tearDown() throws Exception
	{
		TestRun.stop(driver);
	}

}