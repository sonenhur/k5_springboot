package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

	@Test
	@Order(1)
	@DisplayName("중복된 데이터를 미리 제거합니다")
	public void test00() {
		for (int id=100; id<=105; id++)
			memberService.removeMember(id);
	}
	
	@Test
	@Order(2)
	@DisplayName("데이터를 입력합니다")
	public void test01() {
		for (int id = 100; id<=104; id++)
			memberService.addMember(MemberVO.builder()
									.id(id)
									.name("name"+id)
									.pass("pass"+id)
									.regidate(new Date())
									.build());
	}

	@Test
	@Order(3)
	@DisplayName("입력한 데이터를 읽어옵니다")
	public void test02() {
		List<MemberVO> list = memberService.getMembers();
		for (MemberVO m : list)
			System.out.println(m);
	}

	@Test
	@Order(4)
	@DisplayName("데이터를 추가 입력합니다")
	public void test03() {
		int id = 105;
			memberService.addMember(MemberVO.builder()
	    		.id(id)
				.name("name"+id)
				.pass("pass"+id)
				.regidate(new Date())
				.build());
	}
}
