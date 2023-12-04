package com.blue.board.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blue.board.vo.JqMemberVO;

/**
 * 회원정보 Dao
 * @author  JPJ
 * @since   2022.09.14
 * @version 1.0
 * @see
 * 
 *    수정일          수정자                           수정내용
 * ----------  ------  ----------------------------------
 * 2022.09.14   JPJ      최초생성
 * 2022.09.15   JPJ      Dao 정리
 * 
 */
@Repository
public class JqMemberDao {
	
	private static final Logger logger = LoggerFactory.getLogger(JqMemberDao.class);
	
	@Resource
    private SqlSession sqlSession;
	
	private static final String NAMESPACE = "jqMemberMapper";
	
	/**
	 * 회원정보 저장 (등록, 수정)
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    public int memberSave(JqMemberVO memberVO, String memFlag) throws Exception {
    	
    	logger.debug("## JqMemberDao - memberSave");

    	if("I".equals(memFlag)){	// I : 등록 , U : 수정
    		return sqlSession.insert(NAMESPACE + ".memberSave", memberVO);
    	}else {
    		return sqlSession.update(NAMESPACE + ".memberUpdate", memberVO);
    	}
        
    }
    
    /**
	 * 회원 로그인 / 회원정보 조회
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    public JqMemberVO memberLogin(JqMemberVO memberVO) throws Exception {
    	
    	logger.debug("## JqMemberDao - memberLogin");
    	
        return sqlSession.selectOne(NAMESPACE + ".memberLogin", memberVO);
    }
}
