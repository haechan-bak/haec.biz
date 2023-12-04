package com.blue.board.service;

import javax.servlet.http.HttpSession;
import com.blue.board.vo.JqMemberVO;

public interface JqMemberService {
	
	// 회원정보 저장 (등록, 수정)
	public JqMemberVO memberSave(JqMemberVO memberVO, String memFlag) throws Exception;
	
	// 회원 로그인
	public JqMemberVO memberLogin(JqMemberVO memberVO, HttpSession session) throws Exception;
	
	// 회원 정보 조회
	public JqMemberVO memberInfo(JqMemberVO memberVO) throws Exception;

}
