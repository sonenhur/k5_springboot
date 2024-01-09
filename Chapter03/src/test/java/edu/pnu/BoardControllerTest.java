package edu.pnu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class BoardControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testHello() throws Exception {
		// http://localhost:8080/hello?name=둘리
		mockMvc.perform(get("/hello").param("name","둘리"))
		.andExpect(status().isOk())
		.andExpect(content().string("Hello : 둘리"))
		.andDo(print()); 
	}
	
	@Test
	public void testHello1() throws Exception{
		mockMvc.perform(get("/hello").param("name", "홍길동"))
		.andExpect(status().isOk())
		.andExpect(content().string("Hello : 홍길동"))
		.andDo(print()); 
	}
//	@Test
//	@Order(3)
//	public void test01() {
//		System.out.println("1");
//	}
//	
//	@Test
//	@Order(1)
//	public void test02() {
//		System.out.println("2");
//	}
//	
//	@Test
//	@Order(2)
//	public void test03() {
//		System.out.println("3");
//	}
}