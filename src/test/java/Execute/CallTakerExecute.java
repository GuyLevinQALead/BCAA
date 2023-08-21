package Execute;

import Extensions.UIActions;
import Extensions.Verifications;
import Pages.CasePage;
import Utilities.Operations;
import Utilities.ReadCsv;
import Workflows.WebFlows;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Listeners(Utilities.Listeners.class)
public class CallTakerExecute extends Operations {
    @BeforeClass
    public void BeforeExecution() {
        isApiTest = false;
        isAdminExecute = true;
        currentUrl = readData.GetData("Url");
        initBrowser("Chrome");
        WebFlows.Login(readData.GetData("CTusername"), readData.GetData("CTpassword"));
        WebFlows.CloseOpenedTab();
    }

    @AfterMethod
    public void AfterEveryTest() {
        WebFlows.CloseOpenedTab();
    }


    @Test(description = "Story:394, Test:469, 471 ; ValidateFieldsArePopulatedForCase")
    public void ValidateFieldsArePopulatedForCase() {
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
//        casePage.SaveNewCase();
//        UIActions.ClickAndWait(casePage.btn_EditStatus, "status");
        UIActions.Click(casePage.checkbox_JOA);
        UIActions.Click(casePage.checkbox_IsCourtesyCall);
//        casePage.SelectCaseOrigin();
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='JOA']/parent::label/parent::span/parent::div/parent::lightning-input[@checked]")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='Is Courtesy Call']/parent::label/parent::span/parent::div/parent::lightning-input[@checked]")));
    }


    @Ignore("Skipped because requirement was changed with story ROADASSIST-524 which will be automated later")
    @Test(description = "Story 396, Test 560 ; ValidateEvoCallTypeCaseCreatedSuccessfully")
    public void ValidateEvoCallTypeCaseCreatedSuccessfully() {
        String callType = "BK Bike Assist";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SelectCallTypeValue(callType);
        UIActions.SetDelayAfterAction(3000);
        casePage.SaveNewCase();
        UIActions.ScrollBottomOfPage(1, 1, true);
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + callType + "']");
    }


    //    @Test(description = "REST")
//    public void testrest() {
//        RestActions.GetValueFromSpecificRecordCell("Case","Case Priority","Regular Call","id");
//
//    }
    @Test(description = "Story 526, Test 667 ; ValidateCallTakerCanPopulateRideAlongField")
    public void ValidateCallTakerCanPopulateRideAlongField() {
        UIActions.SearchInAppLauncher("Cases");
        String rideAlongText = "Simple Text";
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Tow Destination");
        UIActions.Click(casePage.btn_rideAlongEdit);
        UIActions.UpdateText(casePage.txt_rideAlongText, rideAlongText);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-formatted-text[text()='" + rideAlongText + "']")));
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + rideAlongText + "']");
    }




    @Test(description = "Story 966, Test 1289 ; ValidateTowDestinationFields")
    public void ValidateTowDestinationFields() {
        String[] towDestinationFields = {"Tow Passenger","Tow Destination Address","Tow Destination Name","Tow Distance","Estimated Over KM" ,"Expected Cost to Tow Vehicle"};
        UIActions.SearchInAppLauncher("Cases");
        String rideAlongText = "Simple Text";
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Tow Destination");
        for(String s: towDestinationFields )
        Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
    }


    @Test(description = "Story 966+426, Test 1287+670 ; ValidateBreakdownLocationFields")
    public void ValidateBreakdownLocationFields() {
        String[] towDestinationFields = {"Breakdown Location Address","Address Geolocation","Service Territory","Breakdown Location Cross Street","Area",
        "Landmark","Location Code","Underground Height","Other Underground Height","Pickup Point","Driver Directions","Call Taker Comments" };
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_BreakdownLocationTabUndergroundHeight);
        UIActions.Click(casePage.dropdown_BreakdownLocationTabUndergroundHeightPicklist);
        UIActions.Click(casePage.dropdown_BreakdownLocationTabUndergroundHeightOtherValue);
        UIActions.Click(casePage.txt_BreakdownLocationTabUndergroundHeightOtherValue);
        UIActions.UpdateText(casePage.txt_BreakdownLocationTabUndergroundHeightOtherValue,"1");
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(1000);
        for(String s: towDestinationFields )
            Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
    }

    @Test(description = "Story 426, Test 668 ; ValidateBreakdownLocationUndergrounHeightValues")
    public void ValidateBreakdownLocationUndergrounHeightValues() {
        String[] undergroungHeightValues = {"6’5” (1.96m) and less",
                "6’6” – 6’7” (1.98m – 2.0m)",
                "6’8” – 6’9” (2.03m – 2.06m)",
                "6’10” (2.08m) and more",
                "Other" };
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_BreakdownLocationTabUndergroundHeight);
        UIActions.Click(casePage.dropdown_BreakdownLocationTabUndergroundHeightPicklist);
        for(String s: undergroungHeightValues ) {
            Verifications.VerifyElementIsPresentUsingXpath("//lightning-base-combobox-item[@data-value='"+s+"']");
        }
//        UIActions.Click(casePage.dropdown_BreakdownLocationTabUndergroundHeightOtherValue);
//        UIActions.Click(casePage.txt_BreakdownLocationTabUndergroundHeightOtherValue);
//        UIActions.UpdateText(casePage.txt_BreakdownLocationTabUndergroundHeightOtherValue,"1");
//        UIActions.Click(casePage.btn_SaveFieldEdit);
//        UIActions.SetDelayAfterAction(1000);

    }

    @Test(description = "Story 966+528, Test 1290+1261 ; ValidateServiceFields")
    public void ValidateServiceFields() {
        String[] towDestinationFields = {"PTA","Real-Time PTA","Table PTA",
        "Facility Code","Manual Spot Reason","Manually Spotted","Facility","Facility Phone","Facility Address",
        "Policy","Case Priority","RAE Priority","Cash Call","Expected Cash Amount","Driver To Collect - Details",
        "Call Type","Is Courtesy Call","JOA","Red Flag","Pacesetter Code","Legacy Code","Service Type","Problem Type",
        "Cancellation Reason","Case Origin","Member Holding for Call Back","Member Holding for Call Back Notes","Callback Type","Case Callback Reason", "Call this long before arrival (minutes)",
        "Created By","Last Modified By"
//                "PTA Date & Time",
        };
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.SetDelayAfterAction(10);
        UIActions.ScrollBottomOfPage(2, 1000, true);
        for(String s: towDestinationFields )
            Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
    }

    @Test(description = "Story 426, Test 671 ; ValidateCallTakerCanPopulateCTComment")
    public void ValidateCallTakerCanPopulateCTComment() {
        String callTakerComment = "Simple Text";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_callTakerComments);
        UIActions.UpdateText(casePage.txt_callTakerComments, callTakerComment);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + callTakerComment + "']");
    }

    @Test(description = "ERS app exist ; ValidateCallTakerCanSeeAndChooseERSApp")
    public void ValidateCallTakerCanSeeAndChooseERSApp() {
        UIActions.SearchInAppLauncher("ERS Call Taker Console");
        Verifications.VerifyElementIsPresentUsingXpath("//span[@title='ERS Call Taker Console']");
    }

    @Test(description = "Story 522, Test 696 ; ValidateCallBackOnPageLayout")
    public void ValidateCallBackOnPageLayout() {
        String s = RandomStringUtils.randomAlphabetic(3);
        String CallbackReason = "Sample" + " " + s;
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(2, 1, true);
        UIActions.Click(CasePage.casePage.btn_editPencilButton);
        Verifications.VerifyElementPresent(casePage.dropdown_caseCallback);
        Verifications.VerifyElementPresent(casePage.txt_caseCallbackReason);
        UIActions.Click(casePage.dropdown_caseCallback);
        UIActions.Click(casePage.dropdown_caseCallbackAutomatic);
        UIActions.Click(casePage.txt_caseCallbackReason);
        UIActions.SetDelayAfterAction(2000);
        UIActions.UpdateText(casePage.txt_caseCallbackReason, CallbackReason);
        casePage.SaveNewCase();
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + CallbackReason + "']");
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-formatted-text[text()='Automatic']")));
        UIActions.Click(CasePage.casePage.btn_editPencilButton);
        UIActions.Click(casePage.dropdown_caseCallback);
        UIActions.Click(casePage.dropdown_caseCallbackManual);
        casePage.SaveNewCase();
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-formatted-text[text()='Manual']")));

    }

    @Test(description = "Story 522, Test 697 ; ValidateCallThisLongBeforeArrivalField")
    public void ValidateCallThisLongBeforeArrivalField() {
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(2, 1, true);
        Verifications.VerifyTextInElement(casePage.label_callThisLongBeforeArrival, "10");
        UIActions.Click(casePage.btn_callThisLongBeforeArrival);
        UIActions.UpdateText(casePage.txt_callThisLongBeforeArrival, "4");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "Save edit");
        Verifications.VerifyElementPresentByText("div", "The minimum value is 5 minutes");
        UIActions.UpdateText(casePage.txt_callThisLongBeforeArrival, "31");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "Save edit");
        Verifications.VerifyElementPresentByText("div", "Case Callback Reason is required when Call this long before arrival is over 30 minutes");
        UIActions.UpdateText(casePage.txt_caseCallBackReason, "This is call Reason text");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "Save edit");
    }


    @Test(description = "Story 527, Test 681 ; ValidateCallTypeHasCorrectValues")
    public void ValidateCallTypeHasCorrectValues() {
        List<WebElement> callTypes = new ArrayList<>();
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
//        UIActions.ScrollBottomOfPage(3,1000,true);
        UIActions.Click(casePage.dropdown_CallType);
//        UIActions.ClickAndWait(casePage.dropdown_CallType, "");
        System.out.println(WebFlows.ReadCSVFile(ReadCsv.OBJECTS.CALL_TYPE));
        for (Object s : WebFlows.ReadCSVFile(ReadCsv.OBJECTS.CALL_TYPE)) {
            try {
                callTypes.add(driver.findElement(By.xpath("//span[text()='" + s.toString() + "']")));
                //Verify Call type

                Verifications.VerifyListOfElements(callTypes);
                System.out.println(callTypes);
            } catch (Exception e) {
                Assert.fail("List is not identical" + e);
                break;
            }
        }

    }


    @Test(description = "Story 1076, Test 1122, 1123; ValidateCaseOriginCorrectValues")
    //Verify that the picklist of case origin has the required values and that its mandatory
    public void ValidateCaseOriginCorrectValues() {
        List<WebElement> caseOrigin = new ArrayList<>();
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        UIActions.Click(casePage.dropdown_Origin);
        List<WebElement> caseOriginCSV = WebFlows.ReadCSVFile(ReadCsv.OBJECTS.CASE_ORIGIN);
//        System.out.println(caseOriginCSV);
        for (Object s : WebFlows.ReadCSVFile(ReadCsv.OBJECTS.CASE_ORIGIN)) {
//            System.out.println(s);
            try {
                caseOrigin.add(driver.findElement(By.xpath("//label[text()='Case Origin']//following::span[text()='" + s + "']")));
                //Verify Case Origin
                Verifications.VerifyListOfElements(caseOrigin);
            } catch (Exception e) {
                Assert.fail("List is not identical" + e);
                break;
            }
        }
        casePage.SaveNewCase();
        UIActions.SetDelayAfterAction(1000);
        UIActions.Click(casePage.btn_EditCaseOrigin);
        UIActions.Click(driver.findElement(By.xpath("//button[@aria-label='Case Origin, Call Centre']")));
        UIActions.Click(driver.findElement(By.xpath("//label[text()='Case Origin']//following::span[text()='--None--'][1]")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//div[text()='Complete this field.']")));
        casePage.SaveNewCase();
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//h2[text()='We hit a snag.']")));
    }


    @Test(description = "Story 426, Test 669 ; ValidateLocationCodesCorrectValues")
    //Verify that the picklist of Location codes in the location object has the required values
    public void ValidateLocationCodesCorrectValues() {
        String account = readData.GetData("Account_Kevin");

        String[] statusList = {"A Alley", "C Carport", "CP Covered Parkade", "D Driveway", "DR Driveway REAR", "E East", "EE East bound East of", "EW East bound West of",
                "F Front", "FC Front Carport", "FD Front Driveway", "FG Front Garage", "FP Front Parking lot", "FU Front Underground", "G Garage", "MD Median", "N North", "NN North bound North of", "NS North bound South of", "O Opposite",
                "OC Opposite Carport", "OD Opposite Driveway", "OG Opposite Garage", "OP Opposite Parking lot", "OU Opposite Underground", "PK Parkade", "PL Parking Lot", "R Rear", "RC Rear Carport", "RD Rear Driveway", "RG Rear Garage",
                "RP Rear Parking lot", "RS Residence", "RU Rear Underground", "RW Roadway", "S South", "SC Side Carport", "SD Side Driveway", "SG Side Garage", "SH Shoulder", "SI Side", "SN South bound North of", "SP Side parking lot", "SS South bound South of", "SU Side Underground", "U Underground",
                "W West", "WE West bound East of", "WW West bound West of"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_EditLocationCode);
        UIActions.Click(casePage.dropdown_LocationCode);
//        System.out.println(caseOriginCSV);
        for (Object s : statusList) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[@title='"+s+"']")));

        }
    }

    @Test(description = "Story 623, Test 672 ; ValidatePolicyFieldAndValues")
    //Verify that the multi picklist of Policy has the correct values
    public void ValidatePolicyFieldAndValues() {
        String account = readData.GetData("Account_Kevin");

        String[] policyValues = {"01 Aware 2nd Tow Basic", "02 Overcharges", "03 Basic coverage only", "04 Aware special equipment charge", "05 Ferry charge/Wait time", "06 Border Charge/Wait time", "07 JOA 5km Tow Limit 48hrs", "08 Off Road Charge"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Service");
        UIActions.Click(casePage.btn_EditPolicy);
//        System.out.println(caseOriginCSV);
        for (Object s : policyValues) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[@title='"+s+"']")));

        }
    }


    @Test(description = "Story 525+521, Test 673+674+720 ; ValidateRedFlagAndPriorityAdjusted")
    public void ValidateRedFlagAndPriority() {
        String valueOne = "P1 Baby/Animal Locked in Car";
        String listViewName = "Road Assist-Member Holding for call back";
        //Creating a case and verifying that the red flag checkbox can be marked successfully and that the red flag is displayed
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1, 1000, true);
//        UIActions.ScrollToElementByWebElementWithActions(casePage.btn_editRedFlag);
        UIActions.Click(casePage.btn_editRedFlag);
        UIActions.Click(casePage.checkbox_RedFlag);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        Verifications.VerifyElementIsPresentUsingXpath("//span[text()='Red Flag']/parent::div/parent::div//lightning-input[@checked]");
        //verifying that specific priority values trigger the red flag is

        UIActions.Click(casePage.btn_editRedFlag);
        UIActions.Click(casePage.checkbox_RedFlag);
        casePage.SelectPriorityValue(driver.findElement(By.xpath("//button[@data-value='Regular Call']")), valueOne);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        Verifications.VerifyElementIsPresentUsingXpath("//span[text()='Red Flag']/parent::div/parent::div//lightning-input[@checked]");

        //verifying that specific priority values trigger don't trigger the red flag
        UIActions.Click(casePage.btn_editRedFlag);
        String valueTwo = "Home Based Call";
        casePage.SelectPriorityValue(driver.findElement(By.xpath("//button[@data-value='" + valueOne + "']")), valueTwo);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
        Verifications.VerifyElementIsPresentUsingXpath("//span[text()='Red Flag']/parent::div/parent::div//lightning-input");

    }


    @Test(description = "Story 521, Test 720 ; ValidateMemberCallbackListView")
    public void ValidateMemberHoldingForCallbackListView() {
        String s = RandomStringUtils.randomAlphabetic(3);
        String MemberHoldingForCallBackNotes = "Sample" + " " + s;
        String listViewName = "Road Assist-Member Holding for call back";
        //Marking the Member Holding for callback checkbox on a case and making sure it appeast on the Road Assist-Member Holding for call back list view
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        String caseNumber = WebFlows.GetCaseNumberFromASpecificCaseInAListViewAndReturnString();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(CasePage.casePage.btn_editPencilButton);
        UIActions.Click(casePage.checkbox_MemberHoldingForCallBack);
        UIActions.Click(casePage.txt_MemberHoldingForCallBackNotes);
        UIActions.UpdateText(casePage.txt_MemberHoldingForCallBackNotes, MemberHoldingForCallBackNotes);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
        UIActions.Click(casePage.btn_casesTab);
        WebFlows.OpenAnyListViewBasedOnItsName(listViewName);
        UIActions.Click(casePage.search_casesList);
        UIActions.UpdateText(casePage.search_casesList, caseNumber);
        casePage.search_casesList.sendKeys(Keys.ENTER);
        Verifications.VerifyElementIsPresentUsingXpath("//a[@title='" + caseNumber + "']");

    }

    @Test(description = "Story 521, Test 717 ; ValidateCaseCallbackCausesRedFlagToShow")
    public void ValidateCaseCallbackCausesRedFlagToShow() {
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(CasePage.casePage.btn_editPencilButton);
        UIActions.Click(casePage.checkbox_redFlag);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        Verifications.VerifyElementPresent(casePage.img_callbackFlag);
    }

    @Test(description = "Story 521, Test 718 ; ValidateMemberHoldingBackCheckboxMustValidateNotesFieldIsNotNull")
    public void ValidateMemberHoldingBackCheckboxMustValidateNotesFieldIsNotNull() {
        String s = RandomStringUtils.randomAlphabetic(3);
        String MemberHoldingForCallBackNotes = "Sample" + " " + s;
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
//        UIActions.Click(casePage.btn_redFlag);
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(CasePage.casePage.btn_editPencilButton);
        UIActions.Click(casePage.checkbox_MemberHoldingForCallBack);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Please populate Member Holding for Call Back Notes']")));
        Verifications.VerifyElementPresentByText("div", "Please populate Member Holding for Call Back Notes");
        UIActions.Click(casePage.txt_MemberHoldingForCallBackNotes);
        UIActions.UpdateText(casePage.txt_MemberHoldingForCallBackNotes, MemberHoldingForCallBackNotes);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-formatted-text[text()='" + MemberHoldingForCallBackNotes + "']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-formatted-text[text()='" + MemberHoldingForCallBackNotes + "']")));
    }

    @Test(description = "Story 424, Test 755 ; ValidateAddressAndCityAreDisplayedOnTheLayout")
    public void ValidateAddressAndCityAreDisplayedOnTheLayout() {
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        Verifications.VerifyElementPresent(casePage.txt_address_city);
        Verifications.VerifyElementPresent(casePage.txt_address_province);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
    }

    @Test(description = "Story 767, Test 569+932+938 (phone) ; ValidateAccountAddedAndFieldsPopulatedForCase")
    //Checking that once you choose an account for a case, its mobile phone and address are populated on the case
    public void  ValidateAccountAddedAndFieldsPopulatedForCase() {
        String account = "Abagael Carlin";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        UIActions.SetDelayAfterAction(500);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.SetDelayAfterAction(500);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='" + account + "']")));
        UIActions.SetDelayAfterAction(1000);
