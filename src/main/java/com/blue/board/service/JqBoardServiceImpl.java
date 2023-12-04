package com.blue.board.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.board.common.PagingUtil;
import com.blue.board.common.ResultUtil;
import com.blue.board.dao.JqBoardDao;
import com.blue.board.vo.CommonVO;
import com.blue.board.vo.JqReplyVO;
import com.blue.board.vo.JqBoardVO;

/**
 * 게시판 ServiceImpl
 * 
 * @author JPJ
 * @since 2022.09.14
 * @version 1.0
 * @see
 * 
 *      수정일 수정자 수정내용 ---------- ------ ----------------------------------
 *      2022.09.14 JPJ 최초생성 2022.09.15 JPJ ServiceImpl 정리
 * 
 */
@Service
public class JqBoardServiceImpl implements JqBoardService {

	private static final Logger logger = LoggerFactory.getLogger(JqBoardServiceImpl.class);

	@Autowired
	private JqBoardDao boardDao;

	/**
	 * 게시판 목록 조회 @param @return @exception
	 * 
	 */
	public ResultUtil boardListSearch(JqBoardVO jqBoardVO) throws Exception {

		logger.debug("## JqBoardServiceImpl - boardListSearch Start");

		ResultUtil resultUtil = new ResultUtil();

		CommonVO commonVO = new CommonVO();

		int totalCount = boardDao.boardListCnt(jqBoardVO); // SQL 결과 => totalCount : 2
		logger.debug("## totalCount :: " + totalCount);

		if (totalCount != 0) {
			CommonVO commonPageVO = new CommonVO();
			commonPageVO.setFunctionName(jqBoardVO.getFunctionName());
			logger.debug("##### 2-3. setFunctionName :: " + commonPageVO.getFunctionName());
			commonPageVO.setCurrentPageNo(jqBoardVO.getCurrentPageNo());
			logger.debug("##### 2-4. setCurrentPageNo :: " + commonPageVO.getCurrentPageNo());
			commonPageVO.setCountPerPage(2);
			logger.debug("##### 2-5. setCountPerList :: " + commonPageVO.getCountPerList());
			commonPageVO.setCountPerList(10);
			logger.debug("##### 2-6. setCountPerPage :: " + commonPageVO.getCountPerPage());
			commonPageVO.setTotalCount(totalCount);
			logger.debug("##### 2-7. setTotalCount :: " + totalCount);
			commonVO = PagingUtil.setPageUtil(commonPageVO);
			logger.debug("##### 2-8. PagingUtil :: " + commonVO);
		}

		jqBoardVO.setStartNo(commonVO.getStartNo());
		jqBoardVO.setEndNo(commonVO.getEndNo());

		List<JqBoardVO> list = boardDao.boardListSearch(jqBoardVO);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list); // list=[com.blue.board.vo.JqBoardVO@706ea551,
										// com.blue.board.vo.JqBoardVO@3e1ebcf0]
		resultMap.put("totalCount", totalCount); // totalCount=2
		resultMap.put("pagination", commonVO.getPagination()); // <<, >>
		logger.debug("##### resultMap :: " + resultMap);

		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");

		logger.debug("## JqBoardServiceImpl - boardListSearch End");

		return resultUtil;
	}

	/**
	 * 게시글 조회 @param @return @exception
	 * 
	 */
	public JqBoardVO boardInfo(JqBoardVO jqBoardVO) throws Exception {

		logger.debug("## JqBoardServiceImpl - boardInfo Start");

		JqBoardVO boardResultVO = new JqBoardVO();

		int brdSeq = jqBoardVO.getBrdSeq();

		logger.debug("## brdSeq :: " + brdSeq);

		if (brdSeq > 0) {
			boardDao.boardAddHit(jqBoardVO);
		}

		boardResultVO = boardDao.boardInfo(jqBoardVO);

		logger.debug("## JqBoardServiceImpl - boardInfo End");

		return boardResultVO;
	}

	/**
	 * 게시글 저장(등록, 수정, 삭제) @param @return @exception
	 * 
	 */
	public JqBoardVO boardSave(JqBoardVO jqBoardVO) throws Exception {

		logger.debug("## JqBoardServiceImpl - boardSave Start");

		JqBoardVO boardResultVO = new JqBoardVO();

		int saveCnt = 0;

		saveCnt = boardDao.boardSave(jqBoardVO);

		if (saveCnt > 0) {
			boardResultVO.setResult("SUCCESS");
		} else {
			boardResultVO.setResult("FAIL");
		}

		logger.debug("## JqBoardServiceImpl - boardSave End");

		return boardResultVO;
	}

	/**
	 * 댓글 저장(등록) @param @return @exception
	 * 
	 */
	public JqReplyVO replySave(JqReplyVO jqReplyVO) throws Exception {

		logger.debug("## JqBoardServiceImpl - replySave Start");

		JqReplyVO replyResultVO = new JqReplyVO();

		int saveCnt = 0;

		saveCnt = boardDao.replySave(jqReplyVO);

		if (saveCnt > 0) {
			replyResultVO.setResult("SUCCESS");
		} else {
			replyResultVO.setResult("FAIL");
		}

		logger.debug("## JqBoardServiceImpl - replySave End");

		return replyResultVO;
	}

	/**
	 * 댓글 조회 @param @return @exception
	 * 
	 */
	public ResultUtil replyInfo(JqReplyVO jqReplyVO) throws Exception {

		logger.debug("## JqBoardServiceImpl - replyInfo Start");

		ResultUtil resultUtil = new ResultUtil();

		int rpyBrdSeq = jqReplyVO.getRpyBrdSeq();
		logger.debug("## rpyBrdSeq :: " + rpyBrdSeq);

		List<JqReplyVO> list = boardDao.replyInfo(jqReplyVO);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("list", list);

		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");

		// replyResultVO = boardDao.replyInfo(jqReplyVO);

		logger.debug("## JqBoardServiceImpl - replyInfo End");

		return resultUtil;
	}

	/**
	 * 댓글 삭제 @param @return @exception
	 * 
	 */
	public JqReplyVO replyDelete(JqReplyVO jqReplyVO) throws Exception {

		logger.debug("## JqBoardServiceImpl - replyDelete Start");

		JqReplyVO replyResultVO = new JqReplyVO();

		int saveCnt = 0;

		int aaa = jqReplyVO.getRpySeq();
		
		logger.debug("## aaa :: " + aaa);
		
		saveCnt = boardDao.replyDelete(jqReplyVO);
		logger.debug("## saveCnt :: " + saveCnt);

		if (saveCnt > 0) {
			replyResultVO.setResult("SUCCESS");
		} else {
			replyResultVO.setResult("FAIL");
		}

		logger.debug("## JqBoardServiceImpl - replyDelete End");

		return replyResultVO;
	}
}
