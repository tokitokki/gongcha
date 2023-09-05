package mydogcherry.kr.gongcha.vos.menu;

import mydogcherry.kr.gongcha.entities.menu.BeverageEntity;
import mydogcherry.kr.gongcha.enums.BeverageSizeResult;
import mydogcherry.kr.gongcha.enums.HotIceSelectResult;

public class BeverageVo extends BeverageEntity { //카페음료 정보 저장 Entity를 상속한 VO
    private BeverageSizeResult sizeResult; //사이즈 선택
    private HotIceSelectResult selectResult; //온음료 & 냉음료 선택
    private String allergyIntroductionFood; //알레르기 유발 식품 설명

    public BeverageVo() {} //빈 생성자

    public BeverageVo(int index, String menu, String type, int price, int amount, int kcal, int sugars, int protein, double saturatedFat, int natrium, int caffeine, String content, String imageFile) { //카페음료 정보 저장 Entity의 모든 변수를 상속받는 생성자
        super(index, menu, type, price, amount, kcal, sugars, protein, saturatedFat, natrium, caffeine, content, imageFile);
    }

    //추가하는 변수들의 getter / setter 생성
    public BeverageSizeResult getSizeResult() {
        return sizeResult;
    }

    public void setSizeResult(BeverageSizeResult sizeResult) {
        this.sizeResult = sizeResult;
    }

    public HotIceSelectResult getSelectResult() {
        return selectResult;
    }

    public void setSelectResult(HotIceSelectResult selectResult) {
        this.selectResult = selectResult;
    }

    public String getAllergyIntroductionFood() {
        return allergyIntroductionFood;
    }

    public void setAllergyIntroductionFood(String allergyIntroductionFood) {
        this.allergyIntroductionFood = allergyIntroductionFood;
    }
}
