package mydogcherry.kr.gongcha.mappers;

import mydogcherry.kr.gongcha.entities.store.StoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IStoreMapper { //가맹점 관련 쿼리를 입력하는 Mapper

    //StoreEntity 타입으로 5개의 지역(대구, 대전, 광주, 부산 ,울산) 가맹점 조회
    List<StoreEntity> searchWholeStore();
}
