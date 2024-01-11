package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	List<Board> findByTitleContaining(String title);
	List<Board> findByCntGreaterThan(long cnt);
	List<Board> findByCntBetween(long cnt, long cnt1);
	List<Board> findByTitleContainingAndCntGreaterThan(String string, long cnt);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String string, String string2);
	
	Page<Board> findByTitleContaining(String string, Pageable paging);
}
