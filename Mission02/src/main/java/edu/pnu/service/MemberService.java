package edu.pnu.service;

import java.util.ArrayList;
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

	// 특정 회원 데이터가 존재하는지 확인
	private MemberVO getMember(MemberVO memberVO) {
		List<MemberVO> list = new ArrayList<>();
		for (MemberVO m : list) {
			if (m.getId() == memberVO.getId())
				return m;
		}
		return null;
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
