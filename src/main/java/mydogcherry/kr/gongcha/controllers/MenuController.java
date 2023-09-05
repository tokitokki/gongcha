package mydogcherry.kr.gongcha.controllers;

import mydogcherry.kr.gongcha.entities.menu.BeverageEntity;
import mydogcherry.kr.gongcha.entities.menu.MugEntity;
import mydogcherry.kr.gongcha.services.MenuService;
import mydogcherry.kr.gongcha.vos.menu.BingsuVo;
import mydogcherry.kr.gongcha.vos.menu.DessertVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller(value = "mydogcherry.kr.gongcha.controllers.MenuController")
@RequestMapping(value = "/menu")
public class MenuController { //음료 메뉴에 관한 Controller

    private final MenuService menuService; //MenuService 클래스 객체를 생성해서

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    //해당 Controller 생성자를 생성해서 Autowired 어노테이션 주입

    @GetMapping(value = "seasonmenu") //주소창에 "http://localhost:8080/menu/seasonmenu" 입력 시 실행되는 시즌 음료 정보 조회 Mapping
    public ModelAndView getSeasonMenu(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //해당 종류의 BeverageEntity 배열 객체를 생성해서 해당 종류의 카페 음료를 조회하는 배열 값을 담아온다.
        ArrayList<BeverageEntity> jejuSeason = this.menuService.beverageType("2022 제주 시즌 음료");
        modelAndView.addObject("jejuSeason", jejuSeason);

        //나머지 골든 펄 시즌 음료와 봄 시즌 음료도 똑같은 방법으로 설정해준다.
        ArrayList<BeverageEntity> goldenPearl = this.menuService.beverageType("2022 골든펄 시즌 음료");
        modelAndView.addObject("goldenPearl", goldenPearl);

        ArrayList<BeverageEntity> springSeason = this.menuService.beverageType("2022 봄 시즌 음료");
        modelAndView.addObject("springSeason", springSeason);

        modelAndView.setViewName("menu/seasonmenu"); //template의 menu 폴더 안의 "seasonmenu.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "bestcombination") //주소창에 "http://localhost:8080/menu/bestcombination" 입력 시 실행되는 베스트 콤비네이션 음료 정보 조회 Mapping
    public ModelAndView getBestCombination(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //해당 종류의 BeverageEntity 배열 객체를 생성해서 해당 종류의 카페 음료를 조회하는 배열 값을 담아온다.
        ArrayList<BeverageEntity> bestCombinationMenu = this.menuService.beverageType("베스트 콤비네이션");


        //Model 기능을 사용해서 해당 ArrayList의 데이터를 View로 전송한다.
        modelAndView.addObject("bestCombinationMenu", bestCombinationMenu);
        modelAndView.setViewName("menu/bestcombination"); //template의 menu 폴더 안의 "bestcombination.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "originaltea") //주소창에 "http://localhost:8080/menu/originaltea" 입력 시 실행되는 오리지널 티 정보 조회 Mapping
    public ModelAndView getOriginalTea(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //해당 종류의 BeverageEntity 배열 객체를 생성해서 해당 종류의 카페 음료를 조회하는 배열 값을 담아온다.
        ArrayList<BeverageEntity> originalTeas = this.menuService.beverageType("오리지널 티");

        //Model 기능을 사용해서 해당 ArrayList의 데이터를 View로 전송한다.
        modelAndView.addObject("originalTeas", originalTeas);
        modelAndView.setViewName("menu/originaltea"); //template의 menu 폴더 안의 "originaltea.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "milktea") //주소창에 "http://localhost:8080/menu/milktea" 입력 시 실행되는 밀크 티 정보 조회 Mapping
    public ModelAndView getMilkTea(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //위의 getOriginalTea 메서드에서 실행한 방법과 똑같은 방법으로 실행한다.
        ArrayList<BeverageEntity> originalMilkTeas = this.menuService.beverageType("밀크티");
        modelAndView.addObject("originalMilkTeas", originalMilkTeas);
        modelAndView.setViewName("menu/milktea"); //template의 menu 폴더 안의 "milktea.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "jewel") //주소창에 "http://localhost:8080/menu/jewel" 입력 시 실행되는 쥬얼리 음료 정보 조회 Mapping
    public ModelAndView getJewel(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //해당 종류의 BeverageEntity 배열 객체를 생성해서 해당 종류의 카페 음료를 조회하는 배열 값을 담아온다.
        ArrayList<BeverageEntity> jewels = this.menuService.beverageType("쥬얼리");

        //Model 기능을 사용해서 해당 ArrayList의 데이터를 View로 전송한다.
        modelAndView.addObject("jewels", jewels);
        modelAndView.setViewName("menu/jewel"); //template의 menu 폴더 안의 "jewel.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "fruitmix") //주소창에 "http://localhost:8080/menu/fruitmix" 입력 시 실행되는 과일믹스 음료 정보 조회 Mapping
    public ModelAndView getFruitMix(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //해당 종류의 BeverageEntity 배열 객체를 생성해서 해당 종류의 카페 음료를 조회하는 배열 값을 담아온다.
        ArrayList<BeverageEntity> fruitMixes = this.menuService.beverageType("과일믹스");

        //Model 기능을 사용해서 해당 ArrayList의 데이터를 View로 전송한다.
        modelAndView.addObject("fruitMixes", fruitMixes);
        modelAndView.setViewName("menu/fruitmix"); //template의 menu 폴더 안의 "fruitmix.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "smoothie") //주소창에 "http://localhost:8080/menu/smoothie" 입력 시 실행되는 스무디 정보 조회 Mapping
    public ModelAndView getSmoothie(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        ArrayList<BeverageEntity> smoothies = this.menuService.beverageType("스무디");
        modelAndView.addObject("smoothies", smoothies);
        modelAndView.setViewName("menu/smoothie"); //template의 menu 폴더 안의 "smoothie.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "coffee") //주소창에 "http://localhost:8080/menu/coffee" 입력 시 실행되는 커피 정보 조회 Mapping
    public ModelAndView getCoffee(
        ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //해당 종류의 BeverageEntity 배열 객체를 생성해서 해당 종류의 카페 음료를 조회하는 배열 값을 담아온다.
        ArrayList<BeverageEntity> coffees = this.menuService.beverageType("커피");

        //Model 기능을 사용해서 해당 ArrayList의 데이터를 View로 전송한다.
        modelAndView.addObject("coffees", coffees);
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "/bingsu") //주소창에 "http://localhost:8080/menu/bingsu" 입력 시 실행되는 빙수 정보 조회 Mapping
    public ModelAndView getBingsu(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //빙수 메뉴의 BeverageEntity 배열 객체를 생성해서 빙수 메뉴를 조회하는 배열 값을 담아온다.
        ArrayList<BingsuVo> bingsues = this.menuService.bingsu();

        //설정한 ArrayList 배열을 Model 기능을 사용해서 데이터를 View로 전송한다.
        modelAndView.addObject("bingsues", bingsues); //template의 menu 폴더 안의 "bingsu.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "/dessert") //주소창에 "http://localhost:8080/menu/dessert" 입력 시 실행되는 디저트 정보 조회 Mapping
    public ModelAndView getDessert(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //디저트 메뉴의 BeverageEntity 배열 객체를 위와 똑같은 방법으로 실행시켜 준다.
        ArrayList<DessertVo> desserts = this.menuService.dessert();
        modelAndView.addObject("desserts", desserts); //template의 menu 폴더 안의 "dessert.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "/mug") //주소창에 "http://localhost:8080/menu/mug" 입력 시 실행되는 머그잔 정보 조회 Mapping
    public ModelAndView getMug(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //머그잔 메뉴의 BeverageEntity 배열 객체를 위와 똑같은 방법으로 실행시켜 준다.
        ArrayList<MugEntity> mugs = this.menuService.mug();
        modelAndView.addObject("mugs", mugs); //template의 menu 폴더 안의 "mug.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }
}
