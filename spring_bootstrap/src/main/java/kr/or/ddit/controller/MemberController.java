package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.MemberModifyCommand;
import kr.or.ddit.command.MemberRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.MakeFileName;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/main")
	public void main() {
	}

	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws SQLException {
		String url = "member/list";

		Map<String, Object> dataMap = memberService.getMemberList(cri);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public String registForm() {
		String url = "member/regist";
		return url;
	}

	@Resource(name = "picturePath")
	private String picturePath;

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture)
			throws Exception {
		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;

		/* 파일저장확인 */
		if ((result = savePicture(oldPicture, multi)) == null) {
			result = "업로드 실패했습니다.!";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;

		}

		entity = new ResponseEntity<String>(result, status);

		return entity;
	}

	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;

		/* 파일유무확인 */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* 파일저장폴더설정 */
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD 에 저장.
			multi.transferTo(storeFile);

			if (oldPicture != null && !oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}
	
	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String picture) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String imgPath = this.picturePath;
		try {

			in = new FileInputStream(new File(imgPath, picture));

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
					HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping(value = "/getPictureById/{id}",method=RequestMethod.GET, 
			produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getPictureById(@PathVariable("id") String id) 
																throws Exception {
		String picture = memberService.getMember(id).getPicture();
		
		return  getPicture(picture);
	}
	
	@RequestMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<String> idCheck(String id) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			MemberVO member = memberService.getMember(id);

			if (member != null) {
				entity = new ResponseEntity<String>("duplicated", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq) throws SQLException, IOException {
		String url = "member/regist_success";

		MemberVO member = memberReq.toMemberVO();
		memberService.regist(member);

		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@ModelAttribute("id") String id, Model model) 
													throws SQLException {

		String url = "member/detail";

		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		

		return url;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public String modifyForm(String id, Model model)throws SQLException {

		String url = "member/modify";

		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		

		return url;
	}
	

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(MemberModifyCommand modifyReq,HttpSession session,
						 RedirectAttributes rttr)throws Exception {
		String url="redirect:/member/detail.do";
		
		MemberVO member = modifyReq.toParseMember();

		// 신규 파일 변경 및 기존 파일 삭제
		String fileName = savePicture(modifyReq.getOldPicture(), modifyReq.getPicture());
		member.setPicture(fileName);
		
		//파일변경 없을시 기존 파일명 유지
		if (modifyReq.getPicture().isEmpty()) {
			member.setPicture(modifyReq.getOldPicture());
		}
		//DB 내용 수정
		memberService.modify(member);
		
		rttr.addFlashAttribute("parentReload",false);
		
		// 로그인한 사용자의 경우 수정된 정보로 session 업로드
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null && member.getId().equals(loginUser.getId())) {
			session.setAttribute("loginUser", member);
			rttr.addFlashAttribute("parentReload",true);
		}
		
		rttr.addAttribute("id",member.getId());
		rttr.addAttribute("from","modify");
		
		rttr.addFlashAttribute("member",memberService.getMember(modifyReq.getId()));
		
		return url;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(String id, HttpSession session, RedirectAttributes rttr) throws SQLException {
		String url = "redirect:/member/detail.do";
		
		MemberVO member;

		// 이미지 파일을 삭제
		member = memberService.getMember(id);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, member.getPicture());
		if (imageFile.exists()) {
			imageFile.delete();
		}
		//DB삭제
		memberService.remove(id);
		
		// 삭제되는 회원이 로그인 회원인경우 로그아웃 해야함.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}
		
		rttr.addFlashAttribute("removeMember",member);
		
		rttr.addAttribute("from","remove");		
		rttr.addAttribute("id",id);
		
		return url;
	}
	
}
