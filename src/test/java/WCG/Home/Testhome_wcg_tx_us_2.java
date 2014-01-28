package WCG.Home;

import java.util.Collection;
import java.util.List;


import WCG.launchcomponent.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.compro.automation.core.SetupDriver;
import com.compro.automation.core.TestAssertion;
import com.compro.automation.core.TestEnvironement;
import com.compro.automation.core.TestRun;
import com.compro.automation.utils.CSVHandler;
import com.compro.automation.utils.Screenshot;

@RunWith(Parameterized.class)
public class Testhome_wcg_tx_us_2 {
	
	 private String testEnv;
	 private RemoteWebDriver driver = null;	
	 String screen_resolution;
	 String classname =this.getClass().getSimpleName();
	 String folder ;
	 String pack=this.getClass().getPackage().getName();
	 CSVHandler ele_path=null;
	 CSVHandler per_val=null;
	 
	 public Testhome_wcg_tx_us_2(String testEnv){
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
			per_val = new CSVHandler("src/test/resources/permitted_values.csv");
			String screenshot_path[]=per_val.readCSV_col(7);
			folder= screenshot_path[1] + pack + "/" + classname + "/" ;
		}
		
//		============ List of units should be displayed as per subscribtion =====================
		
	@Test
	public void TC_HOM_US2_2()throws Exception{
			 
			 String[] unit_num=per_val.readCSV_col(3);
			 String[] unit_name=per_val.readCSV_col(4);
			 System.out.println("screen_resolution "+unit_name[12]);
			 int i=1;
			 screen_resolution = SetupDriver.resolutionCategory;
			 Home hm = new Home(); 
		     hm.wcg_login(driver);
				 driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/button")).click();
				 List<WebElement> unit = driver.findElements(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/ul/li/a"));
				 for(WebElement un:unit)
				 {
					String u_num = driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/ul/li["+i+"]/a/div[2]")).getText();
					String u_name = driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/ul/li["+i+"]/a/span")).getText();
					String unit_title_actual=u_num+" "+u_name;
					String unit_title_exp=unit_num[i]+" "+unit_name[i];
					TestAssertion.assertionEquals(driver,unit_title_exp,unit_title_actual);
					i++;
				  }
			 }
				      
//	@Ignore	
	@Test
	public void TC_HOM_US2_1()throws Exception{
			
			 screen_resolution = SetupDriver.resolutionCategory;
			 
			 String base_filename ;
			 String methodname=new Exception().getStackTrace()[0].getMethodName();
//			 String[] unit_num=per_val.readCSV_col(3);
			 String[] unit_name=per_val.readCSV_col(4);
			 String[] page_num=per_val.readCSV_col(5);
			 System.out.println("screen_resolution "+unit_name[12]);
			 int i=1;
			 Home hm = new Home(); 
		     hm.wcg_login(driver);
		     
				 driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/button")).click();
				 List<WebElement> unit = driver.findElements(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/ul/li/a"));
				 for(WebElement un:unit)
				 {
					
					base_filename = folder + methodname + "/"  + SetupDriver.resolutionCategory + "/" + SetupDriver.browserName + "_"+ SetupDriver.host + "_unit_"+i; 
					String page_no = un.getAttribute("href");
					String u_name = driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/ul/li["+i+"]/a/span")).getText();
					page_no=page_no.substring(page_no.lastIndexOf("/")+1);
					TestAssertion.assertionEquals(driver,unit_name[i],u_name);
					TestAssertion.assertionEquals(driver,page_num[i],page_no);	
					un.click();
					Thread.sleep(15000);
					Screenshot.takeScreenshot(driver, (base_filename));
					driver.findElement(By.xpath(ele_path.getElementXpath("home_dropdown"))).click();
					Boolean chk_mark_se = driver.findElement(By.xpath((ele_path.getElementXpath("student_eEdition_dropdown")) + "div")).isDisplayed();  
					TestAssertion.assertionEquals(driver,"true",chk_mark_se.toString()); 
					driver.findElement(By.cssSelector("img.program-logo")).click();
					driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/button")).click();
					i++;
				  }
			 }
//		}
		
	@Test
	public void TC_HOM_US2_3()throws Exception{
			
			 Home hm = new Home(); 
		     hm.wcg_login(driver);
		     screen_resolution = SetupDriver.resolutionCategory;
			 driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/button")).click();
			 driver.findElement(By.xpath(ele_path.getElementXpath("unit_chooser")+"/div/ul/li/a")).click();
			 driver.executeScript("history.go(-1)");
			 
//			 ======================== check eEdition label , dl link , mag image , srd image
			 
			 Thread.sleep(5000);
			 TestAssertion.assertionEquals(driver,"Student eEdition",
					 driver.findElement(By.xpath(ele_path.getElementXpath("eEdition_label"))).getText());
			 Boolean dl=driver.findElement(By.xpath(ele_path.getElementXpath("dl_link"))).isDisplayed();
			 TestAssertion.assertionEquals(driver,"true",dl.toString()); 
			 Boolean mag=driver.findElement(By.xpath(ele_path.getElementXpath("mag_image"))).isDisplayed();
			 TestAssertion.assertionEquals(driver,"true",mag.toString()); 
			 Boolean srd=driver.findElement(By.cssSelector(ele_path.getElementXpath("srd_icon"))).isDisplayed();
			 TestAssertion.assertionEquals(driver,"true",srd.toString());
			 
//			 ======================== Menu should show checkmark with Home in portrait and small		 

			driver.findElement(By.xpath(ele_path.getElementXpath("home_dropdown"))).click();
			synchronized (driver){driver.wait(2000);}	
			Boolean chk_mark = driver.findElement(By.xpath((ele_path.getElementXpath("home")) + "div")).isDisplayed();  
			TestAssertion.assertionEquals(driver,"true",chk_mark.toString()); 
			driver.findElement(By.xpath(ele_path.getElementXpath("home_dropdown"))).click(); 
			
		}
		
	@After
	public void tearDown() throws Exception 
	{
		TestRun.stop(driver);
	}
			 
}
