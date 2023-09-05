package mydogcherry.kr.gongcha.vos.menu;

import mydogcherry.kr.gongcha.entities.menu.BingsuEntity;

public class BingsuVo extends BingsuEntity { //빙수 정보 저장 Entity를 상속한 VO

    private String allergyIntroductionFood; //알레르기 유발 식품 설명

    public BingsuVo() {} //빈 생성자

    public BingsuVo(int index, String menu, int price, int amount, int kcal, int sugars,
                    int protein, double saturatedFat, int natrium, String content, String imageFile) { //빙수 정보 저장 Entity의 모든 변수를 상속받는 생성자
        super(index, menu, price, amount, kcal, sugars, protein, saturatedFat, natrium, content, imageFile);
    }

    //추가하는 변수의 getter / setter 생성
    public String getAllergyIntroductionFood() {
        return allergyIntroductionFood;
    }

    public void setAllergyIntroductionFood(String allergyIntroductionFood) {
        this.allergyIntroductionFood = allergyIntroductionFood;
    }
}
