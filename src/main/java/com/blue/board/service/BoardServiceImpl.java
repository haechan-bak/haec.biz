package com.blue.board.service;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.board.common.PagingUtil;
import com.blue.board.common.ResultUtil;
import com.blue.board.dao.BoardDao;
import com.blue.board.vo.BoardVO;
import com.blue.board.vo.CommonVO;


@Service
public class BoardServiceImpl implements BoardService {
 
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
    @Autowired
    private BoardDao boardDao;

    /** 게시판 - 목록 조회 */    
    /*
    public List<BoardVO> getBoardList(BoardVO boardVO) throws Exception {
    	logger.debug("## BoardServiceImpl : getBoardList");
        return boardDao.getBoardList(boardVO);
    }*/

    public ResultUtil getBoardList(BoardVO boardVO) throws Exception {
 
        ResultUtil resultUtil = new ResultUtil();
 
        CommonVO commonVO = new CommonVO();
 
        int totalCount = boardDao.getBoardCnt(boardVO);
        logger.debug("totalCount :: "+totalCount);
        if (totalCount != 0) {
            CommonVO commonPageVO = new CommonVO();
            commonPageVO.setFunctionName(boardVO.getFunctionName());
            commonPageVO.setCurrentPageNo(boardVO.getCurrentPageNo());
            commonPageVO.setCountPerPage(10);
            commonPageVO.setCountPerList(10);
            commonPageVO.setTotalCount(totalCount);
            commonVO = PagingUtil.setPageUtil(commonPageVO);
        }
 
        boardVO.setStartNo(commonVO.getStartNo());
        boardVO.setEndNo(commonVO.getEndNo());
 
        List<BoardVO> list = boardDao.getBoardList(boardVO);
 
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", list);
        resultMap.put("totalCount", totalCount);
        resultMap.put("pagination", commonVO.getPagination());
 
        resultUtil.setData(resultMap);
        resultUtil.setState("SUCCESS");
 
        return resultUtil;
    }
    
    /** 게시판 - 상세 조회 */
    public BoardVO getBoardDetail(BoardVO boardVO) throws Exception {
    	logger.debug("## BoardServiceImpl : getBoardDetail");
    	BoardVO boardDetVO = new BoardVO();
 
        String searchType = boardVO.getSearchType();
        logger.debug("## searchType ::" +  searchType);
        if("S".equals(searchType)){
            
            int updateCnt = boardDao.updateBoardHits(boardVO);
            logger.debug("## updateCnt ::" +  updateCnt);
            if (updateCnt > 0) {
            	boardDetVO = boardDao.getBoardDetail(boardVO);
            }
            
        } else {
            
        	boardDetVO = boardDao.getBoardDetail(boardVO);
        }
 
        return boardDetVO;
    }
 
    /** 게시판 - 등록 */
    public BoardVO insertBoard(BoardVO boardVO) throws Exception {
 
    	BoardVO boardInsVO = new BoardVO();
 
    	int insertCnt = 0;
        
    	insertCnt = boardDao.insertBoard(boardVO);
 
        if (insertCnt > 0) {
        	boardInsVO.setResult("SUCCESS");
        } else {
        	boardInsVO.setResult("FAIL");
        }
 
        return boardInsVO;
    }
 
    /** 게시판 - 삭제 */
    public BoardVO deleteBoard(BoardVO boardVO) throws Exception {
 
    	BoardVO boardDelVO = new BoardVO();
 
        int deleteCnt = boardDao.deleteBoard(boardVO);
 
        if (deleteCnt > 0) {
        	boardDelVO.setResult("SUCCESS");
        } else {
        	boardDelVO.setResult("FAIL");
        }
 
        return boardDelVO;
    }
 
    /** 게시판 - 수정 */
    public BoardVO updateBoard(BoardVO boardVO) throws Exception {
 
    	BoardVO boardUpdVO = new BoardVO();
 
        int updateCnt = boardDao.updateBoard(boardVO);
 
        if (updateCnt > 0) {
        	boardUpdVO.setResult("SUCCESS");
        } else {
        	boardUpdVO.setResult("FAIL");
        }
 
        return boardUpdVO;
    }
    
    public List<BoardVO> getBoardListApi(Map<String,Object> param) throws Exception {

        List<BoardVO> list = boardDao.getBoardListApi(param);

        return list;
    }
}
