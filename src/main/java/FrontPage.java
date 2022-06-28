import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FrontPage extends BasePage {


    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this site
    private final String url = "http://srkgakezilabda.hu";

    // The Allow Cookies box
    private final By allowCookiesBox = By.xpath("//div[@aria-label=\"cookieconsent\"]"); // allow cookies div

    // The logo of the site
    private final By logoImage = By.id("logo");

    // Title of a blog entry
    private final By blogEntryTitle = By.xpath("//*[@class=\"card\"]//h3");

    //private final String cardFooter = "//*[@class=\"card-footer\"]";
    //private final By blogTitle = By.xpath("//*[@class=\"list-blog-header\"]/h3");

    // A carousel item
    private final By carouselItem = By.xpath("//*[@class=\"carousel-inner\"]/div");

    // An image in a carousel item
    private final By carouselImage = By.xpath("//*[@class= \"carousel-inner\"]//img");


    /**************************************************
     * Constructors
     **************************************************/

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public FrontPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**************************************************
     * Methods
     **************************************************/

    // Navigate to the page
    @Step("Navigate to the page front page - {frontpage.url}")
    public void navigate() {
        driver.navigate().to(url);
    }

    // Inspection of the visibility of the cookie banner
    public boolean isCookieBannerVisible() {
        String style = driver.findElement(allowCookiesBox).getAttribute("style");
        if (style.equals("display: none;")) {
            return false;
        }

        return true;
    }

    // Are all the carousel images present?
    public boolean isAllImagePresent() {
        int carouselNum = driver.findElements(carouselItem).size();
        int carouselImg = driver.findElements(carouselImage).size();
        boolean isAllPresent = carouselImg == carouselNum;
        return isAllPresent;
    }

    // Is the logo present?
    public String findLogo() {
        String imgPath = driver.findElement(logoImage).getAttribute("src");
        return imgPath;
    }

    // Count the numbers of the blog entries on the front page
    public int countBlogEntries() {
        int num = 0;
        List<WebElement> list = driver.findElements(blogEntryTitle);
        for (WebElement item : list) {
            num += 1;
        }
        return num;
    }

    // -------********* NOT USED YET *********-------
    public String[] collectBlogEntryTitles() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        actions.moveToElement(driver.findElement(blogEntryTitle)).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(blogEntryTitle));

        List<WebElement> titles = driver.findElements(blogEntryTitle);
        String[] titlesArray = new String[titles.size()];

        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).getText();
            titlesArray[i] = title;
        }

        return titlesArray;
    }

}
