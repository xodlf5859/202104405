package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:kr/or/ddit/context/root-context.xml")
public class TestMemberServiceImpl {
	
	
	@Autowired
	private MemberServiceImpl service;
	
	@Before
	public void init(){
		//service.setMemberDAO(new MockMemberDAO());		
	}
	
	@Test
	public void testGetList()throws SQLException{
		
		SearchCriteria cri = new SearchCriteria();
			
		 List<MemberVO> memberList
		 	= (List<MemberVO>) service.getMemberList(cri).get("memberList");		
		
		Assert.assertEquals(10,memberList.size());
	}
	
	
}
