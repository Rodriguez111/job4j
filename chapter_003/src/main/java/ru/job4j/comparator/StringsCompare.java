package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Comparator for string values.
 */
public class StringsCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        char[] string1 = o1.toCharArray();
        char[] string2 = o2.toCharArray();
        int minLength = o1.length() < o2.length() ?  o1.length() : o2.length();
        int result = 0;
        for (int i = 0; i < minLength; i++) {
            result = Character.compare(string1[i], string2[i]);
            if (result != 0) {
                break;
            }
        }
        if (result == 0) {
           result =   o1.length() == o2.length() ? 0 : o1.length() < o2.length() ? -1 : 1;
        }
        return result;
    }


}
