package kr.ezen.boot3.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import kr.ezen.boot3.vo.MemoVO;


public interface MemoDAO {
	// 1. 전체갯수
	int selectCount() throws SQLException;
	// 2. 한개얻기
	MemoVO selectByIdx(int idx) throws SQLException;
	// 3. 한페이지얻기
	List<MemoVO> selectList(HashMap<String, Integer> map)throws SQLException;
	// 4. 저장
	int insert(MemoVO vo) throws SQLException;
	// 5. 수정
	int update(MemoVO vo) throws SQLException;
	// 6. 삭제
	int delete(int idx) throws SQLException;
}
