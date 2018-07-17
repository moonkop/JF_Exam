package com.cn.test;

public class Hello
{
    public static void main(String[] ars){
        A ab = new B(); //执行到此处,结果: 1a2b
        ab = new B(); //执行到此处,结果: 1a2bab
    }
}
