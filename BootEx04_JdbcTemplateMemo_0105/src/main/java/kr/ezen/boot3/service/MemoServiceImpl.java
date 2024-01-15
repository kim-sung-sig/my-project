package kr.ezen.boot3.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.boot3.dao.MemoDAO;
import kr.ezen.boot3.vo.CommonVO;
import kr.ezen.boot3.vo.MemoVO;
import kr.ezen.boot3.vo.PagingVO;

@Service(value = "memoService")
public class MemoServiceImpl implements MemoService{
	
	@Autowired
	private MemoDAO memoDAO;
	
	@Override
	public PagingVO<MemoVO> selectList(CommonVO cv) {
		PagingVO<MemoVO> pv = null;
		try {
			int totalCount = memoDAO.selectCount();
			pv = new PagingVO<>(totalCount, cv.getCurrentPage(), cv.getSizeOfPage(), cv.getSizeOfBlock());
			HashMap<String, Integer> map = new HashMap<>();
			map.put("startNo", pv.getStartNo());
			map.put("endNo", pv.getEndNo());
			pv.setList(memoDAO.selectList(map));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pv;
	}

	@Override
	public int insert(MemoVO vo) {
		try {
			if(vo!=null) {
				return memoDAO.insert(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(MemoVO vo) {
		try {
			if(vo!=null) {
				MemoVO dbVO = memoDAO.selectByIdx(vo.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(vo.getPassword())) {
					return memoDAO.update(vo);					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(MemoVO vo) {
		try {
			if(vo!=null) {
				MemoVO dbVO = memoDAO.selectByIdx(vo.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(vo.getPassword())) {
					return memoDAO.delete(vo.getIdx());					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public MemoVO selectByIdx(int idx) {
		MemoVO vo = null;
		try {
			 vo = memoDAO.selectByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
