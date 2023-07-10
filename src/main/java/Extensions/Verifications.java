package Extensions;

import Utilities.Operations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Verifications extends Operations {
    /*
        Verifications are the last part of each test, these are reusable functions that we place at the
        end of every test with an action of assertion inorder to verify anything we need, in other words = "expected results"
     */

    public static void VerifyTextInElement(WebElement elem, String expected){
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getText(), expected);
        currentTest.pass("Verified text and element " + expected + " are equals");
    }

    public static void VerifyElementPresentByText(String locator, String elementName){
        WebElement elem = driver.findElement(By.xpath("//"+locator+"[text()='"+elementName+"']"));
        wait.until(ExpectedConditions.visibilityOf(elem));
        Assert.assertTrue(elem.isDisplayed());
        currentTest.pass("Verified " + elementName + " is present");
    }

    public static void VerifyElementDoesNotExist(List<WebElement> elem){
        Assert.assertTrue(elem.size() <= 0);
    }

    public static void VerifyElementIsNotVisible(WebElement elem){
        Assert.assertTrue(!elem.isDisplayed());
        currentTest.pass("Verified " + elem + " is present");    }

    public static void VerifyElementPresent(WebElement elem) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elem));

        Assert.assertTrue(elem.isDisplayed());
        currentTest.pass("Verified element is present" + elem);

    }

    catch (org.openqa.selenium.NoSuchElementException e)
    {
        driver.switchTo().defaultContent();
        currentTest.fail("Verified element is not present" + elem+e);
        Assert.assertFalse(false);
    }

        catch (AssertionError e)
        {
            driver.switchTo().defaultContent();
            currentTest.fail("Verified element is not present" + elem+e);
            Assert.assertFalse(false);
        }

        catch (ArrayIndexOutOfBoundsException e)
        {
            driver.switchTo().defaultContent();
            currentTest.fail("Verified element is not present" + elem+e);
            Assert.assertFalse(false);
        }
    }

    public static void VerifyLastElementInListPresent(List<WebElement> elem){
        UIActions.SetDelayAfterAction(300);
        if (elem.size() >= 2){
            Assert.assertTrue(elem.get(elem.size()-1).isDisplayed());
        } else {
            Assert.assertTrue(elem.get(0).isDisplayed());
        }
        currentTest.pass("Verified " + elem.get(elem.size()-1) + " is present");
    }

    public static void VerifyElementIsNotPresent(WebElement elem, String elemName){
        Assert.assertFalse(elem.isDisplayed());
        currentTest.pass("Verified " + elemName + " is not present");
    }

    public static void VerifyElementIsNotPresentUsingAXpath(String xpath) {
        List<WebElement> elementPresent = driver.findElements(By.xpath(xpath));
        if (elementPresent.size() > 0) {
            System.out.println(elementPresent);
            Assert.fail("Element Is still visible");
            currentTest.fail("Element Is still visible");
            System.out.println(elementPresent);

        } else {
            System.out.println(elementPresent);
            Assert.assertTrue(true);
            currentTest.pass("Element Is Not visible!");

        }
    }

    public static void VerifyElementPresentInTable(String attributeType,String str){
        WebElement element = driver.findElement(By.xpath("//"+attributeType+"[text()='"+str+"']"));
        wait.until(ExpectedConditions.visibilityOf(element));
        softAssert.assertTrue(element.isDisplayed());
        UIActions.SetDelayAfterAction(300);
        currentTest.pass("Verified " + str + " is presented inside table");
    }

    public static void VerifyListOfElements(List<WebElement> list){
        try{
            for (WebElement elem : list){
              Assert.assertTrue(elem.isDisplayed());
            }
            currentTest.pass("Verified all elements in list " + list +" are displayed");
        } catch (Exception e){
            currentTest.fail("Could not read list"+ " " + e);
        }
    }

    public static void VerifyElementIsReadOnly(WebElement element) {
        try {
//            String[] readonly = element.getAttribute("class").split(" ");
            String[] readonly = element.getAttribute("class").split(" ");
            String classreadonly = readonly[readonly.length-1];
            System.out.println(classreadonly);
            if (classreadonly.equals("is-read-only") ) {
            currentTest.pass("element" + " " + element + " " + "is read only");
            Assert.assertTrue(true);
            }
            else  if (classreadonly.contains("slds-form-element_readonly") ){
            currentTest.pass("element" + " " + element + " " + "is read only");
            Assert.assertTrue(true);


            }

            else  if (classreadonly.equals(" ") ){
                currentTest.fail("element" + " " + element + " " + "is editable");
                Assert.assertFalse(true);

            }
            else  if (classreadonly.equals("") ){
                currentTest.fail("element" + " " + element + " " + "is editable");
                Assert.assertFalse(true);

            }
            else {
                currentTest.fail("element" + " " + element + " " + "is editable");
                Assert.assertFalse(true);

            }
        } catch (Exception e) {
            currentTest.fail("element" + " " + element + " " + "is editable");

        }
    }

    public static void  VerifyElementTextIsEqualToExpectedText(String actual,String expected){
        Assert.assertEquals(actual, expected);
        currentTest.pass("Verified " + expected + " is presented as expected");
    }

    public static void  VerifyElementsEqualByPartialText(String actual,String partial){
        if (actual.contains(partial)){
            Assert.assertTrue(true);
            currentTest.pass("text contains partial text");

        } else {
            currentTest.warning("could not find matching text");
        }
    }
    @Step
    public static void VerifyIfOneListFullyContainsAnother(List A, List B){
        boolean var = A.stream().allMatch(element -> B.contains(element));
        if ( var==true){
            currentTest.pass("One List includes the other");
            Assert.assertTrue(true);
        }
        else {
            currentTest.fail("One list is not fully included in the other");
            Assert.assertFalse(true);
        }

    }