//        Verifications.VerifyElementIsReadOnly(driver.findElement(By.xpath("//span[text()='"+account+"']")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Phone 2']/parent::div/parent::div//div//span//slot//lightning-formatted-text")));
//        Verifications.VerifyElementIsReadOnly(driver.findElement(By.xpath("//span[text()='Phone 2']/parent::div/parent::div//div//span//slot//lightning-formatted-text")));
        System.out.println(driver.findElement(By.xpath("//span[text()='Home Address']//following::div[@class='slds-truncate']")).getText());
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Home Address']//following::div[@class='slds-truncate']")));
    }

    @Test(description = "Story 557, Test 793, Test 938  ; ValidateAccountPopulatesAllCorrectFieldsForBCAAMember")
    public void ValidateAccountPopulatesAllCorrectFieldsForBCAAMember() {
        String account = readData.GetData("Account_Kevin");
//      Vefiry that if a certain account has values in  the fields mentioned on the test, the fields are populated on the case after the account is chosen in the case
        String[] fields = {"Membership Number", "Membership Level", "Membership Status", "Member Since", "Membership Expiry Date"};
        String[] NumericFields = {"Calls Allowed", "Calls Remaining", "Calls Used", "Current Calls", "Chargeable Calls", "History Calls", "Long Tow Calls Allowed", "Long Tow Calls Remaining"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        UIActions.SetDelayAfterAction(500);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        try {
            for (int i = 0; i < fields.length; i++) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + fields[i] + "']/parent::div/following-sibling::div/descendant::lightning-formatted-text")));
                Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='" + fields[i] + "']/parent::div/following-sibling::div/descendant::lightning-formatted-text")));
//            Verifications.VerifyElementIsReadOnly(driver.findElement(By.xpath("//span[text()='" + fields[i] + "']//following::span[1]")));

            }
            for (int i = 0; i < NumericFields.length; i++) {
                Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='" + NumericFields[i] + "']/parent::div/following-sibling::div/descendant::lightning-formatted-number")));
//            Verifications.VerifyElementIsReadOnly(driver.findElement(By.xpath("//span[text()='" +  NumericFields[i] + "']//following::span[1]")));

            }
