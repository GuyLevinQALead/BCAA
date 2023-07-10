package Utilities;

import Pages.FieldServiceSettingsPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
    Base class will store all the basic objects that are used throughout the whole project.
    Base class will be the extendable class of most objcets.
 */
public class Base {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Actions actions;
    protected static SoftAssert softAssert;
    protected static JavascriptExecutor js = (JavascriptExecutor) driver;
    protected static Select select;

    //Extent Report
    protected static ExtentReports extent = new ExtentReports();
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentReports extentReport;
    protected static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    protected static LocalDate localDate = LocalDate.now();


    //Every page in the web used defined once
    protected static Pages.LoginPage loginPage;
    protected static Pages.MainPage mainPage;
    protected static Pages.SetupPage setupPage;
    protected static Pages.CasePage casePage;
    protected static FieldServiceSettingsPage fieldServiceSettingsPage;
    protected static Pages.WorkOrderPage workOrderPage;
    protected static Pages.ServiceAppointmentPage serviceAppointmentPage;


}
