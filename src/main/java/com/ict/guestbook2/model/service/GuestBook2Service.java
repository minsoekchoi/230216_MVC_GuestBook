package com.ict.guestbook2.model.service;

import java.util.List;

import com.ict.guestbook2.model.vo.GuestBook2VO;

public interface GuestBook2Service {
	List<GuestBook2VO> guestBook2List();
	
	GuestBook2VO guestBook2OneList(String idx);
	
	int guestBook2Insert(GuestBook2VO g2vo) throws Exception;
	
	int guestBook2Delete(String idx);
	
	int guestBook2Update(GuestBook2VO g2vo);
}
