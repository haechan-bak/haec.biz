package com.blue.board.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.board.dao.JqMemberDao;
import com.blue.board.vo.JqMemberVO;

/**
 * 회원정보 ServiceImpl
 * @author  JPJ
 * @since   2022.09.14
 * @version 1.0
 * @see
 * 
 *    수정일          수정자                           수정내용
 * ----------  ------  ----------------------------------
 * 2022.09.14   JPJ      최초생성
 * 2022.09.15   JPJ      controller 정리
 * 
 */

@Service
public class JqMemberServiceImpl implements JqMemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
    @Autowired
    private JqMemberDao jqMemberDao;
    
    /**
	 * 회원정보 저장 (등록, 수정)
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    public JqMemberVO memberSave(JqMemberVO memberVO, String memFlag) throws Exception {
 
    	logger.debug("## JqMemberServiceImpl - memberSave Start");
    	
    	JqMemberVO memberResultVO = new JqMemberVO();
    	
    	if("I".equals(memFlag)){
    		JqMemberVO memberCountVO = new JqMemberVO();
    		logger.debug("## memberVO.getMemId() :: " + memberVO.getMemId());
    		memberCountVO.setMemId(memberVO.getMemId());
    		memberCountVO.setMemPwd("");
    		memberResultVO = jqMemberDao.memberLogin(memberCountVO);
    		
    		if(memberResultVO != null) {
    			logger.debug("## memberResultVO != null");
    			memberResultVO.setResult("DUP");
    			return memberResultVO;
    		}else {
    			logger.debug("## memberResultVO == null");
    		}
    	}
    	
    	memberResultVO = new JqMemberVO();
    	
    	int saveCnt = 0;
        
    	saveCnt = jqMemberDao.memberSave(memberVO, memFlag);
 
        if (saveCnt > 0) {
        	memberResultVO.setResult("SUCCESS");
        } else {
        	memberResultVO.setResult("FAIL");
        }
        
        logger.debug("## JqMemberServiceImpl - memberSave End");
 
        return memberResultVO;
    }
    
    /**
	 * 회원 로그인
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    public JqMemberVO memberLogin(JqMemberVO memberVO, HttpSession session) throws Exception {
 
    	logger.debug("## JqMemberServiceImpl - memberLogin Start");
    	
    	JqMemberVO memberResultVO = new JqMemberVO();
 
    	JqMemberVO memberLoginVO = jqMemberDao.memberLogin(memberVO);
    	
    	if(memberLoginVO != null) {
    		
        	String memId = memberLoginVO.getMemId();
        	String memNm = memberLoginVO.getMemNm();
        	String useYn = memberLoginVO.getMemUseYn();
        	
            if("Y".equals(useYn)) {
            	memberResultVO.setResult("SUCCESS");
            	
            	session.setAttribute("memId", memId);
            	session.setAttribute("memNm", memNm);
            }else {
            	memberResultVO.setResult("DROP");
            }
        	
    	} else {
    		memberResultVO.setResult("FAIL");
    	}
        
        logger.debug("## JqMemberServiceImpl - memberLogin End");
 
        return memberResultVO;
    }
    
    /**
	 * 회원 정보 조회
	 * @param
	 * @return
	 * @exception
	 * 
	 */
    public JqMemberVO memberInfo(JqMemberVO memberVO) throws Exception {
 
    	logger.debug("## JqMemberServiceImpl - memberInfo Start");
    	
    	JqMemberVO memberResultVO = new JqMemberVO();
 
    	memberResultVO = jqMemberDao.memberLogin(memberVO);

        logger.debug("## JqMemberServiceImpl - memberInfo End");
 
        return memberResultVO;
    }
}