//        Verifications.VerifyElementIsReadOnly(driver.findElement(By.xpath("//span[text()='" +  NumericFields[i] + "']//following::span[1]")));
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            currentTest.fail("Not all fields were populated");
            Assert.assertFalse(true);
        }

    }


    @Test(description = "Story 382,1049 Test 933+934+935+936+937+1211 ; ValidateSearchAccountByEmailPhoneNameEmailNumber")
    //Validate that you can search an account using his name, phone, number and email
    public void ValidateSearchAccountByEmailPhoneNameEmailNumber() {
        String[] tableHeader = {"Account Name", "Membership Number", "Household Phone", "Mailing Address", "Email"};
        String[] account = {"Abagael Carlin", "911000", "2368111711", "acarlinet@about.com","4567 canada Avenue Silver Spring California 20906 United States"
//                "1440 Sunderland Avenue Silver Spring, MD 20906 US"
        };
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        UIActions.UpdateText(casePage.search_AccountTab_AccountTemp, account[0]);
        UIActions.ClickAndWait(casePage.link_showAllResults, "pressed link");
        for (String s : tableHeader) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//th[@title='" + s + "']")));

        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//a[@title='Abagael Carlin']")));
        Verifications.VerifyElementIsPresentUsingXpath("//td//a[@title='Abagael Carlin']");
        UIActions.UpdateText(driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@placeholder='Search undefined...']")), account[1]);
        actions.sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//a[@title='Abagael Carlin']")));
        Verifications.VerifyElementIsPresentUsingXpath("//td//a[@title='Abagael Carlin']");
        UIActions.UpdateText(driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@placeholder='Search undefined...']")), account[2]);
        actions.sendKeys(Keys.ENTER).build().perform();
        UIActions.UpdateText(driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@placeholder='Search undefined...']")), account[3]);
        actions.sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//a[@title='Abagael Carlin']")));
        Verifications.VerifyElementIsPresentUsingXpath("//td//a[@title='Abagael Carlin']");
        UIActions.UpdateText(driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@placeholder='Search undefined...']")), account[4]);
        actions.sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//a[@title='Abagael Carlin']")));
        Verifications.VerifyElementIsPresentUsingXpath("//td//a[@title='Abagael Carlin']");
        UIActions.ClickElementByXpath("//button[@title='Cancel']");
    }

    @Test(description = "Story 474, Test 768 + 770 ; ValidateHomeAddressFromAccountIsStoredInTwoDestination")
    //Description mentioned in the tests below
    public void ValidateHomeAddressFromAccountIsStoredInTowDestination() {
        String account = readData.GetData("Account_Abagael");
        String address;
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Home Address']/parent::div/parent::div/div/span/slot/lightning-formatted-address/a")));
        address = driver.findElement(By.xpath("//span[text()='Home Address']/parent::div/parent::div/div/span/slot/lightning-formatted-address/a")).getAttribute("aria-label");
        String NewAddress = address.replace("\n", " ");
        System.out.println(NewAddress);
        UIActions.ScrollTopOfPage(1, 1000);
        casePage.NavigateCaseInnerTab("Tow Destination");
        UIActions.ScrollBottomOfPage(1, 1000, true);

        //ticket 768 - validate that the address shown in the two destination table, partially matched the home address of the account
        String S = driver.findElement(By.xpath("//td[@data-label = 'Address']/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")).getText();
        String TableAddress = S.replace(",", "");
        List<String> items = new ArrayList<>(Arrays.asList(NewAddress.split(" ")));
        List<String> items1 = new ArrayList<>(Arrays.asList(TableAddress.split(" ")));
        System.out.println(TableAddress);
        Verifications.VerifyIfOneListFullyContainsAnother(items1, items);

        //ticket : 770 verify that after you select the address from the tow destination table, it populates on the page
        UIActions.Click(casePage.radio_homeAddress);
        UIActions.SetDelayAfterAction(1000);
        UIActions.Click(casePage.input_Select);
        UIActions.ScrollTopOfPage(1, 1000);
        try {
            Verifications.VerifyElementPresent(casePage.address_TowDestination);
        }
        catch (Exception e){
            Assert.assertFalse(true);
        }
        UIActions.ScrollTopOfPage(1, 1000);
    }

    @Test(description = "Story 523, Test 941+942 ; ValidateCashCallTypeISUpdatingAccordingToAccountsConfiguration")
    public void ValidateCashCallTypeISUpdatingAccordingToAccountsConfiguration() {
        String account = readData.GetData("Account_Kevin");
        String cashCallExpected = "Over Entitlement";
        String address;
        //Step 1
//        UIActions.SearchInAppLauncher( "Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("BCAA Member");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Cash Call']/parent::div/following-sibling::div/span/slot/lightning-formatted-text")));
        String cashCallTypeValue = driver.findElement(By.xpath("//span[text()='Cash Call']/parent::div/following-sibling::div/span/slot/lightning-formatted-text")).getText();
        Verifications.VerifyElementTextIsEqualToExpectedText(cashCallTypeValue, cashCallExpected);

        //step 2
        UIActions.ScrollTopOfPage(2, 800);
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, readData.GetData("Account_Abagael"));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Service");
        cashCallTypeValue = driver.findElement(By.xpath("//span[text()='Cash Call']/parent::div/following-sibling::div/span/slot/lightning-formatted-text")).getText();
        Verifications.VerifyElementTextIsEqualToExpectedText(cashCallTypeValue, "");
    }

    @Test(description = "Story 527,966 Test 982, 983,1290+981 ; ValidateKeyLocationCallTypes")
    public void ValidateKeyLocationCallTypes() {
        String[] types = {"KD Drvr Key & Release", "KM Mbr Key & Release", "KO Oper Key & Release", "2T 2nd Tow"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.SetDelayAfterAction(1500);
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_callTypeEdit);

        //Ticket 982+983+1290 (Key location fields appear)
        for (int i = 0; i < 3; i++) {
            casePage.SelectCallTypeValue(types[i]);
            UIActions.SetDelayAfterAction(1500);
            Verifications.VerifyElementPresent(casePage.dropdown_KeyLocation);
        }
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.info_KeyLocationInfo);
        Verifications.VerifyElementIsPresentUsingXpath("//div[text()='If you select ‘Other’ in Key Location, please populate Key Location - Other.']");
        UIActions.Click(casePage.btn_callTypeEdit);
        for (int i = 3; i < 4; i++) {
            casePage.SelectCallTypeValue(types[i]);
            UIActions.SetDelayAfterAction(1500);
            Verifications.VerifyElementIsNotPresentUsingAXpath("//label[text()='Key Location']/parent::lightning-combobox//button[@data-value='--None--']");
        }


    }

    @Test(description = "Story 476, test 952 ; ValidateTowDistanceForRegularCostumerPopulatedAsRequested")
    public void ValidateTowDistanceForRegularCostumerPopulatedAsRequested() {
        String usedCase = readData.GetData("regularCase");
        UIActions.Click(mainPage.link_Cases);
        WebFlows.SelectSpecificRecordInListView(usedCase);
        casePage.NavigateCaseInnerTab("Tow Destination");
        UIActions.Click(casePage.btn_TowDistanceEdit);
        //Step 1
        UIActions.UpdateText(casePage.txt_TowDistanceText, "5");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(1000);
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.EstimateOverKM(), "0.00");
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.ExpectedCostToTowVehicle(), "$0.00");

        //Step 2
        UIActions.Click(casePage.btn_TowDistanceEdit);
        UIActions.UpdateText(casePage.txt_TowDistanceText, "6");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(1000);
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.EstimateOverKM(), "1.00");
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.ExpectedCostToTowVehicle(), "$3.50");
    }

    @Test(description = "Story 476, test 953 ; ValidateTowDistanceForPlusMembershipForMoreThan160KM")
    public void ValidateTowDistanceForPlusMembershipForMoreThan160KM() {
        String account = "Timothy Lee";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(500);
        casePage.NavigateCaseInnerTab("Tow Destination");
        UIActions.Click(casePage.btn_TowDistanceEdit);
        //Step 1
        UIActions.UpdateText(casePage.txt_TowDistanceText, "160");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Estimated Over KM']/parent::div/following-sibling::div/span/slot/records-formula-output/slot/lightning-formatted-number")));
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.EstimateOverKM(), "0.00");
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.ExpectedCostToTowVehicle(), "$0.00");

        //Step 2
        UIActions.Click(casePage.btn_TowDistanceEdit);
        UIActions.UpdateText(casePage.txt_TowDistanceText, "161");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(1000);
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.EstimateOverKM(), "1.00");
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.ExpectedCostToTowVehicle(), "$3.50");
    }

    @Test(description = "Story 476, tst 954 ; ValidateTowDistanceForPremierCostumerPopulatedAsRequested")
    public void ValidateTowDistanceForPremierCostumerPopulatedAsRequested() {
        String usedCase = readData.GetData("premierCase");
        UIActions.Click(mainPage.link_Cases);
        WebFlows.SelectSpecificRecordInListView(usedCase);
        casePage.NavigateCaseInnerTab("Tow Destination");
        UIActions.Click(casePage.btn_TowDistanceEdit);
        //Step 1
        UIActions.UpdateText(casePage.txt_TowDistanceText, "320");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.EstimateOverKM(), "0.00");
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.ExpectedCostToTowVehicle(), "$0.00");

        //Step 2
        UIActions.Click(casePage.btn_TowDistanceEdit);
        UIActions.UpdateText(casePage.txt_TowDistanceText, "321");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.EstimateOverKM(), "1.00");
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.ExpectedCostToTowVehicle(), "$3.50");
    }

    @Test(description = "Story 645, Test 971 ValidateBatteryIssueQuestionProperlyConfigured")
    public void ValidateBatteryIssueQuestionProperlyConfigured() {
        String account = readData.GetData("Account_Kevin");
        String make = "AMERICAN IRONHORSE";
        String[] QAs = {"AAA Battery Purchase", "Member requests AAA Battery warranty", "Left lights on", "Cranks - makes a sound"
                , "Electric vehicle (EV) out of charge", "Member requests tow"};
        String problemType = "Battery Issue";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        UIActions.Click(casePage.btn_problemTypeEdit);
        casePage.SelectProblemType(casePage.dropdown_problemType, problemType);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save after edit");
        casePage.SelectMake(casePage.search_make, make);
        UIActions.ClickAndWait(casePage.btn_SearchVehicle, "search");
        List<WebElement> vehicles = driver.findElements(By.xpath("//strong[text()='Please Select one of the Vehicles']/ancestor::flowruntime-base-section/descendant::table/tbody/tr/td/lightning-primitive-cell-checkbox/span"));
        UIActions.Click(vehicles.get(0));
        UIActions.ClickAndWait(casePage.btn_confirmVehicleSelection, "confirm");
        UIActions.ClickAndWait(casePage.btn_SaveVehicleSelection, "save");
        UIActions.ClickAndWait(casePage.btn_FinishCarSelection, "Finish");

        for (String s : QAs) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + s + "']")));
            Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
        }
        UIActions.SetDelayAfterAction(500);
        UIActions.ScrollTopOfPage(2, 300);
        UIActions.SetDelayAfterAction(500);
        UIActions.ClickElementByXpath("//span[text()='" + QAs[0] + "']/ancestor::label/span[@class='slds-radio_faux']");
        UIActions.SetDelayAfterAction(500);
        UIActions.ClickAndWait(casePage.btn_QA_Next, "QAs Next button");
        UIActions.ClickAndWait(casePage.btn_QA_Finish, "finish");
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='L304 - Member Requests Battery Service']");
    }


    @Test(description = "Story 1136, Test 1254 ValidateTheTriageVehicleTabIncludesAllRequiredComponents")
    public void ValidateTheTriageVehicleTabIncludesAllRequiredComponents() {
        String account = readData.GetData("Account_Kevin");
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='Search Vehicle']")));
        Verifications.VerifyElementPresent(casePage.txt_SearchVehicleComponent);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='Vehicles found in previous cases:']")));
        Verifications.VerifyElementPresent(casePage.txt_PreviousVehiclesComponent);
        Verifications.VerifyElementPresent(casePage.txt_VehicleInformationSection);
        UIActions.ScrollBottomOfPage(1, 1000, true);
//        Verifications.VerifyElementPresent(casePage.txt_RVAdditionalInformationSection);
        Verifications.VerifyElementPresent(casePage.txt_TriageSection);


    }

    @Test(description = "Story 1135,966 Test 930+1288 ValidateResultsOfUniqueVehiclesAreDisplayedI")
    public void ValidateResultsOfUniqueVehiclesAreDisplayedI() {
        String account = readData.GetData("Account_Kevin");
//        String[] fieldValues = {"2022", "KIA", "TELLURIDE", "Passenger Car/Truck", "new", "AWD", "Gas", "White","009", "British Columbia"};
        String[] fieldValues = {"2017", "CHEVROLET", "Silverado", "Passenger Car/Truck", " ", "4WD", "Gas", "Red","908ty", "Alberta"};
        String[] fields = {"Year", "Make", "Model", "Vehicle Type", "Vehicle Description", "Fuel Type", "Driveline", "Color", "License Plate"};
        String[] tableFields = {"Year", "Make", "Model", "Vehicle Description"};


        //Creating a Case with a specific BCAA member
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");

        //Entering vehicle tab and choosing a car and verifying the proper fields are displayed
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
//        for(String s : tableFields){
//            Verifications.VerifyElementIsPresentUsingXpath("//strong[text()='Vehicles found in previous cases:']//following::span[text()='"+s+"'][1]");
//        }

        UIActions.Click(casePage.radbtn_VehicleFromPreviousCases);
        UIActions.ClickAndWait(casePage.btn_confirmVehicleSelectionAndSave, "Save After Vehicle Selection");


        //Verifying if the vehicle details are updated after the vehicle is chosen
        UIActions.ScrollBottomOfPage(1, 1000, true);
        try {
            for (String s : fields) {
                Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
            }
            for (String s : fieldValues) {
                System.out.println(s);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-formatted-text[text()='" + s + "']")));
                Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + s + "']");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            currentTest.fail("Not all fields are displayed");
            Assert.assertFalse(true);
        }


    }


    @Test(description = "Story 646, Test 993 ValidateKeysQuestionProperlyConfigured")
    public void ValidateKeysQuestionProperlyConfigured() {
        String account = readData.GetData("Account_Kevin");
        String makeModel = "AMERICAN IRONHORSE";
//        String[] QAs = {"Stalled while driving", "Engine overheated","Engine won't go into gear", "Engine running poorly","Engine fire"};
        String[] QAs = {"Ignition", "Missing or damaged key", "Other"};
        String problemType = "Keys";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.ClickAndWait(casePage.btn_account_next, "");
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        UIActions.Click(casePage.btn_problemTypeEdit);
        casePage.SelectProblemType(casePage.dropdown_problemType, problemType);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save after edit");

        casePage.SelectMake(casePage.search_make, makeModel);
        UIActions.ClickAndWait(casePage.btn_SearchVehicle, "search");
        List<WebElement> vehicles = driver.findElements(By.xpath("//strong[text()='Please Select one of the Vehicles']/ancestor::flowruntime-base-section/descendant::table/tbody/tr/td/lightning-primitive-cell-checkbox/span"));
        UIActions.Click(vehicles.get(0));
        UIActions.ClickAndWait(casePage.btn_confirmVehicleSelection, "confirm");
        UIActions.ClickAndWait(casePage.btn_SaveVehicleSelection, "save");
        UIActions.ClickAndWait(casePage.btn_FinishCarSelection, "Finish");


        for (String s : QAs) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + s + "']")));

            Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
        }
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.SetDelayAfterAction(2000);
        UIActions.ClickElementByXpath("//span[text()='" + QAs[2] + "']/ancestor::label");
        UIActions.ClickAndWait(casePage.btn_QA_Next, "QAs Next button");
        UIActions.ClickAndWait(casePage.btn_QA_Finish, "finish");
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='L890 - Other Locksmith Problem']");
    }

    @Test(description = "Story 773, Test 1013 ValidateMemberHasTheProperFieldsAndTheyAreEditable")
    public void ValidateMemberHasTheProperFieldsAndTheyAreEditable() {
        UIActions.SetDelayAfterAction(2000);
        String[] values = {"Kevin", "Fowler1", "9119180"};
        String email = "glcttst@bcaa.com";

        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("Non-BCAA Customer");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.casePage.btn_account_next);
