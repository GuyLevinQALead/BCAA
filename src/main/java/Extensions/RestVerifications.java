package Extensions;

import Utilities.Operations;
import Utilities.ReadCsv;
import Workflows.WebFlows;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestVerifications extends Operations {
    public static List<String> csvObjectTempList = new ArrayList<>();

    public static void VerifyValueInElement(String expectedValue, String query, String element){
        Assert.assertEquals(expectedValue, RestActions.GetStringleCellStringValue(query, element));
    }

    public static void VerifyValueInsideRecordCell(String expectedValue, String actualJsonValue){
        try{
            Assert.assertEquals(expectedValue, actualJsonValue);
            System.out.println("Values are equal");
        }catch (Exception e){
            currentTest.fail("could not find " + expectedValue);
        }
    }


    public static void VerifyRecordCreated() {
        if (lastTestResponse == 201 || lastTestResponse == 200 && lastTestRecordId != "") {
            System.out.println("Successful response with code : " + lastTestResponse);
            currentTest.pass("Successful response with code : " + lastTestResponse);
        }
    }

    public static void VerifyListContainsTrueBooleanValue(List<Boolean> list){
        for(int i = 0; i < list.size(); i++) {
            Assert.assertTrue(list.get(i).equals(true));
        }
    }

    public static void VerifyListOfElementOnObjectCSV(List<String> recordList, ReadCsv.OBJECTS objValue){
        //read from the CSV the expected record in the objects and compare the actual recordList received from the API request
        try {
            CSVReader reader = new CSVReader(new FileReader(WebFlows.strFile));
            String [] nextLine ;
            int lineNumber = objValue.getValue();
            nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                csvObjectTempList.add(nextLine[lineNumber]);
            }
            csvObjectTempList.removeAll(recordList);
            if (!csvObjectTempList.isEmpty()){
                System.out.println(csvObjectTempList.removeAll(recordList));
                System.out.println("CSV list" + csvObjectTempList);
                System.out.println("record list" + recordList);
            }
            Assert.assertTrue(csvObjectTempList.isEmpty());

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void VerifyRecordHasBeenDeleted(){
        Assert.assertTrue(isRecordDeleted);
        currentTest.pass("record deleted");
    }

}
