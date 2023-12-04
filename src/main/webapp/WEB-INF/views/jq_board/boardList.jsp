<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<%
	String memId = (String)session.getAttribute("memId");	// 로그인 ID
	String memNm = (String)session.getAttribute("memNm");	// 로그인 NAME
%>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/resources/js/jquery.js"></script>

<script type="text/javascript">
    
    /* 초기화 */
    $(document).ready(function(){
    	
    	$("#loginId").val("<%=memId%>");
    	$("#loginNm").val("<%=memNm%>");
    	
    	boardListSearch(1);
    });
    
    /* 회원정보 수정 화면 이동 */
    function viewMemberRegist(){
    	var memInfoFlag = "U";
    	location.href = "/member/viewMemberRegist?memInfoFlag=" + memInfoFlag ;
    	
    }
    
    /* 로그아웃 */
    function memberLogout(){
    	location.href = "/member/viewMemberLogin";
    }
    
    /* 게시글 상세 페이지 이동 */
    function viewBoardWrite(brdSeq){ 
        location.href = "/board/viewBoardWrite?brdSeq=" + brdSeq;
    }
    
    /* 검색구분 변경 이벤트 */
    function changeSearchType() {
    	var comboValue = $("#searchCombo").val();
    	
    	if(comboValue == ""){
    		$("#searchText").val("");
    	}
    }
    
    /* 로그인시 엔터키 이벤트 */
    function enterKey() {
    	if (window.event.keyCode == 13) {
    		boardListSearch(1);
        }
    }
 
    /* 게시판 목록 조회 */
    function boardListSearch(currentPageNo){
    	
    	if(currentPageNo === undefined){
            currentPageNo = "1";
        }
    	
    	$("#currentPageNo").val(currentPageNo);
    	
    	var searchCombo = $("#searchCombo").val();
    	var searchText = $("#searchText").val();
    	
    	if(searchText == ""){
    		searchCombo = "";
    	}

    	$("#searchType").val(searchCombo);
    	
        $.ajax({    
            url      : "/board/boardListSearch",
            data     : $("#boardForm").serialize(),
            dataType : "JSON",
            cache    : false,
            async    : true,
            type     : "POST",    
            success  : function(obj) {
                 boardListSearchCallback(obj);                
             },           
			error    : function(xhr, status, error) {}
             
		});
	}
    
    /* 게시판 목록 조회 콜백 함수 */
    function boardListSearchCallback(obj){
    	
    	var state = obj.state;
    	
    	if(state == "SUCCESS"){
    		
            var data = obj.data;            
            var list = data.list;
            var listLen = list.length;        
            var totalCount = data.totalCount;
            var pagination = data.pagination;
            
            var str = "";

            if(listLen >  0){

                for(var a=0; a<listLen; a++){

                    var brdSeq      = list[a].brdSeq;
                    var brdSubject  = list[a].brdSubject;
                    var brdContent  = list[a].brdContent;
                    var brdWriter   = list[a].brdWriter;
                    var brdWriterNm = list[a].brdWriterNm; 
                    var brdHitCnt   = list[a].brdHitCnt; 
                    var brdDelYn    = list[a].brdDelYn;
                    var brdInsId    = list[a].brdInsId; 
                    var brdInsDt    = list[a].brdInsDt;
                    var brdUpdId    = list[a].brdUpdId; 
                    var brdUpdDt    = list[a].brdUpdDt;

                    str += "<tr>";
                    str += "<td>"+ brdSeq +"</td>";
                    str += "<td onclick='javascript:viewBoardWrite("+ brdSeq +");' style='cursor:Pointer'>"+ brdSubject +"</td>";
                    str += "<td>"+ brdHitCnt +"</td>";
                    str += "<td>"+ brdWriterNm +"</td>";
                    str += "<td>"+ brdUpdDt +"</td>";  
                    str += "</tr>";
                    
                } 
            } else {
                
            	str += "<tr>";
                str += "<td colspan='5'>등록된 글이 존재하지 않습니다.</td>";
                str += "<tr>";
            }
            
            $("#tbody").html(str);
            $("#totalCount").text(totalCount);
            $("#pagination").html(pagination);
    	}
    	
        $("#tbody").html(str);
    }
    
</script>
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="inner">        
            <h2>게시판</h2>            
            <form id="boardForm" name="boardForm">
            	<input type="hidden" id="functionName" name="functionName" value="boardListSearch" />
                <input type="hidden" id="currentPageNo" name="currentPageNo" value="1" />
                <input type="hidden" id="searchType" name="searchType" value="" />
                
                <input type="hidden" id="loginId" name="loginId" value="" />
                <div align="right">
					<input type="text" id="loginNm" name="loginNm" style="width:80px;border:none;font-size:15px;font-weight:bold;" value="" readonly/>님 환영합니다.&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn black mr5" onclick="javascript:viewMemberRegist();">회원정보수정</button>
					<button type="button" class="btn black mr5" onclick="javascript:memberLogout();">로그아웃</button>
                </div>
                
                <div class="btn_right mt15" style="height:50px; border:1px solid grey;">
                	<br>
	                <select id="searchCombo" onchange="changeSearchType();">
	            		<option value="">전체</option>
	            		<option value="wrt">작성자</option>
	            		<option value="sbj">제목</option>
	            	</select>
	            	<input type="search" id="searchText" name="searchText" style="width:500px;height:20px;valign:center;font-size:13px;vertical-align:middle;" onkeyup="enterKey();" value="">
            		<button type="button" class="btn black mr15" onclick="javascript:boardListSearch(1);">조회</button>
            		<br>
            	</div>
            	<br>
                <div class="page_info">
                    <span class="total_count"><strong>전체</strong> : <span id="totalCount" class="t_red">0</span>개</span>
                </div>
                
                <table width="100%" class="table01">
                    <colgroup>
                        <col width="10%" />
                        <col width="25%" />
                        <col width="10%" />
                        <col width="15%" />
                        <col width="20%" />
                    </colgroup>
                    <thead>        
                        <tr>
                            <th>글번호</th>
                            <th>제목</th>
                            <th>조회수</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                    
                    </tbody>    
                </table>
            </form>            
            <div class="btn_right mt15">
                <button type="button" class="btn black mr5" onclick="javascript:viewBoardWrite(0);">작성하기</button>
            </div>
            
            <div id="pagination"></div>
        </div>
    </div>
</div>
</body>
</html>