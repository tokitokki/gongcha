package mydogcherry.kr.gongcha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "mydogcherry.kr.gongcha.controllers.RootController")
@RequestMapping(value = "/")
public class RootController { //메인 페이지에 관한 Controller

    @GetMapping(value = "main") //주소창에 "http://localhost:8080/main" 입력 시 실행되는 Mapping
    public ModelAndView getMain(ModelAndView modelAndView) { //model 기능과 view 경로를 같이 설정가능한 ModelAndView 객체를 만든다.
        //실행되는 해당 페이지 제목을 공차로 설정할 수 있도록 String 타입의 타이틀 제목("공차")을 만들고 View로 데이터를 전송한다.
        modelAndView.addObject("templateTitle", "공차");
        modelAndView.setViewName("root/gongcha"); //template의 "gongcha.html" 페이지를 실행시킨다.
        return modelAndView; //만들어준 ModelAndView 객체를 반환한다.
    }
}
