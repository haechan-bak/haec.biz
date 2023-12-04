package com.blue.board.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blue.board.vo.JqMemberVO;

/**
 * ȸ������ Dao
 * @author  JPJ
 * @since   2022.09.14
 * @version 1.0
 * @see
 * 
 *    ������          ������                           ��������
 * ----------  ------  ----------------------------------
 * 2022.09.14   JPJ      ���ʻ���
 * 2022.09.15   JPJ      Dao ����
 * 
 */
@Repository
public class JqMemberDao {
	
	private static final Logger logger = LoggerFactory.getLogger(JqMemberDao.class);
	
	@Resource
    private SqlSession sqlSession;
	
	private static final String NAMESPACE = "jqMemberMapper";
	
	/**
	 * ȸ������ ���� (���, ����)
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    public int memberSave(JqMemberVO memberVO, String memFlag) throws Exception {
    	
    	logger.debug("## JqMemberDao - memberSave");

    	if("I".equals(memFlag)){	// I : ��� , U : ����
    		return sqlSession.insert(NAMESPACE + ".memberSave", memberVO);
    	}else {
    		return sqlSession.update(NAMESPACE + ".memberUpdate", memberVO);
    	}
        
    }
    
    /**
	 * ȸ�� �α��� / ȸ������ ��ȸ
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
