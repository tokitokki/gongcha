package mydogcherry.kr.gongcha.services;

import mydogcherry.kr.gongcha.entities.board.EventEntity;
import mydogcherry.kr.gongcha.entities.board.NoticeEntity;
import mydogcherry.kr.gongcha.mappers.IBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "mydogcherry.kr.gongcha.services.BoardService")
public class BoardService { //공차 공지사항, 이벤트에 관한 Service
    private final IBoardMapper boardMapper; //IBoardMapper 인터페이스 객체를 생성해서

    @Autowired
    public BoardService(IBoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    //해당 Service 생성자를 생성해서 Autowired 어노테이션 주입

    //해당 공지사항을 진열해주는 메서드 생성
    public ArrayList<NoticeEntity> notices() {
        //IBoardMapper에 있는 공지사항 List를 ArrayList로 강제 형변환해서 가져온다.
        return (ArrayList<NoticeEntity>) this.boardMapper.notices();
    }

    //클릭한 공지사항 글을 조회해주는 메서드 생성
    public NoticeEntity selectNotice(int index) {
        //클릭한 공지사항 정보를 담는 NoticeEntity 클래스 객체를 반환한다.
        return this.boardMapper.choiceNotice(index);
    }

    //해당 이벤트 진열해주는 메서드 생성
    public ArrayList<EventEntity> events() {
        //IBoardMapper에 있는 이벤트 List를 ArrayList로 강제 형변환해서 가져온다.
        return (ArrayList<EventEntity>) this.boardMapper.events();
    }

    //클릭한 이벤트 글을 조회해주는 메서드 생성
    public EventEntity selectEvent(int index) {
        //클릭한 이벤트 정보를 담는 NoticeEntity 클래스 객체를 반환한다.
        return this.boardMapper.choiceEvent(index);
    }
}