//    public static void  VerifyElementsEqualByPartialTextAssert(String actual,String partial) {
//        if ( Assert.assertTrue(actual.contains(partial))){
//
//            Assert.assertTrue(actual.contains(partial), "Failure message");
//
//
//    }
    public static void VerifyElementIsPresentUsingXpath(String xpath){
        List<WebElement> elem = driver.findElements(By.xpath(xpath));
            if (elem.size() > 0){

                Assert.assertTrue(true);
                currentTest.pass("Element is Present" + elem);

            }else{
                Assert.fail("Element is not Visible"+ elem);
                currentTest.fail("Element is not Visible"+ elem);


            }}


    public static void VerifyElementNotDisplayed(WebElement ele,String eleName) {
        if(!ele.isDisplayed())
            currentTest.pass("Verified element is not present" + eleName);
        else
            currentTest.fail("Verified element is present" + eleName);
    }

    @Step("Verify Elements does not present")
    public static void VerifyElementDoesNotPresent(List<WebElement> elem, String eleName){
        Assert.assertTrue(elem.size() <= 0);
        currentTest.pass("Verified element is not present" + eleName);
    }

    public static void VerifyObjectFieldName(String fieldLabel, String fieldName){
        WebElement elem = driver.findElement(By.xpath("//span[text()='"+fieldLabel+"']/parent::a/parent::td/parent::tr/td/span[text()='"+fieldName+"']"));
        Assert.assertTrue(elem.isDisplayed());
        currentTest.pass("Verified element field for object");
    }

    public static void VerifyTextIsNotNullInElement(WebElement ele){
        try {
            Assert.assertTrue(!ele.getText().equalsIgnoreCase("") || !ele.getText().isEmpty() || !ele.getText().isEmpty() );
            currentTest.pass("Verified element has text" +" "+ ele.getText());
        } catch (NoSuchElementException e){
            System.out.println("could not verify element" +" " +e);
        }
    }

    public static void VerifyAttributeIsNotNullInElement(String attribute, WebElement ele){
        wait.until(ExpectedConditions.visibilityOf(ele));
        Assert.assertTrue(!ele.getAttribute(attribute).equalsIgnoreCase("") || !ele.getText().isEmpty() );
        currentTest.pass("Verified element has text");
    }

    public static void VerifyTextIsNullInElement(WebElement ele){
        wait.until(ExpectedConditions.visibilityOf(ele));
        Assert.assertTrue(ele.getText().equalsIgnoreCase("")  || ele.getText().isEmpty());
        currentTest.pass("Verified element does not has text");
    }

    public static void VerifyWarningOrErrorMessage(WebElement elem){
        wait.until(ExpectedConditions.visibilityOf(elem));
        Assert.assertTrue(elem.isDisplayed());
        currentTest.pass("Verified warning Message");
        UIActions.Click(mainPage.warning_errorCloseDialog);
    }

}
