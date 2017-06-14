package org.evm.biz.morder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.morder.service.IMOrderDbService;
import org.evm.biz.order.entity.OrderFileVO;

public class MOrderDbServiceImpl extends BaseDBService implements IMOrderDbService {

	@Override
	public List<MOrderVO> findAllMorder(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		List<MOrderVO> rows = null;
		rows = super.find(MS_FIND_MORDER_PAGE, whereCause);
		return rows;
	}

	@Override
	@SystemServiceLog(description = "创建维修订单信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public int insertMOrder(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return (Integer) super.insert(MS_INSERT_MORDER, whereCause);
	}

	@Override
	@SystemServiceLog(description = "更新维修订单信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public int updateMOrder(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = (int) super.update(MS_UPDATE_MORDER, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("更新维修订单信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("更新维修订单信息异常", e);
		}
		try {
			super.delete(MS_DELETE_MORDER_FILE_BY_MORDER_ID, whereCause.getMorderId());
			String fileStrs = whereCause.getFileStrs();
			if (fileStrs != "") {
				String[] paths = whereCause.getFileStrs().split("@");
				if (paths.length > 0) {
					List<MOrderFileVO> files = new ArrayList<MOrderFileVO>();
					for (String path : paths) {
						MOrderFileVO file = new MOrderFileVO();
						String[] arr = path.split("&");
						file.setFilePath(arr[0]);
						file.setFileName(arr[1]);
						file.setBizType(arr[2]);
						file.setMorderId(whereCause.getMorderId());
						file.setUpdUser(whereCause.getUpdUser());
						file.setInsUser(whereCause.getInsUser());
						files.add(file);
					}
					super.batchInsert(MS_INSERT_REL_MOREDER_FILE, files);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SmartDBAccessException("更新维修订单文件信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "删除维修订单信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public int deleteMOrder(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return (int) super.update(MS_DELETE_MORDER, whereCause);
	}

	@Override
	@SystemServiceLog(description = "查询维修订单信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public PageResult findAllMorderPage(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<MOrderVO> rows = null;
		int total = super.getCount(MS_FIND_MORDER_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_FIND_MORDER_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	public MOrderVO getMorderById(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return (MOrderVO) super.get(MS_GET_MORDER_BYID, whereCause);
	}

	@Override
	public List<MOrderVO> findAllMorderForApp(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_MORDER_PAGE, whereCause);
	}

	@Override
	public int updateUserProposal(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_USERPROPOSAL, whereCause);
	}

	@Override
	public int updateMaintainBeginDate(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_MAINTAIN_BEGIN_DATE, whereCause);
	}

	@Override
	public int updateMaintainSpeed(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_MAINTAIN_SPEED, whereCause);
	}

	@Override
	public int updateFaultType(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_FAULT_TYPE, whereCause);
	}

	@Override
	public int updateMaintainResultdesc(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_MAINTAIN_RESULTDESC, whereCause);
	}

	@Override
	public long insertMorderImg(MOrderFileVO whereCause) {
		// TODO Auto-generated method stub
		MOrderVO moder = new MOrderVO();
		moder.setUpdDate(whereCause.getUpdDate());
		moder.setUpdUser(whereCause.getUpdUser());
		moder.setMorderId(whereCause.getMorderId());
		int a = this.update(MS_UPDATE_MAINTAIN_UPDDATE, moder);
		if (a == 0)
			throw new SmartDBAccessException("数据已旧，插入文件信息失败！");
		long res = (Long) super.insert(MS_INSERT_REL_MOREDER_FILE, whereCause);
		return res;
	}

	@Override
	public int deleteMorderImg(MOrderFileVO whereCause) {
		// TODO Auto-generated method stub
		MOrderVO moder = new MOrderVO();
		moder.setUpdDate(whereCause.getUpdDate());
		moder.setUpdUser(whereCause.getUpdUser());
		moder.setMorderId(whereCause.getMorderId());
		int a = this.update(MS_UPDATE_MAINTAIN_UPDDATE, moder);
		if (a == 0)
			throw new SmartDBAccessException("数据已旧，删除文件信息失败！");
		int res = (int) super.delete(MS_DELETE_MORDER_FILE_BY_FILE_ID, whereCause);
		return res;
	}

	@Override
	public int updateMorderWorkerCnt(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.update(MS_UPDATE_WORDERCNT, whereCause);
	}

}
