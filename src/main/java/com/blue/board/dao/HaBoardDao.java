package com.blue.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blue.board.vo.JqBoardVO;

@Repository
public class HaBoardDao {

	private static final Logger log = LoggerFactory.getLogger(HaBoardDao.class);

	@Resource
	private SqlSession sqlSession;

	private static final String NAMESPACE = ".jqBoardMapper";

	public int boardListCnt(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### boardDao JqBoardVO Start");
		return sqlSession.selectOne(NAMESPACE + ".boardListCnt", jqBoardVO);
	}

	public List<JqBoardVO> boardListSearch(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### boardDao boardListSearch Start");
		return sqlSession.selectList(NAMESPACE + ".boardListSearch", jqBoardVO);
	}

	public int boardSave(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### boardDao boardSave Start");
		return sqlSession.update(NAMESPACE + ".boardSave", jqBoardVO);
	}

	public int boardAddHit(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### boardDao boardAddHit Start");
		return sqlSession.selectOne(NAMESPACE + ".boardAddHit", jqBoardVO);
	}

	public JqBoardVO boardInfo(JqBoardVO jqBoardVO) throws Exception {
		log.debug("### boardDao boardInfo Start");
		return sqlSession.selectOne(NAMESPACE + ".boardInfo", jqBoardVO);
	}
}
