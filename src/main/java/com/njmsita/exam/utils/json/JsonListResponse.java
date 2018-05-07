package com.njmsita.exam.utils.json;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public JsonListResponse(String fields)
    {
        this.setFields(fields);
    }


    public JsonListResponse(List<T> raw, String fields, int total,boolean withMoreOptions)
    {
        this.raw=raw;
        this.setFields(fields);
        if (!withMoreOptions)
        {
            serialize();
        }
        this.put("total", total);
    }

    public JsonListResponse(List<T> raw, String fields, int total)
    {
        this.raw=raw;
        this.setFields(fields);
        serialize();
        this.put("total", total);
    }

    public JsonListResponse<T> addCustomJsonElementFormater(String key, CustomJsonElementFormater<T> formater)
    {
        elementMap.get(key).formater = formater;
        return this;
    }

    public JsonListResponse<T> addNullValue(String key, Object value)
    {
        elementMap.get(key).nullValue = value;
        return this;
    }

    public JsonListResponse<T> setFields(String fieldString)
    {
        String[] fields = fieldString.split(",");
        for (String field : fields)
        {
            JsonElement element = new JsonElement(field);
            elementMap.put(element.key, element);
        }
        return this;
    }

    public JsonListResponse<T> serialize()
    {
        try
        {
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
        return this;
    }

}
