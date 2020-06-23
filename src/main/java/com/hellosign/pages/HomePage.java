package com.hellosign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hellosign.utility.Reports;


public class HomePage extends BaseActions {

    By TEXT_FIELD_EMAIL = By.xpath("//input[@name='logIn.emailAddress']");
    By TEXT_FIELD_PASSWORD = By.xpath("//input[@name='logIn.password']");
    By BUTTON_CONTINUE_LOGIN = By.xpath("//button[@data-qa-ref='continue-button-login-page']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void loginAsRegisteredUser(String email, String password) {
        Reports.log("Type text field: " + email);
        driver.findElement(TEXT_FIELD_EMAIL).sendKeys(email);

        Reports.log("Click Continue button");
        ajaxClick(BUTTON_CONTINUE_LOGIN);

        Reports.log("Wait password text field");
        wait.until(ExpectedConditions.elementToBeClickable(TEXT_FIELD_PASSWORD));

        Reports.log("Type password in text field: " + password);
        driver.findElement(TEXT_FIELD_PASSWORD).sendKeys(password);

        Reports.log("Click Continue button");
        ajaxClick(BUTTON_CONTINUE_LOGIN);

        Reports.log("Click Continue button");
        javaWaitSec(1);
        ajaxClick(BUTTON_CONTINUE_LOGIN);

    }

}
