import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FrontPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu"; //  main URL

    private final By allowCookiesBox = By.id("tecart-cookie-banner");
    private final By logoImage = By.id("logo"); //logo of the site
    private final By blogEntryTitle = By.xpath("//*[@class=\"card\"]//h3");
    private final String cardFooter = "//*[@class=\"card-footer\"]";
    private final By blogTitle = By.xpath("//*[@class=\"list-blog-header\"]/h3");
    private final By carouselItem = By.xpath("//*[@class=\"carousel-inner\"]/div");
    private final By carouselImage = By.xpath("//*[@class= \"carousel-inner\"]//img");


    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public boolean isCookieBannerVisible() {
        String style = driver.findElement(allowCookiesBox).getAttribute("style");
        if (style.equals("display:none")) {
            return false;
        }

        return true;
    }


    public boolean isAllImagePresent() {
        int carouselNum = driver.findElements(carouselItem).size();
        int carouselImg = driver.findElements(carouselImage).size();
        boolean isAllPresent = carouselImg == carouselNum;
        return isAllPresent;
    }


    public String findLogo() {
        String imgPath = driver.findElement(logoImage).getAttribute("src");
        return imgPath;
    }

    public int countBlogEntries() {
        int num = 0;
        List<WebElement> list = driver.findElements(blogEntryTitle);
        for (WebElement item : list) {
            num += 1;
        }
        return num;
    }

    // NOT USED YET
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
