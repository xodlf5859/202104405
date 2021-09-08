package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberDisabledAction implements Action {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url="/member/stop_success";
		
		String id=request.getParameter("id");
		
		memberService.disabled(id);
		//정지된 유저가 로그인 중이면 로그아웃 되게 하자
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(id.equals(loginUser.getId())) {
			session.invalidate();
		}
		
		request.setAttribute("member", memberService.getMember(id));
		return url;
	}

}
