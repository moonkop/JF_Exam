package com.njmsita.exam.utils.json;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectMapper<T>
{
    protected Map<String, JsonElement> elementMap = new HashMap<>();

    public JsonObjectMapper()
    {

    }

    public Map<String, JsonElement> getElementMap()
    {
        return elementMap;
    }

    public JsonObjectMapper<T> setFields(String fieldString)
    {
        String[] fields = fieldString.split(",");
        for (String field : fields)
        {
            JsonElement element = new JsonElement(field);
            elementMap.put(element.key, element);
        }
        return this;
    }

    public JsonObjectMapper<T> addNullValue(String key, Object value)
    {
        elementMap.get(key).nullValue = value;
        return this;

    }

    public  JsonObjectMapper<T> addCustomJsonElementFormatter(String key, CustomJsonElementFormatter<T> formatter)
    {
        elementMap.get(key).formater = formatter;
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
