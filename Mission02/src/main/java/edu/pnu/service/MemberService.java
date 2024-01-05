package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

public class MemberService {
	private MemberDAO m = new MemberDAO();
	

	public List<MemberVO> getAllMembers() {
		return m.getAllMembers();
	}

	public MemberVO getMember(Integer id) {
		return m.getMember(id);
	}

	public int addMember(MemberVO memberVO) {
		return m.addMember(memberVO);
	}

	public int updateMembers(MemberVO memberVO) {
		return m.updateMembers(memberVO);
	}

	public int removeMember(Integer id) {
		return m.removeMember(id);
	}
}
