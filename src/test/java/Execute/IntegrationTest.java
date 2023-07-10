package Execute;

import Extensions.RestActions;
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
        String serviceAppointmentId = RestActions.GetValueFromSpecificRecordCell("ServiceAppointment","Id","AppointmentNumber","SA-0111");
        System.out.println("this id is "+serviceAppointmentId );

//        RestActions.UpdateRecordFromJson(JsonPayloads.UpdateServiceAppointmentJson(), JsonPayloads.path_ServiceAppointment, "08pAq000000VNy1IAG");
    }
    @Test
    public void CreateSampleCase(){
        RestActions.CreateRecordFromJson(JsonPayloads.CreateCaseJson(), JsonPayloads.path_Case);
        System.out.println(createdRecordID);
    }
    @Test
    public void GetQry(){

      RestActions.GetListFromSpecificObject("Name", "RA_TriageQuestion__c");
        System.out.println(RestActions.GetListFromSpecificObject("Name", "RA_TriageQuestion__c"));
    }
    @Test
    public void GetQry1(){
        RestActions.GetValueFromSpecificRecordCell("Case", "CaseNumber","Id","500Aq000005gSD7IAM");
    }

    }



