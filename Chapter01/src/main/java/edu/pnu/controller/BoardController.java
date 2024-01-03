package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;

@RestController
public class BoardController {

	public BoardController() {
		System.out.println("===> BoardController 생성");
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}

	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO boardVO = new BoardVO();
		boardVO.setSeq(1);
		boardVO.setTitle("title");
		boardVO.setWriter("writer");
		boardVO.setContent("content");
		return boardVO;
	}

	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("title" + i);
			board.setWriter("테스터");
			board.setContent(i + "번 내용입니다");
			boardList.add(board);
		}
		return boardList;
	}

}
