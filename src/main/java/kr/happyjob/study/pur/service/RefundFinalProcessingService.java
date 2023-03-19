package kr.happyjob.study.pur.service;

import java.util.List;
import java.util.Map;

import kr.happyjob.study.pur.model.RefundFinalProcessingModel;

public interface RefundFinalProcessingService {
	public List<RefundFinalProcessingModel> refundList(Map<String, Object> paramMap) throws Exception;

	public int refundTotalCnt(Map<String, Object> paramMap) throws Exception;

	public RefundFinalProcessingModel refundDetail(Map<String, Object> paramMap) throws Exception;

	public int refundUpdate(Map<String, Object> paramMap) throws Exception;
}
