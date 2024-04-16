import factory.*;
import object.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends TestObject {

    @Test
    public void loginAndOutTest(){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUsername(DataProvider.loginUsername());
        loginPage.fillInPassword(DataProvider.loginPassword());

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded!");

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");

        header.clickLogout();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
    }
}