package kr.happyjob.study.ship.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.happyjob.study.ship.model.RefundBuyerModel;
import kr.happyjob.study.ship.service.RefundBuyerService;

@Controller
@RequestMapping("/ship/")
public class RefundBuyerController {
	
	@Autowired
	RefundBuyerService RefundBuyerService;
	
	// Set logger
	private final Logger logger = LogManager.getLogger(this.getClass());

	// Get class name for logger
	private final String className = this.getClass().toString();
	
	
	
	// 반품지시서 초기화
	@RequestMapping("refundBuyer.do")
	public String refundBuyer(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		
		return "ship/refundBuyer";
	}
	
	// 검색버튼 클릭 시 반품지시서 내역 가져오기
	@RequestMapping("refundList.do")
	@ResponseBody
	public Map<String, Object> refundList(Model model, @RequestParam Map<String, Object> paramMap,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		  	logger.info("+ Start " + className + ".initComnCod");
		  
//			  String sname = (String)paramMap.get("sname"); 
//			  String stDate =  (String)paramMap.get("stDate"); 
//			  String edDate =  (String)paramMap.get("edDate");
			  
			  int currentPage = Integer.parseInt(String.valueOf(paramMap.get("currentPage"))) ;
			  int pageSize = Integer.parseInt( String.valueOf(paramMap.get("pageSize"))); 
			  int pageIndex = (currentPage - 1) * pageSize;
			  
			  paramMap.put("loginId", session.getAttribute("loginId"));
			  paramMap.put("pageIndex", pageIndex); 
			  paramMap.put("pageSize", pageSize);
//			  paramMap.put("sname", sname);
//			  paramMap.put("stDate", stDate);
//			  paramMap.put("edDate", edDate);
			  
			  
			  logger.info("   - paramMap ssss  : " + paramMap);
			  
			
			  List<RefundBuyerModel> refundBuyerlist =  RefundBuyerService.refundList(paramMap);
			  
			  logger.info(" ---> refundBuyerlist : " + refundBuyerlist);
			  
			  int refundListCnt =  RefundBuyerService.refundListCnt(paramMap);	
			  
			  Map<String, Object> resultMap = new HashMap<String, Object>();
			  
			  resultMap.put("listrefund", refundBuyerlist);
			  resultMap.put("totalCount", refundListCnt); 
				

		      resultMap.put("pageSize", pageSize);
			  resultMap.put("currentPage",currentPage);
			  
			  logger.info(" ---> resultMap : " + resultMap);
				   
			  logger.info("+ End " + className + ".initComnCod");
			 
		return resultMap;
					
		}

	// 반품번호 클릭 시 상세내역 가져오기
	@RequestMapping("refundDtlList.do")
	@ResponseBody
	public Map<String, Object> refundDtlList(Model model, @RequestParam Map<String, Object> paramMap,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		  	logger.info("+ Start " + className + ".initComnCod");
		  
			
			  String re_code = (String)paramMap.get("re_code"); 
			  
			  int currentPage= Integer.parseInt(String.valueOf(paramMap.get("currentPage"))) ;
			  int pageSize = Integer.parseInt( String.valueOf(paramMap.get("pageSize"))); 
			  int pageIndex = (currentPage - 1) * pageSize;
			  
			  paramMap.put("pageIndex", pageIndex); 
			  paramMap.put("pageSize", pageSize);
			  paramMap.put("re_code", re_code);


			  logger.info("   - paramMap ssss  : " + paramMap);
			  
			
			  List<RefundBuyerModel> refundBuyerlist =  RefundBuyerService.refundDtlList(paramMap);
			  
			  int refundDtlListCnt =  RefundBuyerService.refundDtlListCnt(paramMap);
			  
			  Map<String, Object> resultMap = new HashMap<String, Object>();
			  
			  resultMap.put("listrefund", refundBuyerlist);
			  resultMap.put("totalCount", refundDtlListCnt); 
				

		      resultMap.put("pageSize", pageSize);
			  resultMap.put("currentPage",currentPage);
			  
			  logger.info("+ End " + className + ".initComnCod");
			 
		return resultMap;
					
		}
	
	// 재고처리 (업데이트 부분)
	@RequestMapping("refundUpdate.do")
	@ResponseBody
	public Map<String, Object> refundUpdate(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
	    logger.info("@@@@@@@@@@@@@@@@@@@+ Start " + className + ".refundUpdate");
	    logger.info("    + Param :  " + paramMap);	
	    
	    RefundBuyerService.refundUpdate(paramMap); 
	    
	    Map<String, Object> returnmap = new HashMap<String, Object>(); 
	    returnmap.put("result", "SUCEESS");
	    
	    logger.info("@@@@@@@@@@@@@@@@@@@+ End " + className + ".refundUpdate");
	    
	    return returnmap;
	}	
	
}
