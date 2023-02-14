package com.ict.guestbook.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.ict.guestbook.model.vo.GuestbookVO;

public class GuestbookDAO {
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	// 리스트
	public List<GuestbookVO> getList() {
		return sqlSessionTemplate.selectList("guestbook.list");
	}
	
	// 상세 보기
	public GuestbookVO getOneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook.onelist", idx);
	}
	
	// 삽입
	public int getInsert(GuestbookVO guestbookvo) {
		return sqlSessionTemplate.insert("guestbook.insert", guestbookvo);
	}
	
	// 삭제
	public int getDelete(String idx) {
		return sqlSessionTemplate.delete("guestbook.delete", idx);
	}
	
}
