package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitleContaining(String title);
	List<Board> findByCntGreaterThan(long cnt);
	List<Board> findByCntBetween(long cnt, long cnt1);
	List<Board> findByTitleContainingAndCntGreaterThan(String string, long cnt);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String string, String string2);
	
	Page<Board> findByTitleContaining(String string, Pageable paging);
	
	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest(@Param("searchKeyword") String searchKeyword);
	
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword);
	
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	@Query(value="SELECT seq, title, writer, create_Date FROM board WHERE title like '%'||?1||'%' ORDER BY seq DESC", nativeQuery=true)
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest4(Pageable paging);
}
