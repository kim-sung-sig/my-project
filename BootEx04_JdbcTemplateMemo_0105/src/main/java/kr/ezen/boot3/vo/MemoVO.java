package kr.ezen.boot3.vo;

import java.util.Date;

import lombok.Data;

// 테이블 1개당 1개의 VO를 만든다. 테이블의 필드명과 동일하게 만듭니다.
@Data
public class MemoVO {
	private int idx;
	private String name;
	private String password;
	private String content;
	private Date regDate;
	private String ip;
}
