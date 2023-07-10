package Utilities;

import Extensions.UIActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Listeners extends Operations implements ITestListener  {
	/*
        Listeners are functions which always react upon a given status of the test itself, this will allow us
        to take actions using code or extensions when a test has failed or passed or even when the test started or ended.
	 */

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("------------------ Starting test: " + iTestResult.getName() + "------------------");
        currentTest = extent.createTest("Test Name : "+iTestResult.getMethod().getDescription());
        if (RunOnceBeforeExecution){
            List<WebElement> tabs = driver.findElements(By.xpath("//button[@class='slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container']"));
            if (tabs.size() >= 1){
                for (WebElement tab : tabs){
                    UIActions.ClickAndWait(tab, "1");
                }
            }
            RunOnceBeforeExecution = false;
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if (isExperienceAsUser){
            driver.get(currentUrl);
        }
        if (isThreadInsideIframe){
            driver.switchTo().defaultContent();
            isThreadInsideIframe = false;
        }

        currentTest.pass(iTestResult.getName() + " Passed");
        System.out.println("------------------" + iTestResult.getName() + " Test Passed------------------");
        String srnshot = null;
        //Delete recorded file
        File file = new File("./test-recordings/" + iTestResult.getName() + ".avi");
        if (file.delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("Failed to delete file");
        }


    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("------------------ " + iTestResult.getName() + " Test Failed------------------");
        if (isThreadInsideIframe){
            driver.switchTo().defaultContent();
            isThreadInsideIframe = false;
        }
        if (isExperienceAsUser){
            driver.get(currentUrl);
            //Assert.fail("failed to login experience as a user");
        }

        try {
            if (iTestResult.getStatus() == ITestResult.FAILURE) {
                currentTest.log(Status.FAIL,
                        MarkupHelper.createLabel(iTestResult.getName()
                                        + " Test case FAILED due to below issues:",
                                ExtentColor.RED));
                String srnshotpath = takescreen(iTestResult);
                currentTest.fail(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(srnshotpath).build());
            } else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
                currentTest.log(
                        Status.PASS,
                        MarkupHelper.createLabel(iTestResult.getName()
                                + " Test Case PASSED", ExtentColor.GREEN));
                String srnshot = takescreen(iTestResult);
                currentTest.pass(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(srnshot).build());
            } else {
                currentTest.log(
                        Status.SKIP,
                        MarkupHelper.createLabel(iTestResult.getName()
                                + " Test Case SKIPPED", ExtentColor.ORANGE));
                currentTest.skip(iTestResult.getThrowable());
            }

       //     takescreen(iTestResult); //we want to capture image only on failures


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("------------------ Skipping test: " + iTestResult.getName() + "------------------");
        isTelusQATest = false;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //TODO implemenet in future
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("-------------- Starting Execution ---------------");
        if (isTelusQATest){
            currentTest.skip("incorrect env");
            throw new SkipException("Skipped");
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("-------------- Ending Execution -----------------");
        //TODO print test ticket number
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public String takescreen(ITestResult result) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        File localScreenshots = new File(new File("target"), "screenshots");

        if (!localScreenshots.exists() || !localScreenshots.isDirectory()) {
            localScreenshots.mkdirs();
        }

        File screenshot = new File(localScreenshots, result.getName()+timeStamp+ ".png");
        FileUtils.moveFile(imageFile, screenshot);

        System.out.println(screenshot.getAbsolutePath());

        String extentScrnshot =screenshot.getAbsolutePath(); //I think its better to capture the test name in the image rather than current time

        return extentScrnshot;
    }
}
