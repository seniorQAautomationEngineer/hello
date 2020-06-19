import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DocumentEditMode extends BaseActions {

  public DocumentEditMode(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
  }

public void switchToFrame(){
  javaWaitSec(3);
  ajaxClick(Locators.ROOT);
  driver.switchTo().frame("fancybox-frame");
  }

  public void dragAndDropElementOnDocument(By locator){
    javaWaitSec(3);
    WebElement element = driver.findElement(locator);
    ajaxScroll(Locators.IMAGE_DOCUMENT);
    element.click();
    clickAndHold(element,Locators.IMAGE_DOCUMENT);


  }

  public void clickOnImageOfDocument(){
    driver.findElement(Locators.IMAGE_DOCUMENT).click();
  }


  public void drawSignature(){
    WebElement canvas = driver.findElement(Locators.CANVAS_DRAW_SIGNATURE);
    Actions act = new Actions(driver)
            .moveToElement(canvas).moveByOffset(-100, -100)
            .clickAndHold();
    for (int i = 0; i <24 ; i++) {
      float a1 = (float)Math.sin(i)*20;
      float a2 = (float)Math.sin(i+1)*20;
      if(i <= 13){
        act = act.moveByOffset(10, (int)(a2-a1));
      }else{
        act = act.moveByOffset((int)(Math.cos(i-13)*45), (int)(Math.sin(i-12)*10));
      }

    }
            act = act.release();
            act.perform();

  }

  public void clickInsertButton(){
    wait.until(ExpectedConditions.elementToBeClickable(Locators.BUTTON_INSERT));
    ajaxClick(Locators.BUTTON_INSERT);
  }


  public void clickContinueButtonInEditMode(){
    ajaxScroll(Locators.BUTTON_CONTINUE_EDIT_MODE);
    ajaxClick(Locators.BUTTON_CONTINUE_EDIT_MODE);
  }



  public void addNewElementToDocument(By locator) {
    dragAndDropElementOnDocument(locator);
    clickOnImageOfDocument();
    setAndClickSpanElement("Draw it in");
    drawSignature();
    setAndClickSpanElement("Upload image");
    ajaxUploadFile(Locators.INPUT_UPLOAD_DOCUMENTS, Data.pathToJpegFile);
    clickInsertButton();
  }
}
