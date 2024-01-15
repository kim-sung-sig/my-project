package kr.ezen.boot3.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ezen.boot3.service.TestService;

@Configuration
@Controller
public class HomeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TestService testService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("date1", new Date());
		model.addAttribute("date2", jdbcTemplate.queryForObject("select sysdate from dual", Date.class));
		model.addAttribute("date3", testService.getToday1());
		model.addAttribute("date4", testService.getToday2());

		model.addAttribute("vo1", testService.getTestVO1(11, 22));
		model.addAttribute("vo2", testService.getTestVO2(55, 21));
		return "index";
	}

	// -----------------------------------------------------------------------------------------------------
	// 스프링에서 자동으로 등록한 빈을 확인하는 방법
	// -----------------------------------------------------------------------------------------------------
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	CommandLineRunner getCommandLineRunner() {
		return (args) -> {
			if (applicationContext != null) {
				String[] beans = applicationContext.getBeanDefinitionNames();
				System.out.println("-".repeat(120));
				System.out.println("스프링에서 자동으로 등록한 빈 목록");
				System.out.println("전체 : " + beans.length + "개가 등록됨");
				System.out.println("-".repeat(120));
				for (String bean : beans) {
					System.out.println("bean : " + bean);
				}
				System.out.println("-".repeat(120));
				System.out.println("전체 : " + beans.length + "개가 등록됨");
				System.out.println("-".repeat(120));
			}
		};
	}
	// -----------------------------------------------------------------------------------------------------
}
