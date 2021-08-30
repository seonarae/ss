package com.myspring.pro27.member.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.myspring.pro27.member.service.MemberService;
import com.myspring.pro27.member.vo.MemberVO;



@Controller("memberController")
@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController{
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	/*log4j작업*/
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	
	@Override
	@RequestMapping(value="/member/listMember.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=getViewName(request);
		//System.out.println ppt160쪽 아래 작업
		logger.info("info 레벨: viewName =" + viewName);
		logger.debug("debug 레벨: viewName =" + viewName);
		List membersList=memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	//멤버로그인작업 ppt226
	//@Override
	@RequestMapping(value="/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberVO);
				session.setAttribute("isLogOn", true);
				mav.setViewName("redirect:/member/listMember.do");
			} else {
				rAttr.addAttribute("result", "loginFailed");
				mav.setViewName("redirect:/member/loginForm.do");
				}
		return mav;
	}
	
	//@Override
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		HttpSession session=request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMember.do");
		return mav;
	}
	
	//요기까지 로그인 기능 구현하기 추가
	
	@Override
	@RequestMapping(value="/member/addMembers.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member")MemberVO member, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		int result=0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/removeMembers.do", method=RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id")String id, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav= new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	@RequestMapping(value="/member/*Form.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam(value="result", required=false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=getViewName(request);
		ModelAndView mav=new ModelAndView();
		mav.addObject("result", result); //로그인 기능 구현 추가
		mav.setViewName(viewName);
		return mav;
	}
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath();
		String uri=(String) request.getAttribute("javax.servlet.include.request_uri");
		if(uri==null||uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		int end;
		if(uri.indexOf(";") != -1) {
			end=uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end=uri.length();
		}
		
		String viewName= uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName=viewName.substring(0, viewName.lastIndexOf("."));
		} if (viewName.lastIndexOf("/") != -1) {
			viewName=viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
		}
		return viewName;
	}
}