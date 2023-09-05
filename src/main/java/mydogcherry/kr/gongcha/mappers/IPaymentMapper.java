package mydogcherry.kr.gongcha.mappers;

import mydogcherry.kr.gongcha.entities.payment.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IPaymentMapper { //결제 수단에 관한 Mapper
    //PaymentEntity 타입으로 해당 지불 방법을 조회
    PaymentEntity selectPayment(@Param(value = "index") int index);
}
