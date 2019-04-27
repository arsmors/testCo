package TestDef;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MyStepdefs {
    WebDriver driver;
    public By search = By.xpath("//input[@placeholder='Search…']");
//    public By listOfQuestions = By.xpath("//*[@class='result-link']");
    public By listOfQuestions = By.xpath("//*[@class='question-hyperlink']");

    @When("^I get to stackoverflow webpage$")
    public void iGetToStackoverflowWebpage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://stackoverflow.com");
    }

    @And("^In search field enter \"([^\"]*)\"$")
    public void inSearchFieldEnter(String string) throws IOException {
        driver.findElement(search).click();
        driver.findElement(search).sendKeys("'" + string + "'");
        driver.findElement(search).submit();

        List<WebElement> listItems = driver.findElements(listOfQuestions);
        List<String> texts = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
        texts.removeIf(n -> !(n.contains(string)));

        File myFile = new File("text.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(myFile, true));
        for (String text : texts) {

            writer.write(text + "\n");
            writer.flush();
        }
        writer.newLine();
        writer.close();
    }

    @Then("^All relevant topics with \"([^\"]*)\" are saved in the textfile$")
    public void allRelevantTopicsWithAreSavedInTheTextfile(String string) throws Throwable {

        List<WebElement> listItems = driver.findElements(listOfQuestions);
        List<String> texts = listItems.stream().map(WebElement::getText).collect(Collectors.toList());

        texts.removeIf(n -> !(n.contains(string)));

        String json = new Gson().toJson(texts);

        JsonObject list = new JsonObject();
        list.addProperty(string, json);

        File myFile2 = new File("json.txt");
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(myFile2, true));

        writer2.write(list.toString() + "\n");
        writer2.flush();
        writer2.close();

        driver.quit();
    }
}


