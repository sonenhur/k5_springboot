package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberService {
	List<MemberVO> list;
	public MemberService() {
		System.out.println("===> MemberController 생성");
		list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name : " + i)
					.pass("pass : " + i)
					.regidate(new Date())
					.build());
		}
	}

	public List<MemberVO> getAllMembers() {
		return list;
	}

	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	// 특정 회원 데이터가 존재하는지 확인
	private MemberVO getMember(MemberVO memberVO) {
		for (MemberVO m : list) {
			if (m.getId() == memberVO.getId())
				return m;
		}
		return null;
	}

	public int addMember(MemberVO memberVO) {
		if (getMember(memberVO) != null)
			return 0;
		list.add(memberVO);
		return 1;
	}

	public int updateMembers(MemberVO memVO) {
		MemberVO m = getMember(memVO);
		if (m == null)
		return 0;
		
		if (memVO.getName() != null) {
		m.setName(memVO.getName());
		}
		if(memVO.getPass()!= null){
		m.setPass(memVO.getPass());
		}
		return 1;
	}

	public int removeMember(Integer id) {
		MemberVO m = getMember(id);
		if (m != null) {
			list.remove(m);
			return 1;
		} else	return 0;
	}
}
