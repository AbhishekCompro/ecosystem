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

@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_13 { 
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;
	 CSVHandler per_val=null;
	 private String profile_button;
		
     public Testhome_wcg_tx_us_13(String testEnv){
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
	    per_val =new CSVHandler("src/test/resources/permitted_values.csv");
		profile_button=ele_path.getElementXpath("profile_button");
	}
		
//=================Checking Profile-Class name

@Test
public void TC_HOM_US13_1()throws Exception{
	
    Home hm = new Home(); 
    hm.wcg_login(driver);
    String data[]=per_val.readCSV_col(6);
	driver.findElement(By.xpath(profile_button)).click();
	Thread.sleep(5000);
	String str=data[1];
	StringBuffer sb= new StringBuffer(str);
    Boolean profile_info=driver.findElement(By.xpath(ele_path.getElementXpath("profile_class"))).getText().contentEquals(sb);
    TestAssertion.assertionEquals(driver,"true",profile_info.toString());
	driver.findElement(By.xpath(profile_button)).click();
}


@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}

}