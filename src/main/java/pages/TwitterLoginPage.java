package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.Parameters;
import utilities.SelUtils;

import java.util.Properties;

public class TwitterLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "input[name*=username]")
    private WebElement username;
    @FindBy (css = "input[type=password]")
    private WebElement password;
    @FindBy (css = "div[data-testid=LoginForm_Login_Button]")
    private WebElement loginBtn;

    public TwitterLoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void loginToTwitter(){
        openLoginPage();
        username.sendKeys(Parameters.username);
        SelUtils.focusClickSendKeys(password, driver, Parameters.password);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public void openLoginPage(){
        driver.get(Parameters.url);
    }



}
