package com.example.numbers.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

    public static Integer[] getIntArrayFromString(String text) {
        String[] textS = text.split(" ");
        Integer[] result = new Integer[textS.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(textS[i]);
        }

        return result;

    }

    public static Map groupArrays(Integer[] brr) {
        Map map = new LinkedHashMap();
        for(int i = 0; i < brr.length; i++) {
            if(map.get(brr[i]) == null) {
                map.put(brr[i], 1);
            } else {
                Integer cantidad = (Integer)map.get(brr[i]);
                cantidad ++;
                map.put(brr[i], cantidad);
            }
        }
        return map;
    }

    public static Map getMapFromString(String text) {
        Integer[] intArray = Util.getIntArrayFromString(text);
        return groupArrays(intArray);
    }
}
