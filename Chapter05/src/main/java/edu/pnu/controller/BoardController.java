package edu.pnu.controller;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/boards/title")
	public List<Board> getBoardsByTitle(@RequestParam String keyword) {
		return boardService.findByTitleContaining(keyword);
	}

	@GetMapping("/boards/cnt")
	public List<Board> getBoardsByCntGreaterThan(@RequestParam Long cnt) {
		return boardService.findByCntGreaterThan(cnt);
	}

	@GetMapping("/boards/title/paged")
	public Page<Board> getPagedBoardsByTitle(@RequestParam String keyword, Pageable pageable) {
		return boardService.findByTitleContaining(keyword, pageable);
	}

	@GetMapping("/boards/cnt/paged")
	public Page<Board> getPagedBoardsByCntGreaterThan(@RequestParam Long cnt, Pageable pageable) {
		return boardService.findByCntGreaterThan(cnt, pageable);
	}
}
