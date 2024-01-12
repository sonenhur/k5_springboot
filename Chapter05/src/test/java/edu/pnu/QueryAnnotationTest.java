package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class QueryAnnotationTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(0)
	public void testInsertBoard() {
		for (int i = 1; i <= 100; i++) {
			Board board = new Board();
			Random rand = new Random();
			board.setTitle(i + "번째 게시글");
			board.setWriter("테스터");
			board.setContent("잘 등록되나요? " + i);
			board.setCreateDate(new Date());
			board.setCnt((long) rand.nextInt(100)+1);
			boardRepo.save(board);
		}
	}
	
	@Test
	@Order(1)
	public void testQueryAnnotationTest1() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest1("0번째");
		System.out.println("검색 결과");
		for (Object[] board : boardList) {
			System.out.println("===> " + board.toString());
		}
	}
	
	@Test
	@Order(2)
	public void testQueryAnnotationTest2() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("0번째");
		System.out.println("검색 결과");
		for (Object[] row : boardList) {
			System.out.println("===> " + Arrays.toString(row));
			}
		}
	
	@Test
	@Order(3)
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("0번째");
		System.out.println("검색 결과");
		for (Object[] row : boardList) {
			System.out.println("===> " + Arrays.toString(row));
		}
	}
	
	@Test
	@Order(4)
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> boardList = boardRepo.queryAnnotationTest4(paging);
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("===> " + board.toString());
		}
	}
}
