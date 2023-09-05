package mydogcherry.kr.gongcha.entities.menu;

public class MugEntity{ //머그잔 정보를 저장하는 Entity

    private int index; //순서
    private String menu; //메뉴
    private int price; //가격
    private int volume; //용량
    private String content; //메뉴 설면
    private String imageFile; //메뉴 이미지 파일

    public MugEntity() {} //빈 생성자

    public MugEntity(int index, String menu, int price, int volume, String content, String imageFile) { //모든 변수를 담는 생성자
        this.index = index;
        this.menu = menu;
        this.price = price;
        this.volume = volume;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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
