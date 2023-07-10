package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    @FindBy(xpath = "//div[@class='slds-icon-waffle']")
    public WebElement btn_appLauncher;//

    @FindBy (xpath = "//input[@type='search' and @class='slds-input' and @placeholder='Search apps and items...']")
    public WebElement txt_appLauncherTextbox;

    @FindBy (xpath = "//p[@class='slds-truncate']")
    public WebElement appLauncherTextValueSearched;

    @FindBy (xpath = "//lightning-icon[@class='slds-icon-utility-setup slds-button__icon slds-global-header__icon forceIcon slds-icon_container']")
    public WebElement btn_Setup;

    @FindBy (id = "related_setup_app_home")
    public WebElement btn_SetupOption;

    @FindBy (xpath = "//button[@aria-label='Search']")
    public WebElement btn_mainSearch;

    @FindBy (xpath = "//input[@type='search']")
    public WebElement text_mainSearch;

    @FindBy (xpath = "//ul[@class='slds-dropdown__list slds-show']/li[2]")
    public WebElement list_ViewAllInListview;

    @FindBy (xpath = "//ul[@class='slds-dropdown__list slds-show']/li[2]")
    public WebElement list_RoadAssistMemberHoldingforcallbacklistview;

    @FindBy (xpath = "//div[@role='alertdialog' and @data-key='success']")
    public WebElement alert_successToastMessage;

    @FindBy (xpath = "//span[text()='Service Areas']")
    public WebElement txt_serviceAreas;

    @FindBy (xpath = "//span[text()='Language']")
    public WebElement txt_languages;

    @FindBy (xpath = "//span[text()='Schedules']")
    public WebElement txt_schedules;

    @FindBy (xpath = "//span[text()='Security Clearances']")
    public WebElement txt_securityClearances;

    @FindBy (xpath = "//span[text()='Special Projects']")
    public WebElement txt_specialProjects;

    @FindBy (xpath = "//span[text()='Technicians']")
    public WebElement txt_Technician;

    @FindBy (xpath = "/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[3]")
    public WebElement link_fieldService;

    @FindBy (xpath = "//a[@title='Cases']")
    public WebElement link_Cases;

    @FindBy (xpath = "//div[@data-message-id='loginAsSystemMessage']")
    public WebElement txt_sandbox;

    @FindBy (xpath = "//input[@placeholder = 'Search this list...']")
    public WebElement input_searchAnyListView;

    @FindBy (xpath = "//div[@data-key='success']")
    public WebElement popup_success;

    @FindBy (xpath = "//button[@title='Close error dialog']")
    public WebElement warning_errorCloseDialog;

    @FindBy (xpath = "//span[text()='Service Appointments']/parent::a/parent::one-app-nav-bar-item-root")
    public WebElement link_servieAppointments;

    @FindBy (id = "ybar-sbq")
    public WebElement txt_searchBar;

    @FindBy (id = "ybar-search")
    public WebElement btn_searchBar;

    @FindBy (xpath = "//button[@class='slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container']")
    public WebElement btn_closeOpenTab;













}
