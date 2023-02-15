package com.ict.guestbook2.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.guestbook2.model.vo.GuestBook2VO;

@Repository
public class GuestBook2DAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public List<GuestBook2VO> guestBook2List() {
		return sqlSessionTemplate.selectList("guestbook2.list");
	}
	
	public int guestBook2Insert(GuestBook2VO g2vo) throws Exception {
		return sqlSessionTemplate.insert("guestbook2.insert", g2vo);
	}
	
	public GuestBook2VO guestBook2OneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook2.onelist", idx);
	}
	
	public int guestBook2Delete(String idx) {
		return sqlSessionTemplate.delete("guestbook2.delete", idx);
	}
	
	public int guestBook2Update(GuestBook2VO guestBook2VO) {
		return sqlSessionTemplate.update("guestbook2.update", guestBook2VO);
	}
}
