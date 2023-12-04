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
import com.blue.board.service.HaBoardService;
import com.blue.board.vo.JqBoardVO;
import com.blue.board.vo.JqReplyVO;

@Controller
@RequestMapping(value = "/")
public class HaBoardController {

	private static final Logger log = LoggerFactory.getLogger(HaBoardController.class);

	@Autowired
	private HaBoardService boardService;

	@RequestMapping(value = "/viewBoardList")
	public String viewBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("### viewBoardList Start");
		return "board/boardList";
	}

	@RequestMapping(value = "/viewBoardWrite")
	public String viewBoardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("### viewBoardWrite");
		return "board/boardWrite";
	}

	@RequestMapping(value = "/boardListSearch")
	@ResponseBody
	public ResultUtil boardListSearch(HttpServletRequest request, HttpServletResponse response, JqBoardVO jqBoardVO)
			throws Exception {
		log.debug("### boardListSearch Start");
		ResultUtil result = boardService.boardListSearch(jqBoardVO);
		return result;
	}

	@RequestMapping(value = "/boardSave")
	@ResponseBody
	public JqBoardVO boardSave(HttpServletRequest request, HttpServletResponse response, JqBoardVO jqBoardVO)
			throws Exception {
		log.debug("### boardSave Start");
		JqBoardVO saveVO = boardService.boardSave(jqBoardVO);
		return saveVO;
	}

	@RequestMapping(value = "/boardInfo")
	@ResponseBody
	public JqBoardVO boardInfo(HttpServletRequest request, HttpServletResponse response, JqBoardVO jqBoardVO)
			throws Exception {
		log.debug("### boardInfo Start");
		JqBoardVO infoVO = boardService.boardInfo(jqBoardVO);
		return infoVO;
	}

	@RequestMapping(value = "/replyInfo")
	@ResponseBody
	public ResultUtil replyInfo(HttpServletRequest request, HttpServletResponse response, JqReplyVO jqReplyVO)
			throws Exception {
		log.debug("### replyInfo Start");
		ResultUtil replyInfoUtil = boardService.replyInfo(jqReplyVO);
		return replyInfoUtil;
	}

	@RequestMapping(value = "/replySave")
	@ResponseBody
	public JqReplyVO replySave(HttpServletRequest request, HttpServletResponse response, JqReplyVO jqReplyVO)
			throws Exception {
		log.debug("### replySave Start");
		JqReplyVO replySaveVO = boardService.replySave(jqReplyVO);
		return replySaveVO;
	}

	@RequestMapping(value = "/replyDelete")
	@ResponseBody
	public JqReplyVO replyDelete(HttpServletRequest request, HttpServletResponse response, JqReplyVO jqReplyVO)
			throws Exception {
		log.debug("### replyDelete Start");
		JqReplyVO replyDelVO = boardService.replyDelete(jqReplyVO);
		return replyDelVO;
	}

}
