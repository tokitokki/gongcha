package mydogcherry.kr.gongcha.controllers;

import mydogcherry.kr.gongcha.enums.BeverageSizeResult;
import mydogcherry.kr.gongcha.enums.HotIceSelectResult;
import mydogcherry.kr.gongcha.services.MenuService;
import mydogcherry.kr.gongcha.vos.menu.BeverageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "mydogcherry.kr.gongcha.controllers.BeverageDetailController")
@RequestMapping("/menu")
public class BeverageDetailController { //카페음료 메뉴에 관한 상세 설명 Controller

    private final MenuService menuService; //MenuService 클래스 객체를 생성해서

    @Autowired
    public BeverageDetailController(MenuService menuService) {
        this.menuService = menuService;
    }
    //해당 Controller 생성자를 생성해서 Autowired 어노테이션 주입

    //시즌 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "seasonmenu/{beverageIndex}")
    public ModelAndView getDetailSeasonMenu(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //선택한 음료의 냉음료 Large 사이즈 정보를 담는 객체 생성
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage); //생성한 객체를 Model 기능을 써서 View로 정보를 옮겨준다.
        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/seasonmenudetail"); //template의 menu 폴더 안의 detail 폴더 안의 "seasonmenudetail.html" 페이지를 실행시킨다.

