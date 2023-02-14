package com.ict.guestbook.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook.model.dao.GuestbookDAO;
import com.ict.guestbook.model.vo.GuestbookVO;

@Service
public class GuestbookServiceImpl implements GuestbookService {
	
	@Autowired
	private GuestbookDAO guestbookdao;
	
	@Override
	public List<GuestbookVO> guestbookList() {
		return guestbookdao.getList();
	}
	
	@Override
	public GuestbookVO guestbookOneList(String idx) {
		return guestbookdao.getOneList(idx);
	}
	
	@Override
	public int guestbookInsert(GuestbookVO vo) {
		return guestbookdao.getInsert(vo);
	}

	@Override
	public int guestbookDelete(String idx) {

		return guestbookdao.getDelete(idx);
	}
	
	@Override
	public int guestbookUpdate(GuestbookVO vo) {

		return 0;
	}
	
}
