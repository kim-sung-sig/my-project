package kr.ezen.boot3.service;

import kr.ezen.boot3.vo.CommonVO;
import kr.ezen.boot3.vo.MemoVO;
import kr.ezen.boot3.vo.PagingVO;

public interface MemoService {
	// 1. 목록보기
	PagingVO<MemoVO> selectList(CommonVO cv);
	// 2. 저장하기
	int insert(MemoVO vo);
	// 3. 수정하기
	int update(MemoVO vo);
	// 4. 삭제하기
	int delete(MemoVO vo);
	// 5. 한개 얻기
	MemoVO selectByIdx(int idx);
}
