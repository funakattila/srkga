import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRegisterPage extends BasePage {

    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this page
    private final String url = "http://srkgakezilabda.hu/user_register/";

    // Title of the page
    private final By pageTitle = By.tagName("h1");

    // Full name field
    private final By fullNameField = By.xpath("//input[@name=\"data[fullname]\"]");

    // Username filed
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");

    //Email field
    private final By emailField = By.xpath("//input[@name=\"data[email]\"]");

    // Password field
    private final By password1Field = By.xpath("//input[@name=\"data[password1]\"]");

    // Password again field
    private final By password2Field = By.xpath("//input[@name=\"data[password2]\"]");

    // Submit button
    private final By submitButton = By.xpath("//button[@type=\"submit\"]");

    // Reset button
    private final By resetButton = By.xpath("//button[@type=\"reset\"]");


    /**************************************************
     * Constructors
     **************************************************/

    public UserRegisterPage(WebDriver driver) {
        super(driver);
    }

    public UserRegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**************************************************
     * Methods
     **************************************************/


    @Step("Navigate to the user register page - {url}")
    public void navigate() {
        driver.navigate().to(url);
    }

    @Step("Get the title of the page")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Enter the data of the new user - username: {0}, email: {1}, password: {2}")
    public void enterUserData(String fullName, String username, String email, String password1, String password2) {
        driver.findElement(fullNameField).sendKeys(fullName);
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(password1Field).sendKeys(password1);
        driver.findElement(password2Field).sendKeys(password2);
    }

    @Step("Check are all the fields empty")
    public boolean areAllFieldsEmpty() {
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

    @Step("Check is there an empty field")
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

    @Step("Create new users from a txt file: {file}")
    public List<String[]> addUsersFromFile(String file) {
        List<String[]> users = new ArrayList<String[]>();

        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] user = data.split("; ");
                users.add(user);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return users;
    }

    @Step("Click the reset button to clear the form")
    public void clickResetButton() {
        driver.findElement(resetButton).click();
    }

    @Step("Click submit button to send form")
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }
}
