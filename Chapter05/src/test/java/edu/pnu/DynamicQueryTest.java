package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class DynamicQueryTest {
	@Autowired
	private DynamicBoardRepository boardRepo;

	@Test
	@Order(0)
	public void testInsertBoard() {
		for (int i = 1; i <= 100; i++) {
			Board board = new Board();
			Random rand = new Random();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt((long) rand.nextInt(100) + 1);
			boardRepo.save(board);
		}
	}

	@Test
	@Order(1)
	public void testDynamicQuery() {
	    doDynamicQueryTest("TITLE", "테스트 제목 1");
	    doDynamicQueryTest("CONTENT", "테스트 내용 2");
	}

	private void doDynamicQueryTest(String searchCondition, String searchKeyword) {
	    BooleanBuilder builder = new BooleanBuilder();
	    QBoard qboard = QBoard.board;

	    if (searchCondition.equals("TITLE")) {
	        builder.and(qboard.title.like("%" + searchKeyword + "%"));
	    } else if (searchCondition.equals("CONTENT")) {
	        builder.and(qboard.content.like("%" + searchKeyword + "%"));
	    }
	    System.out.println("검색 결과");
	    System.out.println("=".repeat(100));
	    Page<Board> boardList = boardRepo.findAll(builder, PageRequest.of(0, 5));
	    for (Board board : boardList) {
	        System.out.println("===> " + board.toString());
	    }
	}

	@Test
	@Order(2)
	public void testDynamicQuery1() {
		List<Board> board = boardRepo.findByTitleContaining("1");
		System.out.println("=".repeat(100));
		System.out.println("미션 1-1");
		System.out.println(board.toString());
		
	}
	@Test
	@Order(3)
	public void testDynamicQuery2() {
		List<Board> board = boardRepo.findByCntGreaterThan(50L);
		System.out.println("=".repeat(100));
		System.out.println("미션 1-2");
		System.out.println(board.toString());
	}
	
	@Test
	@Order(4)
    public void testDynamicQuery3() {
        int page = 0; int size = 5;
        Sort sort = Sort.by(Sort.Order.desc("createDate"));
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Board> boardPage = boardRepo.findByTitleContaining("1", paging);
        System.out.println("=".repeat(100));
        System.out.println("미션 1-3");
        System.out.println("총 데이터 수: " + boardPage.getTotalElements());
        System.out.println("한 페이지 크기: " + boardPage.getSize());
        System.out.println("총 페이지 수: " + boardPage.getTotalPages());
        System.out.println(boardPage.getContent().toString());
    }
	@Test
	@Order(5)
	  public void testDynamicQuery4() {
        int page = 0; int size = 5;
        Sort sort = Sort.by(Sort.Order.desc("cnt"));
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Board> boardPage = boardRepo.findByCntGreaterThan(50L, paging);
        System.out.println("=".repeat(100));
        System.out.println("미션 1-4");
        System.out.println("총 데이터 수: " + boardPage.getTotalElements());
        System.out.println("한 페이지 크기: " + boardPage.getSize());
        System.out.println("총 페이지 수: " + boardPage.getTotalPages());
        System.out.println(boardPage.getContent().toString());
    }
}