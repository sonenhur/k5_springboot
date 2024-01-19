package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}

	public Board getBoard(Long seq) {
		return boardRepository.findById(seq).get();
	}

	public Board insertBoard(Board board) {
		return boardRepository.save(board);
	}

	public Board updateBoard(Board board) {
		Board findBoard = boardRepository.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		return boardRepository.save(findBoard);
	}

	public Board deleteBoard(Board board) {
		boardRepository.deleteById(board.getSeq());
		return board;
	}
}