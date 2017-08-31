package org.evm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	// 默认单元格格式化日期字符串
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 格式化数字
	private static DecimalFormat nf = new DecimalFormat("0.00");
	private Sheet sheet;
	private SXSSFWorkbook workBook;
	// 统一写成2007版
	final String extenddName = ".xls";
	Logger logger = Logger.getLogger(this.getClass());

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public SXSSFWorkbook getWorkBook() {
		return workBook;
	}

	public void setWorkBook(SXSSFWorkbook workBook) {
		this.workBook = workBook;
	}

	public ExcelUtil() {
		workBook = new SXSSFWorkbook();
		sheet = workBook.createSheet();
	}

	public String getFileName() {
		return System.currentTimeMillis() + ".xls";
	}

	/**
	 * 创建标题头
	 * 
	 * @param fields
	 */
	public void createHeader(String[] fields) {
		Row row = sheet.getRow(0);
		CellStyle cs = workBook.createCellStyle();
		Font font = workBook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cs.setFont(font);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		for (int colIndex = 0; colIndex < fields.length; colIndex++) {

			if (row == null)
				row = sheet.createRow(0);
			Cell cell = row.createCell(colIndex);
			cell.setCellValue(fields[colIndex]);
			cell.setCellStyle(cs);
		}
	}

	/**
	 * 单元格赋值
	 * 
	 * @param rowIndex
	 *            行索引 从0开始
	 * @param colIndex
	 *            列索引 从0开始
	 * @param value
	 *            单元格数据
	 */
	public void setValue(int rowIndex, int colIndex, String value) {
		Row row = sheet.getRow(rowIndex);
		if (row == null)
			row = sheet.createRow(rowIndex);
		Cell cell = row.createCell(colIndex);
		cell.setCellValue(value);
	}

	public void write(OutputStream outputStream) throws IOException {
		// dispose of temporary files backing this workbook on disk
		try {
			workBook.write(outputStream);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("ExcelUtil write到输出流时异常！", e);
		} finally {
			if (workBook != null)
				workBook.dispose();
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
	}

	public void write(HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					(new StringBuilder("attachment;filename=")).append(getFileName()).toString());
			ServletOutputStream ouputStream;
			ouputStream = response.getOutputStream();
			write(ouputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("ExcelUtil write到输出流时异常!", e);
		}
	}

	//
	/**
	 * 
	 * @param file
	 * @return
	 */
	public ArrayList<ArrayList<Object>> readExcel2007(File file) {
		try {
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Object value;
			for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if (row == null) {
					// 当读取行为空时
					if (i != sheet.getPhysicalNumberOfRows()) {// 判断是否是最后一行
						rowList.add(colList);
					}
					continue;
				} else {
					rowCount++;
				}
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						// 当该单元格为空
						if (j != row.getLastCellNum()) {// 判断是否是该行中最后一个单元格
							colList.add("");
						}
						continue;
					}

					colList.add(getCellValue(cell));
				} // end for j
				rowList.add(colList);
			} // end for i

			return rowList;
		} catch (Exception e) {
			System.out.println("exception");
			return null;
		}
	}

	public String getCellValue(Cell cell) {
		String value = "";
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			// System.out.println(i + "行" + j + " 列 is String type");
			value = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			if ("@".equals(cell.getCellStyle().getDataFormatString())) {
				value = nf.format(cell.getNumericCellValue());
			} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = nf.format(cell.getNumericCellValue());
			} else {
				value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			// System.out.println(i + "行" + j + " 列 is Number type ; DateFormt:" +
			// value.toString());
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			// System.out.println(i + "行" + j + " 列 is Boolean type");
			value = String.valueOf(Boolean.valueOf(cell.getBooleanCellValue()));
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			// System.out.println(i + "行" + j + " 列 is Blank type");
			value = "";
			break;
		default:
			// System.out.println(i + "行" + j + " 列 is default type");
			value = cell.toString();
		}// end switch

		return value;
	}
}
