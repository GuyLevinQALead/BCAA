package Utilities;

import org.json.JSONObject;

public class JsonPayloads extends Operations {
    public static String path_Case = "/sobjects/Case";
    public static String path_ServiceAppointment = "/sobjects/ServiceAppointment";

    public static JSONObject CreateCaseJson(){
        JSONObject createCase = new JSONObject();
        createCase.put("Origin", "Phone");
        createCase.put("RecordTypeId", "012Aq000000FNabIAG");
        return createCase;
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