//        UIActions.ClickAndWait(casePage.btn_account_next, "");
        UIActions.UpdateText(casePage.txt_NonBCAA_FirstName, values[0]);
        UIActions.UpdateText(casePage.txt_NonBCAA_LastName, values[1]);
        UIActions.UpdateText(casePage.txt_NonBCAA_Email, email);
        UIActions.UpdateText(casePage.txt_NonBCAA_Phone, values[2]);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "4918 Nuzum Road");
        UIActions.SetDelayAfterAction(1000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(1000);
        UIActions.ClickAndWait(casePage.btn_account_Save, "save");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        List<WebElement> temp = new ArrayList<>();
        for (String s : values) {
            temp.add(driver.findElement(By.xpath("//lightning-formatted-text[text()='" + s + "']")));
            System.out.println(temp);
        }
        UIActions.SetDelayAfterAction(2000);
        Verifications.VerifyListOfElements(temp);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//a[text()='" + email + "']")));
        Verifications.VerifyElementIsPresentUsingXpath("//a//div[@class='slds-truncate']");
    }


    @Test(description = "Story 1000, Test 1103+1104 VerifyThatCallTakerCanPopulateMemberForXperigoClub")
    public void VerifyThatCallTakerCanPopulateMemberForXperigoClub() {
        UIActions.SetDelayAfterAction(2000);
        String[] spanFields = {"VIN Number", "First Name", "Last Name", "Xperigo Call Number", "Payment Responsibility Code"};
        String[] labelFields = {"Phone", "Email"};
        String[] legendFields = {"Home Address"};
        String[] spanValues = {"9119180", "Kevin", "Fowler1", "666152", "C (Cash Call)"};
        String[] labelValues = {"kf@gmail.com", "9081155"};
        String legendValues = "4918 Nuzum Road";

        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("Xperigo Member");
        UIActions.Click(casePage.casePage.btn_account_next);

        //Verify All fields exist in the Xperigo Member screen
        List<WebElement> temp = new ArrayList<>();
        for (String s : spanFields) {
            temp.add(driver.findElement(By.xpath("//span[text()='" + s + "']")));
        }
        Verifications.VerifyListOfElements(temp);

        List<WebElement> temp1 = new ArrayList<>();
        for (String s : labelFields) {
            temp1.add(driver.findElement(By.xpath("//label[text()='" + s + "']")));
        }
        Verifications.VerifyListOfElements(temp1);

        List<WebElement> temp2 = new ArrayList<>();
        for (String s : legendFields) {
            temp2.add(driver.findElement(By.xpath("//legend[text()='" + s + "']")));
        }
        Verifications.VerifyListOfElements(temp2);
        //Verify you can add text to the fields, save the record and that fields are populated successfully


        UIActions.Click(casePage.search_Xperigo_ClubMembership);
        UIActions.UpdateText(casePage.search_Xperigo_ClubMembership, "514");
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='(514) - *BELL FLEET ROADSIDE ASSIST']")));
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.txt_Xperigo_VINNumber);
        UIActions.SetDelayAfterAction(2000);
        UIActions.UpdateText(casePage.txt_Xperigo_VINNumber, spanValues[0]);
        UIActions.Click(casePage.txt_Xperigo_FirstName);
        UIActions.UpdateText(casePage.txt_Xperigo_FirstName, spanValues[1]);
        UIActions.Click(casePage.txt_Xperigo_LastName);
        UIActions.UpdateText(casePage.txt_Xperigo_LastName, spanValues[2]);
        UIActions.Click(casePage.txt_Xperigo_Email);
        UIActions.UpdateText(casePage.txt_Xperigo_Email, labelValues[0]);
        UIActions.Click(casePage.txt_Xperigo_Phone);
        UIActions.UpdateText(casePage.txt_Xperigo_Phone, labelValues[1]);
        UIActions.Click(casePage.txt_Xperigo_HomeAddress);
        UIActions.SetDelayAfterAction(2000);
        UIActions.UpdateText(casePage.txt_Xperigo_HomeAddress, legendValues);
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.Click(casePage.txt_Xperigo_XperigoCallNumber);
        UIActions.UpdateText(casePage.txt_Xperigo_XperigoCallNumber, spanValues[3]);
        UIActions.Click(casePage.txt_Xperigo_PaymentResponsibilityCode);
        UIActions.Click(casePage.txt_Xperigo_PaymentResponsibilityCodeCashCall);
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_account_next);
//        WebFlows.RefreshPage();

        //Verification that the acutal fields were populated
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Club Membership']//following::lightning-formatted-text[1]")));

        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Club Membership']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Xperigo Call Number']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='First Name']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Last Name']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Payment Responsibility Code']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='VIN Number']//following::lightning-formatted-text[1]")));
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Phone 1']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//a[text()='" + labelValues[0] + "']")));
        Verifications.VerifyElementIsPresentUsingXpath("//a//div[text()='" + legendValues + "']");


        System.out.println(driver.findElement(By.xpath("//span[text()='Club Membership']//following::lightning-formatted-text[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//span[text()='Xperigo Call Number']//following::lightning-formatted-text[1]")).getText());


    }


    @Test(description = "Story 774, Test 945 VerifyThatCallTakerCanPopulateMemberForAffiliatedMember")
    public void VerifyThatCallTakerCanPopulateMemberForAffilatedMember() {
        UIActions.SetDelayAfterAction(2000);
        String[] spanFields = {"MembershipNumber", "FirstNameAffiliatedMember", "LastNameAffiliatedMember"};
        String addressValues = "4918 Nuzum Road";
        String[] spanValues = {"9119180", "Kevin", "Fowler1", "666152", "Plus"};
        String phoneValue = "9081155";
        String emailValue = "kf@gmail.com";

        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("Affiliated Member");
        UIActions.Click(casePage.casePage.btn_account_next);

        //Verify All fields exist in the Affiliated Member screen
        List<WebElement> temp = new ArrayList<>();
        for (String s : spanFields) {
            temp.add(driver.findElement(By.xpath("//input[@name='screeninput_" + s + "']")));
        }
        Verifications.VerifyListOfElements(temp);
        Verifications.VerifyElementPresent(casePage.txt_AffiliatedEmail);
        Verifications.VerifyElementPresent(casePage.txt_AffiliatedPhone);
        Verifications.VerifyElementPresent(casePage.txt_AffiliatedHomeAddress);
        Verifications.VerifyElementPresent(casePage.dropdown_AffiliatedMembershipLevel);

        //Insert values on required fields
        UIActions.Click(casePage.txt_AffiliatedMembershipNumber);
        UIActions.UpdateText(casePage.txt_AffiliatedMembershipNumber, spanValues[0]);
        UIActions.Click(casePage.txt_AffiliatedFirstName);
        UIActions.UpdateText(casePage.txt_AffiliatedFirstName, spanValues[1]);
        UIActions.Click(casePage.txt_AffiliatedLastName);
        UIActions.UpdateText(casePage.txt_AffiliatedLastName, spanValues[2]);
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save");
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();
//        UIActions.SetDelayAfterAction(3000);
        casePage.NavigateCaseInnerTab("Member");
        //Verify that the fields have the edit icon near them
        UIActions.ScrollBottomOfPage(1, 2000, true);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@title='Edit First Name']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@title='Edit Last Name']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@title='Edit Membership Level']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@title='Edit Email']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@title='Edit Phone 1']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@title='Edit Home Address']")));


        //Insert values on remaining fields on update
        UIActions.Click(driver.findElement(By.xpath("//button[@title='Edit First Name']")));
        UIActions.Click(casePage.txt_AffiliatedFirstNameOnUpdate);
        UIActions.UpdateText(casePage.txt_AffiliatedFirstNameOnUpdate, spanValues[1] + "test");
        UIActions.Click(casePage.txt_AffiliatedLastNameOnUpdate);
        UIActions.UpdateText(casePage.txt_AffiliatedLastNameOnUpdate, spanValues[2] + "test");
        UIActions.Click(casePage.txt_AffiliatedEmailOnUpdate);
        UIActions.UpdateText(casePage.txt_AffiliatedEmailOnUpdate, emailValue);
        UIActions.Click(casePage.txt_AffiliatedPhoneOnUpdate);
        UIActions.UpdateText(casePage.txt_AffiliatedPhoneOnUpdate, phoneValue);
        UIActions.Click(casePage.txt_AffiliatedHomeAddressOnUpdate);
        UIActions.SetDelayAfterAction(2000);
        UIActions.UpdateText(casePage.txt_AffiliatedHomeAddressOnUpdate, addressValues);
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.Click(casePage.dropdown_AffiliatedMembershipLevelOnUpdate);
        UIActions.Click(casePage.dropdown_AffiliatedMembershipLevelPlusOnUpdate);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save");
        UIActions.SetDelayAfterAction(5000);

        //Verification that the actual fields were populated after update
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Membership Number']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='First Name']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Last Name']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Membership Level']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='Phone 1']//following::lightning-formatted-text[1]")));
        Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//a[text()='" + emailValue + "']")));
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-static-map");

    }

    @Test(description = "Story 418, Test 1079 ValidateLatitudeAndLongitudePopulatedAutomaticallyWhenAddressAreFilled")
    public void ValidateLatitudeAndLongitudePopulatedAutomaticallyWhenAddressAreFilled() {
        String[] country = {"United States", "Canada"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        casePage.ClickElementInCountryDropdownByXpathUsingText(casePage.dropdown_country, country[0]);
        UIActions.UpdateText(casePage.txt_Address_Street, "Schuman Road 1006");
        UIActions.UpdateText(casePage.txt_address_city, "New York");
        UIActions.UpdateText(casePage.txt_address_postal, "12474");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save");
        UIActions.SetDelayAfterAction(3000);
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='Breakdown Location']")));
        casePage.NavigateCaseInnerTab("Breakdown Location");
        WebElement s = driver.findElement(By.xpath("//span[text()='Address Geolocation']/parent::div/parent::div/div/span/slot/lightning-formatted-location"));
        Verifications.VerifyTextIsNotNullInElement(s);
    }

    @Test(description = "Story 425, Test 761 ValidateServiceTerritoryIsPopulatedAutomaticallyWhenAddressAreFilled")
    public void ValidateServiceTerritoryIsPopulatedAutomaticallyWhenAddressAreFilled() {
        String address="662 Bay Road Gibsons British Columbia V0N 1V8 Canada";
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.Click(casePage.txt_NonBCAA_HomeAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress,address);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save");
        UIActions.SetDelayAfterAction(3000);
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='Breakdown Location']")));
        casePage.NavigateCaseInnerTab("Breakdown Location");
        WebElement s = driver.findElement(By.xpath("//span[text()='Address Geolocation']/parent::div/parent::div/div/span/slot/lightning-formatted-address/a"));
        Verifications.VerifyTextIsNotNullInElement(s);
    }

    @Test(description = "Story 419, Test 1083 ValidateAddressPopulatedAutomaticallyWhenLatitudeAndLongitudeAreFilled")
    public void ValidateAddressPopulatedAutomaticallyWhenLatitudeAndLongitudeAreFilled() {
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.Click(casePage.txt_latitude);
        UIActions.UpdateText(casePage.txt_latitude, "42.319558");
        UIActions.Click(casePage.txt_longitude);
        UIActions.UpdateText(casePage.txt_longitude, "-74.515053");
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save");
        UIActions.SetDelayAfterAction(3000);
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(5000);
        casePage.NavigateCaseInnerTab("Breakdown Location");
//        try {
            List<WebElement> e = driver.findElements(By.xpath("//span[text()='Breakdown Location Address']/parent::div/parent::div/div/span/slot/lightning-formatted-address/a"));
            System.out.println(e.size());
            if(e.size()>0){

                currentTest.pass("Address is updated");
                Assert.assertTrue(true);

            }
            else {
                currentTest.fail("Address is not populated");
                Assert.assertFalse(true);

            }
//            Verifications.VerifyListOfElements(e);
        }
