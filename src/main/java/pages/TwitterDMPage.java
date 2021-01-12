package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.Parameters;
import utilities.ReadWriteExcel;
import utilities.SelUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwitterDMPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "div[data-testid=dmComposerTextInput]")
    private WebElement textInput;
    @FindBy(css = "div[data-testid=dmComposerSendButton]")
    private WebElement sendBtn;
    @FindBy(css = "div[data-testid=messageEntry]")
    private List<WebElement> sentMessages;

    public TwitterDMPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void sendMessage(String message, String twitterUrl) throws IOException {
        if(!isAnyMessageSent()) {
            SelUtils.focusClickSendKeys(textInput, driver, message);
            SelUtils.waitToClickAndClick(sendBtn, wait);
            ReadWriteExcel.writeData("DM SENT", twitterUrl);
        } else {
            System.out.println("MESSAGE ALREADY SENT");
            ReadWriteExcel.writeData("MESSAGE ALREADY SENT", twitterUrl);
        }
    }

    private ArrayList<String> readAllMessages(){
        ArrayList<String> elementStrings = new ArrayList<>();
        sentMessages.forEach(sentMessage -> elementStrings.add(SelUtils.removeHTML(sentMessage.getAttribute("innerHTML"))));

        return elementStrings;
    }

    private boolean isAnyMessageSent(){
        try { Thread.sleep(5000); } catch(Exception e){}
        System.out.println("Already messages: " + driver.findElements(By.cssSelector("div[data-testid=messageEntry]")).size());
        return SelUtils.ifElementExists(driver, "div[data-testid=messageEntry]");
    }

    /*
    public boolean messageIsSentExact(String message){
        for(String messageRead : readAllMessages()){
            System.out.println(messageRead);
            if(messageRead.contains(message))
                return true;
        }
        return false;
    }
*/


}
