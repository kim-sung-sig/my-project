package kr.ezen.boot3.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.ezen.boot3.vo.MemoRowMapper;
import kr.ezen.boot3.vo.MemoVO;

@Repository(value = "memoDAO")
public class MemoDAOImpl implements MemoDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 전체크기
	@Override
	public int selectCount() throws SQLException {
		return jdbcTemplate.queryForObject("select count(*) from memo", int.class);
	}
	// 한개얻기
	@Override
	public MemoVO selectByIdx(int idx) throws SQLException {
		String sql = "select * from memo where idx=?";
		return jdbcTemplate.queryForObject(sql, new MemoRowMapper(), new Object[] {idx});
	}
	// 한페이지 얻기
	@Override
	public List<MemoVO> selectList(HashMap<String, Integer> map) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select R.* from");
		sb.append(" (");
		sb.append(	" select rownum rnum, Q.* from ");
		sb.append(		" (select * from memo order by idx desc) Q ");
		sb.append(	" where rownum <= ?");
		sb.append(	" ) R ");
		sb.append("where rnum >= ?");
		return jdbcTemplate.query(sb.toString(), new MemoRowMapper(), new Object[] {map.get("endNo"),map.get("startNo")});
	}
	// 저장
	@Override
	public int insert(MemoVO vo) throws SQLException {
		String sql = "insert into memo values(memo_idx_seq.nextval,?,?,?,sysdate,?)";
		return jdbcTemplate.update(sql,new Object[] {vo.getName(),vo.getPassword(),vo.getContent(),vo.getIp()});
	}
	// 수정
	@Override
	public int update(MemoVO vo) throws SQLException {
		String sql = "update memo set content = ? ,regdate = sysdate, ip = ? where idx = ?";
		return jdbcTemplate.update(sql,new Object[] {vo.getContent(),vo.getIp(),vo.getIdx()});
	}
	// 삭제
	@Override
	public int delete(int idx) throws SQLException {
		String sql = "delete from memo where idx=?";
		return jdbcTemplate.update(sql,new Object[] {idx});
	}
}
