package com.njmrita.argument;

import org.junit.Test;

import java.util.Date;

public class ObjectToClass
{
    @Test
    public void fun(){
        Object[] objects=new Object[3];
        objects[0]="ashdjkashkj";
        objects[1]=123l;
        objects[2]='c';
        printClassName(objects);
    }

    public void printClassName(Object[] objects){
        for (Object object : objects)
        {
            Class clazz=object.getClass();
            System.out.println(clazz);
        }
    }

    @Test
    public void fun1(){
        Object zhangsan=new Person("zhangsan",18);
        System.out.println(zhangsan);
    }

    @Test
    public void fun2(){
        System.out.println(new Date("2018/05/02").getTime()-new Date("2018/04/25").getTime());

        System.out.println(new Date(1524585600000l));
        System.out.println(new Date(1525190400000l));
    }

}
