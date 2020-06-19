import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.mail.Message;
import javax.mail.MessagingException;

public class EmailTests extends BaseUI{

        EmailUtils emailUtils = new EmailUtils("alex.lavre2@gmail.com", "Naknek@1985", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);

    public EmailTests() throws MessagingException {
    }


    @Test
    public void testLink() {

        //TODO: apply for a loan using criteria that will result in the application being rejected

        try{
            Message email = emailUtils.getMessagesBySubject("Welcome to ProviderPortal", true, 5)[0];
            String link = emailUtils.getUrlsFromMessage(email, "https://sit-dyp.hhstechgroup.com/login").get(0);

            driver.get(link);

            //TODO: continue testing
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
