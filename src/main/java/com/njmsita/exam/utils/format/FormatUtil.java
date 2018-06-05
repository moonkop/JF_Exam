package com.njmsita.exam.utils.format;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

	public static final String formatDate(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return  df.format(new Date(time)); 
	}
	public static final String formatTime(Long time){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	public static final String formatDateTime(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	public static final Double formatScore(Double money){
		DecimalFormat   df   =new DecimalFormat("#.0");
		return Double.parseDouble(df.format(money));
	}

	public static final String cronExpression(Long time){
		String timeStr=formatDateTime(time);
		String[] date=timeStr.split(" ");
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
		return cron.toString();
	}

	public static final Long getTimeByCron(String cron)
	{

		//58 8 11 5 6 ? 2018
		String[] crons=cron.split(" ");
		StringBuilder sb=new StringBuilder();
		sb.append(crons[6]+"-");
		sb.append(crons[4]+"-");
		sb.append(crons[3]+" ");
		sb.append(crons[2]+":");
		sb.append(crons[1]+":");
		sb.append(crons[0]);
		Date date=null;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			date=formatter.parse(sb.toString());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date.getTime();
	}
}
