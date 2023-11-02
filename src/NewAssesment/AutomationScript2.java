package NewAssesment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AutomationScript2 {
	public static void main (String [] args) throws InterruptedException {
		ChromeOptions opt = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window();
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		Thread.sleep(3000);
	WebElement Table_databox=driver.findElement(By.xpath("//summary[normalize-space()='Table Data']"));
	   Table_databox.click();
	   
	          WebElement inputtext=  driver.findElement(By.xpath("//textarea[@id=\"jsondata\"]"));
	          Thread.sleep(100);
	          inputtext.clear();
	          
	          //String as Json data 
	          driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	          
	          String JSONData ="[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\r\n"
	          		+ "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\r\n"
	          		+ "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
	          
	          inputtext.sendKeys(JSONData);
	          
	          WebElement refreshtable=driver.findElement(By.xpath("//button[@id=\"refreshtable\"]"));
	          refreshtable.click();
	          //locate the table rows and cells 
	          List<WebElement>tableRows=driver.findElements(By.xpath("//table[@id=\"dynamictable\"]/tr"));
	          //Iterate through the table row and assert the data 
	          for(int i =1;i<tableRows.size();i++) {
	        	  List<WebElement>tablecells=tableRows.get(i).findElements(By.tagName("td"));
	        	  
	        	  String name=tablecells.get(0).getText();
	        	  String age =tablecells.get(1).getText();
	        	  String gender =tablecells.get(2).getText();
	        	  
	        	  //Assert data with JSON data 
	        	  
	        	  String []jsonDataArray=JSONData.split("\\},");
	        	  
	        	  for(String data :jsonDataArray) {
	        		  if(data.contains(name)&&data.contains(age)&&data.contains(gender)) {
	        			  System.out.println("Data matched:"+data);
	        			  break;
	        		  }
	        	  }
	          }
	       Thread.sleep(4000);
	       driver.quit();
		
		
	          
	          
	          
		
		
	}

}
