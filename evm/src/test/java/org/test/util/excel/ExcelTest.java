package org.test.util.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.evm.core.util.ExcelUtil;
import org.junit.Test;

/**
 * 
 *
 * @Description
 * @author jerry.xu
 * @date 2017年8月14日
 */
public class ExcelTest {

	@Test
	public void testRead() {
		ExcelUtil excelUtil = new ExcelUtil();
		File file = new File("");
		List<ArrayList<Object>> list = excelUtil.readExcel2007(file);
		for (List<Object> list2 : list) {
			for (Object object : list2) {
				System.out.println(String.valueOf(object));
			}
		}
	}

}
