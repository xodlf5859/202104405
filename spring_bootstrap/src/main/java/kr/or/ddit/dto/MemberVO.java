package kr.or.ddit.dto;

import java.util.Date;

public class MemberVO {
	
	private String id;  //아이디
	private String pwd; //패스워드
	private String name="---"; //이름
	private String phone; //전화번호
	private String email;  //이메일
	private String picture; // 사진파일 경로/파일명
	private Date regDate; // 등록일
	private String authority; // 권한
	private int enabled;   // 사용여부
	private String register;//등록자
	private String address;//주소
	
	public MemberVO() {}
	public MemberVO(String id, String pwd, String name, String phone, String email, String picture, Date regDate,
			String authority, int enabled, String register, String address) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.picture = picture;
		this.regDate = regDate;
		this.authority = authority;
		this.enabled = enabled;
		this.register = register;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}




