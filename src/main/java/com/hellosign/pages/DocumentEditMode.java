package com.hellosign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hellosign.utility.Reports;


public class DocumentEditMode extends BaseActions {

    By ROOT = By.xpath("//div[@id='site-wrapper']");
    By TEXT_FIELD_CREATE_SIGNATURE = By.xpath("//input[@id='typedSignatureInput']");
    By BUTTON_CONTINUE_USE_SMARTPHONE = By.xpath("//button[@id='continueButton']");
    By IMAGE_DOCUMENT = By.xpath("//div[@id='page-0']//div[contains(@class, 'signature-request-document-page')]");
    By BUTTON_INSERT = By.xpath("//button[@data-qa-ref='button-insert-everywhere']");
    By BUTTON_CONTINUE_EDIT_MODE = By.xpath("//div[contains(@class,'src-hellospa-components-editor')]//button[@data-qa-ref='continue-button-prepare-docs']");
    By CANVAS_DRAW_SIGNATURE = By.xpath("//canvas[@id='drawSignatureCanvas']");
    By NEW_FIELD_SIGNATURE = By.xpath("//button[@data-qa-ref='new-field-signature']");
    By NEW_FIELD_INITIALS = By.xpath("//button[@data-qa-ref='new-field-initials']");
    By NEW_FIELD_DATE_SIGNED = By.xpath("//button[@data-qa-ref='new-field-date']");
    By NEW_FIELD_TEXTBOX = By.xpath("//button[@data-qa-ref='new-field-text']");
    By NEW_FIELD_CHECKBOX = By.xpath("//button[@data-qa-ref='new-field-checkbox']");


    public DocumentEditMode(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void switchToFrame() {
        javaWaitSec(3);
        Reports.log("Click root of page");
        ajaxClick(ROOT);

        Reports.log("Switch to iframe");
        driver.switchTo().frame("fancybox-frame");
    }

    public void dragAndDropElementOnDocument(By locator) {
        javaWaitSec(3);
        Reports.log("Find and scroll to image of document");
        WebElement element = driver.findElement(locator);
        ajaxScroll(IMAGE_DOCUMENT);

//        Reports.log("Drag and drop element on image");
//        element.click();

        Reports.log("Click and hold element on image");
        clickAndHold(element, IMAGE_DOCUMENT);


    }

    public void clickOnImageOfDocument() {
        driver.findElement(IMAGE_DOCUMENT).click();
    }


    public void drawSignature() {
        Reports.log("Find canvas");
        WebElement canvas = driver.findElement(CANVAS_DRAW_SIGNATURE);

        Reports.log("Draw signature");
        Actions act = new Actions(driver)
                .moveToElement(canvas).moveByOffset(-100, -100)
                .clickAndHold();
        for (int i = 0; i < 24; i++) {
            float a1 = (float) Math.sin(i) * 20;
            float a2 = (float) Math.sin(i + 1) * 20;
            if (i <= 13) {
                act = act.moveByOffset(10, (int) (a2 - a1));
            } else {
                act = act.moveByOffset((int) (Math.cos(i - 13) * 45), (int) (Math.sin(i - 12) * 10));
            }

        }
        act = act.release();
        act.perform();

    }

    public void clickInsertButton() {
        javaWaitSec(3);
        Reports.log("Wait Insert button");
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_INSERT));

        Reports.log("Click Insert button");
        ajaxClick(BUTTON_INSERT);
    }

    public void clickContinueButtonSignature() {
        Reports.log("Wait Continue button");
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_CONTINUE_USE_SMARTPHONE));

        Reports.log("Click Insert button");
        ajaxClick(BUTTON_CONTINUE_USE_SMARTPHONE);
    }


    public void clickContinueButtonInEditMode() {
        Reports.log("Scroll to Continue button");
        ajaxScroll(BUTTON_CONTINUE_EDIT_MODE);

        Reports.log("Click Continue button");
        ajaxClick(BUTTON_CONTINUE_EDIT_MODE);
    }

    public void addNewSignaturesToDocument() {
        dragAndDropElementOnDocument(NEW_FIELD_SIGNATURE);
        clickOnImageOfDocument();

    }

    public void addNewInitialsToDocument() {
        dragAndDropElementOnDocument(NEW_FIELD_INITIALS);
        clickOnImageOfDocument();

    }

    public void addNewDateSignedToDocument() {
        dragAndDropElementOnDocument(NEW_FIELD_DATE_SIGNED);
        clickOnImageOfDocument();

    }

    public void addNewTextboxToDocument() {
        dragAndDropElementOnDocument(NEW_FIELD_TEXTBOX);
        clickOnImageOfDocument();

    }

    public void addNewCheckboxToDocument() {
        dragAndDropElementOnDocument(NEW_FIELD_CHECKBOX);
        clickOnImageOfDocument();

    }

    public void createSignature(String fullName) {
        Reports.log("Wait Continue button in Use smartphone section");
        wait.until(ExpectedConditions.elementToBeClickable(TEXT_FIELD_CREATE_SIGNATURE));

        Reports.log("Clear pre-default values in signature");
        driver.findElement(TEXT_FIELD_CREATE_SIGNATURE).clear();

        Reports.log("Type new signature: " + fullName);
        driver.findElement(TEXT_FIELD_CREATE_SIGNATURE).sendKeys(fullName);
    }
}
