package Execute;

import Extensions.RestActions;
import Extensions.Verifications;
import Utilities.JsonPayloads;
import Utilities.Operations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IntegrationTest extends Operations {

    @BeforeClass
    public void BeforeExecution() {
            RestActions.InitiateAPIConnectionGuy();
        }

        @Test
    public void testApiConnection(){
            System.out.println(RestActions.GetValueFromSpecificRecordCell("Case","RA_FullName__c","CaseNumber","00180048"));
//            System.out.println(RestActions.GenerateQuery("id","Case","CaseNumber","00180048"));
        }

    @Test
    public void UpdateSampleCase(){
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateCaseJson(), JsonPayloads.path_Case, "500Aq000005gSD7IAM");
    }
    @Test
    public void UpdateSampleSA(){
        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJsonStatus("Cancelled"), JsonPayloads.path_ServiceAppointment, "08pAq000000e46XIAQ");
        System.out.println( RestActions.GetValueFromSpecificRecordCell("ServiceAppointment", "Status", "Id", "08pAq000000e46XIAQ"));
;

//        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJson(), JsonPayloads.path_ServiceAppointment, "08pAq000000VNy1IAG");
    }
    @Test
    public void CreateSampleCase(){
        RestActions.CreateRecordFromJson(JsonPayloads.CreateCaseJson(), JsonPayloads.path_Case);
        System.out.println(createdRecordID);
    }

    @Test
    public void CreateSamplesa(){
        RestActions.CreateRecordFromJson(JsonPayloads.CreateSAJson(), JsonPayloads.path_ServiceAppointment);
        System.out.println(createdRecordID);
    }

    @Test
    public void CreateSamplewo(){
        RestActions.CreateRecordFromJson(JsonPayloads.CreateWOJson(), JsonPayloads.path_WorkOrder);
        System.out.println(createdRecordID);
    }

    @Test
    public void GetQry(){

      RestActions.GetListFromSpecificObject("Name", "RA_TriageQuestion__c");
        System.out.println(RestActions.GetListFromSpecificObject("Name", "RA_TriageQuestion__c"));
    }
    @Test
    public void GetQry1(){
        String[] OHName = {"RA+MST+Operating+Hours", "RA+PST+Operating+Hours","BCAA+RA+AP+Operating+Hours"};
        String [] TimeZone = {"America/Denver","America/Los_Angeles","America/Los_Angeles"};
         int i=0;
        for (String s:OHName) {
            String operatingHoursTimeZone = RestActions.GetValueFromSpecificRecordCell("OperatingHours", "TimeZone", "Name", s);
            Verifications.VerifyElementTextIsEqualToExpectedText(operatingHoursTimeZone,TimeZone[i]);
            i++;
        }

    }

    }



