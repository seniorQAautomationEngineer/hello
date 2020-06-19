import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(VideoListener.class)

public class LoginTests extends BaseUI {
    public static final boolean testCase1 = true;

    // retryAnalyzer = RetryAnalyzer.class
    @Video
    @Test(dataProvider = "Different extensions of documents", dataProviderClass = DataProviders.class,
            priority = 1, enabled = testCase1, groups = {"businessAccount"})
    public void loginTests(String pathToFile){
        homePage.loginAsRegisteredUser();
        mainPage.clickSigningOption(Data.signingOptionJustMe);
        signPage.fillInSigningForm();
        signPage.uploadFile(pathToFile);
        signPage.openDocument();
        documentEditMode.switchToFrame();
        documentEditMode.addNewElementToDocument(Locators.NEW_FIELD_SIGNATURE);
        documentEditMode.dragAndDrop(Locators.NEW_FIELD_INITIALS, Locators.IMAGE_DOCUMENT);
        documentEditMode.dragAndDrop(Locators.NEW_FIELD_DATE_SIGNED, Locators.IMAGE_DOCUMENT);
        documentEditMode.dragAndDrop(Locators.NEW_FIELD_TEXTBOX, Locators.IMAGE_DOCUMENT);
       // driver.findElement(Locators.TEXTAREA_ON_DOCUMENT).sendKeys("Alex");
        documentEditMode.dragAndDrop(Locators.NEW_FIELD_TEXTBOX, Locators.NEW_FIELD_CHECKBOX);
        documentEditMode.clickContinueButtonInEditMode();
        wait.until(ExpectedConditions.elementToBeClickable(Locators.BUTTON_SEND_SUBMIT));

        //  driver.findElement(Locators.BUTTON_SEND_SUBMIT);
       // signPage.javaWaitSec(10);
      //  email.openGmail();
    //    email.findMessageAndClickLinkOnGmail(Data.emailReceiver);


    }
}
