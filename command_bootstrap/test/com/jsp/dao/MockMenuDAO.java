package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.MenuVO;

public class MockMenuDAO implements MenuDAO {

@Override
public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
	List<MenuVO> menu = null;
	
	
	return menu;
}

@Override
public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
	return null;
}

@Override
public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
	return null;
}

@Override
public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
	return null;
}
}
