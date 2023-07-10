package Workflows;

import Extensions.UIActions;
import Utilities.Operations;
import Utilities.ReadCsv;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebFlows extends Operations {

    public static String strFile = "./TestCsv.csv"; //CSV file location


    public static void Login(String userName, String password){
        UIActions.UpdateText(loginPage.txt_username, userName);
        UIActions.UpdateText(loginPage.txt_password, password);
        UIActions.Click(loginPage.btn_login);
        currentTest.info("Performed login");
        mainWindowHandle = driver.getWindowHandle(); //store the main window in a windows handler String
        UIActions.SetDelayAfterAction(2000);
    }

    public static void MainPageSearch(String text){
        UIActions.ClickAndWait(mainPage.btn_mainSearch, "Search button");
        UIActions.UpdateText(mainPage.text_mainSearch, text);
        UIActions.ClickElementByXpath("//mark[text()='"+text+"']");
    }

    public static void ChangeToSetupTab(){
        for(String newWindows : driver.getWindowHandles()){
            driver.switchTo().window(newWindows);
            isSetupCurrentTab = true;
        }
    }

    public static void OpenSetupWindow(){
        UIActions.Click(mainPage.btn_Setup);
        UIActions.Click(mainPage.btn_SetupOption);
        currentTest.info("Open Setup Window");
        ChangeToSetupTab();
    }

    public static void SearchInObjectManager(String objectName){
        UIActions.ClickElementInListByXpath("//span[@class='title slds-truncate']", 1);
        UIActions.UpdateText(setupPage.search_ObjectQuickFind, objectName);
        UIActions.SetDelayAfterAction(1000);
        List<WebElement> elems = driver.findElements(By.xpath("//a[text() = '"+objectName+"']"));
        if (elems.size() >= 2){
            UIActions.Click(elems.get(elems.size()-1));
        } else{
            UIActions.Click(driver.findElement(By.xpath("//a[text() = '"+objectName+"']")));
        }
    }    public static void SearchInObjectManagerAndEnterACertainSectionInSetup(String objectName, String section){
        UIActions.ClickElementInListByXpath("//span[@class='title slds-truncate']", 1);
        UIActions.UpdateText(setupPage.search_ObjectQuickFind, objectName);
        UIActions.SetDelayAfterAction(2000);
        List<WebElement> elems = driver.findElements(By.xpath("//a[text() = '"+objectName+"']"));
        if (elems.size() >= 2){
            UIActions.Click(elems.get(elems.size()-1));
            UIActions.Click(driver.findElement(By.xpath("//a[text() = '"+section+"']")));
        } else{
            UIActions.Click(driver.findElement(By.xpath("//a[text() = '"+objectName+"']")));
            UIActions.Click(driver.findElement(By.xpath("//a[text() = '"+section+"']")));
        }
    }

    public static void ChangeToLatestTab(){
        for(String newWindows : driver.getWindowHandles()){
            driver.switchTo().window(newWindows);
        }
    }

    public static void SwitchIframe(List<WebElement> elements){
        UIActions.SetDelayAfterAction(300);
        if (!isThreadInsideIframe){
            if (elements.size() >= 2){
                driver.switchTo().frame(elements.get(elements.size()-1));
            }
            else{
                driver.switchTo().frame(elements.get(0));
            }
            isThreadInsideIframe = true;
            UIActions.SetDelayAfterAction(2000);
        } else{
            driver.switchTo().defaultContent();
            isThreadInsideIframe = false;
        }
    }

    public static void SwitchIframeByXpath(String iframeXpath){
        UIActions.SetDelayAfterAction(300);
        List<WebElement> elements = driver.findElements(By.xpath("//iframe[@title='"+iframeXpath+"']"));
        if (!isThreadInsideIframe){
            if (elements.size() >= 2){
                driver.switchTo().frame(elements.get(elements.size()-1));
            }
            else{
                driver.switchTo().frame(elements.get(0));
            }
            isThreadInsideIframe = true;
            UIActions.SetDelayAfterAction(2000);
        } else{
            driver.switchTo().defaultContent();
            isThreadInsideIframe = false;
        }
    }

    public static void SearchInAppLauncher(String appName){
        UIActions.SetDelayAfterAction(5000);
        UIActions.ClickAndWait(mainPage.btn_appLauncher, "App Launcher");
        UIActions.UpdateText(mainPage.txt_appLauncherTextbox, appName);
        UIActions.Click(mainPage.appLauncherTextValueSearched);
        currentTest.info("Search in app launcher : " + appName);
        UIActions.SetDelayAfterAction(1500);
    }

    public static void RefreshPage(){
        driver.navigate().refresh();
    }

    public static void SelectSpecificRecordInListView(String recordName){
        UIActions.UpdateText(mainPage.input_searchAnyListView, recordName);
        actions.sendKeys(Keys.ENTER).build().perform();
        UIActions.SetDelayAfterAction(1500);
        UIActions.ClickAndWait(driver.findElement(By.xpath("//a[@title='"+recordName+"']")), recordName);
        UIActions.SetDelayAfterAction(1500);
    }

    public static void SelectSpecificRecordInRelatedList(String recordName){
        UIActions.ClickLastElementInList(driver.findElements(By.xpath("//a[text()='"+recordName+"']")), recordName);
    }

    public static void ClickOnRelatedTab(){
        UIActions.SetDelayAfterAction(500);
        List<WebElement> relatedList = driver.findElements(By.xpath("//ul//li//span[text()='Related']"));
        UIActions.ClickLastElementInList(relatedList, "Related List");
        UIActions.SetDelayAfterAction(1500);
        currentResourceRelatedID = driver.findElement(By.xpath("//a[@title = 'Related']")).getAttribute("aria-controls");
    }

    public static void CheckForMultipleElement(String element){
        List<WebElement> elements = driver.findElements(By.xpath(element));
        if (elements.size() >= 2){
            if (element == "//div[@data-aura-class='forceListViewPicker']"){
                UIActions.MoveToElementAndClick(elements.get(0));
            } else{
                UIActions.MoveToElementAndClick(elements.get(elements.size()-1));
            }
        } else{
            UIActions.MoveToElementAndClick(elements.get(0));
        }
    }

    public static void OpenViewAllListview(){
        CheckForMultipleElement("//div[@data-aura-class='forceListViewPicker']");
        UIActions.ScrollBottomOfPage(1, 800, false);
        UIActions.ClickAndWait(mainPage.list_ViewAllInListview, "View All List View");
        UIActions.SetDelayAfterAction(2000);
    }

    public static void OpenAnyListViewBasedOnItsName(String listviewname){
        CheckForMultipleElement("//div[@data-aura-class='forceListViewPicker']");
        UIActions.Click(driver.findElement(By.xpath("//input[@placeholder='Search lists...']")));
        UIActions.UpdateText(driver.findElement(By.xpath("//input[@placeholder='Search lists...']")),listviewname);
        UIActions.SetDelayAfterAction(2000);
        driver.findElement(By.xpath("//input[@placeholder='Search lists...']")).sendKeys(Keys.DOWN);
        driver.findElement(By.xpath("//input[@placeholder='Search lists...']")).sendKeys(Keys.ENTER);
        CheckForMultipleElement("//div[@data-aura-class='forceListViewPicker']");
    }

    @Step
    public static void EnterFieldServiceGantt(){
        if (!isThreadInsideGantt){
            UIActions.Click(mainPage.link_fieldService);
            currentTest.info("Open field service");
            UIActions.SetDelayAfterAction(12000);
            isThreadInsideGantt = true;
        }
    }

    @Step
    public static String GetCaseNumberFromASpecificCaseInAListViewAndReturnString(){
        String casenumber = driver.findElement(By.xpath("//div[text()='Case']//following::slot[@name='primaryField']//lightning-formatted-text")).getText();
                return casenumber;
    }

    public static void SearchInSetup(String value){
//        UIActions.Click(SetupPage.search_Setup);
        UIActions.UpdateText(setupPage.search_Setup, value);
        UIActions.SetDelayAfterAction(4000);
        UIActions.Click(driver.findElement(By.xpath("//span[@title='"+value+"']")));
    }

    public static void SearchInSetupQuickFind(String value){
//        UIActions.Click(SetupPage.search_Setup);
        UIActions.UpdateText(setupPage.search_setupQuickFind, value);
        UIActions.SetDelayAfterAction(4000);
        List<WebElement>list = driver.findElements(By.xpath("//a[text()='"+value+"']"));
        if (list.size()==0) {
            UIActions.Click(driver.findElement(By.xpath("//mark[text()='" + value + "']")));
        }
        else {
            UIActions.Click(driver.findElement(By.xpath("//a[text()='" + value + "']")));

        }
        }


    public static void LoginAsUser(String value){
        SearchInSetup(value);
        UIActions.WaitInMiliseconds(4000);
        SwitchIframeByXpath("User: "+value+" ~ Salesforce - Unlimited Edition");
        UIActions.ClickAndWait(setupPage.btn_loginAsUser, "login as user");
    }

    public static void SearchInSetupHomeQuickFind(String value){
        UIActions.ClickElementInListByXpath("//span[@class='title slds-truncate']", 0);
        UIActions.Click(setupPage.search_setupQuickFind);
        UIActions.UpdateText(setupPage.search_setupQuickFind, value);
        UIActions.SetDelayAfterAction(1000);
    }

    public static List ReadCSVFile(ReadCsv.OBJECTS objValue){
        List<String> temp = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(readData.GetData("csvPath")));
            String [] nextLine ;
            int lineNumber = objValue.getValue();
            nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[lineNumber].equals("")){
                    break;
                }
                temp.add(nextLine[lineNumber]);
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        return temp;
    }

    public static void CloseOpenedTab(){
        try {
            UIActions.SetDelayAfterAction(1500);
            List<WebElement> tabs = driver.findElements(By.xpath("//button[@class='slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container']"));
            for (WebElement tab : tabs){
                UIActions.Click(tab);
            }
            List<WebElement> discardChanges = driver.findElements(By.xpath("//button[text()='Discard Changes']"));
            if (discardChanges.size() > 0){
                UIActions.Click(discardChanges.get(0));
                currentTest.info("Closed current un-saved tab");
            }
            newTabRecordCreated = false;
            currentTest.info("closed current tab");
        } catch (NoSuchElementException e) {
            currentTest.info("No tabs are opened");
        } catch (StaleElementReferenceException e){
            currentTest.warning("failed to resolve closing tabs" + e);
            System.out.println(e);
        } catch (Exception e){
            currentTest.warning("failed to resolve closing tabs" + e);
             System.out.println(e);
        }
    }

    public static void chooseDateFromOotbDatePicker(WebElement dateField, String day, String month, String year){
        UIActions.UpdateText(dateField, year +"-"+month+"-"+day);
    }

    //Choose Time from datepicker (String, 00:00, 00:30, 01:00 etc.)
    public static String ChooseTimeFromDatePicker(String xpathDateElement,String TimeRequested){
        try {
            UIActions.MoveAndDoubleClickOnElement(driver.findElement(By.xpath(xpathDateElement + "//following::label[text()='Time']//following::input[1]")));
            List<WebElement> timeList = driver.findElements(By.xpath("//li[text()='" + TimeRequested + "']"));
            UIActions.Click(timeList.get(timeList.size() - 1));
            //        UserActions.ClickAndWait(driver.findElement(By.xpath("//li[text()='"+TimeRequested+"']")));
            currentTest.info("Choose Time" + TimeRequested);
            return timeList.get(timeList.size() - 1).getText();
        }
        catch (Exception e){
            currentTest.info("Failed to choose time" + TimeRequested);
            return "";

        }
    }

//    public static  void LogInAsAUser (String username) throws InterruptedException {
//        try {
//            OpenSetupWindow();
//            UIActions.UpdateText(setupPage.search_Setup, username);
//            UIActions.ClickAndWait(setupPage.link_SearchedUserName,"Searched for User");
//            WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='User: " + username + " ~ Salesforce - Developer Edition']")));
//            UIActions.ClickAndWait(setupPage.btn_loginAsUser,"Logging in as a user");
//            currentTest.info("Logged in As user" + username);
//        }
//        catch (Exception e){
//            currentTest.info("Failed to login" + username);
//            e.printStackTrace();
//        }
//    }
}
