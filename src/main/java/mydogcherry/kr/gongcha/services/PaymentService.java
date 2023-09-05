package mydogcherry.kr.gongcha.services;

import mydogcherry.kr.gongcha.entities.payment.PaymentEntity;
import mydogcherry.kr.gongcha.mappers.IPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "mydogcherry.kr.gongcha.services.PaymentService")
public class PaymentService { //결제 수단에 관한 Service
    private final IPaymentMapper paymentMapper; //IPaymentMapper 인터페이스 객체를 생성해서

    @Autowired
    public PaymentService(IPaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    } //해당 Service 생성자를 생성해서 Autowired 어노테이션 주입

    //지불 방법 번호(`index`) 값을 변수로 입력하면 해당 번호의 지불 방법에 관한 정보가 나오도록 하는 메서드 생성
    public PaymentEntity paymentChoice(int index) {
        //IPaymentMapper 인터페이스의 selectPayment 메서드를 그대로 가져온다.
        return this.paymentMapper.selectPayment(index);
    }
}
