package com.example.firstproject.DTO;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
public class CoffeeDto {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }


}
