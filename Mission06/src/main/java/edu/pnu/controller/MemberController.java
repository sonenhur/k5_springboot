package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import jakarta.annotation.PostConstruct;

@RestController
public class MemberController {
	
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
	
	// 코드 합치기
	// !위는 List, 아래는 객체를 반환하므로 추가 과정이 필요!
	@GetMapping("/member") // 회원 조회 (전체 / 개별)
	public ResponseEntity<?> getMembers(Long id) {
//		ResponseEntity : Spring Framework에서 HTTP 응답을 나타내는 클래스
//		.ok().body() , .badRequest().body()등
		if (id == null) 
			return ResponseEntity.ok().body(memberService.getAllMembers());
		return ResponseEntity.ok().body(memberService.getMemberById(id));
	}
		
	@PostMapping("/member") // 회원 추가
	// ! 정보 누락 등 오류 상황을 발생시켜보고 해결책도 만들어 둘 것!
	public Member addMember(Member member) {
	    if (member.getName() == null || member.getPassword() == null)
	        return null;
	    return memberService.addMember(member);
	}
	
	@PutMapping("/member") // 멤버 정보 수정
	public Member updateMembers(Member member) {
		if (member.getId() == null) return null;
		member.setName(member.getName());
		member.setPassword(member.getPassword());
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member") // 회원 정보 삭제
	// ! 없는 아이디 등 오류 상황을 발생시켜보고 해결책도 만들어 둘 것!
	public long removeMember(Long id) {
		return memberService.removeMember(id);
	}
}