package edu.pnu;
import java.util.Date;
import edu.pnu.domain.MemberVO;
public class Program {
	public static void main(String[] args) {
		MemberVO m = MemberVO.builder()
				.id(1)
				.name("name")
				.pass("pass")
				.regidate(new Date())
				.build();
		System.out.println("M : " + m);
	}
}