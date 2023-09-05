package mydogcherry.kr.gongcha.mappers;

import mydogcherry.kr.gongcha.entities.board.EventEntity;
import mydogcherry.kr.gongcha.entities.board.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IBoardMapper { //공지사항, 이벤트 글의 쿼리를 입력하는 Mapper

    List<NoticeEntity> notices(); //List<NoticeEntity> 타입으로 공지사항 조회
    NoticeEntity choiceNotice(@Param(value = "index") int index); //int 타입으로 공지사항 글 중 하나를 클릭했을 때 장치 설정
    List<EventEntity> events(); //List<EventEntity> 타입으로 이벤트 조회
    EventEntity choiceEvent(@Param(value = "index") int index); //int 타입으로 이벤트 글 중 하나를 클릭했을 때 장치 설정
}