//        catch (NoSuchElementException e){
//            currentTest.fail("Couldn't find address");
//        }
//    }

    @Test(description = "Story 432,1136 Test 1061, 1065, 1255 ValidateVehicleCanBeSearchedAndProperlyFilledVa")
    //Vefiriy that you can search by vehicle year, and after the vehicle is chosen you can see the vehicle details on the additional information section of the vehicle/Triage tab
    public void ValidateVehicleCanBeSearchedByYear() {
        String[] tableparameters = {"Year", "Make", "Model", "Driveline", "Fuel Type", "Vehicle Type"};
        String[] additionalinformation = {"License Plate Number", "Color", "Province"};
        String year = "2007";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        casePage.SearchCarYear(year);
        UIActions.ClickAndWait(casePage.btn_SearchVehicle, "search");
        for (String s : tableparameters) {
            Verifications.VerifyElementIsPresentUsingXpath("//span[@title='" + s + "']");
        }
        List<WebElement> elements = driver.findElements(By.xpath("//th[@data-col-key-value='RA_Year__c-text-1']//descendant::lightning-base-formatted-text"));
        for (int i = 0; i < elements.size(); i++) {
            Verifications.VerifyTextInElement(elements.get(i), year);
        }
        List<WebElement> vehicles = driver.findElements(By.xpath("//strong[text()='Please Select one of the Vehicles']/ancestor::flowruntime-base-section/descendant::table/tbody/tr/td/lightning-primitive-cell-checkbox/span"));
        UIActions.Click(vehicles.get(0));
        UIActions.Click(casePage.btn_confirmVehicleSelection);
        for (String s : additionalinformation) {
            Verifications.VerifyElementIsPresentUsingXpath("//span[text()='" + s + "']");
        }
        UIActions.UpdateText(casePage.txt_LicensePlateNumber, "104test");
        casePage.ClickElementInVehicleAdditionalInformationByXpathUsingText("Color", "Pink");
        casePage.ClickElementInVehicleAdditionalInformationByXpathUsingText("Province", "Ontario");

        UIActions.Click(casePage.btn_SaveVehicleSelection);
        UIActions.ClickAndWait(casePage.btn_FinishCarSelection, "Finish");
        WebFlows.RefreshPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='Vehicle \\ Triage']")));
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + year + "']");
        for (String s : tableparameters) {
            Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='" + s + "']//following::lightning-formatted-text[1]")));
            System.out.println(driver.findElement(By.xpath("//span[text()='" + s + "']//following::lightning-formatted-text[1]")).getText());
        }
        for (String s : additionalinformation) {
            if (!s.equals("License Plate Number")) {
                Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='" + s + "']//following::lightning-formatted-text[1]")));
                System.out.println(driver.findElement(By.xpath("//span[text()='" + s + "']//following::lightning-formatted-text[1]")).getText());
            } else {
                Verifications.VerifyTextIsNotNullInElement(driver.findElement(By.xpath("//span[text()='License Plate']//following::lightning-formatted-text[1]")));
                System.out.println(driver.findElement(By.xpath("//span[text()='License Plate']//following::lightning-formatted-text[1]")).getText());


            }
        }
    }


    @Test(description = "Story 990, Test 1112 ; ValidateCallTakerSeeCorrectCaseStatus")
    public void ValidateCallTakerSeeCorrectCaseStatus() {
        String[] caseStatus = {"Open", "Submitted"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        for (String s : caseStatus) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + s + "']")));
            Verifications.VerifyElementIsPresentUsingXpath("//a[@title='" + s + "']");
        }
        UIActions.Click(casePage.btn_CaseStatusClosed);
        UIActions.Click(casePage.btn_CaseStatusMarkStatusAsClosed);
        UIActions.Click(casePage.dropdown_SelectAClosedSStage);
        Verifications.VerifyElementPresent(casePage.dropdown_SelectCancelledStatus);
        Verifications.VerifyElementPresent(casePage.dropdown_SelectCompleteStatus);
    }

    @Test(description = "Story 988, Test 1228 ; ValidateCallTakerSeeCorrectFieldsOnCompactLayout")
    public void ValidateCallTakerSeeCorrectFieldsOnCompactLayout() {

        //Verify if "Case Number", "Name", "Membership Level", "Membership Status","RAE Priority","Flag","Cash Call" appear on the compact layout
        String[] caseStatus = {"Name", "Membership Level", "Membership Status", "RAE Priority", "Flag", "Cash Call"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        UIActions.SetDelayAfterAction(5000);
        Verifications.VerifyElementIsPresentUsingXpath("//slot[@name='primaryField']//lightning-formatted-text");
        for (String s : caseStatus) {

            Verifications.VerifyElementIsPresentUsingXpath("//p[@title='" + s + "']");

        }
    }

    @Test(description = "Story 991, Test 1205 ; ValidateCallTakeCantCancelCaseWithoutCancellationReason")
    public void ValidateCallTakeCantCancelCaseWithoutCancellationReason() {
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();

        //Verify an error is presented when trying to cancel without providing cancellation Reason
        UIActions.Click(casePage.btn_CaseStatusClosed);
        UIActions.Click(casePage.btn_CaseStatusMarkStatusAsClosed);
        UIActions.Click(casePage.dropdown_SelectAClosedSStage);
        UIActions.Click(casePage.dropdown_SelectCancelledStatus);
        UIActions.Click(casePage.btn_CaseStatusSave);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='You encountered some errors when trying to save this record']")));
        Verifications.VerifyElementPresent(CasePage.casePage.warning_SubmittedValidationRuleError);

        //Verify that case can be cancelled if cancellation reason is filled
        casePage.NavigateCaseInnerTab("Service");
        UIActions.SetDelayAfterAction(1000);
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_CancellationReasonEdit);
        UIActions.Click(casePage.btn_CancellationReasonPicklist);
        UIActions.Click(casePage.btn_CancellationReasonPicklistTBD);
        UIActions.ScrollTopOfPage(1, 1000);
        casePage.SaveNewCase();
//        UIActions.SetDelayAfterAction(1000);
        UIActions.Click(casePage.btn_CaseStatusClosed);
        UIActions.Click(casePage.btn_CaseStatusMarkStatusAsClosed);
        UIActions.Click(casePage.dropdown_SelectAClosedSStage);
        UIActions.Click(casePage.dropdown_SelectCancelledStatus);
        UIActions.Click(casePage.btn_CaseStatusSave);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Status changed successfully.']")));
        Verifications.VerifyElementPresent(CasePage.casePage.success_SubmittedSuccessMessage);
    }


    @Test(description = "Story 523, Test 943 ; ValidateCallTakeCanAddExpectedCashAmount")
    public void ValidateCallTakeCanAddExpectedCashAmount() {
        String insertedCashAmount= "10.00";
        String expectedCashAmount= "$10.00";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();

        //Verify CT can add Expected cash amount
        casePage.NavigateCaseInnerTab("Service");
        UIActions.SetDelayAfterAction(1000);
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_EditExpectedCashAmount);
        UIActions.UpdateText(casePage.txt_ExpectedCaseAmount,insertedCashAmount);
        casePage.SaveNewCase();
        UIActions.SetDelayAfterAction(1000);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Expected Cash Amount']//following::lightning-formatted-text[1]")).getText(),expectedCashAmount);
    }


    @Test(description = "Story 561, Test 1180+1177+1178+1179 ; ValidateCallThatFieldsAreAutomaticallyPopulatedOnServiceTabWhenChoosingAFacility")
    public void ValidateCallThatFieldsAreAutomaticallyPopulatedOnServiceTabWhenChoosingAFacility() {
        String facility = "ARROWSMITH AUTOMOTIVE";
        String ManualSpotReason = "FM Facility Mismarked";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();

        //Verify when facility is filled the manually spotted checkbox and manual spot reason are filled as expected and also that code and address are populated
        casePage.NavigateCaseInnerTab("Service");
        UIActions.SetDelayAfterAction(1000);
        UIActions.Click(casePage.btn_FacilityEdit);
        UIActions.Click(casePage.search_FacilityList);
        UIActions.UpdateText(casePage.search_FacilityList, facility);
        UIActions.Click(casePage.search_FacilityListRunSearch);
        UIActions.Click(driver.findElement(By.xpath("//a[@title='" + facility + "']")));
        casePage.SaveNewCase();
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.txt_FacilityLManualSpotReasonText.getText(), ManualSpotReason);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='Manually Spotted']//following::lightning-input[@checked]")));
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(), "461");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(), "20 HILLIERS ROAD QUALICUM BEACH BC CA");


        //Verify when facility is removed the manually spotted checkbox and manual spot reason are unchecked/become blank
        UIActions.Click(casePage.btn_FacilityEdit);
        UIActions.Click(driver.findElement(By.xpath("//label[text()='Facility']//following::button[@title='Clear Selection']")));
        casePage.SaveNewCase();
        Verifications.VerifyElementIsNotPresentUsingAXpath("//span[text()='Manually Spotted']//following::lightning-input[@checked]");
        Verifications.VerifyElementIsNotPresent(casePage.txt_FacilityLManualSpotReasonText, "Manual Spot Reason");
    }

    //    @Test(description = "Story 561, Test 1178+1179 ; ValidateCallThatFieldsAreAutomaticallyPopulatedOnServiceTabWhenChoosingAFacility")
