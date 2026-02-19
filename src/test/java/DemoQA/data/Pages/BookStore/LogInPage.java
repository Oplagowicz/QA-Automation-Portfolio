package DemoQA.data.Pages.BookStore;

import org.openqa.selenium.By;

import static DemoQA.Helpers.RandomAlphaNumeric.randomAlphaNumeric;

public class LogInPage {

    public String loginUrl = "login";

    public By loginNameField = By.id("userName");
    public By loginPasswordField = By.id("password");
    public By loginButton = By.id("login");
    public By newUserButton = By.id("newUser");

    public static String loginNameData = "ArtDem";
    public static String loginPasswordData = "12345Qa!";
}
