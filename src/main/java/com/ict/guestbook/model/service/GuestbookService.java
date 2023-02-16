package com.ict.guestbook.model.service;

import java.util.List;

import com.ict.guestbook.model.vo.GuestbookVO;

public interface GuestbookService {
	// 전체 보기
	List<GuestbookVO> guestbookList();

	// 상세 보기
	GuestbookVO guestbookOneList(String idx);

	// 삭제
	int guestbookDelete(String idx);

	// 수정
	int guestbookUpdate(GuestbookVO vo);

	// 삽입
	int guestbookInsert(GuestbookVO vo);

}
