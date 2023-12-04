<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글</title>

<%
	String memId = (String)session.getAttribute("memId"); 			// 로그인 ID
	System.out.println("memId :: " + memId);
	
	String memNm = (String)session.getAttribute("memNm");			// 로그인 NAME
	System.out.println("memNm :: " + memNm);
	
	int brdSeq = Integer.parseInt(request.getParameter("brdSeq")); // 게시글 Sequence;
	System.out.println("brdSeq :: " + brdSeq);
%>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript">

	/* 초기화 */
	$(document).ready(function() {		
		var memId = "<%=memId%>";
		var memNm = "<%=memNm%>";
		var brdSeq = "<%=brdSeq%>";
		
		$("#brdSeq").val(brdSeq);
		$("#brdUpdId").val(memId);	
		
		if(brdSeq == 0){			
			$("#brdWriter").val(memId);			
			$("#brdWriterNm").val(memNm);			
			$("#headText").text("게시글 작성");			
			$("#trInsDt").css("display","none");
			$("#trHitCnt").css("display","none");
			
			$("#replyForm").hide();			
		} else {
			$("#rpyUpdId").val(memId);
			$("#rpyBrdSeq").val(brdSeq);
			$("#rpySeq").val(0);
			boardInfo();
		}
	});
	
	/* 게시글 조회 */
	function boardInfo() {
		$.ajax ({
			url			: "/board/boardInfo",  // controller의 Mapping 값과 일치
			data		: $("#boardForm").serialize(),
			dataType	: "JSON",
			cache		: false,
			async		: true,
			type		: "POST",
			success		: function(obj){
				boardInfoCallback(obj);
			},
			error		: function(xhr, status, error){}
		});
	}
	
	/* 게시글 조회 콜백 함수 */
	function boardInfoCallback(obj) {
		if(obj != null) {
			$("#brdWriter").val(obj.brdWriter);
			$("#brdWriterNm").val(obj.brdWriterNm);
			$("#brdInsDt").val(obj.brdInsDt);
			$("#brdHitCnt").val(obj.brdHitCnt);
			$("#brdSubject").val(obj.brdSubject);
			$("#brdContent").val(obj.brdContent);
			$("#brd_DelYn").val(obj.brd_DelYn);
			$("#headText").text("게시글 상세");
			
			replyInfo();
		} else {
			alert("게시글 정보 조회 실패");
			return;
		}
	}
	
	/* 댓글 조회 */
	function replyInfo(){
		$.ajax({
			url		: "/board/replyInfo",
			data	: $("#replyForm").serialize(),
			dataType: "JSON",
			cache	: false,
			async	: true,
			type	: "POST",
			success	: function(obj) {
				replyInfoCallback(obj);
			},
			error	: function(xhr, status, error) {}
		});
	}
	
	/* 댓글 조회 콜백 함수 */
	function replyInfoCallback(obj) {
		if(obj != null) {
			var state = obj.state;  
			
			alert("state :: " + state);
			
			if(state == "SUCCESS"){		// BoardServiceImpl => resultUtil.setState("SUCCESS")값
				var dataReply	= obj.data;
				var listReply	= dataReply.list;
				var listReplyLen= listReply.length;
				var strReply	= "";
				
				if(listReplyLen > 0) {
					var loginId = $("#brdUpdId").val();
					var spanCnt = listReplyLen + 1;
					
					strReply += "<tr>";
					strReply += "<th rowspan='" + spanCnt + "'>댓글</th>";
					
					for(var i=0; i<listReplyLen; i++) {
						var rpySeq		= listReply[i].rpySeq;
						var rpyBrdSeq	= listReply[i].rpyBrdSeq;
						var rpyWriter	= listReply[i].rpyWriter;
						var rpyContent	= listReply[i].rpyContent;
						var rpyInsDt	= listReply[i].rpyInsDt;
						
						if(i > 0) {
							strReply += "<tr>";
						}
						
						strReply += "<td><input id='rpyWriter_" + i + "' name='rpyWriter_" + i + "' value='" + rpyWriter + "' class='tbox05'/>:&nbsp;&nbsp;";
						strReply += "<input id='rpyContent_" + i + "' name='rpyContent_" + i + "' value='" + rpyContent + "' class='tbox06'/>";
						strReply += "<input id='rpyDate_" + i + "' name='rpyDate_" + i + "' value='" + rpyInsDt + "' class='tbox07'/>";
						strReply += "</td>";
						
						if(loginId == rpyWriter) {
							strReply += "<td><button type='button' class='btn2 dpink' style='width:25px;' onclick='javascript:replyDelete(" + rpySeq + ");'>X</button></td>";
						} else {
							strReply += "<td></td>"
						}
						strReply += "</tr>"
					}
					strReply += "<tr>";
					strReply += "<td><input id='rpyContent' name='rpyContent' value='' class='tbox04'/></td>";
					strReply += "<td><button type='button' class='btn2 dpink' style='width:65px;' onclick='javascript:replySave();'>저장</button></td>";
					strReply += "</tr>";
				} else {
					strReply += "<tr>";
					strReply += "<th>댓글</th>";
					strReply += "<td><input id='rpyContent' name='rpyContent' value='' class='tbox04'/></td>";
					strReply += "<td><button type='button' class='btn2 dpink' style='width:65px;' onclick='javascript:replySave();'>저장</button></td>";
					strReply += "</tr>";
				}
				
				$("#tbodyReply").html(strReply);
			} else{				
			}
		}
	}
	
	/* 게시판 목록 페이지 이동 */
	function viewBoardList(){
		location.href = "/board/viewBoardList";
	}
	
	/* 게시글 삭제 */
	function boardDelete() {
		boardSave('DEL');
	}
	
	/* 게시글 저장 */
	function boardSave(flag) {
		var brdSubject = $("#brdSubject").val();
		
		if(brdSubject == ""){
			alert("제목을 입력해 주세요.");
			$("#brdSubject").focus();
			return;
		}
		
		var confirmText = "저장하시겠습니까?";
		if(flag == "DEL"){
			confirmText = "삭제하시겠습니까?";
		}
		
		var yn = confirm(confirmText);
		
		if(yn){
			var loginId = $("#brdUpdId").val();
			var writerId = $("#brdWriter").val();
			
			if(loginId == writerId) {
				if(flag == "DEL") {
					$("#brdDelYn").val("Y");
				}
			} else {
				alert("본인글만 삭제 가능합니다!");
				return;
			}
			
			$.ajax({
				url		: "/board/boardSave",
				data	: $("#boardForm").serialize(),
				dataType: "JSON",
				cache	: false,
				async	: true,
				type	: "POST",
				success : function(obj){
					boardSaveCallback(obj);
				},
				error	: function(xhr, status, error) {}
			});
		}
	}
	
	/* 게시글 저장 콜백 함수 */
	function boardSaveCallback(obj) {
		if(obj != null){
			var result = obj.result;
			
			if(result == "SUCCESS"){
				alert("저장되었습니다.");
				viewBoardList();
			} else {
				alert("실패했습니다.");
				return;
			}
		}
	}
	
	/* 댓글 저장 */	
	function replySave(){
		var replyCont = $("#rpyContent").val();
		
		if(replyCont == ""){
			alert("내용을 입력해 주세요.");
			$("#rpyContent").focus();
			return;
		}
		
		var confirmText = "저장하시겠습니까?";
		
		var yn = confirm(confirmText);
		
		if(yn){
			$.ajax({
				url		: "/board/replySave",
				data	: $("#replyForm").serialize(),
				dataType: "JSON",
				cache	: false,
				async	: true,
				type	: "POST",
				success	: function(obj) {
					replySaveCallback(obj);
				},
				error	: function(xhr, status, error) {}
			});
		}
	}
	
	/* 댓글 저장 콜백 함수 */
	function replySaveCallback(obj) {
		if(obj != null){
			var result = obj.result;
			
			if(result == "SUCCESS"){
				alert("댓글이 저장되었습니다.");
				replyInfo();		// viewBoardList();
			} else {
				alert("댓글 저장 실패했습니다.");
				return;
			}
		}
	} // 2023-01-12 여기까지 작업함.
	
	/* 댓글 삭제 */
	function replyDelete(seq) {
		alert(seq);
		$("#rpySeq").val(seq);
		var confirmText = "댓글을 삭제하시겠습니까?";
		
		var yn = confirm(confirmText);
		
		if(yn) {
			$.ajax ({
				url		: "/board/replyDelete",
				data	: $("#replyForm").serialize(),
				dataType: "JSON",
				cache	: false,
				async	: true,
				type	: "POST",
				success	: function(obj){
					replyDeleteCallback(obj)},
				error	: function(xhr, status, error){}
			});
		}
	}
	
	
	/* 댓글 삭제 콜백 함수 */
	function replyDeleteCallback(obj){
		if(obj != null) {
			var result = obj.result;
			
			if(result == "SUCCESS"){
				alert("댓글이 삭제되었습니다.");
				replyInfo();
			}else {
				alert("실패하였습니다.");
				return;
			}
		}
	}

