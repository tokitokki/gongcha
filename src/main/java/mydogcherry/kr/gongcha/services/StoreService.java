package mydogcherry.kr.gongcha.services;

import mydogcherry.kr.gongcha.entities.store.StoreEntity;
import mydogcherry.kr.gongcha.mappers.IStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "mydogcherry.kr.gongcha.services.StoreService")
public class StoreService { //공차 가맹점에 관한 Service
    private final IStoreMapper storeMapper; //IStoreMapper 인터페이스 객체를 생성해서

    @Autowired
    public StoreService(IStoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }
    //해당 Service 생성자를 생성해서 Autowired 어노테이션 주입

    //전체 가맹점 정보를 그대로 가져온다.
    public ArrayList<StoreEntity> searchThisPlaceStore() {
        return (ArrayList<StoreEntity>) this.storeMapper.searchWholeStore();
    }
}
