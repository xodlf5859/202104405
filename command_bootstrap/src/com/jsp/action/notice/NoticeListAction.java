package com.jsp.action.notice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.request.SearchCriteria;
import com.jsp.service.NoticeService;

public class NoticeListAction implements Action{

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "notice/list";
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		SearchCriteria cri = new SearchCriteria();
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		//서비스한테 건내줘서 맵을 받는다.
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		
		
		request.setAttribute("dataMap", dataMap);//이게 출력이다
		
//		request.setAttribute("noticeList", dataMap.get("noticeList"));
//		request.setAttribute("pageMaker", dataMap.get("pageMaker"));
		
		return url;
	}

	
}
