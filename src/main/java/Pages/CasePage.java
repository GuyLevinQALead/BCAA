package Pages;

import Extensions.UIActions;
import Utilities.Operations;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CasePage extends Operations {

    @FindBy(xpath = "//a[@title='New']")
    public WebElement btn_New;

    @FindBy(xpath = "//span[text()='Next']")
    public WebElement btn_Next;

    @FindBy(xpath = "//button[@title='Edit Underground Height']")
    public WebElement btn_BreakdownLocationTabUndergroundHeight;

    @FindBy(xpath = "//button[@aria-label='Underground Height, --None--']")
    public WebElement dropdown_BreakdownLocationTabUndergroundHeightPicklist;


    @FindBy(xpath = "//span[@title='Other']")
    public WebElement dropdown_BreakdownLocationTabUndergroundHeightOtherValue;

    @FindBy(xpath = "//label[text()='Other Underground Height']//following::input[1]")
    public WebElement txt_BreakdownLocationTabUndergroundHeightOtherValue;




    @FindBy(xpath = "//a[@title='Submitted']")
    public WebElement btn_CaseStatusSubmitted;

    @FindBy(xpath = "//a[@title='Open']")
    public WebElement btn_CaseStatusOpen;

    @FindBy(xpath = "//a[@title='Complete']")
    public WebElement btn_CaseStatusComplete;
    @FindBy(xpath = "//a[@title='Cancelled']")
    public WebElement btn_CaseStatusCancelled;

    @FindBy(xpath = "//a[@title='Closed']")
    public WebElement btn_CaseStatusClosed;

    @FindBy(xpath = "//button[@title='Edit Cancellation Reason']")
    public WebElement btn_CancellationReasonEdit;

    @FindBy(xpath = "//button[@title='Edit Expected Cash Amount']")
    public WebElement btn_EditExpectedCashAmount;

    @FindBy(xpath = "//input[@name='RA_ExpectedCashAmount__c']")
    public WebElement txt_ExpectedCaseAmount;

    @FindBy(xpath = "//button[@aria-label='Cancellation Reason, --None--']")
    public WebElement btn_CancellationReasonPicklist;

    @FindBy(xpath = "//lightning-base-combobox-item[@data-value='TBD']")
    public WebElement btn_CancellationReasonPicklistTBD;


    @FindBy(xpath = "//button[@title='Edit Facility']")
    public WebElement btn_FacilityEdit;

    @FindBy(xpath = "//input[@placeholder='Search Facilities...']")
    public WebElement search_FacilityList;

    @FindBy(xpath = "//lightning-base-combobox-item[@data-value='actionAdvancedSearch']")
    public WebElement search_FacilityListRunSearch;

    @FindBy(xpath = "//span[text()='Manual Spot Reason']//following::lightning-formatted-text[1]")
    public WebElement txt_FacilityLManualSpotReasonText;






    @FindBy(xpath = "//span[text()='Mark as Current Status']")
    public WebElement btn_CaseStatusMarkAsCurrentStatus;


    @FindBy(xpath = "//span[text()='Select Closed Status']")
    public WebElement btn_CaseStatusMarkStatusAsClosed;
    @FindBy(xpath = "//option[text()='Select a closed stage...']")
    public WebElement dropdown_SelectAClosedSStage;

    @FindBy(xpath = "//option[text()='Cancelled']")
    public WebElement dropdown_SelectCancelledStatus;

    @FindBy(xpath = "//option[text()='Complete']")
    public WebElement dropdown_SelectCompleteStatus;




    @FindBy(xpath = "//div[text()='You encountered some errors when trying to save this record']")
    public WebElement warning_SubmittedValidationRuleError;

    @FindBy(xpath = "//div[text()='You encountered some errors when trying to save this record']")
    public WebElement warning_SubmittedToOpenValidationRuleError;

    @FindBy(xpath = "//span[text()='No matching member information found.']")
    public WebElement warning_CAAOrAAANoMemberFoundError;



    @FindBy(xpath = "//span[text()='Status changed successfully.']")
    public WebElement success_SubmittedSuccessMessage;


    @FindBy(xpath = "//span[text()='BCAA RoadAssist']/parent::label/span[@class='slds-radio--faux topdown-radio--faux']")
    public WebElement radio_BCAAroadAssist;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    public WebElement btn_SaveEdit;

    @FindBy(xpath = "//button[@title='Edit Status']")
    public WebElement btn_EditStatus;

    @FindBy(xpath = "//span[text()='Edit Case Origin']")
    public WebElement dropdown_Origin;

    @FindBy(xpath = "//button[@aria-label='Case Origin, Call Centre']")
    public WebElement dropdown_OriginCallCentreValue;
    @FindBy(xpath = "//button[@title='Edit Location Code']")
    public WebElement btn_EditLocationCode;

    @FindBy(xpath = "//button[@title='Edit Policy']")
    public WebElement btn_EditPolicy;

    @FindBy(xpath = "//button[@title='Edit Case Origin']")
    public WebElement btn_EditCaseOrigin;

    @FindBy(xpath = "//button[@data-value='Open']")
    public WebElement dropdown_StatusEdit;

    @FindBy(xpath = "//span[text()='Edit Call Type']")
    public WebElement dropdown_CallType;

    @FindBy(xpath = "//label[text()='Key Location']/parent::lightning-combobox//button[@data-value='--None--']")
    public WebElement dropdown_KeyLocation;

    @FindBy(xpath = "//label[text()='Location Code']//following::button[@aria-label='Location Code, --None--']")
    public WebElement dropdown_LocationCode;

    @FindBy(xpath = "//span[text()='Key Location']//following::lightning-button-icon[1]")
    public WebElement info_KeyLocationInfo;

    @FindBy(xpath = "//label[text()='Priority']/parent::lightning-combobox//button[@data-value='Low']")
    public WebElement dropdown_Priority;

    @FindBy(xpath = "//span[text()='Is Affiliated Club']/parent::label/parent::lightning-input/div/span/input")
    public WebElement checkbox_IsAffiliatedClub;

    @FindBy(xpath = "//input[@name='RA_IsCourtesyCall__c']")
    public WebElement checkbox_IsCourtesyCall;

    @FindBy(xpath = "//input[@name='RA_JOA__c']")
    public WebElement checkbox_JOA;

    @FindBy(xpath = "//button[@title='Edit JOA']")
    public WebElement btn_EditJOA;

    @FindBy(xpath = "//label[text()='Internal Comments']//following-sibling::div/textarea")
    public WebElement txt_InternalComment;

    @FindBy(xpath = "//input[@name='screeninput_FirstNameAffiliatedMember']")
    public WebElement txt_AffiliatedFirstName;

    @FindBy(xpath = "//input[@name='RA_FirstName__c']")
    public WebElement txt_AffiliatedFirstNameOnUpdate;

    @FindBy(xpath = "//legend[text()='Home Address']//following::input[1]")
    public WebElement txt_AffiliatedHomeAddress;

    @FindBy(xpath = "//input[@placeholder='Search Address']")
    public WebElement txt_AffiliatedHomeAddressOnUpdate;
    @FindBy(xpath = "//input[@name='screeninput_MembershipNumber']")
    public WebElement txt_AffiliatedMembershipNumber;

    @FindBy(xpath = "//input[@name='screeninput_LastNameAffiliatedMember']")
    public WebElement txt_AffiliatedLastName;

    @FindBy(xpath = "//input[@name='RA_LastName__c']")
    public WebElement txt_AffiliatedLastNameOnUpdate;


    @FindBy(xpath = "//select[@name='screeninput_OtherMembershipLevel']")
    public WebElement dropdown_AffiliatedMembershipLevel;

    @FindBy(xpath = "//button[@aria-label='Membership Level, Basic']")
    public WebElement dropdown_AffiliatedMembershipLevelOnUpdate;

    @FindBy(xpath = "//option[@value='pcs_OtherMembershipLevel.Basic']")
    public WebElement dropdown_AffiliatedMembershipLevelBasic;

    @FindBy(xpath = "//span[@title='Basic']")
    public WebElement dropdown_AffiliatedMembershipLevelBasicOnUpdate;

    @FindBy(xpath = "//option[@value='pcs_OtherMembershipLevel.Plus']")
    public WebElement dropdown_AffiliatedMembershipLevelPlus;

    @FindBy(xpath = "//span[@title='Plus']")
    public WebElement dropdown_AffiliatedMembershipLevelPlusOnUpdate;

    @FindBy(xpath = "//option[@value='pcs_OtherMembershipLevel.Premier']")
    public WebElement dropdown_AffiliatedMembershipLevelPremier;

    @FindBy(xpath = "//span[@title='Premier']")
    public WebElement dropdown_AffiliatedMembershipLevelPremierOnUpdate;

    @FindBy(xpath = "//input[@inputmode='email']")
    public WebElement txt_AffiliatedEmail;

    @FindBy(xpath = "//input[@name='RA_Email__c']")
    public WebElement txt_AffiliatedEmailOnUpdate;

    @FindBy(xpath = "//input[@type='tel']")
    public WebElement txt_AffiliatedPhone;

    @FindBy(xpath = "//input[@name='RA_Phone__c']")
    public WebElement txt_AffiliatedPhoneOnUpdate;

    @FindBy(xpath = "//input[@name='input_Last_Name']")
    public WebElement txt_MemberLastName;

    @FindBy(xpath = "//input[@name='input_First_Name']")
    public WebElement txt_MemberFirstName;

    @FindBy(xpath = "//button[text()='Search for Members']")
    public WebElement btn_SearchForMembers;

    @FindBy(xpath = "//input[@name='selectedAccount']")
    public WebElement radio_SelectAccount;

    @FindBy(xpath = "//button[text()='Select and Update Case']")
    public WebElement btn_SelectAndUpdateMember;

    @FindBy(xpath = "//button[text()='Finish']")
    public WebElement btn_FinishAddMember;

    @FindBy(xpath = "//span[text()='Tow Passenger']/parent::div/parent::div/div/button")
    public WebElement btn_rideAlongEdit;

    @FindBy(xpath = "//label[text()='Tow Passenger']/parent::lightning-textarea/div/textarea")
    public WebElement txt_rideAlongText;

    @FindBy(xpath = "//button[@title='Edit Call Taker Comments']")
    public WebElement btn_callTakerComments;

    @FindBy(xpath = "//label[text()='Call Taker Comments']/parent::lightning-textarea/div/textarea")
    public WebElement txt_callTakerComments;

    @FindBy(xpath = "//button[text()='Save']")
    public WebElement btn_SaveFieldEdit;

    @FindBy(xpath = "//button[@title='Save']")
    public WebElement btn_CaseStatusSave;

    @FindBy(xpath = "//label[text()='Pacesetter Code'][1]//following::div[1]")
    public WebElement dropdown_PacesetterCodePicklist;

    @FindBy(xpath = "//button[@aria-label='Pacesetter Code, --None--'][1]")
    public WebElement dropdown_PacesetterCodePicklistOnUpdate;





    @FindBy(xpath = "//button[@title='Edit Pacesetter Code']")
    public WebElement btn_PacesetterCodePicklistEditButton;

    @FindBy(xpath = "//button[@title='Edit Phone 1 Primary']")
    public WebElement btn_Phone1PrimaryEditButton;

    @FindBy(xpath = "//input[@name='RA_Phone1Primary__c']")
    public WebElement checkbox_Phone1Primarycheckbox;

    @FindBy(xpath = "//input[@name='RA_Phone2Primary__c']")
    public WebElement checkbox_Phone2Primarycheckbox;

    @FindBy(xpath = "//li[text()='Only one Phone can be primary.']")
    public WebElement warning_PrimaryPhoneWarning;

    @FindBy(xpath = "//button[@title='Edit Driver To Collect - Details']")
    public WebElement btn_DriverToCollectEditButton;

    @FindBy(xpath = "//label[text()='Driver To Collect - Details']//following::textarea[1]")
    public WebElement txt_DriverToCollectTextArea;

    @FindBy(xpath = "//div[@data-target-selection-name='sfdc:RecordField.Case.RA_DrivertoCollectDetails__c']//lightning-formatted-text")
    public WebElement txt_DriverToCollectTextAreaAfterUpdate;

    @FindBy(xpath = "//label[text()='Club Membership']//following::input[1]")
    public WebElement search_ClubMembershipCAAOrAAA;

    @FindBy(xpath = "//button[@title='Clear Selection']")
    public WebElement btn_ClubMembershipCAAOrAAAClear;


    @FindBy(xpath = "//label[text()='Phone Number']//following::input[1]")
    public WebElement txt_PhoneNumberCAAOrAAA;

    @FindBy(xpath = "//span[text()='First Name']//following::input[1]")
    public WebElement txt_FirstNameCAAOrAAA;

    @FindBy(xpath = "//span[text()='Last Name']//following::input[1]")
    public WebElement txt_LastNameCAAOrAAA;

    @FindBy(xpath = "//span[text()='Membership Number']//following::input[1]")
    public WebElement txt_MembershipNumberCAAOrAAA;


    @FindBy(xpath = "//button[text()='Search']")
    public WebElement btn_SearchBtnCAAOrAAA;

    @FindBy(xpath = "//button[text()='Previous']")
    public WebElement btn_PreviousBtnCAAOrAAA;

    @FindBy(xpath = "//span[@class='slds-radio_faux'][1]")
    public WebElement btn_ResultRadioBtnCAAOrAAA;

    @FindBy(xpath = "//button[@title='Edit Cash Call']")
    public WebElement btn_EditCashCall;


    @FindBy(xpath = "//span[@title='Over Entitlement']")
    public WebElement btn_EditCashCallOverEntitlement;

    @FindBy(xpath = "//div[text()='Cash Call']//following::button[@title='Move selection to Chosen']")
    public WebElement btn_EditCashCallMoveToChosenMultiPicklist;


    @FindBy(xpath = "//img[@alt='Cash Call']")
    public WebElement img_CashCallImage;










    @FindBy(xpath = "//label[text()='Callback Type']//following::button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value']")
    public WebElement dropdown_caseCallback;

    @FindBy(xpath = "//span[@class='inline-edit-trigger-icon slds-button__icon slds-button__icon_hint']")
    public WebElement btn_editPencilButton;

    @FindBy(xpath = "//label[text()='Callback Type']//following::button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value']//following::span[text()='Automatic']")
    public WebElement dropdown_caseCallbackAutomatic;

    @FindBy(xpath = "//label[text()='Callback Type']//following::button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value']//following::span[text()='Manual']")
    public WebElement dropdown_caseCallbackManual;

    @FindBy(xpath = "//label[text()='Case Callback Reason']//following::textarea[@class='slds-textarea']")
    public WebElement txt_caseCallbackReason;

    @FindBy(xpath = "//label[text()='Case Callback Reason']/parent::lightning-textarea/div/textarea")
    public WebElement txt_caseCallBackReason;

    @FindBy(xpath = "//span[text()='Call this long before arrival (minutes)']/parent::div/parent::div//button")
    public WebElement btn_callThisLongBeforeArrival;

    @FindBy(xpath = "//input[@name='RA_CaseCallbackTime__c']")
    public WebElement txt_callThisLongBeforeArrival;

    @FindBy(xpath = "//span[text()='Call this long before arrival (minutes)']/parent::div/parent::div//span//slot//lightning-formatted-number")
    public WebElement label_callThisLongBeforeArrival;

    @FindBy(xpath = "//input[@placeholder = 'Search Vehicle Histories...']")
    public WebElement txt_searchVehicle;

    @FindBy(xpath = "//span[text()='Edit Engine Type']/parent::button")
    public WebElement btn_editEngineType;

    @FindBy(xpath = "//button[@title='Edit Vehicle']")
    public WebElement btn_editVehicle;

    @FindBy(xpath = "//button[@title='Edit Red Flag']")
    public WebElement btn_editRedFlag;

    @FindBy(xpath = "//input[@name='RA_RedFlag__c']")
    public WebElement checkbox_RedFlag;

    @FindBy(xpath = "//input[@name='RA_CaseCallback__c']")
    public WebElement checkbox_caseCallback;

    @FindBy(xpath = "//input[@name='RA_MemberHoldingForCallBack__c']")
    public WebElement checkbox_MemberHoldingForCallBack;

    @FindBy(xpath = "//label[text()='Member Holding for Call Back Notes']//following::textarea[@class='slds-textarea'][1]")
    public WebElement txt_MemberHoldingForCallBackNotes;

    @FindBy(xpath = "//button[@aria-label='Case Priority, --None--']")
    public WebElement btn_casePriority;

    @FindBy(xpath = "//img[@src='/img/samples/flag_red.gif']")
    public WebElement img_callbackFlag;

    @FindBy(xpath = "//input[@name='RA_RedFlag__c']")
    public WebElement checkbox_redFlag;

    @FindBy(xpath = "//button[@title='Edit Address Geolocation']")
    public WebElement btn_editAddress;

    @FindBy(xpath = "//input[@name='city']")
    public WebElement txt_address_city;

    @FindBy(xpath = "//input[@autocomplete='postal-code']")
    public WebElement txt_address_postal;

    @FindBy(xpath = "//input[@name='province']")
    public WebElement txt_address_province;

    @FindBy(xpath = "//button[text()='Next']")
    public WebElement btn_account_next;

    @FindBy(xpath = "//button[text()='Save']")
    public WebElement btn_account_Save;

    @FindBy(xpath = "//input[@name='screeninput_FirstNameNonBCAA']")
    public WebElement txt_NonBCAA_FirstName;

    @FindBy(xpath = "//input[@name='screeninput_LastNameNonBCAA']")
    public WebElement txt_NonBCAA_LastName;

    @FindBy(xpath = "//label[text()='Phone']//following::input[@class='slds-input'][1]")
    public WebElement txt_NonBCAA_Phone;

    @FindBy(xpath = "//label[text()='Email']//following::input[@class='slds-input'][1]")
    public WebElement txt_NonBCAA_Email;

    @FindBy(xpath = "//input[@placeholder='Search Address']")
    public WebElement txt_NonBCAA_HomeAddress;

    @FindBy(xpath = "//label[text()='Club Membership']//following::input[@aria-haspopup='listbox'][1]")
    public WebElement search_Xperigo_ClubMembership;

    @FindBy(xpath = "//input[@name='screeninput_VINNumber']")
    public WebElement txt_Xperigo_VINNumber;


    @FindBy(xpath = "//input[@name='screeninput_FirstNameXperigo']")
    public WebElement txt_Xperigo_FirstName;


    @FindBy(xpath = "//input[@name='screeninput_LastNameXperigo']")
    public WebElement txt_Xperigo_LastName;

    @FindBy(xpath = "//input[@name='screeninput_XperigoCallNumber']")
    public WebElement txt_Xperigo_XperigoCallNumber;

    @FindBy(xpath = "//span[text()='Payment Responsibility Code']//following::select[@class='slds-select']")
    public WebElement txt_Xperigo_PaymentResponsibilityCode;

    @FindBy(xpath = "//option[text()='C (Cash Call)']")
    public WebElement txt_Xperigo_PaymentResponsibilityCodeCashCall;

    @FindBy(xpath = "//option[text()='P (PrePaid)']")
    public WebElement txt_Xperigo_PaymentResponsibilityCodePrePaid;

    @FindBy(xpath = "//option[text()='S (Sponsor)']")
    public WebElement txt_Xperigo_PaymentResponsibilityCodeSponsor;

    @FindBy(xpath = "//label[text()='Phone']//following::input[1]")
    public WebElement txt_Xperigo_Phone;

    @FindBy(xpath = "//label[text()='Email']//following::input[1]")
    public WebElement txt_Xperigo_Email;

    @FindBy(xpath = "//legend[text()='Home Address']//following::input[1]")
    public WebElement txt_Xperigo_HomeAddress;

    @FindBy(xpath = "//input[@placeholder='Search Accounts...']")
    public WebElement search_MemberTab_account;

    @FindBy(xpath = "//input[@placeholder='Search Accounts...']")
    public WebElement search_AccountTab_Account;

    @FindBy(xpath = "//input[@placeholder='Search undefined...']")
    public WebElement search_AccountTab_AccountTemp;

    @FindBy(xpath = "//span[contains(text(), 'Show All Results')]")
    public WebElement link_showAllResults;

    @FindBy(xpath = "//input[@type='radio']/parent::span//following-sibling::label/span[1]")
    public WebElement radio_homeAddress;

    @FindBy(xpath = "//input[@value='Select']")
    public WebElement input_Select;

    @FindBy(xpath = "//span[text()='Tow Destination Address']/parent::div/following-sibling::div/span/slot/lightning-formatted-address/a")
    public WebElement address_TowDestination;

    @FindBy(xpath = "//button[@title='Edit Cash Call Type']")
    public WebElement btn_cashCallTypeEdit;

    @FindBy(xpath = "//input[@name='RA_ExpectedCashAmount__c']")
    public WebElement input_expectedCashAmount;

    @FindBy(xpath = "//label[text()='Call Type']/following-sibling::div/lightning-base-combobox/div/div/button")
    public WebElement combobox_callType;

    @FindBy(xpath = "//button[@title='Edit Call Type']")
    public WebElement btn_callTypeEdit;

    @FindBy(xpath = "//button[@title='Edit Tow Distance']")
    public WebElement btn_TowDistanceEdit;

    @FindBy(xpath = "//input[@name='RA_TowDistance__c']")
    public WebElement txt_TowDistanceText;

    @FindBy(xpath = "//input[@placeholder='Search Makes...']")
    public WebElement search_make;

    @FindBy(xpath = "//strong[text()='Search Vehicle']//ancestor::flowruntime-flow//descendant::button[text()='Next']")
    public WebElement btn_SearchVehicle;

    @FindBy(xpath = "//button[text()='Confirm']")
    public WebElement btn_confirmVehicleSelection;

    @FindBy(xpath = "//button[text()='Save Vehicle Information']")
    public WebElement btn_SaveVehicleSelection;


    @FindBy(xpath = "//strong[text()='Search Vehicle']")
    public WebElement txt_SearchVehicleComponent;

    @FindBy(xpath = "//strong[text()='Vehicles found in previous cases:']")
    public WebElement txt_PreviousVehiclesComponent;

    @FindBy(xpath = "//span[text()='Vehicle Information']")
    public WebElement txt_VehicleInformationSection;

    @FindBy(xpath = "//span[text()='RV Additional Information']")
    public WebElement txt_RVAdditionalInformationSection;

    @FindBy(xpath = "//span[text()='Triage Q&A']")
    public WebElement txt_TriageSection;

    @FindBy(xpath = "//button[@title='Edit Vehicle Type']")
    public WebElement btn_EditVehicleType;

    @FindBy(xpath = "//button[@title='Edit RV Class']")
    public WebElement btn_EditRVClass;

    @FindBy(xpath = "//button[@aria-label='RV Class, --None--']")
    public WebElement dropdown_RVClass;

    @FindBy(xpath = "//button[@aria-label='RV Engine Location, --None--']")
    public WebElement dropdown_RVEngineLocation;

    @FindBy(xpath = "//label[text()='Length']//following::input[1]")
    public WebElement txt_RVLengthTextBox;

    @FindBy(xpath = "//label[text()='Height']//following::input[1]")
    public WebElement txt_RVHeightTextBox;

    @FindBy(xpath = "//label[text()='Weight']//following::input[1]")
    public WebElement txt_RVWeightTextBox;

    @FindBy(xpath = "//button[@title='Edit Motorcycle Type']")
    public WebElement btn_EditMotorcycleType;

    @FindBy(xpath = "//button[@aria-label='Motorcycle Type, --None--']")
    public WebElement dropdown_MotorcycleType;


    @FindBy(xpath = "//button[@aria-label='Motorcycle Engine Type, --None--']")
    public WebElement dropdown_MotorcycleEngineType;


    @FindBy(xpath = "//label[text()='Comment']//following::input[1]")
    public WebElement txt_MotorcycleComment;


    @FindBy(xpath = "//button[@data-value='Passenger Car/Truck']")
    public WebElement dropdown_VehicleType;

    @FindBy(xpath = "//button[@aria-label='Problem Type, --None--']")
    public WebElement dropdown_ProblemType;


    @FindBy(xpath = "//label[text()='Vehicle Description']//following::input[1]")
    public WebElement txt_VehicleDescriptionTextBox;

    @FindBy(xpath = "//button[@aria-label='Year, --None--']")
    public WebElement dropdown_YearVehicleInformationSection;


    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='Make']//following::input[@class='slds-input'][1]")
    public WebElement txt_VehicleMakeTextBox;

    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='Model']//following::input[@class='slds-input'][1]")
    public WebElement txt_VehicleModelTextBox;

    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='License Plate']//following::input[@class='slds-input'][1]")
    public WebElement txt_LicensePlatTextBox;

    @FindBy(xpath = "//button[@aria-label='Driveline, --None--']")
    public WebElement dropdown_Driveline;
    @FindBy(xpath = "//button[@aria-label='Fuel Type, --None--']")
    public WebElement dropdown_FuelType;
    @FindBy(xpath = "//button[@aria-label='Color, --None--']")
    public WebElement dropdown_Color;
    @FindBy(xpath = "//button[@aria-label='Province, --None--']")
    public WebElement dropdown_Province;

    @FindBy(xpath = "//span[text()='Front']")
    public WebElement multipicklist_FlatTireFront;

    @FindBy(xpath = "//button[@title='Move selection to Chosen']")
    public WebElement multipicklist_MoveToChosen;

    @FindBy(xpath = "//button[@title='Edit Trailer Type']")
    public WebElement btn_EditTrailerType;


    @FindBy(xpath = "//button[@aria-label='Trailer Type, --None--']")
    public WebElement dropdown_TrailerType;

    @FindBy(xpath = "//button[@aria-label='Trailer Plug Type, --None--']")
    public WebElement dropdown_TrailerPlugType;

    @FindBy(xpath = "//button[@aria-label='Trailer Hitch Size, --None--']")
    public WebElement dropdown_TrailerHitchSize;

    @FindBy(xpath = "//button[@aria-label='Trailer Hitch Type, --None--']")
    public WebElement dropdown_TrailerHitchType;

    @FindBy(xpath = "//button[@aria-label='Trailer Length, --None--']")
    public WebElement dropdown_TrailerLength;

    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='No. Axles']//following::input[@class='slds-input'][1]")
    public WebElement txt_NoAxlesTextBox;

    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='Condition']//following::input[@class='slds-input'][1]")
    public WebElement txt_ConditionTextBox;

    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='Load Type']//following::input[@class='slds-input'][1]")
    public WebElement txt_LoadTypeTextBox;

    @FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex' and text()='Load Weight']//following::input[@class='slds-input'][1]")
    public WebElement txt_LoadWeightTextBox;







    @FindBy(xpath = "//button[text()='Finish']")
    public WebElement btn_FinishCarSelection;
    @FindBy(xpath = "//input[@name='License_Plate_Number']")
    public WebElement txt_LicensePlateNumber;

    @FindBy(xpath = "//span[text()='Triage Q&A']/ancestor::article//descendant::button")
    public WebElement btn_QA_Next;

    @FindBy(xpath = "//button[text()='Finish']")
    public WebElement btn_QA_Finish;

    @FindBy(xpath = "//button[@title='Edit Problem Type']")
    public WebElement btn_problemTypeEdit;

    @FindBy(xpath = "//a[@title='Cases']")
    public WebElement btn_casesTab;


    @FindBy(xpath = "//input[@placeholder='Search this list...']")
    public WebElement search_casesList;



    @FindBy(xpath = "//span[text()='Edit Problem Type']")
    public WebElement dropdown_problemType;

    @FindBy(xpath = "//select[@name = 'screeninput_ClubType']")
    public WebElement dropdown_clubType;

    @FindBy(xpath = "//input[@name='country']")
    public WebElement dropdown_country;

    @FindBy(xpath = "//span[@class='slds-radio_faux']")
    public WebElement radbtn_VehicleFromPreviousCases;

    @FindBy(xpath = "//button[text()='Confirm Selection and Save']")
    public WebElement btn_confirmVehicleSelectionAndSave;

    @FindBy(xpath = "//input[@name='latitude']")
    public WebElement txt_latitude;

    @FindBy(xpath = "//input[@name='longitude']")
    public WebElement txt_longitude;

    @FindBy(xpath = "//textarea[@name='street']")
    public WebElement txt_Address_Street;

    @FindBy(xpath = "//input[@name = 'country']")
    public WebElement dropdown_Address_Country;

    @FindBy(xpath = "//select[@name='search_form_year']")
    public WebElement dropdown_searchCarFromYear;

    @FindBy(xpath = "//input[@placeholder='Search Makes...']")
    public WebElement search_searchCarMakes;

    @FindBy(xpath = "//input[@placeholder='Search Models...']")
    public WebElement search_searchCarModels;


    @FindBy(xpath = "//button[text()='Back']")
    public WebElement btn_VehicleSearchBackBtn;


    public void OpenNewRoadAssistBCAACaseWindow(){
        UIActions.Click(mainPage.link_Cases);
        UIActions.Click(btn_New);
        newTabRecordCreated = true;
        UIActions.Click(radio_BCAAroadAssist);
//        UIActions.ClickAndWait(btn_Next, "Next");
//        UIActions.ClickAndWait(btn_SaveEdit, "Save");
        currentTest.info("Opened new case window.");
    }

    public void SearchAndSelectAccount(String name){
        UIActions.Click(casePage.btn_account_next);
        UIActions.UpdateText(casePage.search_MemberTab_account, name);
        UIActions.ClickElementByXpath("//lightning-base-combobox-formatted-text[@title='"+name+"']");
        UIActions.ClickAndWait(casePage.btn_account_Save, "save");
    }

    public void SelectPriorityValue(WebElement dropdown, String priorityValue){
        UIActions.Click(dropdown);
        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+priorityValue+"']")));
    }
