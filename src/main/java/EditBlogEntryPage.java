import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditBlogEntryPage extends BasePage {
    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this page (admin)
    private final String url = "http://srkgakezilabda.hu/admin/pages/blog/lorem-ipsum";

    // The URL of this page (public)
    private final String urlPublic = "http://srkgakezilabda.hu/blog/lorem-ipsum";

    /*
     * Login as editor
     */

    // User name field
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");

    // Password filed
    private final By passwordField = By.xpath("//input[@name=\"data[password]\"]");

    // Login button
    private final By loginButton = By.xpath("//button[@value=\"login\"]");

    // Fields
    private final By titleField = By.xpath("//input[@name=\"data[header][title]\"]");

    // Success button
    private final By successButton = By.xpath("//*[@class=\"button success\"]");

    // Title H1
    private final By titleH1 = By.xpath("//h1");

    // Title of the public blog entry
    private final By titleH3 = By.xpath("//*[@class=\"list-blog-header\"]/h3/a");

    // Date of the public blog entry
    private final By dateOfPublish = By.xpath("//*[@class=\"list-blog-date\"]//span");

    private final By blogText = By.className("list-blog-padding");

    // Delete blog entry button
    private final By deleteButton = By.id("titlebar-button-delete");

    // Confirm delete
    private final By confirmDelete = By.xpath("//*[@class=\"button disable-after-click\"]");

    //Log out
    private final By logOutButton = By.xpath("//*[@id=\"admin-menu\"]/li[3]/a");

    /**************************************************
     * Constructors
     **************************************************/

    public EditBlogEntryPage(WebDriver driver) {
        super(driver);
    }
    public EditBlogEntryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**************************************************
     * Methods
     **************************************************/


    @Story("Navigate to the public page")
    public void navigate() {
        driver.navigate().to(urlPublic);
    }

    @Story("Navigate to the admin login page")
    public void navigateAdminLoginPage() {
        driver.navigate().to(url);
    }

    @Story("Admin login")
    public void adminLogin(String username, String password) {
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Story("Change the title of the blog entry")
    public void changeTheTitle(String newTitle) {
        driver.findElement(titleField).clear();
        driver.findElement(titleField).sendKeys(newTitle);
        driver.findElement(successButton).click();
    }

    @Story("Success change the title of the blog entry")
    public void successChange() {
        driver.findElement(successButton).click();
    }

    @Story("Get the header text")
    public String headerText() {
        return driver.findElement(titleH1).getText();
    }

    @Story("Create a file to save the title and the date of the blog entry")
    public void createFile() {
        try {
            File myObj = new File(TestData.saveBlogEntryFilePath);
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

    @Story("Save the title and the date of the blog entry")
    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter(TestData.saveBlogEntryFilePath);

            String title = driver.findElement(titleH3).getText();
            String date = driver.findElement(dateOfPublish).getText();
            String text = driver.findElement(blogText).getText();

            myWriter.write(title + "\n");
            myWriter.write(date + "\n");
            myWriter.write(text + "\n");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Story("Delete blog entry")
    public void deleteBlogEntry() {
        driver.findElement(deleteButton).click();
        driver.findElement(confirmDelete).click();
    }

    @Story("Get page title")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Story("Log out editor user")
    public void logOutEditor() {
        driver.findElement(logOutButton).click();
    }

}
