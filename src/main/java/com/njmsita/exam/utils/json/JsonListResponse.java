package com.njmsita.exam.utils.json;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonListResponse<T> extends JsonObjectResponse<T>
{
    @JsonIgnore
    protected List<T> raw;
    @JsonIgnore
    protected List<Map<String, Object>> rows = new ArrayList<>();

    public JsonListResponse()
    {

    }


    public JsonListResponse(String fields)
    {
        this.setFields(fields);
    }

    public JsonListResponse(List<T> raw, String fields, int total, boolean withMoreOptions)
    {
        this.raw = raw;
        this.setFields(fields);
        if (!withMoreOptions)
        {
            this.serialize();
        }
        this.put("total", total);
    }


    public JsonListResponse(List<T> raw, String fields, int total)
    {
        this(raw, fields, total, false);
    }

    public JsonListResponse<T> setRaw(List<T> raw)
    {
        this.raw = raw;
        return this;
    }

    @Override
    public JsonListResponse<T> addCustomJsonElementFormater(String key, CustomJsonElementFormater<T> formater)
    {
        super.addCustomJsonElementFormater(key, formater);
        return this;
    }

    @Override
    public JsonListResponse<T> addNullValue(String key, Object value)
    {
        super.addNullValue(key, value);
        return this;
    }

    @Override
    public JsonListResponse<T> setFields(String fieldString)
    {
        super.setFields(fieldString);
        return this;
    }

    public JsonListResponse<T> serialize()
    {
        try
        {
            for (T obj : raw)
            {
                Map<String, Object> row = this.serializeObject(obj);
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
