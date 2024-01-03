package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;

@RestController
public class BoardController2 {
	private List<BoardVO> list;

	// 생성자에서 초기 데이터를 가진 BoardVO 객체 리스트를 생성한다.
	public BoardController2() {
		System.out.println("===> BoardController2 생성");

		list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			BoardVO b = new BoardVO();
			b.setSeq(i);
			b.setTitle("title" + i);
			b.setContent("content" + i);
			b.setWriter("writer" + i);
			list.add(b);
		}
	}

	// 파라미터로 전달된 seq 값에 해당하는 BoardVO 객체를 반환한다.
	// 만약 seq가 1보다 작거나 10보다 크면 null을 반환한다.
	@GetMapping("/getBoard2")
	public BoardVO getBoard(Integer seq) {
		// 아이디가 1보다 작거나 10보다 크면 null 리턴
		if (seq < 1 || seq > 10)
			return null;

		// list에 저장된 BoardVO 객체에서 seq값이 파라미터 seq와 같은 객체를 리턴한다.
		// 그러기 위해서는 list를 처음부터 끝까지 순회하면서 같은 데이터가 있는지 검색한다.
		for (int i = 0; i < list.size(); i++) {
			BoardVO b = list.get(i); // list.get(i)를 통해 현재 인덱스(i)에 해당하는 BoardVO 객체 가져오기
			if (b.getSeq() == seq) { // 가져온 BoardVO 객체의 getSeq() 메서드를 호출하여 해당 게시물의 시퀀스 번호를 가져오고, 이를 입력으로 받은 seq 값과 비교
				return b; // 일치하는 경우 반환: seq 값과 게시물의 시퀀스 번호가 일치하면 해당 게시물 객체를 return하여 메서드를 종료합니다.
			}
		}
		//확장된 for 문
		for (BoardVO b : list) {
			if (b.getSeq() == seq)
				return b;
		}
		return null; // 일치하는 seq가 없을 경우 null을 반환한다.
	}

	// localhost:8080/getBoard3/10
	@GetMapping("/getBoard3/{seq}")
	public BoardVO getBoard2(@PathVariable Integer seq) {
		return getBoard(seq);
	}
}
