package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;

@Controller
public class BoardController {
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = new ArrayList<>();
		for (long i=1L; i<=10L; i++) {
			boardList.add(Board.builder()
					.seq(i)
					.title("게시판 프로그램 테스트")
					.writer("도우너")
					.content("게시판 프로그램 테스트입니다...")
					.build());
		}
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello 타임리프. ^^");
	}
	
	@GetMapping("/hello1")
	public String hello1(Model model) {
		model.addAttribute("greeting", "Hello 타임리프. ^^");
		return "hello";
	}
	
	@GetMapping("/hello2")
	public ModelAndView hello2() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", "Hello 타임리프. ^^");
		mv.setViewName("hello");
		return mv;
	}
}