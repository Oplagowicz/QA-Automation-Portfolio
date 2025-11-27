package DemoQA.data.Pages.BookStore;

import org.openqa.selenium.By;

public class LogInPage {
    public String loginUrl = "login";

    public By loginNameField = By.id("userName");
    public By loginPasswordField = By.id("password");
    public By loginButton = By.id("login");
    public By newUserButton = By.id("newUser");

    public String loginNameData = "ArtDem";
    public String loginPasswordData = "12345Qa!";
}
