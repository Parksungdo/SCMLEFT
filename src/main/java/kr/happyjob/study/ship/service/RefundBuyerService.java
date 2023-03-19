package kr.happyjob.study.ship.service;

import java.util.List;
import java.util.Map;

import kr.happyjob.study.ship.model.RefundBuyerModel;


public interface RefundBuyerService {

	// 반품지시서 내역검색
	List<RefundBuyerModel> refundList(Map<String, Object> paramMap) throws Exception;
	// 반품지시서내역 카운트
	int refundListCnt(Map<String, Object> paramMap) throws Exception;
	// 상세내역 내역검색
	List<RefundBuyerModel> refundDtlList(Map<String, Object> paramMap) throws Exception;
	// 상세내역 카운트
	int refundDtlListCnt(Map<String, Object> paramMap) throws Exception;
	
	void refundUpdate(Map<String, Object> paramMap);	
}