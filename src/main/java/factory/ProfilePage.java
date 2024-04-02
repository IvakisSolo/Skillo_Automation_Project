package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    public final WebDriver webDriver;

    @FindBy(xpath = "//*[@class='row no-gutters']//app-post[1]//*[@class='post-img']")
    private WebElement firstPost;
    @FindBy(xpath = "//*[@class='delete-ask']//a")
    private WebElement deletePostButton;
    @FindBy(xpath = "//*[@class='delete-confirm']//button[1]")
    private WebElement deleteConfirmButton;
    public ProfilePage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }
    public void selectFirstPost(){
        firstPost.click();
    }
    public void deletePost(){
        deletePostButton.click();
    }
    public void deleteConfirm(){
        deleteConfirmButton.click();
    }
}