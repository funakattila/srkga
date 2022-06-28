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

    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this page

    private final By teamName = By.xpath("//*[@class=\"main-content csapat\"]//h2");
    private final By aboutTeam = By.xpath("//*[@id=\"about-team\"]");
    private final By trainerField = By.xpath("//*[@id=\"trainer\"]//p");
    private final By player = By.xpath("//*[@class=\"col-sm-6 col-md-4 col-lg-3 player\"]");
    private final By footer = By.xpath("//*[@class=\"footer\"]");
    private final String url = "http://srkgakezilabda.hu/csapataink/fu-14";
    private final String baseImgUrl = "/user/pages/02.csapataink/05.fu-14/";
    private final String fileToSavePath = "src/files/save_from_site_fu14.txt";

    /**************************************************
     * Constructors
     **************************************************/

    public Fu14TeamPage(WebDriver driver) {
        super(driver);
    }
    public Fu14TeamPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**************************************************
     * Methods
     **************************************************/


    // Navigate to the page
    public void navigate() {
        driver.navigate().to(url);
    }

    // Team members of this team
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

    // Count team members
    public int numberOfTeamMembers(String[] teamMembers) {
        int number = 0;

        for (String teamMember:teamMembers) {
            number += 1;
        }

        return number;
    }

    // Get the about text
    public String getAboutTeamText() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        actions.moveToElement(driver.findElement(aboutTeam)).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutTeam));
        String returnText = driver.findElement(aboutTeam).getText();

        return returnText + "\n";
    }


    // Read text from file
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
}
