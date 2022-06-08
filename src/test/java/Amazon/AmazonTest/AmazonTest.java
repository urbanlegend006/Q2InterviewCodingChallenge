package Amazon.AmazonTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest {

		
		@Test
		public void amazonTest() {
			
			WebDriver driver = WebDriverManager.chromedriver().create();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			driver.get("https://www.amazon.in");
			Select select = new Select(driver.findElement(By.id("searchDropdownBox")));
			select.selectByVisibleText("Watches");
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Watches under 5000");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			List<WebElement> watchNames = driver.findElements(By.xpath("//h2[contains(@class,'a-size-mini')]"));
			List<WebElement> watchPrices = driver.findElements(By.xpath("//h2[contains(@class,'a-size-mini')]/ancestor::div[1]/following-sibling::*//span[@class='a-price']"));
			
			
			List<String> moreThan5000 = new ArrayList<String>();
			
			for(int i=0; i<watchNames.size(); i++) {
				
				if(Integer.parseInt(watchPrices.get(i).getText().substring(1).replaceAll("[,\\n]", "")) > 5000)
					moreThan5000.add(watchNames.get(i).getText());
				System.out.println(watchNames.get(i).getText().substring(0, watchNames.get(i).getText().length() >= 50 ? 50:watchNames.get(i).getText().length()) + "    ->    "+ watchPrices.get(i).getText().substring(1).replaceAll(",", ""));
			}
			
			System.out.println("\n\n=============================The Products more than 5000 INR==============================\n\n");
			for(String str : moreThan5000)
				System.out.println(str.substring(0, 50) + "\n\n");
			
		}

}
