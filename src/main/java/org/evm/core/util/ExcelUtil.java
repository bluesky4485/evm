package org.evm.core.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelUtil {
	private Sheet sheet;
	private SXSSFWorkbook workBook;
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
			response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(getFileName()).toString());
			ServletOutputStream ouputStream;
			ouputStream = response.getOutputStream();
			write(ouputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
