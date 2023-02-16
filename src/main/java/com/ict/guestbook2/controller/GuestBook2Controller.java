package com.ict.guestbook2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	// @PostMapping(value = "gb2_onelist.do")
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
		// cmd를 쓰는 경우
		// , @RequestParam("cmd") String cmd)
		// logger.info("게시글 delete 진입");
		ModelAndView mv = new ModelAndView("guestbook2/guestbook2_delete");
		GuestBook2VO guestBook2VO = guestBook2Service.guestBook2OneList(idx);
		mv.addObject("guestBook2VO", guestBook2VO);
		return mv;
	}

	// cmd로 하는법
//	@RequestMapping("gb2_delete.do")
//	public ModelAndView getGuestBook2Delete(@ModelAttribute("idx") String idx,
//			@ModelAttribute("pwd") String pwd,
//			@RequestParam("cmd") String cmd) {
//		// cmd를 쓰는 경우
//		// , @RequestParam("cmd") String cmd)
//		// logger.info("게시글 delete 진입");
//		ModelAndView mv = new ModelAndView();
//		String viewName = "" ;
//		if (cmd.equals("1")) {
//			mv.setViewName("guestbook2/guestbook2_delete");
//		}else {
//			int result = guestBook2Service.guestBook2Delete(idx);
//			mv.setViewName("redirect:gb2_list.do");
//		}
//		return mv;
//	}

	@RequestMapping("gb2_delete_ok.do")
	public ModelAndView getGuestBook2DeleteOK(@RequestParam("idx") String idx) {
		// logger.info("게시글 delete_ok 진입");
		ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
		guestBook2Service.guestBook2Delete(idx);
		return mv;
	}

//	@RequestMapping("gb2_update.do")
	// ModelAttribute 는 다음 단계로 넘겨준다.
	// 여기서는 onelist.do 값이 넘어가니까
	// 그래서 onlist.do 로 넘어갈때 idx를 따로 안넘겨도 된다.
//	public ModelAndView getGuestBook2Update(@ModelAttribute("idx") String idx,
//			@ModelAttribute("pwd") String pwd,
//			GuestBook2VO guestBook2VO, HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView();
// 		String cmd = request.getParameter("cmd");
//		if (cmd.equals("1")) {
//			mv.setViewName("guestbook2/guestbook2_update");
//			guestBook2VO = guestBook2Service.guestBook2OneList(idx);
//			mv.addObject("guestBook2VO", guestBook2VO);
//		} else {
//			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
//			try {
//				MultipartFile f_param = guestBook2VO.getF_param()[0];
//				String old_f_name = request.getParameter("old_f_name");
//
//				if (f_param.equals("") || f_param == null) {
//					guestBook2VO.setF_name(old_f_name);
//				} else {
//					guestBook2VO.setF_name(f_param.getOriginalFilename());
//				}
//				int result = guestBook2Service.guestBook2Update(guestBook2VO);
//				if (result > 0) {
//					f_param.transferTo(new File(path + "/" + guestBook2VO.getF_name()));
//				}
//			} catch (Exception e) {
//			}
//			mv.setViewName("redirect:gb2_onelist.do?");
//		}
//		return mv;
//	}

	@RequestMapping("gb2_update.do")
	public ModelAndView getGuestBook2Udate(@RequestParam("idx") String idx) {
		logger.info("게시글 update 진입");
		ModelAndView mv = new ModelAndView("guestbook2/guestbook2_update");
		GuestBook2VO guestBook2VO = guestBook2Service.guestBook2OneList(idx);
		logger.info("진입" + guestBook2VO.getPwd());
		mv.addObject("guestBook2VO", guestBook2VO);
		return mv;
	}

	// 여기도 잘 공부하자
	// 사진 업데이트를 잘 못해
	@RequestMapping("gb2_update_ok.do")
	public ModelAndView getGuestBook2UdateOK(GuestBook2VO guestBook2VO, HttpServletRequest request) {
		logger.info("게시글 update_ok 진입");
		ModelAndView mv = new ModelAndView();
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		try {
			MultipartFile f_param = guestBook2VO.getF_param()[0];
			String old_f_name = request.getParameter("old_f_name");

			File dir = new File(path);
			String[] arr = dir.list();
			List<String> k = Arrays.asList(arr);
			boolean res = k.contains(f_param.getOriginalFilename());
			String ori_filename = f_param.getOriginalFilename();

			if (ori_filename.equals("") || ori_filename == null) {
				guestBook2VO.setF_name(old_f_name);
			} else {
				guestBook2VO.setF_name(f_param.getOriginalFilename());
				if (!res) {
					f_param.transferTo(new File(path + "/" + guestBook2VO.getF_name()));
				}
			}
			int result = guestBook2Service.guestBook2Update(guestBook2VO);
		} catch (Exception e) {
			System.out.println("3" + e);
		}
		mv.setViewName("redirect:gb2_onelist.do?idx=" + guestBook2VO.getIdx());
		return mv;
	}

	// 여기는 잘 공부하자
	@RequestMapping("gb2_down.do")
	public void getFileDonw(@RequestParam("f_name") String f_name, HttpSession session, HttpServletResponse response) {
		logger.info("파일 다운로드 진입");
		String path = session.getServletContext().getRealPath("/resources/upload/" + f_name);
		try {
			String realpath = URLEncoder.encode(path, "utf-8");
			// 여기서 application 은 뭐니???
			// 확장자 별로 원래의 mime-type을 넣게되면.. 다운로드가 되지 않습니다. (해당 프로그램이 바로 뜨거나.. 이미지로 표시되거나..
			// 등)
			// 따라서 임의의 형식으로 넣는 것입니다. (그래야 인식할 수 없는 mime-type이기 때문에 브라우저가 다운로드를 처리합니다.)

			// 이 Header의 의미가 무엇이니

			response.setContentType("application/x-msdownload");
			// 클라이언트 즉 브라우저에서 요구하는 이름이다.
			// filename 은 정해진 이름이다. 그래서 filename이라고 해주어야한다.
			// 예를 들어 f_name 이런거 안됨
			response.setHeader("Content-Disposition", "attachment; filename=" + realpath);

			// 이 코드의 순서를 이해해야한다 혹은 외우거나
			// 근데 외우는거 바보 짓인듯
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
		}
	}

}
