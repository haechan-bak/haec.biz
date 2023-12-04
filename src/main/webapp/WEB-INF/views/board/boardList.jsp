<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/resources/js/jquery.js"></script>

<script type="text/javascript">
    
    $(document).ready(function(){        
        //getBoardList();
        console.log("aaaaaaaaaaaaaa");
    });
    
    /** 게시판 - 상세 페이지 이동 */
    function goBoardDetail(boardSeq){                
        location.href = "/board/boardDetail?boardSeq="+ boardSeq;
    }
    
    /** 게시판 - 작성 페이지 이동 */
    function goBoardWrite(){        
        location.href = "/board/boardWrite";
    }
 
    function getBoardList(currentPageNo){
    	
    	if(currentPageNo === undefined){
            currentPageNo = "1";
        }
    	
    	$("#currentPageNo").val(currentPageNo);
    	
    	//var comboT = $("#searchCombo").val();

    	//$("#searchType").val(comboT);
    	//var searchT = $("#searchType").val();
    	
    	
        $.ajax({    
            
            url      : "/board/getBoardList",
            data     : $("#boardForm").serialize(),
            dataType : "JSON",
            cache    : false,
            async    : true,
            type     : "POST",    
            success  : function(obj) {
                 getBoardListCallback(obj);                
             },           
            error    : function(xhr, status, error) {}
             
          });
     }
    
    function getBoardListCallback(obj){
    	
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

                    var boardSeq      = list[a].boardSeq; 
                    var boardReRef    = list[a].boardReRef; 
                    var boardReLev    = list[a].boardReLev; 
                    var boardReSeq    = list[a].boardReSeq; 
                    var boardWriter   = list[a].boardWriter; 
                    var boardSubject  = list[a].boardSubject; 
                    var boardContent  = list[a].boardContent; 
                    var boardHits     = list[a].boardHits;
                    var delYn         = list[a].delYn; 
                    var insUserId     = list[a].insUserId;
                    var insDate       = list[a].insDate; 
                    var updUserId     = list[a].updUserId;
                    var updDate       = list[a].updDate;
                    
                    str += "<tr>";
                    str += "<td>"+ boardSeq +"</td>";
                    str += "<td onclick='javascript:goBoardDetail("+ boardSeq +");' style='cursor:Pointer'>"+ boardSubject +"</td>";;
                    str += "<td>"+ boardHits +"</td>";
                    str += "<td>"+ boardWriter +"</td>";
                    str += "<td>"+ insDate +"</td>";  
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


        
        
        //$("#tbody").html(str);
    }
    
</script>
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="inner">        
            <h2>게시글 목록</h2>            
            <form id="boardForm" name="boardForm">
            	<input type="hidden" id="functionName" name="functionName" value="getBoardList" />
                <input type="hidden" id="currentPageNo" name="currentPageNo" value="1" />
                <!-- <input type="hidden" id="searchType" name="searchType" value="" />  -->
                
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
                
                <select id="searchType" name="searchType">
            		<option value="">전체</option>
            		<option value="title">제목</option>
            		<option value="writer">작성자</option>
            	</select>
            	<input type="text" id="searchText" name="searchText" value="" />
            </form>            
            <div class="btn_right mt15">
            	<button type="button" class="btn black mr5" onclick="javascript:getBoardList();">조회</button>
                <button type="button" class="btn black mr5" onclick="javascript:goBoardWrite();">작성하기</button>
            </div>
            
            <div id="pagination"></div>
        </div>
    </div>
</div>
</body>
</html>