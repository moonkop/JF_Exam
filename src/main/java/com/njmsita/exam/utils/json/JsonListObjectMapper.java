package com.njmsita.exam.utils.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonListObjectMapper<T> extends JsonObjectMapper<T>
{

    public JsonListObjectMapper()
    {

    }

    public List<Map<String, Object>> serializeList(List<T> raw)
    {
        List<Map<String, Object>> rows = new ArrayList<>();
        if (raw != null)
        {
            try
            {
                for (T obj : raw)
                {
                    Map<String, Object> row = this.serializeObject(obj);
                    rows.add(row);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return rows;
    }

    @Override
    public JsonListObjectMapper<T> setFields(String fieldString)
    {
        super.setFields(fieldString);
        return this;
    }

    @Override
    public JsonListObjectMapper<T> addNullValue(String key, Object value)
    {
        super.addNullValue(key, value);
        return this;
    }

    @Override
    public JsonListObjectMapper<T> addCustomJsonElementFormatter(String key, CustomJsonElementFormatter<T> formatter)
    {
        super.addCustomJsonElementFormatter(key, formatter);
        return this;
    }
}