//    public void ValidateCallTakerCanSearchFacilityByCodeOrNameOrCity() {
//        String facility = "ARROWSMITH AUTOMOTIVE";
//        String ManualSpotReason = "FM Facility Mismarked";
//        UIActions.SearchInAppLauncher( "Cases");
//        casePage.OpenNewRoadAssistBCAACaseWindow();
//        UIActions.Click(casePage.btn_Next);
//        casePage.SaveNewCase();
//
//        //Verify when facility is filled the manually spotted checkbox and manual spot reason are filled as expected
//        casePage.NavigateCaseInnerTab("Service");
//        UIActions.SetDelayAfterAction(1000);
//        UIActions.Click(casePage.btn_FacilityEdit);
//        UIActions.Click(casePage.search_FacilityList);
//        UIActions.UpdateText(casePage.search_FacilityList,facility);
//        UIActions.Click(casePage.search_FacilityListRunSearch);
//        UIActions.Click(driver.findElement(By.xpath("//a[@title='"+facility+"']")));
//        casePage.SaveNewCase();
//        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.txt_FacilityLManualSpotReasonText.getText(),ManualSpotReason);
//        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='Manually Spotted']//following::lightning-input[@checked]")));
//    }
    @Test(description = "Story 1071,991, 1136 Test 1161,1204,1163, 1256 ; ValidateCallTakerCanChangeToStatusSubmittedFromOpen")
    public void ValidateCallTakerCanChangeToStatusSubmittedFromOpen() {
        String account = readData.GetData("Account_Kevin");
        String[] country = {"United States", "Canada"};
        String make = "AMERICAN IRONHORSE";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();

        //Verify the validation rule error is displayed when trying to change the case to status submitted without providing the required fields
        UIActions.Click(casePage.btn_CaseStatusSubmitted);
        UIActions.Click(casePage.btn_CaseStatusMarkAsCurrentStatus);
        Verifications.VerifyElementPresent(CasePage.casePage.warning_SubmittedValidationRuleError);

        //Verifying that after providing the required fields the case is updated to status submitted

        //Pacesetter Code
        casePage.NavigateCaseInnerTab("Service");
        UIActions.SetDelayAfterAction(1000);
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklistOnUpdate);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L301 - No Crank - Jump Start']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.ScrollTopOfPage(1, 1000);

        //First and Last Name
        casePage.NavigateCaseInnerTab("Member");
        casePage.SelectClubType("BCAA Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");

        //Breadkown location
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.Click(casePage.txt_NonBCAA_HomeAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "4918 Nuzum Road");
        UIActions.SetDelayAfterAction(1000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(1000);
        UIActions.ClickAndWait(casePage.btn_account_Save, "save");
//        casePage.ClickElementInCountryDropdownByXpathUsingText(casePage.dropdown_country,country[0]);
//        UIActions.UpdateText(casePage.txt_Address_Street, "Schuman Road 1006");
//        UIActions.UpdateText(casePage.txt_address_city, "New York");
//        UIActions.UpdateText(casePage.txt_address_postal, "12474");
//        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save");

        //Vehicle details -search by make
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        casePage.SelectMake(casePage.search_make, make);
        UIActions.ClickAndWait(casePage.btn_SearchVehicle, "search");
        List<WebElement> vehicles = driver.findElements(By.xpath("//strong[text()='Please Select one of the Vehicles']/ancestor::flowruntime-base-section/descendant::table/tbody/tr/td/lightning-primitive-cell-checkbox/span"));
        UIActions.Click(vehicles.get(0));
        UIActions.ClickAndWait(casePage.btn_confirmVehicleSelection, "confirm");
        UIActions.ClickAndWait(casePage.btn_SaveVehicleSelection, "save");
        UIActions.ClickAndWait(casePage.btn_FinishCarSelection, "Finish");
        UIActions.ScrollTopOfPage(1, 1000);

        //Trying to submit the case again
        UIActions.ScrollTopOfPage(1, 1000);
        UIActions.Click(casePage.btn_CaseStatusSubmitted);
        UIActions.Click(casePage.btn_CaseStatusMarkAsCurrentStatus);
        UIActions.SetDelayAfterAction(2000);
        Verifications.VerifyElementPresent(CasePage.casePage.success_SubmittedSuccessMessage);

        //Verify you can't change status to open after the status was changed to submitted
        UIActions.Click(casePage.btn_CaseStatusOpen);
        UIActions.Click(casePage.btn_CaseStatusMarkAsCurrentStatus);
        Verifications.VerifyElementPresent(CasePage.casePage.warning_SubmittedValidationRuleError);
    }


    @Test(description = "Story 712, Test 1132 ; ValidateCallTakerCanProvideAdditionInformationOnDriverToCollect")
    public void ValidateCallTakerCanProvideAdditionInformationOnDriverToCollect() {
        String s = RandomStringUtils.randomAlphabetic(3);
        String driverToCollect = "Sample" + " " + s;
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_DriverToCollectEditButton);
        UIActions.Click(casePage.txt_DriverToCollectTextArea);
        UIActions.UpdateText(casePage.txt_DriverToCollectTextArea, driverToCollect);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        System.out.println(casePage.txt_DriverToCollectTextAreaAfterUpdate.getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.RA_DrivertoCollectDetails__c']//lightning-formatted-text")));
        Verifications.VerifyElementTextIsEqualToExpectedText(casePage.txt_DriverToCollectTextAreaAfterUpdate.getText(), driverToCollect);
    }


    @Test(description = "Story 1136, Test = 1257,1258 ; VerifyCallTakeCanSearchByMageOrByModelYearAndMakeTogether")
    public void VerifyCallTakeCanSearchByMageOrByModelYearAndMakeTogether() {
        String make = "AMERICAN IRONHORSE";
        String model = "BANDIT";
        String year = "2000";
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");

        //Verify you can search by make only
        casePage.SelectMake(casePage.search_make, make);
        UIActions.ClickAndWait(casePage.btn_SearchVehicle, "search");
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + make + "']")));
        WebFlows.RefreshPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='Vehicle \\ Triage']")));
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");


        //Verify You can search by Year
        casePage.SearchCarYear(year);
        casePage.SelectMake(casePage.search_make, make);
        casePage.SelectModel(casePage.search_searchCarModels, model);
        UIActions.ClickAndWait(casePage.btn_SearchVehicle, "search");
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-formatted-text[text()='" + year + "']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + make + "']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + model + "']")));


    }


    @Test(description = "Story 1136,966 Test = 1259 + 1288 ; VerifyCallTakeCanManuallyAddVehicleDetails")
    public void VerifyCallTakeCanManuallyAddVehicleDetails() {
        String[] fieldValues = {"RV 24-32 feet", "Flat Tire", "L301 - No Crank - Jump Start", "test vehicle", "2023", "Ford", "Expedition", "4WD", "Gas", "1244", "White", "FLORIDA", "Front"};
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_EditVehicleType);


        //Manually populate all vehicle fields and save
        UIActions.Click(casePage.dropdown_VehicleType);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[0] + "']")));
        UIActions.Click(casePage.dropdown_ProblemType);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[1] + "']")));
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + fieldValues[2] + "']")));
        UIActions.UpdateText(casePage.txt_VehicleDescriptionTextBox, fieldValues[3]);
        UIActions.Click(casePage.dropdown_YearVehicleInformationSection);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[4] + "']")));
        UIActions.UpdateText(casePage.txt_VehicleMakeTextBox, fieldValues[5]);
        UIActions.UpdateText(casePage.txt_VehicleModelTextBox, fieldValues[6]);
        UIActions.Click(casePage.dropdown_Driveline);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[7] + "']")));
        UIActions.Click(casePage.dropdown_FuelType);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[8] + "']")));
        UIActions.UpdateText(casePage.txt_LicensePlatTextBox, fieldValues[9]);
        UIActions.Click(casePage.dropdown_Color);
        UIActions.Click(casePage.dropdown_Color);
        UIActions.SetDelayAfterAction(500);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[10] + "']")));
        UIActions.Click(casePage.dropdown_Province);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + fieldValues[11] + "']")));
        UIActions.Click(casePage.multipicklist_FlatTireFront);
        UIActions.Click(casePage.multipicklist_MoveToChosen);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");


        //Making sure the fields are properly populated
        UIActions.SetDelayAfterAction(2000);
        for (String s : fieldValues) {
            Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + s + "']");
        }
    }


    @Test(description = "Story 1137,433 Test = 692+698 + 1288 ; VerifyCallTakeCanChooseTrailerVehicleTypeAndVlidateAdditionalFields")
    public void VerifyCallTakeCanChooseTrailerVehicleTypeAndVlidateAdditionalFields() {
        String[] trailerType = new String[]{"1 \"5TH WHEEL\"", "2 \"HORSE\"", "3 \"UTILITY\"", "4 \"TRAVEL\"", "5 \"BOAT\""};
        String[] trailerLength = {"1 > 32 ft", "2 26 - 32 ft", "3 <26 ft"};
        String[] trailerPlugType = {"1 \"3 Wire Flat\"", "2 \"4 Wire Flat\"", "3 \"4 Wire Round\"", "4 \"6 Wire Round\"", "5 \"7 Wire Round\""};
        String[] trailerHitchType = {"10 \"Standard Ball Hitch\"", "11 \"5th Wheel\"", "12 \"Gooseneck\""};
        String[] trailerHitchSize = {"1 \"1.5\"\"", "2 \"1.75\"\"", "3 \"2.0\"\"", "4 \"2.375\"\"", "5 \"3.0\"\"", "6 \"5th Wheel\"", "OT \"Other\""};
        String trailerVehicleType = "Trailer";
        String trailerAxles = "2";
        String trailerCondition = "Mint";
        String trailerLoadType = "Cargo";
        String trailerLoadWeight = "2000.00";
        List<String> textFieldsPopulated = new ArrayList<>();
        List<String> numberFieldsPopulated = new ArrayList<>();


        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
        UIActions.ScrollBottomOfPage(1, 1000, true);
        UIActions.Click(casePage.btn_EditVehicleType);


        //Manually choose Trailer Type
        UIActions.Click(casePage.dropdown_VehicleType);
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + trailerVehicleType + "']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);

        //ValidateTrailerRelatedPicklistValues
        UIActions.Click(casePage.btn_EditTrailerType);
        UIActions.Click(casePage.dropdown_TrailerType);

        for (String s : trailerType) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//button[@aria-label='Trailer Type, --None--']//following::span[@title='" + s + "']")));
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + trailerType[1] + "']")));
        textFieldsPopulated.add(trailerType[1]);

        UIActions.Click(casePage.dropdown_TrailerLength);
        for (String s : trailerLength) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + s + "']")));
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + trailerLength[1] + "']")));
        textFieldsPopulated.add(trailerLength[1]);


        UIActions.Click(casePage.dropdown_TrailerPlugType);
        for (String s : trailerPlugType) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + s + "']")));
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + trailerPlugType[1] + "']")));
        textFieldsPopulated.add(trailerPlugType[1]);


        UIActions.Click(casePage.dropdown_TrailerHitchType);
        for (String s : trailerHitchType) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + s + "']")));
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + trailerHitchType[1] + "']")));
        textFieldsPopulated.add(trailerHitchType[1]);


        UIActions.Click(casePage.dropdown_TrailerHitchSize);
        for (String s : trailerHitchSize) {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + s + "']")));
            ;
        }
        UIActions.Click(driver.findElement(By.xpath("//span[text()='" + trailerHitchSize[1] + "']")));
        textFieldsPopulated.add(trailerHitchSize[1]);


        UIActions.Click(casePage.txt_NoAxlesTextBox);
        UIActions.UpdateText(casePage.txt_NoAxlesTextBox, trailerAxles);
        numberFieldsPopulated.add(trailerAxles);

        UIActions.Click(casePage.txt_ConditionTextBox);
        UIActions.UpdateText(casePage.txt_ConditionTextBox, trailerCondition);
        textFieldsPopulated.add(trailerCondition);


        UIActions.Click(casePage.txt_LoadTypeTextBox);
        UIActions.UpdateText(casePage.txt_LoadTypeTextBox, trailerLoadType);
        textFieldsPopulated.add(trailerLoadType);

        UIActions.Click(casePage.txt_LoadWeightTextBox);
        UIActions.UpdateText(casePage.txt_LoadWeightTextBox, trailerLoadWeight);
        numberFieldsPopulated.add(trailerLoadWeight);

        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");

        //Making sure the fields are properly populated
        UIActions.SetDelayAfterAction(2000);
        for (String s : textFieldsPopulated) {
            Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" +s+ "']");

            }
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-number[text()='2']");
        Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-number[text()='2,000.00']");
    }




    @Test(description = "Story 1137,433 Test = 691 + 1288 ; VerifyCallTakeCanChooseRVVehicleTypeAndVlidateAdditionalFields")
    public void VerifyCallTakeCanChooseRVVehicleTypeAndVlidateAdditionalFields() {
        String[] RVEngineLocation = new String[]{"F Front", "R Rear"};
        String[] RVClass = {"A \"Class A\"", "B \"Class B\"", "C \"Class C\""};
        String[] RVVehicleType = {  "RV < 23 feet", "RV 24-32 feet", "RV > 33 feet"};
        String RVLength = "100";
        String RVHeight = "200";
        String RVWeight = "300";
        List<String> textfieldsPopulated = new ArrayList<>();

        for (String s: RVVehicleType) {

            UIActions.SearchInAppLauncher("Cases");
            casePage.OpenNewRoadAssistBCAACaseWindow();
            UIActions.Click(casePage.btn_Next);
            casePage.SaveNewCase();
            UIActions.SetDelayAfterAction(3000);
            casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
            UIActions.ScrollBottomOfPage(1, 1000, true);
            UIActions.Click(casePage.btn_EditVehicleType);
            UIActions.Click(casePage.dropdown_VehicleType);
            UIActions.Click(driver.findElement(By.xpath("//span[text()='"+s+"']")));

            //Manually choose RV Type
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
            UIActions.SetDelayAfterAction(2000);

            //Verify the fields and the picklist values
            UIActions.Click(casePage.btn_EditRVClass);
            UIActions.Click(casePage.dropdown_RVClass);
            for (String rvclass : RVClass) {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + rvclass + "']")));
            }
            UIActions.Click(driver.findElement(By.xpath("//span[text()='" + RVClass[1] + "']")));
            textfieldsPopulated.add(RVClass[1]);
            UIActions.Click(casePage.dropdown_RVEngineLocation);
            for (String rvenginelocation : RVEngineLocation) {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + rvenginelocation + "']")));
            }
            UIActions.Click(driver.findElement(By.xpath("//span[text()='" + RVEngineLocation[1] + "']")));
            textfieldsPopulated.add(RVEngineLocation[1]);
            UIActions.Click(casePage.txt_RVLengthTextBox);
            UIActions.UpdateText(casePage.txt_RVLengthTextBox, RVLength);
            UIActions.Click(casePage.txt_RVHeightTextBox);
            UIActions.UpdateText(casePage.txt_RVHeightTextBox, RVHeight);
            UIActions.Click(casePage.txt_RVWeightTextBox);
            UIActions.UpdateText(casePage.txt_RVWeightTextBox, RVWeight);
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");

            //Making sure the fields are properly populated
            UIActions.SetDelayAfterAction(2000);
            for (String textfield : textfieldsPopulated) {
                Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + textfield + "']");
            }
            Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-number[text()='100.00']");
            Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-number[text()='200.00']");
            Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-number[text()='300.00']");
