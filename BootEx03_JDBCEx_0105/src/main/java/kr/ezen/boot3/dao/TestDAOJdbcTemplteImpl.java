package kr.ezen.boot3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ezen.boot3.vo.TestVO;

@Repository("testDAO2")
public class TestDAOJdbcTemplteImpl implements TestDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Date selectToday() {
		return jdbcTemplate.queryForObject("select sysdate today from dual", Date.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public TestVO selectCalc(TestVO testVO) {
		int num1 = testVO.getNum1();
		int num2 = testVO.getNum2();
		return 
			jdbcTemplate
			.queryForObject("select sysdate today, ? num1, ? num2, ?+? sum, ?*? mul from dual",
							new RowMapper<TestVO>() {
								@Override
								public TestVO mapRow(ResultSet rs, int rowNum) throws SQLException {
									TestVO testVO = new TestVO();
									testVO.setToday(rs.getTimestamp("today"));
									testVO.setNum1(rs.getInt("num1"));
									testVO.setNum2(rs.getInt("num2"));
									testVO.setSum(rs.getInt("sum"));
									testVO.setMul(rs.getInt("mul"));
									return testVO;
								}
							},
				            new Object[] {num1, num2, num1, num2, num1, num2});
	}
}
