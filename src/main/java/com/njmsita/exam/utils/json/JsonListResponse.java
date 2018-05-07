package com.njmsita.exam.utils.json;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonListResponse<T> extends JsonResponse
{
    @JsonIgnore
    List<T> raw;
    @JsonIgnore
    String[] fields;
    @JsonIgnore
    List<Map<String, Object>> rows = new ArrayList<>();

    Map<String, JsonElement> elementMap = new HashMap<>();


    public JsonListResponse()
    {

    }


    public JsonListResponse(List<T> raw, String fields, int total)
    {

        serialize(raw, fields);
        this.put("total", total);
    }

    public JsonListResponse<T> addCustomJsonElementFormater(String key, CustomJsonElementFormater formater)
    {
        elementMap.get(key).formater=formater;
        return this;
    }

    public void setFields(String fieldString)
    {
        String[] fields = fieldString.split(",");
        for (String field : fields)
        {
            JsonElement element = new JsonElement(field);
            elementMap.put(element.key, element);
        }
    }


    public void serialize(List<T> raw, String fields)
    {
        this.raw = raw;
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        try
        {
            setFields(fields);
            for (T obj : raw)
            {
                Map<String, Object> row = new HashMap<>();
                for (JsonElement<T> element : this.elementMap.values())
                {
                    row.put(element.key, element.serialize(obj));
                }
                rows.add(row);
            }
            this.payload.put("rows", rows);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
