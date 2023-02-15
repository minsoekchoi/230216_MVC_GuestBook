package com.ict.guestbook2.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook2.model.dao.GuestBook2DAO;
import com.ict.guestbook2.model.vo.GuestBook2VO;

@Service
public class GuestBook2ServiceImpl implements GuestBook2Service {

	@Autowired
	private GuestBook2DAO guestBook2DAO;
	
	public void setGuestBook2DAO(GuestBook2DAO guestBook2DAO) {
		this.guestBook2DAO = guestBook2DAO;
	}

	@Override
	public List<GuestBook2VO> guestBook2List() {
		return guestBook2DAO.guestBook2List();
	}

	@Override
	public GuestBook2VO guestBook2OneList(String idx) {
		return guestBook2DAO.guestBook2OneList(idx);
	}

	@Override
	public int guestBook2Insert(GuestBook2VO g2vo) throws Exception{
		return guestBook2DAO.guestBook2Insert(g2vo);
	}

	@Override
	public int guestBook2Delete(String idx) {
		return guestBook2DAO.guestBook2Delete(idx);
	}

	@Override
	public int guestBook2Update(GuestBook2VO guestBook2VO) {
		return guestBook2DAO.guestBook2Update(guestBook2VO);
	}
}
