package kr.or.ddit.command;

import kr.or.ddit.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{
	
	private String pno;
	private String[] deleteFile;
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pds = super.toPdsVO();
		pds.setPno(Integer.parseInt(this.pno));
		return pds;
	}
	
}
