import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BlogsPage extends BasePage {

    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this page

    private final String url = "http://srkgakezilabda.hu/blog";

    private final By blogEntryTitle = By.xpath("//*[@class=\"list-blog-header\"]//a");

    private final By nextButton = By.xpath("//*[@class=\"pagination\"]/li[last()]");

    private final By nextButtonLink = By.xpath("//*[@class=\"pagination\"]/li[last()]/a");

    private final List<WebElement> list = driver.findElements(blogEntryTitle);


    /**************************************************
     * Constructors
     **************************************************/

    public BlogsPage(WebDriver driver) {
        super(driver);
    }

    public BlogsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**************************************************
     * Methods
     **************************************************/

    @Story("Navigate to the page")
    public void navigate() {
        driver.navigate().to(url);
    }

    @Story("Count all the published blog")
    public int countAllBlogTitles() {
        int result = 0;
        List<WebElement> list = driver.findElements(blogEntryTitle);

        for (WebElement item : list) {
            String title = item.getText();
            result += 1;
        }

        String nextButtonClass = driver.findElement(nextButton).getAttribute("class");

        while(!nextButtonClass.contains("disabled")) {
            driver.findElement(nextButton).click();
            list = driver.findElements(blogEntryTitle);

            for (WebElement item : list) {
                String title = item.getText();
                result += 1;
            }

            nextButtonClass = driver.findElement(nextButton).getAttribute("class");
        }

        return result;
    }

    @Story("Get all the blog titles")
    public String[] getAllBlogTitlesArray() {
        int counter = 0;
        String[] titles = new String[countAllBlogTitles()];

        navigate();

        List<WebElement> elementList = driver.findElements(blogEntryTitle);

        for (WebElement element : elementList) {
            String title = element.getText();
            titles[counter] = title;
            counter += 1;
            System.out.println(title);
        }

        String nextButtonClass = driver.findElement(nextButton).getAttribute("class");  //
        System.out.println(nextButtonClass);


        while(!nextButtonClass.contains("disabled")) {
            System.out.println(nextButtonClass);
            driver.findElement(nextButtonLink).click();
            elementList = driver.findElements(blogEntryTitle);


            for (WebElement element : elementList) {
                String title = element.getText();
                titles[counter] = title;
                counter += 1;
                System.out.println(title);
                //titles.add(title);
            }

            nextButtonClass = driver.findElement(nextButton).getAttribute("class");
            System.out.println(nextButtonClass);
        }

        return titles;
    }

}
