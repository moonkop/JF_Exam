package com.njmsita.exam.utils.format;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * depend on jackson
 *
 * @author Diamond
 */
public class CustomerJsonSerializer
{

    static final String DYNC_INCLUDE = "DYNC_INCLUDE";
    static final String DYNC_FILTER = "DYNC_FILTER";
    ObjectMapper mapper = new ObjectMapper();

    public CustomerJsonSerializer(Class<?> clazz, String include, String filter)
    {
        filter(clazz, include, filter);
    }

    /**
     * @param clazz   需要设置规则的Class
     * @param include 转换时包含哪些字段
     * @param filter  转换时过滤哪些字段
     */
    public void filter(Class<?> clazz, String include, String filter)
    {
        if (clazz == null) return;
        if (include != null && include.length() > 0)
        {
            mapper.setFilters(new SimpleFilterProvider().addFilter(DYNC_INCLUDE, SimpleBeanPropertyFilter.filterOutAllExcept(include.split(","))));
            mapper.addMixInAnnotations(clazz, DynamicInclude.class);
        } else if (filter != null && filter.length() > 0)
        {
            mapper.setFilters(new SimpleFilterProvider().addFilter(DYNC_FILTER, SimpleBeanPropertyFilter.serializeAllExcept(filter.split(","))));
            mapper.addMixInAnnotations(clazz,DynamicFilter.class);
        }
    }

    public String toJson(Object object)
    {
        try
        {
            return mapper.writeValueAsString(object);

        } catch (Exception ex)
        {

            ex.printStackTrace();
        }
        return "";

    }

    @JsonFilter(DYNC_FILTER)
    interface DynamicFilter
    {
    }

    @JsonFilter(DYNC_INCLUDE)
    interface DynamicInclude
    {
    }
}