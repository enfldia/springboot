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
    void 돈까스_요리하기() {
        //준비
        //IngredientFactory ingredientfatory = new IngredientFactory();
        // 위에 생성자를 @Autowired로 의존성을 부여해서 주석 처리해도 정상 작동한다.
        //Chef chef = new Chef(ingredientfatory);
        // 위에 생성자를 @Autowired로 의존성을 부여해서 주석 처리해도 정상 작동한다.

        String menu = "돈까스";
        //수행
        String food = chef.cook(menu);
        //예상
        String expected = "한돈 등심으로 만든 돈까스";
        //검증
        assertEquals(expected, food);
        System.out.println(food);
        //스테이크가 통과하면 돈까스 요리가 탈락함.
    }

    @Test
    void 스테이크_요리하기(){
        //준비
        //IngredientFactory ingredientfatory = new IngredientFactory();
        //Chef chef = new Chef(ingredientfatory);
        String menu = "스테이크";
        //수행
        String food = chef.cook(menu);
        //예상
        String expected = "한우 꽃등심으로 만든 스테이크";
        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 크리스피치킨_요리하기(){
        //IngredientFactory ingredientfatory = new IngredientFactory();
        //Chef chef = new Chef(ingredientfatory);
        String menu = "크리스피치킨";
        //수행
        String food = chef.cook(menu);
        //예상
        String expected = "국내산 10호 닭으로 만든 크리스피치킨";
        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }
}
//chef 클래스에서 직접 재료를 만들고 있는데 - 재료를 계속 바꿔줘야함
//재료공장에서 재료를 조달 받을 수 있도록 IngredientFactory를 만듬

//IngredientFactory 메서드는 전달한 문자열에 따라
//적합한 재료를 반환하도록 한다.
//Pork 와 Beef 는 Ingredient 를 상속한다.

//객체간 의존성이 높은 코드 : 요구사항에 변경에 취약함
//DI 로 코드 개선 : 외부의 요구사항이 변경되어도 내부 코드 변경 x

//IOC 컨테이너에 필요한 객체를
//@Commponent로 등록하고 @Autowired를 통해 가져온다