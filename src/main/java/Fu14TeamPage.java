import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Fu14TeamPage extends BasePage {

    private final By player = By.xpath("//*[@class=\"col-sm-6 col-md-4 col-lg-3 player\"]");

    private final String url = "http://srkgakezilabda.hu/csapataink/fu-14";
    private final String name = "FU14";
    private final String trainer = "Pánczél Zoltán";
    private final String[] teamMembers = {"Árokszállási Péter Endre", "Csallóközi Ákos", "Csőre Bence Álmos", "Ferkó Zsombor " +
            "Máté", "Funák Zétény Nemere", "Funák Zolta Kadosa", "Hanuder Dániel", "Hegedüs Bottyán", "Király Csaba " +
            "Milán", "Oláh-Hutka Ábel Bendegúz", "Pisztró Tamás", "Sajószegi Tamás", "Takács Máté"};

    public String[] getTeamMembers() {
        return teamMembers;
    }

    private final String baseImgUrl = "/user/pages/02.csapataink/05.fu-14/";

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

}
