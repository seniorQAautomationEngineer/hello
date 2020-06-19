import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignPage extends BaseActions {

  public SignPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
  }

public void fillInSigningForm(){
  wait.until(ExpectedConditions.elementToBeClickable(Locators.TEXT_FIELD_EMAIL_RECEIPENT));
  driver.findElement(Locators.TEXT_FIELD_EMAIL_RECEIPENT).sendKeys(Data.emailSender);
  driver.findElement(Locators.TEXT_FIELD_REQUEST_SUBJECT).sendKeys(Data.subjectEmail);
  driver.findElement(Locators.TEXTAREA_TEXT_MESSAGE).sendKeys(Data.message);

}

public void uploadFile(String pathToDoc){
  wait.until(ExpectedConditions.elementToBeClickable(Locators.BUTTON_UPLOAD_FILE));
  ajaxUploadFile(Locators.INPUT_UPLOAD_DOCUMENTS, pathToDoc);
  wait.until(ExpectedConditions.elementToBeClickable(Locators.BUTTON_FILL_OUT_AND_SIGN));

}

  public void openDocument(){
    ajaxClick(Locators.BUTTON_FILL_OUT_AND_SIGN);
  }
}