//
//    public void SelectPriorityValue(WebElement dropdown, String priorityValue){
//        UIActions.Click(dropdown);
//        UIActions.Click(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+priorityValue+"']")));
//    }

    public void SelectProblemType(WebElement dropdown, String problemValue){
        UIActions.ClickAndWait(dropdown, "problem dropdown");
        UIActions.ClickAndWait(driver.findElement(By.xpath("//label[text()='Problem Type']//following::span[text()='--None--'][1]")), "problem dropdown");
        UIActions.ClickAndWait(driver.findElement(By.xpath("//span[@title='"+problemValue+"']")), problemValue);
    }

    public void SelectCaseOrigin(){
        int repeat = 0;
        while (repeat <= 3){
            try{
                UIActions.ClickAndWait(casePage.dropdown_Origin, "origin");
                actions.sendKeys(Keys.ARROW_DOWN).build().perform();
                actions.sendKeys(Keys.ENTER).build().perform();
                break;
            }catch (StaleElementReferenceException e){
                UIActions.ClickAndWait(casePage.dropdown_Origin, "origin");
                actions.sendKeys(Keys.ARROW_DOWN).build().perform();
                actions.sendKeys(Keys.ENTER).build().perform();
            }
            repeat++;
        }
    }

    public void SelectCallTypeValue(String callTypeValue){
        int repeat = 0;
        while (repeat <= 1){
            try{
//                UIActions.Click(dropdown_CallType);
                UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+callTypeValue+"']")), callTypeValue);


                break;
            }catch (StaleElementReferenceException e){
//                UIActions.Click(dropdown_CallType);

                UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+callTypeValue+"']")), callTypeValue);



