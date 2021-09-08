package com.jsp.service;

import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;

public class MemberServiceImpl {
	
	public void login(String id, String pwd) 
			throws InvalidPasswordException,NotFoundIdException{
		//로그인 처리를 여기에서 작성
		
		if(!id.equals("mimi")) {
					throw new NotFoundIdException();
				}
		if(!pwd.equals("mimi")) {
			throw new InvalidPasswordException();
		}
		
	}
}
