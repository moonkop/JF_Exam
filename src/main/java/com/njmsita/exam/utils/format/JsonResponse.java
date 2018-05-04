package com.njmsita.exam.utils.format;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse
{
    private String mesasge;
    private int code;
    protected Map<String, Object> payload = new HashMap<>();

    public JsonResponse()
    {
        this.mesasge = "ok";
        this.code = 100;
    }

    public Map<String, Object> getPayload()
    {
        return payload;
    }

    public void setPayload(Map<String, Object> payload)
    {
        this.payload = payload;
    }

    public String getMesasge()
    {
        return mesasge;
    }

    public void setMesasge(String mesasge)
    {
        this.mesasge = mesasge;
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
