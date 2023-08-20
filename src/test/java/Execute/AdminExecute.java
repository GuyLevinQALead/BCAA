package Execute;

import Extensions.RestActions;
import Extensions.UIActions;
import Extensions.Verifications;
import Utilities.JsonPayloads;
import Utilities.Operations;
import Workflows.WebFlows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Listeners(Utilities.Listeners.class)
public class AdminExecute extends Operations {

    @BeforeClass
    public void BeforeExecution() {
        isApiTest = true;
        isAdminExecute = true;
        currentUrl = readData.GetData("Url");
        RestActions.InitiateAPIConnectionGuy();
        initBrowser("Chrome");
        WebFlows.Login(readData.GetData("Username"), readData.GetData("Password"));
        WebFlows.CloseOpenedTab();

    }

    @AfterMethod
    public void AfterEveryTest() {
        WebFlows.CloseOpenedTab();
    }


    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    @Test(description = "Story 1397, Test 1430 ; ValidateTheExistenceOfOptimizationUser")
    public void ValidateTheExistenceOfOptimizationUser() {
        String optimizationUser = "Field Service Optimization";
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetup(optimizationUser);
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='User: " + optimizationUser + " ~ Salesforce - Unlimited Edition']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//h2[text()=' Field Service Optimization']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='" + optimizationUser + "']")));
    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the work order object has the required values
    @Test(description = "Story 1149, Test 1305 ; ValidateWOHasCorrectStatus")
    public void ValidateWOHasCorrectStatus() {
        String object = "Work Order";
        String section = "Fields & Relationships";
        String field = "Status";
        String[] statusList = {"Open", "In Progress", "Complete", "Cancelled", "Cannot Complete"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }

    }

    //This test validates that the SA in the system have the correct status according to the config book (on setup)
    @Test(description = "Story 1150, Test 1350 ; ValidateSAHasCorrectStatus")
    public void ValidateSAHasCorrectStatus() {
        String object = "Service Appointment";
        String section = "Fields & Relationships";
        String field = "Status";
        String[] statusList = {"None", "Scheduled", "Dispatched", "Accepted", "En Route", "On Location", "Under Tow", "Complete", "Cannot Complete", "Cancelled"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
    }

    //This test validates that the SA in the system have the In Jeopardy Reason according to the config book (on setup)
    @Test(description = "Story 1475, Test 1525 ; ValidateInJeopardyReasonHasCorrectStatus")
    public void ValidateInJeopardyReasonHasCorrectStatus() {
        String object = "Service Appointment";
        String section = "Fields & Relationships";
        String field = "In Jeopardy Reason";
        String[] statusList = {"PTA In Jeopardy", "Late En Route", "Late On Location", "Late Service Completion", "Late Tow Loading", "Late Under Tow", "Late Tow At Drop", "Late Assigned", "Late Dispatched"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
    }

    //This test validates that the Location object in the system have the correct fields according to the config book (on setup)
    @Test(description = "Story 1302, Test 1425 ; ValidateLocationObjectHasCorrectFields")
    public void ValidateLocationObjectHasCorrectFields() {
        String object = "Location";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Facility ID", "RV Indicator", "Motorcycle Indicator", "Battery Indicator", "Vehicle Type", "Facility", "Vehicle Type Code",
                "Bicycle Service Indicator", "Bicycle Tow Indicator", "Tire Repair Indicator"};
        String[] fieldsType = {"Formula (Text)", "Checkbox", "Checkbox", "Checkbox", "Picklist", "Lookup(Facility)", "Formula (Text)",
                "Checkbox", "Checkbox", "Checkbox"};
        int i = 0;
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        try {
            for (String s : fieldsList) {
                UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
                UIActions.SetDelayAfterAction(500);
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + fieldsType[i] + "']")));
                i++;
            }
        }
        catch (Exception e){
            System.out.println("Some elements were not fount "+ e);
            Assert.assertFalse(true);

        }

    }

    //This test validates that the vehicle type custom picklist of the Location object has the correct values according to the config book (on setup)
    @Test(description = "Story 1302, Test 1425 ; ValidateLocationVehicleTypePicklistHaveCorrectValues")
    public void ValidateLocationVehicleTypePicklistHaveCorrectValues() {
        String object = "Location";
        String section = "Fields & Relationships";
        String field = "Vehicle Type";
        String[] statusList = {"BA - Battery", "WL - Wheel Lift", "LS - Light Service", "TS - Traffic Unit (Scorpion)", "TU - Traffic Unit (Regular)", "FB - Flatbed"};
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
    }

    //This test validates that the location codes custom picklist of the Location object has the correct values according to the config book (on setup)
    @Test(description = "Story 426, Test 669 ; ValidateLocationLocationCodePicklistHaveCorrectValues")
    public void ValidateLocationLocationCodePicklistHaveCorrectValues() {
        String object = "Case";
        String section = "Fields & Relationships";
        String field = "Location Code";
        String[] statusList = {"A Alley", "C Carport", "CP Covered Parkade", "D Driveway", "DR Driveway REAR", "E East", "EE East bound East of", "EW East bound West of",
                "F Front", "FC Front Carport", "FD Front Driveway", "FG Front Garage", "FP Front Parking lot", "FU Front Underground", "G Garage", "MD Median", "N North", "NN North bound North of", "NS North bound South of", "O Opposite",
                "OC Opposite Carport", "OD Opposite Driveway", "OG Opposite Garage", "OP Opposite Parking lot", "OU Opposite Underground", "PK Parkade", "PL Parking Lot", "R Rear", "RC Rear Carport", "RD Rear Driveway", "RG Rear Garage",
                "RP Rear Parking lot", "RS Residence", "RU Rear Underground", "RW Roadway", "S South", "SC Side Carport", "SD Side Driveway", "SG Side Garage", "SH Shoulder", "SI Side", "SN South bound North of", "SP Side parking lot", "SS South bound South of", "SU Side Underground", "U Underground",
                "W West", "WE West bound East of", "WW West bound West of"};
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//th[text()='" + s + "']")));
        }
    }

    //This test validates that the Location type custom picklist of the Location object has the correct values according to the config book (on setup)
    @Test(description = "Story 1292, Test 1460 ; ValidateLocationLocationTypePicklistHaveCorrectValues")
    public void ValidateLocationLocationTypePicklistHaveCorrectValues() {
        String object = "Location";
        String section = "Fields & Relationships";
        String field = "Location Type";
        String[] statusList = {"Warehouse", "Site", "Van", "Plant", "Virtual", "Truck"};
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test checks that locations were added correctly to the system and also checks the location layout
    @Test(description = "Story 1292, Test 1461 ; ValidateLocationWereAddedToTheSystem")
    public void ValidateLocationWereAddedToTheSystem() {

        String[] locationName = {"01", "09", "10", "107", "99"};
        String[] fieldsList = {"Facility ID", "RV Indicator", "Motorcycle Indicator", "Battery Indicator", "Vehicle Type", "Location Name"};
        UIActions.SearchInAppLauncher("Locations");
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(3000);
        WebFlows.OpenViewAllListview();
        for (String s : locationName) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[@title='" + s + "']")));
        }
        UIActions.Click(driver.findElement(By.xpath("//a[@title='" + locationName[0] + "']")));
        UIActions.SetDelayAfterAction(1000);
        for (String s : fieldsList) {
            if (!s.equals("Location Name")) {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            } else {
                List<WebElement> elems = driver.findElements(By.xpath("//span[text()='" + s + "']"));
                Verifications.VerifyElementPresent(elems.get(elems.size() - 1));
            }

        }
    }

    //This test validates that the service resource object in the system have the correct fields according to the config book (on setup)
    @Test(description = "Story 1189+1302, Test 1375+1426 ; ValidateServiceResourceObjectHasCorrectFields")
    public void ValidateServiceResourceObjectHasCorrectFields() {
        String object = "Service Resource";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Truck Type", "Name", "Resource Type", "Facility", "Facility Code", "Partner Account", "Driver ID",
                "Phone", "Efficiency"};
        String[] fieldsType = {"Formula (Text)", "Text(255)", "Picklist", "Lookup(Facility)", "Formula (Text)", "Lookup(Account)", "Number(10, 0)",
                "Text(15)", "Number(16, 2)"};
        int i = 0;
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + fieldsType[i] + "']")));
            i++;
        }

    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test checks the service resource layout for admin
    @Test(description = "Story 1189, Test 1376 ; ValidateServiceResourceLayout")
    public void ValidateServiceResourceLayout() {
        String ServiceResourceName = "Resource 1";
        String[] fieldsList = {"Scheduling Constraint", "Name", "Resource Type", "Active", "Facility",
                "Facility Code", "Partner Account", "Driver ID", "Phone", "Efficiency"};
        UIActions.SearchInAppLauncher("Service Resources");
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(3000);
        WebFlows.OpenViewAllListview();
        UIActions.Click(driver.findElement(By.xpath("//a[@title='" + ServiceResourceName + "']")));
        UIActions.SetDelayAfterAction(1000);
        for (String s : fieldsList) {
            List<WebElement> elems = driver.findElements(By.xpath("//span[text()='" + s + "']"));
            System.out.println(elems.size());
            if (elems.size()==1) {

                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            } else {
                try {
                    Verifications.VerifyElementPresent(elems.get(elems.size() - 1));
                }

                catch (ArrayIndexOutOfBoundsException e){
                    Assert.assertFalse(true);
                }

                catch (IndexOutOfBoundsException e){
                    Assert.assertFalse(true);
                }
            }
        }
    }

    //This test validates that the Facility field was updated correctly on Polygon Assignment, PTA Table, and Case
    @Test(description = "Story 1280, Test 1370 ; ValidateFacilityFieldIsUpdatedOnMultipeObjects")
    public void ValidateFacilityFieldIsUpdatedOnMultipeObjects() {
        String[] objects = {"Polygon Assignment", "PTA Table", "Case"};
        String section = "Fields & Relationships";
        String field = "Facility";
        String fieldType = "Lookup(Facility)";
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);

        for (String s : objects) {
            WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(s, section);
            UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + field + "']")));
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + fieldType + "']")));
            UIActions.ClickElementInListByXpath("//span[@class='title slds-truncate']", 1);
        }

    }

    //This test validates that the facility object in the system have the correct fields and picklist values according to the config book (on setup)
    @Test(description = "Story 1280, Test 1368 ; ValidateFacilityObjectHasCorrectFields")
    public void ValidateFacilityObjectHasCorrectFields() {
        String object = "Facility";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Facility Type", "Facility Code", "Facility Phone", "Facility Contact", "Facility Status", "Facility City", "Address", "IsActive", "Partner Account"};
        String[] fieldsType = {"Picklist", "Text(20)", "Text(20)", "Text(20)", "Picklist", "Text(20)", "Address", "Checkbox", "Lookup(Account)"};
        String[] facilityType = {"BCAA Facility", "Contractor Facility", "Generic Facility"};
        String[] facilityStatus = {"Active", "Inactive"};
        int i = 0;

        //Test the facility fields
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + fieldsType[i] + "']")));
            i++;
        }

        //Test the facility type picklist values
        UIActions.Click(driver.findElement(By.xpath("//a[text() = '" + section + "']")));
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, fieldsList[0]);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldsList[0] + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: " + fieldsList[0] + " ~ Salesforce - Unlimited Edition']")));
        for (String s : facilityType) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
        driver.switchTo().defaultContent();


        //Test the facility status picklist values
