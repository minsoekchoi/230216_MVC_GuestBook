package com.ict.guestbook2.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.controller.GuestbookController;
import com.ict.guestbook2.model.service.GuestBook2Service;
import com.ict.guestbook2.model.vo.GuestBook2VO;

@Controller
public class GuestBook2Controller {
	// 0215
	// 1. 사진 업데이트 및 사진 다운로드 미구현
	// 2. POST 및 GET 안됨
	
	@Autowired
	private GuestBook2Service guestBook2Service;

	private static final Logger logger = LoggerFactory.getLogger(GuestbookController.class);

	public void setGuestBook2Service(GuestBook2Service guestBook2Service) {
		this.guestBook2Service = guestBook2Service;
	}

	@RequestMapping("gb2_list.do")
	public ModelAndView getGuestBook2List() {
		ModelAndView mv = new ModelAndView("guestbook2/guestbook2_list");
		logger.info("게시글 진입");
		List<GuestBook2VO> list = guestBook2Service.guestBook2List();
		mv.addObject("list", list);
		// 이름이 똑같다 게스트북1과 그럼 덮어 쓰기가 된다.
		return mv;
	}

	@RequestMapping("gb2_onelist.do")
	//@PostMapping(value = "gb2_onelist.do")
	public ModelAndView getGuestBook2OneList(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/guestbook2_onelist");
		GuestBook2VO guestBook2VO = guestBook2Service.guestBook2OneList(idx);
		mv.addObject("guestBook2VO", guestBook2VO);
		return mv;
	}

	@RequestMapping("gb2_write.do")
	public ModelAndView getGuestBook2Write() {
		logger.info("게시글 wirte 진입");
		return new ModelAndView("guestbook2/guestbook2_write");
	}

	@RequestMapping("gb2_write_ok.do")
	public ModelAndView getGuestBook2WriteOK(GuestBook2VO g2bvo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
		logger.info("게시글 작성 페이지 진입");
		try {
			String path = session.getServletContext().getRealPath("/resources/upload");
			MultipartFile f_param = g2bvo.getF_param()[0];
			if (f_param.equals("") || f_param == null) {
				g2bvo.setF_name("");
			} else {
				g2bvo.setF_name(f_param.getOriginalFilename());
			}
			int result = guestBook2Service.guestBook2Insert(g2bvo);
			if (result > 0) {
				// 업로드
				// ?? 기억 안남
				// f_name 과 f_param 을 구분해야함
				f_param.transferTo(new File(path + "/" + g2bvo.getF_name()));
			}

		} catch (Exception e) {
		}
		return mv;
	}

	@RequestMapping("gb2_delete.do")
	public ModelAndView getGuestBook2Delete(@RequestParam("idx") String idx) {
		// logger.info("게시글 delete 진입");
		ModelAndView mv = new ModelAndView("guestbook2/guestbook2_delete");
		GuestBook2VO guestBook2VO = guestBook2Service.guestBook2OneList(idx);
		mv.addObject("guestBook2VO", guestBook2VO);
		return mv;
	}

	@RequestMapping("gb2_delete_ok.do")
	public ModelAndView getGuestBook2DeleteOK(@RequestParam("idx") String idx) {
		// logger.info("게시글 delete_ok 진입");
		ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
		guestBook2Service.guestBook2Delete(idx);
		return mv;
	}
	
	@RequestMapping("gb2_update.do")
	public ModelAndView getGuestBook2Udate(@RequestParam("idx") String idx) {
		logger.info("게시글 update 진입");
		ModelAndView mv = new ModelAndView("guestbook2/guestbook2_update");
		GuestBook2VO guestBook2VO = guestBook2Service.guestBook2OneList(idx);
		logger.info("진입"+guestBook2VO.getPwd());
		mv.addObject("guestBook2VO", guestBook2VO);
		return mv;
	}
	
	@RequestMapping("gb2_update_ok.do")
	public ModelAndView getGuestBook2UdateOK(GuestBook2VO guestBook2VO) {
		logger.info("게시글 update_ok 진입");
		ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
		guestBook2Service.guestBook2Update(guestBook2VO);
		return mv;
	}

}
