package com.nagp.selenium.DataFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

// Data converters For different types
public final class DataConverters {
    //getting list of integers in string
    public static List<Integer> GetAllIntegersFromString(String line)
    {
        line = line.replaceAll(",", "");
        line = line.replaceAll("[^\\d]", " ");
        line = line.trim();
        List<Integer> integerList = new ArrayList<Integer>();
        Arrays.stream(line.split(" ")).forEach(i->integerList.add(Integer.parseInt(i)));
        return  integerList;
    }

    //getting float value from string
    public static float GetFirstFloatFromString(String line)
    {
        line = line.replaceAll(",", "");
        line =line.replaceAll("[^\\d.]+|\\.(?!\\d)", "");
        List<Float> floatList = new ArrayList<Float>();
        Arrays.stream(line.split(" ")).forEach(i->floatList.add(Float.parseFloat(i)));
        return floatList.get(0);
    }

    //getting upper value from integerlist
    public static int GetUpperValue(List<Integer> integerList)
    {
        if(integerList.size()==1)
        {
            return integerList.get(0);
        }
        return integerList.get(1);
    }

    //getting lower value from integerlist
    public static int GetLowerValue(List<Integer> integerList)
    {
        if(integerList.size()==1)
        {
            return 0;
        }
        return integerList.get(0);
    }
    // Getting product of 2 float values
    public static float GetProductOfValues(float value1,float value2)
    {
        return value1*value2;
    }
    // get float value for string
    public static float GetFloatValue(String value)
    {
        try {
            value = value.replaceAll("[^\\d.]+|\\.(?!\\d)", "");
            return Float.parseFloat(value);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }
    //getting long value from string
    public static long GetLongValue(String value)
    {
        try {
            value = value.replaceAll("[^\\d.]+|\\.(?!\\d)", "");
            return Long.parseLong(value);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    //getting int value from string
    public static int GetIntegerValue(String value) {
        try {
        value =value.replaceAll("[^\\d.]+|\\.(?!\\d)", "");
        return Integer.parseInt(value);
         }
        catch (NumberFormatException e){

            return 0;
        }
    }

    //Contains With case insensitivity = true
    public static boolean ContainsWithCaseInsensitive(String source, String substring)
    {
        return Pattern.compile(Pattern.quote(substring), Pattern.CASE_INSENSITIVE).matcher(source).find();
    }
}
