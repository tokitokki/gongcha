package mydogcherry.kr.gongcha.vos.menu;

import mydogcherry.kr.gongcha.entities.menu.DessertEntity;

public class DessertVo extends DessertEntity { //디저트 정보 저장 Entity를 상속한 VO
    private String allergyIntroductionFood; //알레르기 유발 식품 설명

    public DessertVo() {} //빈 생성자

    public DessertVo(int index, String menu, int price, int amount, double kcal, double sugars, double protein, double saturatedFat, double natrium, double carbohydrate, double transFat, double fat, double cholesterol, String content, String imageFile) { //디저트 정보 저장 Entity의 모든 변수를 상속받는 생성자
        super(index, menu, price, amount, kcal, sugars, protein, saturatedFat, natrium, carbohydrate, transFat, fat, cholesterol, content, imageFile);
    }

    //추가하는 변수의 getter / setter 생성
    public String getAllergyIntroductionFood() {
        return allergyIntroductionFood;
    }

    public void setAllergyIntroductionFood(String allergyIntroductionFood) {
        this.allergyIntroductionFood = allergyIntroductionFood;
    }
}
