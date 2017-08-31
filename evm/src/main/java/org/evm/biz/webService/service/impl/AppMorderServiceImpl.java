package org.evm.biz.webService.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import org.evm.biz.faulttype.entity.FaultTypeVO;
import org.evm.biz.faulttype.service.IFaultTypeDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.morder.service.IMOrderDbService;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.biz.phoneapp.service.IAppDbService;
import org.evm.biz.webService.service.IAppMorderService;
import org.evm.core.exception.SmartRuntimeException;
import org.evm.core.log.DataCenterLogger;
import org.evm.core.util.UploadHelper;

import sun.misc.BASE64Decoder;

/**
 * 运维订单管理
 * 
 * @author jerry.x update 2016年10月31日 下午8:56:40
 */
public class AppMorderServiceImpl implements IAppMorderService {
	protected DataCenterLogger logger = new DataCenterLogger(this.getClass());

	IMOrderDbService morderDbService;
	IAppDbService appDbService;
	IFaultTypeDbService faultTypeDbService;

	public void setFaultTypeDbService(IFaultTypeDbService faultTypeDbService) {
		this.faultTypeDbService = faultTypeDbService;
	}

	public void setMorderDbService(IMOrderDbService morderDbService) {
		this.morderDbService = morderDbService;
	}

	public void setAppDbService(IAppDbService appDbService) {
		this.appDbService = appDbService;
	}

	@Override
	public List<MOrderVO> findAllMorderList(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return morderDbService.findAllMorderForApp(whereCause);
	}

	@Override
	public MOrderVO getMOrderVO(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		MOrderVO res = morderDbService.getMorderById(whereCause);
		if (res != null) {
			List<FaultTypeVO> faultTypeVOList = this.faultTypeDbService.findAllFaultType(null);
			res.setFaultTypeVOList(faultTypeVOList);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "修改维护订单的评价信息", bizTypeId = BizOptType.update, functionId = FunctionMap.NO_FUNC, isCheckFunction = false)
	public int updateUserProposal(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return morderDbService.updateUserProposal(whereCause);
	}

	@Override
	public int updateMaintainBeginDate(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return morderDbService.updateMaintainBeginDate(whereCause);
	}

	@Override
	public int updateMaintainSpeed(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.morderDbService.updateMaintainSpeed(whereCause);
	}

	@Override
	public int updateMaintainResultdesc(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.morderDbService.updateMaintainResultdesc(whereCause);
	}

	@Override
	public int updateFaultType(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.morderDbService.updateFaultType(whereCause);
	}

	@Override
	public MOrderVO getLastMoarder(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return (MOrderVO) appDbService.getLastMorder(whereCause);
	}

	@Override
	public long uploadMOrderWorkImg(MOrderFileVO whereCause) {
		// TODO Auto-generated method stub
		String fileName = this.decodeBase64ToImage(whereCause.getFileContent(), whereCause.getFileType(),
				String.valueOf(whereCause.getMorderId()));
		String filePath = IMOrderDbService.morderFileDic + "\\" + String.valueOf(whereCause.getMorderId()) + "\\"
				+ fileName;
		whereCause.setFilePath(filePath);
		whereCause.setBizType("1");
		if (whereCause.getFileName() == null) {
			whereCause.setFileName(filePath);
		}
		long res = this.morderDbService.insertMorderImg(whereCause);
		return res;
	}

	@Override
	public int deleteMOrderWorkImg(MOrderFileVO whereCause) {
		// TODO Auto-generated method stub
		return this.morderDbService.deleteMorderImg(whereCause);
	}
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
			logger.info("将Base64位编码的图片的文件名="+imgName);
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

	@Override
	public int updateMorderWorkerCnt(MOrderVO para) {
		// TODO Auto-generated method stub
		return this.morderDbService.updateMorderWorkerCnt(para);
	}
}
