package mydogcherry.kr.gongcha.services;

import mydogcherry.kr.gongcha.entities.menu.BeverageEntity;
import mydogcherry.kr.gongcha.entities.menu.BingsuEntity;
import mydogcherry.kr.gongcha.entities.menu.DessertEntity;
import mydogcherry.kr.gongcha.entities.menu.MugEntity;
import mydogcherry.kr.gongcha.enums.BeverageSizeResult;
import mydogcherry.kr.gongcha.enums.HotIceSelectResult;
import mydogcherry.kr.gongcha.mappers.IMenuMapper;
import mydogcherry.kr.gongcha.vos.menu.BeverageVo;
import mydogcherry.kr.gongcha.vos.menu.BingsuVo;
import mydogcherry.kr.gongcha.vos.menu.DessertVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "mydogcherry.kr.gongcha.services.MenuService")
public class MenuService { //공차 카페음료 메뉴에 관한 Service
    private final IMenuMapper menuMapper; //IMenuMapper 인터페이스 객체를 생성해서

    @Autowired
    public MenuService(IMenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
    //해당 Service 생성자를 생성해서 Autowired 어노테이션 주입

    //해당 카페음료 종류를 진열해주는 메서드 생성
    public ArrayList<BeverageEntity> beverageType(String type) {
        //IMenuMapper에 있는 음료 종류별로 메뉴 진열하는 List를 ArrayList로 강제 형변환해서 가져온다.
        return (ArrayList<BeverageEntity>) this.menuMapper.selectBeverageType(type);
    }

    //해당 카페음료 사진을 클릭했을 때 클릭한 카페음료를 조회해주는 매서드 생성 (BeverageVo 타입의 객체 반환)
    public BeverageVo selectBeverage(int beverageIndex, BeverageSizeResult size, HotIceSelectResult hotIce) {
        BeverageEntity beverage; //BeverageEntity 클래스 객체 beverage 생성
        if(size == BeverageSizeResult.LARGE && hotIce == HotIceSelectResult.ICE) { //냉음료, Large 사이즈일 때
            beverage = this.menuMapper.selectBeverageIceLarge(beverageIndex); //해당 가격과 성분을 포함한 BeverageEntity 객체를 가져온다.
        } else if(size == BeverageSizeResult.JUMBO && hotIce == HotIceSelectResult.ICE) { //냉음료, Jumbo 사이즈일 때
            beverage = this.menuMapper.selectBeverageIceJumbo(beverageIndex); //해당 가격과 성분을 포함한 BeverageEntity 객체를 가져온다.
        } else if(size == BeverageSizeResult.LARGE && hotIce == HotIceSelectResult.HOT) { //온음료, Large 사이즈일 때
            beverage = this.menuMapper.selectBeverageHotLarge(beverageIndex); //해당 가격과 성분을 포함한 BeverageEntity 객체를 가져온다.
        } else { //온음료, Jumbo 사이즈일 때
            beverage = this.menuMapper.selectBeverageHotJumbo(beverageIndex); //해당 가격과 성분을 포함한 BeverageEntity 객체를 가져온다.
        }

        //BeverageVo 타입의 beverageAdd 객체를 beverage 객체를 BeverageVo 클래스 타입으로 강제 형변환해서 가져온다.
        BeverageVo beverageAdd = new BeverageVo(); //빈 BeverageVo 객체를 만들어서
        //BeverageEntity 객체 beverage에 담긴 정보들을 각각 옮겨준다.
        beverageAdd.setIndex(beverage.getIndex());
        beverageAdd.setMenu(beverage.getMenu());
        beverageAdd.setType(beverage.getType());
        beverageAdd.setPrice(beverage.getPrice());
        beverageAdd.setAmount(beverage.getAmount());
        beverageAdd.setKcal(beverage.getKcal());
        beverageAdd.setSugars(beverage.getSugars());
        beverageAdd.setProtein(beverage.getProtein());
        beverageAdd.setSaturatedFat(beverage.getSaturatedFat());
        beverageAdd.setNatrium(beverage.getNatrium());
        beverageAdd.setCaffeine(beverage.getCaffeine());
        beverageAdd.setContent(beverage.getContent());
        beverageAdd.setImageFile(beverage.getImageFile());
        beverageAdd.setSizeResult(size); //정한 카페음료 사이즈를 beverageAdd 객체의 sizeResult 변수에 담아준다.
        beverageAdd.setSelectResult(hotIce); //냉커피인지 온커피인지 선택한 내용을 beverageAdd 객체의 selectResult 변수에 담아준다.
        beverageAdd.setAllergyIntroductionFood(this.menuMapper.checkBeverageAllergy(beverageIndex)); //해당 카페음료의 알레르기 유발 식품 검사 결과를 가져온다.
        return beverageAdd; //설정한 beverageAdd 객체를 반환한다.
    }

    //빙수 메뉴 전체를 알레르기 유발 식품을 검사해서 ArrayList에 담는 매서드 생성 (ArrayList<BingsuVo> 타입의 객체 반환)
    public ArrayList<BingsuVo> bingsu() {
        //IMenuMapper에 있는 빙수 메뉴 List를 ArrayList로 강제 형변환해서 가져온다.
        ArrayList<BingsuEntity> bingsues = (ArrayList<BingsuEntity>) this.menuMapper.bingsu();
        //각 빙수들의 알레르기 유발 식품을 검사해서 ArrayList에 추가하도록 ArrayList<BingsuVo> 배열 객체 bingsuesAdd를 만들어준다.
        ArrayList<BingsuVo> bingsuesAdd = new ArrayList<>();
        //bingsues 배열 객체에 각각 들어 있는 BingsuEntity 타입의 객체들을 향상된 for문을 사용해서 반복문을 돌린다.
        for(BingsuEntity bingsu : bingsues) {
            //bingsu 객체를 BingsuVo 타입으로 강제 형변환시켜서 BingsuVo 타입의 bingsuAdd 객체를 만든다.
            BingsuVo bingsuAdd = new BingsuVo(); //빈 BingsuVo 객체를 만들어서
            //BingsuEntity 객체 bingsu에 담긴 정보들을 각각 옮겨준다.
            bingsuAdd.setIndex(bingsu.getIndex());
            bingsuAdd.setMenu(bingsu.getMenu());
            bingsuAdd.setPrice(bingsu.getPrice());
            bingsuAdd.setAmount(bingsu.getAmount());
            bingsuAdd.setKcal(bingsu.getKcal());
            bingsuAdd.setSugars(bingsu.getSugars());
            bingsuAdd.setProtein(bingsu.getProtein());
            bingsuAdd.setSaturatedFat(bingsu.getSaturatedFat());
            bingsuAdd.setNatrium(bingsu.getNatrium());
            bingsuAdd.setContent(bingsu.getContent());
            bingsuAdd.setImageFile(bingsu.getImageFile());
            String words = "우유, 대두"; //모든 빙수가 알레르기 유발 식품으로 우유, 대두가 검사되었다.
            if(bingsu.getMenu().equals("실크 팥빙수")) {
                words += ", 밀, 땅콩, 이산화황"; //실크 팥빙수는 추가로 밀, 땅콩, 이산화황도 검사되었다.
            }

            //알레르기 유발 식품 검사 결과를 bingsuAdd 객체에 allergyIntroductionFood 변수에 담아주고
            bingsuAdd.setAllergyIntroductionFood(words);
            bingsuesAdd.add(bingsuAdd); //bingsuesAdd 배열 객체에 bingsuAdd 객체를 추가해준다.
        }

        return bingsuesAdd; //검사가 다 되었으면 bingsuesAdd 배열 객체를 반환한다.
    }

    //디저트 메뉴 전체를 알레르기 유발 식품을 검사해서 ArrayList에 담는 매서드 생성 (ArrayList<DessertVo> 타입의 객체 반환)
    public ArrayList<DessertVo> dessert() {
        //IMenuMapper에 있는 디저트 메뉴 List를 ArrayList로 강제 형변환해서 가져온다.
        ArrayList<DessertEntity> desserts = (ArrayList<DessertEntity>) this.menuMapper.dessert();
        //각 디저트들의 알레르기 유발 식품을 검사해서 ArrayList에 추가하도록 ArrayList<DessertVo> 배열 객체 dessertsAdd를 만들어준다.
        ArrayList<DessertVo> dessertsAdd = new ArrayList<>();
        //dessertsAdd 배열 객체에 들어 있는 DessertEntity 타입의 객체들을 향상된 for문을 사용해서 반복문을 돌린다.
        for(DessertEntity dessert : desserts) {
            //dessert 객체를 DessertVo 타입으로 강제 형변환시켜서 DessertVo 타입의 dessertAdd 객체를 만든다.
            DessertVo dessertAdd = new DessertVo(); //빈 DessertVo 객체를 만들어서
            //DessertEntity 객체 dessert에 담긴 정보들을 각각 옮겨준다.
            dessertAdd.setIndex(dessert.getIndex());
            dessertAdd.setMenu(dessert.getMenu());
            dessertAdd.setPrice(dessert.getPrice());
            dessertAdd.setAmount(dessert.getAmount());
            dessertAdd.setKcal(dessert.getKcal());
            dessertAdd.setSugars(dessert.getSugars());
            dessertAdd.setProtein(dessert.getProtein());
            dessertAdd.setSaturatedFat(dessert.getSaturatedFat());
            dessertAdd.setNatrium(dessert.getNatrium());
            dessertAdd.setCarbohydrate(dessert.getCarbohydrate());
            dessertAdd.setTransFat(dessert.getTransFat());
            dessertAdd.setFat(dessert.getFat());
            dessertAdd.setCholesterol(dessert.getCholesterol());
            dessertAdd.setContent(dessert.getContent());
            dessertAdd.setImageFile(dessert.getImageFile());
            //알레르기 식품 검사 결과를 저장하는 문자열 타입의 word 변수를 만든다.
            String words = "";

            //알레르기 유발 식품 검사 결과 (검사 결과가 해당 식품 번호에 포함되면 나온 결과를 words 변수에 이어붙인다.)
            if(!(dessert.getIndex() == 13 || dessert.getIndex() == 14)) {
                words += "밀, 우유"; //디저트 순번이 13번, 14번인 디저트 외에 밀, 우유가 검사되었다.
            }

            if(dessert.getIndex() >= 1 && dessert.getIndex() <= 8) {
                words += ", 대두"; //디저트 순번이 1번부터 8번까지인 디저트 식품들에 대두가 검사되었다.
            }

            if(dessert.getIndex() >= 2 && dessert.getIndex() <= 12) {
                words += ", 계란"; //디저트 순번이 2번부터 12번까지인 디저트 식품들에 계란이 검사되었다.
            }

            if(dessert.getIndex() == 1 || dessert.getIndex() == 2 || dessert.getIndex() == 5 || dessert.getIndex() == 6) {
                words += ", 돼지고기"; //디저트 순번이 각각 1번, 2번, 5번, 6번인 디저트 식품에 돼지고기가 검사되었다.
            }

            if(dessert.getIndex() >= 1 && dessert.getIndex() <= 5) {
                words += ", 닭고기"; //디저트 순번이 1번부터 5번까지인 디저트 식품들에 닭고기가 검사되었다.
            }

            if(dessert.getIndex() >= 3 && dessert.getIndex() <= 6) {
                words += ", 쇠고기"; //디저트 순번이 3번부터 6번까지인 디저트 식품들에 쇠고기가 검사되었다.
            }

            if(dessert.getIndex() == 2 || dessert.getIndex() == 4 || dessert.getIndex() == 5) {
                words += ", 토마토"; //디저트 순번이 각각 2번, 4번, 5번인 디저트 식품에 토마토가 검사되었다.
            }

            if(dessert.getIndex() == 9 || dessert.getIndex() == 12) {
                words += ", 호두"; //디저트 순번이 각각 9번, 13번인 디저트 식품에 호두가 검사되었다.
            }

            if(dessert.getIndex() == 1) words += ", 아황산류"; //디저트 순번이 1인 디저트 식품에 아황산류가 추가로 검사되었다.
            if(dessert.getIndex() == 6) words += ", 조개류"; //디저트 순번이 6인 디저트 식품에 조개류가 추가로 검사되었다.
            dessertAdd.setAllergyIntroductionFood(words); //검사 결과를 dessertAdd 객체에 allergyIntroductionFood 변수에 담고
            dessertsAdd.add(dessertAdd); //dessertsAdd 배열 객체에 추가한다.
        }

        return dessertsAdd; //검사가 다 되었으면 dessertsAdd 배열 객체를 반환한다.
    }

    //머그잔 종류를 진열해주는 메서드 생성
    public ArrayList<MugEntity> mug() {
        //IMenuMapper에 있는 머그잔 메뉴 List를 ArrayList로 강제 형변환해서 가져온다.
        return (ArrayList<MugEntity>) this.menuMapper.mug();
    }
}
