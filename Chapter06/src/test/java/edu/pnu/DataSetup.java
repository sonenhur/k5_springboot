package edu.pnu;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class DataSetup {
	@Autowired
	private BoardRepository boardRepo;
	@Test
	public void setupData() {
		Random rd = new Random();
		for (int i=0; i<10; i++)
			boardRepo.save(Board.builder()
					.title("title"+i)
					.writer("writer"+(i%2+1))
					.content("content"+i)
					.cnt(rd.nextLong(100)+1)
					.build());
	}
}
