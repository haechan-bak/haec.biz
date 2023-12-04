package com.blue.board.service;

import javax.servlet.http.HttpSession;
import com.blue.board.vo.JqMemberVO;

public interface JqMemberService {
	
	// ȸ������ ���� (���, ����)
	public JqMemberVO memberSave(JqMemberVO memberVO, String memFlag) throws Exception;
	
	// ȸ�� �α���
	public JqMemberVO memberLogin(JqMemberVO memberVO, HttpSession session) throws Exception;
	
	// ȸ�� ���� ��ȸ
	public JqMemberVO memberInfo(JqMemberVO memberVO) throws Exception;

}
