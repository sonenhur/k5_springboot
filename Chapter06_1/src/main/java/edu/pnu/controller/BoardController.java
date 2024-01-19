package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
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