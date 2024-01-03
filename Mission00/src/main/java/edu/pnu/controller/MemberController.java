package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	List<MemberVO> list;

	public MemberController() {
		System.out.println("===> MemberController 생성");
		list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name : " + i)
					.pass("pass : " + i)
					.regidate(new Date()).build());
		}
	}

	@GetMapping("/member") // 모든 회원 조회
	public List<MemberVO> getAllMembers() {
		return list;
	}

	@GetMapping("/member/{id}") // 특정 회원 조회
	public MemberVO getMember(@PathVariable Integer id) {
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

	@PostMapping("/member") // 멤버 추가
	public int addMember(MemberVO memberVO) {
		if (getMember(memberVO) != null)
			return 0;
		list.add(memberVO);
		return 1;
	}

	@PutMapping("/member") // 멤버 정보 수정
	public int updateMembers(MemberVO memberVO) {
		if (getMember(memberVO) == null)
			return 0;
		memberVO.setName(memberVO.getName());
		memberVO.setPass(memberVO.getPass());
		return 1;
	}

	@DeleteMapping("/member") // 멤버 정보 삭제
	public int removeMember(Integer id) {
		MemberVO m = getMember(id);
		if (m != null) {
			list.remove(m);
			return 1;
		} else	return 0;
	}
}