        //포화지방 수치가 정수 단위로 전환할 수 있는 경우 ((예) 6.0 같은 경우) 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int round = (int) iceLargeBeverage.getSaturatedFat();
        modelAndView.addObject("round", round); //생성한 변수를 Model 기능을 써서 View로 정보를 옮겨준다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //베스트 콤비네이션 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "bestcombination/{beverageIndex}")
    public ModelAndView getBestCombinationDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //선택한 음료의 냉음료 Large 사이즈 정보를 담는 객체 생성
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);
        //선택한 음료의 냉음료 Jumbo 사이즈 정보를 담는 객체 생성
        BeverageVo iceJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.ICE);

        //2개의 객체를 Model 기능을 써서 View로 정보를 옮겨준다.
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage);
        modelAndView.addObject("iceJumboBeverage", iceJumboBeverage);

        //냉음료 Large 사이즈와 Jumbo 사이즈 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int iceLargeRound = (int) iceLargeBeverage.getSaturatedFat();
        int iceJumboRound = (int) iceJumboBeverage.getSaturatedFat();

        //생성한 2개의 변수를 Model 기능을 써서 View로 정보를 옮겨준다.
        modelAndView.addObject("iceLargeRound", iceLargeRound);
        modelAndView.addObject("iceJumboRound", iceJumboRound);

        if(index >= 18 && index <= 21) { //음료 순번이 18번부터 21번까지의 음료수는 온음료 Large 사이즈와 온음료 Jumbo 사이즈도 있으므로

            //온음료 Large 사이즈와 Jumbo 사이즈 정보들을 추가로 위와 똑같은 방법으로 만들어준다.
            BeverageVo hotLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.HOT);
            BeverageVo hotJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.HOT);
            modelAndView.addObject("hotLargeBeverage", hotLargeBeverage);
            modelAndView.addObject("hotJumboBeverage", hotJumboBeverage);
            int hotLargeRound = (int) hotLargeBeverage.getSaturatedFat();
            int hotJumboRound = (int) hotJumboBeverage.getSaturatedFat();
            modelAndView.addObject("hotLargeRound", hotLargeRound);
            modelAndView.addObject("hotJumboRound", hotJumboRound);
        }

        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/bestcombinationdetail"); //template의 menu 폴더 안의 detail 폴더 안의 "bestcombinationdetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //오리지널 티 또는 오리지널 밀크티 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "original/{beverageIndex}")
    public ModelAndView getOriginal(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //냉음료 Large 사이즈와 Jumbo 사이즈, 온음료 Large 사이즈와 Jumbo 사이즈 정보들을 담는 객체들을 생성한다.
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);
        BeverageVo iceJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.ICE);
        BeverageVo hotLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.HOT);
        BeverageVo hotJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.HOT);

        //각 냉음료, 온음료 사이즈의 포화지방 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int iceLargeRound = (int) iceLargeBeverage.getSaturatedFat();
        int iceJumboRound = (int) iceJumboBeverage.getSaturatedFat();
        int hotLargeRound = (int) hotLargeBeverage.getSaturatedFat();
        int hotJumboRound = (int) hotJumboBeverage.getSaturatedFat();

        //BeverageVo 객체 4개와 포화지방 수치를 정수 단위로 변환하는 변수 4개를 각각 Model 기능을 써서 View로 데이터를 전송한다.
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage);
        modelAndView.addObject("iceJumboBeverage", iceJumboBeverage);
        modelAndView.addObject("hotLargeBeverage", hotLargeBeverage);
        modelAndView.addObject("hotJumboBeverage", hotJumboBeverage);

        //생성한 4개의 변수를 Model 기능을 써서 View로 정보를 옮겨준다.
        modelAndView.addObject("iceLargeRound", iceLargeRound);
        modelAndView.addObject("iceJumboRound", iceJumboRound);
        modelAndView.addObject("hotLargeRound", hotLargeRound);
        modelAndView.addObject("hotJumboRound", hotJumboRound);
        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/originalbeveragedetail"); //template의 menu 폴더 안의 detail 폴더 안의 "originalbeveragedetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //쥬얼리 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "jewel/{beverageIndex}")
    public ModelAndView getJewelDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //냉음료 Large 사이즈 정보를 담는 객체들을 생성한다.
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);

        //냉음료 Large 사이즈의 포화지방 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int iceLargeRound = (int) iceLargeBeverage.getSaturatedFat();
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage);
        modelAndView.addObject("iceLargeRound", iceLargeRound);

        if(index == 39) { //메뉴 순번이 39번인 음료(브라운슈가 쥬얼리 밀크티)는 온음료 Large 사이즈도 있으므로 그러한 정보를 위와 똑같은 방법으로 추가해준다.
            BeverageVo hotLargeBeverage = this.menuService.selectBeverage(39, BeverageSizeResult.LARGE, HotIceSelectResult.HOT);
            int hotLargeRound = (int) hotLargeBeverage.getSaturatedFat();
            modelAndView.addObject("hotLargeBeverage", hotLargeBeverage);
            modelAndView.addObject("hotLargeRound", hotLargeRound);
        }

        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/jeweldetail"); //template의 menu 폴더 안의 detail 폴더 안의 "jeweldetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //과일믹스 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "fruitmix/{beverageIndex}")
    public ModelAndView getFruitMixDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //냉음료 Large 사이즈와 Jumbo 사이즈 정보를 담는 객체들을 생성한다.
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);
        BeverageVo iceJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.ICE);

        //냉음료 Large 사이즈와 Jumbo 사이즈의 포화지방 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int iceLargeRound = (int) iceLargeBeverage.getSaturatedFat();
        int iceJumboRound = (int) iceJumboBeverage.getSaturatedFat();

        //BeverageVo 객체 2개와 포화지방 수치를 정수 단위로 변환하는 변수 2개를 각각 Model 기능을 써서 View로 데이터를 전송한다.
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage);
        modelAndView.addObject("iceJumboBeverage", iceJumboBeverage);
        modelAndView.addObject("iceLargeRound", iceLargeRound);
        modelAndView.addObject("iceJumboRound", iceJumboRound);
        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/fruitmixdetail"); //template의 menu 폴더 안의 detail 폴더 안의 "fruitmixdetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //스무디 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "smoothie/{beverageIndex}")
    public ModelAndView getSmoothieDetail (
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //냉음료 Large 사이즈 정보를 담는 객체들을 생성한다.
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);

        //냉음료 Large 사이즈의 포화지방 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int round = (int) iceLargeBeverage.getSaturatedFat();

        //BeverageVo 객체와 포화지방 수치를 정수 단위로 변환하는 변수를 각각 Model 기능을 써서 View로 데이터를 전송한다.
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage);
        modelAndView.addObject("round", round);
        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/smoothiedetail"); //template의 menu 폴더 안의 detail 폴더 안의 "smoothiedetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //커피 음료 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "coffee/{beverageIndex}")
    public ModelAndView getCoffeeDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "beverageIndex", required = true) int index //해당 카페음료 순번을 담는 변수 생성
    ) {
        //냉음료 Large, 온음료 Large 사이즈 정보를 담는 객체들을 생성한다.
        BeverageVo iceLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.ICE);
        BeverageVo hotLargeBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.LARGE, HotIceSelectResult.HOT);

        //냉음료 Large, 온음료 Large 사이즈의 포화지방 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
        int iceLargeRound = (int) iceLargeBeverage.getSaturatedFat();
        int hotLargeRound = (int) hotLargeBeverage.getSaturatedFat();

        //BeverageVo 객체 2개와 포화지방 수치를 정수 단위로 변환하는 변수 2개를 각각 Model 기능을 써서 View로 데이터를 전송한다.
        modelAndView.addObject("iceLargeBeverage", iceLargeBeverage);
        modelAndView.addObject("hotLargeBeverage", hotLargeBeverage);
        modelAndView.addObject("iceLargeRound", iceLargeRound);
        modelAndView.addObject("hotLargeRound", hotLargeRound);

        if(index >= 57 && index <= 60) { //음료 순번이 57번인 음료부터 60번인 음료까지는 Jumbo 사이즈도 같이 있으므로 Jumbo 사이즈 정보도 각각 추가해준다.
            //냉음료 Jumbo, 온음료 Jumbo 사이즈 정보를 담는 객체들을 생성한다.
            BeverageVo iceJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.ICE);
            BeverageVo hotJumboBeverage = this.menuService.selectBeverage(index, BeverageSizeResult.JUMBO, HotIceSelectResult.HOT);

            //냉음료 Jumbo, 온음료 Jumbo 사이즈의 포화지방 중 포화지방 수치가 정수 단위로 전환할 수 있는 경우 정수 값으로 변환해서 값을 측정한 변수를 생성한다.
            int iceJumboRound = (int) iceJumboBeverage.getSaturatedFat();
            int hotJumboRound = (int) hotJumboBeverage.getSaturatedFat();

            //BeverageVo 객체 2개와 포화지방 수치를 정수 단위로 변환하는 변수 2개를 각각 Model 기능을 써서 View로 데이터를 전송한다.
            modelAndView.addObject("iceJumboBeverage", iceJumboBeverage);
            modelAndView.addObject("hotJumboBeverage", hotJumboBeverage);
            modelAndView.addObject("iceJumboRound", iceJumboRound);
            modelAndView.addObject("hotJumboRound", hotJumboRound);
        }

        modelAndView.addObject("templateTitle", iceLargeBeverage.getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.
        modelAndView.setViewName("menu/detail/coffeedetail"); //template의 menu 폴더 안의 detail 폴더 안의 "coffeedetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }
}
