package Pages;

import Utilities.Operations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FieldServiceSettingsPage extends Operations {

    @FindBy(xpath = "//span[text()='Scheduling']")
    public WebElement btn_SchedulingTab;

    @FindBy(xpath = "//span[text()='Service Appointment Life Cycle']")
    public WebElement btn_ServiceAppointmentLifeCycleTab;

    @FindBy(xpath = "//div[text()='General Logic']")
    public WebElement btn_SchedulingGeneralLogicTab;

    @FindBy(xpath = "//div[text()='SA Status']")
    public WebElement btn_SAStatusTab;

    @FindBy(xpath = "//iframe[@title='Field Service Settings']")
    public WebElement iframe_fieldServiceSettingsIFrame;
}
