package com.blue.board.service;

import com.blue.board.common.ResultUtil;
import com.blue.board.vo.JqBoardVO;
import com.blue.board.vo.JqReplyVO;

public interface HaBoardService {

	// 게시글 목록 조회
	public ResultUtil boardListSearch(JqBoardVO jqBoardVO) throws Exception;

	// 게시글 저장
	public JqBoardVO boardSave(JqBoardVO jqBoardVO) throws Exception;

	// 게시글 정보
	public JqBoardVO boardInfo(JqBoardVO jqBoardVO) throws Exception;

	// 댓글 목록
	public ResultUtil replyInfo(JqReplyVO jqReplyVO) throws Exception;

	// 댓글 저장
	public JqReplyVO replySave(JqReplyVO jqReplyVO) throws Exception;

	// 댓글 삭제
	public JqReplyVO replyDelete(JqReplyVO jqReplyVO) throws Exception;
}
