package TestDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.*;

public class MyStepdefs {
    BaseFunc baseFunc = new BaseFunc();
    HomePage homePage = new HomePage(baseFunc);

    @When("^I get to stackoverflow webpage$")
    public void iGetToStackoverflowWebpage() throws Throwable {
        homePage.openHomePage();
    }

    @And("^In search field enter \"([^\"]*)\"$")
    public void inSearchFieldEnter(String string) throws IOException {
        homePage.searchForKeyword(string);
        homePage.sortQuestionTopicsAndWriteToFile(string);
    }

    @Then("^All relevant topics with \"([^\"]*)\" are saved in the textfile$")
    public void allRelevantTopicsWithAreSavedInTheTextfile(String string) throws Throwable {
        homePage.sortQuestionTopicsAndWriteToJson(string);
        baseFunc.closePage();
    }
}


