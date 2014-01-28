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
public class Testhome_wcg_tx_us_12 { 
	
	private String testEnv;
	private RemoteWebDriver driver = null;	  
	CSVHandler ele_path=null;
	CSVHandler per_val=null;
	private String profile_student_info;
	private String profile_button;
	private String screen_resolution;
	private String profile_close;
	
	public Testhome_wcg_tx_us_12(String testEnv){
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
	    per_val = new CSVHandler("src/test/resources/permitted_values.csv");
		profile_student_info = ele_path.getElementXpath("profile_student_info");
		profile_button=ele_path.getElementXpath("profile_button");
		profile_close=ele_path.getElementXpath("profile_close");
	}
		
//=================Checking Profile-Student Info

@Test
public void TC_HOM_US12_1()throws Exception{
    
	
    Home hm = new Home(); 
    hm.wcg_login(driver);
	String data[]=per_val.readCSV_col(1);
	driver.findElement(By.xpath(profile_button)).click();
	Thread.sleep(5000);
	String str=data[1]+"\n"+ data[2]+"\n"+ data[3];
	StringBuffer sb= new StringBuffer(str);
    Boolean profile_info=driver.findElement(By.xpath(profile_student_info)).getText().contentEquals(sb);
    System.out.println("profile info "+ driver.findElement(By.xpath(profile_student_info)).getText());
    TestAssertion.assertionEquals(driver,"true",profile_info.toString());  
	driver.findElement(By.xpath(profile_button)).click();
}

//=================	Checking Profile close button

@Test
	public void TC_HOM_US12_2()throws Exception{
	    
	    Home hm = new Home(); 
        hm.wcg_login(driver);
        
        if (screen_resolution.equalsIgnoreCase("Large") || screen_resolution.equalsIgnoreCase("Medium_portrait") || screen_resolution.equalsIgnoreCase("Medium_Landscape")){
        	
			driver.findElement(By.xpath(profile_button)).click();
		    driver.findElement(By.cssSelector(profile_close)).click();
		    Boolean profile_menu=driver.findElement(By.xpath(profile_student_info)).isDisplayed();
		    TestAssertion.assertionEquals(driver,"false", profile_menu.toString());
		    
        }
	}

@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}

}