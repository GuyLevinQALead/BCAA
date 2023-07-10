package Workflows;

import Utilities.Operations;
import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestFlows extends Operations {
    static HttpGet httpGet;
    static String response_string;
    static int statusCode;
    //static String workOrderPath = "/sobjects/WorkOrder";

    @Step("Perform a switch to control the new tab opened")
    public static void ReadFromObjectAndStoreInList(String query, String columnName, Boolean isBooleanList){
        try{
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();
            String uri;
            if (isApexRequest){
                uri = baseLoginUrl + query;
            } else{
                uri = baseUri + query;
            }
            httpGet = new   HttpGet(uri);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);

            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject json = new JSONObject(response_string);
                    //               System.out.println("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    System.out.println(j);
                    if (isBooleanList){
                        for (int i = 0; i < j.length(); i++){
                            tempBooleanValue = json.getJSONArray("records").getJSONObject(i).getBoolean(columnName);
                            tempBooleanList.add(tempBooleanValue);
                        }
                    } else {
                        for (int i = 0; i < j.length(); i++){
                            tempObjectRecordName = json.getJSONArray("records").getJSONObject(i).getString(columnName);
                            tempObjectList.add(tempObjectRecordName);
                        }
                    }
                    System.out.println(tempBooleanList);
                    System.out.println(tempObjectList);
                } catch (JSONException je) {
                    System.out.println("failed due to : " + tempBooleanValue + tempObjectRecordName);
                    je.printStackTrace();
                }
            } else {
                System.out.println("Query was unsuccessful. Status code returned is " + statusCode);
                System.out.println("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        } catch (IOException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void CreateRecordJson(JSONObject jsonObject, String targetedObjectPath) {
        String uri;
        StringEntity body;
        if (isApexRequest){
            uri = baseLoginUrl + targetedObjectPath;
        } else{
            uri = baseUri + targetedObjectPath;
        }
        System.out.println(uri);
        try {
            //Construct the objects needed for the request
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader(oauthHeader);
            httpPost.addHeader(prettyPrintHeader);
            // The message we are going to post
            if (isApexRequest){
                body = new StringEntity("["+jsonObject.toString() +"]");
            } else{
                body = new StringEntity(jsonObject.toString());
            }
            System.out.println("Json " + jsonObject);
            body.setContentType("application/json");
            httpPost.setEntity(body);

            //Make the request
            HttpResponse response = httpClient.execute(httpPost);

            //Process the results
            statusCode = response.getStatusLine().getStatusCode();
            response_string = EntityUtils.toString(response.getEntity() );
            if (statusCode == 201 || statusCode == 200) {
                System.out.println("Successful response with code : " + statusCode);
            } else {
                System.out.println(" Error. Status code " + statusCode);
                currentTest.fail(response_string);
            }
            System.out.println("Response: " + response_string);
        } catch (JSONException e) {
            System.out.println("Issue creating JSON or processing results");
            e.printStackTrace();
        } catch (IOException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
        JSONObject json = new JSONObject(response_string);
        if (!isApexRequest){
            Operations.lastTestRecordId = json.getString("id");
        }
        Operations.lastTestResponse = statusCode;
    }

    public static void UpdateRecordJson(JSONObject jsonObject, String targetedObjectPath, String targetRecordId) {
        String uri;
        StringEntity body;
        if (isApexRequest){
            uri = baseLoginUrl + targetedObjectPath + "/" + targetRecordId;
        } else{
            uri = baseUri + targetedObjectPath + "/" + targetRecordId;
        }
        System.out.println(uri);
        try {
            System.out.println("Json " + jsonObject.toString(1));
            //Construct the objects needed for the request
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPatch httpPatch = new HttpPatch(uri);
            httpPatch.addHeader(oauthHeader);
            httpPatch.addHeader(prettyPrintHeader);
            if (isApexRequest){
                body = new StringEntity("["+jsonObject.toString() +"]");
            } else{
                body = new StringEntity(jsonObject.toString());
            }
            // The message we are going to post
            body.setContentType("application/json");
            httpPatch.setEntity(body);

            //Make the request
            HttpResponse response = httpClient.execute(httpPatch);

            //Process the results
            statusCode = response.getStatusLine().getStatusCode();
            //   response_string = EntityUtils.toString(response.getEntity());
            if (statusCode == 201 || statusCode == 200) {
                System.out.println("Successful update : " + statusCode);
                currentTest.pass(statusCode + "request sent Successfully");
            } else {
                System.out.println(response);
                System.out.println(" Error. Status code " + statusCode);
            }
            System.out.println("Response: " + response_string);
        } catch (JSONException e) {
            System.out.println("Issue creating JSON or processing results");
            e.printStackTrace();
        } catch (IOException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
        Operations.lastTestResponse = statusCode;
    }

    public static void DeleteRecordJson(String targetedObjectPath, String targetRecordId) {
        String uri = baseUri + targetedObjectPath + "/" + targetRecordId;
        System.out.println(uri);
        try {
            System.out.println("Delete Id : " + targetRecordId);

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.addHeader(oauthHeader);
            httpDelete.addHeader(prettyPrintHeader);
            // The message we are going to post

            //Make the request
            HttpResponse response = httpClient.execute(httpDelete);

            //Process the results
            statusCode = response.getStatusLine().getStatusCode();
            //   response_string = EntityUtils.toString(response.getEntity());
            if (statusCode == 201 || statusCode == 200 || statusCode == 204) {
                System.out.println("Successful Delete : " + statusCode);
                isRecordDeleted = true;
                currentTest.pass(statusCode + "request sent Successfully");
            } else {
                System.out.println(" Error. Status code " + statusCode);
                currentTest.fail(statusCode + response_string);
            }
            System.out.println("Response: " + response_string);
        } catch (JSONException e) {
            System.out.println("Issue creating JSON or processing results");
            e.printStackTrace();
        } catch (IOException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
        Operations.lastTestResponse = statusCode;
    }

    //get body is used on a failed read of a test
    private static String getBody(InputStream inputStream) {
        String result = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            String inputLine;
            while ( (inputLine = in.readLine() ) != null ) {
                result += inputLine;
                result += "\n";
            }
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    private static class HttpPatch extends HttpPost {
        public HttpPatch(String uri) {
            super(uri);
        }

        public String getMethod() {
            return "PATCH";
        }
    }

}
