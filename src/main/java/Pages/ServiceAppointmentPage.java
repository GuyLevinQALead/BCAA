package Pages;

import Extensions.UIActions;
import Utilities.Operations;
import Workflows.WebFlows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ServiceAppointmentPage extends Operations {
    //Account lookup on service appointment object
    @FindBy(xpath = "//input[@title='Search Accounts']")
    public WebElement search_AccountField;
    //Work Order lookup on service appointment object

    @FindBy(xpath = "//input[@title='Search Work Orders'][1]")
    public WebElement search_workOrderField;
    //Create new account from account lookup on service appointment object
    @FindBy(xpath = "//span[@title='New Account']")
    public WebElement btn_createAccount;
    //Account name txt field on account page when creating it from service appointment object
    @FindBy(xpath = "//span[text()='Account Name']//following::input[@class=' input' and @aria-required]")
    public WebElement txt_newAccountName;

    //Account name txt field on account page when creating it from service appointment object
    @FindBy(xpath = "//span[text()='Earliest Start Permitted']//following::input[1]")
    public WebElement dropdown_startDate;

    //Account name txt field on account page when creating it from service appointment object
    @FindBy(xpath = "//span[text()='Account Name']//following::input[1]")
    public WebElement dropdown_startTime;

    //Account name txt field on account page when creating it from service appointment object
    @FindBy(xpath = "//span[text()='Due Date']//following::input[1]")
    public WebElement dropdown_endDate;

    //Account name txt field on account page when creating it from service appointment object
    @FindBy(xpath = "//span[text()='Account Name']//following::input[1]")
    public WebElement dropdown_endTime;

    //Edit Status Button On SA
    @FindBy(xpath = "//span[text()='Edit Status']")
    public WebElement btn_editStatus;

    //Edit Status Button On SA
    @FindBy(xpath = "//span[text()='Edit Case Priority']")
    public WebElement btn_editCasePriority;

    @FindBy(xpath = "//button[@aria-label='Case Priority, --None--']")
    public WebElement dropdown_BreakdownLocationTabUndergroundHeightPicklist;

    //Edit Status Button On SA
    @FindBy(xpath = "//span[text()='Edit Facility']")
    public WebElement btn_editFacility;

    //Edit Status Button On SA
    @FindBy(xpath = "//span[text()='Status']//following::a[text()='Open'][1]")
    public WebElement dropdown_status;

    //Save SA SA
    @FindBy(xpath = "//button[@title='Save']")
    public WebElement btn_SASave;

    //Parent Record Choose Object Button
    @FindBy(xpath = "//a[@aria-label='Parent Record—Current Selection: Accounts, Pick an object']")
    public WebElement btn_chooseParentObject;

    //The Month of a dropdown
    @FindBy(xpath = "//h2[@class='monthYear' and text()]")
    public WebElement info_Month;

    //The Year of a Dropdown
    @FindBy(xpath = "//select[@class='slds-select picklist__label']")
    public WebElement info_Year;


    //The Time of a Dropdown
    @FindBy(xpath = "//label[text()='Time']//following::input[1]")
    public WebElement info_Time;

    //This method creates a service appointment from scrach and assigns it to an account, if no account exist in the system it creates one.
    //Year, Month, Day and Time are all Strings.

    public  void CreateServiceAppointmentFromWorkOrder(String workOrderNumber,String AccountName, String fromYear,String fromMonth, String fromDay, String fromTime, String toYear, String toDay, String toMonth,String toTime) throws InterruptedException {
        WebFlows.SearchInAppLauncher("Service Appointments");
        WebFlows.RefreshPage();
        UIActions.Click(workOrderPage.btn_newRecord);
        UIActions.Click(serviceAppointmentPage.search_workOrderField);
        UIActions.UpdateText(serviceAppointmentPage.search_workOrderField,workOrderNumber);
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        UIActions.Click(serviceAppointmentPage.search_AccountField);
        List<WebElement> accountList = driver.findElements(By.xpath("//span[text()='Parent Record']//following::ul[@class='lookup__list  visible']/li/a/div/div"));
        if (accountList.size() > 0) {
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        } else {

            UIActions.Click(serviceAppointmentPage.btn_createAccount);
            for (String openTabs : driver.getWindowHandles()) {
                driver.switchTo().window(openTabs);
            }
            UIActions.UpdateText(serviceAppointmentPage.txt_newAccountName, AccountName);
            UIActions.MoveAndDoubleClickOnElement(workOrderPage.btn_woOrSaSaveButton);
            driver.switchTo().defaultContent();
        }
        WebFlows.chooseDateFromOotbDatePicker(ServiceAppointmentPage.serviceAppointmentPage.dropdown_startDate, fromDay ,fromMonth,fromYear);
        WebFlows.ChooseTimeFromDatePicker("//span[text()='Earliest Start Permitted']//following::input[1]", fromTime);
        WebFlows.chooseDateFromOotbDatePicker(ServiceAppointmentPage.serviceAppointmentPage.dropdown_endDate, toDay,toMonth,toYear);
        WebFlows.ChooseTimeFromDatePicker("//span[text()='Due Date']//following::input[1]", toTime);
        UIActions.Click(workOrderPage.btn_woOrSaSaveButton);
        Thread.sleep(1500);
        currentTest.info("Created New Service Appointment");

    }

    public  void CreateServiceAppointment(String AccountName, String fromYear,String fromMonth, String fromDay, String fromTime, String toYear, String toDay, String toMonth,String toTime) throws InterruptedException {
        WebFlows.SearchInAppLauncher("Service Appointments");
        WebFlows.RefreshPage();
        UIActions.Click(workOrderPage.btn_newRecord);
        UIActions.Click(serviceAppointmentPage.search_AccountField);
        List<WebElement> accountList = driver.findElements(By.xpath("//span[text()='Parent Record']//following::ul[@class='lookup__list  visible']/li/a/div/div"));
        if (accountList.size() > 0) {
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            actions.sendKeys(Keys.ENTER).build().perform();
        } else {

            UIActions.Click(serviceAppointmentPage.btn_createAccount);
            for (String openTabs : driver.getWindowHandles()) {
                driver.switchTo().window(openTabs);
            }
            UIActions.UpdateText(serviceAppointmentPage.txt_newAccountName, AccountName);
            UIActions.MoveAndDoubleClickOnElement(workOrderPage.btn_woOrSaSaveButton);
            driver.switchTo().defaultContent();
        }
        WebFlows.chooseDateFromOotbDatePicker(ServiceAppointmentPage.serviceAppointmentPage.dropdown_startDate, fromDay ,fromMonth,fromYear);
        WebFlows.ChooseTimeFromDatePicker("//span[text()='Earliest Start Permitted']//following::input[1]", fromTime);
        WebFlows.chooseDateFromOotbDatePicker(ServiceAppointmentPage.serviceAppointmentPage.dropdown_endDate, toDay,toMonth,toYear);
        WebFlows.ChooseTimeFromDatePicker("//span[text()='Due Date']//following::input[1]", toTime);
        UIActions.Click(workOrderPage.btn_woOrSaSaveButton);
        Thread.sleep(1500);
        currentTest.info("Created New Service Appointment");

    }

//    public static void CreateServiceAppointmentWithWorkOrderParentRecord(String workOrderNumber, String fromYear,String fromMonth, String fromDay, String fromTime, String toYear, String toDay, String toMonth,String toTime) throws InterruptedException {
//        WebFlows.SearchInAppLauncher("Service Appointments");
//        UIActions.Click(WorkOrderPage.btn_newRecord);
//        UIActions.Click(serviceAppointmentPage.btn_chooseParentObject);
//        UIActions.Click(driver.findElement(By.xpath("//a[@title='Work Orders']")));
//        UIActions.UpdateText(serviceAppointmentPage.txt_newAccountName, workOrderNumber);
//        UIActions.MoveAndDoubleClickOnElement(WorkOrderPage.btn_woOrSaSaveButton);
//        WebFlows.chooseDateFromOotbDatePicker(ServiceAppointmentPage.serviceAppointmentPage.dropdown_startDate, fromDay ,fromMonth,fromYear);
//        WebFlows.ChooseTimeFromDatePicker("//span[text()='Earliest Start Permitted']//following::input[1]", fromTime);
//        WebFlows.chooseDateFromOotbDatePicker(ServiceAppointmentPage.serviceAppointmentPage.dropdown_endDate, toDay,toMonth,toYear);
//        WebFlows.ChooseTimeFromDatePicker("//span[text()='Due Date']//following::input[1]", toTime);
//        UIActions.Click(WorkOrderPage.btn_woOrSaSaveButton);
//        Thread.sleep(1500);
//        currentTest.info("Created New Service Appointment");
//
//    }
}
