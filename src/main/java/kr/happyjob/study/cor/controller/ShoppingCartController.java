package kr.happyjob.study.cor.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.happyjob.study.cor.service.ShoppingCartService;

@Controller
@RequestMapping("/cor/")
public class ShoppingCartController {
	@Autowired
	ShoppingCartService scs;
	
	// 장바구니 현황 페이지 이동
	@RequestMapping("shoppingCart.do")
	public String moveSoppingCartPage(Model model, HttpSession session)  throws Exception {	
		String page = "login/login";
		
		if(session.getAttribute("loginId") != null) page = "cor/shoppingCart";
		this.scs.backController(session, model, 1);
		
		return page;
	}
	
	// Vue 장바구니 검색
	@ResponseBody
	@PostMapping("searchCart")
	public HashMap<String, Object> searchCart(HttpSession session, @RequestParam HashMap<String, Object> map) {
		map.put("loginId", session.getAttribute("loginId"));
		this.scs.backController(session, map, 0);
		
		return map;
	}
	
	// 선택 항목 장바구니 목록 삭제
	@ResponseBody
	@PostMapping("delBasketProduct")
	public HashMap<String, Object> deleteSoppingCart(HttpSession session, @RequestParam HashMap<String, Object> map) {
		map.put("loginId", session.getAttribute("loginId"));
		this.scs.backController(session, map, 1);
		
		return map;
	}

	// 주문하기	
	@ResponseBody
	@PostMapping("insJorder")
	public HashMap<String, Object> insertJorderInfo(HttpSession session, @RequestParam HashMap<String, Object> map) {
		map.put("loginId", session.getAttribute("loginId"));
		this.scs.backController(session, map, 2);
		
		return map;
	}
	
	// Vue 장바구니 목록 삭제
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("/delBasketProduct_vue")
	public Map<String, Object> deleteSoppingCart_Vue(@RequestParam(required = false) Map<String, Object> map,  HttpSession session) throws Exception {
		HashMap<String, Object> newMap = new HashMap<String, Object>();
		JSONParser parse = new JSONParser();
		Object object = parse.parse(map.get("list").toString());
		
		map.put("list", object);
		map = (Map<String, Object>) map.get("list");
		
		System.err.println("  deleteSoppingCart_Vue  param check :: " + map);
		
		Iterator<String> iterKey = map.keySet().iterator();

		while (iterKey.hasNext()) {
			String key = iterKey.next();
			
			newMap.put(key.toLowerCase(), map.get(key));
		}
		
		for(int i = 0; i < newMap.size(); i++) {
			System.err.println("     deleteSoppingCart_Vue    newMap  ::   size Data :::: " + newMap.get(Integer.toString(i)));
			this.scs.backController(session, (HashMap<String, Object>) newMap.get(Integer.toString(i)), 3);
		}
		
		return newMap;
	}
	
	// Vue 주문하기 삭제
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("/insJorder_vue")
	public Map<String, Object> insertJorderInfo_vue(@RequestParam(required = false) Map<String, Object> map,  HttpSession session) throws Exception {
		HashMap<String, Object> newMap = new HashMap<String, Object>();
		JSONParser parse = new JSONParser();
		Object object = parse.parse(map.get("list").toString());
		
		map.put("list", object);
		map = (Map<String, Object>) map.get("list");
		
		
		System.err.println("  insJorder_vue  param check :: " + map);
		
		Iterator<String> iterKey = map.keySet().iterator();

		while (iterKey.hasNext()) {
			String key = iterKey.next();
			
			newMap.put(key.toLowerCase(), map.get(key));
		}
		
		this.scs.backController(session, newMap, 4);
//		
//		for(int i = 0; i < newMap.size(); i++) {
//			System.err.println("     insJorder_vue    newMap  ::   size Data :::: " + newMap.get(Integer.toString(i)));
//			this.scs.backController(session, (HashMap<String, Object>) newMap.get(Integer.toString(i)), 4);
//		}
		
		return newMap;
	}
}
