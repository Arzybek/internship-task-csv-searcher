package com.intern.testtask;

import java.util.ArrayList;
import java.util.List;

public class CSVSplitter {

    public static List<String> SplitCSV(String line)
    {
        List<String> result = new ArrayList<String>();
        StringBuilder currentStr = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++)
        {
            if (line.charAt(i) == '\"')
                inQuotes = !inQuotes;
            else if (line.charAt(i) == ',')
            {
                if (!inQuotes) {
                    result.add(currentStr.toString());
                    currentStr = new StringBuilder();
                }
                else currentStr.append(line.charAt(i));
            }
            else currentStr.append(line.charAt(i));
        }
        result.add(currentStr.toString());
        return result;
    }
}