//        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
//        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.Click(driver.findElement(By.xpath("//a[text() = '" + section + "']")));
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, fieldsList[4]);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldsList[4] + "']")));
        UIActions.SetDelayAfterAction(3000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='" + object + " Custom Field: " + fieldsList[4] + " ~ Salesforce - Unlimited Edition']")));
        UIActions.SetDelayAfterAction(500);
        UIActions.ScrollBottomOfPage(1, 1000, true);
        for (String s : facilityStatus) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
        driver.switchTo().defaultContent();

    }


    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the Case object has the required CDX fields in setup
    @Test(description = "Story 385+408+557, 1151+1054+794+1222 ; ValidateCustomFieldsOnCase")
    public void ValidateCustomFieldsOnCase() {
        String object = "Case";
        String section = "Fields & Relationships";
        String[] fieldsList = {"CDX Authorization Level", "CDX Authorization Message", "CDX Authorization Code", "CDX Motorcycle Coverage", "CDX Long Tow Coverage",
         "Triage Answer","Legacy Code","Member Since","Chargeable Calls","History Calls","Member Expiry Date","Phone 1","Phone 1 Opt-In", "Phone 1 Primary","Phone 1 Type",
           "Phone 2","Phone 2 Opt-In","Phone 2 Primary","Phone 2 Type"
//                "Membership Expiry Date"
        };
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(2000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }

    }    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the work order object has the required custom fields in setup
    @Test(description = "Story 1146+1408+1335, Test 1361+1453+1456 ; ValidateWOHasAccurateCustomFields")
    public void ValidateWOHasAccurateCustomFields() {
        String object = "Work Order";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Facility", "Problem Type", "Pacesetter Code", "Service Type", "Facility Text", "Share Internally"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }

    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the shift object has the required custom fields in setup
    @Test(description = "Story 1190, Test 1348 ; ValidateShiftHasAccurateCustomFields")
    public void ValidateShiftHasAccurateCustomFields() {
        String object = "Shift";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Start Time", "End Time", "Status", "Service Resource", "Time Slot Type"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }

    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the service appointment object has the required custom fields in setup
    @Test(description = "Story 1251+1148+1408+1335+1352, Test 1295+1373+1453+1456+1417 ; ValidateSAHasAccurateFields")
    public void ValidateSAHasAccurateFields() {
        String object = "Service Appointment";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Parent Work Order ID", "Case ID", "Is Courtesy Call", "JOA", "Pacesetter Code", "Legacy Code", "PTA Date & Time", "Facility", "Problem Type"
                , "Service Type", "Year", "Vehicle Type", "Vehicle Description", "Underground Height", "Tow Passenger", "Longitude", "Latitude", "Tow Distance", "Tow Destination Name",
                 "Tow Destination Address", "Flag", "Red Flag", "Province", "Policy", "Pickup Point", "Phone 1",
                "Other Underground Height", "Model", "Member Since", "Membership Status", "Membership Level", "Member Holding For Call Back Notes", "Member Holding For Call Back", "Member Expiry Date", "Make"
                , "Long Tow Calls Remaining", "Long Tow Calls Allowed", "Location Code", "License Plate", "Last Name", "Landmark", "Key Location", "Home Address", "History Calls", "Fuel Type", "First Name", "Expected Cash Amount"
                , "Expected Cost To Tow Vehicle", "Estimated Over KM", "Email", "Driver Directions", "Driveline", "Current Calls", "Color", "Club Membership", "Chargeable Calls", "Cash Call", "RAE Priority", "Case Priority", "Call this long before arrival (minutes)", "Case Callback Reason"
                , "Call Type", "Calls Remaining", "Calls Allowed", "Callback", "Callback Type", "Call Taker Comments", "Phone 2", "Phone 1 Type", "Phone 2 Type", "Phone 1 Opt-In", "Phone 2 Opt-In", "Phone 1 Primary", "Phone 2 Primary", "Preferred Name", "Manually Spotted"
                , "Manual Spot Reason", "Cancellation Reason", "Facility Code", "Facility Address", "Facility Phone", "Breakdown Location Cross Street", "Motorcycle Type"
                , "Motorcycle Engine Type", "RV Class", "RV Engine Location", "Trailer Type", "Trailer Length", "Trailer Plug Type", "Trailer Hitch Type", "Trailer Hitch Size", "Call Creation Date & Time", "Flat Tire Location", "Membership Level",
                "Internal SLR Geolocation", "Address", "Key Location Other", "Facility Text", "Share Internally", "Service Appointment Priority",
                "Tow Destination Geolocation"
        };

        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }

    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the field service package is installed on the env
    @Test(description = "Story 603, Test 1336 ; ValidateTheExistenceSalesforceFieldServiceAppPackage")
    public void ValidateTheExistenceSalesforceFieldServiceAppPackage() {
        String setupPage = "Installed Packages";
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetupQuickFind(setupPage);
        UIActions.Click(driver.findElement(By.xpath("//mark[text()='" + setupPage + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='Installed Packages ~ Salesforce - Unlimited Edition']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Salesforce Field Service App Package']")));
    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the BCAA Road assist case custom setting is configured on the env
    @Test(description = "Story 476, Test 950 ; ValidateTheExistenceSalesforceFieldServiceAppPackage")
    public void ValidateTheExistenceOfBCAARoadAssistCustomMetadata() {
        String setupPage = "Custom Metadata Types";
        String customMetadataName="BCAA Road Assist Case";
        String[] customFields={"Basic Long Tow Allotment (KM)","Long Tow Overage Cost/KM","Plus Long Tow Allotment (KM)","Premier Long Tow Allotment (KM)"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetupQuickFind(setupPage);
        UIActions.Click(driver.findElement(By.xpath("//mark[text()='" + setupPage + "']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='All Custom Metadata Types ~ Salesforce - Unlimited Edition']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='"+customMetadataName+" Settings']")));
        UIActions.Click(driver.findElement(By.xpath("//a[text()='"+customMetadataName+" Settings']")));
        UIActions.SetDelayAfterAction(3000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Custom Metadata Type: "+customMetadataName+" Settings ~ Salesforce - Unlimited Edition']")));
        for (String s : customFields) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='" + s + "']")));
        }
        driver.switchTo().defaultContent();
    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the BCAA Road assist case custom setting is configured on the env
    @Test(description = "Story 1497, Test 1526 ; ValidateTheExistenceOfBCAARoadAssistSASettingsCustomMetadata")
    public void ValidateTheExistenceOfBCAARoadAssistSASettingsCustomMetadata() {
        String setupPage = "Custom Metadata Types";
        String customMetadataName="BCAA Road Assist SA";
//        String[] customFields={"Basic Long Tow Allotment (KM)","Long Tow Overage Cost/KM","Plus Long Tow Allotment (KM)","Premier Long Tow Allotment (KM)"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetupQuickFind(setupPage);
        UIActions.Click(driver.findElement(By.xpath("//mark[text()='" + setupPage + "']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='All Custom Metadata Types ~ Salesforce - Unlimited Edition']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='"+customMetadataName+" Settings']")));
//        UIActions.Click(driver.findElement(By.xpath("//a[text()='"+customMetadataName+" Settings']")));
//        UIActions.SetDelayAfterAction(3000);
//        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Custom Metadata Type: "+customMetadataName+" Settings ~ Salesforce - Unlimited Edition']")));
//        for (String s : customFields) {
//            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='" + s + "']")));
//        }
        driver.switchTo().defaultContent();
    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the ERS Vehicle Record Type was assigned for admin
    @Test(description = "Story 1293, Test 1367+1366 ; VerifyAdminProfileHasERSVehicleRecordTypeAssignedForSysAdmin")
    public void VerifyAdminProfileHasERSVehicleRecordTypeAssignedForSysAdmin() {
        String setupPage = "Profiles";
        String specificProfileName = "System Administrator";
        String profilePermissionName = "Object Settings";
        String settingName = "Locations";
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetupQuickFind(setupPage);
        UIActions.Click(driver.findElement(By.xpath("//mark[text()='" + setupPage + "']")));
        driver.switchTo().frame((driver.findElement(By.xpath("//iframe[@title='User Profiles ~ Salesforce - Unlimited Edition']"))));
        UIActions.Click(driver.findElement(By.xpath("//a[text()='" + specificProfileName + "']")));
        driver.switchTo().frame((driver.findElement(By.xpath("//iframe[@title='Profile: System Administrator ~ Salesforce - Unlimited Edition']"))));
        UIActions.ScrollBottomOfPage(1, 1000, true);
        actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Object Settings')]"))).build().perform();
        actions.doubleClick().build().perform();
        UIActions.SetDelayAfterAction(4000);
        driver.switchTo().frame((driver.findElement(By.xpath("//iframe[@title='Profile: System Administrator ~ Salesforce - Unlimited Edition']"))));
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//a[text()='" + settingName + "'][1]")));
        UIActions.Click(driver.findElement(By.xpath("//a[text()='" + settingName + "'][1]")));
        driver.switchTo().frame((driver.findElement(By.xpath("//iframe[@title='Profile: System Administrator ~ Salesforce - Unlimited Edition']"))));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//td[text()='ERS Vehicle']//following::input[@checked][1]")));
        driver.switchTo().defaultContent();

    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test checks that the enable notification checkbox is enabled in the field service settings taB
    @Test(description = "Story 603, Test 1337 ; ValidateThatEnableNotificationCheckboxIsEnable")
    public void ValidateThatEnableNotificationCheckboxIsEnable() {
        String setupPage = "Field Service Settings";
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetupQuickFind(setupPage);
        UIActions.Click(driver.findElement(By.xpath("//mark[text()='" + setupPage + "']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-input[@checked]//span[text()='Enable notifications']")));
    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test checks that the work rule configuration is correct in the system
    @Test(description = "Story 832, Test 1444 ; ValidateWorkRuleConfiguration")
    public void ValidateWorkRuleConfiguration() {
        int i = 0;
        int z = 0;
        String[] workRuleName = {"BCAA Road Assist Working Territories", "BCAA Road Assist Match Skills", "BCAA Road Assist Service Resource Availability", "BCAA Road Assist Driver Availability", "BCAA Road Assist Facility Assignment"};
        String[] workRuleDescription = {"Match Service Appointment Facility service territory based on assigned STMs to drivers", "Match Skills on a Work with Service Resource (Driver)", "Drivers start at grid; Salesforce will not create breaks automatically", "Respect shifts set up for drivers", "Match Facility on the Service Appointment to a Service Resource assigned to a facility"};
        String[] recordType = {"Field Service - Working Territories", "Field Service - Match Skills", "Field Service - Service Resource Availability", "Field Service - TimeSlot Designated Work", "Field Service - Match Fields"};
        UIActions.SearchInAppLauncher("Work Rules");
        WebFlows.OpenViewAllListview();
        for (String s : workRuleName) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[@title='" + s + "']")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[@title='" + workRuleDescription[i] + "']")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[@title='" + recordType[z] + "']")));
            i++;
            z++;
        }

    }

    //This test checks that operating hours were created in the system match the requirement
    @Test(description = "Story 714, Test 1168 ; ValidateOperatingHoursAreCreatedInTheSystem")
    public void ValidateOperatingHoursAreCreatedInTheSystem() {
        int i = 0;
        int z = 0;
        String[] workRuleName = {"RA MST Operating Hours", "RA PST Operating Hours"};
        UIActions.SearchInAppLauncher("Operating Hours");
        WebFlows.RefreshPage();
        WebFlows.OpenViewAllListview();
        UIActions.SetDelayAfterAction(2000);
        for (String s : workRuleName) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[@title='" + s + "']")));

        }

    }


    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test checks that products created in the system match the requirement
    @Test(description = "Story 1183, Test 1358+1359+1360 ; ValidateProductsMatchTheRequirement")
    public void ValidateProductsMatchTheRequirement() {
        String[] columnName = {"Product Name", "Product Code", "Product Family", "Product Description", "Active (Product)"};
        String productFamily="Battery";
        String[] products = {"Battery 1", "Battery 2", "Battery 3"
//                ,"A La Carte Associate", "A La Carte Principal",
//                "Basic Associate","Basic Principal","Kids Go Free Associate","Plus Associate",
//        "Plus Principal","Plus RV Associate","Plus RV Principal","Premier Associate","Premier RV Associate", "Premier RV Principal"
//              ,"Premier Principal"
        };
        UIActions.SearchInAppLauncher("Products");
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(3000);
        WebFlows.OpenViewAllListview();
        for (String s : columnName) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }
        for (String s : products) {
            UIActions.UpdateText(driver.findElement(By.xpath("//input[@aria-label='Search All Products list view.']")), s);
            driver.findElement(By.xpath("//input[@aria-label='Search All Products list view.']")).sendKeys(Keys.ENTER);

            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[@title='" + s + "']")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + productFamily + "']")));
        }

    }

    //In order to execute this test you must run it from an admin profile, since CT doesn't have access to setup
    //This test validates if the skills are created according to the requirement in setup
    @Test(description = "Story 1176, Test 1342 ; ValidateSkillsAreCreatedAsRequired")
    public void ValidateSkillsAreCreatedAsRequired() {
        String[] skillList = {"1 - Boost", "2 - Will Not Start", "3 - Flat", "4 - Lockout", "5 - Fuel-gas", "5D - Fuel-Diesel", "5P - Fuel-Propane",
                "6 - Transmission", "7 - Recovery", "8 - Ignition", "9 - Overheating", "10 - Brakes", "11 - Clutch", "12 - Starter", "13 - Stalling", "14 - Mechanical", "16 - Charging System",
                "17 - Fuel Delivery Problem", "18 - Alternator", "19 - Flatbed Tow (Xperigo)", "20 - Heavy-Duty Tow (Xperigo)", "21 - Locksmith (Xperigo)", "BA - Battery Sale", "MV - Motor Vehicle Accident"
                ,  "BI - Battery Inquiry", "BK - Blocker Call", "8A - Ignition - won't turn"
//                "15 - Other",
        };
        String setupPage = "Skills";
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInSetupQuickFind(setupPage);
        UIActions.SetDelayAfterAction(4000);
        UIActions.Click(driver.findElement(By.xpath("//div[@title='Skills']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Salesforce - Unlimited Edition']")));
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(driver.findElement(By.xpath("//a[text()='more']")));
        UIActions.ScrollTopOfPage(1, 1000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Salesforce - Unlimited Edition']")));
        UIActions.ScrollBottomOfPage(1, 1000, true);
        try{
        for (String s : skillList) {
        if (s.contains("8A - Ignition")) {

            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[contains(text(),'8A - Ignition')]")));
            driver.switchTo().defaultContent();
        }
        else {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='" + s + "']")));
            driver.switchTo().defaultContent();

             }
        }
        }
        catch (Exception e) {
            driver.switchTo().defaultContent();
        }

    }



    //This test checks that priority settings are configured correctly
    @Test(description = "Story 666, Test 666 ; stam")
    public void stam() {
        String expectedServiceAppointmentPriority = "1.0";
        String expectedSLOP = "true";
        Double serviceAppointmentPriority = RestActions.GetFloatValueFromSpecificRecordCell("ServiceAppointment", "RA_SAPriority__c", "AppointmentNumber", "SA-0001");
        String sAP = String.valueOf(serviceAppointmentPriority);
        boolean actualScheduleOverLowerPriority = RestActions.GetBooleanValueFromSpecificRecordCell("ServiceAppointment", "FSL__Schedule_over_lower_priority_appointment__c", "AppointmentNumber", "SA-0001");
        String actualSLOP = String.valueOf(actualScheduleOverLowerPriority);
        System.out.println(serviceAppointmentPriority);
        System.out.println(actualScheduleOverLowerPriority);
        Verifications.VerifyElementTextIsEqualToExpectedText(sAP, expectedServiceAppointmentPriority);
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSLOP, expectedSLOP);

    }



    //This test checks that case priority values are checked in setup
    @Test(description = "Story 521, Test 716 ; ValidateCasePriorityFieldsAreConfiguredInSetup")
    public void ValidateCasePriorityFieldsAreConfiguredInSetup() {
        String[] priorityValues = {"P1 Baby/Animal Locked in Car",
                "P2 Mbr Safety/Medical Concern", "P3 Extreme Weather/No Shelter", "P4 Police on Scene", "P5 Blocking Traffic", "P6 Accident", "P7 Freeway/Highway Call", "P8 Lockout,Engine Running", "R2 BCAA Error/Re-Dispatched Call", "Regular Call", "Home Based Call", "Key & Release (Member not in vehicle)", "Evo (Member not in vehicle)"};
        String object = "Case";
        String section = "Fields & Relationships";
        String field = "Case Priority";
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : priorityValues) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }
    }

//    @Ignore("Skipping as its failing due to configuration of the SA status transition")
    @Test(description = "Story 1347, Test 1415 ; VerifyWOChangesToStatusCancelledIfTheChildSAIsCancelled")
    public void VerifyWOChangesToStatusCancelledIfTheChildSAIsCancelled() throws InterruptedException {
        String status = "Cancelled";
        workOrderPage.CreateNewWorkOrder();
        String workOrderNumber = driver.findElement(By.xpath("//div[text()='Work Order']//following::span[@class='uiOutputText'][1]")).getText();
        WebFlows.CloseOpenedTab();
        serviceAppointmentPage.CreateServiceAppointmentFromWorkOrder(workOrderNumber, "Abagael Carlin", "2023", "06", "01", "10:00 a.m.", "2023", "06", "06", "12:00 p.m.");
        Verifications.VerifyElementPresent(mainPage.alert_successToastMessage);
        WebFlows.RefreshPage();
        UIActions.Click(serviceAppointmentPage.btn_editStatus);
        UIActions.Click(serviceAppointmentPage.dropdown_status);
        UIActions.Click(driver.findElement(By.xpath("//a[@title='" + status + "']")));
        UIActions.Click(serviceAppointmentPage.btn_SASave);
        UIActions.SetDelayAfterAction(2000);
        UIActions.Click(driver.findElement(By.xpath("//a[text()='" + workOrderNumber + "']")));
        WebFlows.RefreshPage();
        try {
            Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Status']//following::span[text()='" + status + "'][1]")).getText(), status);
        }
        catch (AssertionError e){
            Assert.assertFalse(true);
        }        catch (org.openqa.selenium.UnhandledAlertException e){
            Assert.assertFalse(true);
        }
    }

    //This test is checking that after a facility is populated, that the facility text field is populated as well (In the BE) with the same name as the facility
    @Test(description = "Story 1408, Test 1455 ; VerifyFacilityTextIsPopulatedAutomaticallyAfterFacilityIsPopulated")
    public void VerifyFacilityTextIsPopulatedAutomaticallyAfterFacilityIsPopulated() throws InterruptedException {
        String facility = "Guy T Facility";
        serviceAppointmentPage.CreateServiceAppointment("Abagael Carlin", "2023", "06", "01", "10:00 a.m.", "2023", "06", "06", "12:00 p.m.");
        Verifications.VerifyElementPresent(mainPage.alert_successToastMessage);
        WebFlows.RefreshPage();
        UIActions.Click(serviceAppointmentPage.btn_editStatus);
        UIActions.UpdateText(driver.findElement(By.xpath("//input[@placeholder='Search Facilities...']")), facility);
        driver.findElement(By.xpath("//input[@placeholder='Search Facilities...']")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.xpath("//input[@placeholder='Search Facilities...']")).sendKeys(Keys.ENTER);
        UIActions.Click(serviceAppointmentPage.btn_SASave);
        UIActions.SetDelayAfterAction(5000);
        String serviceAppointmentNumber = driver.findElement(By.xpath("//div[text()='Service Appointment']//following::span[@class='uiOutputText'][1]")).getText();
        System.out.println(driver.findElement(By.xpath("//div[text()='Service Appointment']//following::span[@class='uiOutputText'][1]")).getText());
        String actualFacility = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "RA_FacilityText__c", "AppointmentNumber", serviceAppointmentNumber);
        Verifications.VerifyElementTextIsEqualToExpectedText(actualFacility, facility);
    }

    //This test 'Service Appointment Priority' and 'Schedule Over Lower Priority' fields are populating on the SA, based on the SA 'Case Priority' field
    //The field and its value that we update the object with is derived from this function JsonPayloads.UpdateServiceAppointmentJson()
    @Test(description = "Story 1352, Test 1418 ; VerifyAutomaticPopulationOfSAPriorityAndScheduleOverLowerPriority")
    public void VerifyAutomaticPopulationOfSAPriorityAndScheduleOverLowerPriority() throws InterruptedException {
        String expectedServiceAppointmentPriority = "1.0";
        String expectedSLOP = "true";
        serviceAppointmentPage.CreateServiceAppointment("Abagael Carlin", "2023", "06", "01", "10:00 a.m.", "2023", "06", "06", "12:00 p.m.");
        Verifications.VerifyElementPresent(mainPage.alert_successToastMessage);
        String serviceAppointmentNumber = driver.findElement(By.xpath("//div[text()='Service Appointment']//following::span[@class='uiOutputText'][1]")).getText();
        System.out.println(serviceAppointmentNumber);

        String serviceAppointmentId = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "Id", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The id of this record is" + serviceAppointmentId);
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonCriticalPriority(), JsonPayloads.path_ServiceAppointment, serviceAppointmentId);

        Double serviceAppointmentPriority = RestActions.GetFloatValueFromSpecificRecordCell("ServiceAppointment", "RA_SAPriority__c", "AppointmentNumber", serviceAppointmentNumber);
        String actualsAP = String.valueOf(serviceAppointmentPriority);

        boolean actualScheduleOverLowerPriority = RestActions.GetBooleanValueFromSpecificRecordCell("ServiceAppointment", "FSL__Schedule_over_lower_priority_appointment__c", "AppointmentNumber", serviceAppointmentNumber);
        String actualSLOP = String.valueOf(actualScheduleOverLowerPriority);

        System.out.println(actualsAP);
        System.out.println(actualSLOP);
        Verifications.VerifyElementTextIsEqualToExpectedText(actualsAP, expectedServiceAppointmentPriority);
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSLOP, expectedSLOP);

    }

    //This test if "RAE Priority" field is populating on the SA, based on the SA 'Case Priority' field
    //The field and its value that we update the object with is derived from this function JsonPayloads.UpdateServiceAppointmentJson()
    @Test(description = "Story 1442+521, Test 1464+719 ; VerifyAutomaticPopulationOfRAEPriorityFields")
    public void VerifyAutomaticPopulationOfRAEPriorityFields() throws InterruptedException {
        String expectedServiceAppointmentPriorityCritical = "Critical";
        String expectedServiceAppointmentPriorityPriority = "Priority";
        String expectedServiceAppointmentPriorityLowPriority = "Low Priority";
        String expectedServiceAppointmentPriorityHomeBasedCall = "Home Based Calls";
        String expectedServiceAppointmentPriorityRegularCalls = "Regular Calls";
        //Create SA and store its number and fetch its id
        serviceAppointmentPage.CreateServiceAppointment("Abagael Carlin", "2023", "06", "01", "10:00 a.m.", "2023", "06", "06", "12:00 p.m.");
        Verifications.VerifyElementPresent(mainPage.alert_successToastMessage);
        String serviceAppointmentNumber = driver.findElement(By.xpath("//div[text()='Service Appointment']//following::span[@class='uiOutputText'][1]")).getText();
        System.out.println(serviceAppointmentNumber);
        String serviceAppointmentId = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "Id", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The id of this record is" +" "+ serviceAppointmentId);

        // Update the SA to Critical Priority and verify if the actual RAE priority match the expected
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonCriticalPriority(), JsonPayloads.path_ServiceAppointment, serviceAppointmentId);
        String actualSAPC = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "RA_CaseRAEPriority__c", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The RAE priority of this SA has changed to: "+" "+actualSAPC );
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSAPC, expectedServiceAppointmentPriorityCritical);


        // Update the SA to Priority Priority and verify if the actual RAE priority match the expected
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonPriorityPriority(), JsonPayloads.path_ServiceAppointment, serviceAppointmentId);
        String actualSAPP = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "RA_CaseRAEPriority__c", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The RAE priority of this SA has changed to: "+" "+actualSAPP );
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSAPP, expectedServiceAppointmentPriorityPriority);


        // Update the SA to Low Priority Priority and verify if the actual RAE priority match the expected
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonLowPriority(), JsonPayloads.path_ServiceAppointment, serviceAppointmentId);
        String actualSAPLP = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "RA_CaseRAEPriority__c", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The RAE priority of this SA has changed to: "+" "+actualSAPLP );
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSAPLP, expectedServiceAppointmentPriorityLowPriority);

        // Update the SA to Home Based Calls Priority and verify if the actual RAE priority match the expected
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonHomeBasedCall(), JsonPayloads.path_ServiceAppointment, serviceAppointmentId);
        String actualSAPHBC = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "RA_CaseRAEPriority__c", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The RAE priority of this SA has changed to: "+" "+actualSAPHBC );
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSAPHBC, expectedServiceAppointmentPriorityHomeBasedCall);


        // Update the SA to Regular Calls Priority and verify if the actual RAE priority match the expected
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonRegularCalls(), JsonPayloads.path_ServiceAppointment, serviceAppointmentId);
        UIActions.SetDelayAfterAction(1000);
        String actualSAPRC = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "RA_CaseRAEPriority__c", "AppointmentNumber", serviceAppointmentNumber);
        System.out.println("The RAE priority of this SA has changed to: "+" "+actualSAPRC );
        Verifications.VerifyElementTextIsEqualToExpectedText(actualSAPRC, expectedServiceAppointmentPriorityRegularCalls);

    }

    //This test checks that resource absence type field values are configured in the system (setup
    @Test(description = "Story 1298, Test 1355 ; ValidateRATypeValuesAreConfiguredInFieldServiceSettings")
    public void ValidateRATypeValuesAreConfiguredInFieldServiceSettings() {
        String object = "Resource Absence";
        String section = "Fields & Relationships";
        String field = "Type";
        String[] statusList = {"Break", "Absence", "Scheduled Off", "Off Road Work"};
        WebFlows.OpenSetupWindow();
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, field);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + field + "'][1]")));
//        List<WebElement> elems = driver.findElements(By.xpath("//span[text()='" + field + "']"));
//        UIActions.Click(elems.get(elems.size() - 1));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Field: " + field + " ~ Salesforce - Unlimited Edition']")));
        for (String s : statusList) {
            try {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
            }
            catch (AssertionError e){
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);

            }
        }
    }


    //This test checks that resource absence field values are configured in the system (setup(
    @Test(description = "Story 1298, Test 1341 ; ValidateRAFieldsAreConfiguredInSetup")
    public void ValidateRAFieldsAreConfiguredInSetup() {
        String object = "Resource Absence";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Start Time","End Time", "Record Type", "Type", "Resource"};
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }
    }

    //This test validates that the Worl type object in the system have the correct fields according to the config book (on setup)
    @Test(description = "Story 1145, Test 1351 ; ValidateWorkTypeObjectHasCorrectFields")
    public void ValidateWorkTypeObjectHasCorrectFields() {
        String object = "Work Type";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Work Type Name", "Description", "Estimated Duration", "Auto-Create Service Appointment", "Due Date Offset", "Exact Appointments"};
        String[] fieldsType = {"Text(255)", "Long Text Area(32000)", "Number(16, 2)", "Checkbox", "Number(9, 0)", "Checkbox"};
        int i = 0;
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + fieldsType[i] + "']")));
            i++;
        }

    }

    //This test validates Polygon assignment object is created in the system and have the correct fields according to the config book (on setup)
    @Test(description = "Story 1011, Test 1195 ; VerifyPolygonAssignmentObject")
    public void VerifyPolygonAssignmentObject() {
        String object = "Polygon Assignment";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Polygon Territory", "Facility", "Service Type", "Proportion", "Level", "Start Time","End Time","Day"};
        String[] fieldsType = {"Lookup(Service Territory)", "Lookup(Facility)", "Picklist", "Number(16, 2)", "Number(18, 0)", "Time","Time","Picklist (Multi-Select)"};
        int i = 0;
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            UIActions.SetDelayAfterAction(500);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + fieldsType[i] + "']")));
            i++;
        }

    }

    //This test validates Polygon assignment object is created in the system and have the correct fields according to the config book (on setup)
    @Test(description = "Story 1012, Test 1108 ; VerifyServiceTerritoryObject")
    public void VerifyServiceTerritoryObject() {
        String object = "Service Territory";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Name", "Address", "Facility Code", "Facility Phone", "Facility Contact", "Facility Status", "Service Territory Type"};
        String[] serviceTypeValues = {"Province", "Region", "Polygon Territory", "BCAA Facility", "Contractor Facility", "Generic Facility"};
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            UIActions.SetDelayAfterAction(500);
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='Service Territory Type']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: Service Territory Type ~ Salesforce - Unlimited Edition']")));

        try {
            for (String s : serviceTypeValues) {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
            }
        } catch (AssertionError e) {
            driver.switchTo().defaultContent();
            Assert.assertFalse(true);

        }
    }

    //This test validates PTA Table object is created in the system and have the correct fields according to the config book (on setup)
    @Test(description = "Story 1024, Test 1433 ; VerifyPTATableObject")
    public void VerifyPTATableObject() {
        String object = "PTA Table";
        String section = "Fields & Relationships";
        String[] fieldsList = {"Facility Type", "Polygon Service Territory", "PTA", "PTA Threshold", "Facility","Default PTA Record","Service Type"};
        String[] serviceTypeValues={"B","F","L"};
        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        WebFlows.SearchInObjectManagerAndEnterACertainSectionInSetup(object, section);
        UIActions.Click(setupPage.search_setupQuickFindInsideObjectSection);
        for (String s : fieldsList) {
            UIActions.UpdateText(setupPage.search_setupQuickFindInsideObjectSection, s);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
            UIActions.SetDelayAfterAction(500);
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='Service Type']")));
        WebFlows.SwitchIframe(driver.findElements(By.xpath("//iframe[@title='" + object + " Custom Field: Service Type ~ Salesforce - Unlimited Edition']")));
        for (String s : serviceTypeValues) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='Deactivate']//following::th[text()='" + s + "']")));
        }



    }

    //This test validates Traige Question and Triage Answer  objects were the system
    @Test(description = "Story 385, Test 1053 ; VerifyTriageQuestionAndTriageAnswerObject")
    public void VerifyTriageQuestionAndTriageAnswerObject() {
        String[] triageObjects = {"Triage Question","Triage Answer"};

        WebFlows.OpenSetupWindow();
        UIActions.SetDelayAfterAction(4000);
        UIActions.ClickElementInListByXpath("//span[@class='title slds-truncate']", 1);
        for (String s: triageObjects) {
            WebFlows.RefreshPage();
            UIActions.UpdateText(setupPage.search_ObjectQuickFind, s);
            List<WebElement> elems = driver.findElements(By.xpath("//a[text() = '"+s+"']"));
            if (elems.size() >= 2){
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='"+s+"']")));
            } else{
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='"+s+"']")));
            }
        }


    }


    //This test checks that service territories created in the system match the requirement
    @Test(description = "Story 714, Test 1171 ; ValidateSTWereUploadedToTheSystem")
    public void ValidateSTWereUploadedToTheSystem() {
        String[] territoryList = {"107 - Lower Sunshine Coast", "141 - Sechelt Area", "144 - Upper Sunshine Coast", "16 - White Rock Area",
                "20B - South Langley","312 - Sydney Area","452 - Polygon Territory","458 - Polygon Territory","461 - Polygon Territory",
        "545 - Hope Area","633 - Polygon Territory","658 - Polygon Territory","665 - Polygon Territory","667 - Polygon Territory","725 - Blind Bay Area",
        "760 - Logan Lake Area","787 - Chase Area","850 - Yahk Area","857 - Cranbrook Area"};
        UIActions.SearchInAppLauncher("Service Territories");
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(3000);
        WebFlows.OpenViewAllListview();
        for (String s : territoryList) {
            UIActions.Click(casePage.search_casesList);
            UIActions.UpdateText(casePage.search_casesList,s);
            casePage.search_casesList.sendKeys(Keys.ENTER);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='" + s + "']")));
        }

    }

    //This test checks that service territories created in the system match the requirement
    @Test(description = "Story 714, Test 1172 ; ValidateFacilitiesWereUploadedToTheSystem")
    public void ValidateFacilitiesWereUploadedToTheSystem() {
        List<String> expectedFacilityList = new ArrayList<>();
        String[] facilityList = {"ALL COAST TOWING", "ARROWSMITH AUTOMOTIVE", "BOWSER AUTOMOTIVE LTD.", "GARRICK AUTOMOTIVE LTD.",
                "GRANTON MOTORS LTD", "HOPE TOWING LTD", "J D TOWING INC", "KBM AUTOWORKS (GAS WEST OUTLET)", "MID ISLAND TOWING AND TRANSPORT LTD",
                "PENINSULA TOWING", "SORRENTO TOWING & RECOVERY", "VERNON AUTO TOWING LTD"};

        for (String s:facilityList){
            expectedFacilityList.add(s);
        }
        System.out.println(expectedFacilityList);
        List<String> actualFacilityList = new ArrayList<>();
        actualFacilityList = RestActions.GetListFromSpecificObject("Name", "RA_Facility__c");
        System.out.println(actualFacilityList);
        Verifications.VerifyIfOneListFullyContainsAnother(expectedFacilityList,actualFacilityList);
    }

    //This test checks that service territories created in the system match the requirement
    @Test(description = "Story 714, Test 1173 ; ValidateMapPolygonsWereUploadedToTheSystem")
    public void ValidateMapPolygonsWereUploadedToTheSystem() {
        List<String> expectedFacilityList = new ArrayList<>();
        String[] facilityList = {"107", "141", "144", "16",
                "20B", "312", "545", "725", "760",
                "787", "850", "857"};

        for (String s:facilityList){
            expectedFacilityList.add(s);
        }
        System.out.println(expectedFacilityList);
        List<String> actualFacilityList = new ArrayList<>();
        actualFacilityList = RestActions.GetListFromSpecificObject("Name", "FSL__Polygon__c");
        System.out.println(actualFacilityList);
        Verifications.VerifyIfOneListFullyContainsAnother(expectedFacilityList,actualFacilityList);
    }




    //This test checks that Map Polygon tab was created in the system
    @Test(description = "Story 714, Test 1169 ; VerifyMapPolygonTabWasCreated")
    public void VerifyMapPolygonTabWasCreated() {
        UIActions.SearchInAppLauncher("Map Polygons");
        WebFlows.RefreshPage();
        WebFlows.OpenViewAllListview();
        UIActions.SetDelayAfterAction(3000);
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='Map Polygons']")));
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



