package Execute;

import Extensions.RestActions;
import Extensions.UIActions;
import Extensions.Verifications;
import Utilities.Operations;
import Workflows.WebFlows;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utilities.Listeners.class)
public class FieldServiceSettingsExcecute extends Operations {

    @BeforeClass
    public void BeforeExecution() {
        isApiTest = true;
        isAdminExecute = true;
        currentUrl = readData.GetData("Url");
//        if (isApiTest) {
//            RestActions.InitiateAPIConnectionGuy();
//        }
        RestActions.InitiateAPIConnectionGuy();
        initBrowser("Chrome");
        WebFlows.Login(readData.GetData("Username"), readData.GetData("Password"));
        WebFlows.CloseOpenedTab();

    }

    @AfterMethod
    public void AfterEveryTest() {
        WebFlows.CloseOpenedTab();
    }
    //This test checks that priority settings are configured correctly
    @Test(description = "Story 1352, Test 1419 ; ValidatePrioritySettings")
    public void ValidatePrioritySettings() {
        UIActions.SearchInAppLauncher("Field Service Settings");
        UIActions.SetDelayAfterAction(2000);
        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingTab);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingGeneralLogicTab);
        UIActions.ScrollBottomOfPage(2, 1000, true);
        try {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[contains(text(),'Work Order Priority Field')]//following::select[1]//option[@label='None' and@selected]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[contains(text(),'Work Order Line Item Priority Field ')]//following::select[1]//option[@label='None' and @selected]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//option[@label='Service Appointment Priority' and @selected][1]")));
            driver.switchTo().defaultContent();
        }

        catch (AssertionError e) {
            driver.switchTo().defaultContent();
            Assert.assertFalse(true);
        }
    }


    //This test checks that SA Status values are checked in field service settings
    @Test(description = "Story 1362, Test 1372 ; ValidateSAStatusAreConfiguredInFieldServiceSettings")
    public void ValidateSAStatusAreConfiguredInFieldServiceSettings() {
        String[] status = {"Under Tow", "En Route", "On Location", "Complete", "Cannot Complete", "Cancelled"};
        UIActions.SearchInAppLauncher("Field Service Settings");
        UIActions.SetDelayAfterAction(2000);
        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingTab);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingGeneralLogicTab);
        for (String s : status) {
            try {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[@title='" + s + "']//following-sibling::input[@class='ng-pristine ng-untouched ng-valid ng-not-empty']")));
                driver.switchTo().defaultContent();
            }
            catch (AssertionError e){
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);

            }            catch (org.openqa.selenium.NoSuchElementException e){
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);

            }
        }
        driver.switchTo().defaultContent();
    }

    @Test(description = "Story 1362, Test 1372 ; ValidateSALifeCycleInFieldServiceSettings")
    public void ValidateSALifeCycleInFieldServiceSettings() {
        String[] status = {"Open", "Scheduled", "Dispatched", "Accepted", "Cancelled", "Cannot Complete", "Complete"};
        String[] serviceAppointmentState = {"New Service Appointment", "Service Appointment is tentatively scheduled", "Service Appointment is sent to its resource",
                "Service Appointment is in progress", "Service Appointment is canceled", "Service Appointment is not completed", "Service Appointment is completed"};
        int i = 0;
        UIActions.SearchInAppLauncher("Field Service Settings");
        UIActions.SetDelayAfterAction(2000);
        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
        UIActions.Click(fieldServiceSettingsPage.btn_ServiceAppointmentLifeCycleTab);
        UIActions.Click(fieldServiceSettingsPage.btn_SAStatusTab);
        for (String s : status) {
            try {
                Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//label[contains(text(),'" + serviceAppointmentState[i] + "')]//following::select[1]//option[@selected][1]")).getText(), s);
                i++;
                driver.switchTo().defaultContent();
            }

            catch (AssertionError e) {
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);
            }
            catch (org.openqa.selenium.NoSuchElementException e){
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);

            }
        }
        driver.switchTo().defaultContent();

    }
}
