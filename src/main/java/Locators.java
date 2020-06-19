import org.openqa.selenium.By;

public class Locators {

    public static  final By H1_TITLE = By.xpath("//h1");

    //Home Page
    public static final By TEXT_FIELD_EMAIL = By.xpath("//input[@name='logIn.emailAddress']");
    public static final By TEXT_FIELD_PASSWORD = By.xpath("//input[@name='logIn.password']");
    public static final By BUTTON_CONTINUE_LOGIN = By.xpath("//button[@da-category='login']");

    //Main Page
    public static final By ROOT = By.xpath("//div[@id='site-wrapper']");
    public static final By GREETING_NAME = By.xpath("//div[contains(@class, 'hellosign-layout-container')]//span[contains(@class, 'menu--title--text')]");



    //SIGN PAGE
    public static final By TEXT_FIELD_EMAIL_RECEIPENT = By.xpath("//input[@id='tsm_group_send_recipient1']");
    public static final By TEXT_FIELD_REQUEST_SUBJECT = By.xpath("//input[@id='request_subject']");
    public static final By TEXTAREA_TEXT_MESSAGE = By.xpath("//textarea[@id='request_message']");
    public static final By BUTTON_UPLOAD_FILE = By.xpath("//span[@class='upload_files_button']");
    public static final By INPUT_UPLOAD_DOCUMENTS = By.xpath(".//input[@type=\'file\']");
    public static final By BUTTON_FILL_OUT_AND_SIGN = By.xpath("//a[contains(@id, 'edit_link')]");
    public static final By BUTTON_SEND_SUBMIT = By.xpath("//button[@id='tsm_group_send_submit']");


    //DOCUMENT EDITOR
    public static final By BUTTON_CHECK_IT_OUT = By.xpath("//button//span[text()='Check it out']");

    public static final By NEW_FIELD_SIGNATURE = By.xpath("//div[contains(@class, 'src-hellospa-components')]//button[@data-qa-ref='new-field-signature']");
    public static final By NEW_FIELD_INITIALS = By.xpath("//div[contains(@class, 'src-hellospa-components')]//button[@data-qa-ref='new-field-initials']");
    public static final By NEW_FIELD_DATE_SIGNED = By.xpath("//div[contains(@class, 'src-hellospa-components')]//button[@data-qa-ref='new-field-date']");
    public static final By NEW_FIELD_TEXTBOX = By.xpath("//div[contains(@class, 'src-hellospa-components')]//button[@data-qa-ref='new-field-text']");
    public static final By NEW_FIELD_CHECKBOX = By.xpath("//div[contains(@class, 'src-hellospa-components')]//button[@data-qa-ref='new-field-checkbox']");

    public static final By IMAGE_DOCUMENT = By.xpath("//div[@id='page-0']//div[contains(@class, 'signature-request-document-page')]");
    public static final By BUTTON_INSERT = By.xpath("//button[@data-qa-ref='button-insert-everywhere']");
    public static final By BUTTON_CONTINUE_EDIT_MODE = By.xpath("//div[contains(@class,'src-hellospa-components-editor')]//button[@data-qa-ref='continue-button-prepare-docs']");
    public static final By MENU_OPTION_DRAW_IT = By.xpath("//span[text()='Draw it in']");
    public static final By CANVAS_DRAW_SIGNATURE = By.xpath("//canvas[@id='drawSignatureCanvas']");
    public static final By TEXTAREA_ON_DOCUMENT = By.xpath("//textarea[@data-qa-ref='text-input']");



    //Gmail
    public static final By PASSWORD_GMAIL = By.xpath("//input[contains(@name, 'password')]");
    public static final By EMAIL_GMAIL =  By.xpath(".//input[contains(@id, 'identifierId')]");
    public static final By EMAIL_NEXT_BUTTON = By.xpath(".//div[contains(@id, 'identifierNext')]");
    public static final By PASSWORD_NEXT_BUTTON =  By.xpath(".//div[contains(@id, 'passwordNext')]");
    public static final By GOOGLE_APPS  =  By.xpath(".//a[@aria-label='Google apps']/../a");
    public static final By GOOGLE_APPS_IFRAME  = By.xpath("//iframe[contains(@id, 'I0')]") ;
    public static final By GMAIL_ICON  =  By.xpath("//span[@pid='23']") ;
    public static final By SEARCH_INPUT =  By.xpath(".//form[@role='search']//input[@type='text']/../input");
    public static final By SEARCH_BUTTON = By.xpath(".//button[@aria-label='Search mail']");

}
