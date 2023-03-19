package kr.happyjob.study.ship.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.happyjob.study.ship.dao.RefundBuyerDao;
import kr.happyjob.study.ship.model.RefundBuyerModel;

@Service
public class RefundBuyerServiceImpl implements RefundBuyerService {
	
	@Autowired
	RefundBuyerDao RefundBuyerDao;

	@Override
	public List<RefundBuyerModel> refundList(Map<String, Object> paramMap) throws Exception {
		return  RefundBuyerDao.refundList(paramMap);
		
	}

	@Override
	public int refundListCnt(Map<String, Object> paramMap) throws Exception {
		
		return RefundBuyerDao.refundListCnt(paramMap);
	}

	@Override
	public List<RefundBuyerModel> refundDtlList(Map<String, Object> paramMap) throws Exception {
	
		return RefundBuyerDao.refundDtlList(paramMap);
	}

	@Override
	public int refundDtlListCnt(Map<String, Object> paramMap) throws Exception {
	
		return RefundBuyerDao.refundDtlListCnt(paramMap);
	}

	@Override
	@Transactional
	public void refundUpdate(Map<String, Object> paramMap) {
		
		RefundBuyerDao.refundUpdateR(paramMap);
		RefundBuyerDao.refundUpdateW(paramMap);
		RefundBuyerDao.refundInsertW(paramMap);
	}
	
	
}