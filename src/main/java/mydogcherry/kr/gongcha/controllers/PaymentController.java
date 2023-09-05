package mydogcherry.kr.gongcha.controllers;

import mydogcherry.kr.gongcha.entities.payment.PaymentEntity;
import mydogcherry.kr.gongcha.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "mydogcherry.kr.gongcha.controllers.PaymentController")
@RequestMapping(value = "/payment")
public class PaymentController { //결제 수단 정보에 관한 Controller
    private final PaymentService paymentService; //PaymentService 클래스 객체를 생성해서

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    } //해당 Controller 생성자를 생성해서 Autowired 어노테이션 주입

    @GetMapping(value = "consumemethod") //주소창에 "http://localhost:8080/payment/consumemethod" 입력 시 실행되는 전체 매장 조회 Mapping
    public ModelAndView getConsumeMethod(
            ModelAndView modelAndView //Model 기능과 View 설정 기능을 모두 담는 ModelAndView 객체 생성
    ) {
        PaymentEntity giftVoucher = this.paymentService.paymentChoice(1); //문화상품권으로 결제할 때의 정보를 담는 내용을 가져온다.
        PaymentEntity kakaoPay = this.paymentService.paymentChoice(2); //카카오페이로 결제할 때의 정보를 담는 내용을 가져온다.
        PaymentEntity payco = this.paymentService.paymentChoice(3); //페이코로 결제할 때의 정보를 담는 내용을 가져온다.

        //담은 3개의 결제 정보 데이터를 Model 기능을 사용해서 View로 전송한다.
        modelAndView.addObject("giftVoucher", giftVoucher);
        modelAndView.addObject("kakaoPay", kakaoPay);
        modelAndView.addObject("payco", payco);
        modelAndView.setViewName("payment/introducepayment.html"); //template의 payment 폴더 안의 "introducepayment.html" 페이지를 실행시킨다.
        return modelAndView; //설정해준 ModelAndView 객체를 반환한다.
    }
}
