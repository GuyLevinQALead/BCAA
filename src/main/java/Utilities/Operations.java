package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
    commonOps class will handle all the instantiate and terminate of a new Test Run
    These will include handling before and after annotations, instantiate the correct platform and enviornment
 */
public class Operations extends Base {

    public static ReadData readData = new ReadData();
    public static ExtentTest currentTest = extent.createTest("before");
    public static boolean isThreadInsideGantt;
    public static boolean isAdminExecute;
    public static boolean isApiTest;
    public static boolean isTelusQATest = false;
    public static String currentUrl;
    public static String strFile = "./TestCsv.csv"; //CSV file location
    public static boolean isExperienceAsUser = false;
    public static String mainWindowHandle;
    public static boolean isSetupCurrentTab = false;
    public static boolean isThreadInsideIframe;
    public static String currentResourceRelatedID = ""; //while executing we sometimes need the resourceRelatedID to locate elements
    public static boolean RunOnceBeforeExecution;

    //RETS API values
    public static String REST_USERNAME, REST_PASSWORD, REST_URL, GRANTSERVICE, CLIENTID, CLIENTSECRET, REST_ENDPOINT, API_VERSION;
    public static String baseUri;
    public static String baseLoginUrl;
    public static Header oauthHeader;
    public static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
    public static String leadId ;
    public static HttpPost httpPost;
    public static HttpClient httpclient;
    public static int statusCode;
    public static String response_string;
    public static String tempObjectRecordName;
    public static boolean tempBooleanValue;
    public static String createdRecordID;
    public static boolean isApexRequest = false;
    public static String exmapleOfQuery = "/query?q=Select+TLS_FrenchProductName__c+From+Product2";

    public static int lastTestResponse;
    public static String lastTestRecordId;
    public static String lastTechnicianId;

    //flags
    public static Boolean isRecordDeleted = false;
    public static Boolean newTabRecordCreated = false;

    public static List<String> tempObjectList = new ArrayList<>();
    public static List<Boolean> tempBooleanList = new ArrayList<>();



