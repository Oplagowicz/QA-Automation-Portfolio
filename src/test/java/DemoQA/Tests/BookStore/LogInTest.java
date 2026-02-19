package DemoQA.Tests.BookStore;

import DemoQA.Helpers.ScreenshotOnFailureListener;
import DemoQA.Tests.BaseTest;
import DemoQA.data.Pages.BookStore.LogInPage;
import DemoQA.data.Pages.BookStore.ProfilePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotOnFailureListener.class)
public class LogInTest extends BaseTest {
    LogInPage loginPage = new LogInPage();
    ProfilePage profilePage = new ProfilePage();

    @BeforeMethod
    public void prep () {
        open(loginPage.loginUrl);
    }

    @Test
    public void testLogIn () {
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains(loginPage.loginUrl));


        WebElement loginNameField = getDriver().findElement(loginPage.loginNameField);
        wait.until(ExpectedConditions.visibilityOf(loginNameField));
        WebElement loginPasswordField = getDriver().findElement(loginPage.loginPasswordField);
        wait.until(ExpectedConditions.visibilityOf(loginPasswordField));
        WebElement loginButton = getDriver().findElement(loginPage.loginButton);
        wait.until(ExpectedConditions.visibilityOf(loginButton));

        loginNameField.clear();
        loginNameField.sendKeys(LogInPage.loginNameData);
        loginPasswordField.clear();
        loginPasswordField.sendKeys(loginPage.loginPasswordData);

        clickReliableBy(loginPage.loginButton);

        wait.until(ExpectedConditions.urlContains(profilePage.profileUrl));
        currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains(profilePage.profileUrl));

        WebElement userNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(profilePage.userNameLabel));

        Assert.assertEquals(userNameLabel.getText(), LogInPage.loginNameData);

    }


}
