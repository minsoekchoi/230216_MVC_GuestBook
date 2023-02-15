package com.ict.guestbook.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.guestbook.model.vo.GuestbookVO;

// 와 아무것도 안해놨네
@Repository
public class GuestbookDAO {

	// 이것도 안했네
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// guestbook 을 위한
	// 만약 guestbook2가 생기면 아래로 쭉 만드나??
	// 리스트
	// Guestbook 리스트
	public List<GuestbookVO> getbookList() {
		// ArrayList 는 객체
		// List 는 인터페이스
		List<GuestbookVO> list = sqlSessionTemplate.selectList("guestbook.list");
		return list;
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

	// 수정
	public int getUpdate(GuestbookVO guestbookvo) {
		return sqlSessionTemplate.delete("guestbook.update", guestbookvo);
	}

}
