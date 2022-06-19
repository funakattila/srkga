import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserRegisterPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu/user_register/";

    //WebElements
    private final By pageTitle = By.tagName("h1");
    private final By fullNameField = By.xpath("//input[@name=\"data[fullname]\"]");
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");
    private final By emailField = By.xpath("//input[@name=\"data[email]\"]");
    private final By password1Field = By.xpath("//input[@name=\"data[password1]\"]");
    private final By password2Field = By.xpath("//input[@name=\"data[password2]\"]");
    private final By submitButton = By.xpath("//button[@type=\"submit\"]");
    private final By resetButton = By.xpath("//button[@type=\"reset\"]");


    public UserRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public String getPageTitle() {
        String pageTitleText = driver.findElement(pageTitle).getText();
        return pageTitleText;
    }

    public void enterUserData(String fullName, String username, String email, String password1, String password2) {
        driver.findElement(fullNameField).sendKeys(fullName);
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(password1Field).sendKeys(password1);
        driver.findElement(password2Field).sendKeys(password2);
    }

    public boolean isAllFieldEmpty() {
        String fullName = driver.findElement(fullNameField).getText();
        String userName = driver.findElement(userNameField).getText();
        String email = driver.findElement(emailField).getText();
        String password1 = driver.findElement(password1Field).getText();
        String password2 = driver.findElement(password2Field).getText();

        if(fullName.equals("") && userName.equals("") && email.equals("") && password1.equals("") && password2.equals(
                "")) {
            return true;
        }

        return false;
    }

    public boolean isOneFieldEmpty() {
        String fullName = driver.findElement(fullNameField).getText();
        String userName = driver.findElement(userNameField).getText();
        String email = driver.findElement(emailField).getText();
        String password1 = driver.findElement(password1Field).getText();
        String password2 = driver.findElement(password2Field).getText();

        if(fullName.equals("") || userName.equals("") || email.equals("") || password1.equals("") || password2.equals(
                "")) {
            return true;
        }

        return false;
    }

    public void pressResetButton() {
        driver.findElement(resetButton).click();
    }

    public void pressSubmitButton() {
        driver.findElement(submitButton).click();
    }
}
