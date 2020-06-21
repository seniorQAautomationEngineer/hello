package com.hellosign.tests;

import com.hellosign.data.Data;
import org.testng.annotations.Test;

public class Main extends BaseUI {


    @Test
    public void testAllLinksAndImagesOnPage(){
        homePage.loginAsRegisteredUser(Data.emailUser1, Data.password);
        mainPage.checkLinksOnWebPage("//a", "href");
        mainPage.checkLinksOnWebPage("//img", "src");
    }
}

