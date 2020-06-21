package com.hellosign.pages;

import com.hellosign.data.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hellosign.utility.Reports;


public class SignPage extends BaseActions {

    By TEXT_FIELD_EMAIL_SEND_RECIPIENT = By.xpath("//input[@id='tsm_group_send_recipient1']");
    By TEXT_FIELD_EMAIL_REQUEST_RECIPIENT = By.xpath("//input[@id='tsm_group_request_recipient1']");
    By TEXT_FIELD_REQUEST_SUBJECT = By.xpath("//input[@id='request_subject']");
    By TEXTAREA_TEXT_MESSAGE = By.xpath("//textarea[@id='request_message']");
    By BUTTON_UPLOAD_FILE = By.xpath("//span[@class='upload_files_button']");
    By BUTTON_FILL_OUT_AND_SIGN = By.xpath("//a[contains(@id, 'edit_link')]");
    By BUTTON_SEND_SUBMIT = By.xpath("//button[@id='tsm_group_send_submit']");



    public SignPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void fillInSigningForm(String signingOption, String emailSender, String subjectEmail, String message) {

        if (signingOption.contains(Data.signingOptionJustMe)) {
            Reports.log("Wait until com.hellosign.pages.Email recipient text field is displayed");
            wait.until(ExpectedConditions.elementToBeClickable(TEXT_FIELD_EMAIL_SEND_RECIPIENT));

            Reports.log("Type in com.hellosign.pages.Email recipient text field: " + emailSender);
            driver.findElement(TEXT_FIELD_EMAIL_SEND_RECIPIENT).sendKeys(emailSender);
        } else {
            Reports.log("Wait until com.hellosign.pages.Email recipient text field is displayed");
            wait.until(ExpectedConditions.elementToBeClickable(TEXT_FIELD_EMAIL_REQUEST_RECIPIENT));

            Reports.log("Type in com.hellosign.pages.Email recipient text field: " + emailSender);
            driver.findElement(TEXT_FIELD_EMAIL_REQUEST_RECIPIENT).sendKeys(emailSender);
        }

        Reports.log("Type Request subject text field " + Data.subjectEmail);
        driver.findElement(TEXT_FIELD_REQUEST_SUBJECT).sendKeys(subjectEmail);
        driver.findElement(TEXTAREA_TEXT_MESSAGE).sendKeys(message);

    }

    public void uploadFile(String pathToDoc) {
        Reports.log("Wait until Upload file button is displayed");
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_UPLOAD_FILE));

        ajaxUploadFile(pathToDoc);

        Reports.log("Wait Fill and out pages.sign button");
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_FILL_OUT_AND_SIGN));

    }

    public void openDocument() {
        Reports.log("Click Fill and out pages.sign button");
        ajaxClick(BUTTON_FILL_OUT_AND_SIGN);
    }

    public void clickSubmitButton(){
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_SEND_SUBMIT));
        driver.findElement(BUTTON_SEND_SUBMIT);
    }
}
