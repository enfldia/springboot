package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class IngredientFactory {

    public Ingredient get(String menu){
        switch (menu){
            case "돈까스" :
                return  new Pork("한돈 등심");

            case "스테이크" :
                return  new Beef("한우 꽃등심");

            case "크리스피치킨" :
                return  new Beef("국내산 10호 닭");

            default :
                return null;
        }
    }
}
