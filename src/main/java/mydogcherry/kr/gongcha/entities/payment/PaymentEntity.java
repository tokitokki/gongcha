package mydogcherry.kr.gongcha.entities.payment;

public class PaymentEntity { //지불 방법 정보를 저장하는 Entity

    private int index; //순서
    private String title; //지불 방법
    private String firstContent; //첫 번째 줄 설명
    private String secondContent; //두 번째 줄 설명
    private String logoImage; //첨부 이미지

    public PaymentEntity() {} //빈 생성자

    public PaymentEntity(int index, String title, String firstContent, String secondContent, String logoImage) { //모든 변수를 담는 생성자
        this.index = index;
        this.title = title;
        this.firstContent = firstContent;
        this.secondContent = secondContent;
        this.logoImage = logoImage;
    }

    //각 변수들의 getter / setter 생성
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }
}
