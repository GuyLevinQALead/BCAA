package Extensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contains {
    public static void main (String[] args){
        String s = "1440 Sunderland Avenue Silver Spring California 20906 United States";
        String f = "1440 Sunderland Avenue Silver Spring United States";
        List<String> items = new ArrayList<>(Arrays.asList(s.split(" ")));
        List<String> items1 = new ArrayList<>(Arrays.asList(f.split(" ")));

        System.out.println(items);
        System.out.println(items1);


        boolean var = items1.stream().allMatch(element -> items.contains(element));
        System.out.println(var);


        boolean b = items.retainAll(items1);
        System.out.println(b);

        boolean noElementsInCommon = Collections.disjoint(items, items1); // true

        System.out.println(noElementsInCommon);


    }
}
