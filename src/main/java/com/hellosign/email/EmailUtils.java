package com.hellosign.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;


public class EmailUtils {

    private Folder folder;

    public enum EmailFolder {
        INBOX("INBOX"),
        SPAM("SPAM");

        private String text;

        private EmailFolder(String text){
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * Uses com.hellosign.email.username and com.hellosign.email.password properties from the properties file. Reads from Inbox folder of the com.hellosign.email application
     * @throws MessagingException
     */
    public EmailUtils() throws MessagingException {
        this(EmailFolder.INBOX);
    }

    /**
     * Uses username and password in properties file to read from a given folder of the com.hellosign.email application
     * @param emailFolder Folder in com.hellosign.email application to interact with
     * @throws MessagingException
     */
    public EmailUtils(EmailFolder emailFolder) throws MessagingException {
        this(getEmailUsernameFromProperties(),
                getEmailPasswordFromProperties(),
                getEmailServerFromProperties(),
                emailFolder);
    }

    /**
     * Connects to com.hellosign.email server with credentials provided to read from a given folder of the com.hellosign.email application
     * @param username com.hellosign.pages.Email username (e.g. janedoe@com.hellosign.email.com)
     * @param password com.hellosign.pages.Email password
     * @param server com.hellosign.pages.Email server (e.g. smtp.com.hellosign.email.com)
     * @param emailFolder Folder in com.hellosign.email application to interact with
     */
    public EmailUtils(String username, String password, String server, EmailFolder emailFolder) throws MessagingException {
        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("C:\\Users\\oleksii.lavrenin\\Downloads\\hellosign\\src\\main\\resources\\com.hellosign.email.properties")));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Session session = Session.getInstance(props);
        Store store = session.getStore("imaps");
        store.connect(server, username, password);


        folder = store.getFolder(emailFolder.getText());
        folder.open(Folder.READ_WRITE);
    }



    //************* GET EMAIL PROPERTIES *******************

    public static String getEmailAddressFromProperties(){
        return System.getProperty("com.hellosign.email.address");
    }

    public static String getEmailUsernameFromProperties(){
        return System.getProperty("com.hellosign.email.username");
    }

    public static String getEmailPasswordFromProperties(){
        return System.getProperty("com.hellosign.email.password");
    }

    public static String getEmailProtocolFromProperties(){
        return System.getProperty("com.hellosign.email.protocol");
    }

    public static int getEmailPortFromProperties(){
        return Integer.parseInt(System.getProperty("com.hellosign.email.port"));
    }

    public static String getEmailServerFromProperties(){
        return System.getProperty("com.hellosign.email.server");
    }




    //************* EMAIL ACTIONS *******************

    public void openEmail(Message message) throws Exception{
        message.getContent();
    }

    public int getNumberOfMessages() throws MessagingException {
        return folder.getMessageCount();
    }

    public int getNumberOfUnreadMessages()throws MessagingException {
        return folder.getUnreadMessageCount();
    }

    /**
     * Gets a message by its position in the folder. The earliest message is indexed at 1.
     */
    public Message getMessageByIndex(int index) throws MessagingException {
        return folder.getMessage(index);
    }

    public Message getLatestMessage() throws MessagingException{
        return getMessageByIndex(getNumberOfMessages());
    }

    /**
     * Gets all messages within the folder
     */
    public Message[] getAllMessages() throws MessagingException {
        return folder.getMessages();
    }

    /**
     * @param maxToGet maximum number of messages to get, starting from the latest. For example, enter 100 to get the last 100 messages received.
     */
    public Message[] getMessages(int maxToGet) throws MessagingException {
        Map<String, Integer> indices = getStartAndEndIndices(maxToGet);
        return folder.getMessages(indices.get("startIndex"), indices.get("endIndex"));
    }

