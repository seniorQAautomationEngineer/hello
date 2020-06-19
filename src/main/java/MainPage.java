import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage extends BaseActions {

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickSigningOption(String option) {

        Reports.log("Click root of page");
        ajaxClick(Locators.ROOT);

        Reports.log("Find Sign option: " + option);
        WebElement signingOption = driver.findElement(By.xpath("//a[@class='choose-sign-method__signing-option'][text()='" + option + "']"));

        Reports.log("Click sign in option: " + option);
        signingOption.click();

    }
}
