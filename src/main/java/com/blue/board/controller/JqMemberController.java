package com.blue.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blue.board.common.EncryptUtil;
import com.blue.board.service.JqMemberService;
import com.blue.board.vo.JqMemberVO;

/**
 * 회원정보 Controller
 * @author  JPJ
 * @since   2022.09.14
 * @version 1.0
 * @see
 * 
 *    수정일          수정자                           수정내용
 * ----------  ------  ----------------------------------
 * 2022.09.14   JPJ      최초생성
 * 2022.09.15   JPJ      controller 정리
 * 
 */
@Controller
@RequestMapping(value = "/member")
public class JqMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
    private JqMemberService jqMemberService;
	
	/**
	 * 로그인 화면 이동
	 * @param
	 * @return
	 * @exception
	 * 
	 */
	@RequestMapping( value = "/viewMemberLogin")
    public String viewMemberLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        
		logger.debug("## JqMemberController - viewMemberLogin Start");
        
        //세션 삭제
        session.invalidate();

        return "jq_board/memberLogin";
    }
	
	/**
	 * 회원정보 화면 이동
	 * @param
	 * @return
	 * @exception
	 * 
	 */
	@RequestMapping( value = "/viewMemberRegist")
    public String viewMemberRegist(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
		logger.debug("## JqMemberController - viewMemberRegist Start");

        return "jq_board/memberRegist";
    }
	
	/**
	 * 회원정보 저장 (등록, 수정)
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    @RequestMapping( value = "/memberSave")
    @ResponseBody
    public JqMemberVO memberSave(HttpServletRequest request, HttpServletResponse response, JqMemberVO jqMemberVO) throws Exception{
        
    	logger.debug("## JqMemberController - memberSave Start");
    	
    	String memPwdText = request.getParameter("memPwd");
    	
    	// 비밀번호 암호화
		String encryptPwd = EncryptUtil.sha256(memPwdText);
    	jqMemberVO.setMemPwd(encryptPwd);
    	
    	String memFlag = request.getParameter("memFlag");	// 등록, 수정 구분
    	
    	JqMemberVO memSaveVO = jqMemberService.memberSave(jqMemberVO, memFlag);
    	
    	logger.debug("## JqMemberController - memberSave End");
        
        return memSaveVO;
    }  
    
    /**
	 * 회원 로그인
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    @RequestMapping( value = "/memberLogin")
    @ResponseBody
    public JqMemberVO memberLogin(HttpServletRequest request, HttpServletResponse response, JqMemberVO jqMemberVO,  HttpSession session) throws Exception{
        
    	logger.debug("## JqMemberController - memberLogin Start");
    	
    	String memPwdText = request.getParameter("memPwd");
    	
    	// 비밀번호 암호화 **
		String encryptPwd = EncryptUtil.sha256(memPwdText);
    	jqMemberVO.setMemPwd(encryptPwd);

    	JqMemberVO memLoginVO = jqMemberService.memberLogin(jqMemberVO, session);
    	
    	logger.debug("## JqMemberController - memberLogin End");
        
        return memLoginVO;
    }
    
    /**
	 * 회원 정보 조회
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    @RequestMapping( value = "/memberInfo")
    @ResponseBody
    public JqMemberVO memberInfo(HttpServletRequest request, HttpServletResponse response, JqMemberVO jqMemberVO) throws Exception{
        
    	logger.debug("## JqMemberController - memberInfo Start");
    	
    	String memId = request.getParameter("sessionMemId");
    	jqMemberVO.setMemId(memId);

    	JqMemberVO memLoginVO = jqMemberService.memberInfo(jqMemberVO);
    	
    	logger.debug("## JqMemberController - memberInfo End");
        
        return memLoginVO;
    }
}
