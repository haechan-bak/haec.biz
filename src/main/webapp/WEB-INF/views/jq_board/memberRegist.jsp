<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보</title>

<%	// Strign memInfoFlag에 getParameter를 request한 "memInfoFalg" 값이 담긴다.//    
    String memInfoFlag = request.getParameter("memInfoFlag");
	System.out.println("memInfoFlag :: "+ memInfoFlag);
	
	String sessionMemId = (String)session.getAttribute("memId");
	System.out.println("sessionMemId :: "+ sessionMemId);
%>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript">
    
    /* 초기화 */
    $(document).ready(function(){
    	alert("### memberRegist.jsp ###");
    	var memFlag = "<%=memInfoFlag%>";
    	$("#memFlag").val(memFlag);

    	if(memFlag == "U"){
    		$("#sessionMemId").val("<%=sessionMemId%>");
    		memberInfo();
    	}
    });
    
    /* 회원정보 조회 */
    function memberInfo(){

        $.ajax({    
            url      : "/member/memberInfo",
            data     : $("#memberForm").serialize(),
            dataType : "JSON",
            cache    : false,
            async    : true,
            type     : "POST",    
            success  : function(obj) {
                 memberInfoCallback(obj);                
             },           
            error    : function(xhr, status, error) {}
             
          });
     }
    
    /* 로그인 화면 이동 */
    function viewMemberLogin(){
    	location.href = "/member/viewMemberLogin";
    }
    
    /* 게시판 목록 이동 */
    function viewBoardList(){
    	location.href = "/board/viewBoardList";
    }
    
    /* 회원정보 저장 */
    function saveMemberInfo(){
    	
    	var name	= $("#memNm").val();
    	var id		= $("#memId").val();;
    	var pwd 	= $("#memPwd").val();
    	var use 	= $("input[name=memUseY]:checked").val();
    	
    	if(name == ""){
    		alert("이름을 입력해 주세요.");
    		$("#memNm").focus();
    		return;
    	}else if(id == ""){
    		alert("ID 입력해 주세요.");
    		$("#memId").focus();
    		return;
    	}else if(pwd == ""){
    		alert("비밀번호 입력해 주세요.");
    		$("#memPwd").focus();
    		return;
    	}else if(pwd.length < 5){
    		alert("비밀번호는 5자리 이상입니다.");
    		$("#memPwd").focus();
    		return;
    	}
    	
    	var yn = confirm("회원 정보를 저장하시겠습니까?");        
        if(yn){
                
            $.ajax({    
                
               url      : "/member/memberSave",
               data     : $("#memberForm").serialize(),
               dataType : "JSON",
               cache    : false,
               async    : true,
               type     : "POST",    
               success  : function(obj) {
                    memberSaveCallback(obj);                
                },           
               error    : function(xhr, status, error) {}
                
            });
        }
    }
    
    /** 회원정보 저장 콜백 함수 */
    function memberSaveCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("회원정보 저장을 성공하였습니다.");
                
                var viewPage = $("#memFlag").val();
                if(viewPage == "U"){
                	 viewBoardList();
                }else{
                	viewMemberLogin();
                }
            } else if(result == "DUP") { 
            	alert("사용중인 ID입니다.");    
                return;
            } else {
                alert("회원정보 저장을 실패하였습니다.");    
                return;
            }
        }
    }
    
    /** 회원정보 조회 콜백 함수 */
    function memberInfoCallback(obj){
    
        if(obj != null){        
        	
        	$("#memNm").val(obj.memNm);
        	$("#memId").val(obj.memId);
        	
        	var checkVal = obj.memUseYn;
        	$("input:radio[name='memUseYn']:radio[value='"+ checkVal + "']").prop('checked', true);
        	
        	$("#memId").attr("readonly", true);
        	$("#memNm").attr("readonly", true);
                
        } else {                
	        alert("회원정보 조회 실패하였습니다.");    
	        return;

        }
    }
    
        
    
</script>
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="inner">        
            <h2></h2>
            <form id="memberForm" name="memberForm">
            
            	<input type="hidden" id="memFlag" name="memFlag" value="" />
            	<input type="hidden" id="sessionMemId" name="sessionMemId" value="" />
            	
                <table width="100%" class="table02">
                <caption><B>회원정보</B></caption>
                    <colgroup>
                        <col width="20%">
                        <col width="*">
                    </colgroup>
                    <tbody id="tbody">
                        <tr>
                            <th align="right">NAME : </th>
                            <td><input type="text" id="memNm" name="memNm" value="" maxlength="10" style="width:200px;" placeholder="이름을 입력하세요"/></td>
                        </tr>
                        <tr>
                            <th align="right">ID : </th>
                            <td><input type="text" id="memId" name="memId" value=""  maxlength="20" style="width:200px;" placeholder="ID를 입력하세요"/></td>
                        </tr>
                        <tr>
                            <th align="right">PASSWORD : </th>
                            <td><input type="password" id="memPwd" name="memPwd" value=""  maxlength="20" style="width:200px;" placeholder="비밀번호를 입력하세요"/></td>
                        </tr>
                        <tr>
                            <th align="right">USE : </th>
                            <td>
                            	<input type="radio" id="memUseY" name="memUseYn" value="Y" checked> 로그인 가능
                            	<input type="radio" id="memUseN" name="memUseYn" value="N"> 로그인 불가능
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <div class="btn_right mt15">
            	<button type="button" class="btn black mr5" onclick="javascript:viewMemberLogin();">로그인</button>
                <button type="button" class="btn black mr5" onclick="javascript:saveMemberInfo();">저장</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>