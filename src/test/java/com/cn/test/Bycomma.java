package com.cn.test;

import java.util.*;

public class Bycomma
{
    public static String[] splitStringByComma(String source){
        if(source==null||source.trim().equals(""))
            return null;
        StringTokenizer commaToker = new StringTokenizer(source,",");
        String[] result = new String[commaToker.countTokens()];
        int i=0;
        while(commaToker.hasMoreTokens()){
            result[i] = commaToker.nextToken();
            i++;
        }
        return result;
    }
    public static void main(String args[]){
        String[] s = splitStringByComma("5,8,7,4,3,9,1");
        int[] ii = new int[s.length];
        for(int i = 0;i<s.length;i++){
            ii[i] =Integer.parseInt(s[i]);
        }
        Arrays.sort(ii);
        //asc
        for(int i=0;i<s.length;i++){
            System.out.println(ii[i]);
        }
        //desc
        for(int i=(s.length-1);i>=0;i--){
            System.out.println(ii[i]);
        }
    }
}
