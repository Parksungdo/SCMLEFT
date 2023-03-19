<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Scm Left :: 반품 내역관리</title>

<jsp:include page="/WEB-INF/view/common/common_include.jsp"></jsp:include>
<!-- duDatePicker import css-->
<link rel="stylesheet" type="text/css" href="${CTX_PATH}/css/duDatePicker/duDatepicker.css">
<!-- duDatePicker import js -->
<script type="text/javascript" src="${CTX_PATH}/js/duDatePicker/duDatepicker.js"></script>

<script type="text/javascript">
	
	//제품정보 페이징 처리
	var pageSize = 5;
	var pageBlock = 5;

	var VueArea;
	var VueEdit;
	
	/*OnLoad event */
	$(function() {
		
		init();
		
		//전체 목록 조회
		refundList();
		
		//버튼이벤트
		clickButton();
		
		//clickckbox();	//체크박스
		
		//버튼 이벤트 등록(검색)	
		board_search();
		
	});
	
	function init() {
		
		VueArea = new Vue({
			el: '#initRefundList',
			
			data:{
				listitem: [],
				action:"",
				pagenavi: ""
			},
			
			methods:{
				detailview: function(re_CODE){
					alert(re_CODE);
					refundDetail(re_CODE);
				}
			}
		});
		
		VueEdit = new Vue({
			el: '#layer1',
			data: {
				re_CODE:"",
				loginId:"",
				model_NAME:"",
				re_AMT:"",
				re_DATE:"",
				pd_PRICE:"",
				re_TYPE:"",
				flag:true
			}
		});
	}
	
	function clickButton(){
		$('a[name=btn]').click(function(e){
			e.preventDefault();
			
			var btnId = $(this).attr('id');
			switch(btnId){
			case 'savebtn':
				saveModal();
				break;
			case 'closebtn':
				gfCloseModal();
				break;
				
				
			}
		})
	}
	//검색
	function board_search(){
		$('a[name=btn]').click(function(e) {
			e.preventDefault();

			var btnId = $(this).attr('id');
			
			switch (btnId) {
 				case 'searchBtn' :
 					board_search(); 
					break;
			}
		});
	
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//주문내역 전체 조회
	function refundList(currentPage) {
				
		currentPage = currentPage || 1;
		
		console.log(pageSize);
		var param = {
					
				currentPage : currentPage,
				pageSize : pageSize,
		
		}
		var resultCallBack = function(data){
			console.log(JSON.parse(data));
			refundListResult(JSON.parse(data), currentPage);
		}
		callAjax("/pur/refundList.do", "post", "text", true, param, resultCallBack );
	} 	
	//전체  페이징 처리
	function refundListResult(data, currentPage) {
		console.log(data);

		VueArea.listitem = data.listRefund; // 내용

		//console.log("Test");
		var totalCnt = data.totalCnt;
		
		VueArea.pagenavi = getPaginationHtml(currentPage,totalCnt, pageSize, pageBlock, 'refundList');
		
		
		$("#currentPage").val(currentPage);
	}

	
	//-----------------------------------------------------------------------------------------------------------------------------
	//주문내역 검색	`			
	function refundSearch(currentPage) {

			currentPage = currentPage || 1;

			var searchKey = document.getElementById("searchKey").value;
			var searchWord = document.getElementById("searchWord").value;

			var sdate = $("#sdate").val();
			var edate = $("#edate").val();

			if ($("#clickckbox").is(":checked")) {
				checkBoxStatus = "checked";

			} else {
				//alert("unChecked");
				checkBoxStatus = "unChecked";
			}
			var param = {

				searchKey : searchKey,
				searchWord : searchWord,
				currentPage : currentPage,
				pageSize : pageSize,
				sdate : sdate,
				edate : edate,
				checkBoxStatus : checkBoxStatus
			}
			//console.log("sdate : " + sdate + ", edate : " + edate);

			var resultCallBackSearch = function(data) {
				checkBoxListResult(data, currentPage);

			}

			callAjax("/pur/refundList.do", "post", "text", true, param, resultCallBackSearch);
		}

	
	//-----------------------------------------------------------------------------------------------------------
	//상세보기 (모달팝업)
	function refundDetail(recode){
		
		var param = {
				recode : recode
		};

		var resultCallBack = function(data){
		//console.log(data);

			console.log("resultCallBack : " + JSON.stringify(data));
			refundDetailResult(data);
		};

		callAjax("/pur/refundDetail.do", "post", "json", true, param, resultCallBack);
	}
	//상세보기 콜백
	function refundDetailResult(data) {

		console.log("e : " + JSON.stringify(data));
		
		VueArea.action ="U";
		
		gfModalPop("#layer1");
		
		refundModal(data.result);
			
	}
	
	//임원 승인 
	function refundModal(object){
		
		//console.log(object);
		
		VueEdit.re_CODE = object.re_CODE;	
		VueEdit.re_TYPE = object.re_TYPE;
		
		if(VueEdit.re_TYPE == 1){
			VueEdit.flag =false;
		}else{
			VueEdit.flag = true;
		}
		
		
	}

	//----------------------------------------------------------------------------------------------------------------
	//확인버튼 클릭
	function saveModal(){
		
		var param={
				action : VueArea.action,
				recode : VueEdit.re_CODE,
				retype : VueEdit.re_TYPE
					
		}
		console.log(param);
		var saveCallback = function(saveReturn){
			
			console.log("saveCallback:" + JSON.stringify(saveReturn));
			
			if(saveReturn.result == "UPDATED"){
				alert("저장 완료");
				gfCloseModal();
				refundList();
			}
			else{
				alert("저장 실패");
			}
		}
		
		callAjax("/pur/refundUpdate.do", "post", "json", true, param, saveCallback);
	}
</script>
<body>
<form id="myForm" action="" method="">
		<input type="hidden" id="currentPage" value=""> 
		<input type="hidden" id="currentPageModal" value=""> 

		<div id="mask"></div>
		<div id="wrap_area">


			<h2 class="hidden">header 영역</h2>
			<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
			<h2 class="hidden">컨텐츠 영역</h2>
			<div id="container">
				<ul>
					<li class="lnb">
						<!-- lnb 영역 --> <jsp:include
							page="/WEB-INF/view/common/lnbMenu.jsp"></jsp:include> <!--// lnb 영역 -->
					</li>
					<li class="contents">
						<!-- contents -->
						<h3 class="hidden">contents 영역</h3> <!-- content -->
						<div class="content">

							<p class="Location">
								<a href="/dashboard/dashboard.do" class="btn_set home">메인으로</a> 
								<a class="btn_nav">기업고객</a>
								 <span class="btn_nav bold">반품 내역 관리</span> 
								<a href="" class="btn_set refresh">새로고침</a>
							</p>
						
							<p class="conTitle">
								<span>반품 내역 관리</span>
							</p>

							<div id="initRefundList">
								<div class="conTitle" style="margin: 0 25px 10px 0;"
									align=center>

									<select name="searchKey" id="searchKey">
										<option value="cpname" id="option1">업체명</option>
										<option value="pdname" id="option2">제품명</option>
									</select> <input type="text" style="width: 160px; height: 30px;"
										id="searchWord" name="searchWord"
										onkeypress="if( event.keyCode == 13 ){refundList();}">
									<input type="date" style="width: 160px; height: 30px;"
										id="sdate" name="sdate"
										onkeypress="if( event.keyCode == 13 ){refundList();}">
									<input type="date" style="width: 160px; height: 30px;"
										id="edate" name="edate"
										onkeypress="if( event.keyCode == 13 ){refundList();}">
									<a href="javascript:searchrefundList()" class="btnType blue"
										onkeydown="enterKey()" name="search"><span>검 색</span></a>
									<!-- enter입력하면 검색실행   -->

								</div>
								<table class="col">
									<caption>caption</caption>
									<colgroup>
										<col width="15%">
										<col width="15%">
										<col width="15%">
										<col width="10%">
										<col width="20%">
										<col width="10%">
										<col width="10%">
									</colgroup>

									<thead>
										<tr>
											<th scope="col">반품번호</th>
											<th scope="col">반품회사</th>
											<th scope="col">반품제품</th>
											<th scope="col">반품수량</th>
											<th scope="col">날짜</th>
											<th scope="col">반품금액</th>
											<th scope="col">임원승인</th>
										</tr>
									</thead>
									<tbody id="listRefund" v-for="(item,index) in listitem"
										v-if="listitem.length">
										<tr @click="detailview(item.re_CODE)">
											<td>{{item.re_CODE}}</td>
											<td>{{item.loginID}}</td> 
											<td>{{item.model_NAME}}</td>
											<td>{{item.re_AMT}}</td>
											<td>{{item.re_DATE}}</td>
											<td>{{item.pd_PRICE}}</td>
											<td><template v-if="item.re_TYPE == '1'">승인</template> <template
													v-else-if="item.re_TYPE == '0'">반려</template> <template
													v-else>대기</template></td>
										</tr>
									</tbody>
								</table>

								<!-- 페이징 처리 -->
								<div class="paging_area" id="pagingnavi" v-html="pagenavi"></div>
							</div>
						</div> <!--// content -->							
						<h3 class="hidden">풋터 영역</h3> <jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>							
					</li>
				</ul>
			</div>
		</div>
							
						
<!-- ---Modal 시작------------------------------------------------------- -->
	<div id="layer1" class="layerPop layerType2" style="width: 500px; height: auto;">
		<input type="hidden" id="refund" name="modal">
			<dl>
			<dt>
				<strong>반품 내역 확인서</strong>
			</dt>
			<dd class="content">
				<!-- 테이블 페이지 네비게이션 영역 -->
				<div>
				<h2>승인 하시겠습니까?</h2>
				</div>	

				<!-- e : 여기에 내용입력 -->

				<div class="btn_areaC mt30">
					<a href="" class="btnType gray" id="savebtn" name="btn" v-show="flag"><span>입금 완료</span></a>
					<a href="" class="btnType gray" id="closebtn" name="btn"><span>취소</span></a>
				</div>
			</dd>
		</dl>
		<a href="" class="closePop"><span class="hidden">닫기</span></a>
		</div>
							
	
	</form>
</body>
</html>