import factory.*;
import object.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestObject {

    @Test
    public void likePostTest(){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded!");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUsername(DataProvider.loginUsername());
        loginPage.fillInPassword(DataProvider.loginPassword());

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded!");

        homePage.selectFirstPostPicture();

        homePage.clickLikeButton();
        Assert.assertTrue(homePage.isPostLiked(), "Post is not liked!");
    }
}
