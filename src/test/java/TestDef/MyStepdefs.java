package TestDef;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MyStepdefs {
    WebDriver driver;
//    public By search = By.id("search");
    public By search = By.xpath("//input[@placeholder='Search…']");

    @When("^I get to stackoverflow webpage$")
    public void iGetToStackoverflowWebpage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://stackoverflow.com");
    }

    @And("^In search field enter \"([^\"]*)\"$")
    public void inSearchFieldEnter(String string) {
        driver.findElement(search).click();
        driver.findElement(search).sendKeys(string);
        driver.findElement(search).submit();

    }
}
