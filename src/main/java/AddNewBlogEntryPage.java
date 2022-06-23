import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    // Success button
    private final By successButton = By.xpath("//*[@class=\"button success\"]");

    /*
     * Content tab
     */

    // Content tab button
    private final By contentTabLink = By.xpath("//*[@class=\"tabs-nav\"]/a[1]");

    // The text field
    private final By blogText = By.xpath("//pre[@role=\"presentation\"]/span/span");


    //options tab
    private final By optionsTabLink = By.xpath("//*[@class=\"tabs-nav\"]/a[2]");

    // Change content to unpublished
    //private final By unpublishedButton = By.xpath("//*[@for=\"toggle_header.published0\"]");

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

    // Navigate to the admin login page and login
    public void navigateAndLogin() {
        driver.navigate().to(url);
        driver.findElement(userNameField).sendKeys(TestData.editorUsername);
        driver.findElement(passwordField).sendKeys(TestData.editorPassword);
        driver.findElement(loginButton).click();
    }

    // Create new blog entry
    public void createBlogEntry(String title) {
        navigateAndLogin();

        driver.findElement(addButton).click();
        driver.findElement(titleField).sendKeys(title);
        driver.findElement(route).click();
        driver.findElement(setRouteToBlog).click();
        driver.findElement(continueButton).click();
        driver.findElement(dropdownList).click();
        driver.findElement(itemTemplate).click();
        driver.findElement(successButton).click();
        driver.findElement(blogText).sendKeys(TestData.text);
        driver.findElement(optionsTabLink).click();
        //driver.findElement(unpublishedButton).click();
        driver.findElement(saveContentButton).click();
    }

    // Get the title of the new entry
    public String getNewBlogEntryTitle() {
        return driver.findElement(blogTitle).getText();
    }

}
