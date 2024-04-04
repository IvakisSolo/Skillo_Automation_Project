package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";

    private final WebDriver webDriver;

    public HomePage(WebDriver driver){
        this.webDriver = driver;
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
        WebElement firstPostPicture =  wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[@class='container']//app-post-detail[1]//*[@class='post-feed-img']/img")));
        firstPostPicture.click();
    }
    public void clickLikeButton(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        WebElement likeButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath("//div[@class='post-modal-container']//i[1]")));
        likeButton.click();
    }
}