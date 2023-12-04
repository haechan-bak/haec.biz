package com.blue.board.dao;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blue.board.vo.BoardVO;
//import com.blue.board.form.BoardForm;
 
@Repository
public class BoardDao {
 
	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
	
	@Resource
    private SqlSession sqlSession;
	
	private static final String NAMESPACE = "boardMapper";
	
	/** 게시판 - 목록 수 */
    public int getBoardCnt(BoardVO boardVO) throws Exception {
 
        return sqlSession.selectOne(NAMESPACE + ".getBoardCnt", boardVO);
    }

    /** 게시판 - 목록 조회  */
    public List<BoardVO> getBoardList(BoardVO boardVO) throws Exception {
    	logger.debug("## BoardDao : getBoardList");
        return sqlSession.selectList(NAMESPACE + ".getBoardList", boardVO);
    }
    
    /** 게시판 - 조회 수 수정  */
    public int updateBoardHits(BoardVO boardVO) throws Exception {
        
        return sqlSession.update(NAMESPACE + ".updateBoardHits", boardVO);
    }
    
    /** 게시판 - 상세 조회  */
    public BoardVO getBoardDetail(BoardVO boardVO) throws Exception {
        
        return sqlSession.selectOne(NAMESPACE + ".getBoardDetail", boardVO);
    }
    
    /** 게시판 - 등록  */    
    public int insertBoard(BoardVO boardVO) throws Exception {
        return sqlSession.insert(NAMESPACE + ".insertBoard", boardVO);
    }
    
    /** 게시판 - 삭제  */
    public int deleteBoard(BoardVO boardVO) throws Exception {
        
        return sqlSession.delete(NAMESPACE + ".deleteBoard", boardVO);
    }
    
    /** 게시판 - 수정  */
    public int updateBoard(BoardVO boardVO) throws Exception {
        
        return sqlSession.update(NAMESPACE + ".updateBoard", boardVO);
    }
    
    public List<BoardVO> getBoardListApi(Map<String,Object> param) throws Exception {
    	logger.debug("## BoardDao : getBoardListApi");
        return sqlSession.selectList(NAMESPACE + ".getBoardListApi", param);
    }
}
