package com.hellosign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hellosign.utility.Reports;


public class MainPage extends BaseActions {

    By ROOT = By.xpath("//div[@id='site-wrapper']");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickSigningOption(String option) {
        javaWaitSec(5);
        Reports.log("Wait root of page each 5 seconds");
        advanceFindElement(ROOT, 5, 10);

        Reports.log("Click root of page");
        ajaxClick(ROOT);

        Reports.log("Find Sign option: " + option);
        WebElement signingOption = driver.findElement(By.xpath("//a[@class='choose-sign-method__signing-option'][text()='" + option + "']"));

        Reports.log("Click pages.sign in option: " + option);
        signingOption.click();

    }
}