</script>
</head>
<body>
<div id="wrap">
	<div id="container">
		<div class="inner">
			<h2 id="headText">게시글</h2>
			<div class="btn_right mt15">
				<button type="button" class="btn_black mr5" onclick="javascript:viewBoardList();">목록</button>
			</div>
			<br>
			<form id="boardForm" name="boardForm">
				<input type="hidden" id="brdSeq" name="brdSeq" value=""/>
				<input type="hidden" id="brdDelYn" name="brdDelYn" value=""/>
				<input type="hidden" id="brdUpdId" name="brdUpdId" value=""/>
				<table width="100%" class="table02">
					<caption><strong><span class="t_red">*</span>표시는 필수 입력 사항입니다.</strong></caption>
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody id="tbody">
						<tr>
							<th>작성자ID<span class="t_red">*</span></th>
							<td><input id="brdWriter" name="brdWriter" value="" class="tbox02" readonly/></td>
							<th>작성자명<span class="t_red">*</span></th>
							<td><input id="brdWriterNm" name="brdWriterNm" value="" class="tbox02" readonly/></td>
						</tr>
						<tr id="trInsDt">
							<th>작성일자</th>
							<td colspan="3"><input id="brdInsDt" name="brdInsDt" value="" class="tbox03" readonly/></td>
						</tr>
						<tr id="trHitCnt">
							<th>조회수</th>
							<td colspan="3"><input type id="brdHitCnt" name="brdHitCnt" value=0 class="tbox03" readonly/></td>
						</tr>
						<tr>
							<th>제목<span class="t_red">*</span></th>
							<td colspan="3"><input id="brdSubject" name="brdSubject" value="" class="tbox03"/></td>
						</tr>
						<tr>
							<th>내용<span class="t_red">*</span></th>
							<td colspan="3"><textarea id="brdContent" name="brdContent" cols="90" rows="5" class="textarea02"></textarea></td>
						</tr>
					</tbody>
				</table>
			</form>
			
			<form id="replyForm" name="replyForm">
				<input type="hidden" id="rpyUpdId" name="rpyUpdId" value=""/>
				<input type="hidden" id="rpyBrdSeq" name="rpyBrdSeq" value=""/>
				<input type="hidden" id="rpySeq" name="rpySeq"/>
				<table width="100%" class="table04" border="1">
					<colgroup>
						<col width="20%">
						<col width="*">
						<col width="10%">
					</colgroup>
					<tbody id="tbodyReply">
					
					</tbody>
				</table>
			</form>
			<div class="btn_right mt15">
				<button type="button" class="btn black" onclick="javascript:boardDelete();">삭제</button>
				<button type="button" class="btn black" onclick="javascript:boardSave('UPD');">저장</button>			
			</div>
		</div>
	</div>
</div>
</body>
</html>