import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SandBoxPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu/blog";

    private final By blogEntryTitle = By.xpath("//*[@class=\"list-blog-header\"]//a");

    private final By nextButton = By.xpath("//*[@class=\"pagination\"]/li[last()]");
    private final By nextButtonLink = By.xpath("//*[@class=\"pagination\"]/li[last()]//a");


    public SandBoxPage(WebDriver driver) {
        super(driver);
    }
    public SandBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public int countTitles() {
        int result = 0;
        List<WebElement> list = driver.findElements(blogEntryTitle); // A bejegyzések elementjeinek listája
        //List<String> titles = new ArrayList<String>(); // lista a bejegyzések címeinek

        for (WebElement item : list) {
            String title = item.getText();
            //titles.add(title);
            result += 1;
        }

        String nextButtonClass = driver.findElement(nextButton).getAttribute("class");

        while(!nextButtonClass.contains("disabled")) {
            driver.findElement(nextButton).click();
            list = driver.findElements(blogEntryTitle);

            for (WebElement item : list) {
                String title = item.getText();
                //titles.add(title);
                result += 1;
            }

            nextButtonClass = driver.findElement(nextButton).getAttribute("class");
        }

        return result;
    }
}
