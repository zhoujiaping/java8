package java8.chapter1lambda;

import java.util.Comparator;

public class LengthComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        //return o1.length()-o2.length();//可能会导致数值溢出bug
        return Integer.compare(o1.length(), o2.length());
    }

}
