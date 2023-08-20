package Extensions;

import Utilities.Operations;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/*
    UIActions class will hold all of our used selenium-actions and wrap them in our own methods
    inorder to help us control the actions better, such as waits, logs, reporting, Steps documentation and more
 */
public class UIActions extends Operations {

    @Step("Click an element")
    public static void Click(WebElement element){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            currentTest.info("Clicked on element");
            SetDelayAfterAction(2000);
        } catch (ElementNotInteractableException | TimeoutException e){
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e){
            currentTest.fail("Could not click at all"  +element +e);
        }
    }



    public static void ClickAndWait(WebElement element, String eleName){
        try {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        Thread.sleep(1500);
        }
        catch (ElementClickInterceptedException e){
            MoveToElementAndClick(element);
            UIActions.SetDelayAfterAction(1500);
            currentTest.fail("element could not be clicked");
        }catch (InterruptedException e) {
            e.printStackTrace();
            currentTest.fail("element could not be clicked");
        } catch(TimeoutException e){
            element.click();
            currentTest.warning("Timeout Exception encountered.");
        }
        currentTest.info("Clicked on "+ eleName);
    }

    public static void ClickLastElementInList(List<WebElement> elems, String elemName){
        if (elems.size() >= 2 ){
            elems.get(elems.size()-1).click();
        } else{
            elems.get(0).click();
        }
        UIActions.SetDelayAfterAction(1500);
        currentTest.info("Clicked On" + elemName);
    }

    @Step("Update an element")
    public static void UpdateText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
        currentTest.info("Entered Text : " + text );
        SetDelayAfterAction(500);
    }


    @Step("Update an element")
    public static void UpdateTextInList(List<WebElement> elems, String text){
        if (elems.size() >= 2 ){
            elems.get(elems.size()-1).clear();
            elems.get(elems.size()-1).sendKeys(text);
        } else{
            elems.get(0).clear();
            elems.get(0).sendKeys(text);
        }
        currentTest.info("Entered Text : " + text);
        SetDelayAfterAction(500);
    }

    public static void SetDelayAfterAction(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Select element From Dropdown")
    public static void List_ChooseRandomValueFromDropdown(int index, WebElement element,String picklistname){
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        UIActions.Click(element);
        List<WebElement> chooseValue = driver.findElements(By.xpath("//lightning-base-combobox-item"));
        UIActions.Click(chooseValue.get(index));


//        Select dropdown = new Select(element);
//        select.selectByVisibleText(text);
    }

    @Step("Mouse hover an element")
    public static void MouseHover(WebElement elemOne, WebElement elemTwo){
        actions.moveToElement(elemOne).moveToElement(elemTwo).build().perform();
    }

    @Step("Click an element from array of same elements")
    public static void ClickElementInListByXpath(String elem, int index){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> inputs = driver.findElements(By.xpath(elem));
        UIActions.ClickAndWait(inputs.get(index), inputs.get(index).getText());
        currentTest.info("Clicked on element from list : " + inputs.get(index).getText());
    }



    @Step
    public static void ScrollBottomOfPage(int repeatNum, int interval, boolean requiredSpace){
        for (int i=0; i<repeatNum; i++){
            if (requiredSpace){
                actions.sendKeys(Keys.SPACE).build().perform();
            } else{
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Step
    public static void ScrollToASpecificElement (WebElement Element) {
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }


    public static void MoveAndDoubleClickOnElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            actions.moveToElement(element).perform();
            actions.doubleClick().perform();
            currentTest.info("Moved and Clicked on Element");
        } catch (ElementClickInterceptedException e) {
            currentTest.warning("Click Intercepted by another Element" + e);
        } catch (Exception e) {
            currentTest.fail("Test Failed" + e);


        }
    }

    @Step
    public static void WaitInMiliseconds(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public static void ClickElementByLink(String str){
        SetDelayAfterAction(1000);
        UIActions.Click(driver.findElement(By.xpath("//a[text() = '"+str+"']")));
    }


    @Step
    public static void ClickElementBySpanText(String str){
        SetDelayAfterAction(1000);
        UIActions.Click(driver.findElement(By.xpath("//span[text() = '"+str+"']")));
    }

    @Step
    public static void ClickElementByXpath(String elem){
        UIActions.ClickAndWait(driver.findElement(By.xpath(elem)), "");
    }

    @Step
    public static void ScrollTopOfPage(int repeatNum, int delayInMiliseconds){
        for (int i=0; i<repeatNum; i++) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_UP);
            UIActions.SetDelayAfterAction(delayInMiliseconds);
        }
    }

        @Step
    public static void ScrollToElementByWebelement(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        WaitInMiliseconds(500);
    }


    @Step
    public static void ScrollToElementByWebElementWithActions(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        WaitInMiliseconds(500);
    }

    @Step
    public static void ClickElementByJavaScriptExecutor(WebElement element, String eleName){
        SetDelayAfterAction(300);
        js.executeScript("arguments[0].click()", element);
        currentTest.info("Clicked on : " + eleName);
    }



    public static void MoveToElementAndClick(WebElement elemOne){
        int repeat = 0;
        while (repeat < 3){
            try {
                actions.moveToElement(elemOne).click().build().perform();
                SetDelayAfterAction(300);
                currentTest.info("Moved to an element and clicked");
                break;
            } catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
            repeat++;
        }
    }
    @Step
    public static void SearchInAppLauncher(String value){
//        wait.until(ExpectedConditions.elementToBeClickable(mainPage.btn_appLauncher));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slds-icon-waffle']")));
        Click(mainPage.btn_appLauncher);
        UpdateText(mainPage.txt_appLauncherTextbox, value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='"+value+"']/div/lightning-formatted-rich-text/span/p")));
        Click(driver.findElement(By.xpath("//a[@data-label='"+value+"']/div/lightning-formatted-rich-text/span/p")));
        SetDelayAfterAction(2000);
    }

    @Step
    public static String GetTextFromTheElement(WebElement element){
        SetDelayAfterAction(300);
        String text = element.getText();
        return text;
    }

    @Step("Switch to new window")
    public static void SwitchToNewWindow(String window){
        try {
            Thread.sleep(500);
            for(String winHandle : driver.getWindowHandles()){
                if(winHandle.equalsIgnoreCase(window))
                    driver.switchTo().window(winHandle);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public static void EnterInElementByJavaScriptExecutor(WebElement element, String value){
        SetDelayAfterAction(300);
        js.executeScript("arguments[0].value='"+value+";", element);
        currentTest.info("Entered value : " + value);
    }

    @Step
    public static WebElement ChangeXpathOfElementUsingReplace(String xpath, String replace, String eleName){
        SetDelayAfterAction(300);
        String newXpath = xpath.replace("replace", replace);
        WebElement element = driver.findElement(By.xpath(newXpath));
        return element;
    }

    @Step
    public static void MouseHoverElement(WebElement element){
        actions.moveToElement(element).build().perform();
    }

    @Step
    public static void DoubleClickOnElement(WebElement element){
        actions.doubleClick(element).build().perform();
    }

    @Step
    public static void SwitchtoNewWindow() {
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    @Step
    public static void ClickElementByJavaScriptExecutorUsingXpath(String xpath){
        SetDelayAfterAction(300);
        WebElement element = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].click()", element);
    }

    @Step
    public static void ScrollToTopWithJavascript() {
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    @Step
    public static void ScrollToBottomWithJavascript() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static String GetTextLastElementInList(List<WebElement> elems, String elemName){
        String value= null;
        if (elems.size() >= 2 ){
            value=elems.get(elems.size()-1).getText();
        } else{
            value=elems.get(0).getText();
        }
        UIActions.SetDelayAfterAction(1500);
        return value;
    }

    public static void dragNDropInGantt(WebElement source, WebElement destination) {
        actions.dragAndDrop(source, destination).build().perform();
    }



}
