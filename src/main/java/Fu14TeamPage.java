import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Fu14TeamPage extends BasePage {

    private final By teamName = By.xpath("//*[@class=\"main-content csapat\"]//h2");
    private final By aboutTeam = By.xpath("//*[@id=\"about-team\"]");
    private final By trainerField = By.xpath("//*[@id=\"trainer\"]//p");
    private final By player = By.xpath("//*[@class=\"col-sm-6 col-md-4 col-lg-3 player\"]");
    private final By footer = By.xpath("//*[@class=\"footer\"]");
    private final String url = "http://srkgakezilabda.hu/csapataink/fu-14";
    private final String baseImgUrl = "/user/pages/02.csapataink/05.fu-14/";
    private final String fileToSavePath = "src/files/save_from_site_fu14.txt";

    public Fu14TeamPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public String[] teamMembers() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        actions.moveToElement(driver.findElement(player)).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(player));

        List<WebElement> members = driver.findElements(player);
        String[] team = new String[members.size()];

        for (int i = 0; i < members.size(); i++) {
            String member = members.get(i).getText();
            team[i] = member;
        }

        return team;
    }

    public int numberOfTeamMembers(String[] teamMembers) {
        int number = 0;

        for (String teamMember:teamMembers) {
            number += 1;
        }

        return number;
    }

    public String getAboutTeamText() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        actions.moveToElement(driver.findElement(aboutTeam)).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutTeam));
        String returnText = driver.findElement(aboutTeam).getText();

        return returnText + "\n";
    }
    public String readFileAboutTeam(String fileName) {
        String returnText = "";
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                returnText += line + "\n";
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return returnText;
    }

    public void createFile() {
        try {
            File myObj = new File(fileToSavePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter(fileToSavePath);

            Actions actions = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, 20);

            actions.moveToElement(driver.findElement(aboutTeam)).build().perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(aboutTeam));

            String team = driver.findElement(teamName).getText();
            myWriter.write(team + "\n");

            String about = driver.findElement(aboutTeam).getText();
            myWriter.write(about);


            /*
            actions.moveToElement(driver.findElement(aboutTeam)).build().perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(aboutTeam));
            String about = driver.findElement(aboutTeam).getText();
            myWriter.write(about + "\n");


             */
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
