package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {
	
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin(); // Transaction 시작
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			em.persist(board); // Entity 등록

			tx.commit(); // Transaction 커밋
			System.out.println("등록 완료");
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	private static void searchData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		try {
			Board board = em.find(Board.class, 1L); //글 상세 조회
			if (board != null) System.out.println("Search : " + board);
			else			   System.out.println("Not Found");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	private static void modifyData(EntityManagerFactory emf) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin(); // Transaction 시작
	        Board board = em.find(Board.class, 1L); // 글 상세 조회
	        if (board != null) {
	            board.setContent("수정된 게시물입니다");
	            tx.commit(); // Transaction 커밋
	            System.out.println("수정 완료");
	        } else {
	            System.out.println("글을 찾을 수 없습니다.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        tx.rollback();
	    } finally {
	        em.close();
	    }
	}

	public static void main(String[] args) {
		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		//데이터 입력
		insertData(emf);
		// 데이터 검색
		searchData(emf);
		// 데이터 수정
		modifyData(emf);
		// EntityManagerFactory 닫기
		emf.close();
		
		// 동시에 실행했을 경우 상단 메소드에서 닫아버리면 다음 메소드에서 오류가 난다
		// "만든(연) 곳에서 닫는다"
	}
}