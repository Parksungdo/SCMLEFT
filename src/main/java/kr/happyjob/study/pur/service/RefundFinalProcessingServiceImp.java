package kr.happyjob.study.pur.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.happyjob.study.pur.dao.RefundFinalProcessingDao;
import kr.happyjob.study.pur.model.RefundFinalProcessingModel;

@Service
public class RefundFinalProcessingServiceImp implements RefundFinalProcessingService {
	@Autowired
	RefundFinalProcessingDao refundDao;

	@Override
	public List<RefundFinalProcessingModel> refundList(Map<String, Object> paramMap) throws Exception{
		List<RefundFinalProcessingModel> refundList = refundDao.refundList(paramMap);
		
		return refundList;
	}

	@Override
	public int refundTotalCnt(Map<String, Object> paramMap) throws Exception{
		int refundTotalCnt = refundDao.refundTotalCnt(paramMap);
		
		return refundTotalCnt;
	}

	@Override
	public RefundFinalProcessingModel refundDetail(Map<String, Object> paramMap) throws Exception{
		RefundFinalProcessingModel refundDetail = refundDao.refundDetail(paramMap);
		
		return refundDetail;
	}
	
	@Override
	public int refundUpdate(Map<String, Object> paramMap) throws Exception{
		int updateRefund = refundDao.refundUpdate(paramMap);
		
		return updateRefund;
	}
	
}
