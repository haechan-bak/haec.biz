package com.blue.board.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.board.common.PagingUtil;
import com.blue.board.common.ResultUtil;
import com.blue.board.dao.HaBoardDao;
import com.blue.board.vo.CommonVO;
import com.blue.board.vo.JqBoardVO;

@Service
public class HaBoardServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(HaBoardServiceImpl.class);

	@Autowired
	private HaBoardDao boardDao;

	public ResultUtil boardListSearch(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### ServiceImpl boardListSearch Start");
		ResultUtil result = new ResultUtil();
		int totalCount = 0;
		totalCount = boardDao.boardListCnt(jqBoardVO);
		log.debug("### ServiceImpl totalCount :: " + totalCount);
		CommonVO commonVO = new CommonVO();

		if (totalCount != 0) {
			CommonVO commPageVO = new CommonVO();
			commPageVO.setFunctionName(jqBoardVO.getFunctionName());
			commPageVO.setCurrentPageNo(jqBoardVO.getCurrentPageNo());
			commPageVO.setCountPerList(10);
			commPageVO.setCountPerPage(10);
			commPageVO.setTotalCount(totalCount);
			commonVO = PagingUtil.setPageUtil(commPageVO);
		}

		jqBoardVO.setStartNo(commonVO.getStartNo());
		log.debug("### ServiceImpl jqBoardVO.setStartNo :: " + jqBoardVO.getStartNo());
		jqBoardVO.setEndNo(commonVO.getEndNo());
		log.debug("### ServiceImpl jqBoardVO.setEndNo :: " + jqBoardVO.getEndNo());

		List<JqBoardVO> list = boardDao.boardListSearch(jqBoardVO);
		log.debug("### SErviceImpl List<JqBoardVO> :: " + list);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("Pagination", commonVO.getPagination());

		result.setData(resultMap);
		result.setState("SUCCESS");

		return result;
	}

	public JqBoardVO boardSave(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### ServiceImpl boardSave Start");
		JqBoardVO boardSaveVO = new JqBoardVO();

		int saveCnt = 0;
		saveCnt = jqBoardVO.getBrdSeq();
		log.debug("### ServiceImpl boardSave - saveCnt :: " + saveCnt);
		if (saveCnt > 0) {
			boardSaveVO.setResult("SUCCESS");
		} else {
			boardSaveVO.setResult("FAIL");
		}
		log.debug("### ServiceImpl boardSave Start");
		return boardSaveVO;
	}

	public JqBoardVO boardInfo(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### ServiceImpl boardInfo Start");
		JqBoardVO boardInfoVO = new JqBoardVO();
		int brdSeq = jqBoardVO.getBrdSeq();
		log.debug("### ServiceImpl boardInfo brdSeq :: " + brdSeq);
		if (brdSeq > 0) {
			boardDao.boardAddHit(jqBoardVO);
		}
		boardInfoVO = boardDao.boardInfo(jqBoardVO);
		return boardInfoVO;
	}
}
