package com.hellosign.tests;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.hellosign.data.Data;
import com.hellosign.data.DataProviders;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(VideoListener.class)

public class CreateDocument extends BaseUI {
    public static final boolean testCase1 = true;


    @Video
    @Test(dataProvider = "Different combinations of documents and signatures", dataProviderClass = DataProviders.class,
            priority = 1, enabled = testCase1, groups = {"businessAccount"}, retryAnalyzer = com.hellosign.tests.RetryAnalyzer.class)


    public void setUpDocument(String signingOption, String pathToFile, String typesOfSignatures) {
        homePage.loginAsRegisteredUser(Data.emailUser1, Data.password);
        mainPage.clickSigningOption(signingOption);
        signPage.fillInSigningForm(signingOption, Data.emailUser1, Data.subjectEmail, Data.message);
        signPage.uploadFile(pathToFile);
        if (signingOption.contains(Data.signingOptionJustMe)) {
            signPage.openDocument();
            documentEditMode.switchToFrame();
            documentEditMode.addNewSignaturesToDocument();
            if (typesOfSignatures.contains(Data.drawItIn)) {
                documentEditMode.setAndClickSpanElement(Data.drawItIn);
                documentEditMode.drawSignature();
            } else if (typesOfSignatures.contains(Data.uploadImage)) {
                documentEditMode.setAndClickSpanElement(Data.uploadImage);
                documentEditMode.ajaxUploadFile(Data.pathToJpegFile);
            } else if (typesOfSignatures.contains(Data.typeItIn)) {
                documentEditMode.setAndClickSpanElement(Data.typeItIn);
                documentEditMode.createSignature(Data.fullName);
            } else if (typesOfSignatures.contains(Data.useSmartphone)) {
                documentEditMode.setAndClickSpanElement(Data.useSmartphone);
            }
            if (!typesOfSignatures.contains(Data.useSmartphone)) {
                documentEditMode.clickInsertButton();
            } else {
                documentEditMode.clickContinueButtonSignature();
            }
            documentEditMode.addNewInitialsToDocument();
            documentEditMode.addNewDateSignedToDocument();
            documentEditMode.addNewTextboxToDocument();
            documentEditMode.addNewCheckboxToDocument();
            documentEditMode.clickContinueButtonInEditMode();
        }
      //  signPage.clickSubmitButton();

    }
}
