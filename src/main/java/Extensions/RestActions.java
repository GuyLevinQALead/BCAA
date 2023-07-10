package Extensions;

import Utilities.Operations;
import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RestActions extends Operations {

    static HttpGet httpGet;

    @Step("Get Data From Server")
    public static String GetStringleCellStringValue(String query, String element){
        try {
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();
            String uri;
            if (isApexRequest){
                uri = baseLoginUrl + query;
            } else{
                uri = baseUri + query;
            }
            httpGet = new HttpGet(uri);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);

            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(response_string);
                JSONArray j = json.getJSONArray("records");
                System.out.println(j);
                return j.getJSONObject(0).getString(element);
            } else{
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        }
        catch (IOException ioe) {
            System.out.println("could not connect");
            ioe.printStackTrace();
        }
        return null;
    }

    public static String GetValueFromSpecificRecordCell(String object,String column, String whereField, String whereValue  ){
        try {
            String query = "/query?q=Select+"+column+"+From+"+object+"+where+"+whereField+"="+"'"+whereValue+"'";
            System.out.println(query);
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();
            String uri = baseUri + query;
            httpGet = new HttpGet(uri);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);

            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(response_string);
                JSONArray j = json.getJSONArray("records");
                System.out.println(j.getJSONObject(0));
                if (j.getJSONObject(0).isNull(column)){
                    return "";
                }else{
                    return j.getJSONObject(0).getString(column);
                }
            } else{
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        }
        catch (IOException ioe) {
            System.out.println("could not connect");
            ioe.printStackTrace();
        }
        return null;
    }
    //This function can get a certain field value for all records of a certain object
    public static List<String> GetListFromSpecificObject(String column,String object) {
        try {
                String query = "/query?q=Select+" + column + "+From+" + object + "";
                //Set up the HTTP objects needed to make the request.

                HttpClient httpClient = HttpClientBuilder.create().build();
                String uri = baseUri + query;
                httpGet = new HttpGet(uri);
                httpGet.addHeader(oauthHeader);
                httpGet.addHeader(prettyPrintHeader);

                // Make the request.
                HttpResponse response = httpClient.execute(httpGet);
                // Process the result
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String response_string = EntityUtils.toString(response.getEntity());
                    JSONObject json = new JSONObject(response_string);
                    JSONArray j = json.getJSONArray("records");
                    List<String> columnValues = new ArrayList<>();
                    for (int i = 0; i < j.length(); i++) {
                        JSONObject record = j.getJSONObject(i);
                        String value = record.optString(column);
                        columnValues.add(value);
                    }
                    return columnValues;

                } else {
                    System.out.println(getBody(response.getEntity().getContent()));
                    System.exit(-1);
                }
            } catch (IOException ioe) {
                System.out.println("could not connect");
                ioe.printStackTrace();
            }
            return null;
        }


    public static Double GetFloatValueFromSpecificRecordCell(String object,String column, String whereField, String whereValue  ){
        try {
            String query = "/query?q=Select+"+column+"+From+"+object+"+where+"+whereField+"="+"'"+whereValue+"'";
            System.out.println(query);
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();
            String uri = baseUri + query;
            httpGet = new HttpGet(uri);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);

            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(response_string);
                JSONArray j = json.getJSONArray("records");
                return j.getJSONObject(0).getDouble(column);
            } else{
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        }
        catch (IOException ioe) {
            System.out.println("could not connect");
            ioe.printStackTrace();
        }
        return null;
    }


    public static Boolean GetBooleanValueFromSpecificRecordCell(String object,String column, String whereField, String whereValue  ){
        try {
            String query = "/query?q=Select+"+column+"+From+"+object+"+where+"+whereField+"="+"'"+whereValue+"'";
            System.out.println(query);
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();
            String uri = baseUri + query;
            httpGet = new HttpGet(uri);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);

            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(response_string);
                JSONArray j = json.getJSONArray("records");
                return j.getJSONObject(0).getBoolean(column);
            } else{
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        }
        catch (IOException ioe) {
            System.out.println("could not connect");
            ioe.printStackTrace();
        }
        return null;
    }

    public static String GenerateQuery(String value, String fromTable, String whereField, String whereValue){
        String query = "/query?q=Select+"+value+"+from+"+fromTable+"+where+"+whereField+"+like+'"+whereValue+"'";
        System.out.println(query);
        return query;
    }
    public static void UpdateRecordFromJson(JSONObject jsonObject, String targetedObjectPath, String targetId) {
        String uri = baseUri + targetedObjectPath + "/" + targetId;
        System.out.println(uri);
        try {
            //Construct the objects needed for the request
            httpclient = HttpClientBuilder.create().build();
            HttpPatch httpPatch = new HttpPatch(uri);
            httpPatch.addHeader(oauthHeader);
            httpPatch.addHeader(prettyPrintHeader);
            // The message we are going to post
            StringEntity body = new StringEntity(jsonObject.toString());
            System.out.println("Json " + jsonObject);
            body.setContentType("application/json");
            httpPatch.setEntity(body);

            //Make the request
            HttpResponse response = httpclient.execute(httpPatch);

            //Process the results
            statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 201 || statusCode == 200 || statusCode == 204) {
                System.out.println("Successful response with code : " + statusCode);
                currentTest.pass("Update request " + response_string + " is successful");
            } else {
                System.out.println(" Error. Status code " + statusCode);
                currentTest.fail(response_string);
            }
            System.out.println("Response: " + response_string);
        } catch (JSONException e) {
            System.out.println("Issue updating JSON or processing results");
            e.printStackTrace();
        } catch (IOException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void CreateRecordFromJson(JSONObject jsonObject, String targetedObjectPath) {
        String uri = baseUri + targetedObjectPath;
        System.out.println(uri);
        try {
            //Construct the objects needed for the request
            httpclient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(uri);
            httpPost.addHeader(oauthHeader);
            httpPost.addHeader(prettyPrintHeader);
            // The message we are going to post
            StringEntity body = new StringEntity(jsonObject.toString());
            System.out.println("Json " + jsonObject);
            body.setContentType("application/json");
            httpPost.setEntity(body);

            //Make the request
            HttpResponse response = httpclient.execute(httpPost);

            //Process the results
            statusCode = response.getStatusLine().getStatusCode();
            response_string = EntityUtils.toString(response.getEntity() );
            if (statusCode == 201 || statusCode == 200) {
                JSONObject json = new JSONObject(response_string);
                createdRecordID = json.getString("id");
                currentTest.info("Created record from request - ID : " + createdRecordID);
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
    }

    @AfterClass
    public static void AfterApiExecution(){
        //perform a connection release on end of all tests
        httpPost.releaseConnection();
    }

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
}
