import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserRegisterPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu/user_register/";

    //WebElements
    private final By fullNameField = By.xpath("//input[@name=\"data[fullname]\"]");

    public By getFullNameField() {
        return fullNameField;
    }
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");

    public By getUserNameField() {
        return userNameField;
    }
    private final By emailField = By.xpath("//input[@name=\"data[email]\"]");

    public By getEmailField() {
        return emailField;
    }

    private final By password1Field = By.xpath("//input[@name=\"data[password1]\"]");

    public By getPassword1Field() {
        return password1Field;
    }

    private final By password2Field = By.xpath("//input[@name=\"data[password2]\"]");

    public By getPassword2Field() {
        return password2Field;
    }

    private final By submitButton = By.xpath("//button[@type=\"submit\"]");
    private final By resetButton = By.xpath("//button[@type=\"reset\"]");


    public UserRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public void enterUserData(String fullName, String username, String email, String password1, String password2) {
        driver.findElement(fullNameField).sendKeys(fullName);
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(password1Field).sendKeys(password1);
        driver.findElement(password2Field).sendKeys(password2);
    }

    public void pressResetButton() {
        driver.findElement(resetButton).click();
    }

    public void pressSubmitButton() {
        driver.findElement(submitButton).click();
    }
}
