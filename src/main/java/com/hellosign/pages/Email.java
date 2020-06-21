package com.hellosign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hellosign.utility.Reports;

import java.util.ArrayList;

public class Email extends BaseActions {

     By PASSWORD_GMAIL = By.xpath("//input[contains(@name, 'password')]");
     By EMAIL_GMAIL =  By.xpath(".//input[contains(@id, 'identifierId')]");
     By EMAIL_NEXT_BUTTON = By.xpath(".//div[contains(@id, 'identifierNext')]");
     By PASSWORD_NEXT_BUTTON =  By.xpath(".//div[contains(@id, 'passwordNext')]");
     By GOOGLE_APPS  =  By.xpath(".//a[@aria-label='Google apps']/../a");
     By GOOGLE_APPS_IFRAME  = By.xpath("//iframe[contains(@id, 'I0')]") ;
     By GMAIL_ICON  =  By.xpath("//span[@pid='23']") ;
     By SEARCH_INPUT =  By.xpath(".//form[@role='search']//input[@type='text']/../input");
     By SEARCH_BUTTON = By.xpath(".//button[@aria-label='Search mail']");

    public Email(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openGmail(){
        String providerGmail = "ukrainiandreams1000@gmail.com";
        driver.get("https://accounts.google.com/AccountChooser?service=mail&amp;continue=https://mail.google.com/mail/");

        Reports.log("Type com.hellosign.email: " + providerGmail);
        driver.findElement(EMAIL_GMAIL).sendKeys(providerGmail);

        Reports.log("Click Next button");
        driver.findElement(EMAIL_NEXT_BUTTON).click();

        //  mainPageIU.ajaxClick(By.xpath("//div[contains(@aria-label, 'Switch account')]"));

        wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_GMAIL));
        driver.findElement(PASSWORD_GMAIL).sendKeys("Julia@1985");
        driver.findElement(PASSWORD_NEXT_BUTTON).click();
    }

    public void findMessageAndClickLinkOnGmail(String newProviderEmail){

        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));
        driver.findElement(SEARCH_INPUT).sendKeys(newProviderEmail);
        driver.findElement(SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//td//a[contains(@href,'https://app.hellosign.com')][contains(text(), 'View Document')]")));
        ajaxClick(By.xpath(".//td//a[contains(@href,'https://app.hellosign.com')][contains(text(), 'View Document')]"));
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        //driver.close();
        driver.switchTo().window(tabs2.get(1));
    }
}
