package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import edu.pnu.domain.Board;

public interface DynamicBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

	List<Board> findByTitleContaining(String string);

	List<Board> findByCntGreaterThan(long l);

	Page<Board> findByTitleContaining(String string, Pageable paging);

	Page<Board> findByCntGreaterThan(long l, Pageable paging);

}
