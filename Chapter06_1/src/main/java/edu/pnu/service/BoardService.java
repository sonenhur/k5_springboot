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

}