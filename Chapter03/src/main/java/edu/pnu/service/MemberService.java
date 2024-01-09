package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public MemberService() {
		System.out.println("=====> MemberService 생성");
	}

	public List<MemberVO> getMembers() {
		return memberDAO.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDAO.getMember(id);
	}

	public int addMember(MemberVO memberVO) {
	    return memberDAO.addMember(memberVO);
	}

	public int addTestMember(MemberVO memberVO) {
	    return memberDAO.addTestMember(memberVO);
	}

	public int addTestMember1(MemberVO memberVO) {
	    return memberDAO.addTestMember1(memberVO);
	}

	public int updateMembers(MemberVO memberVO) {
		return memberDAO.updateMembers(memberVO);
	}

	public int removeMember(Integer id) {
		return memberDAO.removeMember(id);
	}

}
