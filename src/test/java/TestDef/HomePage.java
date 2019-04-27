package TestDef;

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

    public void enterKeyword(String string) {
        baseFunc.getElement(search).click();
        baseFunc.getElement(search).sendKeys("'" + string + "'");
        baseFunc.getElement(search).submit();

    }

    public void getQuestionTopicsAndWriteToFile(String string) {
        List<WebElement> listItems = baseFunc.getElements(listOfQuestions);
        List<String> texts = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
        texts.removeIf(n -> !(n.contains(string)));

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
            System.out.println("xx");
        };


    }
}