//            WebFlows.RefreshPage();
            WebFlows.CloseOpenedTab();
        }



    }
    @Test(description = "Story 1137,433 Test = 690 + 1288 ; VerifyCallTakeCanChooseRVVehicleTypeAndVlidateAdditionalFields")
    public void VerifyCallTakeCanChooseMotorcycleVehicleTypeAndVlidateAdditionalFields() {
        String[] MotorcycleType = new String[]{"3W \"3-Wheeled\"","CB \"Custom Bike\"","CH \"Chopper\"","CR \"Cruiser\"", "SB \"Sports Bike\"","SC \"Scooter\"", "ST \"Standard\""};
        String[] MotorcycleEngineType = {"1 \"<500cc\"","2 \"501cc-1000cc\"", "3 \">1000cc\"", "4 \"0cu in-88cu in\"", "5 \"88 cu in\"","6 \">88cu in\""};
        String[] RVVehicleType = {  "2-Wheeled Motorcycle", "3-Wheeled Motorcycle"};
        List<String> textfieldsPopulated = new ArrayList<>();
        String comment = "Sample";
        for (String s: RVVehicleType) {

            UIActions.SearchInAppLauncher("Cases");
            casePage.OpenNewRoadAssistBCAACaseWindow();
            UIActions.Click(casePage.btn_Next);
            casePage.SaveNewCase();
            UIActions.SetDelayAfterAction(3000);
            casePage.NavigateCaseInnerTab("Vehicle \\ Triage");
            UIActions.ScrollBottomOfPage(1, 1000, true);
            UIActions.Click(casePage.btn_EditVehicleType);
            UIActions.Click(casePage.dropdown_VehicleType);
            UIActions.Click(driver.findElement(By.xpath("//span[text()='"+s+"']")));

            //Manually choose Motorcycle Type
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
            UIActions.SetDelayAfterAction(2000);

            //Verify the fields and the picklist values and choosing random values from the list
            UIActions.Click(casePage.btn_EditMotorcycleType);
            UIActions.Click(casePage.dropdown_MotorcycleType);
            for (String motorcycletype : MotorcycleType) {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + motorcycletype + "']")));
            }
            UIActions.Click(driver.findElement(By.xpath("//span[text()='" + MotorcycleType[1] + "']")));
            textfieldsPopulated.add(MotorcycleType[1]);
            UIActions.Click(casePage.dropdown_MotorcycleEngineType);
            for (String motorcycleenginetype : MotorcycleEngineType) {
                Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + motorcycleenginetype + "']")));
            }
            UIActions.Click(driver.findElement(By.xpath("//span[text()='" + MotorcycleEngineType[1] + "']")));
            textfieldsPopulated.add(MotorcycleEngineType[1]);

            UIActions.Click(casePage.txt_MotorcycleComment);
            UIActions.UpdateText(casePage.txt_MotorcycleComment, comment);
            textfieldsPopulated.add(comment);
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");

            //Making sure the fields are properly populated
            UIActions.SetDelayAfterAction(2000);
            for (String textfilled : textfieldsPopulated) {
                Verifications.VerifyElementIsPresentUsingXpath("//lightning-formatted-text[text()='" + textfilled + "']");
            }
            //            WebFlows.RefreshPage();
            WebFlows.CloseOpenedTab();

        }

    }



    @Test(description = "Story 408, Test 1152+1153+1154+1155 ; ValidateCallTakerCanSearchMemberBaseForCAAOrAAA")
    public void ValidateCallTakerCanSearchMemberBaseForCAAOrAAA() {
        String firstName = "ANNE";
        String firstNamephone = "JONATHAN";
        String lastName = "HAYDEN";
        String lastNamephone = "JAMES";
        String membershipNumber = "6202720637550027";
        String phoneNumber = "614-389-7981";
        String clubMembership = "(272) - Alberta Motor Assoc - 7804307700";
        String clubMembershipPhone = "(130) - AAA Ohio Auto Club - 6144313388";


        UIActions.SearchInAppLauncher( "Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("CAA/AAA Member");
        UIActions.Click(casePage.btn_account_next);



        //Verify CAA/AAA Member can be searched using Club Membership and First Name+Last Name
        UIActions.Click(casePage.search_ClubMembershipCAAOrAAA);
        UIActions.UpdateText(casePage.search_ClubMembershipCAAOrAAA,clubMembership);
        UIActions.Click(casePage.txt_FirstNameCAAOrAAA);
        UIActions.UpdateText(casePage.txt_FirstNameCAAOrAAA,firstName);
        UIActions.Click(casePage.txt_LastNameCAAOrAAA);
        UIActions.UpdateText(casePage.txt_LastNameCAAOrAAA,lastName);
        UIActions.Click(casePage.btn_SearchBtnCAAOrAAA);
        UIActions.SetDelayAfterAction(7000);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-base-formatted-text[text()='ANNE HAYDEN']")));
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-formatted-text[text()='ANNE HAYDEN']")));
        }
        catch (Exception e){
//            fail();
            Assert.assertFalse(true);
        }
        UIActions.Click(casePage.btn_PreviousBtnCAAOrAAA);




        //Verify CAA/AAA Member can be searched using Club Membership and MembershipNumber
        UIActions.Click(casePage.txt_FirstNameCAAOrAAA);
        casePage.txt_FirstNameCAAOrAAA.clear();
        UIActions.Click(casePage.txt_LastNameCAAOrAAA);
        casePage.txt_LastNameCAAOrAAA.clear();
        UIActions.Click(casePage.txt_MembershipNumberCAAOrAAA);
        UIActions.UpdateText(casePage.txt_MembershipNumberCAAOrAAA,membershipNumber);
        UIActions.Click(casePage.btn_SearchBtnCAAOrAAA);
        UIActions.SetDelayAfterAction(7000);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-formatted-text[text()='ANNE HAYDEN']")));
        UIActions.Click(casePage.btn_PreviousBtnCAAOrAAA);


        //Verify CAA/AAA Member can be searched using Club Membership and Phone
        UIActions.Click(casePage.search_ClubMembershipCAAOrAAA);
        UIActions.Click(casePage.btn_ClubMembershipCAAOrAAAClear);
        UIActions.Click(casePage.search_ClubMembershipCAAOrAAA);
        UIActions.UpdateText(casePage.search_ClubMembershipCAAOrAAA,clubMembershipPhone);
        UIActions.Click(casePage.txt_MembershipNumberCAAOrAAA);
        casePage.txt_MembershipNumberCAAOrAAA.clear();
        UIActions.Click(casePage.txt_PhoneNumberCAAOrAAA);
        UIActions.UpdateText(casePage.txt_PhoneNumberCAAOrAAA,phoneNumber);
        UIActions.Click(casePage.btn_SearchBtnCAAOrAAA);
        UIActions.SetDelayAfterAction(3000);
        try {
            Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-formatted-text[text()='JONATHAN JAMES']")));
        }
        catch (Exception e){
            Assert.assertFalse(true);
        }
        UIActions.Click(casePage.btn_PreviousBtnCAAOrAAA);



        //Verify CAA/AAA Member can be searched using Club Membership and Phone + First Name+Last Name
        UIActions.Click(casePage.txt_FirstNameCAAOrAAA);
        UIActions.UpdateText(casePage.txt_FirstNameCAAOrAAA,firstNamephone);
        UIActions.Click(casePage.txt_LastNameCAAOrAAA);
        UIActions.UpdateText(casePage.txt_LastNameCAAOrAAA,lastNamephone);
        UIActions.Click(casePage.btn_SearchBtnCAAOrAAA);
        UIActions.SetDelayAfterAction(3000);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-formatted-text[text()='JONATHAN JAMES']")));
        UIActions.Click(casePage.btn_PreviousBtnCAAOrAAA);

    }



    @Test(description = "Story 1059, Test 1231 ; ValidateCallTakerCanSetCashCallForCAAOrAAAMember")
    public void ValidateCallTakerCanSetCashCallForCAAOrAAAMember() {
        String membershipNumber = "6202722772449007";
        String clubMembership = "(272) - Alberta Motor Assoc - 7804307700";



        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("CAA/AAA Member");
        UIActions.Click(casePage.btn_account_next);


        //Choosing a specific CAA/AAA member
        UIActions.Click(casePage.search_ClubMembershipCAAOrAAA);
        UIActions.UpdateText(casePage.search_ClubMembershipCAAOrAAA, clubMembership);
        UIActions.Click(casePage.txt_MembershipNumberCAAOrAAA);
        UIActions.UpdateText(casePage.txt_MembershipNumberCAAOrAAA,membershipNumber);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SearchBtnCAAOrAAA);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-base-formatted-text[text()='LINDSEY HAYDEN']")));
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//lightning-base-formatted-text[text()='LINDSEY HAYDEN']")));
        UIActions.Click(casePage.btn_ResultRadioBtnCAAOrAAA);
        UIActions.SetDelayAfterAction(2000);
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_account_Save);
        UIActions.SetDelayAfterAction(2000);


        //Set Cash Call and check status
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_EditCashCall);
        UIActions.Click(casePage.btn_EditCashCallOverEntitlement);
        UIActions.Click(casePage.btn_EditCashCallMoveToChosenMultiPicklist);
        UIActions.Click(casePage.btn_account_Save);
        UIActions.SetDelayAfterAction(2000);
        UIActions.ScrollTopOfPage(1,1000);
        Verifications.VerifyElementPresent(casePage.img_CashCallImage);

    }

    @Test(description = "Story 408, Test 1421 ; ValidateCallTakerReceivesAnErrorWhenCAAOrAAAMemberDoesNotExist")
    public void ValidateCallTakerReceivesAnErrorWhenCAAOrAAAMemberDoesNotExist() {
        String randomLastName = "random";
        String clubMembership = "(272) - Alberta Motor Assoc - 7804307700";

        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.SelectClubType("CAA/AAA Member");
        UIActions.Click(casePage.btn_account_next);


        //Searching for a  CAA/AAA member with Member last name that doesn't exist
        UIActions.Click(casePage.search_ClubMembershipCAAOrAAA);
        UIActions.UpdateText(casePage.search_ClubMembershipCAAOrAAA, clubMembership);
        UIActions.Click(casePage.txt_LastNameCAAOrAAA);
        UIActions.UpdateText(casePage.txt_LastNameCAAOrAAA,randomLastName);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SearchBtnCAAOrAAA);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='No matching member information found.']")));
            Verifications.VerifyElementPresent(casePage.warning_CAAOrAAANoMemberFoundError);
        }
        catch (Exception e){
            Assert.assertFalse(true);
        }
    }


    @Test(description = "Story 1059, Test 1230 ; ValidateThatCashCallIconAppearsForBCCMemberWithCallsAllowed")
    public void ValidateThatCashCallIconAppearsForBCCMemberWithCallsAllowed() {
        String accountnotallowed = readData.GetData("Account_Kevin");
        String accountallowed = "Timothy Lee";

//      Choose BCAA Member with calls allowed 0 and check if cash call image is displayed
        UIActions.SearchInAppLauncher( "Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.SetDelayAfterAction(1000);
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, accountnotallowed);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.ScrollTopOfPage(1,1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Cash Call']")));
        Verifications.VerifyElementPresent(casePage.img_CashCallImage);

        //Choose BCAA Member with calls allowed >0 and check that the cash call image is not displayed
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, accountallowed);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.ScrollTopOfPage(1,1000);
        Verifications.VerifyElementIsNotPresentUsingAXpath("//img[@alt='Cash Call']");

    }

    @Test(description = "Story 968, Test 1223 ; ValidateOnlyOnePhoneCanBePrimary")
    public void ValidateOnlyOnePhoneCanBePrimary() {
        String account = readData.GetData("Account_Kevin");

//      Choose BCAA Member with calls allowed 0 and check if cash call image is displayed
        UIActions.SearchInAppLauncher( "Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        casePage.NavigateCaseInnerTab("Member");
        UIActions.ScrollBottomOfPage(1,1000,true);


        //Marking both phone 1 primary and phone 2 primary and expecting a validation rule error
        UIActions.Click(casePage.btn_Phone1PrimaryEditButton);
        UIActions.Click(casePage.checkbox_Phone1Primarycheckbox);
        UIActions.Click(casePage.checkbox_Phone2Primarycheckbox);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Only one Phone can be primary.']")));
        Verifications.VerifyElementPresent(casePage.warning_PrimaryPhoneWarning);
    }


    @Test(description = "Story 1026, Test 1100 ; ValidateServiceTypeIsPopulatedBasedOnPacesetterCode")
    public void ValidateServiceTypeIsPopulatedBasedOnPacesetterCode() {
        String [] SValues={"L301 - No Crank - Jump Start" , "L507 - Undercar Component Dragging"
                ,"L601 - Engine Runs Poorly", "L690 - Other Runs Poorly Problem", "L390 - Other No Crank Problem", "L401 - Crank No Start",
                "L490 - Other Crank No Start Problem", "L101 - Flat Tire w/spare", "L102 - Flat Tire w/o spare", "L104 - Need Air In Tire", "L105 - Bicycle Assist Service",
                "L190 - Other Tire Problem", "L701 - Key Locked In Passenger Compartment", "L703 - Key Broken In Door - Not Ignition Key", "L704 - Frozen Door Lock", "L790 - Other Lockout Problem", "L802 - Key Locked In Trunk - No Trunk Release", "L806 - Key Broken in Ignition Switch", "L890 - Other Locksmith Problem", "L803 - Lost/Damaged Vehicle Key", "L702 - Key Locked In Trunk w/Trunk Release",
                "L402 - Out of Gasoline", "L403 - Out of Diesel", "L201 - Engine Overheat"
//                "L804 - Lost/Damaged "Club" Key","L501 - Won\'\t Go Into Gear","L505 - Parking Brake Won\"'t Release","L506 - Hood/Door Won\"'t Close/Latch","L590 - Other Runs Won\"'t Move Problem","L801 - Ignition Key Won\"'t Turn In Switch"
        };

        String [] BValues={"L302 - No Crank - Bat Svc (non-AAA Bat)", "L303 - No Crank - Bat Svc (AAA Bat)", "L304 - Member Requests Battery Service"};
        String [] TValues={"T931 - Extrication - Probable GO", "L103 - Multiple Flat Tires", "L404 - Electric Vehicle (EV) Out Of Charge", "L508 - Leaking Fluids", "L509 - Windshield Damage", "L510 - Car Alarm Issue",
"L805 - Key Broken In Door - Ignition Key","T180 - Tire Issue Requires Tow To Shop", "T280 - Engine Overheat", "T380 - Member Requests Tow", "T381 - Known Starter Problem",
"T382 - Known Alternator Problem", "T480 - Member Requests Tow", "T481 - Vehicle Mis-Fueled", "T483 - Electric Vehicle Out Of Charge", "T484 - Known Alternator Problem", "T580 - Transmission/Clutch Failure", "T581 - Axle/Driveshaft/Suspension Failure",
"T582 - Brake System Failure", "T680 - Engine Stalled While Driving", "T880 - Lock Issue Required Tow To Dealer", "T907 - Convenience/Member Concern Tow", "T921 - Insurance Accident Assist", "T922 - Insurance Accident Assist 2nd Tow", "T932 - Extrication - Probable TOW",
"T980 - Light Service Redispatch As Tow", "T990 - Other Required Tow"};

        UIActions.SearchInAppLauncher( "Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        for (String s: BValues){
            UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
            UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
            UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+s+"']")));
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
            UIActions.SetDelayAfterAction(2000);
            Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.RA_ServiceType__c']//following::lightning-formatted-text[1]")).getText(),"B");
        }
        for (String s: TValues){
            UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
            UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
            UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+s+"']")));
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
            UIActions.SetDelayAfterAction(2000);
            Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.RA_ServiceType__c']//following::lightning-formatted-text[1]")).getText(),"T");

        }
        for (String s: SValues){
            UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
            UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
            UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+s+"']")));
            UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
            UIActions.SetDelayAfterAction(2000);
            Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.RA_ServiceType__c']//following::lightning-formatted-text[1]")).getText(),"S");

    }
    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1263; ValidateAutomaticSpottingForCaseWithSServiceType")
    //For this test case you need to make sure your polygon assignment has light service type before running the test

    public void ValidateAutomaticSpottingForCaseWithSServiceType(){
        String faciltyCode = "545";
        String facilty = "HOPE TOWING LTD";
        String faciltyAddress = "1060 5TH AVE HOPE BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "451 Corbett St, Hope, BC V0X 1L4, Canada");
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_UP);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L402 - Out of Gasoline']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1264 ; ValidateAutomaticSpottingForCaseWithTServiceType")
    //For this test case you need to make sure your polygon assignment has Full service type before running the test

    public void ValidateAutomaticSpottingForCaseWithTServiceType(){
        String faciltyCode = "312";
        String facilty = "PENINSULA TOWING";
        String faciltyAddress = "6678 BERTRAM PLACE SAANICHTON BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "6701 Oldfield Rd, Saanichton, BC V8M 2A1, Canada");
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L509 - Windshield Damage']")));
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }

    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1265 ; ValidateAutomaticSpottingForCaseWithBServiceType")
    public void ValidateAutomaticSpottingForCaseWithBServiceType(){
        String faciltyCode = "725";
        String facilty = "SORRENTO TOWING & RECOVERY";
        String faciltyAddress = "2827 HILLTOP ROAD SORRENTO BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "2847 Walsh Rd, Sorrento, BC V0E 2W1, Canada");
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L302 - No Crank - Bat Svc (non-AAA Bat)']")));
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped")
    @Test(description = "Story= 528, Test= 1271; ValidateAnErrorIsDisplayedIfNoPolygonAssignmentAreReturned")
    public void ValidateAnErrorIsDisplayedIfNoPolygonAssignmentAreReturned(){
        String faciltyCode = "725";
        String facilty = "SORRENTO TOWING & RECOVERY";
        String faciltyAddress = "2827 HILLTOP ROAD SORRENTO BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "2847 Walsh Rd, Sorrento, BC V0E 2W1, Canada");
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L101 - Flat Tire w/spare']")));
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='Service is not available - please update Case Pacesetter Code']")));;

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1274 ; ValidateSystemWontPerformAutomaticSpottingWhenCaseServiceTypeBAndServiceTypeLightOrFull")
    public void ValidateSystemWontPerformAutomaticSpottingWhenCaseServiceTypeBAndServiceTypeLightOrFull(){
        //For this test case you need to make sure that all your polygon assignment for the address in this test don't have battery service type before running the test
        String faciltyCode = "725";
        String facilty = "SORRENTO TOWING & RECOVERY";
        String faciltyAddress = "2827 HILLTOP ROAD SORRENTO BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "2847 Walsh Rd, Sorrento, BC V0E 2W1, Canada");
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L302 - No Crank - Bat Svc (non-AAA Bat)']")));
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='Service is not available - please update Case Pacesetter Code']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1273; ValidateAutomaticSpottingForCaseWithSServiceType")
    public void ValidateAutomaticSpottingForCaseWithSServiceTypeAndLightPolygonAssignment(){
        //For this test case you need to make sure your polygon assignment has light service type before running the test
        String faciltyCode = "545";
        String facilty = "HOPE TOWING LTD";
        String faciltyAddress = "1060 5TH AVE HOPE BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "451 Corbett St, Hope, BC V0X 1L4, Canada");
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_UP);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L402 - Out of Gasoline']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(5000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1270; ValidateThatAutomaticSpottingWillBeperformedBasedOnLevelIfMoreThanOnePloygonAssignmentFound")
    public void ValidateThatAutomaticSpottingWillBeperformedBasedOnLevelIfMoreThanOnePloygonAssignmentFound(){
        //For this test case you need to make sure you have two polygon assignments that have the same service type and one has level 1 and one has level 0
        String faciltyCode = "666";
        String facilty = "Guy Battery";
        String faciltyAddress = "2176 Trans-Canada Highway Sorrento BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "2847 Walsh Rd, Sorrento, BC V0E 2W1, Canada");
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_UP);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L302 - No Crank - Bat Svc (non-AAA Bat)']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(5000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1266; ValidateAutomaticSpottingForCaseWithSServiceType")
    public void ValidateAutomaticSpottingForCaseWithSServiceTypeAndLightPolygonAssignmentWithPacesetterCode(){
        //For this test case you need to make sure you have two polygon assignment with light service type but one of them have pacesetter code
        String faciltyCode = "678";
        String facilty = "Guy S Facility";
        String faciltyAddress = "1070 5th Avenue Hope BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "451 Corbett St, Hope, BC V0X 1L4, Canada");
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_UP);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L402 - Out of Gasoline']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(5000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1268; ValidateThatAutomaticSpottingWillBeperformedBasedOnPaceSetterCodeIfMoreThanOnePloygonAssignmentFoundForBServiceType")
    public void ValidateThatAutomaticSpottingWillBeperformedBasedOnPaceSetterCodeIfMoreThanOnePloygonAssignmentFoundForBServiceType(){
        //For this test case you need to make sure you have two polygon assignments that have the same service type (B)and one pacesetter code that matches the case while the other don't
        String faciltyCode = "666";
        String facilty = "Guy Battery";
        String faciltyAddress = "2176 Trans-Canada Highway Sorrento BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "2847 Walsh Rd, Sorrento, BC V0E 2W1, Canada");
        UIActions.SetDelayAfterAction(2000);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_UP);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L302 - No Crank - Bat Svc (non-AAA Bat)']")));
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "edit save");
        UIActions.SetDelayAfterAction(5000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1267 ; ValidateThatAutomaticSpottingWillBeperformedBasedOnPaceSetterCodeIfMoreThanOnePloygonAssignmentFoundForTServiceType")
    //For this test case you need to make sure you have two polygon assignments that have the same service type (T)and one pacesetter code that matches the case while the other don't

    public void ValidateThatAutomaticSpottingWillBeperformedBasedOnPaceSetterCodeIfMoreThanOnePloygonAssignmentFoundForTServiceType(){
        String faciltyCode = "981";
        String facilty = "Guy T Facility";
        String faciltyAddress = "6711 Butler Crescent Saanichton BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "6701 Oldfield Rd, Saanichton, BC V8M 2A1, Canada");
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L509 - Windshield Damage']")));
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }
    @Ignore("Skipped because requirement needs manual configuration before running the test ")
    @Test(description = "Story= 528, Test= 1269 ; ValidateThatAutomaticSpottingWillBeperformedBasedOnDayAndTime")
    //For this test case you need to make sure you have two polygon assignments that have the same service type that one matches the case created date and time and one don't
    public void ValidateThatAutomaticSpottingWillBeperformedBasedOnDayAndTime(){
        String faciltyCode = "981";
        String facilty = "Guy T Facility";
        String faciltyAddress = "6711 Butler Crescent Saanichton BC CA";
        String account = readData.GetData("Account_Kevin");
//      Choosing an account
        UIActions.SearchInAppLauncher("Cases");
        casePage.OpenNewRoadAssistBCAACaseWindow();
        UIActions.Click(casePage.btn_Next);
        casePage.SaveNewCase();
        casePage.NavigateCaseInnerTab("Member");
        UIActions.Click(casePage.btn_account_next);
        casePage.SelectAccountByName(casePage.search_AccountTab_AccountTemp, account);
        UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");
        UIActions.SetDelayAfterAction(2000);


        //Add address in breakdown location tab
        casePage.NavigateCaseInnerTab("Breakdown Location");
        UIActions.Click(casePage.btn_editAddress);
        UIActions.UpdateText(casePage.txt_NonBCAA_HomeAddress, "6701 Oldfield Rd, Saanichton, BC V8M 2A1, Canada");
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ARROW_DOWN);
        casePage.txt_NonBCAA_HomeAddress.sendKeys(Keys.ENTER);
        UIActions.SetDelayAfterAction(3000);
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);


        //Populate Service Type
        casePage.NavigateCaseInnerTab("Service");
        UIActions.ScrollBottomOfPage(1,1000,true);
        UIActions.Click(casePage.btn_PacesetterCodePicklistEditButton);
        UIActions.Click(casePage.dropdown_PacesetterCodePicklist);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='L509 - Windshield Damage']")));
        UIActions.Click(casePage.btn_SaveFieldEdit);
        UIActions.SetDelayAfterAction(2000);
