package com.company;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TwitterDMPage;
import pages.TwitterLoginPage;
import pages.TwitterProfilePage;
import properties.DriverInitialization;
import properties.Parameters;
import utilities.ReadWriteExcel;
import utilities.SelUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        DriverInitialization initiateDriver = new DriverInitialization();
        WebDriver driver = initiateDriver.getDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 35L);

        ArrayList<String> twitterUrls = new ArrayList<>();
        ArrayList<String> twitchNames = new ArrayList<>();
        ArrayList<String> twitchGames = new ArrayList<>();
        ReadWriteExcel.fillInArrayListsFromExcel(twitterUrls, twitchNames, twitchGames);

        for(String twitterURL : twitterUrls){
            int indexOf = twitterUrls.indexOf(twitterURL);
            try { Thread.sleep(12000); } catch(Exception e){}

            TwitterLoginPage twitterLoginPage = new TwitterLoginPage(driver, wait);
            twitterLoginPage.openLoginPage();
            if(SelUtils.ifElementExists(driver, "input[name*=username]"))
                twitterLoginPage.loginToTwitter();

            driver.get(twitterURL);
            try { Thread.sleep(5000); } catch(Exception e){}

            TwitterProfilePage twitterProfilePage = new TwitterProfilePage(driver, wait);
            if(SelUtils.ifElementExists(driver, "div[data-testid*=follow]")) {
                twitterProfilePage.followProfile();
                ReadWriteExcel.writeFollowDate(twitterURL);
            }

            if(SelUtils.ifElementExists(driver, "div[data-testid=sendDMFromProfile]"))
                twitterProfilePage.clickSendDM();
            else {
                ReadWriteExcel.writeData("NO DM", twitterURL);
                continue;
            }

            String username = twitchNames.get(indexOf);
            String game = twitchGames.get(indexOf);

            String message = Parameters.messageToSend(username, game);

            TwitterDMPage twitterDMPage = new TwitterDMPage(driver, wait);
            twitterDMPage.sendMessage(message, twitterURL);


        }


        driver.quit();




    }
}
