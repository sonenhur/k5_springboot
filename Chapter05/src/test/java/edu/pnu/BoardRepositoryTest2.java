package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardRepositoryTest2 {
	@Autowired
	private BoardRepository boardRepo;

	@Test
	@Order(1)
	public void testInsertBoard() {
		for (int i = 0; i < 200; i++) {
			Board board = new Board();
			Random rand = new Random();
			board.setTitle(i + "번째 게시글");
			board.setWriter("테스터");
			board.setContent("잘 등록되나요? " + i);
			board.setCreateDate(new Date());
			board.setCnt((long) rand.nextInt(200));
			boardRepo.save(board);
		}
	}

	@Test
	@Order(2)
	@DisplayName("Page 객체")
	public void testGetBoard() {		//앞은 페이지 번호, 뒤는 갯수, 페이징은 sort 오더링과 함께
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "seq"); 
		Page<Board> page = boardRepo.findByTitleContaining("1", paging);
		
		System.out.println("PageNumber: "+page.getNumber());
		System.out.println("PageSize: "+page.getSize());
		System.out.println("TotalPages: "+page.getTotalPages());
		System.out.println("NumberOfElements: "+page.getNumberOfElements());
		System.out.println("hasPrevious: " + page.hasPrevious());
		System.out.println("hasNext: " + page.hasNext());
		System.out.println("isLast: " + page.isLast());
		System.out.println("nextPageable: " + page.nextPageable());
		System.out.println("previousPageable: " + page.previousPageable());
		System.out.println("getContent: " + page.getContent());
		System.out.println("hasContent: " + page.hasContent());
		System.out.println("getSort: " + page.getSort());
		
		for (Board b : page)
		System.out.println(b);
	}

	@Test
	@Order(3)
	public void testGetBoard1() {
		List<Board> board = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		System.out.println("미션 3");
		System.out.println(board.toString());
	}

	@Test
	@Order(4)
	public void testGetBoard2() {
		List<Board> board = boardRepo.findByCntBetween(10L, 50L);
		System.out.println("미션 4");
		System.out.println(board.toString());
	}

	@Test
	@Order(5)
	public void testGetBoard3() {
		List<Board> board = boardRepo.findByTitleLikeOrContentLikeOrderBySeqDesc("%10%", "%2%");
		System.out.println("미션 5");
		System.out.println(board.toString());
	}
}
