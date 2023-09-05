package mydogcherry.kr.gongcha.controllers;

import mydogcherry.kr.gongcha.entities.store.StoreEntity;
import mydogcherry.kr.gongcha.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller(value = "mydogcherry.kr.gongcha.controllers.StoreController")
@RequestMapping(value = "/store")
@SessionAttributes({"searchArea", "searchGu", "searchPlace"})
//가맹점 검색 정보 목록 "searchStores"를 Model 방법을 써서 View로 전달할 때 session에도 저장해준다.
public class StoreController { //가맹점 조회에 관한 Controller

    private final StoreService storeService; //StoreService 클래스 객체를 생성해서

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    } //해당 Controller 생성자를 생성해서 Autowired 어노테이션 주입

    @GetMapping(value = "/search/{index}") //주소창에 "http://localhost:8080/store/search" 입력 시 실행되는 전체 매장 조회 Mapping
    public ModelAndView getSearch(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "index") int index //해당 지점 지도를 보여주려는 StoreEntity 클래스 객체의 index 번호
    ) {
        ArrayList<StoreEntity> searchStores = this.storeService.searchThisPlaceStore(); //전체 매장에 관한 정보를 불러온다

        //위의 index 번호 값을 Model 기능을 사용해서 View로 전송한다.
        modelAndView.addObject("index", index);

        //전체 매장 목록을 Model 기능을 사용해서 데이터를 View로 전송한다.
        modelAndView.addObject("searchStores", searchStores);
        modelAndView.setViewName("store/searchstore"); //template의 store 폴더 안의 "searchstore.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }
}
