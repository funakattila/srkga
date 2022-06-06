import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewBlogEntryPage extends PageBase{

    private final String url = "http://srkgakezilabda.hu/admin/pages";

    //Log in settings
    private final String username = "janedoe";
    private final String password = "JaNeDoE#1";
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");
    private final By passwordField = By.xpath("//input[@name=\"data[password]\"]");
    private final By loginButton = By.xpath("//button[@value=\"login\"]");


    private final By addButton = By.xpath("//*[@id=\"titlebar-add\"]/button[1]");

    //add page modal
    private final By titleField = By.xpath("//*[@id=\"new-page\"]//input[@name=\"data[title]\"]");
    private final By folderField = By.xpath("//*[@id=\"new-page\"]//input[@name=\"data[folder]\"]");
    private final By route = By.xpath("//*[@id=\"new-page\"]//div[@data-parents=\"data[route]\"]");
    private final By setRouteToBlog = By.xpath("//li[@data-fjs-item=\"blog\"]/a");
    private final By continueButton = By.xpath("//*[@data-remodal-id=\"parents\"]//div[@class=\"button-bar\"]/a[2]");
    private final By dropdownList = By.xpath("//*[@id=\"new-page\"]//*[@class=\"selectize-input items full " +
            "has-options has-items\"]");
    private final By itemTemplate = By.xpath("//*[@data-value=\"item\"]");
    private final By successButton = By.xpath("//*[@class=\"button success\"]");

    //add page tabs
    //content tab
    private final By contentTabLink = By.xpath("//*[@class=\"tabs-nav\"]/a[1]");
    private final By blogText = By.xpath("//pre[@role=\"presentation\"]/span");

    private final String text = """
                                ## Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                            
                                Cras aliquam **ullamcorper** felis non aliquam. Vivamus fermentum porta massa, ultrices vestibulum metus sollicitudin in. Vivamus ultricies porttitor semper. Pellentesque quis orci dolor. Nunc in libero _efficitur_, rutrum nibh in, dictum nibh. Etiam a sollicitudin eros. Nam consectetur pharetra dolor a efficitur. Praesent eu ex vitae _lorem_ egestas eleifend viverra a lectus.
                                            
                                1. Pellentesque venenatis
                                2. Tortor in dolor hendrerit
                                3. Vel interdum
                                4. Sapien suscipit
                                """;

    //options tab
    private final By optionsTabLink = By.xpath("//*[@class=\"tabs-nav\"]/a[2]");
    //private final By unpublishedButton = By.xpath("//*[@for=\"toggle_header.published0\"]");

    private final By saveContentButton = By.xpath("//button[@type=\"submit\"]");

    public AddNewBlogEntryPage(WebDriver driver) {
        super(driver);
    }

    public void navigateAndLogin() {
        driver.navigate().to(url);
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

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
        driver.findElement(blogText).sendKeys(text);
        driver.findElement(optionsTabLink).click();
        //driver.findElement(unpublishedButton).click();
        driver.findElement(saveContentButton).click();
    }

}
