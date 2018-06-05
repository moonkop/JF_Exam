package com.njmsita.exam.utils.format;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormatUtilTest
{

    @Test
    public void fun1() throws Exception
    {
        System.out.println(FormatUtil.getTimeByCron("58 8 11 5 6 ? 2018"));
        System.out.println(FormatUtil.formatDateTime(FormatUtil.getTimeByCron("58 8 11 5 6 ? 2018")));
    }
}