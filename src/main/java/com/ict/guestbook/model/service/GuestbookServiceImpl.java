package com.ict.guestbook.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ict.guestbook.model.dao.GuestbookDAO;
import com.ict.guestbook.model.vo.GuestbookVO;

@Service
public class GuestbookServiceImpl implements GuestbookService {
	// impl 사용 이유
	// @Autowired
	@Inject
	private GuestbookDAO guestbookDAO;

	public void setGuestbookDAO(GuestbookDAO guestbookDAO) {
		this.guestbookDAO = guestbookDAO;
	}

	@Override
	public List<GuestbookVO> guestbookList() {
		return guestbookDAO.getbookList();
	}

	@Override
	public GuestbookVO guestbookOneList(String idx) {
		return guestbookDAO.getOneList(idx);
	}

	@Override
	public int guestbookInsert(GuestbookVO vo) {
		return guestbookDAO.getInsert(vo);
	}

	@Override
	public int guestbookDelete(String idx) {

		return guestbookDAO.getDelete(idx);
	}

	@Override
	public int guestbookUpdate(GuestbookVO vo) {

		return guestbookDAO.getUpdate(vo);
	}

}
