package com.blue.board.service;

import com.blue.board.common.ResultUtil;
import com.blue.board.vo.JqBoardVO;
import com.blue.board.vo.JqReplyVO;

public interface HaBoardService {

	// �Խñ� ��� ��ȸ
	public ResultUtil boardListSearch(JqBoardVO jqBoardVO) throws Exception;

	// �Խñ� ����
	public JqBoardVO boardSave(JqBoardVO jqBoardVO) throws Exception;

	// �Խñ� ����
	public JqBoardVO boardInfo(JqBoardVO jqBoardVO) throws Exception;

	// ��� ���
	public ResultUtil replyInfo(JqReplyVO jqReplyVO) throws Exception;

	// ��� ����
	public JqReplyVO replySave(JqReplyVO jqReplyVO) throws Exception;

	// ��� ����
	public JqReplyVO replyDelete(JqReplyVO jqReplyVO) throws Exception;
}
