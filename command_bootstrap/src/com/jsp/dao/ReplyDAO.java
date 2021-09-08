package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.ReplyVO;
import com.jsp.request.SearchCriteria;

public interface ReplyDAO {

	void insertReply(SqlSession session,ReplyVO reply)throws SQLException;
	void updateReply(SqlSession session,ReplyVO reply)throws SQLException;
	void deleteReply(SqlSession session,int rno)throws SQLException;
	
	int selectReplySeqNextValue(SqlSession session)throws SQLException;
	List<ReplyVO> selectReplyListPage(SqlSession session, int bno,SearchCriteria cri)
																throws SQLException;
	int countReply(SqlSession session,int bno) throws SQLException;
}