package Pages;

import Extensions.UIActions;
import Utilities.Operations;
import Workflows.WebFlows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkOrderPage extends Operations {

    @FindBy(xpath ="//button[@title='Save']//span[@class=' label bBody' and text()='Save']")
    public  WebElement btn_woOrSaSaveButton;

    @FindBy (xpath = "//div[@title='New']")
    public  WebElement btn_newRecord;

    // WorkOrder doesn't have any required fields OOTB
    public  void CreateNewWorkOrder() throws InterruptedException {
        WebFlows.SearchInAppLauncher("Work Orders");
        WebFlows.RefreshPage();
        UIActions.Click(btn_newRecord);
        Thread.sleep(2000);
        actions.moveToElement(btn_woOrSaSaveButton).perform();
        actions.doubleClick().perform();
        currentTest.info("Created New Work Order");
    }
}
