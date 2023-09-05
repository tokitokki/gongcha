package mydogcherry.kr.gongcha.controllers;

import mydogcherry.kr.gongcha.entities.menu.MugEntity;
import mydogcherry.kr.gongcha.services.MenuService;
import mydogcherry.kr.gongcha.vos.menu.BingsuVo;
import mydogcherry.kr.gongcha.vos.menu.DessertVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

@Controller(value = "mydogcherry.kr.gongcha.controllers.OtherMenuDetailController")
@RequestMapping(value = "/menu")
public class OtherMenuDetailController { //카페음료 음료 이외 메뉴에 관한 상세 설명 Controller

    private final MenuService menuService; //MenuService 클래스 객체를 생성해서

    @Autowired
    public OtherMenuDetailController(MenuService menuService) {
        this.menuService = menuService;
    }
    //해당 Controller 생성자를 생성해서 Autowired 어노테이션 주입

    //빙수 메뉴 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "bingsu/{index}")
    public ModelAndView getBingsuDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "index") int index //해당 빙수 순번을 담는 변수 생성
    ) {
        ArrayList<BingsuVo> bingsues = this.menuService.bingsu(); //모든 빙수 메뉴 정보 데이터를 불러들이고
        HashMap<Integer, BingsuVo> matchBingsues = new HashMap<>(); //HashMap 객체를 생성해 빙수 메뉴 순번에 따라 값을 출력하도록 객체를 만든다.

        /*
            그리고 빙수 메뉴 순번에 따라 포화지방 성분 수치 중 정수 단위로 전환할 수 있는 경우((예) 6.0 같은 경우)가 있으면
            정수 단위의 포화지방 성분 양을 출력해주는 HashMap 객체를 만든다.
        */
        HashMap<Integer, Integer> roundSaturatedFatIndexs = new HashMap<>();
        for(BingsuVo bingsu : bingsues) { //향상된 for문을 활용해서
            matchBingsues.put(bingsu.getIndex(), bingsu); //HashMap 객체 matchBingsues에 빙수 정보들을 담고
            if((bingsu.getSaturatedFat() * 10) % 10 == 0) { //포화지방 성분 수치 중 정수 단위로 전환할 수 있는 경우가 있으면
                int roundSaturatedFatIndex = (int) Math.round(bingsu.getSaturatedFat());

                //HashMap 객체 roundSaturatedFatIndexs에 정수값을 반환하는 빙수 포화지방 정보를 담아준다.
                roundSaturatedFatIndexs.put(bingsu.getIndex(), roundSaturatedFatIndex);
            }
        }

        //빙수에 관한 정보를 HashMap 타입으로 만든 객체, 빙수 메뉴 순번을 Model 기능을 써서 View로 데이터를 전송해준다.
        modelAndView.addObject("matchBingsues", matchBingsues);
        modelAndView.addObject("index", index);

        modelAndView.addObject("templateTitle", matchBingsues.get(index).getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.

        /*
            정수값으로 반환된 빙수 메뉴의 포화지방 성분 값들을 담은 HashMap 객체를 Model 기능을 써서 View로 데이터를 전송해준다.
        */
        modelAndView.addObject("roundSaturatedFatIndexs", roundSaturatedFatIndexs);

        //template의 menu 폴더 안의 detail 폴더 안의 "bingsudetail.html" 페이지를 실행시킨다.
        modelAndView.setViewName("menu/detail/bingsudetail");
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //디저트 메뉴 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "dessert/{index}")
    public ModelAndView getDessertDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "index") int index //해당 빙수 순번을 담는 변수 생성
    ) {
        ArrayList<DessertVo> desserts = this.menuService.dessert(); //모든 디저트 메뉴 정보 데이터를 불러들이고
        HashMap<Integer, DessertVo> matchDesserts = new HashMap<>(); //HashMap 객체를 생성해 디저트 메뉴 순번에 따라 값을 출력하도록 객체를 만든다.

        /*
            영양 성분 종류별로 각 영양 성분 수치 중에서 정수 값으로 반환할 수 있는 디저트의 영양 성분의 수치를
            배열 index 순서대로 값을 대입해주는 HashMap 객체를 생성한다.
            [(칼로리), (당류), (단백질), (포화지방), (나트륨), (탄수화물), (트랜스지방), (지방), (콜레스테롤)] 순서이고,
            정수 값으로 반환할 수 없는 값((예) 6.5 같은 경우)의 경우는 값을 대입해주지 않는다.
        */
        HashMap<Integer, int[]> roundValues = new HashMap<>();
        for(DessertVo dessert : desserts) { //향상된 for문을 활용해서 실행한다.
            int[] roundValue = new int[9];
            if((dessert.getKcal() * 10) % 10 == 0) { //칼로리 성분의 경우
                roundValue[0] = (int) dessert.getKcal();
            }

            if((dessert.getSugars() * 10) % 10 == 0) { //당류 성분의 경우
                roundValue[1] = (int) dessert.getSugars();
            }

            if((dessert.getProtein() * 10) % 10 == 0) { //단백질 성분의 경우
                roundValue[2] = (int) dessert.getProtein();
            }

            if((dessert.getSaturatedFat() * 10) % 10 == 0) { //포화지방 성분의 경우
                roundValue[3] = (int) dessert.getSaturatedFat();
            }

            if((dessert.getNatrium() * 10) % 10 == 0) { //나트륨 성분의 경우
                roundValue[4] = (int) dessert.getNatrium();
            }

            if((dessert.getCarbohydrate() * 10) % 10 == 0) { //탄수화물 성분의 경우
                roundValue[5] = (int) dessert.getCarbohydrate();
            }

            if((dessert.getTransFat() * 10) % 10 == 0) { //트랜스지방 성분의 경우
                roundValue[6] = (int) dessert.getTransFat();
            }

            if((dessert.getFat() * 10) % 10 == 0) { //지방 성분의 경우
                roundValue[7] = (int) dessert.getFat();
            }

            if((dessert.getCholesterol() * 10) % 10 == 0) { //콜레스테롤 성분의 경우
                roundValue[8] = (int) dessert.getCholesterol();
            }
            matchDesserts.put(dessert.getIndex(), dessert); //해당 디저트 메뉴의 정보를 추가해주고
            //해당 디저트 메뉴의 정수 값으로 반환할 수 있는 디저트의 영양 성분의 수치 정보를 추가해준다.
            roundValues.put(dessert.getIndex(), roundValue);
        }

        //디저트 메뉴에 관한 정보를 HashMap 타입으로 만든 객체, 디저트 메뉴 순번을 Model 기능을 써서 View로 데이터를 전송해준다.
        modelAndView.addObject("matchDesserts", matchDesserts);
        modelAndView.addObject("index", index);

        /*
            정수값으로 반환된 디저트 메뉴의 포화지방 성분 값의 배열들을 담은 HashMap 객체를 Model 기능을 써서 View로 데이터를 전송해준다.
        */
        modelAndView.addObject("roundValues", roundValues);
        modelAndView.addObject("templateTitle", matchDesserts.get(index).getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.

        //template의 menu 폴더 안의 detail 폴더 안의 "dessertdetail.html" 페이지를 실행시킨다.
        modelAndView.setViewName("menu/detail/dessertdetail");
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    //머그잔 메뉴 중 하나를 클릭했을 때 실행되는 페이지 Mapping
    @GetMapping(value = "mug/{index}")
    public ModelAndView getMugDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(value = "index") int index //해당 빙수 순번을 담는 변수 생성
    ) {
        ArrayList<MugEntity> mugs = this.menuService.mug(); //모든 머그잔 메뉴 정보 데이터를 불러들이고
        HashMap<Integer, MugEntity> matchMugs = new HashMap<>(); //HashMap 객체를 생성해 머그잔 메뉴 순번에 따라 값을 출력하도록 객체를 만든다.
        for(MugEntity mug : mugs) { //향상된 for문을 활용해서
            matchMugs.put(mug.getIndex(), mug); //해당 머그잔 메뉴의 정보를 추가해준다.
        }

        //머그잔 메뉴에 관한 정보를 HashMap 타입으로 만든 객체, 머그잔 메뉴 순번을 Model 기능을 써서 View로 데이터를 전송해준다.
        modelAndView.addObject("matchMugs", matchMugs);
        modelAndView.addObject("index", index);
        modelAndView.addObject("templateTitle", matchMugs.get(index).getMenu()); //클릭한 페이지 제목은 클릭한 메뉴 이름으로 지정해준다.

        //template의 menu 폴더 안의 detail 폴더 안의 "mugdetail.html" 페이지를 실행시킨다.
        modelAndView.setViewName("menu/detail/mugdetail");
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }
}
