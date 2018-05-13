package com.njmrita.argument;

import com.njmsita.exam.utils.format.FormatUtil;
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

    @Test
    public void fun3(){
        String time=FormatUtil.formatDateTime(System.currentTimeMillis());
        String[] date=time.split(" ");
        String[] year=date[0].split("-");
        String[] second=date[1].split(":");
        StringBuilder cron=new StringBuilder();
        for(int i=second.length-1;i>=0;i--){
            cron.append(Integer.parseInt(second[i])+" ");
        }
        for(int i=year.length-1;i>0;i--){
            cron.append(Integer.parseInt(year[i])+" ");
        }
        cron.append("? "+year[0]);
        System.out.println(cron);
        System.out.println(time);
    }

}
