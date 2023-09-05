package mydogcherry.kr.gongcha.entities.menu;

public class DessertEntity { //디저트 정보를 저장하는 Entity

    private int index; //순서
    private String menu; //메뉴
    private int price; //가격
    private int amount; //양
    private double kcal; //칼로리
    private double sugars; //당류
    private double protein; //단백질
    private double saturatedFat; //포화지방
    private double natrium; //나트륨
    private double carbohydrate; //탄수화물
    private double transFat; //트랜스지방
    private double fat; //지방
    private double cholesterol; //콜레스테롤
    private String content; //메뉴 설명
    private String imageFile; //메뉴 이미지 파일

    public DessertEntity() {} //빈 생성자

    public DessertEntity(int index, String menu, int price, int amount, double kcal, double sugars, double protein, double saturatedFat, double natrium, double carbohydrate, double transFat, double fat, double cholesterol, String content, String imageFile) { //모든 변수를 담는 생성자
        this.index = index;
        this.menu = menu;
        this.price = price;
        this.amount = amount;
        this.kcal = kcal;
        this.sugars = sugars;
        this.protein = protein;
        this.saturatedFat = saturatedFat;
        this.natrium = natrium;
        this.carbohydrate = carbohydrate;
        this.transFat = transFat;
        this.fat = fat;
        this.cholesterol = cholesterol;
        this.content = content;
        this.imageFile = imageFile;
    }

    //각 변수들의 getter / setter 생성
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getNatrium() {
        return natrium;
    }

    public void setNatrium(double natrium) {
        this.natrium = natrium;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getTransFat() {
        return transFat;
    }

    public void setTransFat(double transFat) {
        this.transFat = transFat;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
