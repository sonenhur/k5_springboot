package edu.pnu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findByTitleContaining(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }

    public List<Board> findByCntGreaterThan(Long cnt) {
        return boardRepository.findByCntGreaterThan(cnt);
    }

	public Page<Board> findByTitleContaining(String keyword, Pageable pageable) {
		return null;
	}

	public Page<Board> findByCntGreaterThan(Long cnt, Pageable pageable) {
		return null;
	}
}