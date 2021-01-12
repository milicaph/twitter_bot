package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SelUtils;

import java.util.List;

public class TwitterProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "div[data-testid*=follow]")
    private WebElement followButton;
    @FindBy(css = "div[data-testid=sendDMFromProfile]")
    private WebElement sendDM;
    @FindBy(css = "div[data-testid=confirmationSheetCancel]")
    private WebElement cancelWelcome;

    public TwitterProfilePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    // klik na follow dugme
    public void followProfile(){
        if(SelUtils.ifElementExists(driver, "div[data-testid=confirmationSheetCancel]")){
            cancelWelcome.click();
            try { Thread.sleep(8000); } catch(Exception e){}
        }

        try {
            SelUtils.waitToClickAndClick(followButton, wait);
        } catch (Exception e){e.printStackTrace();}
    }

    public void clickSendDM(){
        if(SelUtils.ifElementExists(driver, "div[data-testid=confirmationSheetCancel]")){
            cancelWelcome.click();
            try { Thread.sleep(8000); } catch(Exception e){}
        }

        try {
            SelUtils.waitToClickAndClick(sendDM, wait);
        } catch (Exception e){}
    }



}
