package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SetupPage {
    @FindBy(id = "globalQuickfind")
    public WebElement search_ObjectQuickFind;

    @FindBy (xpath = "//input[@placeholder = 'Quick Find']")
    public WebElement search_setupQuickFind;

    @FindBy (xpath = "//input[@id='globalQuickfind']")
    public WebElement search_setupQuickFindInsideObjectSection;

    @FindBy (xpath = "//a[@data-list = 'Fields & Relationships']")
    public WebElement link_FieldsAndRelations;

    @FindBy (xpath = "//a[@data-list = 'Validation Rules']")
    public WebElement link_ValidationRules;

    @FindBy (xpath = "//a[@data-list = 'Search Layouts']")
    public WebElement link_SearchLayout;

    @FindBy (xpath = "//a[@data-list = 'Record Types']")
    public WebElement link_RecordTypes;

    @FindBy (xpath = "//a[@data-list = 'Page Layouts']")
    public WebElement link_pageLayouts;

    @FindBy (xpath = "//a[@data-list = 'Field Sets']")
    public WebElement link_fieldSets;

    @FindBy (xpath = "//a[@data-list = 'Compact Layouts']")
    public WebElement link_compactLayouts;

    @FindBy (xpath = "//input[@title='Search Setup']")
    public WebElement search_Setup;



    @FindBy (xpath = "//div[@class='pbHeader']//input[@title='Login']")
    public WebElement btn_loginAsUser;

    @FindBy (xpath ="//ul[@class='lookup__list  visible' and @role='presentation']//li[@class='lookup__item SETUP_NODE slds-text-link--reset slds-grid slds-grid--vertical-align-center slds-size_1-of-1 slds-truncate  default uiAutocompleteOption forceSearchInputDesktopOption']")
    public WebElement link_SearchedUserName;
}
