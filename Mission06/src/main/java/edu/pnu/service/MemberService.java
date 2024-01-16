package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	public Member getMemberById(Long id) {
//		return memberRepository.findById(id).orElse(null);
		
		Optional<Member> opt = memberRepository.findById(id);
//		Optional<Member> : Java에서 Null 포인터 예외를 방지하고 코드를 명시적으로 처리하기 위한 클래스
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public Member addMember(Member member) {
		return memberRepository.save(member);
	}

	public long removeMember(Long id) {
		try {
			memberRepository.deleteById(id);
		} catch (Exception e) {
			return 0;
		}
		return 1L;
	}

	public Member updateMember(Member member) {
		return memberRepository.save(member);
	}
}