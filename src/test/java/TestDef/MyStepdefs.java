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
    public By listOfQuestions = By.xpath("//*[@class='result-link']");

    BaseFunc baseFunc = new BaseFunc();
    HomePage homePage = new HomePage(baseFunc);

    @When("^I get to stackoverflow webpage$")
    public void iGetToStackoverflowWebpage() throws Throwable {
       homePage.openHomePage();
    }

    @And("^In search field enter \"([^\"]*)\"$")
    public void inSearchFieldEnter(String string) throws IOException {
        homePage.enterKeyword(string);
        homePage.getQuestionTopicsAndWriteToFile(string);

    }

//    @Then("^All relevant topics with \"([^\"]*)\" are saved in the textfile$")
//    public void allRelevantTopicsWithAreSavedInTheTextfile(String string) throws Throwable {
//
//        List<WebElement> listItems = driver.findElements(listOfQuestions);
//        List<String> texts = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
//
//        texts.removeIf(n -> !(n.contains(string)));
//
//        String json = new Gson().toJson(texts);
//
//        JsonObject list = new JsonObject();
//        list.addProperty(string, json);
//
//        File myFile2 = new File("json.txt");
//        BufferedWriter writer2 = new BufferedWriter(new FileWriter(myFile2, true));
//
//        writer2.write(list.toString() + "\n");
//        writer2.flush();
//        writer2.close();
//
//        driver.quit();
//    }
}


