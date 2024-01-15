package kr.ezen.boot3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ezen.boot3.vo.TestVO;

@Repository("testDAO1")
public class TestDAODataSourceImpl implements TestDAO{
	
	@Autowired
	private DataSource dataSource;

	@Override
	public Date selectToday() {
		Date today = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select sysdate today from dual");
			rs.next();
			today = rs.getTimestamp("today");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return today;
	}

	@Override
	public TestVO selectCalc(TestVO testVO) {
		TestVO vo = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("select sysdate today, ?+? sum, ?*? mul from dual");
			stmt.setInt(1, testVO.getNum1());
			stmt.setInt(2, testVO.getNum2());
			stmt.setInt(3, testVO.getNum1());
			stmt.setInt(4, testVO.getNum2());
			rs = stmt.executeQuery();
			rs.next();
			vo = new TestVO();
			vo.setToday(rs.getTimestamp("today"));
			vo.setNum1(testVO.getNum1());
			vo.setNum2(testVO.getNum2());
			vo.setSum(rs.getInt("sum"));
			vo.setMul(rs.getInt("mul"));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return vo;
	}
}
