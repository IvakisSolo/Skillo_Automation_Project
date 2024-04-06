import factory.*;
import object.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

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

    @org.testng.annotations.DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"Ivakis", "Qwerty1"}
        };
    }

    @Test(dataProvider = "getUser")
    public void loginAndOutTest(String username, String password){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        header.clickLogin();

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUsername(username);
        loginPage.fillInPassword(password);

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");

        header.clickLogout();
    }

    @org.testng.annotations.DataProvider(name = "getUserData")
    public Object[][] getUsersData(){
        File postPicture = new File("src\\test\\resources\\upload\\mona_cat.jpg");
        String caption = "A picture of my cat";
        return new Object[][]{
                {"Ivakis", "Qwerty1", postPicture, caption}
        };
    }

    @Test(dataProvider = "getUserData")
    public void testPostPicture(String username, String password, File postPicture, String caption){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login page!");

        loginPage.completeSignIn(username, password);

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is profile page!");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostLoaded(), "The New Post form is not loaded!");

        postPage.uploadPicture(postPicture);
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("mona_cat.jpg"), "Image is not uploaded!");
        Assert.assertEquals(actualImageText, "mona_cat.jpg", "Incorrect image is uploaded!");

        postPage.typePostCaption(caption);
        postPage.clickCreatePost();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page!");
    }

    @Test(dataProvider = "getUser")
    public void deletePostPicture(String username, String password){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login page!");

        loginPage.completeSignIn(username, password);

        header.clickProfilePage();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is profile page!");

        profilePage.selectFirstPost();
        profilePage.deletePost();
        profilePage.deleteConfirm();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page!");
    }

    @Test(dataProvider = "getUser")
    public void likePostTest(String username, String password){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded!");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login!");

        loginPage.completeSignIn(username, password);
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded!");

        homePage.selectFirstPostPicture();

        homePage.clickLikeButton();
    }
}