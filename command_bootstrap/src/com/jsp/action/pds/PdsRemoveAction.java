package com.jsp.action.pds;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.service.PdsService;

public class PdsRemoveAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	final private int MEMORY_THRESHOLD = 1024*1024*3; // 3MB
	final private int MAX_FILE_SIZE = 1024*1024*40; // 40MB
	final private int MAX_REQUEST_SIZE = 1024*1024*200; // 200MB
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "pds/remove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));

		// pno에 대한 attachList 확보
		List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();
		
		//파일 삭제
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				String storedFilePath = attach.getUploadPath() + File.separator + attach.getFileName();
				
				File file = new File(storedFilePath);
				
				if (file.exists()) {
					file.delete();
				}
			}
		}
		
		//DB 내용 삭제
		pdsService.remove(pno);
		
		return url;
	}

}