//    //This test checks that priority settings are configured correctly
//    @Test(description = "844 1514, Test 1419 ; ValidateReshuffleAssignmentsSettings")
//    public void ValidateReshuffleAssignmentsSettings() {
//        UIActions.SearchInAppLauncher("Field Service Settings");
//        UIActions.SetDelayAfterAction(2000);
//        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
//        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingTab);
//        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingDynamicGanttTab);
//        UIActions.ScrollBottomOfPage(3, 1000, true);
//        try {
//            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[contains(text(),'Max time horizon (days) in which the appointment can be scheduled')]//following::input[text()='1'][1]")));
//            driver.switchTo().defaultContent();
//        }
//
//        catch (AssertionError e) {
//            driver.switchTo().defaultContent();
//            Assert.assertFalse(true);
//        }
//    }

    //This test checks that fix scheduling settings are configured correctly
    @Test(description = "Story 844, Test 1515 ; ValidateFixSchedulingSettings")
    public void ValidateFixSchedulingSettings() {
        UIActions.SearchInAppLauncher("Field Service Settings");
        UIActions.SetDelayAfterAction(2000);
        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingTab);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingDynamicGanttTab);
        UIActions.ScrollBottomOfPage(1, 2000, true);
        try {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[contains(text(),'Automatically fix overlaps when an appointment overlaps with another appointment or absence')]//following::input[@class='ng-pristine ng-untouched ng-valid ng-not-empty'][1]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//option[@selected='selected' and text()='Schedule to all resources'][1]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//option[@selected='selected' and text()='Unschedule the appointment(s)'][1]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[contains(text(),'After unscheduling services reschedule them by')]//following::option[@selected='selected' and text()='Priority'][1]")));

            driver.switchTo().defaultContent();
        }

        catch (AssertionError e) {
            driver.switchTo().defaultContent();
            Assert.assertFalse(true);
        }
    }

    //This test checks that fill in schedule settings are configured correctly
    @Test(description = "Story 842, Test 1523 ; ValidateFillInScheduleSettings")
    public void ValidateFillInScheduleSettings() {
        UIActions.SearchInAppLauncher("Field Service Settings");
        UIActions.SetDelayAfterAction(2000);
        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingTab);
        UIActions.Click(fieldServiceSettingsPage.btn_SchedulingDynamicGanttTab);
        UIActions.ScrollBottomOfPage(1, 2000, true);
        try {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//p[contains(text(),'Fill-in schedule')]//following::label[contains(text(),'Service Appointment candidate Boolean field ')][1]//following::option[@label='Is Fill In Candidate' and @selected='selected'][1]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//p[contains(text(),'Fill-in schedule')]//following::label[contains(text(),'Work Order candidate Boolean field')][1]//following::option[@label='Is Fill In Candidate' and @selected='selected'][1]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//p[contains(text(),'Fill-in schedule')]//following::label[contains(text(),'Work Order Line Item candidate Boolean field')][1]//following::option[@label='Is Fill In Candidate' and @selected='selected'][1]")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//p[contains(text(),'Fill-in schedule')]//following::label[contains(text(),'Order candidate appointments by')][1]//following::option[@label='Priority' and @selected='selected'][1]")));
//            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//p[contains(text(),'Fill-in schedule')]//following::label[contains(text(),'Work Order Line Item candidate Boolean field')][1]//following::option[@label='Is Fill In Candidate' and @selected='selected'][1]")));
//            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//p[contains(text(),'Fill-in schedule')]//following::label[contains(text(),'Work Order Line Item candidate Boolean field')][1]//following::option[@label='Is Fill In Candidate' and @selected='selected'][1]")));


            driver.switchTo().defaultContent();
        }

        catch (AssertionError e) {
            driver.switchTo().defaultContent();
            Assert.assertFalse(true);
        }
    }

    //This test checks that rule violation configuration settings are configured correctly
    @Test(description = "Story 1429, Test 1513 ; ValidateRuleViolationConfigurationSettings")
    public void ValidateRuleViolationConfigurationSettings() {
        UIActions.SearchInAppLauncher("Field Service Settings");
        UIActions.SetDelayAfterAction(2000);
        driver.switchTo().frame(fieldServiceSettingsPage.iframe_fieldServiceSettingsIFrame);
        UIActions.Click(fieldServiceSettingsPage.btn_DispatcherConsoleUI);
        UIActions.Click(fieldServiceSettingsPage.btn_GANTTConfigurationsTab);
        UIActions.ScrollBottomOfPage(2, 1000, true);
        try {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//label[contains(text(),'Rule Validation Frequency')]//following::option[text()='Always']")));
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

    @Test(description = "Story 1346, Test 1451 ; ValidateSALifeCycleInFieldServiceSettings")
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
                System.out.println(s);
                i++;
            }


            catch (AssertionError e) {
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);
            }
            catch (org.openqa.selenium.NoSuchElementException e){
                System.out.println( );
                e.printStackTrace();
                driver.switchTo().defaultContent();
                Assert.assertFalse(true);

            }
        }
        driver.switchTo().defaultContent();

    }
}


