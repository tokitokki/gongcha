package mydogcherry.kr.gongcha.entities.board;

import java.util.Date;

public class EventEntity { //이벤트 정보를 저장하는 Entity

    private int index; //글 번호
    private String title; //제목
    private Date startAt; //이벤트 시작 일자
    private Date endAt; //이벤트 종료 일자
    private String eventImage; //해당 이벤트 첨부 이미지

    public EventEntity() {} //빈 생성자

    public EventEntity(int index, String title, Date startAt, Date endAt, String eventImage) { //모든 변수를 담는 생성자
        this.index = index;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.eventImage = eventImage;
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

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
