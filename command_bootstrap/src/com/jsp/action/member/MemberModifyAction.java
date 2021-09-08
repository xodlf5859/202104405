package com.jsp.action.member;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.controller.FileUploadResolver;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.GetUploadPath;
import com.jsp.util.MultipartHttpServletRequestParser;

public class MemberModifyAction implements Action {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; // 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; // 2MB
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "member/modify_success";
		
		MultipartHttpServletRequestParser multiReq = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		
		String id = multiReq.getParameter("id");
		String pwd = multiReq.getParameter("pwd");
		String email = multiReq.getParameter("email");
		String authority = multiReq.getParameter("authority");
		String name = multiReq.getParameter("name");
		String phone = multiReq.getParameter("phone");
		
		//멤버 VO 객체에 저장
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setAuthority(authority);
		member.setName(name);
		member.setPhone(phone);
		
		//픽처를 지금 저장할 수 없다 파일이 저장될때 파일명이 만들어지기 때문에 지금은 null인 상태다
		
		//기존 사진 변경 유무
		String uploadPicture = multiReq.getParameter("uploadPicture");
		if(uploadPicture!=null && !uploadPicture.isEmpty()) { // 사진 변경
	
			//저장 경로
			String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
			File file = new File(uploadPath);
			if(!file.mkdir()) {
				System.out.println(uploadPath + "가 이미 존재합니다.");
			}
			
			//기존 이미지 삭제
			File deleteFile = new File(uploadPath,multiReq.getParameter("oldPicture"));
			if (deleteFile.exists()) {
				deleteFile.delete();
				
			}
			
			//최근 사진 이미지 저장
			List<File> fileList = FileUploadResolver.fileupload(multiReq.getFileItems("picture"), uploadPath);
			File saveFile = fileList.get(0);
			
			member.setPicture(saveFile.getName());
			
			
		}else {
			member.setPicture(multiReq.getParameter("oldPicture"));
		}
		
		//DB처리
		
		memberService.modify(member);
		//수정된 멤버의 정보 보내기
		request.setAttribute("member", member);
		
		//로그인한 사용자 정보일 경우 세션을 닫아주자
		
		//로그인 사용자 확인
		
		//세션을 가져오고 어트리뷰트를 꺼내온다 그리고 멤버아이디랑 비교
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(member.getId().equals(loginUser.getId())) {
			//리퀘스트 어트리뷰트의 패런트 리로드 (세션정보가 왼쪽 상단(패런트)에 있으니까)
			//로그인 유저에 대한 세션정보 갱신하고 바꾸기
			request.setAttribute("parentReload", true);
			session.setAttribute("loginUser", memberService.getMember(member.getId()));
		}
		
		
		return url;
	}

}
