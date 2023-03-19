package kr.happyjob.study.pur.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.happyjob.study.pur.model.RefundFinalProcessingModel;
import kr.happyjob.study.pur.service.RefundFinalProcessingService;

@Controller
@RequestMapping("/pur/")
public class RefundFinalProcessingController{
	
	@Autowired
	RefundFinalProcessingService refundService;
	
	private final Logger logger = LoggerFactory.getLogger(RefundFinalProcessingController.class);
	private final String className = this.getClass().toString();
	
	@RequestMapping("refundFinalProcessing.do")
	public String refundFinalProcessing(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession Session) throws Exception{
		
		logger.info("+ Start" + className + ".refundFinalProcessing");
		
		return "pur/refundFinalProcessing";
	}
	
	//조회
	@RequestMapping("refundList.do")
	@ResponseBody
	public Map<String, Object> refundList(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start" + className + ".refundList");
		logger.info("-paramMap : " + paramMap);
		
		int currentPage = Integer.parseInt((String)paramMap.get("currentPage"));	// 현재 페이지 번호
		int pageSize = Integer.parseInt((String)paramMap.get("pageSize"));			// 페이지 사이즈
		int pageIndex = (currentPage-1)*pageSize;									// 페이지 시작 row 번호
				
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		
		List<RefundFinalProcessingModel> listRefund = refundService.refundList(paramMap);
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		
		resultmap.put("listRefund", listRefund);
		
		int totalCnt = refundService.refundTotalCnt(paramMap);
		resultmap.put("totalCnt", totalCnt);
		resultmap.put("pageSize", pageSize);
		resultmap.put("currentPage",currentPage);
		
		logger.info("+ End " + className + ".refundList");
		
		return resultmap;
	}
	
	//상세조회
	@ResponseBody
	@RequestMapping("refundDetail.do")
	public Map<String, Object> refundDetail(Model model, @RequestParam Map<String, Object> paramMap) throws Exception{
		
		logger.info("+ Start " + className + ".refundDetail");
		logger.info(" paramMap : " + paramMap);
		

		RefundFinalProcessingModel refundModel = refundService.refundDetail(paramMap);
		
		String result = "";
		
		if(refundModel != null){
			result = "성공";
		}
		else{
			result ="실패";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("result", refundModel);
		
		logger.info("+ End" + className + ".refundModel");
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("refundUpdate.do")
	public Map<String, Object> refundUpdate(Model model, @RequestParam Map<String, Object>paramMap,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		logger.info("+ Start " + className + "refundUpdate");
		logger.info(" - paramMap : " + paramMap);
		
		String action = (String) paramMap.get("action");
		String result = "";
		
		System.out.println(result);
		paramMap.put("loginId", session.getAttribute("loginId"));
		logger.info("loginId : " + paramMap.get("loginId"));
		
		if("U".equals(action)){
			refundService.refundUpdate(paramMap);
			result = "UPDATED";
			System.out.println(paramMap);
		}
		else{
			result = "FAIL";
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		
		return resultMap;
	}
	
}


























