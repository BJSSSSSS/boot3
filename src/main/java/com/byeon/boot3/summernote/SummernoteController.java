package com.byeon.boot3.summernote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/summernote/*")
public class SummernoteController {
	
	@Autowired
	private SummernoteService summernoteService;
	
		//summerFileDelete
		@GetMapping("summerFileDelete")
		public ModelAndView setSummerFileDelete(String fileName) throws Exception{
			ModelAndView mv = new ModelAndView();
			//System.out.println(fileName);
			
			boolean result = summernoteService.setSummerFileDelete(fileName);
			//System.out.println(result);
			mv.addObject("result", result);
			mv.setViewName("common/result");
			return mv;
		}
		
		
		//summerFileUpload
		@PostMapping("summerFileUpload")
		public ModelAndView setSummerFileUpload(MultipartFile summerFiles) throws Exception{
			ModelAndView mv = new ModelAndView();
			
			//System.out.println(files.getOriginalFilename());
			//System.out.println(files.getSize());
			
			String fileName = summernoteService.setSummerFileUpload(summerFiles);
			//System.out.println(fileName);
			mv.addObject("result", fileName);
			mv.setViewName("common/result");
			
			return mv;
		}

}
