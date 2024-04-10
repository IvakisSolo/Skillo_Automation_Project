import factory.*;
import object.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTests extends TestObject {

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

        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");
        header.clickProfilePage();

        header.clickLogout();
    }

    @Test
    public void testPostPicture(){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login page!");

        header.clickLogin();

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUsername(DataProvider.loginUsername());
        loginPage.fillInPassword(DataProvider.loginPassword());

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is profile page!");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostLoaded(), "The New Post form is not loaded!");

        postPage.uploadPicture(DataProvider.getUsersPicture());
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("mona_cat.jpg"), "Image is not uploaded!");
        Assert.assertEquals(actualImageText, "mona_cat.jpg", "Incorrect image is uploaded!");

        postPage.typePostCaption(DataProvider.getUsersCaption());
        postPage.clickCreatePost();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page!");
    }

    @Test
    public void deletePostPicture(){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login page!");

        header.clickLogin();

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUsername(DataProvider.loginUsername());
        loginPage.fillInPassword(DataProvider.loginPassword());

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is profile page!");

        profilePage.selectFirstPost();
        profilePage.deletePost();
        profilePage.deleteConfirm();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page!");
    }

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
    }
}