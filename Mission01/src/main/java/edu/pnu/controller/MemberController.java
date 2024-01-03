package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	@GetMapping("/member") // 모든 회원 조회
	public List<MemberVO> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("/member/{id}") // 특정 회원 조회
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}

	@PostMapping("/member") // 멤버 추가
	public int addMember(MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}

	@PutMapping("/member") // 멤버 정보 수정
	public int updateMembers(MemberVO memberVO) {
		return memberService.updateMembers(memberVO);
	}

	@DeleteMapping("/member") // 멤버 정보 삭제
	public int removeMember(Integer id) {
		return memberService.removeMember(id);
	}
}