package com.blue.board.vo;
 
import java.util.Date;
 
public class BoardVO extends CommonVO {
 
	/*
    int board_seq;
    int board_re_ref;
    int board_re_lev;
    int board_re_seq;
    String board_writer;
    String board_subject;
    String board_content;
    int board_hits;
    String del_yn;
    String ins_user_id;
    Date ins_date;
    String upd_user_id;
    Date upd_date;
    */
    
	/*ÀÌ°Å ¹Ù²ã ºÃ¾î¿ä vo by ÅÂ°æ*/
	
	int boardSeq;
    int boardReRef;
    int boardReLev;
    int boardReSeq;
    String boardWriter;
    String boardSubject;
    String boardContent;
    int boardHits;
    String delYn;
    String insUserId;
    String insDate;
    String updUserId;
    String updDate;
    
	String searchType;
	String searchText;
	
    public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	String result;
    
    public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
    
    public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getBoardReRef() {
		return boardReRef;
	}

	public void setBoardReRef(int boardReRef) {
		this.boardReRef = boardReRef;
	}

	public int getBoardReLev() {
		return boardReLev;
	}

	public void setBoardReLev(int boardReLev) {
		this.boardReLev = boardReLev;
	}

	public int getBoardReSeq() {
		return boardReSeq;
	}

	public void setBoardReSeq(int boardReSeq) {
		this.boardReSeq = boardReSeq;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardHits() {
		return boardHits;
	}

	public void setBoardHits(int boardHits) {
		this.boardHits = boardHits;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getInsUserId() {
		return insUserId;
	}

	public void setInsUserId(String insUserId) {
		this.insUserId = insUserId;
	}

	public String getInsDate() {
		return insDate;
	}

	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}

	public String getUpdUserId() {
		return updUserId;
	}

	public void setUpdUserId(String updUserId) {
		this.updUserId = updUserId;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}


	/*
    public int getBoard_seq() {
        return board_seq;
    }
 
    public void setBoard_seq(int board_seq) {
        this.board_seq = board_seq;
    }
 
    public int getBoard_re_ref() {
        return board_re_ref;
    }
 
    public void setBoard_re_ref(int board_re_ref) {
        this.board_re_ref = board_re_ref;
    }
 
    public int getBoard_re_lev() {
        return board_re_lev;
    }
 
    public void setBoard_re_lev(int board_re_lev) {
        this.board_re_lev = board_re_lev;
    }
 
    public int getBoard_re_seq() {
        return board_re_seq;
    }
 
    public void setBoard_re_seq(int board_re_seq) {
        this.board_re_seq = board_re_seq;
    }
 
    public String getBoard_writer() {
        return board_writer;
    }
 
    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }
 
    public String getBoard_subject() {
        return board_subject;
    }
 
    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }
 
    public String getBoard_content() {
        return board_content;
    }
 
    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }
 
    public int getBoard_hits() {
        return board_hits;
    }
 
    public void setBoard_hits(int board_hits) {
        this.board_hits = board_hits;
    }
 
    public String getDel_yn() {
        return del_yn;
    }
 
    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }
 
    public String getIns_user_id() {
        return ins_user_id;
    }
 
    public void setIns_user_id(String ins_user_id) {
        this.ins_user_id = ins_user_id;
    }
 
    public Date getIns_date() {
        return ins_date;
    }
 
    public void setIns_date(Date ins_date) {
        this.ins_date = ins_date;
    }
 
    public String getUpd_user_id() {
        return upd_user_id;
    }
 
    public void setUpd_user_id(String upd_user_id) {
        this.upd_user_id = upd_user_id;
    }
 
    public Date getUpd_date() {
        return upd_date;
    }
 
    public void setUpd_date(Date upd_date) {
        this.upd_date = upd_date;
    }
    */
	/*ÀÌ°Å ¹Ù²ãºÁ¿ä*/
}
