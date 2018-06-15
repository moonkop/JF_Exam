package com.njmsita.exam.utils.json;

import org.codehaus.jackson.annotate.JsonIgnore;

public class JsonObjectResponse<T> extends JsonResponse
{

    @JsonIgnore
    protected JsonObjectMapper<T> mapper = new JsonObjectMapper<T>();

    @JsonIgnore
    private T object;


    public JsonObjectResponse()
    {

    }

    public JsonObjectResponse(T object, String fields, boolean withMoreOptions)
    {
        this.object = object;
        this.setFields(fields);
        if (!withMoreOptions)
        {
            this.serialize();
        }
    }

    public JsonObjectResponse(T object, String fields)
    {
        this(object, fields, false);
    }

    public void setObject(T object)
    {
        this.object = object;
    }

    public JsonObjectResponse<T> addCustomJsonElementFormatter(String key, CustomJsonElementFormatter<T> formatter)
    {
        mapper.addCustomJsonElementFormatter(key, formatter);
        return this;
    }

    public JsonObjectResponse<T> addNullValue(String key, Object value)
    {
        mapper.addNullValue(key, value);
        return this;
    }

    public JsonObjectResponse<T> setFields(String fieldString)
    {
        mapper.setFields(fieldString);
        return this;
    }

    public JsonObjectResponse<T> serialize()
    {
        this.put("object", mapper.serializeObject(this.object));
        return this;
    }


}