//            }
//            catch (ElementClickInterceptedException e){
//                JavascriptExecutor executor = (JavascriptExecutor)driver;
//                executor.executeScript("arguments[0].click();", dropdown_CallType);
//                executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Call Type']//following::button[@data-value='--None--'][1]")));
//                UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+callTypeValue+"']")), callTypeValue);
//                UIActions.ClickAndWait(casePage.btn_SaveFieldEdit, "save edit");

            }
            repeat++;
        }
    }

    //span[@title='"+callTypeValue+"']
    public void SelectStatusValue(WebElement dropdown, String statusValue){
        try {
            UIActions.ClickAndWait(dropdown, "Status dropdown");
            UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+statusValue+"']")), statusValue);
        } catch (StaleElementReferenceException e){
            UIActions.SetDelayAfterAction(1000);
            UIActions.ClickAndWait(dropdown, "Status dropdown");
            UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+statusValue+"']")), statusValue);

        }
    }

    public void SelectCaseOrigin(WebElement dropdown, String originValue){
        UIActions.ClickAndWait(dropdown, "");
        UIActions.ClickAndWait(driver.findElement(By.xpath("//span[@title='"+originValue+"']")), "");
    }

    public static void ClickElementInCountryDropdownByXpathUsingText(WebElement elem ,String listOption){
        try {
            Thread.sleep(500);
            UIActions.Click(elem);
            WebElement dropdown = driver.findElement(By.xpath("//span[@title='"+listOption+"']"));
            UIActions.Click(dropdown);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ClickElementInVehicleAdditionalInformationByXpathUsingText(String colorProvince ,String listOption){
        try {
            Thread.sleep(500);
            UIActions.Click(driver.findElement(By.xpath("//select[@name='"+colorProvince+"']")));
            WebElement dropdown = driver.findElement(By.xpath("//option[text()='"+listOption+"']"));
            UIActions.Click(dropdown);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void SelectAccountByName(WebElement dropdown, String accountName){
        try {
            UIActions.UpdateText(dropdown, accountName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")));
            UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")), "click on account");
        }

        catch (Exception e){
            System.out.println("Couldn't Select An account");
        }
    }

    public void SelectMake(WebElement dropdown, String make){
        UIActions.UpdateText(dropdown, make);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-base-combobox-formatted-text[@title='"+make+"']")));
        UIActions.ClickAndWait(driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='"+make+"']")), "selected make model");
    }

    public void SelectModel(WebElement dropdown, String model){
        UIActions.Click(dropdown);
        UIActions.UpdateText(dropdown, model);
        dropdown.sendKeys(Keys.DOWN);
        dropdown.sendKeys(Keys.DOWN);
        dropdown.sendKeys(Keys.ENTER);
    }

    public void NavigateCaseInnerTab(String tabName) {
        try {
            UIActions.SetDelayAfterAction(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='" + tabName + "']")));
            List<WebElement> tab = driver.findElements(By.xpath("//a[@data-label='" + tabName + "']"));
            if (tab.size() > 0) {
                UIActions.Click(tab.get(tab.size() - 1));
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='More Tabs']")));
                List<WebElement> moreTab = driver.findElements(By.xpath("//button[@title='More Tabs']"));
                if (moreTab.size() > 0) {
                    UIActions.Click(moreTab.get(moreTab.size() - 1));
                    List<WebElement> tabsInMore = driver.findElements(By.xpath("//span[text()='" + tabName + "']/parent::a"));
                    UIActions.Click(tab.get(tabsInMore.size() - 1));
                }
            }
            UIActions.SetDelayAfterAction(500);
        } catch (Exception e) {
            e.printStackTrace();
            currentTest.warning("Could not resolve Inner case navigate");
            Assert.fail();

        }
    }

    public WebElement GetRelatedTabComment(String commentText){
        return driver.findElement(By.xpath("//td//span[text()='"+commentText+"']"));
    }

    public void SaveNewCase(){
        try{
            UIActions.SetDelayAfterAction(1000);
            UIActions.ClickAndWait(btn_SaveEdit, "Save");
            UIActions.SetDelayAfterAction(2000);
            currentTest.info("Saved new case");
        }catch (Exception e){
            currentTest.warning("Could not resolve new case window.");
        }
    }

    public void SelectVehicleFromPicklist(String vehicle){
        UIActions.UpdateText(txt_searchVehicle, vehicle);
        UIActions.ClickElementByXpath("//span[contains(text(), 'Show All Results')]");
        UIActions.SetDelayAfterAction(2500);
        UIActions.ClickElementByLink("Motorcycle 2-Wheeled");
        UIActions.Click(btn_SaveEdit);
    }

    public String EstimateOverKM(){
        return driver.findElement(By.xpath("//span[text()='Estimated Over KM']/parent::div/following-sibling::div/span/slot/records-formula-output/slot/lightning-formatted-number")).getText();
    }

    public String ExpectedCostToTowVehicle(){
        return driver.findElement(By.xpath("//span[text()='Expected Cost to Tow Vehicle']/parent::div/following-sibling::div/span/slot/records-formula-output/slot/lightning-formatted-text")).getText();
    }

    public void SelectClubType(String type){
        UIActions.ClickAndWait(dropdown_clubType, "club type");
        UIActions.ClickElementByXpath("//option[text()='"+type+"']");
    }

    public void SelectAddressCountry(String CountryValue){
        UIActions.ClickAndWait(dropdown_country, "");
        UIActions.ClickElementByXpath("//lightning-base-combobox-item[@data-value='"+CountryValue+"']");
    }

    public void SearchCarYear(String year){
        Select select = new Select(dropdown_searchCarFromYear);
        select.selectByVisibleText(year);
    }



}
