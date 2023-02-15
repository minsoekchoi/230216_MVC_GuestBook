package com.ict.guestbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.model.service.GuestbookService;
import com.ict.guestbook.model.vo.GuestbookVO;
import com.ict.guestbook2.model.vo.GuestBook2VO;

@Controller
public class GuestbookController {

	private static final Logger logger = LoggerFactory.getLogger(GuestbookController.class);

	@Autowired
	private GuestbookService guestbookservice;
	// 이유를 몰라
	// ????????????
	// 뭘물어봐야 될지도 모라
	// 이건 왜 해놓은걸까
	// 인터페이스라 서비스는 생성이 안된다.
	// 하지만 인터페이스 GuestbookService를 먼저가는게 맞다고 한다.

	// 게시판 목록 페이지
	@RequestMapping("list.do")
	public ModelAndView getGuestBookList() {
		// logger.info("게시판 목록 페이지 진입");
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestbookVO> list = guestbookservice.guestbookList();
		mv.addObject("list", list);

		return mv;
	}

	// 상세 보기
	@RequestMapping("onelist.do")
	public ModelAndView getGuestBookOneList(@RequestParam("idx") String idx) {
		logger.info("게시판 상세목록 페이지 진입");
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestbookVO guestbookvo = guestbookservice.guestbookOneList(idx);
		mv.addObject("guestbookvo", guestbookvo);
		return mv;
	}
	
	// 삽입 시 작성 페이지 진입
	@RequestMapping("write.do")
	public ModelAndView getInsert(GuestbookVO guestbookvo) {
		logger.info("게시판 삽입 시 작성 페이지 진입");
		ModelAndView mv = new ModelAndView("guestbook/write");
		return mv;
	}
	
	@RequestMapping("write_ok.do")
	public ModelAndView getInsertOK(GuestbookVO guestbookvo) {
		logger.info("게시판 삽입 시 페이지 진입");
		ModelAndView mv = new ModelAndView("redirect:list.do");
		int result = guestbookservice.guestbookInsert(guestbookvo);
		if (result > 0) {
			return mv;
		}else {
			return new ModelAndView("error");
		}
	}

	// 삭제 시 패스워드 확인
	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	//@RequestMapping("delete.do")
	public ModelAndView getDelete(@RequestParam("idx") String idx) {
		logger.info("게시판 삭제 시 패스워드 페이지 진입");
		ModelAndView mv = new ModelAndView("guestbook/delete");
		GuestbookVO guestbookVO = guestbookservice.guestbookOneList(idx);
		mv.addObject("guestbookVO", guestbookVO);
		return mv;
	}

	// 삭제
	@RequestMapping(value = "delete_ok.do", method = RequestMethod.POST)
	//@RequestMapping("delete_ok.do")
	public ModelAndView getDeleteOK(@RequestParam("idx") String idx) {
		logger.info("게시판 삭제 페이지 진입");
		ModelAndView mv = new ModelAndView("redirect:list.do");
		int result = guestbookservice.guestbookDelete(idx);
		if (result > 0) {
			return mv;
		} else {
			return new ModelAndView("error");
		}
	}
	
	@RequestMapping("update.do")
	public ModelAndView getUdate(@RequestParam("idx") String idx) {
		logger.info("게시글 update 진입");
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestbookVO guestbookVO = guestbookservice.guestbookOneList(idx);
		mv.addObject("guestbookVO", guestbookVO);
		return mv;
	} 
	
	@RequestMapping("update_ok.do")
	public ModelAndView getUdateOK(GuestbookVO guestbookVO) {
		logger.info("게시글 update_ok 진입");
		ModelAndView mv = new ModelAndView("redirect:list.do");
		guestbookservice.guestbookUpdate(guestbookVO);
		return mv;
	} 
	
	
}
