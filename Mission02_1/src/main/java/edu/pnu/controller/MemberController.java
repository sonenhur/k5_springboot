package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {

	@Autowired
	// MemberService 의존성 주입을 위한 필드
	private MemberService memberService;

	// 생성자를 통한 MemberService 의존성 주입
	public MemberController(MemberService memberService) {
//		System.out.println("=====> MemberController 생성");
		log.info("=====> MemberController 생성");
	}

	// 모든 멤버 정보를 JSON 형태로 브라우저에 출력
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return memberService.getMembers(); // MemberService를 통해 멤버 정보 반환
	}

	// 아이디가 {id} 인 member를 찾아서 브라우저에 출력
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}

	// 추가하고자 하는 member 정보를 전달, 추가된 객체 수를 출력
	@PostMapping("/member")
	public int addMember(MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}

	// 수정 대상 객체 정보를 전달, 수정된 객체 수를 출력
	public int updateMembers(MemberVO memberVO) {
		return memberService.updateMembers(memberVO);
	}

	// 아이디가 {id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체 수를 출력
	public int removeMember(@PathVariable Integer id) {
		return memberService.removeMember(id);
	}
}