    //Every time we initiate the browser command we can select different browser by string and the automation will initiate the specified driver
    public void initBrowser(String browserType) {
        if (browserType.equalsIgnoreCase(readData.GetData("Chrome"))) {
            driver = initChromeDriver();
        } else {
            throw new RuntimeException("Invalid browser");
        }
        //Seperate test profiles to different classes each will create its own report
        if (isAdminExecute){
            htmlReporter = new ExtentSparkReporter("ExtentReports/extentReport" + dtf.format(localDate) +"Admin.html");
        } else {
            htmlReporter = new ExtentSparkReporter("ExtentReports/extentReport" + dtf.format(localDate) +"Dispatcher.html");
        }
        extent.attachReporter(htmlReporter);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 15);
        driver.get(currentUrl);
        PageManager.InitiatePages();
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        js = (JavascriptExecutor) driver;
    }


    public static WebDriver initChromeDriver(){
        ChromeOptions p = new ChromeOptions();
        p.addArguments("--disable-notifications"); //disables 3rd party notifications
        p.addArguments("disable-extensions"); //disables 3rd party extensions
        p.addArguments("--no-sandbox");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(p);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static void initAPI(){
        htmlReporter = new ExtentSparkReporter("ExtentReports/extentReport" + dtf.format(localDate) +"Integration.html");
        htmlReporter.config().setDocumentTitle("Text-Automation");
        htmlReporter.config().setReportName("Regression");
        htmlReporter.config().setTheme(Theme.DARK);
        extentReport = new ExtentReports();
        extentReport.attachReporter(htmlReporter);
        extentReport.setSystemInfo("Environment","TEST");
        extentReport.setReportUsesManualConfiguration(true);
        extent.attachReporter(htmlReporter);

        //these credentials are same for all env.
        REST_URL = readData.GetData("RestUrl");
        GRANTSERVICE = readData.GetData("GrantService");
        REST_ENDPOINT = readData.GetData("Rest_EndPoint");
        API_VERSION = readData.GetData("API_Version");
        //these credentials are environment specific
        REST_USERNAME = readData.GetData("RestUserName");
        REST_PASSWORD = readData.GetData("RestPassword");
        CLIENTID = readData.GetData("ClientID");
        CLIENTSECRET = readData.GetData("ClientSecret");
    }

//    public static void InitiateAPIConnection(){
//        //Before Class method runs one time before every execution (not before every test)
//        isApiTest = true;
//        initAPI(); //Method in commonOps to initiate all variables of the target environment
//        httpclient = HttpClientBuilder.create().build();
//        String loginURL = REST_URL +
//                GRANTSERVICE +
//                "&client_id=" + CLIENTID +
//                "&client_secret=" + CLIENTSECRET +
//                "&username=" + REST_USERNAME +
//                "&password=" + REST_PASSWORD;
//        System.out.println("login url :" + loginURL);
//
//        // Login requests must be POSTs
//        httpPost = new HttpPost(loginURL);
//        HttpResponse response = null;
//
//        // Execute the login POST request
//        try {
//            response = httpclient.execute(httpPost);
//            System.out.println("response : " + response);
//        } catch (IOException cpException) {
//            cpException.printStackTrace();
//        }
//
//        // verify response is HTTP OK - must be 200 otherwise will fail to connect.
//        final int statusCode = response.getStatusLine().getStatusCode();
//        if (statusCode != HttpStatus.SC_OK) {
//            System.out.println("Error authenticating to Force.com: "+statusCode);
//            // Error is in EntityUtils.toString(response.getEntity())
//            return;
//        }
//
//        //store the response received in a getResult string
//        String getResult = null;
//        try {
//            getResult = EntityUtils.toString(response.getEntity());
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        //define JSON Object and the access token that will be used to fetch the retrieved information.
//        JSONObject jsonObject;
//        String loginAccessToken = null;
//        String loginInstanceUrl = null;
//
//        try {
//            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
//            loginAccessToken = jsonObject.getString("access_token");
//            loginInstanceUrl = jsonObject.getString("instance_url");
//        } catch (JSONException jsonException) {
//            jsonException.printStackTrace();
//        }

        public static void InitiateAPIConnectionGuy(){
            //Before Class method runs one time before every execution (not before every test)
            initAPI(); //Method in commonOps to initiate all variables of the target environment
            httpclient = HttpClientBuilder.create().build();
            String loginURL = REST_URL +
                    GRANTSERVICE +
                    "&client_id=" + CLIENTID +
                    "&client_secret=" + CLIENTSECRET +
                    "&username=" + REST_USERNAME +
                    "&password=" + REST_PASSWORD;
            System.out.println("login url :" + loginURL);

            // Login requests must be POSTs
            httpPost = new HttpPost(loginURL);
            HttpResponse response = null;

            // Execute the login POST request
            try {
                response = httpclient.execute(httpPost);
                System.out.println("response : " + response);
            } catch (IOException cpException) {
                cpException.printStackTrace();
            }

            // verify response is HTTP OK - must be 200 otherwise will fail to connect.
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Error authenticating to Force.com: "+statusCode);
                // Error is in EntityUtils.toString(response.getEntity())
                return;
            }

            //store the response received in a getResult string
            String getResult = null;
            try {
                getResult = EntityUtils.toString(response.getEntity());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            //define JSON Object and the access token that will be used to fetch the retrieved information.
            JSONObject jsonObject;
            String loginAccessToken = null;
            String loginInstanceUrl = null;

            try {
                jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
                loginAccessToken = jsonObject.getString("access_token");
                loginInstanceUrl = jsonObject.getString("instance_url");
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }

            baseLoginUrl = loginInstanceUrl;
            baseUri = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
            oauthHeader = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
            System.out.println("oauthHeader1: " + oauthHeader);
            System.out.println("\n" + response.getStatusLine());
            System.out.println("Successful login");
            System.out.println("instance URL: "+loginInstanceUrl);
            System.out.println("access token/session ID: "+loginAccessToken);
            System.out.println("baseUri: "+ baseUri);
        }


    @BeforeClass() //Before Class method runs one time before every execution (not before every test)
    public void InitiateApiConnectionOnce(){
        isApiTest = false;
//        InitiateAPIConnection();
        isThreadInsideGantt = false;
    }

    @AfterClass
    public void TerminateSession(){
        extent.flush(); //Generate extent report
        if (!isApiTest){
//            driver.close();
//            driver.quit();
        }
        //SendReport sendReport = new SendReport();
        //sendReport.SendEmailReport();
    }

}
