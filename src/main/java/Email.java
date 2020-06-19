import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Email extends BaseActions {

    public Email(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openGmail(){
        String providerGmail = "ukrainiandreams1000@gmail.com";
        driver.get("https://accounts.google.com/AccountChooser?service=mail&amp;continue=https://mail.google.com/mail/");

        Reports.log("Type email: " + providerGmail);
        driver.findElement(Locators.EMAIL_GMAIL).sendKeys(providerGmail);

        Reports.log("Click Next button");
        driver.findElement(Locators.EMAIL_NEXT_BUTTON).click();

        //  mainPageIU.ajaxClick(By.xpath("//div[contains(@aria-label, 'Switch account')]"));

        wait.until(ExpectedConditions.elementToBeClickable(Locators.PASSWORD_GMAIL));
        driver.findElement(Locators.PASSWORD_GMAIL).sendKeys("Julia@1985");
        driver.findElement(Locators.PASSWORD_NEXT_BUTTON).click();
    }

    public void findMessageAndClickLinkOnGmail(String newProviderEmail){

        wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_INPUT));
        driver.findElement(Locators.SEARCH_INPUT).sendKeys(newProviderEmail);
        driver.findElement(Locators.SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//td//a[contains(@href,'https://app.hellosign.com')][contains(text(), 'View Document')]")));
        ajaxClick(By.xpath(".//td//a[contains(@href,'https://app.hellosign.com')][contains(text(), 'View Document')]"));
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        //driver.close();
        driver.switchTo().window(tabs2.get(1));
    }
}
