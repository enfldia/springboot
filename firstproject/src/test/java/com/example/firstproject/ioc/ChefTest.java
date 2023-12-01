package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired
    IngredientFactory ingredientFactory;
    @Autowired
    Chef chef;


    @Test
    void 돈가스_요리하기(){
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";
        // 수행
        String food = chef.cook(menu);
        // 예상
        String expected = "한돈 등심으로 만든 돈가스";
        // 검증
        assertEquals(expected,food);
        System.out.println(food);
        // 스테이크가 통과하면 돈가스 요리가 탈락함
    }

    @Test
    void 스테이크_요리하기(){
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";
        // 수행
        String food = chef.cook(menu);
        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";
        // 검증
        assertEquals(expected,food);
        System.out.println(food);

    }

    @Test
    void 크리스피_치킨_요리하기(){
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);
        String menu = "크리스피 치킨";
        // 수행
        String food = chef.cook(menu);
        // 예상
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";
        // 검증
        assertEquals(expected,food);
        System.out.println(food);

    }
}
// 셰프 클래스에서 직접 재료를 만들고 있는데 - 재료를 계속 바꿔줘야 함
// 재료 공장에서 재료를 조달받을 수 있도록 IngredientFactory

// IngredientFactory 에서는 전달한 문자열에 따라 적합한 재료를 반환하도록 한다.
// Pork와 Beef는 Ingredient를 상속한다.

// 객체간 의존성이 높은 코드: 요구사항의 변경에 취약함
// DI로 코드 개선: 외부의 요구사항이 변경되어도 내부 코드 변경 X

// IOC 컨테이너에 필요한 객체를 @Compoenet로 등록하고 @Autowired를 통해 가져온다.
