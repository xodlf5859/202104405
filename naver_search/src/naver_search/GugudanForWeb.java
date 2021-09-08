package naver_search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GugudanForWeb extends Gugudan {

	public void inputDataByWeb(HttpServletRequest request,
								   HttpServletResponse response)
								   throws ServletException, IOException {
		setDan(Integer.parseInt(request.getParameter("dan")));
		setGop(Integer.parseInt(request.getParameter("gop")));
		//private해놔서 못쓰기 때문에 게터세터를 만들어서 쓸수있게한다.
		
	}

	public void outDataByWeb(HttpServletRequest request,
								 HttpServletResponse response)
								 throws ServletException, IOException {
		response.setContentType("text/plane;charset=utf-8");
		PrintWriter out = response.getWriter(); //세팅을 한 다음에 아웃객체를 가져와야한다.
		out.print(getResult());
	}
	//처리는 가져오고 입출력만 웹을 위한 걸로 만들면 된다!!
	
	
}
