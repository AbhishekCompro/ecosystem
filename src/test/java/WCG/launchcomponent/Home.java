package WCG.launchcomponent;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.compro.automation.utils.CSVHandler;

public class Home {

	CSVHandler login_details = null;
    
    public void wcg_login(RemoteWebDriver driver) throws Exception {
	
		login_details = new CSVHandler("src/test/resources/login_details.csv");
		String baseurl  =  login_details.getElementXpath("baseurl");
    	String usertype = login_details.getElementXpath("usertype");
    	String username = login_details.getElementXpath("username");
    	String password = login_details.getElementXpath("password");

		driver.get(baseurl + "login/chooseMainUI.spr");
		
		
        synchronized (driver){driver.wait(5000);}	
    	if (usertype.equalsIgnoreCase("student")){driver.findElement(By.id("imgStudents")).click();}
        if (usertype.equalsIgnoreCase("teacher") || usertype.equalsIgnoreCase("educator")){driver.findElement(By.id("imgEducators")).click();}

	    driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys(username);
		driver.findElement(By.id("passwordField")).clear();
	    driver.findElement(By.id("passwordField")).sendKeys(password);
	    driver.findElement(By.id("imgLogin")).click();
	    synchronized (driver) {driver.wait(10000);}
 }
    
}
