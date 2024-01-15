package kr.ezen.boot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import kr.ezen.boot3.service.MemoService;
import kr.ezen.boot3.vo.CommonVO;
import kr.ezen.boot3.vo.MemoVO;

@Controller
@Configuration
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST}) // 모든 방식의 요청을 허용한다.(메소드 지정)
	public String index(@ModelAttribute(value = "cv") CommonVO cv, Model model) {
		model.addAttribute("pv",memoService.selectList(cv));
		return "index";
	}
	@PostMapping(value = "/update") // 모든 방식의 요청을 허용한다.(메소드 지정)
	public String update(@ModelAttribute(value = "cv") CommonVO cv, @ModelAttribute(value = "vo") MemoVO vo, HttpServletRequest request, Model model) {
		// IP는 수동으로 넣어주자.
		vo.setIp(request.getRemoteAddr());
		// String viewPage = "redirect:/";
		switch (cv.getMode()) {
			case "insert": {
				memoService.insert(vo);
				// viewPage += "?p=1&s="+cv.getSizeOfPage()+"&b="+cv.getSizeOfBlock();
				break;
			}
			case "update": {
				memoService.update(vo);
				// viewPage += "?p="+cv.getCurrentPage()+"&s="+cv.getSizeOfPage()+"&b="+cv.getSizeOfBlock();
				break;
			}
			case "delete": {
				memoService.delete(vo);
				// viewPage += "?p="+cv.getCurrentPage()+"&s="+cv.getSizeOfPage()+"&b="+cv.getSizeOfBlock();
				break;
			}
		}
		// return "redirect:/"; // 지정주소로 가라~~ 근데 이렇게만 보내면 무조건 1페이지로 가버림..ㅠ
		// return viewPage;
		
		//Post 전송을 위하여 html 페이지 1개를 만들어서 사용해보자!
		model.addAttribute("p",cv.getMode().equals("insert") ? 1: cv.getCurrentPage());
		model.addAttribute("s",cv.getSizeOfPage());
		model.addAttribute("b",cv.getSizeOfBlock());
		return "update";
	}
}
