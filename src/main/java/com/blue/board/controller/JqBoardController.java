package com.blue.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blue.board.common.ResultUtil;
import com.blue.board.service.JqBoardService;
import com.blue.board.vo.JqBoardVO;
import com.blue.board.vo.JqReplyVO;

@Controller
@RequestMapping(value = "/board")
public class JqBoardController {

	private static final Logger logger = LoggerFactory.getLogger(JqBoardController.class);

	@Autowired
	private JqBoardService boardService;

	@RequestMapping(value = "/viewBoardList")
	public String viewBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("##### 1-1. viewBoardList Start");
		return "jq_board/boardList";
	}

	@RequestMapping(value = "/viewBoardWrite")
	public String viewboardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("##### 1-2. viewBoardWrite Start");
		return "jq_board/boardWrite";
	}

	@RequestMapping(value = "/boardListSearch")
	@ResponseBody
	public ResultUtil boardListSearch(HttpServletRequest request, HttpServletResponse response, JqBoardVO jqBoardVO)
			throws Exception {
		logger.debug("##### 1-3. boardListSearch Start");
		ResultUtil result = boardService.boardListSearch(jqBoardVO);

		return result;
	}

	@RequestMapping(value = "/boardSave")
	@ResponseBody
	public JqBoardVO boardSave(HttpServletRequest request, HttpServletResponse response, JqBoardVO jqBoardVO)
			throws Exception {
		logger.debug("##### 1-4. boardSave Start");
		JqBoardVO saveVO = boardService.boardSave(jqBoardVO);
		return saveVO;
	}

	@RequestMapping(value = "/boardInfo")
	@ResponseBody
	public JqBoardVO boardInfo(HttpServletRequest request, HttpServletResponse response, JqBoardVO jqBoardVO)
			throws Exception {
		logger.debug("##### 1-5. boardInfo Start");
		JqBoardVO InfoVO = boardService.boardInfo(jqBoardVO);
		return InfoVO;
	}

	@RequestMapping(value = "/replySave")
	@ResponseBody
	public JqReplyVO replySave(HttpServletRequest request, HttpServletResponse response, JqReplyVO jqReplyVO)
			throws Exception {
		logger.debug("##### 1-6. replySave Start");
		JqReplyVO reSaveVO = boardService.replySave(jqReplyVO);
		return reSaveVO;
	}

	@RequestMapping(value = "/replyInfo")
	@ResponseBody
	public ResultUtil replyInfo(HttpServletRequest request, HttpServletResponse response, JqReplyVO jqReplyVO)
			throws Exception {
		logger.debug("##### 1-7. replyInfo Start");
		ResultUtil reInfoVO = boardService.replyInfo(jqReplyVO);
		return reInfoVO;
	}

	@RequestMapping(value = "/replyDelete")
	@ResponseBody
	public JqReplyVO replyDelete(HttpServletRequest request, HttpServletResponse response, JqReplyVO jqReplyVO)
			throws Exception {
		logger.debug("##### 1-8. replyDelete");
		
		logger.debug("##### aaaa :"+jqReplyVO.getRpySeq());
		
		JqReplyVO reDeleteVO = boardService.replyDelete(jqReplyVO);
		return reDeleteVO;
	}

} // ENd