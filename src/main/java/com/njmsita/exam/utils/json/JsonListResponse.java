package com.njmsita.exam.utils.json;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;
import java.util.Map;

public class JsonListResponse<T> extends JsonResponse
{
    @JsonIgnore
    protected List<T> raw;
    @JsonIgnore
    protected JsonListObjectMapper<T> listMapper = new JsonListObjectMapper<>();
    @JsonIgnore
    protected List<Map<String, Object>> rows;

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

    public JsonListResponse(List<T> raw, String fields)
    {
        this(raw, fields, raw.size());
    }


    public JsonListResponse<T> setRaw(List<T> raw)
    {
        this.raw = raw;
        return this;
    }

    public JsonListResponse<T> addCustomJsonElementFormatter(String key, CustomJsonElementFormatter<T> formatter)
    {
        this.listMapper.addCustomJsonElementFormatter(key, formatter);
        return this;
    }

    public JsonListResponse<T> addNullValue(String key, Object value)
    {
        this.listMapper.addNullValue(key, value);
        return this;
    }

    public JsonListResponse<T> setFields(String fieldString)
    {
        this.listMapper.setFields(fieldString);
        return this;
    }

    public JsonListResponse<T> serialize()
    {
        this.rows = this.listMapper.serializeList(raw);
        this.payload.put("rows", this.rows);
        return this;
    }

}