//        WebFlows.RefreshPage();

        //Verify If expected result was populated on the facility Code, facility and facility address
        casePage.NavigateCaseInnerTab("Service");
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Code']//following::lightning-formatted-text[1]")).getText(),faciltyCode);
        Verifications.VerifyElementTextIsEqualToExpectedText(driver.findElement(By.xpath("//span[text()='Facility Address']//following::lightning-formatted-text[1]")).getText(),faciltyAddress);
        Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='"+facilty+"']")));

    }

    //This test checks the service resource layout for Call Taker
    @Test(description = "Story 1189, Test 1377 ; ValidateServiceResourceLayout")
    public void ValidateServiceResourceLayout() {
        String ServiceResourceName = "Resource 1";
        String[] fieldsList = {"Name", "Resource Type", "Active", "Facility",
                "Facility Code", "Partner Account", "Driver ID", "Phone"
//                "Efficiency"
        };
        UIActions.SearchInAppLauncher("Service Resources");
        WebFlows.RefreshPage();
        UIActions.SetDelayAfterAction(3000);
        WebFlows.OpenViewAllListview();
        UIActions.Click(driver.findElement(By.xpath("//a[@title='" + ServiceResourceName + "']")));
        UIActions.SetDelayAfterAction(1000);
        try {
            for (String s : fieldsList) {
                List<WebElement> elems = driver.findElements(By.xpath("//span[text()='" + s + "']"));
                if (elems.size() == 1) {

                    Verifications.VerifyElementPresent(driver.findElement(By.xpath("//span[text()='" + s + "']")));
                } else {
                    Verifications.VerifyElementPresent(elems.get(elems.size() - 1));
                }
            }
        }
        catch (Exception e){
            Assert.assertFalse(true);
        }
    }

}

