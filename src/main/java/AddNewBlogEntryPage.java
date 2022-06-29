import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewBlogEntryPage extends BasePage {

    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this page
    private final String url = "http://srkgakezilabda.hu/admin/pages";

    /*
     * Login as editor
     */

    // User name field
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");

    // Password filed
    private final By passwordField = By.xpath("//input[@name=\"data[password]\"]");

    // Login button
    private final By loginButton = By.xpath("//button[@value=\"login\"]");

    /*
     * Add content
     */

    // Add button
    private final By addButton = By.xpath("//*[@id=\"titlebar-add\"]/button[1]");

    /*
     * Add page modal
     */

    // Title field
    private final By titleField = By.xpath("//*[@id=\"new-page\"]//input[@name=\"data[title]\"]");

    // Folder field
    private final By folderField = By.xpath("//*[@id=\"new-page\"]//input[@name=\"data[folder]\"]");

    // Change the route field
    private final By route = By.xpath("//*[@id=\"new-page\"]//div[@data-parents=\"data[route]\"]");

    // Set the route field
    private final By setRouteToBlog = By.xpath("//li[@data-fjs-item=\"blog\"]/a");

    // Continue button to after set the route
    private final By continueButton = By.xpath("//*[@data-remodal-id=\"parents\"]//div[@class=\"button-bar\"]/a[2]");

    // Dropdown list field to choose the page type
    private final By dropdownList = By.xpath("//*[@id=\"new-page\"]//*[@class=\"selectize-input items full " +
            "has-options has-items\"]");

    // Choose the type of the page
    private final By itemTemplate = By.xpath("//*[@data-value=\"item\"]");

    // Is page visible?
    private final By visibleNoButton = By.xpath("//label[@for=\"toggle_visible0\"]");

    // Success button
    private final By successButton = By.xpath("//*[@class=\"button success\"]");

    /*
     * Content tab
     */

    // Content tab button
    private final By contentTabLink = By.xpath("//*[@data-scope=\"data.content\"]");

    // The text field
    private final By blogText = By.xpath("//pre[@role=\"presentation\"]/span/span");

    //options tab
    private final By optionsTabLink = By.xpath("//*[@data-scope=\"data.options\"]");

    // Change content to published
    private final By publishedButton = By.xpath("//label[@for=\"toggle_header.published1\"]");

    // Taxonomy
    private final By taxonomyCategoryField = By.xpath("//*[@id=\"tab-flex-pages-2bae5fe879569fad82c846f2d3a3b83d2" +
            "\"]/div/div[2]/div[1]/div[2]/div/div/div[1]/input");

    // Save button
    private final By saveContentButton = By.xpath("//button[@type=\"submit\"]");

    // New blog entry title
    private final By blogTitle = By.xpath("//*[@class=\"list-blog-header\"]/h3/a");


    /**************************************************
     * Constructors
     **************************************************/

    public AddNewBlogEntryPage(WebDriver driver) {
        super(driver);
    }
    public AddNewBlogEntryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**************************************************
     * Methods
     **************************************************/


    @Story("Navigate to the admin login page and login")
    public void navigate() {
        driver.navigate().to(url);
    }

    @Story("Admin login")
    public void adminLogin(String username, String password) {
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Story("Click add button")
    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    @Story("Add title")
    public void addTitle(String title) {
        driver.findElement(titleField).sendKeys(title);
    }

    @Story("Set route")
    public void setRoute() {
        driver.findElement(route).click();
        driver.findElement(setRouteToBlog).click();
        driver.findElement(continueButton).click();
    }

    @Story("Select page type")
    public void selectPageType() {
        driver.findElement(dropdownList).click();
        driver.findElement(itemTemplate).click();
    }

    @Story("Set Content invisible")
    public void setContentInvisible() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(visibleNoButton)).click().build().perform();
    }

    @Story("Press success button")
    public void successButton() {
        driver.findElement(successButton).click();
    }

    @Story("Add new blog entry content")
    public void addBlogEntryContent(String text) {
        driver.findElement(contentTabLink).click();
        driver.findElement(blogText).sendKeys(text);
    }

    @Story("Set options of blog entry content")
    public void setOptionsOfBlogEntryContent() {
        Actions action = new Actions(driver);

        driver.findElement(optionsTabLink).click();
        driver.findElement(publishedButton).click();
        driver.findElement(taxonomyCategoryField).sendKeys("blog", Keys.TAB);
    }

    @Story("Save blog entry content")
    public void saveBlogEntryContent() {
        Actions action = new Actions(driver);

        action.moveToElement(driver.findElement(saveContentButton)).click().build().perform();
    }


    @Story("Get the title of the new entry")
    public String getNewBlogEntryTitle() {
        return driver.getTitle();
    }

}
