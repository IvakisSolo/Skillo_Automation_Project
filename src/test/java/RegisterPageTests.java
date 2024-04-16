import factory.*;
import object.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterPageTests extends TestObject {

    @Test
    public void registerUser(){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.clickRegister();
        Assert.assertTrue(registerPage.isUrlLoaded(), "Current page is not register");

        registerPage.fillUserName(DataProvider.generateRandomUsername());
        registerPage.fillEmail(DataProvider.generateRandomEmail());
        registerPage.fillPassword(DataProvider.generateRandomPassword());
        registerPage.fillConfirmPassword(DataProvider.generateRandomConfirmPassword());
        registerPage.clickSignIn();

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");
    }
}
