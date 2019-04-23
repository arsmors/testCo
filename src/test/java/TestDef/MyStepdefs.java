package TestDef;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MyStepdefs {
    WebDriver driver;


    @When("^I get to stackoverflow webpage$")
    public void iGetToStackoverflowWebpage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://stackoverflow.com");
    }
}
