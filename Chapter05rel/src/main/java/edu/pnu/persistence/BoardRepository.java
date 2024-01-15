package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByCntGreaterThan(Long cnt);

	Page<Board> findByCntGreaterThan(Long cnt, Pageable pageable);

	List<Board> findByTitleContaining(String title);

	List<Board> findByCntGreaterThan(long cnt);

	List<Board> findByCntBetween(long cnt, long cnt1);

	List<Board> findByTitleContainingAndCntGreaterThan(String string, long cnt);

	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);

	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String string, String string2);

	Page<Board> findByTitleContaining(String string, Pageable paging);

}