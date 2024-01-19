package edu.pnu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

    public BoardService(BoardRepository boardRepository) {
    }

	public Object getBoardList() {
		List<Board> boardList = new ArrayList<>();
		for (long i=1L; i<=10L; i++) {
			boardList.add(Board.builder()
					.seq(i)
					.title("게시판 프로그램 테스트")
					.writer("도우너")
					.content("게시판 프로그램 테스트입니다...")
					.build());
		}
		return boardList;
	}
}