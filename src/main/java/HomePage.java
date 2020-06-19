import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BaseActions {

  public HomePage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
  }

  public void loginAsRegisteredUser(){
       Reports.log("Type text field: " + Data.emailSender);
       driver.findElement(Locators.TEXT_FIELD_EMAIL).sendKeys(Data.emailSender);

       Reports.log("Click Continue button");
       ajaxClick(Locators.BUTTON_CONTINUE_LOGIN);

       Reports.log("Wait password text field");
       wait.until(ExpectedConditions.elementToBeClickable(Locators.TEXT_FIELD_PASSWORD));

       Reports.log("Type password in text field: " + Data.password);
       driver.findElement(Locators.TEXT_FIELD_PASSWORD).sendKeys(Data.password);

       Reports.log("Click Continue button");
       ajaxClick(Locators.BUTTON_CONTINUE_LOGIN);

       Reports.log("Click Continue button");
       ajaxClick(Locators.BUTTON_CONTINUE_LOGIN);
     }

}
