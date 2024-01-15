package kr.ezen.boot3.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

// ResultSet을 받아 VO를 완성해주는 클래스 (이것이 잇어야 jdbctemplate에서 vo를 받을수 있다.!
public class MemoRowMapper implements RowMapper<MemoVO>{

	@Override
	public MemoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemoVO memoVO = new MemoVO();
		memoVO.setIdx(rs.getInt("idx"));
		memoVO.setName(rs.getString("name"));
		memoVO.setPassword(rs.getString("password"));
		memoVO.setContent(rs.getString("content"));
		memoVO.setRegDate(rs.getTimestamp("regDate"));
		memoVO.setIp(rs.getString("ip"));
		return memoVO;
	}
	
}
