package mydogcherry.kr.gongcha.entities.board;

import java.util.Date;

public class NoticeEntity { //공지사항 정보를 저장하는 Entity

    private int index; //글 번호
    private String title; //제목
    private Date createdAt; //작성 일자

    public NoticeEntity() {} //빈 생성자

    public NoticeEntity(int index, String title, Date createdAt) { //모든 변수를 담는 생성자
        this.index = index;
        this.title = title;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
