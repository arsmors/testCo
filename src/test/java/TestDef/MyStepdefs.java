package TestDef;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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
    public By listOfQuestions = By.xpath("//*[@class='result-link']");

    @When("^I get to stackoverflow webpage$")
    public void iGetToStackoverflowWebpage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://stackoverflow.com");
    }

    @And("^In search field enter \"([^\"]*)\"$")
    public void inSearchFieldEnter(String string) throws IOException {
        driver.findElement(search).click();
        driver.findElement(search).sendKeys(string);
        driver.findElement(search).submit();

        List<WebElement> listItems = driver.findElements(listOfQuestions);
        List<String> texts = listItems.stream().map(WebElement::getText).collect(Collectors.toList());

        texts.removeIf(n -> !(n.contains(string)));

        File myFile = new File("text.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
        writer.write(texts + "\n");
        writer.newLine();
        writer.flush();
        writer.close();

        driver.quit();

    }
}



//        for (String list : texts) {
//            System.out.println(list);
//        }


//
//        String fileName = "out.txt";
//
//        try {
//            PrintWriter outputStream = new PrintWriter(fileName);
//
//            for (String list : texts) {
//                if (list.contains(string)) {
//                    outputStream.println(list);
//                    outputStream.close();
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        File myFile = new File("text.txt");
//
//        for (String list : texts) {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(myFile, true));
//            if (list.contains(string)) {
//                writer.write(list + "\n");
//                writer.flush();
//                writer.close();
//            }
//        }
//
//        driver.quit();
//    }
//}
//
//        for (String list : texts) {
//            if(list.contains(string)){
//                System.out.println(list);
//            }
//        }


//        for (int i = 0; i < listItems.size(); i++) {
//            if (listItems.get(i).getText().contains(string)) {
//                System.out.println(listItems.get(i).getText());
//                break;
//            }
//
//        }


