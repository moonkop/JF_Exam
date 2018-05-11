package com.njmsita.exam.utils.json;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectResponse<T> extends JsonResponse
{

    @JsonIgnore
    protected Map<String, JsonElement> elementMap = new HashMap<>();
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
        this(object, fields,false);
    }


    public void setObject(T object)
    {
        this.object = object;
    }

    public JsonObjectResponse<T> addCustomJsonElementFormater(String key, CustomJsonElementFormater<T> formater)
    {
        elementMap.get(key).formater = formater;
        return this;
    }

    public JsonObjectResponse<T> addNullValue(String key, Object value)
    {
        elementMap.get(key).nullValue = value;
        return this;
    }

    public JsonObjectResponse<T> setFields(String fieldString)
    {
        String[] fields = fieldString.split(",");
        for (String field : fields)
        {
            JsonElement element = new JsonElement(field);
            elementMap.put(element.key, element);
        }
        return this;
    }

    public JsonObjectResponse<T> serialize()
    {
        this.put("object", serializeObject(object));
        return this;
    }

    public Map<String, Object> serializeObject(T object)
    {
        Map<String, Object> objectMap = new HashMap<>();
        for (JsonElement<T> element : this.elementMap.values())
        {
            objectMap.put(element.key, element.serialize(object));
        }
        return objectMap;
    }
}
