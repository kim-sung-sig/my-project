package kr.ezen.boot3.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ezen.boot3.dao.MemoDAO;
import kr.ezen.boot3.vo.MemoVO;

@Configuration
@RestController
@RequestMapping(value = "/api")
public class MemoAPIController {
	
	@Autowired
	MemoDAO memoDAO;
	
	@GetMapping("/selectByIdx") // ajax로 호출하기 위한 주소 json으로 응답한다.! (RestController로 vo를 읽으면 json으로 읽힌다.)
	public MemoVO selectByIdx(@RequestParam(value = "idx") Integer idx) {
		MemoVO memoVO = null;
		try {
			memoVO = memoDAO.selectByIdx(idx);
			if(memoVO!=null) {
				memoVO.setPassword("");// 중요한 개인정보를 지우고!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memoVO;
	}
}
