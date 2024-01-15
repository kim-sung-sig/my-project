package kr.ezen.boot3.dao;

import java.util.Date;

import kr.ezen.boot3.vo.TestVO;

public interface TestDAO {
	Date selectToday();
	TestVO selectCalc(TestVO testVO);
}
