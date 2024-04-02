package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver webDriver;

    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[1]/input")
    private WebElement usernameTextField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[2]/input")
    private WebElement emailTextField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[3]/input")
    private WebElement birthDateTextField;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordTextField;
    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordTextField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[6]/textarea")
    private WebElement publicInfoField;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    public RegisterPage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public void fillUserName(String username){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        usernameTextField.sendKeys(username);
    }

    public void fillEmail(String email){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
    }

    public void fillBirthDate(String birthdate){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(birthDateTextField));
        birthDateTextField.sendKeys(birthdate);
    }

    public void fillPassword(String password) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(password);
    }

    public void fillConfirmPassword(String confirmpassword){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordTextField));
        confirmPasswordTextField.sendKeys(confirmpassword);
    }

    public void fillPublicInfo(String publicinfo){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(publicInfoField));
        publicInfoField.sendKeys(publicinfo);
    }

    public void clickSignIn(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
}
