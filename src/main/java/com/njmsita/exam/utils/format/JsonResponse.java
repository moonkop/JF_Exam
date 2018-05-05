package com.njmsita.exam.utils.format;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse
{
    protected Map<String, Object> payload = new HashMap<>();
    private String message;
    private int code;

    public JsonResponse()
    {
        this.message = "ok";
        this.code = 100;
    }

    public JsonResponse(String message)
    {
        this.message = message;
        this.code = 100;
    }

    public JsonResponse(int code, String message)
    {
        this.message = message;
        this.code = code;
    }

    public Map<String, Object> getPayload()
    {
        return payload;
    }

    public void setPayload(Map<String, Object> payload)
    {
        this.payload = payload;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public JsonResponse put(String key, Object value)
    {
        this.payload.put(key, value);
        return this;
    }

}
