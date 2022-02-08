package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

//스프링에게 제어권을 맡긴 3가지
//1) 컨트롤러 : @Controller, @RestController(json으로 리턴하는 컨트롤러)
//2) 서비스 : @Service
//3) 모델(dao) : @Repository

@Controller //컨트롤러 빈으로 등록
@RequestMapping("memo/*") //공통적인 url pattern
public class MemoController {
	@Inject
	MemoService memoService;
	
	@RequestMapping("list.do") //세부적인 url
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items=memoService.list();
		mav.setViewName("memo/memo_list");
		mav.addObject("list", items);
		return mav;
		//return new ModelAndView("memo/memo_list","list",items);
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		memoService.insert(dto);
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx
			, ModelAndView mav) {
		//포워딩
		mav.setViewName("memo/view");
		//전달할 데이터
		mav.addObject("dto", memoService.memo_view(idx));
		return mav;
	}
	
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx
			, @ModelAttribute MemoDTO dto) {
		//메모수정
		memoService.update(dto);
		//수정완료 후 목록으로 이동
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		//레코드 삭제처리
		memoService.delete(idx);
		//목록
		return "redirect:/memo/list.do";
	}
	
	

}