    /**
     * Searches for messages with a specific subject
     * @param subject Subject to search messages for
     * @param unreadOnly Indicate whether to only return matched messages that are unread
     * @param maxToSearch maximum number of messages to search, starting from the latest. For example, enter 100 to search through the last 100 messages.
     */
    public Message[] getMessagesBySubject(String subject, boolean unreadOnly, int maxToSearch) throws Exception{
        Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);

        Message messages[] = folder.search(
                new SubjectTerm(subject),
                folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));
        if(unreadOnly){
            List<Message> unreadMessages = new ArrayList<Message>();
            for (Message message : messages) {
               if(isMessageUnread(message)) {
                    unreadMessages.add(message);
               }
            }
            messages = unreadMessages.toArray(new Message[]{});
        }

        return messages;
    }

    /**
     * Returns HTML of the com.hellosign.email's content
     */
    public String getMessageContent(Message message) throws Exception {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(message.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    /**
     * Returns all urls from an com.hellosign.email message with the linkText specified
     */
    public List<String> getUrlsFromMessage(Message message, String linkText) throws Exception{
        String html = getMessageContent(message);
        List<String> allMatches = new ArrayList<String>();
        Matcher matcher = Pattern.compile("(<a [^>]+>)View Document</a>").matcher(html);
        while (matcher.find()) {
            String aTag = matcher.group(1);
            String startStr = aTag.substring(aTag.indexOf("href=\"") + 6);
            String result = startStr.substring(0, startStr.indexOf('\"'));
            allMatches.add(result);
            //  allMatches.add(aTag.substring(aTag.indexOf("http"), aTag.indexOf("\">")));
        }
        return allMatches;
    }

    private Map<String, Integer> getStartAndEndIndices(int max) throws MessagingException {
        int endIndex = getNumberOfMessages();
        int startIndex = endIndex - max;

        //In event that maxToGet is greater than number of messages that exist
        if(startIndex < 1){
            startIndex = 1;
        }

        Map<String, Integer> indices = new HashMap<String, Integer>();
        indices.put("startIndex", startIndex);
        indices.put("endIndex", endIndex);

        return indices;
    }

    /**
     * Gets text from the end of a line.
     * In this example, the subject of the com.hellosign.email is 'Authorization Code'
     * And the line to get the text from begins with 'Authorization code:'
     * Change these items to whatever you need for your com.hellosign.email. This is only an example.
     */
    public String getAuthorizationCode() throws Exception {
        Message email = getMessagesBySubject("Authorization Code", true, 5)[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));

        String line;
        String prefix = "Authorization code:";

        while ((line = reader.readLine()) != null) {
            if(line.startsWith(prefix)) {
                return line.substring(line.indexOf(":") + 1);
            }
        }
        return null;
    }

    /**
     * Gets one line of text
     * In this example, the subject of the com.hellosign.email is 'Authorization Code'
     * And the line preceding the code begins with 'Authorization code:'
     * Change these items to whatever you need for your com.hellosign.email. This is only an example.
     */
    public String getVerificationCode() throws Exception {
        Message email = getMessagesBySubject("Authorization Code", true, 5)[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            if(line.startsWith("Authorization code:")) {
                return reader.readLine();
            }
        }
        return null;
    }



    //************* BOOLEAN METHODS *******************

    /**
     * Searches an com.hellosign.email message for a specific string
     */
    public boolean isTextInMessage(Message message, String text) throws Exception {
        String content = getMessageContent(message);

        //Some Strings within the com.hellosign.email have whitespace and some have break coding. Need to be the same.
        content = content.replace("&nbsp;", " ");
        return content.contains(text);
    }

    public boolean isMessageInFolder(String subject, boolean unreadOnly) throws Exception {
        int messagesFound = getMessagesBySubject(subject, unreadOnly, getNumberOfMessages()).length;
        return messagesFound > 0;
    }

    public boolean isMessageUnread(Message message) throws Exception {
        return !message.isSet(Flags.Flag.SEEN);
    }
}
