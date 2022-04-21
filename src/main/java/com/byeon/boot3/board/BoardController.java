package com.byeon.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byeon.boot3.util.Pager;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "board";
	}
	
	//fileDown
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFilesVO boardFilesVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boardFilesVO = boardService.getFileDetail(boardFilesVO);
		
		//속성명은 fileDown 클래스에서 사용하는 이름과 동일하게
		mv.addObject("fileVO", boardFilesVO);
		
		//view의 이름은 fileDown 클래스에서 Bean(클래스) 이름과 동일하게
		mv.setViewName("fileDown");
		
		return mv;
	}
	
	
	
	
	//list
	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = boardService.getList(pager);
		
		mv.setViewName("board/list");
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		
		return mv;
	}
	
	//detail
	@GetMapping("detail")
	public ModelAndView getDetail(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boardVO = boardService.getDetail(boardVO);
		
		mv.addObject("vo", boardVO);
		mv.setViewName("board/detail");
		
		return mv;
	}

	//add Form 이동
	@GetMapping("add")
	public void setAdd() throws Exception{}
		
	
	//add DB 이동
	@PostMapping("add")
	public ModelAndView setAdd(BoardVO boardVO, MultipartFile [] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//업로드시 파일명을 출력
//		for(MultipartFile f : files) {
//			System.out.println(f.getOriginalFilename());
//		}	
		
		int result = boardService.setAdd(boardVO, files);
		
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	//delete
	@GetMapping("delete")
	public ModelAndView setDelete(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = boardService.setDelete(boardVO);
	
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	//update form 이동
	@GetMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = boardService.getDetail(boardVO);
		mv.addObject("vo", boardVO);
		mv.setViewName("board/update");
		return mv;
	}
	
	//update DB
	@PostMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO, ModelAndView mv) throws Exception{
		
		int result = boardService.setUpdate(boardVO);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	
	
	

}
