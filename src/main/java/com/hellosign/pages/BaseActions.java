package com.hellosign.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import com.hellosign.utility.Reports;
public class BaseActions {

    By H1_TITLE = By.xpath("//h1");
    By INPUT_UPLOAD_DOCUMENTS = By.xpath(".//input[@type=\'file\']");


        protected WebDriver driver;
        protected WebDriverWait wait;

        public BaseActions(WebDriver driver, WebDriverWait wait){
            this.driver = driver;
            this.wait = wait;
        }


        // Different varieties of Ajax click
        public void ajaxClick(WebElement element) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }

        public void ajaxClick(By by){
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            ajaxClick(driver.findElement(by));
        }

        public void ajaxClick(By by, int index){
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            ajaxClick(driver.findElements(by).get(index));
        }

        public void performClick(By locator){
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            actions.click().build().perform();
        }

        public void performClick(By locator, int index){
            WebElement element = driver.findElements(locator).get(index);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            actions.click().build().perform();
        }

        public void performClick(WebElement element){
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            actions.click().build().perform();
        }

        public void clickUnselectedCheckbox(By checkbox){
            WebElement currentCheckbox = driver.findElement(checkbox);
            if (!currentCheckbox.isSelected()){
                ajaxClick(currentCheckbox);
            }
        }

        public void clickUnselectedCheckbox(WebElement currentCheckbox){
            if (!currentCheckbox.isSelected()){
                ajaxClick(currentCheckbox);
            }
        }

        public void clickUnselectedCheckbox(By checkbox, int index){
            WebElement currentCheckbox = driver.findElements(checkbox).get(index);
            if (!currentCheckbox.isSelected()){
                ajaxClick(currentCheckbox);
            }
        }

        // Scrolls
        public void scrollToBottomOfPage(){
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }

        public void ajaxScroll(WebElement element){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
        public void ajaxScroll(By locator){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
        }

        public void ajaxScroll(By by, int index) {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            ajaxScroll(driver.findElements(by).get(index));
        }

        public void ajaxScrollUp(){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-250)", "");
        }


        public static String generateNewNumber(String name, int length){

            return name + RandomStringUtils.random(length, "172984757");
        }

        public void getDropDownListByIndex(WebElement element, int index){
            Select select = new Select(element);
            select.selectByIndex(index);
        }
        public void getDropDownListByIndex(By locator, int index){
            Select select = new Select(driver.findElement(locator));
            select.selectByIndex(index);
        }

        public void getDropDownListByText(WebElement element, String text){
            Select select = new Select(element);
            select.selectByVisibleText(text);

        }

        public void getDropDownListByValue(WebElement element, String value){
            Select select = new Select(element);
            select.selectByValue(value);
        }




        public void javaWait(int ms){
            System.out.println("Parent!!!!");
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        public void javaWaitSec(int sec){
            try {
                Thread.sleep(sec * 1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        public void ajaxSendKeys(WebElement element, String text){
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text +"')", element);
        }





        public static String generateRandomString(){
            return new BigInteger(120, new SecureRandom()).toString(32);
        }

        //Method for random choice from dropdown list
        public void selectItemDropDownRandomOption(By locator, String dropDownName){
            try {
                WebElement element = driver.findElement(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Select select = new Select(driver.findElement(locator));
                select.selectByIndex((int) (Math.random() * (select.getOptions().size() - 1)) + 1);
                System.out.println(dropDownName + ": " + select.getFirstSelectedOption().getText());
            } catch (NoSuchElementException e){

            }
        }

        public void checkLinksOnWebPage(String typeElement, String attribute){

            List<WebElement> links = driver.findElements(By.xpath(typeElement));

            System.out.println("I start taking  attributes on page");
            for (int i = 0; i < links.size(); i++) {
                WebElement ele = links.get(i);
                String url = ele.getAttribute(attribute);
                verifyLinkActive(url);
                links = driver.findElements(By.xpath(typeElement));
            }

            System.out.println("Total links are " + links.size());
        }
        // Method for link verification
        public void verifyLinkActive (String linkUrl){
            try {
                URL url = new URL(linkUrl);
                HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
                httpURLConnect.setConnectTimeout(3000);
                httpURLConnect.connect();
                if (httpURLConnect.getResponseCode() == 200){
                    System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                }
                else  if (httpURLConnect.getResponseCode() >=400 && httpURLConnect.getResponseCode()<= 504){
                    System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + httpURLConnect.getResponseMessage());
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        public int getSizeDropDownList(By locator) {
            try {
                WebElement element = driver.findElement(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Select select = new Select(driver.findElement(locator));
                return select.getOptions().size();

            } catch (NoSuchElementException e) {
                System.out.println("getSizedropDownList error");

            }
            return 0;

        }
        public void clickValueOfLists(By locator, String text){

            Reports.log("Collect elements in list");
            List<WebElement> elements = driver.findElements(locator);

            Reports.log("Start using loop with size of list");
            for (int i = 0; i <elements.size() ; i++) {

                Reports.log("Create new webelement of list");
                WebElement elementOfList = elements.get(i);

                Reports.log("Create new String with text from element of list");
                String value = elementOfList.getText();
                Reports.log("Value of list: " + value);

                if(value.contains(text)){
                    Reports.log("Wait element list is clickable");
                    wait.until(ExpectedConditions.elementToBeClickable(elementOfList));

                    Reports.log("Click list of elements");
                    elementOfList.click();
                }
            }
        }



        public String getAnyTitle(){
           String title = driver.findElement(H1_TITLE).getText();
           return title;
        }


        public void ajaxUploadFile(String nameOfFile) {
            //  WebElement element = setAndFindButton(com.hellosign.data.Data.TEXT_UPLOAD_FILES);
            Reports.log("Upload file");
            WebElement element = driver.findElement(INPUT_UPLOAD_DOCUMENTS);
            ((JavascriptExecutor) driver)
                    .executeScript(("arguments[0].style = \"\"; arguments[0].style.display = \"block\"; arguments[0].style.visibility = \"visible\";"), element);
          //  wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(new File(nameOfFile).getAbsolutePath());

        }

        public void dragAndDrop(By locator, By target) {
            //Element which needs to drag.
            WebElement from = driver.findElement(locator);

            //Element on which need to drop.
            WebElement to = driver.findElement(target);

            //Using Action class for drag and drop.
            Actions act = new Actions(driver);

            //Dragged and dropped.
            act.dragAndDrop(from, to).build().perform();
            act.click().perform();
        }

        public void clickAndHold(By locator, By target) {
            Actions action = new Actions(driver);
            Actions dragdrop = action.clickAndHold(driver.findElement(locator)).moveToElement(driver.findElement(target));
            dragdrop.perform();
        }
        public void clickAndHold(WebElement element, By target) {
            Actions action = new Actions(driver);
           // Actions dragdrop = action.clickAndHold(element).moveToElement(driver.findElement(target));
            Actions dragdrop = action.clickAndHold(element).moveToElement(driver.findElement(target));
            dragdrop.perform();
        }

        public void setAndClickSpanElement(String text){
            Reports.log("Find and click: " + text);
            driver.findElement(By.xpath("//span[text() ='"+text+"']")).click();
        }

        public void doublePerformClick(WebElement element) {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
        }

        public void doublePerformClick(By locator) {
            Actions actions = new Actions(driver);
            actions.doubleClick(driver.findElement(locator)).perform();
        }

        public WebElement advanceFindElement(By by, long timeout, long period){
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofSeconds(period))
                    .ignoring(org.openqa.selenium.NoSuchElementException.class);
            return fluentWait.until(d-> d.findElement(by));
        }
    }

