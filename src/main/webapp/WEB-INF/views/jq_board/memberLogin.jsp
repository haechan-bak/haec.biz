<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/resources/js/jquery.js"></script>

<script type="text/javascript">
    
    /* 초기화 */
    $(document).ready(function(){
    	alert("환영합니다!");
    });
    
    /* 회원가입 화면 이동 */
    function viewMemberRegist(){    	
    	var memInfoFlag = "I";
    	location.href = "/member/viewMemberRegist?memInfoFlag=" + memInfoFlag;
    }
    
    /* 로그인시 엔터키 이벤트 */
    function enterKey() {
    	if (window.event.keyCode == 13) {
    		memberLogin();
        }
    }
    
    /* 회원 로그인 */
    function memberLogin(){
    	
    	var id	= $("#memId").val();
    	var pwd = $("#memPwd").val();
    	
    	if(id == ""){
    		alert("ID 입력해 주세요.");
    		$("#memId").focus();
    		return;
    	}else if(pwd == ""){
    		alert("비밀번호 입력해 주세요.");
    		$("#memPwd").focus();
    		return;
    	}

		$.ajax({    
		   url      : "/member/memberLogin",
		   data     : $("#loginForm").serialize(),
		   dataType : "JSON",
		   cache    : false,
		   async    : true,
		   type     : "POST",    
		   success  : function(obj) {
			   memberLoginCallback(obj);                
		    },           
		   error    : function(xhr, status, error) {}
		    
		});
    }
    
    /* 회원 로그인 콜백 함수 */
    function memberLoginCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){
            	var logId = $("#memId").val()
                alert(logId + " 님, 로그인에 성공하였습니다.");
                viewBoardList();
            } else if(result == "DROP"){
            	alert("탈퇴 회원입니다.");
            	
            	$("#memId").val("");
            	$("#memPwd").val("");
            	
                return;
            } else {
                alert("ID/PASSWORD를 확인하세요.");
                return;
            }
        }
    }
    
    /* 게시판 목록 이동 */
    function viewBoardList(){
    	location.href = "/board/viewBoardList";
    }

    
</script>
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="inner">        
            <h2></h2>
            <form id="loginForm" name="loginForm">
                <table width="100%" class="table02">
                <caption><B>로그인</B></caption>
                    <colgroup>
                        <col width="20%">
                        <col width="*">
                    </colgroup>
                    <tbody id="tbody">
                        <tr>
                            <th align="right">ID : </th>
                            <td><input type="text" id="memId" name="memId" value=""  maxlength="20" style="width:200px;" placeholder="ID를 입력하세요"/></td>
                        </tr>
                        <tr>
                            <th align="right">PASSWORD : </th>
                            <td><input type="password" id="memPwd" name="memPwd" value=""  maxlength="20" style="width:200px;" placeholder="비밀번호를 입력하세요" onkeyup="enterKey();"/></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <div class="btn_right mt15">
            	<button type="button" class="btn black mr5" onclick="javascript:viewMemberRegist();">회원가입</button>
                <button type="button" class="btn black mr5" onclick="javascript:memberLogin();">로그인</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>