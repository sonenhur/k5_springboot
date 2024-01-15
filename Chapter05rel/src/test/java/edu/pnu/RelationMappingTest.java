package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
public class RelationMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	@Test
	@Transactional
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get(); 
		// 부트 특성상 위 코드 보내면 트랜젝션이 1회 종료됨.
		// FetchType.LAZY 실행시 프록시(가짜) 객체를 가져오므로 아랫단에서 에러 발생
		// => @Transactional로 감싼다
		System.out.println("[ " + 			board.getSeq() + "번 게시글 정보 ]");
		System.out.println("제목 : " + 		board.getTitle());
		System.out.println("내용 : " + 		board.getContent());
		System.out.println("작성자 : " + 		board.getMember().getName());
		System.out.println("작성자 권한 : " + 	board.getMember().getRole());
	}
}
