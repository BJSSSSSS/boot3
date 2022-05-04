package com.byeon.boot3.product;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byeon.boot3.member.MemberVO;
import com.byeon.boot3.util.Pager;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "product";
	}
	
	//fileDelete(ajax)
	@PostMapping("fileDelete")
	public ModelAndView setFileDelete(ProductFilesVO productFilesVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//System.out.println(productFilesVO.getFileNum());
		
		int result = productService.setFileDelete(productFilesVO);
		
		mv.addObject("result", result);
		mv.setViewName("common/result");
		
		return mv;
	}
	
	
	
	
	@GetMapping("detail")
	public ModelAndView getDetail(ProductVO productVO) throws Exception{
//		if(productVO.getId() == null) {
//			throw new SQLException();
//		}
		//parameter는 productNum
		//모든 구매자가 보는 페이지
		ModelAndView mv = new ModelAndView();
		
		productVO = productService.getDetail(productVO);
		
		mv.addObject("vo", productVO);
		mv.setViewName("product/detail");
		
		return mv;
	}
	
	@GetMapping("manageDetail")
	public ModelAndView getManageDetail(ProductVO productVO, HttpSession session) throws Exception{
		//parameter는 productNum
		//판매자가 보는 페이지
		ModelAndView mv = new ModelAndView();
		
//		MemberVO memberVO = (MemberVO)session.getAttribute("member");
//		productVO.setId(memberVO.getId());
		productVO = productService.getDetail(productVO);
		
		mv.addObject("vo", productVO);
		mv.setViewName("product/manageDetail");
		
		return mv;
	}
	
	
	
	
	//판매자용 list
	@GetMapping("manage")
	public ModelAndView manage(Pager pager, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		pager.setId(memberVO.getId());
	
		List<ProductVO> ar = productService.getList(pager);
		
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		
		mv.setViewName("product/manage");
		
		return mv;
	}
	
	
	
	//list
	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<ProductVO> ar = productService.getList(pager);
		
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("product/list");
		return mv;
	}

	
	//add form 이동
	@GetMapping("add")
	public void setAdd(@ModelAttribute ProductVO productVO) throws Exception{}
	
	
	//add DB
	@PostMapping("add")
	public ModelAndView setAdd(@Valid ProductVO productVO, BindingResult bindingResult, MultipartFile [] files, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("product/add");
			return mv;
		}
		
//		for(MultipartFile f: files) {
//			System.out.println(f.getOriginalFilename());
//			System.out.println(f.getSize());
//		}
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		productVO.setId(memberVO.getId()); 
		
		//System.out.println(productVO.getSale());
		
		int result = productService.setAdd(productVO, files);
		mv.setViewName("common/result");
		mv.addObject("result", result);
		//비동기 방식일 경우
		//mv.setViewName("redirect:./list");

		return mv;
	}
	
	
	//list
	@GetMapping("ajaxList")
	public ModelAndView getAjaxList(Pager pager, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		pager.setId(memberVO.getId());
		
		List<ProductVO> ar = productService.getList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("common/productList");
		return mv;
	}
	
	//update form
	@GetMapping("update")
	public ModelAndView setUpdate(ProductVO productVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		productVO = productService.getDetail(productVO);
		
		mv.addObject("vo", productVO);
		mv.setViewName("product/update");
		
		return mv;
	}
	
	//update DB
	@PostMapping("update")
	public ModelAndView setUpdate(ProductVO productVO, MultipartFile [] files) throws Exception{
		ModelAndView mv = new ModelAndView();

		int result = productService.setUpdate(productVO, files);
		
		if(result>0) {
			mv.setViewName("redirect:./manage");
		}else {
			mv.addObject("msg","Update Fail");
			mv.addObject("path","./manageDetail?productNum="+productVO.getProductNum());
			mv.setViewName("common/getResult");
		}
		return mv;
	}
	

	//delete
	@GetMapping("delete")
	public ModelAndView setDelete(ProductVO productVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = productService.setDelete(productVO);
		
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	
	
	//예외처리 메서드 ++++++++++++++++++++++++++++++++++++++++++++++
//	@ExceptionHandler(BindException.class)
//	public ModelAndView ex1() {
//		ModelAndView mv = new ModelAndView();
//		
//		System.out.println("예외 발생 처리");
//		
//		mv.setViewName("error/error");
//		return mv;
//	}
//	
//	@ExceptionHandler(NullPointerException.class)
//	public ModelAndView ex2() {
//		ModelAndView mv = new ModelAndView();
//		
//		System.out.println("NullPointException 예외 발생 처리");
//		
//		mv.setViewName("error/error");
//		return mv;
//	}
//	
//	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView ex3() {
//		ModelAndView mv = new ModelAndView();
//		
//		System.out.println("Exception 예외 발생 처리");
//		
//		mv.setViewName("error/error");
//		return mv;
//	}
//
//	@ExceptionHandler(Throwable.class)
//	public ModelAndView ex4() {
//		ModelAndView mv = new ModelAndView();
//		
//		System.out.println("Throwable 예외 발생 처리");
//		
//		mv.setViewName("error/error");
//		return mv;
//	}
	
	
	
	
	
	
	
}
