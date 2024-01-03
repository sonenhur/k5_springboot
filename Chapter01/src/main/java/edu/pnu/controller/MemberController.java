package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
//@RequestMapping("/api/public/") // 공통적인 url 정의 (이게 앞에 오고 뒤에 /member 등이 붙음)
public class MemberController {

	List<MemberVO> list;

	public MemberController() {
		System.out.println("===> MemberController 생성");

		list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
//			MemberVO m = new MemberVO();
//			m.setId(i);
//			m.setPass("Password : " + i);
//			m.setName("Name : " + i);
//			m.setRegidate(new Date());
//			list.add(m);
			list.add(MemberVO.builder().id(i).name("name" + i).pass("pass" + i).regidate(new Date()).build());
		}
	}

	// 모든 회원 목록 조회
	@GetMapping("/member")
	public List<MemberVO> getAllMembers() {
		return list;
	}

	// 특정 ID의 회원 조회
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	// 특정 회원 데이터가 존재하는지 확인
	private MemberVO getMember(MemberVO memberVO) {
		for (MemberVO m : list)
			if (m.getId() == memberVO.getId())
				return m;
		return null;
	}

	// 회원 추가
	@PostMapping("/member")
	public int addMember(MemberVO memberVO) {
		// 중복 확인 후 추가
		if (getMember(memberVO) != null)
			return 0;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return 1;
	}

	// JSON 형식의 회원 추가
	@PostMapping("/memberJSON")
	public int addMember1(MemberVO memberVO) {
		// 중복 확인 후 추가
		if (getMember(memberVO) != null)
			return 0;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return 1;
	}

	// 회원 정보 수정
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMember(memberVO);
		// 존재하는 회원인 경우 정보 수정
		if (m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}

	// 회원 삭제
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		MemberVO m = getMember(id);
		if (m != null) {
			list.remove(m);
			return 1;
		} else {
			return 0;
		}
	}
}