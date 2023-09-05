package mydogcherry.kr.gongcha.mappers;

import mydogcherry.kr.gongcha.entities.menu.BeverageEntity;
import mydogcherry.kr.gongcha.entities.menu.BingsuEntity;
import mydogcherry.kr.gongcha.entities.menu.DessertEntity;
import mydogcherry.kr.gongcha.entities.menu.MugEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMenuMapper { //공차 카페 메뉴에 관한 Mapper

    //List<BeverageEntity> 타입으로 선택된 카페음료 종류 조회 (종류(`type`) 컬럼의 값을 String 변수로 가져와서 대입한다.)
    List<BeverageEntity> selectBeverageType(@Param(value = "type") String type);
    //냉음료, Large 사이즈 선택 시 해당 카페음료 조회
    BeverageEntity selectBeverageIceLarge(@Param(value = "index") int index);
    //냉음료, Jumbo 사이즈 선택 시 해당 카페음료 조회
    BeverageEntity selectBeverageIceJumbo(@Param(value = "index") int index);
    //온음료, Large 사이즈 선택 시 해당 카페음료 조회
    BeverageEntity selectBeverageHotLarge(@Param(value = "index") int index);
    //온음료, Jumbo 사이즈 선택 시 해당 카페음료 조회
    BeverageEntity selectBeverageHotJumbo(@Param(value = "index") int index);
    //해당 음료의 알레르기 유발 음식을 검사
    String checkBeverageAllergy(@Param(value = "index") int index);
    List<BingsuEntity> bingsu(); //List<BingsuEntity> 타입으로 빙수 조회
    List<DessertEntity> dessert(); //List<DessertEntity> 타입으로 디저트 조회
    List<MugEntity> mug(); //List<MugEntity> 타입으로 머그잔 조회
}
