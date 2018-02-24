package org.evm.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.evm.core.exception.SmartRuntimeException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class UploadHelper {

	protected static final Log logger = LogFactory.getLog(UploadHelper.class);

	private MultipartHttpServletRequest request;

	public UploadHelper(HttpServletRequest request) {
		this.request = (MultipartHttpServletRequest) request;
	}

	/**
	 * 获取MultipartFile集合
	 * 
	 * @param maxLength
	 *            文件最大限制
	 * @param allowExtName
	 *            不允许上传的文件扩展名
	 * @return MultipartFile集合
	 */
	public List<MultipartFile> getFileMap(long maxLength, String[] allowExtName) {

		// 获得上传的文件（根据前台的name名称得到上传的文件）
		@SuppressWarnings("unchecked")
		Map<String, MultipartFile> files = request.getFileMap();
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		Collection<MultipartFile> collection = files.values();
		for (Iterator<MultipartFile> iterator = collection.iterator(); iterator.hasNext();) {
			MultipartFile multipartFile = (MultipartFile) iterator.next();
			if (maxLength==-1||validateFile(multipartFile, maxLength, allowExtName)) {
				logger.debug("upload multipart files :" + multipartFile.getOriginalFilename() + ","
						+ multipartFile.getSize());
				list.add(multipartFile);
			} else {
				logger.debug("upload multipart validate files fail:" + multipartFile.getOriginalFilename() + ","
						+ multipartFile.getSize());
			}

		}
		return list;
	}

	/**
	 * @descrption 保存文件 返回服务器
	 * @param multipartFile
	 *            MultipartFile对象
	 * @param path
	 * @throws Exception
	 *             文件保存失败
	 */
	public String uploadFile(MultipartFile multipartFile, String path) throws Exception {

		String filePath = "";
		try {
			if (!path.endsWith(File.separator)) {
				path = path + File.separator;
			}
			File fileFolder = new File(path);
			// 如果文件夹不存在则创建
			if (!fileFolder.exists() && !fileFolder.isDirectory()) {
				System.out.println("//不存在");
				fileFolder.mkdir();
			} else {
			}
			// 图片路径
			String prefix = multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
			filePath = path + UUID.randomUUID() + "." + prefix.toLowerCase();
			FileCopyUtils.copy(multipartFile.getBytes(), new File(filePath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除文件", e);
		}

		logger.debug("copy upload file :" + filePath);

		return filePath;
	}

	/**
	 * 验证文件合法性
	 * 
	 * @param file
	 *            MultipartFile对象
	 * @param maxLength
	 *            最大限制
	 * @param allowExtName
	 *            不允许上传的扩展名
	 * @return 格式是否合法
	 */
	private boolean validateFile(MultipartFile file, long maxLength, String[] allowExtName) {
		if (file.getSize() < 0 || file.getSize() > maxLength)
			return false;
		String filename = file.getOriginalFilename();

		// 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
		if (filename == "") {
			return false;
		}
		String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
		if (allowExtName == null || allowExtName.length == 0 || Arrays.binarySearch(allowExtName, extName) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param fileName
	 * @param fileName
	 * @param fileSize
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public HttpServletResponse download(String filePath, String fileName, Long fileSize, HttpServletResponse response)
			throws IOException {
		InputStream fis = null;
		OutputStream toClient = null;
		logger.debug("文件下载:name=" + fileName + ",path" + filePath);
		try {
			// 以流的形式下载文件。
			fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			// response.addHeader("Content-Length", "" + fis.available());
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();

		} catch (IOException ex) {
			logger.debug("文件下载错误", ex);
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (toClient != null) {
				toClient.close();
			}
		}
		return response;
	}

	public void downloadLocal(String filePath, String fileName, Long fileSize, HttpServletResponse response)
			throws FileNotFoundException {
		// 下载本地文件
		logger.debug("下载本地文件");
		// 读到流中
		InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			logger.error("下载异常", e);
		}
	}

	/**
	 * 将Base64位编码的图片进行解码，并保存到指定目录
	 * 
	 * @param base64
	 *            base64编码的图片信息
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decodeBase64ToImage(String base64, String fileType, String orderId) {

		URL url = this.getClass().getClassLoader().getResource("../../upload/morder/");
		String imgName = UUID.randomUUID() + "." + fileType;
		try {
			String path = URLDecoder.decode(url.getPath(), "utf-8");
			// url.getPath();//
			// directory.getAbsolutePath();//
			path += "\\" + orderId;
			File fileFolder = new File(path);
			// 如果文件夹不存在则创建
			if (!fileFolder.exists() && !fileFolder.isDirectory()) {
				logger.debug("需要创建文件夹:" + path);
				fileFolder.mkdir();
			}
			logger.info("将Base64位编码的图片的文件名=" + imgName);
			BASE64Decoder decoder = new BASE64Decoder();
			FileOutputStream write = new FileOutputStream(new File(path + "\\" + imgName));
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			logger.error("将Base64位编码的图片进行解码异常", e);
			throw new SmartRuntimeException("写文件异常" + e.getMessage());
		}
		return imgName;
	}

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("C:\\QQ53.png");
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		String strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
		in.close();
		System.out.println(strBase64);
		UploadHelper a = new UploadHelper(null);
		String im=a.decodeBase64ToImage(strBase64, "png", "909");
		System.out.println(im);
	}

	public static void main990(String[] args) throws IOException {
		String fileName = "C:\\QQ53.png";
		String splitReg = "\\.";
		String[] arr = fileName.split(splitReg);
		System.out.println(arr[1]);
	}
}
