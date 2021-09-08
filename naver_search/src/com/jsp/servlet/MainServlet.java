package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naver_search.Gugudan;
import naver_search.GugudanForWeb;

@WebServlet("/gugudan")
public class MainServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GugudanForWeb gugudan = new GugudanForWeb();
		
		//입력
		gugudan.setDan(Integer.parseInt(request.getParameter("dan")));
		gugudan.setGop(Integer.parseInt(request.getParameter("gop")));
		
		//처리
		gugudan.processGugudan();
		
		//출력
		response.setContentType("text/plane;charset=utf-8");
		PrintWriter out = response.getWriter(); //세팅을 한 다음에 아웃객체를 가져와야한다.
		out.print(gugudan.getResult());

	}

}
