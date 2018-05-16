package com.njmsita.exam.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionUtil
{
    public static String[] toOptionList(String string) throws IOException
    {
        String[] result = CustomJsonSerializer.getDefaultMapper().readValue(string, String[].class);
        return result;
    }

    public static String toOptionString(String[] options)
    {
        Map<String, String> map = new HashMap<>();
        int i = 0;
        for (String option : options)
        {
            map.put("" + i, option);
            i++;
        }
        return CustomJsonSerializer.toJsonString_static(map);
    }
}
