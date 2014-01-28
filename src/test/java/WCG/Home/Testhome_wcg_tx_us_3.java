package WCG.Home;

import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import WCG.launchcomponent.*;

//import com.saucelabs.saucerest.SauceREST;


@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_3 { 
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	  
	 CSVHandler ele_path=null;
	 private String dl_carousal;
	 
	public Testhome_wcg_tx_us_3(String testEnv){
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
		dl_carousal = ele_path.getElementXpath("dl_carousal");
	}

//==================Clicking on the next and prev button in DL carousal changes the preview image

@Test
public void TC_HOM_US3_3()throws Exception{
	
	Home hm = new Home(); 
	hm.wcg_login(driver);
	String img1 = driver.findElement(By.cssSelector("li.flex-active-slide")).getAttribute("style");
	Actions actions = new Actions(driver);
	actions.moveToElement(driver.findElement(By.xpath(dl_carousal))).build().perform();
	driver.findElement(By.cssSelector("span.flex-next")).click();
	driver.findElement(By.cssSelector("span.flex-prev")).click();
	String img2 = driver.findElement(By.cssSelector("li.flex-active-slide")).getAttribute("style");
	Boolean img_comp=img1.equalsIgnoreCase(img2);
	TestAssertion.assertionEquals(driver, "true", img_comp.toString());

}

//==================Mouse hover the Carousal

@Test
public void TC_HOM_US3_2()throws Exception{
	
	Home hm = new Home(); 
	hm.wcg_login(driver);
	Actions actions = new Actions(driver);
	actions.moveToElement(driver.findElement(By.xpath(dl_carousal))).build().perform();
	Boolean prev=driver.findElement(By.cssSelector("span.flex-prev")).isDisplayed();
	TestAssertion.assertionEquals(driver, "true", prev.toString());
	Actions actions1 = new Actions(driver);
	actions1.moveToElement(driver.findElement(By.xpath(dl_carousal))).build().perform();
	Boolean next=driver.findElement(By.cssSelector("span.flex-next")).isDisplayed();
	TestAssertion.assertionEquals(driver, "true", next.toString());

}

//@Ignore
//@Test
//public void TC_HOM_010()throws Exception{
//	System.out.println("Simple Class name1: " + this.getClass().getSimpleName());
//	String tcname = new Exception().getStackTrace()[0].getMethodName();
//	Home hm = new Home(); 
//	hm.wcg_login(driver);
//	String slide="WCG_SNGC_U01C02S1-5_P001H.png";
//	WebElement dl=driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/section[2]/div/div/div/ul/li[@class='flex-active-slide']"));
//	System.out.println("slide " + dl.getAttribute("style")); 
//	for(int i=1;i<=21;i++){
//		Thread.sleep(4000);
////		if (i==5)
////		 System.out.println("slide " + i +  driver.findElement(By.cssSelector("li.flex-active-slide")).getAttribute("style"));
//		if (i==21)
//			 System.out.println("slide 5" + driver.findElement(By.cssSelector("li.flex-active-slide")).getAttribute("style"));
//	}
//
//}

@After
public void tearDown() throws Exception 
{
	TestRun.stop(driver);
}

}