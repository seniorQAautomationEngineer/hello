package com.hellosign.tests;

import com.hellosign.data.Data;
import com.hellosign.email.EmailUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.Message;
import javax.mail.MessagingException;

public class EmailTests extends BaseUI {

    EmailUtils emailUtils = new EmailUtils(Data.emailUser2, Data.passwordEmail, "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
    public EmailTests() throws MessagingException {
    }


    @Test
    public void testLink() {

        //TODO: apply for a loan using criteria that will result in the application being rejected
        try {
            Message email = emailUtils.getMessagesBySubject("You've received a document via HelloSign", true, 5)[0];
            String link = emailUtils.getUrlsFromMessage(email, Data.mainUrl + "/t").get(0);

            driver.get(link);

            //TODO: continue testing
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
