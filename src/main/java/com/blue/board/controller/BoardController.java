package com.blue.board.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.blue.board.vo.BoardVO;
import com.blue.board.common.ResultUtil;
import com.blue.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
 
    @Autowired
    private BoardService boardService;
 
    @RequestMapping( value = "/boardList")
    public String getBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        logger.debug("## boardList");
        
        // 2022203232449 controller 占쎈쐻占쎈솂占쎈닰占쎌굲 占쎈쐻占쎈솯�ⓦ끉�굲
        
        
        return "board/boardList";
    }
 
    @RequestMapping(value = "/getBoardList")
    @ResponseBody
    /*
    public List<BoardVO> getBoardList(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception {
    	logger.debug("## BoardController : getBoardList");
        List<BoardVO> boardList = boardService.getBoardList(boardVO);;
        return boardList;
    }*/
    public ResultUtil getBoardList(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO, HttpSession session) throws Exception {
    	logger.debug("## BoardController : getBoardList");
    	logger.debug("######## getCurrentPageNo: "+ boardVO.getCurrentPageNo());
    	logger.debug("######## getFunctionName : "+ boardVO.getFunctionName());
    	logger.debug("## searchType : " + boardVO.getSearchType());
    	logger.debug("## searchText : " + boardVO.getSearchText());
        ResultUtil resultUtil = boardService.getBoardList(boardVO);
        String stauts = resultUtil.getState();
        logger.debug("## aaaa :: " + stauts);
        
        //세션 테스트용 추가
        session.setAttribute("id", "hgd");
        String id = (String)session.getAttribute("id");
        logger.debug("## 세션테스트 :: " + id);
        
        //세션 삭제
        //session.invalidate();
        
        return resultUtil;
    }
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뼓占쎈쿈占쎌굲 */
    @RequestMapping( value = "/boardDetail")
    public String boardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "board/boardDetail";
    }    
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌돳  */
    @RequestMapping(value = "/getBoardDetail")
    @ResponseBody
    public BoardVO getBoardDetail(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception {
    	logger.debug("## BoardController : getBoardDetail");
    	boardVO.setSearchType(request.getParameter("search_type"));
    	boardVO.setBoardSeq(Integer.parseInt(request.getParameter("board_seq")));
    	BoardVO boardDetVO = boardService.getBoardDetail(boardVO);
 
        return boardDetVO;
    }
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈솙占쎈닰占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뼓占쎈쿈占쎌굲 */
    @RequestMapping( value = "/boardWrite")
    public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "board/boardWrite";
    }
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� */
    @RequestMapping( value = "/insertBoard")
    @ResponseBody
    public BoardVO insertBoard(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception{
        
    	boardVO.setBoardSubject(request.getParameter("board_subject"));
    	boardVO.setBoardWriter(request.getParameter("board_writer"));
    	boardVO.setBoardContent(request.getParameter("board_content"));
    	
    	BoardVO boardInsVO = boardService.insertBoard(boardVO);
        
        return boardInsVO;
    }
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 */
    @RequestMapping( value = "/deleteBoard")
    @ResponseBody
    public BoardVO deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception{
        
    	boardVO.setBoardSeq(Integer.parseInt(request.getParameter("board_seq")));
    	
    	BoardVO boardDelVO = boardService.deleteBoard(boardVO);
        
        return boardDelVO;
    }
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뼓占쎈쿈占쎌굲 */
    @RequestMapping( value = "/boardUpdate")
    public String boardUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "board/boardUpdate";
    }
    
    /** 占쎈쐻占쎈셾占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 - 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 */
    @RequestMapping( value = "/updateBoard")
    @ResponseBody
    public BoardVO updateBoard(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception{
        
    	boardVO.setSearchType(request.getParameter("search_type"));
    	boardVO.setBoardSeq(Integer.parseInt(request.getParameter("board_seq")));
    	boardVO.setBoardSubject(request.getParameter("board_subject"));
    	boardVO.setBoardContent(request.getParameter("board_content"));
    	
    	BoardVO boardUpdVO = boardService.updateBoard(boardVO);
        
        return boardUpdVO;
    }
    
    
    @RequestMapping(value = "/getBoardListApi")
    @ResponseBody
    public List<BoardVO> getBoardListApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	logger.debug("## BoardController : getBoardListApi Start");
    	
    	List<BoardVO> boardListVO = null;
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	
    	paramMap.put("schStartDt", request.getParameter("date1"));
    	paramMap.put("schEndDt",request.getParameter("date2"));

    	boardListVO = boardService.getBoardListApi(paramMap);
    	logger.debug("## BoardController : getBoardListApi End");
        return boardListVO;
    }
    
    // pull 테스트입니다.
}

