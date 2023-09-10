package Utilities;

import org.json.JSONObject;

public class JsonPayloads extends Operations {
    public static String path_Case = "/sobjects/Case";
    public static String path_ServiceAppointment = "/sobjects/ServiceAppointment";
    public static String path_WorkOrder = "/sobjects/WorkOrder";

    public static JSONObject CreateCaseJson(){
        JSONObject createCase = new JSONObject();
        createCase.put("Origin", "Phone");
        createCase.put("RecordTypeId", "012Aq000000FNabIAG");
        return createCase;
    }

    public static JSONObject CreateSAJson(){
        JSONObject createSA = new JSONObject();
        createSA.put("ParentRecordId", "001Aq000008PbpWIAS");
        createSA.put("EarliestStartTime", "2023-08-28T09:00:00.000+0000");
        createSA.put("DueDate", "2023-08-29T09:00:00.000+0000");
        createSA.put("RA_Facility__c", "a2jAq00000089anIAA");
        return createSA;
    }

    public static JSONObject CreateWOJson(){
        JSONObject createWO = new JSONObject();
        createWO.put("Subject", "Test");
        return createWO;
    }

    public static JSONObject UpdateCaseJson(){
        JSONObject createCase = new JSONObject();
        createCase.put("Origin", "Email");
        return createCase;
    }

    public static JSONObject UpdateServiceAppointmentJsonCriticalPriority(){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_CasePriority__c", "P1 Baby/Animal Locked in Car");
        return createSA;
    }

    public static JSONObject UpdateServiceAppointmentJsonFacility(){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_Facility__c", "a2jAq00000089anIAA");
        return createSA;
    }

    public static JSONObject UpdateServiceAppointmentJsonWo(String WorkOrder){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_WorkOrderId__c", WorkOrder);
        return createSA;
    }

    public static JSONObject UpdateServiceAppointmentJsonStatus(String status){
        JSONObject createSA = new JSONObject();
        createSA.put("Status", status);
        return createSA;
    }

    public static JSONObject UpdateServiceAppointmentJsonPriorityPriority(){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_CasePriority__c", "P4 Police on Scene");
        return createSA;
    }

    public static JSONObject UpdateServiceAppointmentJsonRegularCalls(){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_CasePriority__c", "Regular Call");
        return createSA;
    }
    public static JSONObject UpdateServiceAppointmentJsonHomeBasedCall(){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_CasePriority__c", "Home Based Call");
        return createSA;
    }

    public static JSONObject UpdateServiceAppointmentJsonLowPriority(){
        JSONObject createSA = new JSONObject();
        createSA.put("RA_CasePriority__c", "Key & Release (Member not in vehicle)");
        return createSA;
    }



}
