package Utilities;

public class ReadCsv {
    /*
        Object Values are a constant values that represent each object in salesforce we need to read its fields for testing.
        each object is assigned the appropriate number position inside "TestCsv.csv" file starting with 0.
        we use this method to read large number of values such as fields and compare them for example
     */

    public enum OBJECTS{
        MOTORCYCLE_TYPE(0),
        MOTORCYCLE_ENGINE_TYPE(3),
        CALL_TYPE(1),
        CASE_ORIGIN(2);
        private final int value;

        OBJECTS(final int newValue){
            value = newValue;
        }
        public  int getValue(){return value;}
    }
}