//    @Test(description = "Story 1347, Test 1416 ; VerifyWOChangesToStatusCannotCompleteIfTheChildSAIsInStatusCannotComplete")
//    public void VerifyWOChangesToStatusCannotCompleteIfTheChildSAIsInStatusCannotComplete() throws InterruptedException {
//        String[] statusList = { "Open","Scheduled", "Dispatched", "Accepted", "En Route","On Location","Under Tow","Cannot Complete"};
//        String status = "Cannot Complete";
//        int i=0;
//        workOrderPage.CreateNewWorkOrder();
//        String workOrderNumber = driver.findElement(By.xpath("//div[text()='Work Order']//following::span[@class='uiOutputText'][1]")).getText();
//        WebFlows.CloseOpenedTab();
//        serviceAppointmentPage.CreateServiceAppointment(workOrderNumber,"Abagael Carlin","2023","06","01","10:00 AM","2023","06","06","12:00 AM");
//        Verifications.VerifyElementPresent(mainPage.alert_successToastMessage);
//        WebFlows.RefreshPage();
//        for (String s : statusList) {
//            UIActions.Click(serviceAppointmentPage.btn_editStatus);
//            if (s.equals("Open")){
//                UIActions.Click(driver.findElement(By.xpath("//span[text()='Status']//following::a[text()='Open'][1]")));
//                UIActions.SetDelayAfterAction(2000);
//                UIActions.Click(driver.findElement(By.xpath("//a[@title='"+statusList[i+1]+"']")));
//            }
//            if (s.equals("Cannot Complete")){
//            break;
//            }
//            else {
//                UIActions.Click(driver.findElement(By.xpath("//span[text()='Status']//following::a[text()='" + s + "'][1]")));
//                UIActions.Click(driver.findElement(By.xpath("//a[@title='"+statusList[i+1]+"']")));
//            }
//                UIActions.Click(serviceAppointmentPage.btn_SASave);
//                UIActions.SetDelayAfterAction(2000);
//                i++;
//        }
//        UIActions.Click(driver.findElement(By.xpath("//a[text()='"+workOrderNumber+"']")));
//        WebFlows.RefreshPage();
//        UIActions.SetDelayAfterAction(3000);
//        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Status']//following::span[text()='"+status+"'][1]")).getText(),status);
//
//    }

