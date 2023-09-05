package mydogcherry.kr.gongcha.entities.store;

public class StoreEntity { //가맹점 정보를 저장하는 Entity

    private int index; //순서
    private String storeName; //지점 이름
    private String area; //지역
    private String gu; //구 & 군
    private String address; //주소
    private String mapImage; //약도 이미지

    public StoreEntity() {} //빈 생성자

    public StoreEntity(int index, String storeName, String area, String gu, String address, String mapImage) { //모든 변수를 담는 생성자
        this.index = index;
        this.storeName = storeName;
        this.area = area;
        this.gu = gu;
        this.address = address;
        this.mapImage = mapImage;
    }

    //각 변수들의 getter / setter 생성
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }
}
