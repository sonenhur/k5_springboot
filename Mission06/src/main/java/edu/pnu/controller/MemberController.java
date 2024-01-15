package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostConstruct
	public void init() {
		for (Long i = 1L; i <= 10; i++) {
			memberService.saveMember(Member.builder()
					.id(i).password("pass" + i)
					.name("name" + i)
					.role("user")
					.build());
		}
	}

	@GetMapping
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("{id}")
	public Member getMemberById(@PathVariable Long id) {
		return memberService.getMemberById(id);
	}

	@PostMapping
	public Member saveMember(@RequestBody Member member) {
		return memberService.saveMember(member);
	}

	@DeleteMapping("{id}")
	public void deleteMember(@PathVariable Long id) {
		memberService.deleteMember(id);
	}
}
