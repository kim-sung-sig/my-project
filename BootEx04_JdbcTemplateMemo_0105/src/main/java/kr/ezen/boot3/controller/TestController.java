package kr.ezen.boot3.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ezen.boot3.dao.MemoDAO;
import kr.ezen.boot3.vo.MemoVO;

@Configuration		// 설정파일이다!
@Controller			// 템플릿을 이용하여 출력
@RequestMapping(value = "/test")	// 경로 지정
public class TestController {
	
	@Autowired		// 자동주입
	private JdbcTemplate jdbcTemplate;
	@Autowired
	MemoDAO memoDAO;
	
	@GetMapping("/")	//주소만든다.
	public String test(Model model) {
		model.addAttribute("today",jdbcTemplate.queryForObject("select sysdate from dual", Date.class));
		
		// DAO테스트
		try {
			MemoVO vo = new MemoVO();
			vo.setName("나그네");
			vo.setPassword("1234");
			vo.setContent("저장일되나여ㅛ?ㅠㅠ");
			vo.setIp("127.0.0.1");
			memoDAO.insert(vo);
			model.addAttribute("count",memoDAO.selectCount());
			// model.addAttribute("vo",memoDAO.selectByIdx(1));
			HashMap<String, Integer> map = new HashMap<>();
			map.put("startNo", 1);
			map.put("endNo", 10);
			model.addAttribute("voList",memoDAO.selectList(map));
			
			// 저장 해보자!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "test";
	}
}
