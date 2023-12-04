package com.blue.board.service;

import com.blue.board.common.ResultUtil;
import com.blue.board.vo.JqReplyVO;
import com.blue.board.vo.JqBoardVO;

public interface JqBoardService {
	
	// 게시판 목록 조회
	public ResultUtil boardListSearch(JqBoardVO jqBoardVO) throws Exception;
	
	// 게시글 조회
	public JqBoardVO boardInfo(JqBoardVO jqBoardVO) throws Exception;
	
	// 게시글 저장(등록, 수정, 삭제)
	public JqBoardVO boardSave(JqBoardVO jqBoardVO) throws Exception;
	
	// 뎃글 저장(등록)
	public JqReplyVO replySave(JqReplyVO jqReplyVO) throws Exception;
	
	// 댓글 조회
	public ResultUtil replyInfo(JqReplyVO jqReplyVO) throws Exception;
	
	// 뎃글 삭제
	public JqReplyVO replyDelete(JqReplyVO jqReplyVO) throws Exception;

}
