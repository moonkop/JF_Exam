package com.njmsita.exam.utils.excelpoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public static void setCellValueForString(Cell cell, String value) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(value);
		}
	}
	
	/**
	 * 根据字符串格式得到单元格的值
	 * @param cell
	 * @return
	 */
	public static String getCellValueForString(Cell cell) {
		if (cell == null) {
			return null;
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();
	}
	
	/**  
	 * 判断指定的单元格是否是合并单元格  
	 * @return  
	 */  
	public static boolean isMergedRegionCell(Sheet sheet, Cell cell) {
		int row = cell.getRowIndex();
		int column = cell.getColumnIndex();
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**  
	* 返回给定合并单元格的CellRangeAdress对象
	* @return  
	*/  
	public static CellRangeAddress getMergedRegionCell(Sheet sheet, Cell cell) {
		int row = cell.getRowIndex();
		int column = cell.getColumnIndex();
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return range;
				}
			}
		}
		return null;
	}

	/**
	 * 行复制功能
	 * @param fromRow
	 * @param toRow
	 */
	public static void copyRow(Workbook wb,Row fromRow,Row toRow,boolean copyValueFlag){
		int i=0;
		for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
			Cell tmpCell = (Cell) cellIt.next();
			//Cell newCell = toRow.createCell(tmpCell.getCellNum());
			Cell newCell = toRow.createCell(i);
			copyCell(wb,tmpCell, newCell, copyValueFlag);
			i++;
		}
	}
	/**
	 * 复制单元格
	 * 
	 * @param srcCell
	 * @param distCell
	 * @param copyValueFlag
	 *            true则连同cell的内容一起复制
	 */
	public static void copyCell(Workbook wb,Cell srcCell, Cell distCell,
			boolean copyValueFlag) {
		CellStyle newstyle=wb.createCellStyle();
		copyCellStyle(srcCell.getCellStyle(), newstyle,distCell);
		//distCell.setEncoding(srcCell.getEncoding());
		//样式
		distCell.setCellStyle(newstyle);
		//评论
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		// 不同数据类型处理
		int srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);
		if (copyValueFlag) {
			if (srcCellType == Cell.CELL_TYPE_NUMERIC) {
				//if (DateUtil.isCellDateFormatted(srcCell)) {
				if (true) {
					distCell.setCellValue(srcCell.getDateCellValue());
				} else {
					distCell.setCellValue(srcCell.getNumericCellValue());
				}
			} else if (srcCellType == Cell.CELL_TYPE_STRING) {
				distCell.setCellValue(srcCell.getRichStringCellValue());
			} else if (srcCellType == Cell.CELL_TYPE_BLANK) {
				// nothing21
			} else if (srcCellType == Cell.CELL_TYPE_BOOLEAN) {
				distCell.setCellValue(srcCell.getBooleanCellValue());
			} else if (srcCellType == Cell.CELL_TYPE_ERROR) {
				distCell.setCellErrorValue(srcCell.getErrorCellValue());
			} else if (srcCellType == Cell.CELL_TYPE_FORMULA) {
				distCell.setCellFormula(srcCell.getCellFormula());
			} else { // nothing29
			}
		}
	}
	
	/**
	 * 复制一个单元格样式到目的单元格样式
	 * @param fromStyle
	 * @param toStyle
	 */
	public static void copyCellStyle(CellStyle fromStyle,CellStyle toStyle,Cell distCell) {
		toStyle.setAlignment(fromStyle.getAlignment());
		//边框和边框颜色
		toStyle.setBorderBottom(fromStyle.getBorderBottom());
		toStyle.setBorderLeft(fromStyle.getBorderLeft());
		toStyle.setBorderRight(fromStyle.getBorderRight());
		toStyle.setBorderTop(fromStyle.getBorderTop());
		toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
		toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
		toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
		toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
		
		//背景和前景
		toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
		toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
		
		toStyle.setDataFormat(fromStyle.getDataFormat());
		toStyle.setFillPattern(fromStyle.getFillPattern());
//		toStyle.setFont(fromStyle.getFont(null));
		toStyle.setHidden(fromStyle.getHidden());
		toStyle.setIndention(fromStyle.getIndention());//首行缩进
		toStyle.setLocked(fromStyle.getLocked());
		toStyle.setRotation(fromStyle.getRotation());//旋转
		toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
		toStyle.setWrapText(fromStyle.getWrapText());
		
		String dataFormat = fromStyle.getDataFormatString();
//		if(dataFormat!=null && dataFormat.startsWith("[=")){
//			Pattern p = Pattern.compile("\\=(.*?)\\]");//正则表达式，取=和]之间的字符串，不包括=和|
//			Matcher m = p.matcher(dataFormat);
//			List<String> cnsList = new ArrayList<String>();
//			while(m.find()) {
//				cnsList.add(m.group(1));//m.group(1)不包括这两个字符
//			}
//			Object[] objectArray = cnsList.toArray();
//			String[] temp = new String[cnsList.size()];
//			for(int i=0;i<cnsList.size();i++){
//				temp[i] = objectArray[i].toString();
//			}
//			DataValidationHelper dvHelper = distCell.getSheet().getDataValidationHelper();
//    		DataValidationConstraint dvConstraint = dvHelper
//    				.createExplicitListConstraint(cnsList.toArray(temp));
//    		
//    		//CellRangeAddressList(a, b, c, d);  a:起始行  b：结束行   c：起始列  d：结束列
//    		CellRangeAddressList addressList = new CellRangeAddressList(distCell.getRowIndex(), distCell.getRowIndex(), distCell.getColumnIndex(), distCell.getColumnIndex());
//    		DataValidation validation = dvHelper.createValidation(dvConstraint,
//    				addressList);
//    		if (validation instanceof XSSFDataValidation) {
//    			validation.setSuppressDropDownArrow(true);
//    			validation.setShowErrorBox(true);
//    		} else {
//    			// If the Datavalidation contains an instance of the
//    			// HSSFDataValidation
//    			// class then 'true' should be passed to the
//    			// setSuppressDropDownArrow()
//    			// method and the call to setShowErrorBox() is not necessary.
//    			validation.setSuppressDropDownArrow(true);
//    		}
//    		 distCell.getSheet().addValidationData(validation); 
//		}
	}
	
	/**
	 * 复制数据有效性
	 * @param distCell
	 * @param dataValidationName
	 */
	public static void copyDataValidation(Cell distCell, String dataValidationName) {
		DataValidationHelper dvHelper = distCell.getSheet().getDataValidationHelper();
		DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(dataValidationName);
				//.createExplicitListConstraint(cnsList.toArray(temp));
		
		//CellRangeAddressList(a, b, c, d);  a:起始行  b：结束行   c：起始列  d：结束列
		CellRangeAddressList addressList = new CellRangeAddressList(distCell.getRowIndex(), distCell.getRowIndex(), distCell.getColumnIndex(), distCell.getColumnIndex());
		DataValidation validation = dvHelper.createValidation(dvConstraint,
				addressList);
		if (validation instanceof XSSFDataValidation) {
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
		} else {
			// If the Datavalidation contains an instance of the
			// HSSFDataValidation
			// class then 'true' should be passed to the
			// setSuppressDropDownArrow()
			// method and the call to setShowErrorBox() is not necessary.
			validation.setSuppressDropDownArrow(true);
		}
		distCell.getSheet().addValidationData(validation);
	}

	/**
	 * 根据给定Excel文件对象，返回相应的Workbook
	 * @param excelFile
	 * @return
	 */
	public static Workbook getWorkbook(File excelFile) {
		Workbook workbook = null;
		InputStream is = null;
		try {
			is = new FileInputStream(excelFile);
			if (excelFile.getName().endsWith(".xlsx") || excelFile.getName().endsWith(".xlsm")) {
				workbook = new XSSFWorkbook(is);
			}
			else {
				workbook = new HSSFWorkbook(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(is);
		}
		return workbook;
	}
	
	/**
	 * 获取workbook中所有命名过的单元格
	 * @param wb
	 * @return  key为  sheetName+":"+cellName      value为 cell
	 */
	public static Map<String, Cell> getAllNamedCell(Workbook wb){
		
		Map<String, Cell> cells = new HashMap<String,Cell>();
		
		int nameCount = wb.getNumberOfNames();
		String cellName = "";
		for (int nameIndex = 0; nameIndex < nameCount; nameIndex++) {
		    try {
				Name name = wb.getNameAt(nameIndex);
				cellName = name.getNameName();
				AreaReference[] arefs = AreaReference.generateContiguous(name.getRefersToFormula());
				CellReference[] crefs = arefs[0].getAllReferencedCells();
				String sheetName = crefs[0].getSheetName();
				Sheet s = wb.getSheet(sheetName);
				Row r = s.getRow(crefs[0].getRow());
				Cell cell = r.getCell(crefs[0].getCol());
				cells.put(sheetName+":"+cellName, cell);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				//System.out.println(nameIndex+"    sheetName="+sheetName+"  cellName="+cellName+" value=" +cell.getStringCellValue());
			} catch (Exception e) {
				//System.out.print(nameIndex+"    ==========cellName="+cellName+"=========");
				e.printStackTrace();
			}
		}
		
		return cells;
	}

	/**
	 * 复制Sheet
	 */
	public static void copySheet(Workbook wb, Sheet source, Sheet target) {
		for (Iterator<Row> rowIter = source.rowIterator(); rowIter.hasNext();) {
			Row row = rowIter.next();
			copyRow(wb, row, target.createRow(row.getRowNum()), true);
		}
	}
	
}
