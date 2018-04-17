package com.njmsita.exam.utils.excelpoi;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * 导入Excel工具类
 */
public class ExcelPoi
{
    /**
     * 获取表单
     * @param filePath      文件路径
     * @return              返回表单中所有的行
     * @throws Exception
     */
    public static HSSFSheet getHSSFSheet(String filePath) throws Exception
    {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
        return workbook.getSheetAt(0);
    }
}
