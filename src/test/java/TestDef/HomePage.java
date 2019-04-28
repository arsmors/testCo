package TestDef;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    BaseFunc baseFunc;

    public By search = By.xpath("//input[@placeholder='Search…']");
    public By listOfQuestions = By.xpath("//*[@class='question-hyperlink']");
    private final String HOMEPAGE = "https://stackoverflow.com";

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void openHomePage() {
        baseFunc.openPage(HOMEPAGE);
    }

    public void searchForKeyword(String string) {
        baseFunc.getElement(search).click();
        baseFunc.getElement(search).sendKeys("'" + string + "'");
        baseFunc.getElement(search).submit();
    }


    public void writeToFile(String string, String write) {
        List<WebElement> listItems = baseFunc.getElements(listOfQuestions);
        List<String> texts = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
        texts.removeIf(n -> !(n.contains(string)));

        if (write == "toFile") {
            File myFile = new File("text.txt");

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(myFile, true));
                for (String text : texts) {

                    writer.write(text + "\n");
                    writer.flush();
                }
                writer.newLine();
                writer.close();
            } catch (Exception e) {
                System.out.println("something went wrong");
            }
            ;

        } else if (write == "toJson") {
            String json = new Gson().toJson(texts);

            JsonObject list = new JsonObject();
            list.addProperty(string, json);

            File myFile = new File("json.txt");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(myFile, true));
                writer.write(list.toString() + "\n");
                writer.flush();
                writer.close();
            } catch (Exception e) {
                System.out.println("something went wrong");
            }
            ;
        }
    }
}
