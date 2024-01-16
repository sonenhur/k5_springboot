package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import jakarta.annotation.PostConstruct;

@RestController

public class MemberController {
	List<Member> list;
	@Autowired
	private MemberService memberService;

	@PostConstruct
	public void init() {	// 회원 테이블 생성
		for (Long i = 1L; i <= 10; i++) {
			memberService.addMember(Member.builder()
					.id(i)
					.password("pass" + i)
					.name("name" + i)
					.role("user")
					.build());
		}
	}

//	@GetMapping("/member") //모든 회원 조회
//	public List<Member> getAllMembers() {
//		return memberService.getAllMembers();
//	}
//
//	@GetMapping("/member/{id}") // 특정 회원 조회
//	public Member getMemberById(@PathVariable Long id) {
//		return memberService.getMemberById(id);
//	}

	@GetMapping("/member")
	public ResponseEntity<?> getMembers(Long id) {
		if (id == null) 
			return ResponseEntity.ok(memberService.getAllMembers());
		return ResponseEntity.ok(memberService.getMemberById(id));
	}
	
	@PostMapping("/member") // 회원 추가
	// ! 정보 누락 등 오류 상황을 발생시켜보고 해결책도 만들어 둘 것!
	public Member addMember(Member member) {
	    if (member.getName() == null || member.getPassword() == null)
	        return null;
	    return memberService.addMember(member);
	}

	@DeleteMapping("/member") // 회원 정보 삭제
	// ! 없는 아이디 등 오류 상황을 발생시켜보고 해결책도 만들어 둘 것!
	public long removeMember(Long id) {
		return memberService.removeMember(id);
	}
}