//    @Test(description = "Story 1347, Test 1414 ; VerifyWOChangesToStatusInProgressIfTheChildSAIsInStatusCompleted")
//    public void VerifyWOChangesToStatusInProgressIfTheChildSAIsInStatusCompleted() throws InterruptedException {
//        String[] statusList = { "Open","Scheduled", "Dispatched", "Accepted", "En Route","On Location","Under Tow","Cannot Complete"};
//        int i=0;
//        workOrderPage.CreateNewWorkOrder();
//        String workOrderNumber = driver.findElement(By.xpath("//div[text()='Work Order']//following::span[@class='uiOutputText'][1]")).getText();
//        WebFlows.CloseOpenedTab();
//        serviceAppointmentPage.CreateServiceAppointment(workOrderNumber,"Abagael Carlin","2023","06","01","10:00 AM","2023","06","06","12:00 AM");
//        Verifications.VerifyElementPresent(mainPage.alert_successToastMessage);
//        WebFlows.RefreshPage();
//        for (String s : statusList) {
//            UIActions.Click(serviceAppointmentPage.btn_editStatus);
//            if (s.equals("Open")){
//                UIActions.Click(driver.findElement(By.xpath("//span[text()='Status']//following::a[text()='Open'][1]")));
//                UIActions.SetDelayAfterAction(2000);
//                UIActions.Click(driver.findElement(By.xpath("//a[@title='"+statusList[i+1]+"']")));
//            }
//            if (s.equals("Cannot Complete")){
//                break;
//            }
//            else {
//                UIActions.Click(driver.findElement(By.xpath("//span[text()='Status']//following::a[text()='" + s + "'][1]")));
//                UIActions.Click(driver.findElement(By.xpath("//a[@title='"+statusList[i+1]+"']")));
//            }
//            UIActions.Click(serviceAppointmentPage.btn_SASave);
//            UIActions.SetDelayAfterAction(2000);
//            i++;
//        }
//        UIActions.Click(driver.findElement(By.xpath("//a[text()='"+workOrderNumber+"']")));
//        WebFlows.RefreshPage();
//        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Status']//following::span[text()='"+status+"'][1]")).getText(),status);
//
//    }

