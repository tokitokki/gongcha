package mydogcherry.kr.gongcha.controllers;

import mydogcherry.kr.gongcha.entities.board.EventEntity;
import mydogcherry.kr.gongcha.entities.board.NoticeEntity;
import mydogcherry.kr.gongcha.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static java.util.Calendar.*;

@Controller(value = "mydogcherry.kr.gongcha.controllers.BoardController")
@RequestMapping(value = "/board")
public class BoardController { //공지사항과 이벤트에 관한 Controller

    private final BoardService boardService; //BoardService 클래스 객체를 생성해서

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    //해당 Controller 생성자를 생성해서 Autowired 어노테이션 주입

    @GetMapping(value = "event") //주소창에 "http://localhost:8080/board/event" 입력 시 실행되는 이벤트 조회 Mapping
    public ModelAndView getEvent(ModelAndView modelAndView) {

        //모든 이벤트 정보를 EventEntity의 ArrayList 배열 객체에 담는다.
        ArrayList<EventEntity> events = this.boardService.events();

        //이벤트 시작 요일 목록을 저장하기 위한 HashMap 생성
        HashMap<Integer, String> eventStartDays = new HashMap<>();

        //이벤트 종료 요일 목록을 저장하기 위한 HashMap 생성
        HashMap<Integer, String> eventEndDays = new HashMap<>();

        for(EventEntity event : events) {
            Calendar eventStartDay = getInstance();
            eventStartDay.setTime(event.getStartAt()); //해당 이벤트 시작일

            Calendar eventEndDay = getInstance();
            eventEndDay.setTime(event.getEndAt()); //해당 이벤트 종료일

            //해당 이벤트 시작 요일을 숫자 값으로 나타낸다.
            int dayOfEventStart = eventStartDay.get(DAY_OF_WEEK);

            //해당 이벤트 종료 요일을 숫자 값으로 나타낸다.
            int dayOfEventEnd = eventEndDay.get(DAY_OF_WEEK);

            String dayOfEventStartResult = switch (dayOfEventStart) { //숫자 값에 따라 이벤트 시작 요일을 반환한다.
                case 1 -> "일";
                case 2 -> "월";
                case 3 -> "화";
                case 4 -> "수";
                case 5 -> "목";
                case 6 -> "금";
                default -> "토";
            }; //이벤트 시작 요일

            String dayOfEventEndResult = switch (dayOfEventEnd) { //숫자 값에 따라 이벤트 종료 요일을 반환한다.
                case 1 -> "일";
                case 2 -> "월";
                case 3 -> "화";
                case 4 -> "수";
                case 5 -> "목";
                case 6 -> "금";
                default -> "토";
            }; //이벤트 종료 요일

            //확인된 이벤트 시작 요일을 이벤트 시작 요일 목록 HashMap에 추가한다.
            eventStartDays.put(event.getIndex(), dayOfEventStartResult);

            //확인된 이벤트 종료 요일을 이벤트 종료 요일 목록 HashMap에 추가한다.
            eventEndDays.put(event.getIndex(), dayOfEventEndResult);
        }

        //2개의 HashMap을 Model 기능을 사용해서 데이터를 View로 전송한다.
        modelAndView.addObject("eventStartDays", eventStartDays);
        modelAndView.addObject("eventEndDays", eventEndDays);

        //전체 이벤트 목록을 Model 기능을 사용해서 데이터를 View로 전송한다.
        modelAndView.addObject("events", events);
        modelAndView.setViewName("board/event"); //template의 board 폴더 안의 "event.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "event/{index}") //이벤트 글 중 하나를 클릭 시 그 글의 내용을 조회하는 Mapping
    public ModelAndView getEventDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(name = "index", required = true) int index //주소에 있는 이벤트 글의 순번(index)를 가져온 int 타입의 변수
    ) {
        //View로 전송할 EventEntity 객체 생성
        EventEntity eventEntity = this.boardService.selectEvent(index); //index 값을 Mapper에 대입해서 나온 이벤트 글의 정보를 해당 eventEntity 객체에 저장한다.

        Calendar eventStartDay = getInstance();
        eventStartDay.setTime(eventEntity.getStartAt()); //해당 이벤트 시작일

        Calendar eventEndDay = getInstance();
        eventEndDay.setTime(eventEntity.getEndAt()); //해당 이벤트 종료일

        //해당 이벤트 시작 요일을 숫자 값으로 나타낸다.
        int dayOfEventStart = eventStartDay.get(DAY_OF_WEEK);

        //해당 이벤트 종료 요일을 숫자 값으로 나타낸다.
        int dayOfEventEnd = eventEndDay.get(DAY_OF_WEEK);

        String dayOfEventStartResult = switch (dayOfEventStart) { //숫자 값에 따라 이벤트 시작 요일을 반환한다.
            case 1 -> "일";
            case 2 -> "월";
            case 3 -> "화";
            case 4 -> "수";
            case 5 -> "목";
            case 6 -> "금";
            default -> "토";
        }; //이벤트 시작 요일

        String dayOfEventEndResult = switch (dayOfEventEnd) { //숫자 값에 따라 이벤트 종료 요일을 반환한다.
            case 1 -> "일";
            case 2 -> "월";
            case 3 -> "화";
            case 4 -> "수";
            case 5 -> "목";
            case 6 -> "금";
            default -> "토";
        }; //이벤트 종료 요일

        //반환된 이벤트 시작 요일 변수와 이벤트 종료 요일 변수를 Model 기능을 사용해서 View로 전송한다.
        modelAndView.addObject("dayOfEventStartResult", dayOfEventStartResult);
        modelAndView.addObject("dayOfEventEndResult", dayOfEventEndResult);

        //저장된 EventEntity 객체 eventEntity의 데이터를 Model 기능을 사용해서 View로 전송한다.
        modelAndView.addObject("eventEntity", eventEntity);
        modelAndView.setViewName("board/eventdetail"); //template의 board 폴더 안의 "eventdetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "notice") //주소창에 "http://localhost:8080/board/notice" 입력 시 실행되는 공지사항 조회 Mapping
    public ModelAndView getNotice(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        //모든 공지사항 정보를 NoticeEntity의 ArrayList 배열 객체에 담는다.
        ArrayList<NoticeEntity> notices = this.boardService.notices();

        //몇년 몇월 몇일인지 공지사항 index 번호를 key로, 나타내려는 문자열을 value로 하는 HashMap을 생성한다.
        HashMap<Integer, String> yearMonthDays = new HashMap<>();
        for(NoticeEntity notice : notices) {
            Date createAt = notice.getCreatedAt(); //게시글 작성일을 불러오고
            SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd"); //SimpleDateFormat 클래스의 객체를 생성해서
            String result = simple.format(createAt); //해당 날짜를 출력해준다.
            //(예시) 2022년 5월 17일의 경우는 String 타입으로 20220517로 출력된다.

            //substring을 활용해서 나타내려는 문자열로 바꿔주면서 value 값을 설정한다.
            yearMonthDays.put(notice.getIndex(),
                    result.substring(0, 4) + "년 " + result.substring(4, 6) + "월 " + result.substring(6, 8) + "일");
        }

        //yearMonthDays HashMap을 Model 기능을 사용해서 데이터를 View로 전송한다.
        modelAndView.addObject("yearMonthDays", yearMonthDays);

        //전체 공지사항 목록을 Model 기능을 사용해서 데이터를 View로 전송한다.
        modelAndView.addObject("notices", notices);
        modelAndView.setViewName("board/notice"); //template의 board 폴더 안의 "notice.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }

    @GetMapping(value = "notice/{index}") //공지사항 글 중 하나를 클릭 시 그 글의 내용을 조회하는 Mapping
    public ModelAndView getNoticeDetail(
            ModelAndView modelAndView, //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
            @PathVariable(name = "index", required = true) int index //주소에 있는 공지사항 글의 순번(index)를 가져온 int 타입의 변수
    ) {
        //NoticeEntity 클래스 객체를 생성해서
        NoticeEntity noticeEntity = this.boardService.selectNotice(index);//index 값을 Mapper에 대입해서 나온 공지사항 글의 정보를 해당 noticeEntity 객체에 저장한다.

        //저장된 NoticeEntity 객체 noticeEntity의 데이터를 Model 기능을 사용해서 View로 전송한다.
        modelAndView.addObject("noticeEntity", noticeEntity);
        modelAndView.setViewName("board/noticedetail"); //template의 board 폴더 안의 "noticedetail.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }
}
