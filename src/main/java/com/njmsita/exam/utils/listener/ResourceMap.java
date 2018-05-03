package com.njmsita.exam.utils.listener;

import java.util.HashSet;
import java.util.Set;

public class ResourceMap
{
    Set<String> urls = new HashSet<>();
    public boolean contains(String url)
    {
       return urls.contains(url);

    }
}
