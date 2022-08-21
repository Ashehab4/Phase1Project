import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		WebElement test = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
		test.sendKeys("Samsung");
		WebElement SearchBtn = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]"));
		SearchBtn.click();
		List<WebElement> Products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> Prices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price-whole']"));

		for(int i=0 ;i<Products.size() ; i++) {

			System.out.println(Products.get(i).getText() + " " + Prices.get(i).getText());
		}
		String ParentWin = driver.getWindowHandle();
		String ExpectedTitle = Products.get(0).getText();
		Products.get(0).click();
		Set<String> allWins = driver.getWindowHandles();
		for(String win:allWins) {
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}

		WebElement ProductDetails = driver.findElement(By.id("productTitle"));
		String Productlabel = ProductDetails.getText();

		if(Productlabel.equalsIgnoreCase(ExpectedTitle)) {
			System.out.println("Title is Matching");
		}	
		else {
			System.out.println("Title is not Matching");
		}
		driver.quit();
	}


}


