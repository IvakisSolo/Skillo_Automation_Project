package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";

    private final WebDriver webDriver;

    @FindBy(xpath = "//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x']")
    private WebElement likeButton;
    @FindBy(xpath = "//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x liked']")
    private WebElement likedButton;
    @FindBy(xpath = "//*[@class='row post-list-container ng-star-inserted']//*[@class='col-12 offset-md-1 col-md-10 offset-lg-3 col-lg-6 ng-star-inserted'][1]/app-post-detail//*[@class='post-feed-img']/img")
    private WebElement firstPostPicture;

    public HomePage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public void navigateTo(){
        this.webDriver.get(HOME_URL);
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
    public void selectFirstPostPicture(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(firstPostPicture));
        firstPostPicture.click();
    }
    public void clickLikeButton(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        likeButton.click();
    }
    public boolean isPostLiked(){
        WebDriverWait wait = new WebDriverWait(this.webDriver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(likedButton));
        return true;
    }
}