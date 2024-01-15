package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class BoardTest {
	@Test
	@Order(0)
	public void testInsertBoard() {
		for (int i = 1; i <= 100; i++) {
			Board board = new Board();
			Random rand = new Random();
			board.setTitle(i + "번째 게시글");
			board.setContent("잘 등록되나요? " + i);
			board.setCreateDate(new Date());
			board.setCnt((long) rand.nextInt(100)+1);
		}
	}
}
