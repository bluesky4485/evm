package org.evm.core.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.core.util.UploadHelper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * s
 * 
 * @Description
 * @author jerry.xu
 * @date 2017年7月27日
 */
public class FileController extends AbstractMultiController {
	static final String excelFileDir = "\\upload\\excel\\";

	/**
	 * 文件服务器路径
	 * 
	 * @return
	 */
	public String getFileServerPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		String rootDir = sdf.format(new Date());
		return getServletContext().getRealPath("/") + FileController.excelFileDir + "//" + rootDir + "//";
	}

	public ModelAndView uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String importRule = request.getParameter("importRule");
			System.out.println(importRule);
			UploadHelper helper = new UploadHelper(request);

			// 上传文件
			List<MultipartFile> multipartFiles = helper.getFileMap(1024 * 1024 * 10, null);
			for (MultipartFile multipartFile : multipartFiles) {
				String filePath = helper.uploadFile(multipartFile, getFileServerPath());
				System.out.println(filePath);
			}
			ReturnAjaxResult(response, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return new ModelAndView("imp");
	}
}
