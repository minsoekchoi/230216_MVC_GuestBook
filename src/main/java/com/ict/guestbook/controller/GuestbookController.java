package com.ict.guestbook.controller;

import java.util.ArrayList;
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

@Controller
public class GuestbookController {

	private static final Logger logger = LoggerFactory.getLogger(GuestbookController.class);

	@Autowired
	private GuestbookService guestbookservice;

	// 게시판 목록 페이지
	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public ModelAndView getList() {
		// logger.info("게시판 목록 페이지 진입");
		ModelAndView mv = new ModelAndView("list");
		List<GuestbookVO> list = guestbookservice.guestbookList();
		mv.addObject("list", list);

		return mv;
	}

	// 상세 보기
	@RequestMapping("onelist.do")
	public ModelAndView getOneList(@RequestParam("idx") String idx) {
		// logger.info("게시판 상세목록 페이지 진입");
		ModelAndView mv = new ModelAndView("onelist");
		GuestbookVO guestbookvo = guestbookservice.guestbookOneList(idx);
		mv.addObject("guestbookvo", guestbookvo);
		return mv;
	}
	
	// 삽입
	@RequestMapping("insert.do")
	public ModelAndView getInsert(GuestbookVO guestbookvo) {
		logger.info("게시판 삽입 시 페이지 진입");
		ModelAndView mv = new ModelAndView("index");
		int result = guestbookservice.guestbookInsert(guestbookvo);
		if (result > 0) {
			return mv;
		}else {
			return new ModelAndView("error");
		}
	}
	

	// 삭제 시 패스워드 확인
	// 삭제 아직 못함
	@RequestMapping("delete.do")
	public ModelAndView getDeleteOK(@RequestParam("idx") String idx) {
		logger.info("게시판 삭제 시 패스워드 페이지 진입");
		ModelAndView mv = new ModelAndView("redirect:delete.do");
		return mv;
	}

	// 삭제
	// 삭제 아직 못함
	@RequestMapping("delete_ok.do")
	public ModelAndView getDelete(@RequestParam("idx") String idx) {
		logger.info("게시판 삭제 페이지 진입");
		ModelAndView mv = new ModelAndView("redirect:list.do");
		int result = guestbookservice.guestbookDelete(idx);
		if (result > 0) {
			return mv;
		} else {
			return new ModelAndView("error");
		}
	}
	
}
