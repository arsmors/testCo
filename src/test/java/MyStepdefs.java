import cucumber.api.PendingException;
import cucumber.api.java.en.When;

public class MyStepdefs {
    @When("^I get to \"([^\"]*)\" webpage$")
    public void iGetToWebpage(String url){


        throw new PendingException();
    }
}
